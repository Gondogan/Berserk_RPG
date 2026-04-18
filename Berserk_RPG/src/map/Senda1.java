package map;

<<<<<<< HEAD
import enemies.Enemigo;
import enemies.EnemigoBasico;

public class Senda1 {

    
    private final Enemigo[] enemigos = {
        new EnemigoBasico("Soldado Kushan", 80, 15, 5, 10,"Navajazo"),
        new EnemigoBasico("Explorador Pishacha", 110, 22, 8, 12, "Golpe rápido")
    };

    public Enemigo[] getEnemigos() {
        return enemigos;
=======
import java.util.Scanner;

import characters.Personajes;
import combat.Combate;
import combat.ResultadoCombate;
import enemies.Enemigo;
import game.EstadoJuego;
import store.GestorRecompensa;
import store.Tienda;

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
    private Tienda tienda;
    private Scanner scanner;

    private static final int TOTAL_COMBATES = 3;

    public Senda1(Personajes jugador, EstadoJuego estadoJuego) {
        this.jugador = jugador;
        this.estadoJuego = estadoJuego;
        this.gestorRecompensa = new GestorRecompensa();
        this.tienda = new Tienda();
        this.scanner = new Scanner(System.in);
    }

    // ================= INICIO SENDA =================

    public void iniciarSenda() {

        System.out.println("\n🌿 Entras en la SENDA 1...");

        int progreso = 0;

        // 🔁 Bucle principal de la senda
        while (progreso < TOTAL_COMBATES && jugador.isVivo()) {

            System.out.println("\n⚔️ Combate " + (progreso + 1) + " de " + TOTAL_COMBATES);

            Enemigo[] enemigos = generarEnemigo(progreso);

            Combate combate = new Combate(jugador, enemigos);

            ResultadoCombate resultado = combate.iniciarCombate();

            // ================= RESULTADO =================

            if (resultado == ResultadoCombate.DERROTA) {

                System.out.println("\n💀 Has sido derrotado...");
                estadoJuego.marcarDerrota();
                return;
            }

            if (resultado == ResultadoCombate.HUIDA) {

                System.out.println("\n🏃 Has abandonado la senda.");
                return;
            }

            // ================= VICTORIA =================

            System.out.println("\n✔ Has ganado el combate.");

            // 🔥 Recompensa
            gestorRecompensa.generarRecompensa(jugador, progreso + 1);

            // 🔥 Curación completa tras cada combate
            jugador.curarCompleto();
            System.out.println("❤️ Tu vida ha sido restaurada al máximo.");

            progreso++;

            // ================= DECISIÓN =================

            if (progreso < TOTAL_COMBATES) {

                if (!menuPostCombate()) {

                    System.out.println("\n❌ Has abandonado la senda. Progreso reiniciado.");
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
        System.out.println("2. Ir a tienda (pierdes progreso)");

        int opcion = scanner.nextInt();

        switch (opcion) {

            case 1:
                return true;

            case 2:
                abrirTienda();
                return false;

            default:
                System.out.println("Opción inválida.");
                return menuPostCombate();
        }
    }

    // ================= TIENDA =================

    private void abrirTienda() {

        tienda.mostrarMensajeBienvenida();
        tienda.mostrarCatalogo();

        System.out.println("\nSales de la tienda...");
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
                    new Enemigo("Caballero oscuro", 80, 80, 18, 8, 10, 14) {}
                };

            default:
                return new Enemigo[] {};
        }
>>>>>>> pedro
    }
}