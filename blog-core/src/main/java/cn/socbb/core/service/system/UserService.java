package cn.socbb.core.service.system;

import cn.socbb.core.bean.system.User;

public interface UserService {

    User findById(String id);

    User findByUsername(String username);

    boolean update(User user);

    boolean updateUserLastLoginInfo(User user);
}
