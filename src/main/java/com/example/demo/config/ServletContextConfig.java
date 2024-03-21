import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.example.demo.controller.HomeController;

@ComponentScan(basePackageClasses = HomeController.class)
public class ServletContextConfig extends WebMvcConfigurerAdapter {
    @Bean
    public TemplateEngine templateEngine(ApplicationContext applicationContext) {
        final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setEnableSpringELCompiler(true);
        templateEngine.addDialect(new SpringSecurityDialect(););
        templateEngine.setTemplateResolver(defaultTemplateResolver(applicationContext));
        return templateEngine;
    }
    // other @Bean configurations ...
}