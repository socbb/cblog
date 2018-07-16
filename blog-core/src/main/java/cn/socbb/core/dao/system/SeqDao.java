package cn.socbb.core.dao.system;

import cn.socbb.common.mapper.MyMapper;
import cn.socbb.core.bean.system.Seq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface SeqDao extends MyMapper<Seq> {

    @Update("update seq set seq = seq + 1 where table = #{table}")
    public int add(String table);

}
