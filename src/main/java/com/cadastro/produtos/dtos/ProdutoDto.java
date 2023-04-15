package com.cadastro.produtos.dtos;

import com.cadastro.produtos.models.ProdutoModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDto {

    private String nomeProduto;
    private String fabricanteProduto;
    private BigDecimal precoProduto;

    public ProdutoDto(ProdutoModel produtoModel){
        this.nomeProduto = produtoModel.getNomeProduto();
        this.fabricanteProduto = produtoModel.getFabricanteProduto();
        this.precoProduto = produtoModel.getPrecoProduto();
    }

}
