package br.dev.JocaS2.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.dev.JocaS2.entity.Usuario;
import br.dev.JocaS2.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    UsuarioRepository repository;

    @PostMapping()
    public ResponseEntity<Usuario> create(@RequestBody Usuario entity) {
        Optional<Usuario> existe = repository.findById(entity.getEmail());
        if(existe.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "já existe um Usuario com este email");
        }

        Usuario newEntity = repository.save(entity);
        return new ResponseEntity<Usuario>(newEntity, HttpStatus.CREATED);
    }

    @GetMapping()
    public Iterable<Usuario> readByNome(@RequestParam(required = false) String nome) {
        if(nome == null) {
            return repository.findAll();
        }
        return repository.findByNomeContainingIgnoreCase(nome);
    }

    @PutMapping("/{email}")
    public ResponseEntity<Usuario> update(@RequestBody Usuario newUsuario, @PathVariable String email) {
        //1. Verifica se o usuario existe pelo Email
        Optional<Usuario> entity = repository.findById(email);

        //2. Se nao existe devolve erro 404
        if(!entity.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        //3. Se existe altera objeto e salva no banco
        Usuario usuario = entity.get();
        usuario.setNome(newUsuario.getNome());
        usuario.setEmail(newUsuario.getEmail());
        usuario.setSenha(newUsuario.getSenha());
        //4. Devolve novo usuario
        repository.save(usuario);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Usuario> deleteReceita(@PathVariable String email) {
        //1. Verifica se o Usuario existe pelo Email
        Optional<Usuario> entity = repository.findById(email);

        //2. Se nao existe devolve erro 404
        if(!entity.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        //3. Se existe apaga o objeto do banco
        Usuario usuario = entity.get();
        repository.delete(usuario);
        return ResponseEntity.ok(usuario);
    }
}
