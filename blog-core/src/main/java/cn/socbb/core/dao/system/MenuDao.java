package cn.socbb.core.dao.system;

import cn.socbb.core.bean.system.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by cbb on 2018/7/15.
 */
@Mapper
public interface MenuDao {

    @Select("select * from menu")
    public List<Menu> findAll();

    @Select("select * from  menu where id = #{id}")
    public Menu findById(String id);

}
