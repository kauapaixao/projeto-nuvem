// -------------- CREATE --------------
export async function addReceita(receita) {
    try {
      let response = await fetch("http://3.227.13.29:8080/api/receitas", {
        method: 'POST',
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(receita)
      });
      return response.status;
    } catch (error) {
      console.error("Erro na API:", error.message);
    }
  }

  export async function addUsuario(usuario) {
    try {
      let response = await fetch("http://3.227.13.29:8080/api/usuario", {
        method: 'POST',
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(usuario)
      });
      return response.status;
    } catch (error) {
      console.error("Erro na API:", error.message);
    }
  }
  
  // -------------- READ ----------------
  export async function getReceitas() {
    try {
      let response = await fetch("http://3.227.13.29:8080/api/receitas");
      let data = await response.json();
      return data;
    } catch (error) {
      console.error("Erro na API:", error.message);
    }
  }
  
  export async function getReceitasById(id) {
    try {
      let response = await fetch(`http://3.227.13.29:8080/api/receitas?id=${id}`);
      let data = await response.json();
      return data;
    } catch (error) {
      console.error("Erro na API:", error.message);
    }
  }

  export async function getUsuario() {
    try {
      let response = await fetch("http://3.227.13.29:8080/api/usuario");
      let data = await response.json();
      return data;
    } catch (error) {
      console.error("Erro na API:", error.message);
    }
  }
  
  // ------------------- UPDATE -------------------
  export async function updateReceita(receita) {
    try {
      let response = await fetch("http://3.227.13.29:8080/api/receitas" + receita.id, {
        method: 'PUT',
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(atividade)
      });
      return response.status;
    } catch (error) {
      console.error("Erro na API:", error.message);
    }
  }
  
  // --------------------- DELETE ----------------------
  export async function deleteReceita(id) {
    try {
      let response = await fetch("http://3.227.13.29:8080/api/receitas" + id, {
        method: 'DELETE',
      });
      return response.status;
    } catch (error) {
      console.error("Erro na API:", error.message);
    }
  }
  