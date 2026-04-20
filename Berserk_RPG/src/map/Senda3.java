package map;

import enemies.Enemigo;
import enemies.EnemigoBasico;

import java.util.ArrayList;

public class Senda3 {

	private ArrayList<EnemigoBasico> enemigos;

    public Senda3() {
        enemigos = new ArrayList<>();

        enemigos.add(new EnemigoBasico("Poseso", 220, 40, 18, 10, "Zarpazo"));
        enemigos.add(new EnemigoBasico("Apóstol Menor", 260, 48, 22, 10, "Golpe demoníaco"));
        enemigos.add(new EnemigoBasico("Gran Inquisidor Mozgus", 450, 60, 33, 10, "Muro de piedra"));
    }

    public ArrayList<EnemigoBasico> getEnemigos() {
        return enemigos;
    }
    
    
    @Override
    public String getIntroduccion() {
        return "El suelo bajo tus pies se siente blando, casi como carne. Estás cerca del epicentro " +
               "de un antiguo ritual; el hedor a azufre y desesperación es casi insoportable.";
    }

    @Override
    public String getDialogoIntermedio() {
        return "Una criatura de múltiples ojos te observa desde un pilar en ruinas. " +
               "'Tu marca... brilla con una intensidad deliciosa. El festín está por comenzar'.";
    }

    @Override
    public String getDialogoFinal() {
        return "Una figura imponente con rostro de piedra bloquea el camino: '¡En el nombre de la pureza absoluta, " +
               "tus pecados serán purgados con el fuego sagrado de la agonía!'";
    }
}
    
