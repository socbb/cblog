package cn.socbb.core.service.system;

import cn.socbb.core.bean.system.User;

import java.util.List;

public interface UserService {

    User findById(Long id);

    User findByUsername(String username);

    boolean update(User user);

    boolean updateUserLastLoginInfo(User user);

    boolean updatePwd(User user);

    User insert(User user);

    List<User> findByRoleId(Long roleId);
}
