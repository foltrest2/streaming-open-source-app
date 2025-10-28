-- ==============================
-- DATABASE INIT: recommendation
-- ==============================

-- Tabla de historial de visualización
CREATE TABLE watch_history (
    id SERIAL PRIMARY KEY,
    user_id UUID NOT NULL,
    content_id UUID NOT NULL,
    watched_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    progress NUMERIC(5,2) DEFAULT 0.0, -- % visto, ej: 75.5
    finished BOOLEAN DEFAULT FALSE
);

-- Tabla de likes
CREATE TABLE user_likes (
    id SERIAL PRIMARY KEY,
    user_id UUID NOT NULL,
    content_id UUID NOT NULL,
    liked BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE(user_id, content_id) -- evita duplicados
);

-- Tabla de perfiles de usuario
CREATE TABLE user_profiles (
    user_id UUID PRIMARY KEY,
    favorite_genres TEXT[],          -- lista de géneros favoritos
    favorite_actors TEXT[],          -- lista de actores/directores
    avg_watch_time INTERVAL,         -- promedio de tiempo de sesión
    last_active TIMESTAMP,           -- última vez que entró
    preferences JSONB DEFAULT '{}'   -- espacio flexible para gustos adicionales
);

-- Índices para mejorar consultas
CREATE INDEX idx_watch_history_user ON watch_history(user_id);
CREATE INDEX idx_watch_history_content ON watch_history(content_id);
CREATE INDEX idx_user_likes_user ON user_likes(user_id);
CREATE INDEX idx_user_likes_content ON user_likes(content_id);
