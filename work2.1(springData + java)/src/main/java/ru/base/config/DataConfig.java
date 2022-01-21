package ru.base.config;

import org.hibernate.Hibernate;

//import org.hibernate.ejb.HibernatePersistence;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration//помечаем файл как конфигурационный
@EnableTransactionManagement//включаем TransactionManager для управления транзакциями БД
@ComponentScan("ru.base")
@PropertySource("classpath:application.properties")//файл свойст для конфигурации
@EnableJpaRepositories("ru.base.model.repository")
@EnableWebMvc    //помечаем,что у нас теперь приложение web
public class DataConfig implements WebMvcConfigurer {

    //тут мы записываем в константы информацию из файла свойств:
    private static final String DRIVER = "db.driver";
    private static final String PASSWORD = "db.password";
    private static final String URL = "db.url";
    private static final String USERNAME = "db.username";
    private static final String DIALECT = "db.hibernate.dialect";
    private static final String SQL = "db.hibernate.show_sql";
    private static final String PACKAGES = "db.entitymanager.packages.to.scan";
    private static final String PROP_HIBERNATE = "db.hibernate.hbm2ddl.auto";

    private final ApplicationContext applicationContext;

    @Autowired//автоматическое внедрение зависимости
    public DataConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Resource
    //используемся для получения свойств из файла application.properties
    //и для дальнейшего конфигурирования с помощью этих свойств
    private Environment env;

    @Bean
    public DataSource dataSource() {//конфигурируем подключение к БД
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty(DRIVER));
        dataSource.setUrl(env.getRequiredProperty(URL));
        dataSource.setUsername(env.getRequiredProperty(USERNAME));
        dataSource.setPassword(env.getRequiredProperty(PASSWORD));
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {//конфигурируем entityManager
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan(env.getRequiredProperty(PACKAGES));
        entityManagerFactoryBean.setJpaProperties(getHibernateProperties());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        return entityManagerFactoryBean;
    }

    @Bean
    public JpaTransactionManager transactionManager() {//конфигурируем менеджер транзакций
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    private Properties getHibernateProperties() {//конфигурируем хибернэйт
        Properties properties = new Properties();
        properties.put(DIALECT, env.getRequiredProperty(DIALECT));
        properties.put(SQL, env.getRequiredProperty(SQL));
        properties.put(PROP_HIBERNATE, env.getRequiredProperty(PROP_HIBERNATE));
        return properties;
    }


    //три метода ниже испльзуются для конфигурирования шаблонизатора ThymeLeaf
    @Bean//создать бин
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/school/views/");//папка для представлений
        templateResolver.setSuffix(".html");//расширения файлов в папке представлений
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        registry.viewResolver(resolver);
    }

}