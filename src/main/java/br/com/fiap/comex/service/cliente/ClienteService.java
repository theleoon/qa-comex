package br.com.fiap.comex.service.cliente;

import br.com.fiap.comex.model.cliente.Cliente;
import br.com.fiap.comex.repository.cliente.ClienteRepository;
import br.com.fiap.comex.service.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService implements ServiceInterface<Long, Cliente> {

    @Autowired
    private ClienteRepository repository;

    @Override
    public void cadastra(Cliente cliente) {
        if(cliente==null) return;
        this.repository.save(cliente);
    }

    @Override
    public Optional<Cliente> buscaPorId(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public void atualiza(Cliente cliente) {
        if(cliente==null) return;
        this.repository.save(cliente);
    }

    @Override
    public Page<Cliente> listaTodos(Pageable paginacao) {
        return this.repository.findAll(paginacao);
    }

    public Optional<Cliente> getById(Long idCliente) {
        return this.repository.findById(idCliente);
    }

    public int getTotalDePedidosDoCliente(Long idCliente){return this.repository.findTotalDePedidos(idCliente);}
}
