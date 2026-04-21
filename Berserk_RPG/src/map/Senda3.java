package map;

import enemies.Enemigo;
import game.EstadoJuego;
import game.Narrador;
import store.GestorRecompensa;
import java.util.Scanner;
import characters.Personajes;
import combat.Combate;
import combat.ResultadoCombate;

public class Senda3 {
    
    private Personajes jugador;
    private EstadoJuego estadoJuego;
    private Narrador narrador;
    private GestorRecompensa gestorRecompensa;
    private Scanner scanner;

    private static final int TOTAL_COMBATES = 3;

    public Senda3(Personajes jugador, EstadoJuego estadoJuego, Narrador narrador) {
        this.jugador = jugador;
        this.estadoJuego = estadoJuego;
        this.narrador = narrador;
        this.gestorRecompensa = new GestorRecompensa();
        this.scanner = new Scanner(System.in);
    }
    

    public void iniciarSenda() throws InterruptedException {
    narrador.limpiarConsola();
    narrador.presentarSenda("SENDA 3 — EL ALTAR DE LA INVOCACIÓN");
    narrador.decirDialogo("El aire pesa como el plomo. El olor a azufre y excremento humano se te mete en los pulmones, recordándote que el infierno está subiendo a la superficie.");
    narrador.decirDialogo("El suelo se siente blando, casi como si estuvieras caminando sobre una herida abierta que se niega a cerrar.");
    narrador.decirDialogo("Escuchas el eco de látigos y el rezo fanático de aquellos que creen que el dolor es el único camino hacia la pureza.");
    narrador.decirDialogo("Tu Marca comienza a sangrar, palpitando al ritmo de un corazón oscuro que late en lo profundo de este matadero sagrado.");
    

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
                 System.out.println("\nCombate " + progreso + " de " + TOTAL_COMBATES);
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

             estadoJuego.registrarVictoria();
             
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

             System.out.println("\n🏆 ¡Has completado la senda El Altar de la Invocación!");

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
                     new Enemigo("Poseso", 242, 45, 26, 36) {}
                 };

             case 1:
                 return new Enemigo[] {
                     new Enemigo("Explorador Pishacha", 287, 51, 33, 60) {}
                 };

             case 2:
             	return new Enemigo[] {
                         new Enemigo("Soldado Bikaraka", 450, 62, 36, 39) {}
                 };
             
             default:
                 return new Enemigo[] {};
         }
     }
     
}
    
