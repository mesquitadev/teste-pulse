package br.com.mesquitadev.estoque.dto.response;

import lombok.Data;

@Data
public class ProdutoResponse {
  private Long id;
  private String nome;
  private String descricao;
  private double preco;
  private int quantidade;
}
