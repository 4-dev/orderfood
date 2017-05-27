package br.com.fourdev.orderfood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fourdev.orderfood.model.Grupo;

@Repository
public interface Grupos extends JpaRepository<Grupo, Long> {

}
