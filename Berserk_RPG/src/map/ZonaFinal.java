package map;

import enemies.Enemigo;
import enemies.EnemigoBasico;

public class ZonaFinal {

    private final Enemigo[] enemigos = {
        new EnemigoBasico("Horda Muerta", 400, 55, 25, 10, "Asalto brutal"),
        new EnemigoBasico("Skull Knight", 500, 55, 50, 10, "Corte espectral")
    };

    public Enemigo[] getEnemigos() {
        return enemigos;
    }
}