package br.com.ads.automobilistico.repository;

import br.com.ads.automobilistico.model.MarcaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MarcaRepository extends JpaRepository<MarcaModel, Long> {

    Optional<MarcaModel> findById(long id);


}
