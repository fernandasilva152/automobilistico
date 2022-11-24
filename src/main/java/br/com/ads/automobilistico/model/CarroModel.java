package br.com.ads.automobilistico.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import javax.persistence.*;

@Entity
@Table(name = "carro")
@AllArgsConstructor
@NoArgsConstructor

public class CarroModel extends RepresentationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter @Getter
    @ApiModelProperty(notes = "ID")
    private long id;

    @Column(name = "nome", nullable = false, length = 50)
    @Setter @Getter
    @ApiModelProperty(notes = "Nome")
    private String nome;

    @Column(nullable = false, length = 1)
    @Getter @Setter
    @ApiModelProperty(notes = "Quantidade de portas")
    private Integer portas;

    @Column(nullable = false, length = 50)
    @Getter @Setter
    @ApiModelProperty(notes = "Cor")
    private String cor;

    @Column(nullable = false, length = 50)
    @Getter @Setter
    @ApiModelProperty(notes = "Modelo")
    private String modelo;

    @Column(nullable = false, length = 4)
    @Getter @Setter
    @ApiModelProperty(notes = "Ano")
    private Integer ano;

    @Column(nullable = false, length = 7)
    @Getter @Setter
    @ApiModelProperty(notes = "Placa")
    private String placa;



    //..relacionamento com a model Marca
    @ManyToOne
    @JoinColumn(name = "marca_id")
    @Setter @Getter
    private MarcaModel marca;



}
