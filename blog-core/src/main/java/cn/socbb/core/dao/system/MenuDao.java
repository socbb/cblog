package cn.socbb.core.dao.system;

import cn.socbb.common.mapper.MyMapper;
import cn.socbb.core.bean.system.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * Created by cbb on 2018/7/15.
 */
@Mapper
public interface MenuDao extends MyMapper<Menu> {

    List<Menu> findByRoleId(Long roleId);

    List<Menu> findByUserId(Map<String, Object> param);

    List<Menu> findUrlAndPerms();

    List<Menu> findMenusWithSelected(Long roleId);
}
