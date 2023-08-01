package FinTrackAPI.com.br.FinTrackAPI.Model.Repository;

import FinTrackAPI.com.br.FinTrackAPI.Model.Entity.Receita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {
}
