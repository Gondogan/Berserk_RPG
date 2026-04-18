package map;

import enemies.Enemigo;
import enemies.EnemigoBasico;

public class Senda1 {

    
    private final Enemigo[] enemigos = {
        new EnemigoBasico("Soldado Kushan", 80, 15, 5, 10,"Navajazo"),
        new EnemigoBasico("Explorador Pishacha", 110, 22, 8, 12, "Golpe rápido")
    };

    public Enemigo[] getEnemigos() {
        return enemigos;
    }
}