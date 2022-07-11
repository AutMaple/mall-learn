package com.autmaple.mall.tiny.service.Impl;

import cn.hutool.core.util.StrUtil;
import com.autmaple.mall.tiny.dao.UmsRoleDao;
import com.autmaple.mall.tiny.mbg.mapper.UmsRoleMapper;
import com.autmaple.mall.tiny.mbg.mapper.UmsRoleMenuRelationMapper;
import com.autmaple.mall.tiny.mbg.mapper.UmsRoleResourceRelationMapper;
import com.autmaple.mall.tiny.mbg.model.*;
import com.autmaple.mall.tiny.service.UmsAdminCacheService;
import com.autmaple.mall.tiny.service.UmsRoleService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName UmsRoleServiceImpl
 * @Description
 * @Author AutMaple
 * @Date 2022/7/10 20:41
 * @Version 1.0
 **/
@Service
public class UmsRoleServiceImpl implements UmsRoleService {

    @Autowired
    private UmsRoleMapper roleMapper;

    @Autowired
    private UmsRoleMenuRelationMapper roleMenuRelationMapper;

    @Autowired
    private UmsRoleResourceRelationMapper roleResourceRelationMapper;

    @Autowired
    private UmsRoleDao roleDao;

    @Autowired
    private UmsAdminCacheService adminCacheService;

    @Override
    public int create(UmsRole role) {
        role.setCreateTime(new Date());
        role.setAdminCount(0);
        role.setSort(0);
        return roleMapper.insert(role);
    }

    @Override
    public int update(Long id, UmsRole role) {
        role.setId(id);
        return roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public int delete(List<Long> ids) {
        UmsRoleExample example = new UmsRoleExample();
        example.or().andIdIn(ids);
        adminCacheService.deleteResourceListByRoleIds(ids);
        return roleMapper.deleteByExample(example);
    }

    @Override
    public List<UmsRole> list() {
        return roleMapper.selectByExample(new UmsRoleExample());
    }

    @Override
    public List<UmsRole> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        UmsRoleExample example = new UmsRoleExample();
        if (!StrUtil.isEmpty(keyword)) {
            example.or().andNameLike("%" + keyword + "%");
        }
        return roleMapper.selectByExample(example);
    }

    @Override
    public List<UmsMenu> getMenuList(Long adminId) {
        return roleDao.getMenuList(adminId);
    }

    @Override
    public List<UmsMenu> listMenu(Long roleId) {
        return roleDao.getMenuListByRoleId(roleId);
    }

    @Override
    public List<UmsResource> listResource(Long roleId) {
        return roleDao.getResourceListByRoleId(roleId);
    }

    @Override
    public int allocMenu(Long roleId, List<Long> menuIds) {
        UmsRoleMenuRelationExample example = new UmsRoleMenuRelationExample();
        example.or().andRoleIdEqualTo(roleId);
        roleMenuRelationMapper.deleteByExample(example);
        for (Long menuId : menuIds) {
            UmsRoleMenuRelation relation = new UmsRoleMenuRelation();
            relation.setId(roleId);
            relation.setMenuId(menuId);
            roleMenuRelationMapper.insert(relation);
        }
        return menuIds.size();
    }

    @Override
    public int allocResource(Long roleId, List<Long> resourceIds) {
        UmsRoleResourceRelationExample example = new UmsRoleResourceRelationExample();
        example.or().andRoleIdEqualTo(roleId);
        roleResourceRelationMapper.deleteByExample(example);
        for (Long resourceId : resourceIds) {
            UmsRoleResourceRelation relation = new UmsRoleResourceRelation();
            relation.setResourceId(resourceId);
            relation.setRoleId(roleId);
            roleResourceRelationMapper.insert(relation);
        }
        adminCacheService.deleteResourceListByRole(roleId);
        return resourceIds.size();

    }
}
