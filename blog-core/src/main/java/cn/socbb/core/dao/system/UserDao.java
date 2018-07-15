package cn.socbb.core.dao.system;

import cn.socbb.common.mapper.MyMapper;
import cn.socbb.core.bean.system.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by cbb on 2018/7/15.
 */
@Mapper
public interface UserDao extends MyMapper<User> {

}
