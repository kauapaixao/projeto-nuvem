package br.dev.JocaS2.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.dev.JocaS2.entity.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {
    public List<Usuario> findByNomeContainingIgnoreCase(String nome);
}
