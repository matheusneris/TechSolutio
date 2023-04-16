package com.cadastro.produtos.controllers;

import com.cadastro.produtos.models.UsuarioModel;
import com.cadastro.produtos.repositories.UsuarioRepository;
import com.cadastro.produtos.requests.AuthenticationRequest;
import com.cadastro.produtos.requests.AuthenticationResponse;
import com.cadastro.produtos.services.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
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

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public AuthenticationResponse login(@RequestBody AuthenticationRequest request){
        var authentication = new UsernamePasswordAuthenticationToken(request.username(), request.password());
        authenticationManager.authenticate(authentication);
        UsuarioModel usuario = usuarioRepository.findByUsername(request.username()).orElseThrow();
        String token = jwtService.createToken(usuario);
        return new AuthenticationResponse(token);
    }

}
