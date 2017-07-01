package org.recoit.controlSensoresTemperatura.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


	  @Override
	  protected void configure(HttpSecurity http) throws Exception {
		  System.out.println("Request para cazar!");
	    http.csrf().disable().authorizeRequests()
	        .antMatchers("/").permitAll()
	        .antMatchers(HttpMethod.POST, "/login").permitAll()
	        .anyRequest().authenticated()
	        .and()// Add CORS Filter
	        .addFilterBefore(corsFilter(), ChannelProcessingFilter.class)
	        // We filter the api/login requests
	        .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
	                UsernamePasswordAuthenticationFilter.class)
	        // And filter other requests to check the presence of JWT in header
	        .addFilterBefore(new JWTAuthenticationFilter(),
	                UsernamePasswordAuthenticationFilter.class);
	  }

	  @Override
	  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    // Create a default account
	    auth.inMemoryAuthentication()
	        .withUser("elMason")
	        .password("sysCerveza").roles("ADMIN");
	  }
	  
	  @Bean
	  public CorsFilter corsFilter() {
		  System.out.println("Se llama a croosfilter");
	
	      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	
	      CorsConfiguration config = new CorsConfiguration();
	
	      config.setAllowCredentials(true);
	
	      config.addAllowedOrigin("*");
	
	      config.addAllowedHeader("Content-Type");
	
	//      config.addAllowedMethod("OPTIONS");
	
	      config.addAllowedMethod("GET");
	
	      config.addAllowedMethod("POST");
	
	      config.addAllowedMethod("PUT");
	
	      config.addAllowedMethod("DELETE");
	
	      source.registerCorsConfiguration("/**", config);
	
	      return new CorsFilter(source);
	
	  }
	  
}
