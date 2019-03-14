package com.example.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.example.authentication.SampleJdbcDaoImpl;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/top.html").permitAll().antMatchers("/admin/**")
				.hasAuthority("ROLE_ADMIN").anyRequest().authenticated().and().formLogin()
				.defaultSuccessUrl("/top.html").and().logout().logoutUrl("/logout").logoutSuccessUrl("/top.html").and()
				.csrf().disable();
	}

	@Autowired
	private DataSource dataSource;

	// SQLも変更されていることに注意
	private static final String USER_QUERY = "select LOGIN_ID, PASSWORD, FULL_NAME, DEPT_NAME " + "from T_USER "
			+ "where LOGIN_ID = ?";

	private static final String ROLES_QUERY = "select LOGIN_ID, ROLE_NAME " + "from T_ROLE "
			+ "inner join T_USER_ROLE on T_ROLE.ID = T_USER_ROLE.ROLE_ID "
			+ "inner join T_USER on T_USER_ROLE.USER_ID = T_USER.ID " + "where LOGIN_ID = ?";

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		SampleJdbcDaoImpl userService = new SampleJdbcDaoImpl();
		userService.setDataSource(dataSource);
		userService.setUsersByUsernameQuery(USER_QUERY);
		userService.setAuthoritiesByUsernameQuery(ROLES_QUERY);
		auth.userDetailsService(userService);
	}
}
