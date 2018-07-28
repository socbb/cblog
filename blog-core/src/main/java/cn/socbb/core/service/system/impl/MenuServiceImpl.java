package cn.socbb.core.service.system.impl;

import cn.socbb.common.annotation.RedisCache;
import cn.socbb.common.enums.MenuTypeEnum;
import cn.socbb.common.utils.TreeUtils;
import cn.socbb.core.bean.system.Menu;
import cn.socbb.core.bean.system.RoleMenu;
import cn.socbb.core.dao.system.MenuDao;
import cn.socbb.core.service.system.MenuService;
import cn.socbb.core.service.system.RoleMenuService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * Created by cbb on 2018/7/16.
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private RoleMenuService roleMenuService;

    @Override
    public Menu findById(Long id) {
        return menuDao.selectByPrimaryKey(id);
    }

    @Override
    public List<Menu> findByUserId(Long userId) {
        Map<String, Object> param = new HashMap<>();
        param.put("userId", userId);
        return findByMapWithUserId(param);
    }

    @Override
    public List<Menu> findUrlAndPerms() {
        return menuDao.findUrlAndPerms();
    }

    @Override
    public List<Menu> findByMenu(Menu menu) {
        return menuDao.select(menu);
    }

    /**
     * 查找首页菜单
     * @param userId
     * @return
     */
    @Override
    public List<Menu> homeMenuTree(Long userId) {
        Map<String, Object> param = new HashMap<>();
        param.put("userId", userId);
        param.put("type", MenuTypeEnum.MENU.getCode());
        List<Menu> menuList = findByMapWithUserId(param);
        return TreeUtils.getTree(menuList);
    }

    @Override
    public List<Menu> findByMenuTree(Menu menu) {
        List<Menu> menuList = menuDao.select(menu);
        return TreeUtils.getTree(menuList);
    }

    @Override
    public List<Menu> findAll() {
        return menuDao.selectAll();
    }

    @Override
    @RedisCache
    public List<Menu> findByMapWithUserId(Map<String, Object> param) {
        return menuDao.findByUserId(param);
    }

    @Override
    @Transactional
    @RedisCache(flush = true)
    public void save(Menu menu) {
        menu.applyDefaultValue();
        menuDao.insert(menu);
    }

    @Override
    @Transactional
    @RedisCache(flush = true)
    public void update(Menu menu) {
        menu.setUpdateTime(new Date());
        menuDao.updateByPrimaryKeySelective(menu);
    }

    @Override
    @Transactional
    @RedisCache(flush = true)
    public void delete(Long... id) {
        Arrays.stream(id).forEach(_id -> {
            menuDao.deleteByPrimaryKey(_id);
            roleMenuService.deleteByMenuId(_id);
        });
    }

    /**
     * 获取权限设置ztree
     * @param roleId
     * @return
     */
    @Override
    public List<Map<String, Object>> findWithSelected(Long roleId){
        List<Menu> menuList = menuDao.findMenusWithSelected(roleId);
        if (CollectionUtils.isEmpty(menuList)) {
            return null;
        }

        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        menuList.forEach(menu -> {
            Map<String, Object> map = new HashMap<>(3);
            map.put("id", menu.getId());
            map.put("pId", menu.getParentId());
            map.put("checked", menu.getChecked());
            map.put("name", menu.getName());
            mapList.add(map);
        });

        return mapList;
    }

    /**
     * 添加角色资源
     * @param roleId
     * @param menuIds
     */
    @Override
    @Transactional
    public void addRoleMenus(Long roleId, Long[] menuIds) {
        //删除
        removeByRoleId(roleId);
        //添加
        if (ArrayUtils.isNotEmpty(menuIds)) {
            Arrays.stream(menuIds).forEach(menuId -> {
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.applyDefaultValue();
                roleMenu.setRoleId(roleId);
                roleMenu.setMenuId(menuId);
                roleMenuService.save(roleMenu);
            });
        }
    }

    /**
     * 通过角色id批量删除
     * @param roleId
     */
    @Override
    @Transactional
    public void removeByRoleId(Long roleId) {
        //删除
        Example example = new Example(RoleMenu.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("roleId", roleId);
        roleMenuService.deleteByExample(example);
    }
}
