package com.brunoonofre64.service;

import com.brunoonofre64.apiError.ErrorMessage;
import com.brunoonofre64.domain.Produto;
import com.brunoonofre64.repositories.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public Produto getProdutoById( Integer id ) {
        return produtoRepository
                .findById(id)
                .orElseThrow(() -> getResponseStatusException());
    }

    public Produto save( Produto produto ) {
        return produtoRepository.save(produto);
    }

    public void update( Integer id, Produto produto ) {
        produtoRepository
                .findById(id)
                .map( produtoAtual -> {
                    produto.setId(produtoAtual.getId());
                    produtoRepository.save(produto);
                    return produtoAtual;
                }).orElseThrow( () -> getResponseStatusException());
    }

    public void delete( Integer id) {
        produtoRepository
                .findById(id)
                .map( produto -> {
                    produtoRepository.delete(produto);
                    return produto;
                }).orElseThrow( () -> getResponseStatusException());
    }

    public List<Produto> findProduto( Produto filtro ) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filtro, matcher);
        return produtoRepository.findAll(example);
    }

    private ResponseStatusException getResponseStatusException() {
        return new ResponseStatusException(HttpStatus.NOT_FOUND,
                ErrorMessage.PRODUTO_NAO_ENCONTRADO);
    }
}
