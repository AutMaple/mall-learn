package com.autmaple.mall.tiny.service.Impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.autmaple.mall.tiny.dao.*;
import com.autmaple.mall.tiny.dto.PmsProductParam;
import com.autmaple.mall.tiny.dto.PmsProductQueryParam;
import com.autmaple.mall.tiny.dto.PmsProductResult;
import com.autmaple.mall.tiny.mbg.mapper.*;
import com.autmaple.mall.tiny.mbg.model.*;
import com.autmaple.mall.tiny.service.PmsProductService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName PmsProductServiceImpl
 * @Description 商品管理 Service 实现类
 * @Author AutMaple
 * @Date 2022/7/16 19:42
 * @Version 1.0
 **/
@Service
public class PmsProductServiceImpl implements PmsProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PmsProductServiceImpl.class);

    @Autowired
    private PmsProductMapper productMapper;
    @Autowired
    private PmsProductDao productDao;

    @Autowired
    private PmsMemberPriceMapper memberPriceMapper;
    @Autowired
    private PmsMemberPriceDao memberPriceDao;

    @Autowired
    private PmsProductLadderMapper productLadderMapper;
    @Autowired
    private PmsProductLadderDao productLadderDao;

    @Autowired
    private PmsProductFullReductionMapper productFullReductionMapper;
    @Autowired
    private PmsProductFullReductionDao productFullReductionDao;

    @Autowired
    private PmsSkuStockMapper skuStockMapper;
    @Autowired
    private PmsSkuStockDao skuStockDao;

    @Autowired
    private PmsProductAttributeValueMapper productAttributeValueMapper;
    @Autowired
    private PmsProductAttributeValueDao productAttributeValueDao;

    @Autowired
    private CmsSubjectProductRelationMapper subjectProductRelationMapper;
    @Autowired
    private CmsSubjectProductRelationDao subjectProductRelationDao;

    @Autowired
    private CmsPrefrenceAreaProductRelationMapper preferenceAreaProductRelationMapper;
    @Autowired
    private CmsPreferenceAreaProductRelationDao preferenceAreaProductRelationDao;

    @Autowired
    private PmsProductVerifyRecordDao productVerifyRecordDao;


    @Override
    public int create(PmsProductParam productParam) {
        PmsProduct product = new PmsProduct();
        product.setId(null);
        productMapper.insertSelective(product);
        // 根据促销类型设置价格: 会员价格、阶梯价格、満减价格
        Long productId = product.getId();

        // 会员价格
        relateAndInsertList(memberPriceDao, productParam.getMemberPriceList(), productId);
        //阶梯价格
        relateAndInsertList(productLadderDao, productParam.getProductLadderList(), productId);
        //満减价格
        relateAndInsertList(productFullReductionDao, productParam.getProductFullReductionList(), productId);
        //处理 sku 的编码
        handleSkuStockCode(productParam.getSkuStockList(), productId);
        //处理 sku 库存信息
        relateAndInsertList(skuStockDao, productParam.getSkuStockList(), productId);
        //添加商品参数，添加自定义商品规格
        relateAndInsertList(productAttributeValueDao, productParam.getProductAttributeValueList(), productId);
        //关联主题
        relateAndInsertList(subjectProductRelationDao, productParam.getSubjectProductRelationList(), productId);
        //关联优选
        relateAndInsertList(preferenceAreaProductRelationDao, productParam.getPreferenceAreaProductRelationList(), productId);
        return 1;
    }

    /**
     * @param dao       可以操作的 dao
     * @param dataList  要插入的数据
     * @param productId 建立关系的 ID
     * @Author AutMaple
     * @Description 简历和插入关系表操作
     * @Date 2022/7/16 21:29
     **/
    private void relateAndInsertList(Object dao, List dataList, Long productId) {
        if (CollectionUtils.isEmpty(dataList)) return;
        try {
            for (Object item : dataList) {
                Method setId = item.getClass().getMethod("setId", Long.class);
                setId.invoke(item, (Long) null);
                Method setProductId = item.getClass().getMethod("insertList", List.class);
                setProductId.invoke(item, productId);
            }
            Method insertList = dao.getClass().getMethod("insertList", List.class);
            insertList.invoke(dao, dataList);
        } catch (Exception e) {
            LOGGER.warn("创建产品出错:{}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    private void handleSkuStockCode(List<PmsSkuStock> skuStockList, Long productId) {
        if (CollectionUtils.isEmpty(skuStockList)) return;
        for (int i = 0; i < skuStockList.size(); i++) {
            PmsSkuStock skuStock = skuStockList.get(i);
            if (StrUtil.isEmpty(skuStock.getSkuCode())) {
                SimpleDateFormat format = new SimpleDateFormat("yyyMMdd");
                StringBuilder sb = new StringBuilder();
                //日期
                sb.append(format.format(new Date()));
                // 四位商品 ID
                sb.append(String.format("%04d", productId));
                // 三位索引 ID
                sb.append(String.format("%03d", i + 1));
                skuStock.setSkuCode(sb.toString());
            }
        }
    }

    private void handleUpdateSkuStockList(Long id, PmsProductParam productParam) {
        // 当期的 sku 信息
        List<PmsSkuStock> currSkuList = productParam.getSkuStockList();
        // 当前没有 sku 直接删除
        if (CollUtil.isEmpty(currSkuList)) {
            PmsSkuStockExample stockExample = new PmsSkuStockExample();
            stockExample.or().andProductIdEqualTo(id);
            skuStockMapper.deleteByExample(stockExample);
            return;
        }
        // 获取初始 sku 信息
        PmsSkuStockExample stockExample = new PmsSkuStockExample();
        stockExample.or().andProductIdEqualTo(id);
        List<PmsSkuStock> originSkuList = skuStockMapper.selectByExample(stockExample);
        // 获取新增 sku 信息
        List<PmsSkuStock> insertSkuList = currSkuList.stream().filter(item -> item.getId() == null).collect(Collectors.toList());
        // 获取需要更新的 sku 信息
        List<PmsSkuStock> updateSkuList = currSkuList.stream().filter(item -> item.getId() != null).collect(Collectors.toList());
        List<Long> updateSkuIds = updateSkuList.stream().map(PmsSkuStock::getId).collect(Collectors.toList());

        List<PmsSkuStock> removeSkuList = originSkuList.stream().filter(item -> !updateSkuIds.contains(item.getId())).collect(Collectors.toList());

        handleSkuStockCode(insertSkuList, id);
        handleSkuStockCode(updateSkuList, id);

        if (CollUtil.isNotEmpty(insertSkuList))
            relateAndInsertList(skuStockDao, insertSkuList, id);
        if (CollUtil.isNotEmpty(removeSkuList)) {
            List<Long> removeSkuIds = removeSkuList.stream().map(PmsSkuStock::getId).collect(Collectors.toList());
            PmsSkuStockExample example = new PmsSkuStockExample();
            example.or().andIdIn(removeSkuIds);
            skuStockMapper.deleteByExample(example);
        }
        // 修改 sku
        if (CollUtil.isNotEmpty(updateSkuIds))
            for (PmsSkuStock skuStock : updateSkuList)
                skuStockMapper.updateByPrimaryKey(skuStock);
    }

    @Override
    public PmsProductResult getUpdateInfo(Long id) {
        return productDao.getUpdateInfo(id);
    }

    @Override
    public int update(Long id, PmsProductParam productParam) {
        int count;
        // 更新商品信息
        productParam.setId(id);
        productMapper.updateByPrimaryKeySelective(productParam);

        // 会员价格
        PmsMemberPriceExample memberPriceExample = new PmsMemberPriceExample();
        memberPriceExample.or().andProductIdEqualTo(id);
        memberPriceMapper.deleteByExample(memberPriceExample);
        relateAndInsertList(memberPriceDao, productParam.getMemberPriceList(), id);

        //阶梯价格
        PmsProductLadderExample ladderExample = new PmsProductLadderExample();
        ladderExample.or().andProductIdEqualTo(id);
        productLadderMapper.deleteByExample(ladderExample);
        relateAndInsertList(productLadderDao, productParam.getProductLadderList(), id);

        // 満减价格
        PmsProductFullReductionExample reductionExample = new PmsProductFullReductionExample();
        reductionExample.or().andProductIdEqualTo(id);
        productFullReductionMapper.deleteByExample(reductionExample);
        relateAndInsertList(productFullReductionMapper, productParam.getProductFullReductionList(), id);

        // 修改 sku 库存信息
        handleUpdateSkuStockList(id, productParam);

        // 修改商品参数，添加自定义商品规格
        PmsProductAttributeValueExample attributeExample = new PmsProductAttributeValueExample();
        attributeExample.or().andProductIdEqualTo(id);
        productAttributeValueMapper.deleteByExample(attributeExample);
        relateAndInsertList(productAttributeValueDao, productParam.getProductAttributeValueList(), id);

        // 关联主题
        CmsSubjectProductRelationExample relationExample = new CmsSubjectProductRelationExample();
        relationExample.or().andProductIdEqualTo(id);
        subjectProductRelationMapper.deleteByExample(relationExample);
        relateAndInsertList(subjectProductRelationDao, productParam.getSubjectProductRelationList(), id);

        // 关联优选
        CmsPrefrenceAreaProductRelationExample preferenceExample = new CmsPrefrenceAreaProductRelationExample();
        preferenceExample.or().andProductIdEqualTo(id);
        preferenceAreaProductRelationMapper.deleteByExample(preferenceExample);
        relateAndInsertList(preferenceAreaProductRelationDao, productParam.getPreferenceAreaProductRelationList(), id);
        return 1;
    }

    @Override
    public List<PmsProduct> list(PmsProductQueryParam productQueryParam, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        PmsProductExample productExample = new PmsProductExample();
        PmsProductExample.Criteria criteria = productExample.or().andDeleteStatusEqualTo(0);
        if (productQueryParam.getPublicStatus() != null)
            criteria.andPublishStatusEqualTo(productQueryParam.getPublicStatus());

        if (productQueryParam.getVerifyStatus() != null)
            criteria.andVerifyStatusEqualTo(productQueryParam.getVerifyStatus());
        if (!StrUtil.isEmpty(productQueryParam.getKeyword()))
            criteria.andNameLike("%" + productQueryParam.getKeyword() + "%");
        if (!StrUtil.isEmpty(productQueryParam.getProductSn()))
            criteria.andProductSnEqualTo(productQueryParam.getProductSn());
        if (productQueryParam.getBrandId() != null)
            criteria.andBrandIdEqualTo(productQueryParam.getBrandId());
        if (productQueryParam.getProductCategoryId() != null)
            criteria.andProductCategoryIdEqualTo(productQueryParam.getProductCategoryId());

        return productMapper.selectByExample(productExample);

    }

    @Override
    public int updateVerifyStatus(List<Long> ids, Integer verifyStatus, String detail) {
        PmsProduct product = new PmsProduct();
        product.setVerifyStatus(verifyStatus);
        PmsProductExample example = new PmsProductExample();
        example.or().andIdIn(ids);
        List<PmsProductVertifyRecord> recordList = new ArrayList<>();
        int count = productMapper.updateByExampleSelective(product, example);
        for (Long id : ids) {
            PmsProductVertifyRecord record = new PmsProductVertifyRecord();
            record.setProductId(id);
            record.setCreateTime(new Date());
            record.setDetail(detail);
            record.setStatus(verifyStatus);
            record.setVertifyMan("AutMaple");
            recordList.add(record);
        }
        productVerifyRecordDao.insertList(recordList);
        return count;
    }

    @Override
    public int updatePublishStatus(List<Long> ids, Integer publishStatus) {
        PmsProduct product = new PmsProduct();
        product.setPublishStatus(publishStatus);
        PmsProductExample example = new PmsProductExample();
        example.or().andIdIn(ids);
        return productMapper.updateByExampleSelective(product, example);
    }

    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        PmsProduct product = new PmsProduct();
        product.setRecommandStatus(recommendStatus);
        PmsProductExample example = new PmsProductExample();
        example.or().andIdIn(ids);
        return productMapper.updateByExampleSelective(product, example);
    }

    @Override
    public int updateNewStatus(List<Long> ids, Integer newStatus) {
        PmsProduct product = new PmsProduct();
        product.setNewStatus(newStatus);
        PmsProductExample example = new PmsProductExample();
        example.or().andIdIn(ids);
        return productMapper.updateByExampleSelective(product, example);
    }

    @Override
    public int updateDeleteStatus(List<Long> ids, Integer deleteStatus) {
        PmsProduct product = new PmsProduct();
        product.setDeleteStatus(deleteStatus);
        PmsProductExample example = new PmsProductExample();
        example.or().andIdIn(ids);
        return productMapper.updateByExampleSelective(product, example);
    }

    @Override
    public List<PmsProduct> list(String keyword) {
        PmsProductExample example = new PmsProductExample();
        PmsProductExample.Criteria criteria = example.or().andDeleteStatusEqualTo(0);
        if (StrUtil.isNotEmpty(keyword)) {
            criteria.andNameLike("%" + keyword + "%");
            example.or().andDeleteStatusEqualTo(0).andProductSnLike("%" + keyword + "$");
        }
        return productMapper.selectByExample(example);
    }
}
