package FinTrackAPI.com.br.FinTrackAPI.Model.Repository;

import FinTrackAPI.com.br.FinTrackAPI.Model.Entity.Categoria;
import FinTrackAPI.com.br.FinTrackAPI.Model.Entity.Despesa;
import FinTrackAPI.com.br.FinTrackAPI.Model.Entity.Receita;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public interface DespesasRepository extends JpaRepository<Despesa, Long> {
    List<Despesa> findAllByCategoria( Categoria categoria);

    @Query("SELECT D FROM Despesa D WHERE YEAR(D.data) = :ano and MONTH(D.data) = :mes")
    List<Despesa> findByData(Integer ano, Integer mes);

    @Query("SELECT SUM(D.valor) FROM Despesa D WHERE YEAR(D.data) = :ano AND  MONTH(D.data) = :mes")
    BigDecimal somaTotalDepesas(Integer ano, Integer mes);

    @Query("SELECT D.categoria,SUM(D.valor) as valor_total FROM Despesa D WHERE YEAR(D.data) = :ano AND  MONTH(D.data) = :mes GROUP BY D.categoria ")
    List<Object[]> valorGastoPorCategoria(Integer ano, Integer mes);
}
