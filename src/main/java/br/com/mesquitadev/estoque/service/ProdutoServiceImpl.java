package br.com.mesquitadev.estoque.service;

import br.com.mesquitadev.estoque.converter.ProdutoConverter;
import br.com.mesquitadev.estoque.dto.request.ProdutoPersist;
import br.com.mesquitadev.estoque.dto.request.ProdutoUpdate;
import br.com.mesquitadev.estoque.exception.ProdutoNaoEncontradoException;
import br.com.mesquitadev.estoque.factory.ProdutoDAOFactory;
import br.com.mesquitadev.estoque.models.Produto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoServiceImpl implements ProdutoService {

  private final ProdutoDAOFactory produtoDaoFactory;
  private final ProdutoConverter produtoConverter;

  @Autowired
  public ProdutoServiceImpl(ProdutoDAOFactory produtoDaoFactory, ProdutoConverter produtoConverter) {
    this.produtoDaoFactory = produtoDaoFactory;
    this.produtoConverter = produtoConverter;
  }

  public Page<Produto> listarProdutos(Pageable pageable) {
    return produtoDaoFactory.getProdutoDAO().findAll(pageable);
  }


  @Transactional
  public Produto criarProduto(ProdutoPersist produtoPersist) {
    Produto produto = produtoConverter.persistToEntity(produtoPersist);
    produtoDaoFactory.getProdutoDAO().save(produto);
    return produto;
  }

  @Override
  public Optional<Produto> buscarProdutoPorId(Long id) {
    return Optional.ofNullable(produtoDaoFactory.getProdutoDAO().findById(id).orElseThrow(() -> new ProdutoNaoEncontradoException(id)));
  }

  @Override
  @Transactional
  public Produto editarProduto(Long id, ProdutoUpdate produto) {
    if(!produtoDaoFactory.getProdutoDAO().existsById(id)) {
      throw new ProdutoNaoEncontradoException(id);
    }
    Produto produtoEditado = produtoConverter.updateToEntity(produto);
    produtoEditado.setId(id);
    produtoDaoFactory.getProdutoDAO().save(produtoEditado);
    return produtoEditado;
  }

  @Override
  @Transactional
  public void deletarProduto(Long id) {
    if(!produtoDaoFactory.getProdutoDAO().existsById(id)) {
      throw new ProdutoNaoEncontradoException(id);
    }
    produtoDaoFactory.getProdutoDAO().deleteById(id);
  }
}
