package br.com.domain.Loja.Models;

import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity @Data
public class Produto {

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

    private BigDecimal preco;
}