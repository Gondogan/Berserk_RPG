package inventory;

/**
 * Clase base de todos los items del juego
 */
public abstract class Item {

    public enum TipoItem {
        Pocion_pequenia,
        Pocion_grande,
        Cura_totalEstado,
        Mejora_Armadura,
        Mejora_Arma,
        Oro
    }

    private String nombre;
    private String descripcion;
    private int precio;
    private TipoItem tipo;
    private int valorEfecto;
    private int cantidad;

    public Item(String nombre, String descripcion, int precio, TipoItem tipo, int valorEfecto, int cantidad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.tipo = tipo;
        this.valorEfecto = valorEfecto;
        this.cantidad = cantidad;
    }

    // ================= GETTERS =================

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getPrecio() {
        return precio;
    }

    public TipoItem getTipo() {
        return tipo;
    }

    public int getValorEfecto() {
        return valorEfecto;
    }

    public int getCantidad() {
        return cantidad;
    }

    // ================= CANTIDAD =================

    public void incrementarCantidad() {
        this.cantidad++;
    }

    public void decrementarCantidad() {
        if (this.cantidad > 0) {
            cantidad--;
        }
    }

    // ================= TIPOS =================

    public boolean esConsumible() {
        return tipo == TipoItem.Pocion_pequenia ||
               tipo == TipoItem.Pocion_grande ||
               tipo == TipoItem.Cura_totalEstado;
    }

    public boolean esMejoraArmadura() {
        return tipo == TipoItem.Mejora_Armadura;
    }

    public boolean esMejoraArma() {
        return tipo == TipoItem.Mejora_Arma;
    }

    public boolean esApilable() {
        return esConsumible();
    }

    // ================= USO =================

    public abstract void usar(characters.Personajes personaje);

    @Override
    public String toString() {
        return nombre + " | Precio: " + precio + " | Efecto: " + valorEfecto + " | " + descripcion;
    }
}