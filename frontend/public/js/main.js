const output = document.getElementById("output");
const btnHealthAll = document.getElementById("btnHealthAll");

btnHealthAll.addEventListener("click", async () => {
  await checkHealth("All", "/api/health/all");
});

async function checkHealth(name, url) {
  output.textContent = `Verificando ${name}...`;

  try {
    if (!window.auth?.token) {
      throw new Error("Usuario no autenticado");
    }

    const res = await fetch(url, {
      headers: {
        Authorization: `Bearer ${window.auth?.token}`,
      },
    });

    if (!res.ok) {
      throw new Error(`HTTP ${res.status}`);
    }

    const data = await res.json();
    output.textContent = `${name}: ${JSON.stringify(data, null, 2)}`;

  } catch (err) {
    output.textContent =
      `${name}: Error al conectar o no autorizado → ${err.message}`;
  }
}
