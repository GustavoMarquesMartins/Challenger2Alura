package FinTrackAPI.com.br.FinTrackAPI.Service;

import FinTrackAPI.com.br.FinTrackAPI.DTO.RequestDTO;
import FinTrackAPI.com.br.FinTrackAPI.DTO.ResponseDTO;
import FinTrackAPI.com.br.FinTrackAPI.Model.Entity.Categoria;
import FinTrackAPI.com.br.FinTrackAPI.Model.Entity.Despesa;
import FinTrackAPI.com.br.FinTrackAPI.Model.Repository.DespesasRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class DespesaServico {

    @Autowired
    private DespesasRepository despesasRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Despesa salvar(RequestDTO requestDTO) throws SQLIntegrityConstraintViolationException {
        return despesasRepository.save(modelMapper.map(requestDTO, Despesa.class));
    }

    public List<ResponseDTO> listagem() {
        return despesasRepository.findAll().stream().map(ResponseDTO::new).collect(Collectors.toList());
    }

    public List<ResponseDTO> listagemPorCategoria(Categoria categoria) {
        return despesasRepository.findAllByCategoria(categoria).stream().map(ResponseDTO::new).collect(Collectors.toList());
    }

    public ResponseDTO detalhar(long id) {
        var despesa = despesasRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Valor não encontrado"));
        return new ResponseDTO(despesa);
    }

    @Transactional
    public ResponseDTO atualizar(Long id, RequestDTO requestDTO) {
        var despesa = despesasRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Valor não encontrado"));
        despesa.atualizar(requestDTO);
        return new ResponseDTO(despesa);
    }

    @Transactional
    public void deletar(Long id) {
        despesasRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Valor não encontrado"));
        despesasRepository.deleteById(id);
    }

    public List<ResponseDTO> listagemPorData(Integer ano, Integer mes) {
        return despesasRepository.findByData(ano, mes).stream().map(receitas -> new ResponseDTO(receitas)).collect(Collectors.toList());
    }
}