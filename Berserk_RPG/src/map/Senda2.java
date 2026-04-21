package map;

import enemies.Enemigo;
import enemies.EnemigoBasico;
import game.EstadoJuego;
import game.Narrador;
import store.GestorRecompensa;

import java.util.ArrayList;
import java.util.Scanner;

import characters.Personajes;
import combat.Combate;
import combat.ResultadoCombate;

public class Senda2 {
    
    private Personajes jugador;
    private EstadoJuego estadoJuego;
    private GestorRecompensa gestorRecompensa;
    private Narrador narrador;
    private Scanner scanner;

    private static final int TOTAL_COMBATES = 3;

    public Senda2(Personajes jugador, EstadoJuego estadoJuego, Narrador narrador) {
        this.jugador = jugador;
        this.estadoJuego = estadoJuego;
        this.gestorRecompensa = new GestorRecompensa();
        this.scanner = new Scanner(System.in);
    }

	public void iniciarSenda() throws InterruptedException{

	    narrador.limpiarConsola();
	    narrador.presentarSenda("SENDA 2 — EL BOSQUE DE LOS ESPIRITUS");
	    narrador.decirDialogo("La niebla aquí no es agua; es el aliento frío de los muertos que se niegan a abandonar este mundo.");
	    narrador.decirDialogo("Te has adentrado en el Intersticio, donde las leyes de los hombres se doblan ante el capricho de los espíritus.");
	    narrador.decirDialogo("Los árboles parecen retorcerse de dolor, extendiendo ramas como dedos esqueléticos que buscan tocar tu Marca.");
	    narrador.decirDialogo("Sientes miles de ojos observándote desde la penumbra. En este bosque, el tiempo no avanza, solo espera a que tu voluntad se quiebre.");
		
		String nombreAnterior = null;
        int progreso = 0;
        int indiceEnemigo = 0;

        // Bucle principal de la senda
        while (indiceEnemigo < TOTAL_COMBATES && jugador.isVivo()) {

            Enemigo[] enemigos = generarEnemigo(indiceEnemigo);
            String nombreActual = enemigos[0].getNombre();
                   
        // Si el enemigo actual tiene el mismo nombre que el anterior, interpretamos que seguimos dentro de la misma horda y no anunciamos un combate nuevo.

             
            if (nombreAnterior == null || !nombreAnterior.equals(nombreActual)) {
                progreso++;
                System.out.println("\nCombate " + progreso + " de " + (TOTAL_COMBATES - 1));
            } else {
                System.out.println("\nContinúa la horda de " + nombreActual);
            }
            
            Combate combate = new Combate(jugador, enemigos);

            ResultadoCombate resultado = combate.iniciarCombate();

            // ================= RESULTADO =================

            if (resultado == ResultadoCombate.DERROTA) {

                System.out.println("\n💀 Has sido derrotado... 💀");
                estadoJuego.marcarDerrota();
                return;
            }

            if (resultado == ResultadoCombate.HUIDA) {

                System.out.println("\n🏃 Has abandonado la senda. 🏃");
                return;
            }

            // ================= VICTORIA =================

            System.out.println("\nHas ganado el combate.");

            // Recompensa
            gestorRecompensa.generarRecompensa(jugador, progreso + 1);

            // Aumento de Energía
            
            jugador.recuperarEnergia(30);
            System.out.println("Tu energía ha sido restaurada.\n");
            
            System.out.println(jugador);
            
            nombreAnterior = nombreActual;
            
            indiceEnemigo++;
            // ================= DECISIÓN =================

            if (progreso < (TOTAL_COMBATES - 1)) {

                if (!menuPostCombate()) {

                    System.out.println("\n❌ Has abandonado la senda. Progreso reiniciado. ❌");
                    return;
                }
            }
        }

        // ================= FINAL SENDA =================

        if (jugador.isVivo()) {

            System.out.println("\n🏆 ¡Has completado la SENDA 1!");

            estadoJuego.registrarVictoria();
        }
    }

    // ================= MENÚ ENTRE COMBATES =================

    /**
     * true → continuar senda
     * false → ir a tienda (pierde progreso)
     */
    private boolean menuPostCombate() {

        System.out.println("\n¿Qué quieres hacer?");
        System.out.println("1. Continuar senda");
        System.out.println("2. Huir de la senda (pierdes progreso)");

        int opcion = scanner.nextInt();

        switch (opcion) {

            case 1:
                return true;

            case 2:
                return false;

            default:
                System.out.println("Opción inválida.");
                return menuPostCombate();
        }
    }

    // ================= GENERACIÓN ENEMIGOS =================

    /**
     * Genera enemigos según el progreso
     * (escala dificultad)
     */
    private Enemigo[] generarEnemigo(int nivel) {

        switch (nivel) {

            case 0:
                return new Enemigo[] { //String nombre, int vidaMax, int danioBase, int defensa, int velocidad
                    new Enemigo("Troll", 155, 31, 21, 24) {}
                };

            case 1: 
                return new Enemigo[] {
                    new Enemigo("Sétiro Corrupto", 110, 29, 15, 69) {}
                };

            case 2:
            	return new Enemigo[] {
                        new Enemigo("Kelpie de las Sombras", 300, 45, 20, 49) {}
                };
            	
            default:
                return new Enemigo[] {};
        }
    }
    
}





