package cn.socbb.core.service.system;

import cn.socbb.core.bean.system.Role;

import java.util.List;

public interface RoleService {

    List<Role> findRolesByUserId(Long userId);

}
