package com.cadastro.produtos.controllers;

import com.cadastro.produtos.dtos.UsuarioDto;
import com.cadastro.produtos.models.UsuarioModel;
import com.cadastro.produtos.repositories.UsuarioRepository;
import com.cadastro.produtos.requests.UsuarioRequest;
import com.sun.jdi.request.DuplicateRequestException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
@Log4j2
public class UsuarioController {

    @Autowired
    private final UsuarioRepository usuarioRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDto cadastrar(@RequestBody @Valid UsuarioRequest usuarioRequest) {
        Optional<UsuarioModel> optionalUserModel = this.usuarioRepository.findByUsername(usuarioRequest.getUsername());
        if (optionalUserModel.isPresent()) {
            throw new DuplicateRequestException("Usuário já cadastrado");
        }
        UsuarioModel user = UsuarioModel.builder()
                .username(usuarioRequest.getUsername())
                .password(passwordEncoder.encode(usuarioRequest.getPassword()))
                .build();

        UsuarioModel userModel = this.usuarioRepository.save(user);

        return new UsuarioDto(userModel);
    }

}
