package map;

import enemies.Enemigo;
import enemies.EnemigoBasico;

public class Senda2 {

    // Array fijo con los 2 enemigos básicos de la Senda 2
    private final Enemigo[] enemigos = {
        new EnemigoBasico("Troll", 150, 28, 12, 10, "Mazazo"),
        new EnemigoBasico("Sátiro Corrupto", 180, 32, 15, 10, "Cornada")
    };

    public Enemigo[] getEnemigos() {
        return enemigos;
    }
}