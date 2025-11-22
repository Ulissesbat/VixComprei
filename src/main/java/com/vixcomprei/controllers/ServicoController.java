package com.vixcomprei.controllers;

import com.vixcomprei.dto.ServicoDto;
import com.vixcomprei.dto.UsuarioDto;
import com.vixcomprei.services.ServicoService;
import com.vixcomprei.services.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/servico")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;
    @GetMapping
    public ResponseEntity<Page<ServicoDto>> findAll(Pageable pageable) {
        Page<ServicoDto> list = servicoService.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ServicoDto> findById(@PathVariable Long id) {
        ServicoDto dto = servicoService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<ServicoDto> insert(@RequestBody ServicoDto dto){
        dto = servicoService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ServicoDto> update(@PathVariable Long id, @RequestBody ServicoDto dto) {
        try {
            ServicoDto newDto = servicoService.update(id, dto);
            return ResponseEntity.ok().body(newDto);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        servicoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
