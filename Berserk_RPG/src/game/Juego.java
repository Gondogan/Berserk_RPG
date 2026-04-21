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
                    tienda.abrirTienda(jugador);
                    break;

                case 3:
                    seleccionarPersonaje();
                    break;

                case 4:
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
        System.out.println("2. Tienda");
        System.out.println("3. Cambiar Personaje");
        System.out.println("4. Salir");
    }

    // ================= MENÚ SENDA =================

    private void menuSenda() throws InterruptedException {

        System.out.println("\nSelecciona una senda:\n");
        System.out.println("1. Senda 1");
        System.out.println("2. Senda 2");
        System.out.println("3. Senda 3");

        // 🔥 Solo aparece si cumple condición
        if (estadoJuego.getCombatesGanados() >= 4) {
            System.out.println("4. Zona Final");
        }

        int opcion = scanner.nextInt();

        switch (opcion) {

            case 1:
                new Senda1(jugador, estadoJuego).iniciarSenda();
                break;

            case 2:
                new Senda2(jugador, estadoJuego).iniciarSenda();
                break;

            case 3:
                new Senda3(jugador, estadoJuego).iniciarSenda();
                break;

            case 4:
                if (estadoJuego.getCombatesGanados() >= 4) {
                    new ZonaFinal(jugador, estadoJuego).iniciarSenda();
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
}
