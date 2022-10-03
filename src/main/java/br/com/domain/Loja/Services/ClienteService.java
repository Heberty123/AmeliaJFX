package br.com.domain.Loja.Services;

import br.com.domain.Loja.Models.Cliente;
import br.com.domain.Loja.Models.Endereco;
import br.com.domain.Loja.Repositories.ClienteRepository;
import br.com.domain.Loja.Repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Cliente Register(Cliente cliente){
        Cliente clienteRegistered = clienteRepository.save(cliente);
        return clienteRegistered;
    }

    public void addEnderecoParaCliente(Long idCliente, Endereco endereco) {

        Cliente cliente = clienteRepository.getReferenceById(idCliente);
        endereco.setCliente(cliente);
        enderecoRepository.save(endereco);


    }

    public List<Endereco> buscarEnderecosPorCliente(Long id) {
        List<Endereco> enderecos = enderecoRepository.findAllByClienteId(id);
        return enderecos;
    }



}
