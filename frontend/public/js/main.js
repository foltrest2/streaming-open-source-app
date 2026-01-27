// ===== CÓDIGO ORIGINAL - VERIFICACIÓN DE SALUD =====
const output = document.getElementById("output");
const btnHealthAll = document.getElementById("btnHealthAll");

if (btnHealthAll) {
  btnHealthAll.addEventListener("click", async () => {
    await checkHealth("All", "/api/health/all");
  });
}

async function checkHealth(name, url) {
  if (!output) return;
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

// ===== CONTENIDO DE EJEMPLO - PLATAFORMA DE STREAMING =====
const mockContent = [
    { id: 1, title: "Stranger Things", category: "drama", rating: 9.0, emoji: "🌀", year: 2016 },
    { id: 2, title: "The Crown", category: "drama", rating: 8.8, emoji: "👑", year: 2016 },
    { id: 3, title: "Breaking Bad", category: "drama", rating: 9.5, emoji: "⚗️", year: 2008 },
    { id: 4, title: "Inception", category: "scifi", rating: 8.8, emoji: "🎭", year: 2010 },
    { id: 5, title: "The Matrix", category: "scifi", rating: 8.7, emoji: "💊", year: 1999 },
    { id: 6, title: "Interstellar", category: "scifi", rating: 8.6, emoji: "🚀", year: 2014 },
    { id: 7, title: "The Office", category: "comedy", rating: 9.0, emoji: "🏢", year: 2005 },
    { id: 8, title: "Brooklyn Nine-Nine", category: "comedy", rating: 8.4, emoji: "🚔", year: 2013 },
    { id: 9, title: "The Conjuring", category: "horror", rating: 7.5, emoji: "👻", year: 2013 },
    { id: 10, title: "Hereditary", category: "horror", rating: 7.6, emoji: "😱", year: 2018 },
    { id: 11, title: "Mad Max", category: "action", rating: 8.1, emoji: "🚗", year: 2015 },
    { id: 12, title: "John Wick", category: "action", rating: 7.4, emoji: "🔫", year: 2014 },
];

// ===== ELEMENTOS DEL DOM - STREAMING =====
const searchInput = document.getElementById("searchInput");
const contentGrid = document.getElementById("content-grid");
const categoryBtns = document.querySelectorAll(".category-btn");
const modal = document.getElementById("modalDetail");
const closeBtn = document.querySelector(".close");
const sectionTitle = document.getElementById("section-title");
const heroSearch = document.querySelector(".hero-search");

let currentCategory = "all";
let filteredContent = [...mockContent];

// ===== INICIALIZACIÓN =====
document.addEventListener("DOMContentLoaded", () => {
    if (contentGrid) {
        loadContent(mockContent);
        setupEventListeners();
    }
});

// ===== EVENT LISTENERS - STREAMING =====
function setupEventListeners() {
    // Búsqueda
    if (searchInput) {
        searchInput.addEventListener("input", (e) => {
            filterContent(e.target.value);
        });
    }

    if (heroSearch) {
        heroSearch.addEventListener("keypress", (e) => {
            if (e.key === "Enter") {
                filterContent(e.target.value);
            }
        });
    }

    // Categorías
    categoryBtns.forEach(btn => {
        btn.addEventListener("click", (e) => {
            categoryBtns.forEach(b => b.classList.remove("active"));
            e.target.classList.add("active");
            currentCategory = e.target.getAttribute("data-category");
            filterByCategory(currentCategory);
        });
    });

    // Modal
    if (closeBtn) {
        closeBtn.addEventListener("click", () => {
            if (modal) modal.style.display = "none";
        });
    }

    window.addEventListener("click", (e) => {
        if (e.target == modal && modal) {
            modal.style.display = "none";
        }
    });
}

// ===== FUNCIONES DE CARGA Y FILTRADO =====
function loadContent(content) {
    if (!contentGrid) return;
    contentGrid.innerHTML = "";
    
    if (content.length === 0) {
        contentGrid.innerHTML = "<p style='grid-column: 1/-1; text-align: center; padding: 40px;'>No se encontraron resultados</p>";
        return;
    }

    content.forEach(item => {
        const card = createContentCard(item);
        contentGrid.appendChild(card);
    });
}

function createContentCard(item) {
    const card = document.createElement("div");
    card.className = "content-card";
    card.innerHTML = `
        <div class="content-image">
            ${item.emoji}
            <button class="play-btn">▶</button>
        </div>
        <div class="content-info">
            <div class="content-title">${item.title}</div>
            <div class="content-meta">
                <span class="content-rating">⭐ ${item.rating}</span>
                <span>${item.year}</span>
            </div>
        </div>
    `;

    card.addEventListener("click", () => {
        showModal(item);
    });

    return card;
}

function showModal(item) {
    const modalBody = document.getElementById("modalBody");
    if (!modalBody) return;
    modalBody.innerHTML = `
        <h2>${item.title}</h2>
        <p><strong>Categoría:</strong> ${capitalize(item.category)}</p>
        <p><strong>Año:</strong> ${item.year}</p>
        <p><strong>Calificación:</strong> ⭐ ${item.rating}/10</p>
        <p><strong>Descripción:</strong> Disfruta de este contenido increíble. Haz clic en reproducir para comenzar a ver.</p>
        <button class="btn-primary" onclick="playContent('${item.title}')">Reproducir Ahora</button>
        <button class="btn-primary" style="background-color: transparent; color: var(--primary-color); border: 2px solid var(--primary-color); margin-left: 10px;">+ Agregar a Favoritos</button>
    `;
    if (modal) modal.style.display = "block";
}

function playContent(title) {
    alert(`🎬 Reproduciendo: ${title}\n\nEsta es una demostración. La reproducción real se conectaría con el servicio de streaming.`);
    if (modal) modal.style.display = "none";
}

function filterContent(searchTerm) {
    const term = searchTerm.toLowerCase();
    filteredContent = mockContent.filter(item => 
        item.title.toLowerCase().includes(term) && 
        (currentCategory === "all" || item.category === currentCategory)
    );

    if (sectionTitle) {
        sectionTitle.textContent = term ? `Resultados para "${searchTerm}"` : "Contenido Popular";
    }
    loadContent(filteredContent);
}

function filterByCategory(category) {
    if (category === "all") {
        filteredContent = [...mockContent];
        if (sectionTitle) sectionTitle.textContent = "Contenido Popular";
    } else {
        filteredContent = mockContent.filter(item => item.category === category);
        if (sectionTitle) sectionTitle.textContent = capitalize(category);
    }
    loadContent(filteredContent);
}

function capitalize(str) {
    return str.charAt(0).toUpperCase() + str.slice(1);
}
