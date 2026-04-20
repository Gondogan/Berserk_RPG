package game;

/**
 * Enum que representa las distintas fases del juego.
 * Se usa para controlar el flujo global desde EstadoJuego.
 */
public enum FaseJuego {

    INICIO,           // El juego acaba de empezar

    SENDA1,           // Jugando senda 1
    SENDA2,           // Jugando senda 2
    SENDA3,           // Jugando senda 3

    TIENDA,           // El jugador está en la tienda

    SENDA_FINAL,      // Senda final desbloqueada

    VICTORIA,         // Ha ganado el juego
    DERROTA           // Ha perdido el juego
}