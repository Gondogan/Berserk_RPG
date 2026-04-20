package map;

import enemies.Enemigo;
import enemies.EnemigoBasico;
import game.Narrador;

public class Senda2 {

    // Array fijo con los 2 enemigos básicos de la Senda 2
    private final Enemigo[] enemigos = {
        new EnemigoBasico("Troll", 150, 28, 12, 10, "Mazazo"),
        new EnemigoBasico("Sátiro Corrupto", 180, 32, 15, 10, "Cornada")
    };

    public Enemigo[] getEnemigos() {
        return enemigos;
    }


    @Override
    public String getIntroduccion() {
        return "La niebla es tan espesa que parece sólida. Te has adentrado en un lugar donde " +
               "el mundo de los hombres y el reino de los espíritus se superponen violentamente.";
    }

    @Override
    public String getDialogoIntermedio() {
        return "Una risa estridente sacude las ramas. Una figura astada bloquea el sendero: " +
               "'Pobre alma perdida... tus miedos huelen tan dulces en este bosque'.";
    }

    @Override
    public String getDialogoFinal() {
        return "El cauce del río se alza, desafiando la gravedad, formando la silueta de una bestia líquida. " +
               "Un espíritu ancestral ha decidido que tu viaje termina en el fondo de estas aguas.";
    }
}





