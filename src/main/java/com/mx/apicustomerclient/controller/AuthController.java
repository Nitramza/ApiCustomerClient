package com.mx.apicustomerclient.controller;

import com.mx.apicustomerclient.security.dto.CrediancialesDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    @Value("${api.admin.user}")
    private String adminUser;

    @Value("${api.admin.pass}")
    private String adminPass;

    @PostMapping("/token")
    public ResponseEntity<Object> generarToken(@RequestBody CrediancialesDto credenciales) {

        String usuario = credenciales.getUsuario();
        String clave = credenciales.getClave();

        if (!adminUser.equals(usuario) || !adminPass.equals(clave)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no autorizado para esta aplicación");
        }

        long tiempoActual = System.currentTimeMillis();
        String token = Jwts.builder()
                .setSubject(usuario)
                .setIssuedAt(new Date(tiempoActual))
                .setExpiration(new Date(tiempoActual + expiration))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
                .compact();

        return ResponseEntity.ok(Map.of("token", token));
    }
}
