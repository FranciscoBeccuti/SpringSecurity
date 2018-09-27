package com.example.demo.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private DataSource dataSource;

	/**
	 * get the value that we set in applicaction.properties
	 */
	@Value("${spring.queries.userquery}")
	private String usersQuery;

	/**
	 * get the value that we set in applicaction.properties
	 */
	@Value("${spring.queries.rolequery}")
	private String rolesQuery;

	/**
	 * Enables the use of users
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().usersByUsernameQuery(usersQuery).authoritiesByUsernameQuery(rolesQuery)
				.dataSource(dataSource).passwordEncoder(bCryptPasswordEncoder);
	}

	/**
	 * configuration about the accesses that the user will have according to their role.
	 * Note,  usernameParameter("email") and passwordParameter("password") get data from login.html
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/login").permitAll()
				.antMatchers("/registration").permitAll().antMatchers("/rest/**").hasAuthority("ADMIN").antMatchers("/admin/**").hasAuthority("ADMIN").anyRequest()
				.authenticated().and().csrf().disable().formLogin().loginPage("/login").failureUrl("/login?error=true")
				.defaultSuccessUrl("/home").usernameParameter("email").passwordParameter("password").and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login").and()
				.exceptionHandling().accessDeniedPage("/403");
		
	}
	/**
	 * Configure which directories will not have access.
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**");
	}

}