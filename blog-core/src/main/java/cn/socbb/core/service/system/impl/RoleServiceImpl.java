package cn.socbb.core.service.system.impl;

import cn.socbb.common.enums.RoleStatusEnum;
import cn.socbb.core.bean.system.Role;
import cn.socbb.core.dao.system.RoleDao;
import cn.socbb.core.service.system.RoleMenuService;
import cn.socbb.core.service.system.RoleService;
import cn.socbb.core.service.system.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by cbb on 2018/7/16.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private RoleMenuService roleMenuService;

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

    @Override
    @Transactional
    public void save(Role role) {
        role.applyDefaultValue();
        roleDao.insert(role);
    }

    @Override
    @Transactional
    public void update(Role role) {
        Assert.notNull(role, "Role不可为空！");
        role.setUpdateTime(new Date());
        roleDao.updateByPrimaryKey(role);
    }

    @Override
    @Transactional
    public void delete(Long... id) {
        Arrays.stream(id).forEach(_id-> {
            roleDao.deleteByPrimaryKey(_id);
            roleMenuService.deleteByRoleId(_id);
        });
    }
}
