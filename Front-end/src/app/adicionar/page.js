"use client"

import Link from "next/link";
import { useState } from "react";
import { addReceita } from "../utils/api";
import styles from "./page.module.css";

export default function Home() {

  const [author, setAuthor] = useState("");
  const [title, setTitle] = useState("");
  const [ingredients, setIngredients] = useState("");
  const [prepare, setPrepare] = useState("");
  const [id, setId] = useState("");

  function changeAuthor(event) {
    setAuthor(event.target.value);
  }
  function changeTitle(event) {
    setTitle(event.target.value);
  }
  function changeIngredients(event) {
    setIngredients(event.target.value);
  }
  function changePrepare(event) {
    setPrepare(event.target.value);
  }
  function changeId(event) {
    setId(event.target.value);
  }

  function handleAddReceita(event) {
    event.preventDefault();
    let newReceita = { author, title, ingredients, prepare, id };
    addReceita(newReceita)
  }

  return (
    <main className={styles.add}>
      <Link href={"/cadastrado"}>Voltar</Link>
      <p>
        <input type="text" value={title} onChange={changeTitle} placeholder="Adicionar título" />
        <input type="text" value={author} onChange={changeAuthor} placeholder="Adicionar autor" />
        <input type="text" value={ingredients} onChange={changeIngredients} placeholder="Adicionar Ingredientes" />
        <input type="text" value={prepare} onChange={changePrepare} placeholder="Adicionar modo de preparo" />
        <input type="text" value={id} onChange={changeId} placeholder="Adicionar id" />
        <button onClick={handleAddReceita}>Adicionar</button>
      </p>
    </main>
  );
}