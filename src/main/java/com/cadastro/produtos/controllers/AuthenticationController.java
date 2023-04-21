package com.cadastro.produtos.controllers;

import com.cadastro.produtos.models.UsuarioModel;
import com.cadastro.produtos.repositories.UsuarioRepository;
import com.cadastro.produtos.requests.AuthenticationRequest;
import com.cadastro.produtos.requests.AuthenticationResponse;
import com.cadastro.produtos.services.JwtService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.SSLSession;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@Log
public class AuthenticationController {

    @Autowired
    private final UsuarioRepository usuarioRepository;
    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final JwtService jwtService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthenticationResponse login(@RequestBody AuthenticationRequest request) throws IOException {
        var authentication = new UsernamePasswordAuthenticationToken(request.username(), request.password());
        authenticationManager.authenticate(authentication);
        UsuarioModel usuario = usuarioRepository.findByUsername(request.username()).orElseThrow();
        String token = jwtService.createToken(usuario);
        return new AuthenticationResponse("Bearer " + token);

    }

    @SecurityRequirement(name = "USER")
    @GetMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String authorization){
        String token = authorization.split(" ")[1];
        if(!jwtService.tokenNotBlocked(token)){
            return ResponseEntity.status(HttpStatus.OK).body("Usuário não está logado.");
        }
        jwtService.saveTokenToBlockedList(token);
        return ResponseEntity.status(HttpStatus.OK).body("Logout feito com sucesso");
    }

}
