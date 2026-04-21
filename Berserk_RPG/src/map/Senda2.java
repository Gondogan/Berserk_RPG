package map;

import enemies.Enemigo;
import enemies.EnemigoBasico;
import game.EstadoJuego;
import game.Narrador;
import store.GestorRecompensa;

import java.util.ArrayList;
import java.util.Scanner;

import characters.Personajes;

public class Senda2 {

	private ArrayList<EnemigoBasico> enemigos;

    public Senda2() {
        enemigos = new ArrayList<>();

        enemigos.add(new EnemigoBasico("Troll", 150, 28, 12, 10, "Mazazo"));
        enemigos.add(new EnemigoBasico("Sátiro Corrupto", 180, 32, 15, 10, "Cornada"));
        enemigos.add(new EnemigoBasico("Kelpie de las Sombras", 300, 45, 20, 10, "Carga oscura"));
    }
    
    private Personajes jugador;
    private EstadoJuego estadoJuego;
    private GestorRecompensa gestorRecompensa;
    private Scanner scanner;

    private static final int TOTAL_COMBATES = 3;

    public Senda2(Personajes jugador, EstadoJuego estadoJuego) {
        this.jugador = jugador;
        this.estadoJuego = estadoJuego;
        this.gestorRecompensa = new GestorRecompensa();
        this.scanner = new Scanner(System.in);
    }

    public ArrayList<EnemigoBasico> getEnemigos() {
        return enemigos;
    }

	public void iniciarSenda() {
		// TODO Auto-generated method stub
		
	}

	//DIALOGOS COMENTADOS
	
/*
    public void iniciarSenda() throws InterruptedException {
    narrador.limpiarConsola();
    narrador.decirDialogo("La niebla aquí no es agua; es el aliento frío de los muertos que se niegan a abandonar este mundo.");
    narrador.decirDialogo("Te has adentrado en el Intersticio, donde las leyes de los hombres se doblan ante el capricho de los espíritus.");
    narrador.decirDialogo("Los árboles parecen retorcerse de dolor, extendiendo ramas como dedos esqueléticos que buscan tocar tu Marca.");
    narrador.decirDialogo("Sientes miles de ojos observándote desde la penumbra. En este bosque, el tiempo no avanza, solo espera a que tu voluntad se quiebre.");
    */
}





