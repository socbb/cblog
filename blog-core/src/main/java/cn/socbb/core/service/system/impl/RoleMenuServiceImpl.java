package cn.socbb.core.service.system.impl;

import cn.socbb.core.bean.system.Role;
import cn.socbb.core.bean.system.RoleMenu;
import cn.socbb.core.dao.system.RoleMenuDao;
import cn.socbb.core.service.system.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
 * Created by cbb on 2018/7/27.
 */
@Service
public class RoleMenuServiceImpl implements RoleMenuService {

    @Autowired
    private RoleMenuDao roleMenuDao;

    @Override
    @Transactional
    public void deleteByMenuId(Long... id) {
        Arrays.stream(id).forEach(_id -> {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setMenuId(_id);
            roleMenuDao.delete(roleMenu);
        });
    }
}
