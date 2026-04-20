package combat;

import java.util.Random;
import java.util.Scanner;

import characters.Personajes;
import enemies.Enemigo;

public class Combate {

    private Personajes jugador;
    private Enemigo[] enemigos;
    private int indiceEnemigoActual;

    private Scanner scanner;
    private Random rand;

    public Combate(Personajes jugador, Enemigo[] enemigos) {
        this.jugador = jugador;
        this.enemigos = enemigos;
        this.indiceEnemigoActual = 0;
        this.scanner = new Scanner(System.in);
        this.rand = new Random();
    }

    /**
     * Método principal del combate
     * Ahora devuelve el resultado para que lo gestione EstadoJuego
     */
    public ResultadoCombate iniciarCombate() {

        mostrarInicioCombate();

        while (indiceEnemigoActual < enemigos.length && jugador.isVivo()) {
        	
            Enemigo enemigoActual = enemigos[indiceEnemigoActual];

            boolean continuar = combatirContraEnemigo(enemigoActual);

            // Si huye → devolvemos resultado
            if (!continuar) {
                System.out.println("Has escapado del combate.");
                return ResultadoCombate.HUIDA;
            }

            // Si muere → derrota
            if (!jugador.isVivo()) {
                return ResultadoCombate.DERROTA;
            }

            // Pasamos al siguiente enemigo
            if (!enemigoActual.estaVivo()) {
                indiceEnemigoActual++;
            }
        }

        finalizarCombate();

        return ResultadoCombate.VICTORIA;
    }

    // ================= COMBATE INDIVIDUAL =================

    private boolean combatirContraEnemigo(Enemigo enemigo) {

        boolean turnoJugador = jugador.getVelocidad() >= enemigo.getVelocidad();

        while (jugador.isVivo() && enemigo.estaVivo()) {

            mostrarEstado(enemigo);

            if (turnoJugador) {

                if (!turnoJugador(enemigo)) {
                    return false; // huida
                }

            } else {
                turnoEnemigo(enemigo);
            }

            turnoJugador = !turnoJugador;
        }

        return true;
    }

    // ================= TURNO JUGADOR =================

    private boolean turnoJugador(Enemigo enemigo) {

        System.out.println("\n--- TU TURNO ---");
        System.out.println("1. Usar habilidad");
        System.out.println("2. Inventario");
        System.out.println("3. Huir");

        int opcion = scanner.nextInt();

        switch (opcion) {

            case 1:
                usarHabilidad(enemigo);
                break;

            case 2:
                usarInventario();
                break;

            case 3:
                return intentarHuir();

            default:
                System.out.println("Opción inválida.");
                return turnoJugador(enemigo);
        }

        return true;
    }

    // ================= HABILIDADES =================

    private void usarHabilidad(Enemigo enemigo) {

        jugador.mostrarHabilidades();

        System.out.println("Selecciona habilidad:");
        int opcion = scanner.nextInt() - 1;

        jugador.usarHabilidadContra(opcion, convertirEnemigo(enemigo));
    }

    /*
     * Adaptador para reutilizar sistema de habilidades
     */
    
    private Personajes convertirEnemigo(Enemigo enemigo) {

        return new Personajes(enemigo.getNombre(), enemigo.getVidaMax(),
                enemigo.getDanioBase(), enemigo.getDefensa(),
                enemigo.getVelocidad(), 0) {

            @Override protected void inicializarHabilidades() {}
            @Override protected void aplicarBonusDeSubidaNivel() {}
            @Override public void activarPasiva() {}

            @Override
            public void recibirDanio(int danio) {
                enemigo.recibirDanio(danio);
            }
        };
    }

    // ================= INVENTARIO =================

    private void usarInventario() {

        if (jugador.getInventario().estaVacio()) {
            System.out.println("\nNo tienes objetos.");
            return;
        }

        jugador.getInventario().mostrarInventario();

        System.out.println("\nSelecciona objeto:");
        int opcion = scanner.nextInt() - 1;

        jugador.getInventario().usarItem(opcion, jugador);
    }

    // ================= TURNO ENEMIGO =================

    private void turnoEnemigo(Enemigo enemigo) {

        System.out.println("\n--- TURNO ENEMIGO ---\n");

        int danio = enemigo.atacar();

        jugador.recibirDanio(danio);
    }

    // ================= UTIL =================

    private boolean intentarHuir() {

        if (rand.nextInt(100) < 35) {
            return false; // huye
        } else {
            System.out.println("\nNo has podido huir...");
            return true; // sigue combate
        }
    }

    private void mostrarEstado(Enemigo enemigo) {

        System.out.println("\n=================================================================\n");
        System.out.println(jugador);
        System.out.println("\n- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  \n");
        System.out.println(enemigo);
        System.out.println("\n=================================================================\n");
    }

    private void mostrarInicioCombate() {

        if (enemigos.length > 1) {
            System.out.println("\n⚔️ ¡COMBATE CONTRA HORDA! ⚔️");
        } else {
            System.out.println("\n⚔️ ¡COMBATE INICIADO! ⚔️");
        }
    }

    private void finalizarCombate() {

        if (!jugador.isVivo()) {
            System.out.println("\n💀 Has sido derrotado... 💀");
        } else {
            System.out.println("\n🏆 ¡Has derrotado a todos los enemigos! 🏆");
        }
    }
}