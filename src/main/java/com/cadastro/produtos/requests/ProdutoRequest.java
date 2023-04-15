package com.cadastro.produtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoRequest {
    @NotBlank
    private String nomeProduto;
    @NotBlank
    private String fabricanteProduto;

    private BigDecimal precoProduto;

}
