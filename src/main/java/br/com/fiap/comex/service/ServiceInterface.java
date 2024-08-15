package br.com.fiap.comex.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface ServiceInterface <K,T>{
    void cadastra(T t);
    Optional<T> buscaPorId(K k);
    void atualiza(T t);
    Page<T> listaTodos(Pageable paginacao);
}
