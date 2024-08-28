package br.com.mesquitadev.estoque.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProdutoPersist {
    @NotNull(message = "O nome do produto é obrigatório")
    private String nome;
    @NotNull(message = "A descrição do produto é obrigatória")
    private String descricao;
    @NotNull(message = "O preço do produto é obrigatório")
    private double preco;
    @NotNull(message = "A quantidade do produto é obrigatória")
    private int quantidade;
}
