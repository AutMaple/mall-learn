package com.autmaple.mall.tiny.service.Impl;

import cn.hutool.core.util.StrUtil;
import com.autmaple.mall.tiny.dao.PmsSkuStockDao;
import com.autmaple.mall.tiny.mbg.mapper.PmsSkuStockMapper;
import com.autmaple.mall.tiny.mbg.model.PmsSkuStock;
import com.autmaple.mall.tiny.mbg.model.PmsSkuStockExample;
import com.autmaple.mall.tiny.service.PmsSkuStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName PmsSkuStockServiceImpl
 * @Description 商品 Sku 库存管理 Service 实现类
 * @Author AutMaple
 * @Date 2022/7/17 09:46
 * @Version 1.0
 **/
@Service
public class PmsSkuStockServiceImpl implements PmsSkuStockService {

    @Autowired
    private PmsSkuStockMapper skuStockMapper;

    @Autowired
    private PmsSkuStockDao skuStockDao;


    @Override
    public List<PmsSkuStock> getList(Long pid, String keyword) {
        PmsSkuStockExample example = new PmsSkuStockExample();
        if (StrUtil.isNotEmpty(keyword)) {
            example.or().andProductIdEqualTo(pid).andSkuCodeLike("%" + keyword + "%");
        }
        return skuStockMapper.selectByExample(example);
    }

    @Override
    public int update(Long id, List<PmsSkuStock> skuStockList) {
        return skuStockDao.replaceList(skuStockList);
    }
}
