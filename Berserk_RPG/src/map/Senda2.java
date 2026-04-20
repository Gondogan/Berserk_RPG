package map;

import enemies.Enemigo;
import enemies.EnemigoBasico;
import game.Narrador;
import java.util.ArrayList;

public class Senda2 {

	private ArrayList<EnemigoBasico> enemigos;

    public Senda2() {
        enemigos = new ArrayList<>();

        enemigos.add(new EnemigoBasico("Troll", 150, 28, 12, 10, "Mazazo"));
        enemigos.add(new EnemigoBasico("Sátiro Corrupto", 180, 32, 15, 10, "Cornada"));
        enemigos.add(new EnemigoBasico("Kelpie de las Sombras", 300, 45, 20, 10, "Carga oscura"));
    }

    public ArrayList<EnemigoBasico> getEnemigos() {
        return enemigos;
    }
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





