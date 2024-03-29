package com.ensah.authservice.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Time;
import java.util.*;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager ;

	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		String username = request.getParameter("username");
		String password = request.getParameter("password") ;

		System.out.println(username);
		System.out.println(password);
		UsernamePasswordAuthenticationToken authenticationToken =
				new UsernamePasswordAuthenticationToken(username, password);
		return  authenticationManager.authenticate(authenticationToken) ;

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
											FilterChain chain, Authentication authResult)
			throws IOException, ServletException {
		System.out.println("authentication success!!!");


		User user = (User) authResult.getPrincipal();

		Algorithm algorithm = Algorithm.HMAC256("mysecret") ;

		List<String> roles = new ArrayList<>();
		user.getAuthorities().forEach(auth ->{
			roles.add(auth.getAuthority() );
		});

		System.out.println("les roles sont : " + roles.toArray());

		String jwtAccessToken = JWT.create()
				.withSubject(user.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + 1*60*1000))
				.withIssuer(request.getRequestURL().toString())
				.withArrayClaim("roles", roles.toArray(new String[0]))
				.sign(algorithm) ;


		String jwtRefreshToken = JWT.create()
				.withSubject(user.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + 5*60*1000))
				.withIssuer(request.getRequestURL().toString())
				.sign(algorithm) ;

		Map<String , String> idToken = new HashMap<>() ;
		idToken.put("access-token", jwtAccessToken) ;
		idToken.put("refresh-token", jwtRefreshToken) ;

		response.setContentType("application/json");

		new ObjectMapper().writeValue(response.getOutputStream(), idToken);

	}
}
