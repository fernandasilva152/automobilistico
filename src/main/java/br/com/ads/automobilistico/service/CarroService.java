package br.com.ads.automobilistico.service;

import br.com.ads.automobilistico.model.CarroModel;
import br.com.ads.automobilistico.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarroService {

    @Autowired
    private CarroRepository repository;

    public Optional<CarroModel> findById(long id){
        return repository.findById(id);
    }

    public Page<CarroModel> findAll(Pageable pageable){
        return repository.findAll(pageable);
    }

    public CarroModel save(CarroModel model){
        return repository.save(model);
    }

    public CarroModel update(CarroModel model){
        var found = repository.findById(model.getId());
        if(found.isPresent()){
            found.get().setNome(model.getNome());
            found.get().setPortas(model.getPortas());
            found.get().setCor(model.getCor());
            found.get().setModelo(model.getModelo());
            found.get().setAno(model.getAno());
            found.get().setPlaca(model.getPlaca());
            return repository.save(found.get());
        } else {
            return null;
        }
    }

    public void delete(long id){
        var found = repository.findById(id);
        if(found.isPresent()){
            repository.delete(found.get());
        }
    }

    public List<CarroModel> findByPlaca(String placa){
        return repository.findByPlacaStartsWithIgnoreCase(placa);
    }


}
