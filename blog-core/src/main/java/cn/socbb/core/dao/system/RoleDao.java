package cn.socbb.core.dao.system;

import cn.socbb.core.bean.system.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by cbb on 2018/7/15.
 */
@Mapper
public interface RoleDao {

    @Select("select * from role")
    public List<Role> findAll();

    @Select("select * from  role where id = #{id}")
    public Role findById(String id);

}
