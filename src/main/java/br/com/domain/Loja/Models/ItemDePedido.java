package br.com.domain.Loja.Models;

import javax.persistence.*;
import java.math.BigDecimal;
@Entity
public class ItemDePedido {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantidade;

    private BigDecimal preco;

    @ManyToOne
    private Pedido pedido;

    @OneToOne
    private Produtos produtos;
}