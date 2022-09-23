package br.com.domain.Loja.Models;

import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity @Data
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String nome;
    @Column(unique = true)
    private String cpf;

    @OneToMany(mappedBy = "cliente")
    private List<Endereco> enderecos = new ArrayList<Endereco>();

    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos = new ArrayList<Pedido>();

    public Cliente(){}

}