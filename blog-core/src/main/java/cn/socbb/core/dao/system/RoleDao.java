package cn.socbb.core.dao.system;

import cn.socbb.common.mapper.MyMapper;
import cn.socbb.core.bean.system.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by cbb on 2018/7/15.
 */
@Mapper
public interface RoleDao extends MyMapper<Role> {

}
