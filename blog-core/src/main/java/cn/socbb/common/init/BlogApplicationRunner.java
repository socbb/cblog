
package cn.socbb.common.init;

import cn.socbb.common.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 程序启动后通过ApplicationRunner处理一些事务
 *
 */
@Slf4j
@Component
public class BlogApplicationRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments applicationArguments) {
        log.info("启动完成，当前时间：" + DateUtils.YYYYMMDDHHMMSS.format(new Date()));
    }
}
