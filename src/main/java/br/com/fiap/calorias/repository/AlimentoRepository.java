package br.com.fiap.calorias.repository;

import br.com.fiap.calorias.model.Alimento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AlimentoRepository extends JpaRepository<Alimento, Long> {

    @Query("SELECT a FROM Alimento a WHERE a.nome = :nome")
    Optional<Alimento> buscarPorNome(@Param("nome") String nome);

    @Query("SELECT a FROM Alimento a WHERE a.totalCalorias BETWEEN :minimo AND :maximo ORDER BY a.totalCalorias DESC")
    Page<Alimento> listarAlimentosPorFaixaDeCalorias(
            @Param("minimo") Double minimo,
            @Param("maximo") Double maximo,
            Pageable pageable
    );

    @Query("SELECT a FROM Alimento a WHERE a.nome LIKE CONCAT(:prefixo, '%')")
    Page<Alimento> listarPorNomeComecandoCom(@Param("prefixo") String prefixo, Pageable pageable);

    @Query("SELECT a FROM Alimento a WHERE a.quantidadeGorduras < :limite")
    Page<Alimento> listarPorGorduraInferiorA(@Param("limite") Double limite, Pageable pageable);

    Page<Alimento> findByTotalCaloriasLessThan(Double caloria, Pageable pageable);
}
