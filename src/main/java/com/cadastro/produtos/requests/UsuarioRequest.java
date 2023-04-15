package com.cadastro.produtos.requests;

import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UsuarioRequest {

    @Pattern(regexp = "[\\w\\p{L}.]{5,20}")
    private String username;//a-zA-Zacentos0-9. tamanho de 5 a 20
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{3,20}$")
    private String password;

}
