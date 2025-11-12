package com.vixcomprei.repositories;

import com.vixcomprei.entities.Restaurante;
import com.vixcomprei.entities.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {
}
