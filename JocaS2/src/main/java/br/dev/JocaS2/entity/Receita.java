package br.dev.JocaS2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Receita {
    private String title;
    private String author;
    private String ingredients;
    private String prepare;
    @Id
    private String id;
}
