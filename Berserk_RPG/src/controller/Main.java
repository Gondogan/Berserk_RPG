package controller;

import characters.Guts;
import combat.Combate;
import enemies.Enemigo;

public class Main {

	public static void main(String[] args) {
		
		// da error en habilidad pasiva, esto lo he hecho para comprobar si funcionaba el código de combate (pedro)
		
		 // ================= JUGADOR =================
        Guts jugador = new Guts();

        // ================= ENEMIGO =================
        Enemigo enemigo = new Enemigo("Orco", 60, 60, 12, 5, 8, 10) {};

        // ================= COMBATE =================
        Combate combate = new Combate(jugador, new Enemigo[]{enemigo});
        combate.iniciarCombate();

	}

}
