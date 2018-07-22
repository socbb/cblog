package cn.socbb.core.service.system.impl;

import cn.socbb.core.bean.system.Menu;
import cn.socbb.core.dao.system.MenuDao;
import cn.socbb.core.service.system.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cbb on 2018/7/16.
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Override
    public List<Menu> findByUserId(Long userId) {
        return menuDao.findByUserId(userId);
    }

    @Override
    public List<Menu> findUrlAndPerms() {
        return menuDao.findUrlAndPerms();
    }
}
