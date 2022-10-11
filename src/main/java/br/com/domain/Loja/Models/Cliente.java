package br.com.domain.Loja.Models;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@ToString
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(unique = true)
    private String cpf;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private List<Endereco> enderecos = new ArrayList<Endereco>();

    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos = new ArrayList<Pedido>();

    public Cliente(){}


}