package br.com.fiap.comex.service.categoria;

import br.com.fiap.comex.model.categoria.Categoria;
import br.com.fiap.comex.repository.categoria.CategoriaRepository;
import br.com.fiap.comex.service.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService implements ServiceInterface<Long, Categoria> {

    @Autowired
    private CategoriaRepository repository;

    @Override
    public void cadastra(Categoria categoria) {
        if(categoria==null) return;
        this.repository.save(categoria);
    }

    @Override
    public Optional<Categoria> buscaPorId(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public void atualiza(Categoria categoria) {
        if(categoria==null) return;
        this.repository.save(categoria);
    }

    @Override
    public Page<Categoria> listaTodos(Pageable paginacao) {
       return this.repository.findAll(paginacao);
    }

    public Optional<Categoria> getById(Long id) {
        return repository.findById(id);
    }
}
