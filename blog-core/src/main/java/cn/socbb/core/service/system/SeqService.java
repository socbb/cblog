package cn.socbb.core.service.system;

import cn.socbb.core.bean.system.Seq;

import java.util.List;

public interface SeqService {

    public Seq get(String table);

    public List<Seq> findAll();

    public boolean save(Seq seq);

    public boolean update(Seq seq);

    public boolean add(String table);

    public Long next(String table);

}
