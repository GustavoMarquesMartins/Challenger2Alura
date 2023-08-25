package FinTrackAPI.com.br.FinTrackAPI.Model.Repository;

import FinTrackAPI.com.br.FinTrackAPI.DTO.ResponseDTO;
import FinTrackAPI.com.br.FinTrackAPI.Model.Entity.Categoria;
import FinTrackAPI.com.br.FinTrackAPI.Model.Entity.Receita;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {
    List<Receita> findAllByCategoria(Categoria categoria);

    @Query("SELECT D FROM Receita D WHERE YEAR(D.data) = :ano and MONTH(D.data) = :mes")
    List<Receita> findByData(Integer ano, Integer mes);

    @Query("SELECT SUM(D.valor) FROM Receita D WHERE YEAR(D.data) = :ano AND  MONTH(D.data) = :mes")
    BigDecimal somaTotalReceitas(Integer ano, Integer mes);
}
