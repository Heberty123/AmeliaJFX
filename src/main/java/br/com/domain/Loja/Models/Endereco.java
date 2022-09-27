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

    private String logradouro;

    private int numero;

    private String bairro;

    private String localidade;

    private String uf;

    private String cep;

    private TipoEntrega tipoEntrega;

    private String telefone;

    private String celular;

    @ManyToOne
    private Cliente cliente;

    @Override
    public String toString() {
        return "Endereco{" +
                "id=" + id +
                ", logradouro='" + logradouro + '\'' +
                ", numero=" + numero +
                ", bairro='" + bairro + '\'' +
                ", localidade='" + localidade + '\'' +
                ", uf='" + uf + '\'' +
                ", cep='" + cep + '\'' +
                ", tipoEntrega=" + tipoEntrega +
                ", telefone='" + telefone + '\'' +
                ", celular='" + celular + '\'' +
                ", cliente=" + cliente +
                '}';
    }

}