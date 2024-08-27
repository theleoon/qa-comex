package br.com.fiap.comex.service.pedido;

import br.com.fiap.comex.model.pedido.Pedido;
import br.com.fiap.comex.model.pedido.desconto.TipoDescontoEnum;
import br.com.fiap.comex.repository.pedido.PedidoRepository;
import br.com.fiap.comex.service.cliente.ClienteService;
import br.com.fiap.comex.service.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService implements ServiceInterface<Long, Pedido> {

    @Autowired
    private PedidoRepository repository;
    @Autowired
    private ClienteService clienteService;

    @Override
    public void cadastra(Pedido pedido) {
        if(pedido==null) return;
        this.repository.save(pedido);
    }

    @Override
    public Optional<Pedido> buscaPorId(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public void atualiza(Pedido pedido) {
        if(pedido==null) return;
        this.repository.save(pedido);
    }

    @Override
    public Page<Pedido> listaTodos(Pageable paginacao) {
        return this.repository.findAll(paginacao);
    }

    public void aplicarDescontos(Pedido pedido){
        int qntPedidosDoCliente = clienteService.getTotalDePedidosDoCliente(pedido.getCliente().getId());

        if(qntPedidosDoCliente > 5){
            pedido.aplicaDesconto(TipoDescontoEnum.FIDELIDADE);
        }else{
            pedido.aplicaDesconto(TipoDescontoEnum.NENHUM);
        }
    }

    public Optional<Pedido> getById(Long id) {
        return this.repository.findById(id);
    }
}
