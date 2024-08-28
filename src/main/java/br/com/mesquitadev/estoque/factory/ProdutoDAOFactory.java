package br.com.mesquitadev.estoque.factory;

import br.com.mesquitadev.estoque.dao.ProdutoDAO;

public interface ProdutoDAOFactory {
  ProdutoDAO getProdutoDAO();
}