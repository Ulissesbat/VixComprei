package com.vixcomprei.controllers;

import com.vixcomprei.dto.RestauranteDto;
import com.vixcomprei.dto.ServicoDto;
import com.vixcomprei.services.RestauranteService;
import com.vixcomprei.services.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/restaurante")
public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;

    @GetMapping
    public ResponseEntity<Page<RestauranteDto>> findAll(Pageable pageable) {
        Page<RestauranteDto> list = restauranteService.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RestauranteDto> findById(@PathVariable Long id) {
        RestauranteDto dto = restauranteService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<RestauranteDto> insert(@RequestBody RestauranteDto dto){
        dto = restauranteService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<RestauranteDto> update(@PathVariable Long id, @RequestBody RestauranteDto dto) {
        try {
            RestauranteDto newDto = restauranteService.update(id, dto);
            return ResponseEntity.ok().body(newDto);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        restauranteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
