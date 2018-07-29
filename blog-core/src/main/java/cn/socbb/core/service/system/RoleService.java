package cn.socbb.core.service.system;

import cn.socbb.core.bean.system.Role;

import java.util.List;
import java.util.Map;

public interface RoleService {

    Role findById(Long id);

    List<Role> findRolesByUserId(Long userId);

    List<Role> findByRole(Role role);

    void save(Role role);

    void update(Role role);

    void delete(Long... id);

    public List<Map<String, Object>> findRolesWithSelected(Long userId);
}
