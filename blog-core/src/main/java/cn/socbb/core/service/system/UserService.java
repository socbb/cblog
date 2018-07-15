package cn.socbb.core.service.system;

import cn.socbb.core.bean.system.User;

public interface UserService {

    User findById(String id);

    boolean update(User user);

    boolean updateUserLastLoginInfo(User user);
}
