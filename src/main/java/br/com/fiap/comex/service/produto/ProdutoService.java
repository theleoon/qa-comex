package br.com.fiap.comex.service.produto;

import br.com.fiap.comex.model.produto.Produto;
import br.com.fiap.comex.repository.produto.ProdutoRepository;
import br.com.fiap.comex.service.categoria.CategoriaService;
import br.com.fiap.comex.service.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService implements ServiceInterface<Long, Produto> {

    @Autowired
    private ProdutoRepository repository;
    @Autowired
    private CategoriaService categoriaService;
    @Override
    public void cadastra(Produto produto) {
        if(produto==null) return;
        this.repository.save(produto);
    }

    @Override
    public Optional<Produto> buscaPorId(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public void atualiza(Produto produto) {
        if(produto==null) return;
        this.repository.save(produto);
    }

    @Override
    public Page<Produto> listaTodos(Pageable paginacao) {
        return this.repository.findAll(paginacao);
    }

    public List<Produto> getIndisponiveis(){
        return this.repository.findIndisponiveis();
    }

    public List<Produto> getMaisVendidos(){
        return this.repository.findMaisVendidos();
    }

    public Optional<Produto> getById(Long idProduto) {
        return this.repository.findById(idProduto);
    }
}
