package com.diemme.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.diemme.business.impl.UserDetailsServiceImpl;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	private final UserDetailsServiceImpl userDetailsService;

	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public WebSecurityConfig(BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailsServiceImpl userDetailsService) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.userDetailsService = userDetailsService;
	}


	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(userDetailsService)
		    .passwordEncoder(bCryptPasswordEncoder)
		    ;
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}




	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.requestMatchers("/","/home","/showcase/**","/login","/registration","/backoffice/**").permitAll()
        .requestMatchers("/dashboard/**").authenticated()
        .requestMatchers("/chatGestione").authenticated()
        .requestMatchers("/chat/**").authenticated()
        .requestMatchers("/chatVisione").authenticated()
        .requestMatchers("/fileVisione/**").authenticated()
        .requestMatchers("/chatFile/**").authenticated()
        .requestMatchers("/chatCrea").authenticated()
        .requestMatchers("/contattiCrea").authenticated()
        .requestMatchers("/contattiUpdate").authenticated()
        .requestMatchers("/layoutVisione").authenticated()
        .requestMatchers("/layout/image/**").authenticated()
        .requestMatchers("/layoutGestione").authenticated()
        .requestMatchers("/layoutProduzioneGestione").authenticated()
        .requestMatchers("/layoutClientGestione").authenticated()
        .requestMatchers("/layoutCrea").authenticated()
        .requestMatchers("/layoutUpdate").authenticated()
        .requestMatchers("/layoutUpdateProductor").authenticated()
        .requestMatchers("/layoutUpdateProductor/**").authenticated()
        .requestMatchers("/layoutUpdateClient").authenticated()
        .requestMatchers("/tecnologieCrea").authenticated()
        .requestMatchers("/newsGestione").authenticated()
        .requestMatchers("/newsCrea").authenticated()
        .requestMatchers("/newsUpdate").authenticated()
        .requestMatchers("/prodottiGestione").authenticated()
        .requestMatchers("/prodottiCrea").authenticated()
        .requestMatchers("/prodottiUpdate").authenticated()
        .requestMatchers("/preventiviGestione").authenticated()
        .requestMatchers("/preventiviCrea").authenticated()
        .requestMatchers("/preventiviUpdate").authenticated()
        .requestMatchers("/tecnologieGestione").authenticated()
        .requestMatchers("/tecnologie/image/**").authenticated()
        .requestMatchers("/tecnologieCrea").authenticated()
        .requestMatchers("/tecnologieUpdate").authenticated()
        .requestMatchers("/utenteCrea").authenticated()
        .requestMatchers("/utentiGestione").authenticated()
        .requestMatchers("/utenteUpdate").authenticated()
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
		return http.build();
	}

}
