package cn.socbb.core.service.system;

import cn.socbb.core.bean.system.User;

import java.util.List;

public interface UserService {

    User findById(Long id);

    User findByUsername(String username);

    User findByUsernameOrEmailOrMobile(String username);

    boolean update(User user, long[] roleIds);

    boolean update(User user);

    boolean updateUserLastLoginInfo(User user);

    boolean updatePwd(User user);

    User save(User user);

    User save(User user, long[] roleIds);

    List<User> findByRoleId(Long roleId);

    List<User> findByUser(User user);

    boolean deleteById(Long... id);

    boolean deleteWhitRoleById(Long... id);

    boolean exist(User user);

    void allotRole(Long userId, List<Long> roleIds);
}
