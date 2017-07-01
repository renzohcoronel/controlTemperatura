package org.recoit.controlSensoresTemperatura.security;

import java.io.IOException;
import java.util.Collections;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.google.gson.Gson;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter  {
	
	  public JWTLoginFilter(String url, AuthenticationManager authManager) {
		    super(new AntPathRequestMatcher(url));
		    setAuthenticationManager(authManager);
		  }

		  @Override
		  public Authentication attemptAuthentication(
		      HttpServletRequest req, HttpServletResponse res)
		      throws AuthenticationException, IOException, ServletException {
			 
			 Gson gson = new Gson();
			 String requestData = req.getReader().lines().collect(Collectors.joining());
			 System.out.println("Request Data: "+ requestData+ " " + req.toString());
			 AccountCredentials creds = gson.fromJson(requestData, AccountCredentials.class);
		    
		    return getAuthenticationManager().authenticate(
		        new UsernamePasswordAuthenticationToken(
		            creds.getUsername(),
		            creds.getPassword(),
		            Collections.emptyList()
		        )
		    );
		  }

		  @Override
		  protected void successfulAuthentication(
		      HttpServletRequest req,
		      HttpServletResponse res, FilterChain chain,
		      Authentication auth) throws IOException, ServletException {
			  
			  TokenAuthenticationService
		        .addAuthentication(res, auth.getName());
		  }

}
