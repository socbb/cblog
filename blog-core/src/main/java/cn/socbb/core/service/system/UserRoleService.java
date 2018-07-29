package cn.socbb.core.service.system;

import cn.socbb.core.bean.system.UserRole;

/**
 * Created by cbb on 2018/7/27.
 */
public interface UserRoleService {

    UserRole save(UserRole role);

    void deleteByUserId(Long... id);

    void deleteByRoleId(Long... id);

}
