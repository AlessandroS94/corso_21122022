package com.diemme.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.diemme.business.impl.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(userDetailsService)
		    .passwordEncoder(bCryptPasswordEncoder)
		    ;
	}


	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeRequests()

		.antMatchers("/home","/","/login","/registration","/backoffice/**","/").permitAll()
        .antMatchers("/dashboard/**").authenticated()
        .antMatchers("/chatGestione").authenticated()
        .antMatchers("/chat/**").authenticated()
        .antMatchers("/chatVisione").authenticated()
        .antMatchers("/fileVisione/**").authenticated()
        .antMatchers("/chatFile/**").authenticated()
        .antMatchers("/chatCrea").authenticated()
        .antMatchers("/contattiCrea").authenticated()
        .antMatchers("/contattiUpdate").authenticated()
        .antMatchers("/layoutVisione").authenticated()
        .antMatchers("/layout/image/**").authenticated()
        .antMatchers("/showcase/**").authenticated()
        .antMatchers("/layoutGestione").authenticated()
        .antMatchers("/layoutProduzioneGestione").authenticated()
        .antMatchers("/layoutClientGestione").authenticated()
        .antMatchers("/layoutCrea").authenticated()
        .antMatchers("/layoutUpdate").authenticated()
        .antMatchers("/layoutUpdateProductor").authenticated()
        .antMatchers("/layoutUpdateProductor/**").authenticated()
        .antMatchers("/layoutUpdateClient").authenticated()
        .antMatchers("/tecnologieCrea").authenticated()
        .antMatchers("/newsGestione").authenticated()
        .antMatchers("/newsCrea").authenticated()
        .antMatchers("/newsUpdate").authenticated()
        .antMatchers("/prodottiGestione").authenticated()
        .antMatchers("/prodottiCrea").authenticated()
        .antMatchers("/prodottiUpdate").authenticated()
        .antMatchers("/preventiviGestione").authenticated()
        .antMatchers("/preventiviCrea").authenticated()
        .antMatchers("/preventiviUpdate").authenticated()
        .antMatchers("/tecnologieGestione").authenticated()
        .antMatchers("/tecnologie/image/**").authenticated()
        .antMatchers("/tecnologieCrea").authenticated()
        .antMatchers("/tecnologieUpdate").authenticated()
        .antMatchers("/utenteCrea").authenticated()
        .antMatchers("/utentiGestione").authenticated()
        .antMatchers("/utenteUpdate").authenticated()
			        .and()
			        .csrf().disable()
				     .formLogin()
			        .loginPage("/login")
				    .failureUrl("/login?error=true")
			        .defaultSuccessUrl("/dashboard",true)
			        .usernameParameter("user_name")
			        .passwordParameter("password")
			        .and()
		            .logout()
		            .logoutUrl("/logout")
		            .logoutSuccessUrl("/login")
		            .and()
		            .exceptionHandling()
			        .accessDeniedPage("/access-denied");
	}

}
