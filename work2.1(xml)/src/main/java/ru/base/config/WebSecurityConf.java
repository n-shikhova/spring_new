package ru.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class WebSecurityConf extends WebSecurityConfigurerAdapter {//конфигурация спирг Security

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//добавляем пользователей с ролями и паролями:
        auth.inMemoryAuthentication()
                .withUser("user")
                .password("user")
                .roles("USER")
                .and()
                .withUser("admin")
                .password("admin")
                .roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //прописываем разрешения,куда может ходить админ, и куда может юзер
        http.authorizeRequests()
                .antMatchers("/").hasAnyRole("ADMIN","USER")
                .antMatchers("/watch/**").hasAnyRole("ADMIN","USER")
                .antMatchers("/**").hasRole("ADMIN")
                .and().formLogin();//отправляем стандартную форму логина
    }

    @Bean
    public PasswordEncoder encoder(){//метод для шифрования паролей(хэширование)
        return NoOpPasswordEncoder.getInstance();//возвращаем пустой encoder, т.к. не требуется хэшировать пароли
    }

}
