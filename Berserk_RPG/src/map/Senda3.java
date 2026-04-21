package map;

import enemies.Enemigo;
import enemies.EnemigoBasico;
import game.EstadoJuego;
import game.Narrador;
import store.GestorRecompensa;

import java.util.ArrayList;
import java.util.Scanner;

import characters.Personajes;

public class Senda3 {

	private ArrayList<EnemigoBasico> enemigos;

    public Senda3() {
        enemigos = new ArrayList<>();

        enemigos.add(new EnemigoBasico("Poseso", 220, 40, 18, 10, "Zarpazo"));
        enemigos.add(new EnemigoBasico("Apóstol Menor", 260, 48, 22, 10, "Golpe demoníaco"));
        enemigos.add(new EnemigoBasico("Gran Inquisidor Mozgus", 450, 60, 33, 10, "Muro de piedra"));
    }

    public ArrayList<EnemigoBasico> getEnemigos() {
        return enemigos;
    }
    
    private Personajes jugador;
    private EstadoJuego estadoJuego;
    private GestorRecompensa gestorRecompensa;
    private Scanner scanner;

    private static final int TOTAL_COMBATES = 3;

    public Senda3(Personajes jugador, EstadoJuego estadoJuego, Narrador narrador) {
        this.jugador = jugador;
        this.estadoJuego = estadoJuego;
        this.gestorRecompensa = new GestorRecompensa();
        this.scanner = new Scanner(System.in);
    }
    
    //DIALOGOS COMENTADOS
    
    /*
    public void iniciarSenda() throws InterruptedException {
    narrador.limpiarConsola();
    narrador.presentarSenda("SENDA 3 — EL ALTAR DE LA INVOCACIÓN");
    narrador.decirDialogo("El aire pesa como el plomo. El olor a azufre y excremento humano se te mete en los pulmones, recordándote que el infierno está subiendo a la superficie.");
    narrador.decirDialogo("El suelo se siente blando, casi como si estuvieras caminando sobre una herida abierta que se niega a cerrar.");
    narrador.decirDialogo("Escuchas el eco de látigos y el rezo fanático de aquellos que creen que el dolor es el único camino hacia la pureza.");
    narrador.decirDialogo("Tu Marca comienza a sangrar, palpitando al ritmo de un corazón oscuro que late en lo profundo de este matadero sagrado.");
    */

	public void iniciarSenda() {
		// TODO Auto-generated method stub
		
	}
}
    
