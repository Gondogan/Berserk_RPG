package characters;

import skills.Ataque;
import skills.HabilidadEspecial;
import skills.HabilidadPasiva;


public class Guts extends Personajes {

    public Guts() {
        super("Guts", 120, 30, 20, 10, 50);
    }

    // Aquí es donde llenamos la caja vacía de habilidades y metemos las d Guts.
    @Override
    protected void inicializarHabilidades() {
        this.getHabilidades().add(new HabilidadPasiva("Marca del Sacrificio", "Aumenta el daño a baja salud."));
        this.getHabilidades().add(new Ataque("Tajo Matadragones", "Un barrido horizontal muy pesado.", 1.2));
        this.getHabilidades().add(new HabilidadEspecial("Corte Ciclón", "Gira dañando con gran fuerza.", 1.8, 25));
        this.getHabilidades().add(new HabilidadEspecial("Cañón de Brazo", "Disparo a quemarropa.", 3.5, 45));
    }

    	// Guts prioriza Vida, Ataque bruto y Defensa. Sacrifica Velocidad.
    
    @Override
    protected void aplicarBonusDeSubidaNivel() {
              
        this.setVidaMaxima(this.getVidaMaxima() + 20);
        this.setDanioBase(this.getDanioBase() + 6);
        this.setDefensa(this.getDefensa() + 5);
        this.setVelocidad(this.getVelocidad() + 1);
        this.setEnergiaMaxima(this.getEnergiaMaxima() + 5);
       
        System.out.println("Guts se vuelve más letal y resistente, como un auténtico monstruo.");
    }
}