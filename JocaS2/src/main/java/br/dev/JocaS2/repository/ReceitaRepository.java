package br.dev.JocaS2.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.dev.JocaS2.entity.Receita;

public interface ReceitaRepository extends CrudRepository<Receita, String> {
    public List<Receita> findByTitleContainingIgnoreCase(String title);
}
