package FinTrackAPI.com.br.FinTrackAPI.Controller;

import FinTrackAPI.com.br.FinTrackAPI.DTO.ResponseDTO;
import FinTrackAPI.com.br.FinTrackAPI.DTO.ResponseResumoDTO;
import FinTrackAPI.com.br.FinTrackAPI.Service.DespesaServico;
import FinTrackAPI.com.br.FinTrackAPI.Service.ReceitaServico;
import FinTrackAPI.com.br.FinTrackAPI.Service.ResumoServico;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/resumo")
@SecurityRequirement(name = "bearer-key")
public class ResumoController {

    @Autowired
    private ResumoServico resumoServico;
    @Autowired
    private ReceitaServico receitaServico;
    @Autowired
    private DespesaServico despesaServico;
    @GetMapping("/{ano}/{mes}")
    public ResponseEntity<ResponseResumoDTO> listagem(@PathVariable Integer ano, @PathVariable Integer mes) {
        return ResponseEntity.ok().body(resumoServico.listarSomaTodasReceitas(ano,mes));
    }
}
