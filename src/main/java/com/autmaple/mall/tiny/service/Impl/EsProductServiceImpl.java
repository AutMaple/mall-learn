package com.autmaple.mall.tiny.service.Impl;

import com.autmaple.mall.tiny.dao.EsProductDao;
import com.autmaple.mall.tiny.nosql.elasticsearch.document.EsProduct;
import com.autmaple.mall.tiny.nosql.elasticsearch.repository.EsProductRepository;
import com.autmaple.mall.tiny.service.EsProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @ClassName EsProductServiceImpl
 * @Description 商品搜索管理 Service 实现类
 * @Author AutMaple
 * @Date 2022/6/19 20:37
 * @Version 1.0
 **/
@Service
public class EsProductServiceImpl implements EsProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EsProductServiceImpl.class);

    @Autowired
    private EsProductDao productDao;

    @Autowired
    private EsProductRepository productRepository;

    /**
     * @Author AutMaple
     * @Description TODO 返回所有的商品列表
     * @Date 2022/6/19 20:48
     * @return 返回商品的总数
     **/
    @Override
    public int importAll() {
        List<EsProduct> esProductList = productDao.getAllEsProductList(null);
        Iterable<EsProduct> esProductIterable = productRepository.saveAll(esProductList);
        Iterator<EsProduct> iterator = esProductIterable.iterator();
        int result = 0;
        while(iterator.hasNext()){
            result++;
            iterator.next();
        }
        return result;
    }

    /**
     * @Author AutMaple
     * @Description TODO 根据 id 删除产品
     * @Date 2022/6/19 20:51
     * @param id 商品的唯一标识
     **/
    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    /**
     * @Author AutMaple
     * @Description TODO 创建商品
     * @Date 2022/6/19 20:56
     * @param id 商品的唯一标识
     * @return 创建成功之后的 EsProduct 实例对象
     **/
    @Override
    public EsProduct create(Long id) {
        List<EsProduct> esProductList = productDao.getAllEsProductList(id);
        return esProductList.size() > 0 ? productRepository.save(esProductList.get(0)): null;
    }

    /**
     * @Author AutMaple
     * @Description TODO 批量删除商品
     * @Date 2022/6/19 21:00
     * @param ids 将要删除商品的 id 列表
     **/
    @Override
    public void delete(List<Long> ids) {
        if(!CollectionUtils.isEmpty(ids)){
            ArrayList<EsProduct> esProductList = new ArrayList<>();
            for (Long id : ids) {
                EsProduct esProduct = new EsProduct();
                esProduct.setId(id);
                esProductList.add(esProduct);
            }
            productRepository.deleteAll(esProductList);
        }
    }

    /**
     * @Author AutMaple
     * @Description TODO 根据关键字搜索名称或者副标题
     * @Date 2022/6/19 21:04
     * @param keyword 关键字
     * @param pageNum 页码
     * @param pageSize 页面大小
     * @return 搜索到的商品
     **/
    @Override
    public Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize) {
        Pageable pageRequest = PageRequest.of(pageNum, pageSize);
        return productRepository.findByNameOrSubTitleOrKeywords(keyword, keyword,keyword, pageRequest);
    }
}
