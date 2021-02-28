package com.project.GPrint3D.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    private DataSource dataSource;

    @Autowired
    protected void configAuthentication(AuthenticationManagerBuilder auth) throws Exception 
    {
        auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
            .dataSource(dataSource)
            .usersByUsernameQuery("SELECT usu_email, usu_senha, usu_ativo FROM usuarios WHERE usu_email=?")
            .authoritiesByUsernameQuery("SELECT usu_email, usu_regra FROM usuarios WHERE usu_email=?");
    }

    @Override
    public void configure(WebSecurity web) throws Exception 
    {
        web.ignoring().antMatchers("/javascripts/**", "/stylesheets/**", "/images/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception 
    {
        http.authorizeRequests()
            .antMatchers("/admin/**").hasRole("ADM")
            .antMatchers("/cliente/**").hasRole("CLI")
            .anyRequest().permitAll()
            .and()
            .formLogin()
            .loginPage("/login")
            .permitAll()
            .and()
            .logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/index")
            .permitAll();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() 
    {
        return new BCryptPasswordEncoder();
    }
}
