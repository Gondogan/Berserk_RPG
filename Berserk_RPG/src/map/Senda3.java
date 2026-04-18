package map;

import enemies.Enemigo;
import enemies.EnemigoBasico;

public class Senda3 {

    private final Enemigo[] enemigos = {
        new EnemigoBasico("Poseso", 220, 40, 18, 10, "Zarpazo"),
        new EnemigoBasico("Apóstol Menor", 260, 48, 22, 10, "Golpe demoníaco")
    };

    public Enemigo[] getEnemigos() {
        return enemigos;
    }
}