package com.vixcomprei.repositories;

import com.vixcomprei.entities.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteRepository extends JpaRepository <Restaurante, Long> {
}
