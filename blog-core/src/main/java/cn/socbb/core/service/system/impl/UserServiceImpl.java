package cn.socbb.core.service.system.impl;

import cn.socbb.common.support.RequestHolder;
import cn.socbb.common.utils.IPUtils;
import cn.socbb.core.bean.system.User;
import cn.socbb.core.dao.system.UserDao;
import cn.socbb.core.service.system.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * Created by cbb on 2018/7/15.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User findById(Long id) {
        return userDao.selectByPrimaryKey(id);
    }

    @Override
    public User findByUsername(String username) {
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
    public boolean update(User user) {
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
    public User insert(User user) {
        Assert.notNull(user, "User不可为空！");
        user.applyDefaultValue();
        userDao.insertSelective(user);
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
}
