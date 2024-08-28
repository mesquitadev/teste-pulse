package br.com.mesquitadev.estoque.dao;

import br.com.mesquitadev.estoque.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoDAO extends JpaRepository<Produto, Long> {}