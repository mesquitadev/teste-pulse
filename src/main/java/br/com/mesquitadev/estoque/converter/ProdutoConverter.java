package br.com.mesquitadev.estoque.converter;
import br.com.mesquitadev.estoque.dto.request.ProdutoPersist;
import br.com.mesquitadev.estoque.dto.request.ProdutoUpdate;
import br.com.mesquitadev.estoque.dto.response.ProdutoResponse;
import br.com.mesquitadev.estoque.models.Produto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface ProdutoConverter {
    ProdutoConverter produtoConverter    = Mappers.getMapper(ProdutoConverter.class);

    ProdutoResponse entityToResponse(Produto produto);

    default Page<ProdutoResponse> pageEntityToResponse(Page<Produto> produtos) {
        return produtos.map(this::entityToResponse);
    }

    Produto persistToEntity(ProdutoPersist produtoPersist);

    Produto updateToEntity(ProdutoUpdate produtoUpdate);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toEntity(Produto produto, @MappingTarget Produto newProduto);
}