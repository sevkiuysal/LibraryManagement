package com.koylumuhendis.librarymanagement.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.koylumuhendis.librarymanagement.service.UserDetailServiceImpl;
import com.koylumuhendis.librarymanagement.utils.TokenGenerator;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

	private final TokenGenerator tokenGenerator;
	private final UserDetailServiceImpl detailServiceImpl;
	
	public JwtFilter(TokenGenerator tokenGenerator, UserDetailServiceImpl detailServiceImpl) {
		this.tokenGenerator = tokenGenerator;
		this.detailServiceImpl = detailServiceImpl;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token=getToken(request); 
		String username;
		try {
			if(!token.isBlank()) {
				username=tokenGenerator.verifyJWT(token).getSubject();
				UserDetails userDetails=detailServiceImpl.loadUserByUsername(username); 
				UsernamePasswordAuthenticationToken authenticationToken=
						new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
			filterChain.doFilter(request, response);
		} catch (Exception e) {
			ObjectMapper objectMapper=new ObjectMapper();
			response.setContentType("application/json");
			Map<String, String> errors=new HashMap<>();
			errors.put("error", e.getMessage());
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().write(objectMapper.writeValueAsString(errors));
		}
		
	}
	
	private String getToken(HttpServletRequest httpServletRequest) {
		String header=httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
		if(header==null && !header.startsWith("Bearer ")) {
			return "";
		}
		return header.substring(7);
	}
}
