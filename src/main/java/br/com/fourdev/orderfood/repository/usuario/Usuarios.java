package br.com.fourdev.orderfood.repository.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fourdev.orderfood.model.Usuario;

@Repository
public interface Usuarios extends JpaRepository<Usuario, Long> {

}
