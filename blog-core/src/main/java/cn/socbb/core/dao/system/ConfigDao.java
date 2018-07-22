package cn.socbb.core.dao.system;

import cn.socbb.common.mapper.MyMapper;
import cn.socbb.core.bean.system.Config;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by cbb on 2018/7/15.
 */
@Mapper
public interface ConfigDao extends MyMapper<Config> {
}
