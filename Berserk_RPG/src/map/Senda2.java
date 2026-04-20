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


/*
    @Override
    public String getIntroduccion() {
        return "La niebla es tan espesa que parece sólida. Te has adentrado en un lugar donde " +
               "el mundo de los hombres y el reino de los espíritus se superponen violentamente.";
    }

    @Override
    public String getDialogoIntermedio() {
        return "Una risa estridente sacude las ramas. Una figura astada bloquea el sendero: " +
               "'Pobre alma perdida... tus miedos huelen tan dulces en este bosque'.";
    }

    @Override
    public String getDialogoFinal() {
        return "El cauce del río se alza, desafiando la gravedad, formando la silueta de una bestia líquida. " +
               "Un espíritu ancestral ha decidido que tu viaje termina en el fondo de estas aguas.";
    }
    */
}





