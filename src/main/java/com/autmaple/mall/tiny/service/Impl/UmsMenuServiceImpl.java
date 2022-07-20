package com.autmaple.mall.tiny.service.Impl;

import com.autmaple.mall.tiny.dto.UmsMenuNode;
import com.autmaple.mall.tiny.mbg.mapper.UmsMenuMapper;
import com.autmaple.mall.tiny.mbg.model.UmsMenu;
import com.autmaple.mall.tiny.mbg.model.UmsMenuExample;
import com.autmaple.mall.tiny.service.UmsMenuService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName UmsMenuServiceImpl
 * @Description 后台菜单管理 Service 实现类
 * @Author AutMaple
 * @Date 2022/7/20 20:48
 * @Version 1.0
 **/
@Service
public class UmsMenuServiceImpl implements UmsMenuService {
    @Autowired
    private UmsMenuMapper menuMapper;

    @Override
    public int create(UmsMenu menu) {
        menu.setCreateTime(new Date());
        updateLevel(menu);
        return menuMapper.insert(menu);

    }

    /**
     * @Author AutMaple
     * @Description 修改菜单层级
     * @Date 2022/7/20 20:53
     **/
    private void updateLevel(UmsMenu menu) {
        if (menu.getParentId() == 0)
            menu.setLevel(0);
        else {
            UmsMenu parentMenu = menuMapper.selectByPrimaryKey(menu.getParentId());
            if (parentMenu != null)
                menu.setLevel(parentMenu.getLevel() + 1);
            else
                menu.setLevel(0);
        }
    }

    @Override
    public int update(Long id, UmsMenu menu) {
        menu.setId(id);
        updateLevel(menu);
        return menuMapper.updateByPrimaryKeySelective(menu);
    }

    @Override
    public UmsMenu getItem(Long id) {
        return menuMapper.selectByPrimaryKey(id);
    }

    @Override
    public int delete(Long id) {
        return menuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<UmsMenu> list(Long parentId, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        UmsMenuExample example = new UmsMenuExample();
        example.setOrderByClause("sort desc");
        example.or().andParentIdEqualTo(parentId);
        return menuMapper.selectByExample(example);
    }

    @Override
    public List<UmsMenuNode> treeList() {
        List<UmsMenu> menuList = menuMapper.selectByExample(new UmsMenuExample());
        return menuList.stream()
                .filter(menu -> menu.getParentId().equals(0L))
                .map(menu -> convertMenuNode(menu, menuList))
                .collect(Collectors.toList());
    }

    private UmsMenuNode convertMenuNode(UmsMenu menu, List<UmsMenu> menuList) {
        UmsMenuNode menuNode = new UmsMenuNode();
        BeanUtils.copyProperties(menu, menuNode);
        List<UmsMenuNode> children = menuList.stream()
                .filter(subMenu -> subMenu.getParentId().equals(menu.getId()))
                .map(subMenu -> convertMenuNode(subMenu, menuList))
                .collect(Collectors.toList());
        menuNode.setChildren(children);
        return menuNode;
    }

    @Override
    public int updateHidden(Long id, Integer hidden) {
        UmsMenu menu = new UmsMenu();
        menu.setId(id);
        menu.setHidden(hidden);
        return menuMapper.updateByPrimaryKeySelective(menu);
    }
}
