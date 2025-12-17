package com.mx.apicustomerclient.security.component;

import com.mx.apicustomerclient.dao.value.DaoValue;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

@Component
public class JwtFilterComponent extends OncePerRequestFilter {

    private static final Logger LOG = LogManager.getLogger(JwtFilterComponent.class);
    @Value("${jwt.secret}")
    private String secret;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        LOG.info("::::::::::::Validacion del token:::::::::::");
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            try {
                String usuario = Jwts.parserBuilder()
                        .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
                        .build()
                        .parseClaimsJws(token)
                        .getBody()
                        .getSubject();

                if (usuario != null) {
                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(usuario, null, Collections.emptyList());
                    SecurityContextHolder.getContext().setAuthentication(auth);
                    LOG.info("::::::::::::Usuario validado:::::::::::");
                }

            } catch (Exception e) {
                LOG.info("::::::::::::Token invalido:::::::::::");
                final String metodo = e.getStackTrace()[DaoValue.INT_VALOR_METODO].getMethodName();
                LOG.error(DaoValue.STR_FROMATO_DAO_ERROR.formatted(metodo, e));
            }
        }

        chain.doFilter(request, response);
    }
}
