package com.example.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.springframework.http.HttpMethod.*;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
	protected void configure(HttpSecurity http) throws Exception {
    	http
    		.authorizeRequests()
    			.antMatchers(GET, "/top.html").permitAll()
        		.antMatchers(GET, "/login.html").permitAll()
        		.antMatchers(POST, "/processLogin").permitAll()
        		.antMatchers(POST, "/logout").authenticated()
        		.antMatchers("/admin/**").hasRole("ADMIN")
        		.antMatchers("/user/**").authenticated()
        		.and()
			.formLogin()
				.loginPage("/login.html")
				.loginProcessingUrl("/processLogin")
				.defaultSuccessUrl("/top.html")
				.failureUrl("/login.html")
				.usernameParameter("paramLoginId")
				.passwordParameter("paramPassword")
				.and()
			// 演習4 ログアウト処理をするように設定しなさい
			// 演習4 エラー画面が出るように設定しなさい
				
			.csrf().disable();
	}

    @Autowired
    private DataSource dataSource;

    private static final String USER_QUERY
        = "select LOGIN_ID, PASSWORD, true "
        + "from T_USER "
        + "where LOGIN_ID = ?";

    private static final String ROLES_QUERY
        = "select LOGIN_ID, ROLE_NAME "
        + "from T_ROLE "
        + "inner join T_USER_ROLE on T_ROLE.ID = T_USER_ROLE.ROLE_ID "
        + "inner join T_USER on T_USER_ROLE.USER_ID = T_USER.ID "
        + "where LOGIN_ID = ?";

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
    	// 演習4 パスワードが暗号化できるように設定しなさい
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(USER_QUERY)
                .authoritiesByUsernameQuery(ROLES_QUERY);
    }
}
