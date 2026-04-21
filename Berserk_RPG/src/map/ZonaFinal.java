package map;

import enemies.Enemigo;
import enemies.JefeFinalFase2;
import java.util.Scanner;
import characters.Personajes;
import combat.Combate;
import combat.ResultadoCombate;
import game.EstadoJuego;
import store.GestorRecompensa;
import java.util.ArrayList;
import game.Narrador;

public class ZonaFinal {

    private ArrayList<JefeFinalFase2> jefes;

    private Personajes jugador;
    private EstadoJuego estadoJuego;
    private GestorRecompensa gestorRecompensa;
    private Narrador narrador;
    private Scanner scanner;

    private static final int TOTAL_COMBATES = 2;

    public ZonaFinal(Personajes jugador, EstadoJuego estadoJuego) {
        this.jugador = jugador;
        this.estadoJuego = estadoJuego;
        this.narrador = new Narrador();
        this.gestorRecompensa = new GestorRecompensa();
        this.scanner = new Scanner(System.in);

        jefes = new ArrayList<>();

        jefes.add(new JefeFinalFase2("Horda Muerta", 400, 55, 25, 10, "Asalto brutal"));
        jefes.add(new JefeFinalFase2("Skull Knight", 500, 55, 50, 10, "Corte espectral"));
    }

    public ArrayList<JefeFinalFase2> getJefes() {
        return jefes;
    }

    /**
     * Zona Final:
     * - 2 combates consecutivos
     * - recompensa tras cada combate
     * - recuperación de energía tras victoria
     * - textos del narrador antes, entre y después del combate final
     */

    public void iniciarSenda() throws InterruptedException {

        // --- 1. INTRODUCCIÓN DE LA ZONA FINAL ---
        narrador.limpiarConsola();
        narrador.decirDialogo("El tiempo se ha detenido aquí, en el nexo entre la vida y la condenación.");
        narrador.decirDialogo("Caminas sobre un mar de espadas oxidadas que brotan de una tierra saturada de sangre antigua.");
        narrador.decirDialogo("Sobre ti, un cielo de ébano es desgarrado por un eclipse eterno que no da luz, solo sombra.");
        narrador.decirDialogo("El aire es pesado, cargado con el lamento de millones de almas sacrificadas por la ambición.");

        int indiceJefe = 0;

        while (indiceJefe < TOTAL_COMBATES && jugador.isVivo()) {

            Enemigo[] enemigos = generarEnemigo(indiceJefe);
            String nombreActual = enemigos[0].getNombre();

            System.out.println("\nCombate " + (indiceJefe + 1) + " de " + TOTAL_COMBATES);

            // --- 2. DIÁLOGO ANTES DE LUCHAR CONTRA SKULL KNIGHT ---
            if (nombreActual.equals("Skull Knight")) {
                narrador.hablarPersonaje("Skull Knight", "Luchador contra el destino... Forcejeador que se arrastra en la oscuridad.");
                narrador.hablarPersonaje("Skull Knight", "Tu marca sangra con una intensidad que no veía desde hace eras. ¿Buscas desafiar la causalidad o simplemente has venido a que tu alma sea devorada por el abismo?");
                narrador.hablarPersonaje("Skull Knight", "Empuña tu acero, mortal, pues aquí la muerte no es el final, sino una eternidad de tormento. ¡En guardia!");
            }

            Combate combate = new Combate(jugador, enemigos);
            ResultadoCombate resultado = combate.iniciarCombate();

            // ================= RESULTADO =================

            if (resultado == ResultadoCombate.DERROTA) {

                // --- FRASE CUANDO ÉL ME MATE ---
                if (nombreActual.equals("Horda Muerta")) {
                    narrador.hablarPersonaje("Skull Knight", "Incluso aquel que lucha contra el flujo del río termina ahogándose en su corriente. Tu lucha termina aquí, un simple fragmento de cristal roto en el gran esquema de la causalidad.");
                } else if (nombreActual.equals("Skull Knight")) {
                    narrador.hablarPersonaje("Skull Knight", "Tu voluntad se ha quebrado antes que el destino. Descansa ahora en el olvido, pues tu nombre no será más que un susurro en la tormenta.");
                }

                System.out.println("\n Has sido derrotado... ");
                estadoJuego.marcarDerrota();
                return;
            }

            if (resultado == ResultadoCombate.HUIDA) {

                System.out.println("\n Has abandonado la Zona Final. ");
                return;
            }

            // ================= VICTORIA =================

            System.out.println("\nHas ganado el combate.");

            gestorRecompensa.generarRecompensa(jugador, indiceJefe + 1);

            jugador.recuperarEnergia(30);
            System.out.println("Tu energía ha sido restaurada.\n");

            System.out.println(jugador);

            // --- 3. DIÁLOGO CUANDO ACTIVA LA FASE 2 ---
            if (nombreActual.equals("Horda Muerta")) {
                narrador.decirDialogo("El caballero sobre el corcel negro alza su espada, y el Intersticio parece agrietarse bajo su presencia.");
                narrador.hablarPersonaje("Skull Knight", "¡No está mal! Has resistido el embate de la horda, pero el verdadero peso del destino no se mide en carne, sino en voluntad.");
                narrador.hablarPersonaje("Skull Knight", "¡Observa cómo la realidad se doblega ante mi acero! ¡Siente el peso de la eternidad!");
            }

            indiceJefe++;

            // ================= DECISIÓN =================

            if (indiceJefe < TOTAL_COMBATES) {

                if (!menuPostCombate()) {

                    System.out.println("\n Has abandonado la Zona Final. ");
                    return;
                }
            }
        }

        // ================= FINAL ZONA =================

        if (jugador.isVivo()) {

            // --- 5. FRASE CUANDO YO LE DERROTE ---
            narrador.hablarPersonaje("Skull Knight", "Has forzado una grieta en lo absoluto. El destino ha sido herido, pero no te engañes... esto es solo el comienzo.");
            narrador.hablarPersonaje("Skull Knight", "Sigue adelante, forcejeador. El verdadero Eclipse apenas empieza a proyectar su sombra sobre este mundo.");
            narrador.decirDialogo("Skull Knight se desvanece en la niebla espectral, dejando tras de sí el eco de una batalla que resonará por siempre.");

            System.out.println("\n🏆 ¡Has completado la ZONA FINAL!");
            estadoJuego.registrarVictoria();
        }
    }

    // ================= MENÚ ENTRE COMBATES =================

    private boolean menuPostCombate() {

        System.out.println("\n¿Qué quieres hacer?");
        System.out.println("1. Continuar");
        System.out.println("2. Huir de la zona final");

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

    private Enemigo[] generarEnemigo(int nivel) {

        switch (nivel) {

            case 0:
                return new Enemigo[] { jefes.get(0) };

            case 1:
                return new Enemigo[] { jefes.get(1) };

            default:
                return new Enemigo[] {};
        }
    }
}