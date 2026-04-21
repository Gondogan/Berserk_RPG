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
import game.Narrador;

/**
 * Senda1:
 * - 3 combates consecutivos
 * - recompensa tras cada combate
 * - curación completa tras cada victoria
 * - opción de ir a tienda (reinicia progreso)
 */

public class Senda1 {


    private Personajes jugador;
    private EstadoJuego estadoJuego;
    private GestorRecompensa gestorRecompensa;
    private Narrador narrador;
    private Scanner scanner;

    private static final int TOTAL_COMBATES = 6;

    public Senda1(Personajes jugador, EstadoJuego estadoJuego, Narrador narrador) {
        this.jugador = jugador;
        this.estadoJuego = estadoJuego;
        this.narrador = narrador;
        this.gestorRecompensa = new GestorRecompensa();
        this.scanner = new Scanner(System.in);
    }
    
    
    // ================= INICIO SENDA =================

    public void iniciarSenda() throws InterruptedException {
    	
    	
    	
    	narrador.limpiarConsola();
        narrador.decirDialogo("El cielo de Midland ha muerto; ahora es solo una costra de hollín y oraciones no escuchadas.");
        narrador.decirDialogo("El viento arrastra el hedor dulce de la carne quemada y el hierro oxidado de miles de armaduras rotas.");
        narrador.decirDialogo("A lo lejos, los gritos de los agonizantes son silenciados por las risas guturales de los Pishacha que patrullan las ruinas.");
        narrador.decirDialogo("Bajo tus pies, el barro es una mezcla espesa de lluvia y sangre vieja. Aquí, la humanidad es solo combustible para el fuego del Imperio Kushan.");
        
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
                return new Enemigo[] {// String nombre, int vidaMax, int danioBase, int defensa, int velocidad
                    new Enemigo("Soldado Kushan", 80, 25, 10, 24) {}
                };

            case 1:
                return new Enemigo[] {
                    new Enemigo("Explorador Pishacha", 110, 29, 12, 60) {}
                };

            case 2:
            	return new Enemigo[] {
                        new Enemigo("Soldado Bikaraka", 220, 38, 30, 20) {}
                };
            	
            case 3: 
            	return new Enemigo[] {
                        new Enemigo("Soldado Bikaraka", 220, 38, 30, 20) {}
                };
            	
            case 4:
            	return new Enemigo[] {
                        new Enemigo("Soldado Bikaraka", 220, 38, 30, 20) {}
                };
            	
            case 5:
            	return new Enemigo[] {
                        new Enemigo("Soldado Bikaraka", 220, 38, 30, 20) {}
                };
            	
            default:
                return new Enemigo[] {};
        }
    }
    
    
}
