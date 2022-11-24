package br.com.ads.automobilistico.controller;

import br.com.ads.automobilistico.model.MarcaModel;
import br.com.ads.automobilistico.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/marca/v1")
public class MarcaController {

    @Autowired
    private MarcaService service;

    @GetMapping()
    public List<MarcaModel> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public MarcaModel findById(@PathVariable("id") long id){
        var marcaModel = service.findById(id);
        if(marcaModel.isPresent()){
            return marcaModel.get();
        } else{
            return null;
        }
    }

}
