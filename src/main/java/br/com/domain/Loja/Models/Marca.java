package br.com.domain.Loja.Models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Marca {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToMany(mappedBy = "marca")
    private List<Produtos> produtos = new ArrayList<Produtos>();
}
