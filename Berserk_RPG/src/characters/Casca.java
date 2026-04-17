package characters;

import skills.Ataque;
import skills.HabilidadEspecial;
import skills.HabilidadPasiva; 

public class Casca extends Personajes {

    // CONSTRUCTOR 
    public Casca() {
       super("Casca", 100, 20, 15, 20, 60);
    }

    // --- GETTERS Y SETTERS ---
    // Heredados de Personajes.

    // MÉTODOS VARIOS

    @Override
    protected void inicializarHabilidades() {
    	// Aquí es donde llenamos la caja vacía de habilidades y metemos las d Casca.
    	
        this.getHabilidades().add(new HabilidadPasiva("Supervivencia", "Sobrevive con 1 HP una vez por combate."));
        this.getHabilidades().add(new Ataque("Corte de Oficial", "Ataque táctico equilibrado.", 1.0));
        this.getHabilidades().add(new HabilidadEspecial("Patada de Desvío", "Golpe que aturde al enemigo.", 1.4, 15));
        this.getHabilidades().add(new HabilidadEspecial("Carga de la Banda", "Ataque en equipo devastador.", 2.5, 35));
    }

    		// Casca es equilibrada, todo sube un poco
    
    @Override
    protected void aplicarBonusDeSubidaNivel() {
       
        this.setVidaMaxima(this.getVidaMaxima() + 15);
        this.setDanioBase(this.getDanioBase() + 4);
        this.setDefensa(this.getDefensa() + 3);
        this.setVelocidad(this.getVelocidad() + 3);
        this.setEnergiaMaxima(this.getEnergiaMaxima() + 8);
        
        System.out.println("Casca afina sus dotes de liderazgo y combate.");
    }
}