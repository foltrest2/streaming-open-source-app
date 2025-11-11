const output = document.getElementById("output");

document.getElementById("btnHealthAll").addEventListener("click", async () => {
  await checkHealth("All", "/api/health/all");
});

async function checkHealth(name, url) {
  output.textContent = `Verificando ${name}...`;

  try {
    const res = await fetch(url);
    const data = await res.json();
    output.textContent = `${name}: ${JSON.stringify(data, null, 2)}`;
  } catch (err) {
    output.textContent = `${name}: Error al conectar → ${err}`;
  }
}
