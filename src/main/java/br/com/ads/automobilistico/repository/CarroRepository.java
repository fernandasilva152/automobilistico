package br.com.ads.automobilistico.repository;

import br.com.ads.automobilistico.model.CarroModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CarroRepository extends JpaRepository<CarroModel, Long> {

    Optional<CarroModel> findById(long id);

    public Page<CarroModel> findAll(Pageable pageable);


    List<CarroModel> findByNomeContainsIgnoreCaseOrderByNome(String nome);


    List<CarroModel> findByPlacaStartsWithIgnoreCase(String placa);

}