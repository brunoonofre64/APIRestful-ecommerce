package com.brunoonofre64.rest.controller;

import com.brunoonofre64.domain.Cliente;
import com.brunoonofre64.domain.Produto;
import com.brunoonofre64.service.ProdutoService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/protuto/v1/api")
public class ProdutoController {

    private ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/{id}")
    public Produto getProdutoById(@PathVariable("id") Integer id ) {
        return produtoService.getProdutoById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Produto save( @RequestBody @Valid Produto produto ) {
        return produtoService.save(produto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void update( @PathVariable("id") Integer id,
                        @RequestBody @Valid Produto produto ) {
        produtoService.update(id, produto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete( @PathVariable("id") Integer id ) {
        produtoService.delete(id);
    }

    @GetMapping
    public List<Produto> findCliente( Produto filtro ) {
        return produtoService.findProduto(filtro);
    }


}
