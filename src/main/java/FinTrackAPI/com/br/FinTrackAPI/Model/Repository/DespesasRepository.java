package FinTrackAPI.com.br.FinTrackAPI.Model.Repository;

import FinTrackAPI.com.br.FinTrackAPI.Model.Entity.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DespesasRepository extends JpaRepository<Despesa, Long> {
}
