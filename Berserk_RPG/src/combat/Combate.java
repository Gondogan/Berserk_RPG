package combat;

import java.util.Random;
import java.util.Scanner;

import characters.Personajes;
import enemies.Enemigo;

public class Combate {

    private Personajes jugador;
    private Enemigo[] enemigos;   // ahora es un array
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

    public void iniciarCombate() {

        mostrarInicioCombate();

        // recorremos todos los enemigos
        while (indiceEnemigoActual < enemigos.length && jugador.isVivo()) {

            Enemigo enemigoActual = enemigos[indiceEnemigoActual];

            System.out.println("\n👹 Aparece: " + enemigoActual.getNombre());

            combatirContraEnemigo(enemigoActual);

            // si el jugador muere, se corta todo
            if (!jugador.isVivo()) {
                break;
            }

            // si el enemigo muere → siguiente
            if (!enemigoActual.estaVivo()) {
                indiceEnemigoActual++;
            }
        }

        finalizarCombate();
    }

    // ================= COMBATE INDIVIDUAL =================

    private void combatirContraEnemigo(Enemigo enemigo) {

        boolean turnoJugador = jugador.getVelocidad() >= enemigo.getVelocidad();

        while (jugador.isVivo() && enemigo.estaVivo()) {

            mostrarEstado(enemigo);

            if (turnoJugador) {
                if (!turnoJugador(enemigo)) {
                    return; // huida
                }
            } else {
                turnoEnemigo(enemigo);
            }

            turnoJugador = !turnoJugador;
        }
    }

    // ================= TURNO JUGADOR =================

    private boolean turnoJugador(Enemigo enemigo) {

        System.out.println("\n--- TU TURNO ---");
        System.out.println("1. Atacar");
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
                if (intentarHuir()) {
                    System.out.println("Has huido del combate.");
                    return false;
                } else {
                    System.out.println("No has podido huir...");
                    turnoEnemigo(enemigo);
                }
                break;

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

    /**
     * Adaptador para poder usar habilidades sobre Enemigo
     */
    private Personajes convertirEnemigo(Enemigo enemigo) {

        return new Personajes(enemigo.getNombre(), enemigo.getVidaMax(),
                enemigo.getDanioBase(), enemigo.getDefensa(),
                enemigo.getVelocidad(), 0) {

            @Override
            protected void inicializarHabilidades() {}

            @Override
            protected void aplicarBonusDeSubidaNivel() {}

            @Override
            public void activarPasiva() {}

            @Override
            public void recibirDanio(int danio) {
                enemigo.recibirDanio(danio);
            }
        };
    }

    // ================= INVENTARIO =================

    private void usarInventario() {

        if (jugador.getInventario().estaVacio()) {
            System.out.println("No tienes objetos.");
            return;
        }

        jugador.getInventario().mostrarInventario();

        System.out.println("Selecciona objeto:");
        int opcion = scanner.nextInt() - 1;

        jugador.getInventario().usarItem(opcion, jugador);
    }

    // ================= TURNO ENEMIGO =================

    private void turnoEnemigo(Enemigo enemigo) {

        System.out.println("\n--- TURNO ENEMIGO ---");

        int danio = enemigo.atacar();

        jugador.recibirDanio(danio);
    }

    // ================= UTIL =================

    private boolean intentarHuir() {
        return rand.nextInt(100) < 50;
    }

    private void mostrarEstado(Enemigo enemigo) {

        System.out.println("\n=====================================================================\n");
        System.out.println(jugador);
        System.out.println("\n - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - \n");
        System.out.println(enemigo);
        System.out.println("\n=======================================================================\n");
    }
    
    // ============== INICIO COMBATE ==================
    
    private void mostrarInicioCombate() {

        if (enemigos.length > 1) {
        	System.out.println("\n⚔️  ¡COMBATE CONTRA HORDA!  ⚔️");
        } else {
            System.out.println("\n⚔️  ¡COMBATE INICIADO!  ⚔️");
        }
    }

    private void finalizarCombate() {

        if (!jugador.isVivo()) {
            System.out.println("\n💀 Has sido derrotado...");
        } else {
            System.out.println("\n🏆 ¡Has derrotado a toda la horda!");
        }
    }
}