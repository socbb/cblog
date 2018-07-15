package cn.socbb.common.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Created by cbb on 2018/7/15.
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
