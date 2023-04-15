package com.cadastro.produtos.dtos;

import com.cadastro.produtos.models.UsuarioModel;

public record UsuarioDto (String username, String role) {

    public UsuarioDto(UsuarioModel userModel) {
        this(userModel.getUsername() , "ROLE_USER");
    }

}
