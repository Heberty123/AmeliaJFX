package br.com.domain.Loja.Services;

import br.com.domain.Loja.Models.Cliente;
import br.com.domain.Loja.Repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente Register(Cliente cliente){
        Cliente clienteRegistered = clienteRepository.save(cliente);
        return clienteRegistered;
    }
}
