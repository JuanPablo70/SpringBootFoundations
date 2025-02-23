package com.juan.sanchez.config;

import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

@Configuration
@Profile("security")
@EnableWebSecurity(debug=true)
class SecurityConfig {

//  @Bean
//  PasswordEncoder passwordEncoder() {
//	  return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//  }

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
		UserDetails user001 = User.withUsername("juansanchez")
				                  .password("$2a$10$...")
				                  .roles("ADMIN","OVERSIGHT")
				                  .build();
		UserDetails user002 = User.withUsername("pablosanchez")
								  .password("$2a$10$...")//mi-hermano
								  .roles("USER","SUPPORT","SALES")
								  .build();
		return new InMemoryUserDetailsManager(user001, user002);
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http,
									@Qualifier("inspectorFilter") Filter filter) throws Exception {
		http.authorizeHttpRequests(auth -> {
				auth.requestMatchers("/url", "/uri").hasAnyRole("USER","ADMIN")
					.requestMatchers("/url/author/delete").hasAnyAuthority("ROLE_ADMIN","ROLE_OVERSIGHT")
					.requestMatchers("uri/authors/{id}/delete").hasAnyAuthority("ROLE_ADMIN","ROLE_OVERSIGHT")
					.anyRequest().authenticated();
			})
			.formLogin(Customizer.withDefaults())
			.exceptionHandling(exceptionHandler -> exceptionHandler.accessDeniedPage("/security/status/403"))
			.logout(Customizer.withDefaults())
			.addFilterBefore(filter, AnonymousAuthenticationFilter.class);
		return http.build();
	}

//	@Bean
//	FilterRegistrationBean<Filter> registration(@Qualifier("inspectorFilter") Filter filter) {
//		FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
//		registration.setFilter(filter);
//		registration.setEnabled(false);
//		return registration;
//	}

}
