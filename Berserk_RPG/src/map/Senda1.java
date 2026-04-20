package map;

import enemies.Enemigo;
import enemies.EnemigoBasico;
import java.util.Scanner;
import characters.Personajes;
import combat.Combate;
import combat.ResultadoCombate;
import game.EstadoJuego;
import store.GestorRecompensa;
import store.Tienda;
import java.util.ArrayList;

public class Senda1 {

	private ArrayList<EnemigoBasico> enemigos;
    
	public Senda1() {
        enemigos = new ArrayList<>();
  
    		enemigos.add(new EnemigoBasico("Soldado Kushan", 80, 15, 5, 10, "Cuchillada"));
            enemigos.add(new EnemigoBasico("Explorador Pishacha", 110, 22, 8, 10, "Golpe rápido"));
            enemigos.add(new EnemigoBasico("Horda de Bakiraka", 220, 35, 12, 10, "Ataque múltiple"));
    };

    public ArrayList<EnemigoBasico> getEnemigos() {
        return enemigos;
    }

/**
 * Senda1:
 * - 3 combates consecutivos
 * - recompensa tras cada combate
 * - curación completa tras cada victoria
 * - opción de ir a tienda (reinicia progreso)
 */


    private Personajes jugador;
    private EstadoJuego estadoJuego;
    private GestorRecompensa gestorRecompensa;
    private Scanner scanner;

    private static final int TOTAL_COMBATES = 4;

    public Senda1(Personajes jugador, EstadoJuego estadoJuego) {
        this.jugador = jugador;
        this.estadoJuego = estadoJuego;
        this.gestorRecompensa = new GestorRecompensa();
        this.scanner = new Scanner(System.in);
    }
    
    
    
    //DIALOGOS
    /*
    @Override
    public String getIntroduccion() {
        return "El cielo se tiñe de ceniza sobre las llanuras de Midland. El aire huele a hierro y humo; " +
               "la invasión de los Kushan ha convertido este reino en un matadero de hombres.";
    }

    @Override
    public String getDialogoIntermedio() {
        return "De entre las tiendas de campaña en llamas surge una figura deforme. '¡Más carne para el General!', " +
               "sisea el Pishacha mientras su mandíbula se desencaja de forma antinatural.";
    }

    @Override
    public String getDialogoFinal() {
        return "Te han rodeado. Figuras encapuchadas emergen de las sombras proyectadas por los incendios. " +
               "'No pasarás de aquí, intruso. El Imperio reclama tu cabeza como trofeo'.";
    }
	*/

    // ================= INICIO SENDA =================

    public void iniciarSenda() {
    	
    	String nombreAnterior = null;
        System.out.println("\n🌿 Entras en la SENDA 1...");
        
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
                return new Enemigo[] {
                    new Enemigo("Goblin", 40, 40, 10, 3, 12, 8) {}
                };

            case 1:
                return new Enemigo[] {
                    new Enemigo("Orco", 60, 60, 12, 5, 8, 10) {}
                };

            case 2:
            	return new Enemigo[] {
                        new Enemigo("Orco", 60, 60, 12, 5, 8, 10) {}
                };
            	
            case 3: 
            	return new Enemigo[] {
                        new Enemigo("Caballero oscuro", 80, 80, 18, 8, 10, 14) {}
                };
                
            default:
                return new Enemigo[] {};
        }
    }
    

    
}
