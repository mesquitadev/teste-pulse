package br.com.mesquitadev.estoque.factory;

import br.com.mesquitadev.estoque.dao.ProdutoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoProdutoDaoFactoryImpl implements ProdutoDAOFactory {
  @Autowired
  private ProdutoDAO produtoDAO;

  @Override
  public ProdutoDAO getProdutoDAO() {
    return produtoDAO;
  }
}