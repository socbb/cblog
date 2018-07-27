package cn.socbb.core.service.system.impl;

import cn.socbb.common.annotation.RedisCache;
import cn.socbb.common.enums.MenuTypeEnum;
import cn.socbb.common.utils.TreeUtils;
import cn.socbb.core.bean.system.Menu;
import cn.socbb.core.dao.system.MenuDao;
import cn.socbb.core.service.system.MenuService;
import cn.socbb.core.service.system.RoleMenuService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import sun.util.resources.cldr.id.CurrencyNames_id;

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
}
