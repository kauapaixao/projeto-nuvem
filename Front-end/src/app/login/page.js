"use client"

import Link from "next/link";
import styles from "./page.module.css"
import { useState } from "react";
import { addUsuario } from "../utils/api";

export default function Home(){

  const [nome, setNome] = useState("");
  const [email, setEmail] = useState("");
  const [senha, setSenha] = useState("");

  function changeNome(event) {
    setNome(event.target.value);
  }
  function changeEmail(event) {
    setEmail(event.target.value);
  }
  function changeSenha(event) {
    setSenha(event.target.value);
  }

  function handleAddUsuario(event) {
    event.preventDefault();
    let newUsuario = { nome, email, senha };
    addUsuario(newUsuario)
  }

  return (
    <main className={styles.add}>
      <div className={styles.loginDiv}>
        <section className={styles.login}>
            <input type="text" value={nome} onChange={changeNome} placeholder="Nome" />
            <input type="text" value={email} onChange={changeEmail} placeholder="Email" />
            <input type="text" value={senha} onChange={changeSenha} placeholder="Senha" />
            <button onClick={handleAddUsuario}>Adicionar</button>
            <Link href={"/cadastrado"}>cadastrar</Link>
        </section>
      </div>
    </main>
  );

    /*return (
        <div className={styles.loginDiv}>
            <section className={styles.login}>
                <input placeholder="Nome"></input>
                <input placeholder="Email"></input>
                <input placeholder="Senha"></input>
                <input placeholder="Confirmar senha"></input>
                <Link href={"/cadastrado"}>confirmar</Link>
            </section>
        </div>
    )*/
}