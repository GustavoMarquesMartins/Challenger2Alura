package FinTrackAPI.com.br.FinTrackAPI.Service;

import FinTrackAPI.com.br.FinTrackAPI.DTO.RequestDTO;
import FinTrackAPI.com.br.FinTrackAPI.DTO.ResponseDTO;
import FinTrackAPI.com.br.FinTrackAPI.Model.Entity.Receita;
import FinTrackAPI.com.br.FinTrackAPI.Model.Repository.ReceitaRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.NoSuchElementException;

@Service
public class ReceitaServico {

    @Autowired
    private ReceitaRepository receitaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Receita salvar(RequestDTO requestDTO) throws SQLIntegrityConstraintViolationException {
        return receitaRepository.save(modelMapper.map(requestDTO, Receita.class));
    }

    public Page<ResponseDTO> listagem(Pageable pageable) {
        return receitaRepository.findAll(pageable).map(ResponseDTO::new);
    }

    public ResponseDTO detalhar(long id) {
        var receita = receitaRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Valor não encontrado"));
        return new ResponseDTO(receita);
    }

    @Transactional
    public ResponseDTO atualizar(Long id, RequestDTO requestDTO) {
        var receita = receitaRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Valor não encontrado"));
        receita.atualizar(requestDTO);
        return new ResponseDTO(receita);
    }

    @Transactional
    public void deletar(Long id) {
        receitaRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Valor não encontrado"));
        receitaRepository.deleteById(id);
    }
}