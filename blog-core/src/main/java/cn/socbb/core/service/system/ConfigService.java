package cn.socbb.core.service.system;

import cn.socbb.core.bean.system.Config;

/**
 * Created by cbb on 2018/7/22.
 */
public interface ConfigService {

    Config get(Long id);

    boolean save(Config config);

}
