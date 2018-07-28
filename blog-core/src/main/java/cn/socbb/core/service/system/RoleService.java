package cn.socbb.core.service.system;

import cn.socbb.core.bean.system.Role;

import java.util.List;

public interface RoleService {

    Role findById(Long id);

    List<Role> findRolesByUserId(Long userId);

    List<Role> findByRole(Role role);

    void save(Role role);

    void update(Role role);

    void delete(Long... id);
}
