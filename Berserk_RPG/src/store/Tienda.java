package store;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import characters.Personajes;
import inventory.*;

public class Tienda {

    private List<Item> catalogo;
    private boolean itemGratisDisponible;
    private Item itemRecompensa;
    private Random rand;

    public Tienda() {
        this.catalogo = new ArrayList<>();
        this.rand = new Random();
        this.itemGratisDisponible = true;

        inicializarCatalogo();
        this.itemRecompensa = generarItemRecompensaGratis();
    }

    // ================= CATÁLOGO =================

    private void inicializarCatalogo() {

        catalogo.add(new Pocion("Poción pequeña", "Recupera 30 de vida", 20, Item.TipoItem.Pocion_pequenia, 30, 1));
        catalogo.add(new Pocion("Poción grande", "Recupera 80 de vida", 50, Item.TipoItem.Pocion_grande, 80, 1));

        catalogo.add(new MejoraArmadura("Refuerzo de armadura", "+5 defensa", 60, 5));
        catalogo.add(new MejoraArma("Afilado de espada", "+5 ataque", 60, 5));
    }

    public void mostrarCatalogo() {

        System.out.println("\n── TIENDA ─────────────────────");

        mostrarItemGratis();

        System.out.println("\n--- Catálogo ---");
        for (int i = 0; i < catalogo.size(); i++) {
            Item item = catalogo.get(i);
            System.out.println((i + 1) + ". " + item.getNombre() + " - " + item.getPrecio() + " oro");
        }

        System.out.println("───────────────────────────────");
    }

    // ================= COMPRA =================

    public void comprarItem(int indice, Personajes personaje) {

        if (indice < 0 || indice >= catalogo.size()) {
            System.out.println("Selección inválida.");
            return;
        }

        Item item = catalogo.get(indice);

        if (!jugadorPuedePermitirse(item, personaje)) {
            return;
        }

        personaje.perderOro(item.getPrecio());
        personaje.getInventario().agregarItem(item);

        System.out.println("Has comprado: " + item.getNombre());
    }

    private boolean jugadorPuedePermitirse(Item item, Personajes personaje) {

        if (personaje.getOro() >= item.getPrecio()) {
            return true;
        }

        System.out.println("No tienes suficiente oro.");
        return false;
    }

    // ================= ITEM GRATIS =================

    public void reclamarItemGratis(Personajes personaje) {

        if (!itemGratisDisponible) {
            System.out.println("Ya has reclamado el item gratuito.");
            return;
        }

        personaje.getInventario().agregarItem(itemRecompensa);
        personaje.ganarOro(20);

        itemGratisDisponible = false;

        System.out.println("Has recibido: " + itemRecompensa.getNombre() + " + 20 de oro");
    }

    private Item generarItemRecompensaGratis() {

        int probabilidad = rand.nextInt(100);

        if (probabilidad < 30) {
            return new Pocion("Poción grande", "Recupera 80 de vida", 0,
                    Item.TipoItem.Pocion_grande, 80, 1);
        }

        return new Pocion("Poción pequeña", "Recupera 30 de vida", 0,
                Item.TipoItem.Pocion_pequenia, 30, 1);
    }

    private void mostrarItemGratis() {

        String estado = itemGratisDisponible ? "[Disponible]" : "[Reclamado]";

        System.out.println("Item gratis " + estado + ": " + itemRecompensa.getNombre());
    }
}