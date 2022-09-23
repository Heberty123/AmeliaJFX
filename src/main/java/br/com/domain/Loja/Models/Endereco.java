package br.com.domain.Loja.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.*;


@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Endereco {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String rua;

    private int numero;

    private String bairro;

    private String cidade;

    private String cep;

    private TipoEntrega tipoEntrega;

    private String telefone;

    private String celular;

    @ManyToOne
    private Cliente cliente;

}