package cn.socbb.core.service.system;

import cn.socbb.core.bean.system.Menu;

import java.util.List;
import java.util.Map;

/**
 * Created by cbb on 2018/7/16.
 */
public interface MenuService {

    Menu findById(Long id);

    public List<Menu> findByUserId(Long userId);

    List<Menu> findUrlAndPerms();

    List<Menu> findByMenu(Menu menu);

    List<Menu> homeMenuTree(Long userId);

    List<Menu> findByMenuTree(Menu menu);

    List<Menu> findByMapWithUserId(Map<String, Object> param);

    void save(Menu menu);

    void update(Menu menu);

    void delete(Long... id);
}
