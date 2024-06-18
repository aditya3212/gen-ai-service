package com.example.genaiservice.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.genaiservice.util.JwtTokenUtil;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

	private final JwtTokenUtil jwtTokenUtil;

	private static final List<String> noAuthUrls = Arrays.asList("/user/login", "/user/sign-in");

	private AntPathMatcher pathMatcher = new AntPathMatcher();

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		log.info("CustomFilter: Request pre-processing");

		final String requestURI = request.getRequestURI();

		// Pass request down the filter chain

//        if (noAuthUrls.stream().anyMatch(url -> pathMatcher.match(url, requestURI))) {
//            chain.doFilter(request, response);
//            return;
//        }
		if (noAuthUrls.stream().anyMatch(url -> pathMatcher.match(url, requestURI))) {
			filterChain.doFilter(request, response);
			return;
		}

		final String authHeader = request.getHeader("auth-token");

		if (StringUtils.isEmpty(authHeader) || !authHeader.startsWith("Bearer ")) {
			log.info("Auth header token missing");
			handleException(request, response, "JWT token missing");
			return;
		}

		String jwt = authHeader.substring(7);

		if (jwtTokenUtil.checkExpiration(jwt)) {
			handleException(request, response, "JWT token missing");
			return;
		}

		filterChain.doFilter(request, response);
		// Postprocessing of response
		log.info("CustomFilter: Response post-processing");
		// TODO Auto-generated method stub

	}

	private void handleException(HttpServletRequest request, HttpServletResponse response, String message)
			throws IOException {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType("application/json");
		response.getWriter().write("{\"error\": \"" + message + "\"}");
	}

}
