package inventory;

import characters.Personajes;

public class MejoraArma extends Item {

    public MejoraArma(String nombre, String descripcion, int precio, int danioExtra) {
        super(nombre, descripcion, precio, TipoItem.Mejora_Arma, danioExtra, 1);
    }

    @Override
    public void usar(Personajes personaje) {

        personaje.setDanioBase(personaje.getDanioBase() + getValorEfecto());

        System.out.println(personaje.getName() + " aumenta su ataque en " + getValorEfecto());
    }
}