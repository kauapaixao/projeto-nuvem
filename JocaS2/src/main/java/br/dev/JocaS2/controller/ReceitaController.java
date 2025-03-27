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

import br.dev.JocaS2.entity.Receita;
import br.dev.JocaS2.repository.ReceitaRepository;

@RestController
@RequestMapping("/api/receitas")
public class ReceitaController {
    @Autowired
    ReceitaRepository repository;

    @PostMapping()
    public ResponseEntity<Receita> create(@RequestBody Receita entity) {
        if(entity.getId() == null || "".equals(entity.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "precisa informar o ID");
        }

        Optional<Receita> existe = repository.findById(entity.getId());
        if(existe.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "já existe uma Receita com este ID");
        }

        Receita newEntity = repository.save(entity);
        return new ResponseEntity<Receita>(newEntity, HttpStatus.CREATED);
    }

    // rota1: /api/receitas?title="voltar"   <--- retorna filtrando pelo titulo
    // rota2: /api/receitas                  <--- retorna todas as receitas
    @GetMapping()
    public Iterable<Receita> readByTitle(@RequestParam(required = false) String title) {
        if(title == null) {
            return repository.findAll();
        }
        return repository.findByTitleContainingIgnoreCase(title);
    }

    // rota: /api/receitas/123
    @GetMapping("/{id}")
    public ResponseEntity<Receita> readById(@PathVariable String id) {
        Optional<Receita> resultado = repository.findById(id);

        if(resultado.isPresent()) {
            return ResponseEntity.ok(resultado.get());
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID " + id + " não encontrado");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Receita> update(@RequestBody Receita newReceita, @PathVariable String id) {
        //1. Verifica se a receita existe pelo ID
        Optional<Receita> entity = repository.findById(id);

        //2. Se nao existe devolve erro 404
        if(!entity.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        //3. Se existe altera objeto e salva no banco
        Receita receita = entity.get();
        receita.setTitle(newReceita.getTitle());
        receita.setAuthor(newReceita.getAuthor());
        receita.setIngredients(newReceita.getIngredients());
        receita.setPrepare(newReceita.getPrepare());

        //4. Devolve nova receita
        repository.save(receita);
        return ResponseEntity.ok(receita);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Receita> deleteReceita(@PathVariable String id) {
        //1. Verifica se a receita existe pelo ID
        Optional<Receita> entity = repository.findById(id);

        //2. Se nao existe devolve erro 404
        if(!entity.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        //3. Se existe apaga o objeto do banco
        Receita receita = entity.get();
        repository.delete(receita);
        return ResponseEntity.ok(receita);
    }
}
