package cn.socbb.common.freemarker;

import cn.socbb.core.directive.SystemDirective;
import com.jagregory.shiro.freemarker.ShiroTags;
import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import javax.annotation.PostConstruct;

@Component
public class FreemarkerConfig {

    @Autowired
    private FreeMarkerViewResolver resolver;

    @Autowired
    protected Configuration configuration;

    @Autowired
    private SystemDirective systemDirective;

    @PostConstruct
    public void  setSharedVariable() {
        resolver.setRequestContextAttribute("request");
        configuration.setSharedVariable("shiro", new ShiroTags());
        configuration.setSharedVariable("system", systemDirective);
    }
}
