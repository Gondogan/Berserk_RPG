package inventory;

import characters.Personajes;

public class Pocion extends Item {

    public Pocion(String nombre, String descripcion, int precio, TipoItem tipo, int curacion, int cantidad) {
        super(nombre, descripcion, precio, tipo, curacion, cantidad);
    }

    @Override
    public void usar(Personajes personaje) {

        personaje.curar(getValorEfecto());

        System.out.println(personaje.getName() + " recupera " + getValorEfecto() + " de vida");
    }
}