package cn.socbb.core.dao.system;

import cn.socbb.common.mapper.MyMapper;
import cn.socbb.core.bean.system.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by cbb on 2018/7/15.
 */
@Mapper
public interface MenuDao extends MyMapper<Menu> {

    public List<Menu> findByRoleId(Long roleId);

    public List<Menu> findByUserId(Long userId);

    public List<Menu> findUrlAndPerms();
}
