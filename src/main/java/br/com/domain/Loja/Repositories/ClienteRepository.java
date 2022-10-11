package br.com.domain.Loja.Repositories;

import br.com.domain.Loja.Models.Cliente;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsByCpf(String newValue);

    @Query("SELECT c FROM Cliente c WHERE c.nome LIKE %:nome%")
    List<Cliente> findClienteByNomeContaining(@Param("nome") String nome);


}
