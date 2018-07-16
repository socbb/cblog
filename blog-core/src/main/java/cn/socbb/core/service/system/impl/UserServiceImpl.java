package cn.socbb.core.service.system.impl;

import cn.socbb.common.support.RequestHolder;
import cn.socbb.common.utils.IPUtils;
import cn.socbb.core.bean.system.User;
import cn.socbb.core.dao.system.UserDao;
import cn.socbb.core.service.system.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by cbb on 2018/7/15.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User findById(String id) {
        return userDao.selectByPrimaryKey(id);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.selectOne(new User(username));
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
}
