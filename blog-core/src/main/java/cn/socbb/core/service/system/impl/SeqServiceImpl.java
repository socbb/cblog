package cn.socbb.core.service.system.impl;

import cn.socbb.common.annotation.RedisCache;
import cn.socbb.core.bean.system.Seq;
import cn.socbb.core.dao.system.SeqDao;
import cn.socbb.core.service.system.SeqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by cbb on 2018/7/16.
 */
@Service
public class SeqServiceImpl implements SeqService {

    @Autowired
    private SeqDao seqDao;

    @Override
    public Seq get(String table) {
        return seqDao.selectByPrimaryKey(table);
    }

    @Override
    @RedisCache
    public List<Seq> findAll() {
        return seqDao.selectAll();
    }

    @Override
    @Transactional
    @RedisCache(flush = true)
    public boolean save(Seq seq) {
        return seqDao.insert(seq) > 0;
    }

    @Override
    @Transactional
    @RedisCache(flush = true)
    public boolean update(Seq seq) {
        return seqDao.updateByPrimaryKeySelective(seq) > 0;
    }

    @Override
    @Transactional
    @RedisCache(flush = true)
    public boolean add(String table) {
        return seqDao.add(table) > 0;
    }

    @Override
    public Long next(String table) {
        return this.get(table).getSeq() + 1;
    }
}
