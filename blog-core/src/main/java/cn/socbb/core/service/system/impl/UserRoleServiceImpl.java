package cn.socbb.core.service.system.impl;

import cn.socbb.core.bean.system.UserRole;
import cn.socbb.core.dao.system.UserRoleDao;
import cn.socbb.core.service.system.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
 * Created by cbb on 2018/7/27.
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    @Transactional
    public UserRole save(UserRole role) {
        role.applyDefaultValue();
        int insert = userRoleDao.insert(role);
        return role;
    }

    @Override
    @Transactional
    public void deleteByUserId(Long... id) {
        Arrays.stream(id).forEach(_id -> {
            UserRole userRole = new UserRole();
            userRole.setUserId(_id);
            userRoleDao.delete(userRole);
        });
    }

    @Override
    @Transactional
    public void deleteByRoleId(Long... id) {
        Arrays.stream(id).forEach(_id -> {
            UserRole userRole = new UserRole();
            userRole.setRoleId(_id);
            userRoleDao.delete(userRole);
        });
    }
}
