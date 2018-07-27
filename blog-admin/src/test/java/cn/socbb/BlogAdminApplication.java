package cn.socbb;

import cn.socbb.core.service.system.impl.MenuServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BlogAdminApplication.class)
public class BlogAdminApplication {

    @Autowired
    private MenuServiceImpl menuService;

    @Test
    public void findHomeMenus() {
    }

}
