package br.com.domain.Loja.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Marca {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;


    @OneToMany(mappedBy = "marca", fetch = FetchType.LAZY)
    private List<Produto> produtos = new ArrayList<Produto>();

}
