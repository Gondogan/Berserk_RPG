package store;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import characters.Personajes;
import inventory.Item;
import inventory.Pocion;
import inventory.MejoraArma;
import inventory.MejoraArmadura;

public class Tienda {

    private List<Item> catalogo;
    private boolean itemGratisDisponible;
    private Item itemRecompensa;
    private List<String> mensajesBienvenida;
    private Random rand;

    public Tienda() {
        this.catalogo = new ArrayList<>();
        this.mensajesBienvenida = new ArrayList<>();
        this.rand = new Random();

        inicializarCatalogo();
        inicializarMensajes();

        this.itemGratisDisponible = true;
        this.itemRecompensa = generarItemRecompensa();
    }

    // ================= MÉTODO PRINCIPAL =================

    public void abrirTienda(Personajes jugador) {

        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        mostrarMensajeBienvenida();

        while (!salir) {

            System.out.println("\n===== TIENDA =====");
            System.out.println("Oro actual: " + jugador.getOro());

            mostrarItemGratis();
            mostrarStockDisponible();

            System.out.println("\nOpciones:");
            System.out.println("1. Comprar item");

            if (itemGratisDisponible) {
                System.out.println("2. Reclamar item gratis");
                System.out.println("3. Salir");
            } else {
                System.out.println("2. Salir");
            }

            int opcion = scanner.nextInt();

            if (itemGratisDisponible) {

                switch (opcion) {

                    case 1:
                        comprarDesdeMenu(jugador, scanner);
                        break;

                    case 2:
                        reclamarItemGratis(jugador);
                        break;

                    case 3:
                        salir = true;
                        mostrarDespedida();
                        break;

                    default:
                        System.out.println("Opción inválida.");
                }

            } else {

                switch (opcion) {

                    case 1:
                        comprarDesdeMenu(jugador, scanner);
                        break;

                    case 2:
                        salir = true;
                        mostrarDespedida();
                        break;

                    default:
                        System.out.println("Opción inválida.");
                }
            }
        }
    }

    // ================= COMPRA =================

    private void comprarDesdeMenu(Personajes jugador, Scanner scanner) {

        System.out.println("\nSelecciona el número del item:");

        for (int i = 0; i < catalogo.size(); i++) {
            Item item = catalogo.get(i);
            System.out.println((i + 1) + ". " + item.getNombre()
                    + " - " + item.getPrecio() + " oro");
        }

        int opcion = scanner.nextInt() - 1;

        if (opcion >= 0 && opcion < catalogo.size()) {

            Item item = catalogo.get(opcion);

            if (jugador.getOro() >= item.getPrecio()) {

                jugador.perderOro(item.getPrecio());
                jugador.getInventario().agregarItem(item);

                System.out.println("Has comprado: " + item.getNombre());

            } else {
                System.out.println("No tienes suficiente oro.");
            }

        } else {
            System.out.println("Selección inválida.");
        }
    }

    // ================= ITEM GRATIS =================

    public void reclamarItemGratis(Personajes jugador) {

        if (!itemGratisDisponible) {
            System.out.println("Ya has reclamado el item gratuito.");
            return;
        }

        jugador.getInventario().agregarItem(itemRecompensa);
        jugador.ganarOro(20);

        itemGratisDisponible = false;

        System.out.println("Has recibido: " + itemRecompensa.getNombre() + " + 20 oro");
    }

    private void mostrarItemGratis() {

        String estado = itemGratisDisponible ? "[Disponible]" : "[Reclamado]";
        System.out.println("\nItem gratis " + estado + ": " + itemRecompensa.getNombre());
    }

    // ================= CATÁLOGO =================

    public void inicializarCatalogo() {

        // Pociones
        catalogo.add(new Pocion(
                "Poción pequeña",
                "Recupera vida",
                20,
                Item.TipoItem.Pocion_pequenia,
                30,
                1
        ));

        catalogo.add(new Pocion(
                "Poción grande",
                "Recupera mucha vida",
                50,
                Item.TipoItem.Pocion_grande,
                80,
                1
        ));

        // Mejoras
        catalogo.add(new MejoraArma(
                "Mejora arma",
                "Aumenta daño",
                100,
                10
        ));

        catalogo.add(new MejoraArmadura(
                "Mejora armadura",
                "Aumenta defensa",
                100,
                10
        ));
    }

    private void mostrarStockDisponible() {

        System.out.println("\n--- Catálogo ---");

        for (Item item : catalogo) {
            System.out.println(item.getNombre() + " - " + item.getPrecio() + " oro");
        }
    }

    // ================= ITEM GRATIS RANDOM =================

    private Item generarItemRecompensa() {

        // 30% probabilidad de poción grande
        if (rand.nextInt(100) < 30) {
            return new Pocion(
                    "Poción grande",
                    "Recupera mucha vida",
                    0,
                    Item.TipoItem.Pocion_grande,
                    80,
                    1
            );
        }

        // Siempre mínimo una pequeña
        return new Pocion(
                "Poción pequeña",
                "Recupera vida",
                0,
                Item.TipoItem.Pocion_pequenia,
                30,
                1
        );
    }

    // ================= MENSAJES =================

    private void inicializarMensajes() {

        mensajesBienvenida.add("Bienvenido, viajero. Tengo lo que necesitas...");
        mensajesBienvenida.add("No encontrarás mejor mercancía.");
        mensajesBienvenida.add("El poder tiene un precio.");
    }

    public void mostrarMensajeBienvenida() {

        int indice = rand.nextInt(mensajesBienvenida.size());

        System.out.println("\n===== TIENDA DEL CAMINO =====");
        System.out.println(mensajesBienvenida.get(indice));
    }

    public void mostrarDespedida() {

        String[] mensajes = {
                "Vuelve pronto.",
                "Que tengas suerte.",
                "Recuerda hacerte más fuerte.",
                "Hasta la próxima."
        };

        int indice = rand.nextInt(mensajes.length);
        System.out.println("\n" + mensajes[indice]);
    }
}