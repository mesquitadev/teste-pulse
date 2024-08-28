package br.com.mesquitadev.estoque.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProdutoUpdate {
    private String nome;
    private String descricao;
    private double preco;
    private int quantidade;
}
