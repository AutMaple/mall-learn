package com.autmaple.mall.tiny.controller;

import com.autmaple.mall.tiny.common.api.CommonPage;
import com.autmaple.mall.tiny.common.api.CommonResult;
import com.autmaple.mall.tiny.dto.PmsPortalProductDetail;
import com.autmaple.mall.tiny.dto.PmsProductCategoryNode;
import com.autmaple.mall.tiny.mbg.model.PmsProduct;
import com.autmaple.mall.tiny.service.PmsPortalProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @ClassName PmsPortalProductController
 * @Description 前台商品管理Controller
 * @Author AutMaple
 * @Date 2022/7/16 12:47
 * @Version 1.0
 **/
@Tag(description = "PmsPortalProductController", name = "前台商品管理")
@RestController
@RequestMapping("/product")
public class PmsPortalProductController {

    @Autowired
    private PmsPortalProductService portalProductService;

    //    @ApiImplicitParams({
//            @ApiImplicitParam(
//                    name = "sort",
//                    value = "排序字段:0->按相关度；1->按新品；2->按销量；3->价格从低到高；4->价格从高到低",
//                    defaultValue = "0",
//                    allowableValues = "0,1,2,3,4",
//                    paramType = "query",
//                    dataType = "integer")
//    })
    @Operation(summary = "综合搜索、筛选、排序")
    @Parameter(name = "sort", description = "排序字段:0->按相关度；1->按新品；2->按销量；3->价格从低到高；4->价格从高到低")
    @GetMapping("/serarch")
    public CommonResult<CommonPage<PmsProduct>> search(@RequestParam(required = false) String keyword,
                                                       @RequestParam(required = false) Long brandId,
                                                       @RequestParam(required = false) Long productCategoryId,
                                                       @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                                       @RequestParam(required = false, defaultValue = "5") Integer pageSize,
                                                       @RequestParam(required = false, defaultValue = "0") Integer sort) {
        List<PmsProduct> productList = portalProductService.search(keyword, brandId, productCategoryId, pageNum, pageSize, sort);
        return CommonResult.success(CommonPage.restPage(productList));
    }

    @Operation(summary = "以树形结构获取所有商品分类")
    @GetMapping("/categoryTreeList")
    public CommonResult<List<PmsProductCategoryNode>> categoryTreeList() {
        List<PmsProductCategoryNode> list = portalProductService.categoryTreeList();
        return CommonResult.success(list);
    }

    @Operation(summary = "获取前台商品详情")
    @GetMapping("/detail/{id}")
    public CommonResult<PmsPortalProductDetail> detail(@PathVariable Long id) {
        PmsPortalProductDetail productDetail = portalProductService.detail(id);
        return CommonResult.success(productDetail);
    }
}
