package cn.socbb.core.service.system;

import cn.socbb.core.bean.system.Menu;

import java.util.List;

/**
 * Created by cbb on 2018/7/16.
 */
public interface MenuService {

    public List<Menu> findByUserId(Long userId);

}
