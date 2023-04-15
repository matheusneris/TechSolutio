package com.cadastro.produtos.requests;

import lombok.*;

import javax.validation.constraints.NotBlank;
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
