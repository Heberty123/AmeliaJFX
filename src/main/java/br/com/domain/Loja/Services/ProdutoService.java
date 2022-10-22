package br.com.domain.Loja.Services;

import br.com.domain.Loja.Models.Marca;
import br.com.domain.Loja.Models.Produto;
import br.com.domain.Loja.Repositories.MarcaRepository;
import br.com.domain.Loja.Repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private MarcaRepository marcaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Marca> getAllMarcas(){
        return marcaRepository.findAll();
    }

    public void save(Produto produto) {
        produtoRepository.save(produto);
    }
}
