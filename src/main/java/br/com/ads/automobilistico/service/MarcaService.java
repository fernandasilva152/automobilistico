package br.com.ads.automobilistico.service;

import br.com.ads.automobilistico.model.MarcaModel;
import br.com.ads.automobilistico.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarcaService {

    @Autowired
    private MarcaRepository repository;

    public List<MarcaModel> findAll(){
        return repository.findAll();
    }

    public Optional<MarcaModel> findById(long id){
        return repository.findById(id);
    }


}
