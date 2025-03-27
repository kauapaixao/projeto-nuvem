"use client"

import Link from "next/link";
import { useEffect, useState } from "react";
import { deleteReceita, getReceitas } from "../utils/api";

export default function Home() {
  const [receitas, setReceitas] = useState(null);

  useEffect(() => {
    getReceitas().then((data) => setReceitas(data));
  }, []);

  async function handleDeleteReceita(id) {
    await deleteReceita(id);
    getReceitas().then(setReceitas);
  }

  return (
    <main>
      <h1>Receitas</h1>
      <Link href={"/"}>Log out</Link>
      <Link href={"/adicionar"}>adicionar receita</Link>
      <div className="todas">
      {receitas ? (
              receitas.map((a) => (
                <li key={a.id}>
                  <Receita id={a.id} title={a.title} ingredients={a.ingredients} onDelete={() => handleDeleteReceita(a.id)} />
                </li>
              ))
            ) : (
              <p>Carregando...</p>
            )}
      </div>
    </main>
  );
}

function Receita({id, title, ingredients, onDelete}) {
  return (
    <section className="receita">
        <div className="receitaTitle">
            <button onClick={onDelete}>X</button>
            <h1>{title}</h1>
        </div>
        <p>id: {id}</p>
        <p>ingredientes: {ingredients}</p>
        <Link href={"/receita?id=" + id}>ver mais</Link>
    </section>
  )
}
