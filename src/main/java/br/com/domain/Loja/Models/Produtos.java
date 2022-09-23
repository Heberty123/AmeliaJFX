package br.com.domain.Loja.Models;

import lombok.Data;
import javax.persistence.*;

@Entity @Data
public class Produtos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Lob
    private String descricao;

    private String referencia;

    private Long codigoBarra;
    @ManyToOne
    private Marca marca;

    @OneToOne
    private Preco preco;
}