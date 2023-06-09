package com.cadastro.produtos.models;

import com.cadastro.produtos.dtos.ProdutoDto;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "TB_PRODUTO")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 50)
    private String nomeProduto;
    @Column(nullable = false, length = 50)
    private String fabricanteProduto;
    @Column(nullable = false)
    private BigDecimal precoProduto;

    public ProdutoModel(ProdutoDto produtoDto){
        this.nomeProduto = produtoDto.getNomeProduto();
        this.fabricanteProduto = produtoDto.getFabricanteProduto();
        this.precoProduto = produtoDto.getPrecoProduto();
    }

}
