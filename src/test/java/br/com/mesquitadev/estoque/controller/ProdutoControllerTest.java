package br.com.mesquitadev.estoque.controller;

import br.com.mesquitadev.estoque.converter.ProdutoConverter;
import br.com.mesquitadev.estoque.dto.request.ProdutoPersist;
import br.com.mesquitadev.estoque.dto.request.ProdutoUpdate;
import br.com.mesquitadev.estoque.dto.response.ProdutoResponse;
import br.com.mesquitadev.estoque.models.Produto;
import br.com.mesquitadev.estoque.service.ProdutoService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProdutoControllerTest {
  private final MockMvc mockMvc;
  @Mock
  private ProdutoService produtoService;
  @Mock
  private ProdutoConverter produtoConverter;
  @InjectMocks
  private ProdutoController produtoController;

  public ProdutoControllerTest() {
    MockitoAnnotations.openMocks(this);
    this.mockMvc = MockMvcBuilders.standaloneSetup(produtoController).build();
  }

  @Test
  public void testBuscarProdutoPorId() throws Exception {
    Produto produto = new Produto();
    produto.setId(1L);
    produto.setNome("Produto 1");
    produto.setDescricao("Descricao 1");
    produto.setPreco(100.0);
    produto.setQuantidade(10);

    when(produtoService.buscarProdutoPorId(1L)).thenReturn(Optional.of(produto));

    mockMvc.perform(get("/api/v1/produtos/1")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().json("{'id':1,'nome':'Produto 1','descricao':'Descricao 1','preco':100.0,'quantidade':10}"));
  }

  @Test
  public void testSave() throws Exception {
    ProdutoPersist persist = new ProdutoPersist();
    persist.setNome("Produto 1");
    persist.setDescricao("Descricao 1");
    persist.setPreco(100.0);
    persist.setQuantidade(10);

    Produto produto = new Produto();
    produto.setNome("Produto 1");
    produto.setDescricao("Descricao 1");
    produto.setPreco(100.0);
    produto.setQuantidade(10);

    ProdutoResponse response = new ProdutoResponse();
    response.setNome("Produto 1");
    response.setDescricao("Descricao 1");
    response.setPreco(100.0);
    response.setQuantidade(10);

    when(produtoService.criarProduto(persist)).thenReturn(produto);
    when(produtoConverter.entityToResponse(produto)).thenReturn(response);

    mockMvc.perform(post("/api/v1/produtos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"nome\":\"Produto 1\",\"descricao\":\"Descricao 1\",\"preco\":100.0,\"quantidade\":10}"))
            .andExpect(status().isOk())
            .andExpect(content().json("{'nome':'Produto 1','descricao':'Descricao 1','preco':100.0,'quantidade':10}"));
  }

  @Test
  public void testUpdate() throws Exception {
    ProdutoUpdate update = new ProdutoUpdate();
    update.setNome("Produto Editado");
    update.setDescricao("Descricao Editada");
    update.setPreco(150.0);
    update.setQuantidade(20);

    Produto produto = new Produto();
    produto.setId(1L);
    produto.setNome("Produto Editado");
    produto.setDescricao("Descricao Editada");
    produto.setPreco(150.0);
    produto.setQuantidade(20);

    ProdutoResponse response = new ProdutoResponse();
    response.setNome("Produto Editado");
    response.setDescricao("Descricao Editada");
    response.setPreco(150.0);
    response.setQuantidade(20);

    when(produtoService.editarProduto(1L, update)).thenReturn(produto);
    when(produtoConverter.entityToResponse(produto)).thenReturn(response);

    mockMvc.perform(patch("/api/v1/produtos/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"nome\":\"Produto Editado\",\"descricao\":\"Descricao Editada\",\"preco\":150.0,\"quantidade\":20}"))
            .andExpect(status().isOk())
            .andExpect(content().json("{'nome':'Produto Editado','descricao':'Descricao Editada','preco':150.0,'quantidade':20}"));
  }

  @Test
  public void testDelete() throws Exception {
    mockMvc.perform(delete("/api/v1/produtos/1")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());
  }
}