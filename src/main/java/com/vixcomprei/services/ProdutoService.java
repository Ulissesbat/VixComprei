package com.vixcomprei.services;

import com.vixcomprei.dto.CategoriaDto;
import com.vixcomprei.dto.ProdutoDto;
import com.vixcomprei.entities.Categoria;
import com.vixcomprei.entities.Produto;
import com.vixcomprei.repositories.ProdutoRepository;
import com.vixcomprei.services.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional(readOnly = true)
    public Page<ProdutoDto> findAllPaged(Pageable pageable) {
        Page<Produto> list = produtoRepository.findAll(pageable);
        return list.map(ProdutoDto::new);
    }

    @Transactional(readOnly = true)
    public ProdutoDto findById(Long id) {
        Produto entity = produtoRepository.findByIdWithImages(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found " + id));
        return new ProdutoDto(entity);
    }

}
