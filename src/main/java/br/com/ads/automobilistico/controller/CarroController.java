package br.com.ads.automobilistico.controller;

import br.com.ads.automobilistico.model.CarroModel;
import br.com.ads.automobilistico.service.CarroService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carro/v1")
@Api(value = "Carro Endpoint")
public class CarroController {

    @Autowired
    private CarroService service;

    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE })
    @ApiOperation(value = "Retorna o carro pelo ID")
    public CarroModel findById(
            @ApiParam(name = "id", value = "Valor inteiro válido", required = true)
            @PathVariable("id") long id){
        //..........................................................
        var carroModel = service.findById(id);
        if(carroModel.isPresent()){
            buildEntityLink(carroModel.get());
            return carroModel.get();
        } else {
            return null;
        }
        //............................................................
    }

    @GetMapping( produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<PagedModel<CarroModel>> findAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "direction", defaultValue = "asc") String direction,
            PagedResourcesAssembler<CarroModel> assembler
    ){
        var sortDirection = "desc".equals(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "nome"));

        Page<CarroModel> carros = service.findAll(pageable);

        for(CarroModel carro : carros){
            buildEntityLink(carro);
        }

        return new ResponseEntity(assembler.toModel(carros), HttpStatus.OK);

    }

    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public CarroModel save(@RequestBody CarroModel model){
        return service.save(model);
    }

    @PutMapping( produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE } )
    public CarroModel update(@RequestBody CarroModel model){
        return service.update(model);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id){
        Optional<CarroModel> found = service.findById(id);
        if(found.isPresent()){
            service.delete(id);
            return ResponseEntity.ok().build();
        } else {
            return null;
        }
    }

    @GetMapping("/find/placa/{placa}")
    public List<CarroModel> findByPlaca(@PathVariable("placa") String placa){
        return service.findByPlaca(placa);
    }

    private void buildEntityLink(CarroModel model){
        model.add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(
                                this.getClass()).findById(model.getId())
                ).withSelfRel()
        );

        if(!model.getMarca().hasLinks()) {
            Link marcaLink = WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(
                            MarcaController.class).findById(model.getMarca().getId())
            ).withSelfRel();
            model.getMarca().add(marcaLink);
        }
    }

}
