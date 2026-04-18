package inventory;

import characters.Personajes;

public class MejoraArmadura extends Item {

    public MejoraArmadura(String nombre, String descripcion, int precio, int defensaExtra) {
        super(nombre, descripcion, precio, TipoItem.Mejora_Armadura, defensaExtra, 1);
    }

    @Override
    public void usar(Personajes personaje) {

        personaje.setDefensa(personaje.getDefensa() + getValorEfecto());

        System.out.println(personaje.getName() + " aumenta su defensa en " + getValorEfecto());
    }
}