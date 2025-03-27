"use client"

import Link from "next/link";
import { useEffect, useState } from "react";
import { getReceitas } from "./utils/api";

export default function Home() {
  const [receitas, setReceitas] = useState(null);
  
  useEffect(() => {
    getReceitas().then((data) => setReceitas(data));
  }, []);


  return (
    <main>
      <h1>Receitas</h1>
      <Link href={"/login"}>Log in</Link>
      <div className="todas">
      {receitas ? (
              receitas.map((a) => (
                <li key={a.id}>
                  <Receita id={a.id} title={a.title} ingredients={a.ingredients} />
                </li>
              ))
            ) : (
              <p>Carregando...</p>
            )}
      </div>
    </main>
  );
}

function Receita({id, title, ingredients}) {
  return (
    <section className="receita">
      <h1>{title}</h1>
      <p>id: {id}</p>
      <p>ingredientes: {ingredients}</p>
      <Link href={"/receita?id=" + id}>ver mais</Link>
    </section>
  )
}
