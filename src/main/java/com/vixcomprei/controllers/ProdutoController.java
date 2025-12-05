package com.vixcomprei.controllers;

import com.vixcomprei.dto.CategoriaDto;
import com.vixcomprei.dto.ProdutoDto;
import com.vixcomprei.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<Page<ProdutoDto>> findAll(Pageable pageable) {
        Page<ProdutoDto> list = produtoService.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProdutoDto> findById(@PathVariable Long id) {
        ProdutoDto dto = produtoService.findById(id);
        return ResponseEntity.ok().body(dto);
    }
}
