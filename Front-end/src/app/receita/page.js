"use client"
import Link from "next/link";
import { useSearchParams } from "next/navigation"
import { useEffect, useState } from "react";



export default function ReceitaPagina() {
    const [receita, setReceita] = useState(null);
    const params = useSearchParams();
    const id = params.get("id");

    async function buscarReceita(id) {
        const resp = await fetch("http://localhost:8080/api/receitas/" + id);
        const receitaResp = await resp.json();
        setReceita(receitaResp);
    }

    useEffect(() => {
        buscarReceita(id);
    }, [])

    if (receita === null) {
        return <main>carregando...</main>
    }

    return (
        <main>
            <section className="receita2">
                <h1>{receita.title}</h1>
                <p>Autor: {receita.author}</p>
                <p>Ingredientes: {receita.ingredients}</p>
                <p>Preparo: {receita.prepare}</p>
                <p>ID: {receita.id}</p>
            </section>
            <Link href="/">Voltar</Link>
        </main>
    )
}