package inventory;

import java.util.ArrayList;
import java.util.List;

public class Inventario {

    private List<Item> items;

    public Inventario() {
        this.items = new ArrayList<>();
    }

    public void agregarItem(Item nuevoItem) {

        // Si es apilable, buscar si ya existe
        if (nuevoItem.esApilable()) {

            for (Item item : items) {
                if (item.getNombre().equals(nuevoItem.getNombre())) {
                    item.incrementarCantidad();
                    return;
                }
            }
        }

        // Si no existe o no es apilable
        items.add(nuevoItem);
    }

    public void mostrarInventario() {

        System.out.println("=== INVENTARIO ===");

        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);

            if (item.esApilable()) {
                System.out.println((i + 1) + ". " + item.getNombre() + " x" + item.getCantidad());
            } else {
                System.out.println((i + 1) + ". " + item.getNombre());
            }
        }
    }

    // Lógica para usar un item
    
    public void usarItem(int indice, characters.Personajes personaje) {

        Item item = items.get(indice);

        System.out.println("Usas: " + item.getNombre());

        item.usar(personaje);

        if (item.esConsumible()) {
            item.decrementarCantidad();

            if (item.getCantidad() == 0) {
                items.remove(indice);
            }
        } else {
            // mejoras se consumen directamente
            items.remove(indice);
        }
    }
    
    // Comprueba si el inventario está vacío
    
    public boolean estaVacio() {
        return items.isEmpty();
    }
}