package com.example.genaiservice.security;
import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtTokenFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		log.info("CustomFilter: Request pre-processing");

        // Pass request down the filter chain
        filterChain.doFilter(request, response);

        // Postprocessing of response
        log.info("CustomFilter: Response post-processing");
		// TODO Auto-generated method stub
		
	}

}
