package controller;

import characters.Guts;
import combat.Combate;
import enemies.Enemigo;
import game.EstadoJuego;
import map.Senda1;

public class Main {


    public static void main(String[] args) {

        // 1. Crear personaje (usa el que ya tengas, ej: Guts)
        Guts jugador = new Guts(); // o tu clase concreta

        // 2. Crear estado del juego
        EstadoJuego estadoJuego = new EstadoJuego();

        // 3. Crear senda
        Senda1 senda1 = new Senda1(jugador, estadoJuego);

        // 4. Ejecutar senda
        senda1.iniciarSenda();
    }

}
