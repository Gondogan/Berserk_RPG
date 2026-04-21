package game;

import java.util.Scanner;

import characters.Casca;
import characters.Griffith;
import characters.Guts;
import characters.Personajes;
import game.EstadoJuego;
import map.Senda1;
import map.Senda2;
import map.Senda3;
import map.ZonaFinal;
import store.Tienda;
import game.Narrador;
import inventory.Inventario;

public class Juego {
    private Scanner scanner;
    private EstadoJuego estadoJuego;
    private Personajes jugador;
    private Tienda tienda = new Tienda();
    private Personajes guts;
    private Personajes griffith;
    private Personajes casca;
    private Narrador narrador;

    public Juego() {
        scanner = new Scanner(System.in);
        estadoJuego = new EstadoJuego();
        this.guts = new Guts();
        this.griffith = new Griffith();
        this.casca = new Casca();
        this.narrador = new Narrador();
        
    }

    // ================= INICIO =================

    public void iniciar() throws InterruptedException{

    	
    	
    	
    	
    	narrador.imprimirAsciiRojo("                                                                                                                                  \r\n"
    			+ "                                                                ██                                                                \r\n"
    			+ "                                                             █  ██  █                                                             \r\n"
    			+ "                                                      ██      ██████      ██                                                      \r\n"
    			+ "                                                    ███        ████        ███                                                    \r\n"
    			+ "                                                   ███         ████         ███                                                   \r\n"
    			+ "                                                 ████          ████          ████                                                 \r\n"
    			+ "                                               █████           ████           █████                                               \r\n"
    			+ "                                             ██████            ████            ██████                                             \r\n"
    			+ "                                            ██████             ████             ██████                                            \r\n"
    			+ "                                              ██████           ████           ██████▓                                             \r\n"
    			+ "                                               ███████         ████         ███████                                               \r\n"
    			+ "                                                 ███████       ████       ███████                                                 \r\n"
    			+ "                                                   ███████     ████     ███████                                                   \r\n"
    			+ "                                                     ███████   ████   ███████                                                     \r\n"
    			+ "                                                       ███████ ████ ███████                                                       \r\n"
    			+ "                                                         ████████████████                                                         \r\n"
    			+ "                                                           ████████████                                                           \r\n"
    			+ "                                                             ████████                                                             \r\n"
    			+ "                                                           ████████████                                                           \r\n"
    			+ "                                                         ████████████████                                                         \r\n"
    			+ "                                                        ██████████████████                                                        \r\n"
    			+ "                                                      ███████  ████  ███████                                                      \r\n"
    			+ "                                                    ███████▓   ████    ███████                                                    \r\n"
    			+ "                                                  ███████      ████      ███████                                                  \r\n"
    			+ "                                                ███████        ████        ███████                                                \r\n"
    			+ "                                              ███████          ████          ███████                                              \r\n"
    			+ "                                            ███████            ████            ███████                                            \r\n"
    			+ "                                            ███████            ████            ███████                                            \r\n"
    			+ "                                              ███████          ████          ███████                                              \r\n"
    			+ "                                                ███████        ████        ███████                                                \r\n"
    			+ "                                                  ███████      ████      ███████                                                  \r\n"
    			+ "                                                    ███████    ████    ███████                                                    \r\n"
    			+ "                                                      ███████  ████  ▓███████                                                     \r\n"
    			+ "                                                        ██████ ████ ██████                                                        \r\n"
    			+ "                                                         ████████████████                                                         \r\n"
    			+ "                                                           ████████████                                                           \r\n"
    			+ "                                                             ████████                                                             \r\n"
    			+ "                                                               ████                                                               \r\n"
    			+ "                                                                                                                                  ");
    	narrador.imprimirAsciiRojo("                         ██████╗ ███████╗██████╗ ███████╗███████╗██████╗ ██╗  ██╗    ██████╗ ██████╗  ██████╗ \r\n"
    			+ "                         ██╔══██╗██╔════╝██╔══██╗██╔════╝██╔════╝██╔══██╗██║ ██╔╝    ██╔══██╗██╔══██╗██╔════╝ \r\n"
    			+ "                         ██████╔╝█████╗  ██████╔╝███████╗█████╗  ██████╔╝█████╔╝     ██████╔╝██████╔╝██║  ███╗\r\n"
    			+ "                         ██╔══██╗██╔══╝  ██╔══██╗╚════██║██╔══╝  ██╔══██╗██╔═██╗     ██╔══██╗██╔═══╝ ██║   ██║\r\n"
    			+ "                         ██████╔╝███████╗██║  ██║███████║███████╗██║  ██║██║  ██╗    ██║  ██║██║     ╚██████╔╝\r\n"
    			+ "                         ╚═════╝ ╚══════╝╚═╝  ╚═╝╚══════╝╚══════╝╚═╝  ╚═╝╚═╝  ╚═╝    ╚═╝  ╚═╝╚═╝      ╚═════╝ \r\n"
    			+ "                                                                                                              ");
       
        narrador.decirDialogo("Las campanas de la desesperación doblan por aquellos que aún respiran...");
        narrador.decirDialogo("En un mundo donde la luz es solo una pálida memoria, la Mano de Dios mueve los hilos de la humanidad como marionetas de carne.");
        narrador.decirDialogo("Llevas la Marca del Sacrificio. No hay salvación, solo la agonía de luchar contra el destino.");
        narrador.decirDialogo("¿Mantendrás tu humanidad... o te convertirás en un monstruo? El Eclipse se acerca.\n\n");
        
        
        seleccionarPersonaje();

        boolean salir = false;

        while (!salir) {

            mostrarMenuPrincipal();

            int opcion = scanner.nextInt();

            switch (opcion) {

                case 1:
                    menuSenda();
                    break;

                case 2:
                    abrirInventario();
                    break;

                case 3:
                    tienda.abrirTienda(jugador);
                    break;

                case 4:
                    seleccionarPersonaje();
                    break;

                case 5:
                	salir = true;
                    System.out.println("Saliendo del juego...");
                    break;
                    
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    // ================= MENÚ PERSONAJE =================

    private void seleccionarPersonaje() {

        System.out.println("\nSelecciona personaje:\n");
        System.out.println("1. " + guts + "\n");
        System.out.println("2. " + griffith + "\n");
        System.out.println("3. " + casca + "\n");

        int opcion = scanner.nextInt();

        switch (opcion) {

            case 1:
                jugador = new Guts();
                break;

            case 2:
                jugador = new Griffith();
                break;

            case 3:
                jugador = new Casca();
                break;

            default:
                System.out.println("Opción inválida.");
                seleccionarPersonaje();
        }

        System.out.println("\nHas elegido: " + jugador.getName());
    }

    // ================= MENÚ PRINCIPAL =================

    private void mostrarMenuPrincipal() {

        System.out.println("\n===== MENÚ PRINCIPAL =====\n");
        System.out.println("1. Elegir Senda");
        System.out.println("2. Inventario");
        System.out.println("3. Tienda");
        System.out.println("4. Cambiar Personaje");
        System.out.println("5. Salir");
    }

    // ================= MENÚ SENDA =================

    private void menuSenda() throws InterruptedException {

        System.out.println("\nSelecciona una senda:\n");
        System.out.println("1. El Asedio de Midland");
        System.out.println("2. El Bosque de los Espíritus");
        System.out.println("3. El Altar de la Invocación");

        // 🔥 Solo aparece si cumple condición
        if (estadoJuego.getCombatesGanados() >= 4) {
            System.out.println("4. Dominio de Skull Knight");
        }

        int opcion = scanner.nextInt();

        switch (opcion) {

            case 1:
                new Senda1(jugador, estadoJuego,narrador).iniciarSenda();
                break;

            case 2:
                new Senda2(jugador, estadoJuego,narrador).iniciarSenda();
                break;

            case 3:
                new Senda3(jugador, estadoJuego,narrador).iniciarSenda();
                break;

            case 4:
                if (estadoJuego.getCombatesGanados() >= 4) {
                    new ZonaFinal(jugador, estadoJuego,narrador).iniciarSenda();
                } else {
                    System.out.println("Zona Final bloqueada.");
                }
                break;

            default:
                System.out.println("Opción inválida.");
        }
    }

    // ================= TIENDA =================

    private void abrirTienda() {

        tienda.mostrarMensajeBienvenida();
        tienda.inicializarCatalogo();

        System.out.println("Pulsa cualquier número para salir...");
        scanner.nextInt();
    }
    
    // ============= INVENTARIO ================
    private void abrirInventario() {

        if (jugador.getInventario().estaVacio()) {
            System.out.println("\nEl inventario está vacío.");
            return;
        }

        boolean salir = false;

        while (!salir) {

            System.out.println("\n===== INVENTARIO =====");

            jugador.getInventario().mostrarInventario();

            System.out.println("\nOpciones:");
            System.out.println("1. Usar objeto");
            System.out.println("2. Salir");

            int opcion = scanner.nextInt();

            switch (opcion) {

                case 1:
                    usarObjetoInventario();
                    break;

                case 2:
                    salir = true;
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
    
    private void usarObjetoInventario() {

        while (true) {

            jugador.getInventario().mostrarInventario();

            System.out.println("\nSelecciona objeto (0 para cancelar):");

            int opcion = scanner.nextInt();

            if (opcion == 0) {
                return;
            }

            int indice = opcion - 1;

            if (indice >= 0 && indice < jugador.getInventario().size()) {
                jugador.getInventario().usarItem(indice, jugador);
                return;
            } else {
                System.out.println("Selección inválida.");
            }
        }
    }
}
