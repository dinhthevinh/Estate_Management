package com.laptrinhjavaweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.laptrinhjavaweb.security.CustomSuccessHandler;
import com.laptrinhjavaweb.service.impl.CustomUserDetailService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailService();
	}

	// đối tượng xử lí phần mã hóa
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/admin/list").authenticated()// cho tất cả có đăng nhập
//				.antMatchers("/admin/customer-list").authenticated() // vào trang đó.
				.antMatchers("/admin/edit-**").authenticated()// cập nhật
				.antMatchers("/admin/edit").hasRole("manager")// thêm mới chỉ có role manager.
//				.antMatchers("/admin/customer-edit-**").authenticated()// cập nhật
				.antMatchers("/admin/customer-edit").hasRole("manager")
				.antMatchers("/login", "/resource/**", "/trang-chu", "/api/**").permitAll().and().formLogin()
				.loginPage("/login").usernameParameter("j_username").passwordParameter("j_password").permitAll()
				.loginProcessingUrl("/j_spring_security_check").successHandler(myAuthenticationSuccessHandler())
				.failureUrl("/login?incorrectAccount").and().logout().logoutUrl("/logout").deleteCookies("JSESSIONID")
				.and().exceptionHandling().accessDeniedPage("/access-denied").and().sessionManagement()
				.maximumSessions(1).expiredUrl("/login?sessionTimeout");
	}

	@Bean
	public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
		return new CustomSuccessHandler();
	}
}
