package cn.socbb.core.service.system.impl;

import cn.socbb.common.support.RequestHolder;
import cn.socbb.common.utils.IPUtils;
import cn.socbb.common.utils.PasswordUtils;
import cn.socbb.common.utils.StringUtils;
import cn.socbb.core.bean.system.User;
import cn.socbb.core.bean.system.UserRole;
import cn.socbb.core.dao.system.UserDao;
import cn.socbb.core.dao.system.UserRoleDao;
import cn.socbb.core.service.system.UserService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by cbb on 2018/7/15.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public User findById(Long id) {
        return userDao.selectByPrimaryKey(id);
    }

    @Override
    public User findByUsername(String username) {
        User user = new User(username);
        return userDao.selectOne(user);
    }

    @Override
    public User findByUsernameOrEmailOrMobile(String username) {
        User user = new User(username);
        user = userDao.selectOne(user);
        if (user == null) {
            user = new User();
            user.setEmail(username);
            user = userDao.selectOne(user);
        }
        if (user == null) {
            user = new User();
            user.setMobile(username);
            user = userDao.selectOne(user);
        }
        return user;
    }


    @Override
    @Transactional
    public boolean update(User user, long[] roleIds) {
        String password = user.getPassword();
        if (StringUtils.isNotBlank(password)) {
            try {
                user.setPassword(PasswordUtils.encrypt(password, user.getUsername()));
            } catch (Exception e) {
                throw new RuntimeException("密码加密错误");
            }
        }
        int i = userDao.updateByPrimaryKeySelective(user);

        // 更新用户角色关系, 先全部删除, 再添加
        if (i > 0 && ArrayUtils.isNotEmpty(roleIds)) {
            userRoleDao.delete(new UserRole(user.getId()));
            Arrays.stream(roleIds).forEach(roleId -> {
                UserRole userRole = new UserRole(user.getId(), roleId);
                userRole.applyDefaultValue();
                userRoleDao.insert(userRole);
            });
        }
        return i > 0;
    }

    @Override
    @Transactional
    public boolean update(User user) {
        String password = user.getPassword();
        if (StringUtils.isNotBlank(password)) {
            try {
                user.setPassword(PasswordUtils.encrypt(password, user.getUsername()));
            } catch (Exception e) {
                throw new RuntimeException("密码加密错误");
            }
        }
        return userDao.updateByPrimaryKeySelective(user) > 0;
    }

    @Override
    @Transactional
    public boolean updateUserLastLoginInfo(User user) {
        if (user != null) {
            user.setLoginCount(user.getLoginCount() + 1);
            user.setLastLoginTime(new Date());
            user.setLastLoginIp(IPUtils.getIpAddr(RequestHolder.getRequest()));
            user.setPassword(null);
            return this.update(user);
        }
        return false;
    }

    @Override
    @Transactional
    public User save(User user) {
        Assert.notNull(user, "User不可为空！");
        user.applyDefaultValue();
        userDao.insertSelective(user);
        return user;
    }

    @Override
    public User save(User user, long[] roleIds) {
        Assert.notNull(user, "User不可为空！");
        user.applyDefaultValue();
        userDao.insertSelective(user);

        // 添加用户-角色关系
        Arrays.stream(roleIds).forEach(roleId -> {
            UserRole userRole = new UserRole(user.getId(), roleId);
            userRole.applyDefaultValue();
            userRoleDao.insert(userRole);
        });
        return user;
    }

    @Override
    public List<User> findByRoleId(Long roleId) {
        return userDao.findByRoleId(roleId);
    }

    @Override
    @Transactional
    public boolean updatePwd(User user) {
        return false;
    }

    @Override
    public List<User> findByUser(User user) {
        return userDao.select(user);
    }

    @Override
    @Transactional
    public boolean deleteById(Long... id) {
        Arrays.stream(id).forEach(_id -> userDao.deleteByPrimaryKey(_id));
        return true;
    }

    @Override
    @Transactional
    public boolean deleteWhitRoleById(Long... id) {
        Arrays.stream(id).forEach(_id -> {
            userDao.deleteByPrimaryKey(_id);
            userRoleDao.delete(new UserRole(_id));
        });
        return true;
    }

    @Override
    public boolean exist(User user) {
        return CollectionUtils.isNotEmpty(userDao.select(user));
    }
}
