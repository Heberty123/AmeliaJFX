package br.com.domain.Loja.Repositories;

import br.com.domain.Loja.Models.Cliente;
import br.com.domain.Loja.Models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    @Query("SELECT p FROM Produto p WHERE p.nome LIKE %:nome%")
    List<Produto> findProdutoByNomeContaining(@Param("nome") String nome);
}
