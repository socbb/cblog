package cn.socbb.core.service.system.impl;

import cn.socbb.common.enums.RoleStatusEnum;
import cn.socbb.core.bean.system.Role;
import cn.socbb.core.dao.system.RoleDao;
import cn.socbb.core.service.system.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Created by cbb on 2018/7/16.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public Role findById(Long id) {
        return roleDao.selectByPrimaryKey(id);
    }

    @Override
    public List<Role> findRolesByUserId(Long userId) {
        return roleDao.findByUserId(new HashMap<String, Object>(){{
            put("userId", userId);
            put("status", RoleStatusEnum.NORMAL.getCode());
        }});
    }

    @Override
    public List<Role> findByRole(Role role) {
        return roleDao.select(role);
    }
}
