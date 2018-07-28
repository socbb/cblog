package cn.socbb.core.service.system;

import cn.socbb.core.bean.system.RoleMenu;
import tk.mybatis.mapper.entity.Example;

public interface RoleMenuService {

    void deleteByMenuId(Long... id);

    void deleteByRoleId(Long... id);

    void save(RoleMenu roleMenu);

    void deleteByExample(Example example);
}
