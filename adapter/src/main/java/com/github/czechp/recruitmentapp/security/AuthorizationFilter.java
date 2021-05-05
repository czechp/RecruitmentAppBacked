package com.github.czechp.recruitmentapp.security;

import com.github.czechp.recruitmentapp.utility.security.JwtAuthorizationTokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component()
class AuthorizationFilter extends OncePerRequestFilter {
    private final JwtAuthorizationTokenService jwtAuthorizationTokenService;
    private final UserDetailsService userDetailsService;
    private final Logger logger;

    @Autowired()
    AuthorizationFilter(final JwtAuthorizationTokenService jwtAuthorizationTokenService, final UserDetailsService userDetailsService) {
        this.jwtAuthorizationTokenService = jwtAuthorizationTokenService;
        this.userDetailsService = userDetailsService;
        this.logger = LoggerFactory.getLogger(this.getClass());
    }

    @Override
    protected void doFilterInternal(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse, final FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        String token = "";

        try {
            token = authorizationHeader.substring(7);
        } catch (Exception e) {
            logger.info("Request has no authorization header");
        }

        if (token.length() > 0) {
            Jws<Claims> claimsJws = jwtAuthorizationTokenService.decodeToken(token);
            String username = claimsJws.getBody().getSubject();
            Optional<UsernamePasswordAuthenticationToken> optionalAuthenticationToken = fetchAuthenticationToken(username);

            System.out.println(claimsJws.getBody().get("address"));

            boolean userAuthenticated = optionalAuthenticationToken.isPresent()
                    && addressesAreEquals(httpServletRequest.getRemoteAddr(), claimsJws.getBody().get("address").toString());

            if (userAuthenticated) {
                UsernamePasswordAuthenticationToken authenticationToken = optionalAuthenticationToken.get();
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }


        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private boolean addressesAreEquals(final String remoteAddress, final String tokenAddress) {
        boolean result = remoteAddress.equals(tokenAddress);
        if (!result)
            logger.info("Remote address {} <----------> Token address", remoteAddress, tokenAddress);
        return result;
    }


    private Optional<UsernamePasswordAuthenticationToken> fetchAuthenticationToken(final String username) {
        Optional<UsernamePasswordAuthenticationToken> result = Optional.empty();
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            result = Optional.of(new UsernamePasswordAuthenticationToken(
                    userDetails.getUsername(), null, userDetails.getAuthorities()));
        } catch (UsernameNotFoundException e) {
            logger.info("User not exists");
        }
        return result;
    }
}
