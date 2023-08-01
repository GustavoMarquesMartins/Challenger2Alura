package FinTrackAPI.com.br.FinTrackAPI.Service;

import FinTrackAPI.com.br.FinTrackAPI.DTO.ReceitaDadosCadastroDTO;
import FinTrackAPI.com.br.FinTrackAPI.DTO.ReceitaDadosResponseDTO;
import FinTrackAPI.com.br.FinTrackAPI.Model.Entity.Receita;
import FinTrackAPI.com.br.FinTrackAPI.Model.Repository.ReceitaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import java.sql.SQLIntegrityConstraintViolationException;

@Service
public class ReceitaServico {

    @Autowired
    private ReceitaRepository receitaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Receita salvar(ReceitaDadosCadastroDTO receitaDadosCadastroDTO) throws SQLIntegrityConstraintViolationException {
        return receitaRepository.save(modelMapper.map(receitaDadosCadastroDTO, Receita.class));
    }
}