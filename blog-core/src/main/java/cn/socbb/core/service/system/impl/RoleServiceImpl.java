package cn.socbb.core.service.system.impl;

import cn.socbb.core.bean.system.Role;
import cn.socbb.core.dao.system.RoleDao;
import cn.socbb.core.service.system.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cbb on 2018/7/16.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> findRolesByUserId(Long userId) {
        return roleDao.findByUserId(userId);
    }
}
