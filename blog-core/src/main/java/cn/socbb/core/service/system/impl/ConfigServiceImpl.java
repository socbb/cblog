package cn.socbb.core.service.system.impl;

import cn.socbb.core.bean.system.Config;
import cn.socbb.core.dao.system.ConfigDao;
import cn.socbb.core.service.system.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by cbb on 2018/7/22.
 */
@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    private ConfigDao configDao;

    @Override
    public Config get(Long id) {
        return configDao.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public boolean save(Config config) {
        return configDao.insert(config) > 0;
    }
}
