package br.com.fiap.calorias.repository;

import br.com.fiap.calorias.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    @Query("SELECT u FROM Usuario u WHERE u.email LIKE CONCAT('%', :dominio)")
    Page<Usuario> listarPorDominioEmail(@Param("dominio") String dominio, Pageable pageable);
}
