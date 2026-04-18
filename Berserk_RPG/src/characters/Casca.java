package characters;

import skills.Ataque;
import skills.HabilidadEspecial;


public class Casca extends Personajes {

    // CONSTRUCTOR HP, ATK, DEF, VEL, ENERGÍA
    public Casca() {
    	super("Casca", 120, 30, 45, 45, 111);
    }

    // --- GETTERS Y SETTERS ---
    // Heredados de Personajes.

    // MÉTODOS VARIOS

    @Override
    protected void inicializarHabilidades() {
    	// Aquí es donde llenamos la caja vacía de habilidades y metemos las d Casca.
    	
        this.getHabilidades().add(new Ataque("Corte de Oficial", "Ataque táctico equilibrado.", 1.0));
        this.getHabilidades().add(new HabilidadEspecial("Patada de Desvío", "Golpe que aturde al enemigo.", 1.4, 15));
        this.getHabilidades().add(new HabilidadEspecial("Carga de la Banda", "Ataque en equipo devastador.", 2.5, 35));
    }

    // Supervivencia.Es como Robustez de PkM esta se dispara cuando Casca recibe un golpe que debería derrotarla.
    
    @Override
    public void activarPasiva() {
        // Si la vida es 0 o menos y aún no ha usado la pasiva en este combate
        if (!getPasivaUsada() && getVidaActual() <= 0) {
            
            setVidaActual(1); // Le devolvemos 1 punto de vida
            setPasivaUsada(true); // Gastamos el cartucho para este combate
            
            System.out.println("\n[PASIVA: Supervivencia]");
            System.out.println("¡Casca se mantiene en pie por puro instinto! Sobrevive con 1 HP.");
        }
    }

  
    // Sobrescribimos recibirDanio para que, tras restar la vida, el juego compruebe automáticamente si debe saltar la pasiva.
     
    @Override
    public void recibirDanio(int danio) {
        // Llamamos al método del padre para que reste la vida normalmente
        super.recibirDanio(danio);
        
        // Justo después de recibir el golpe, chequeamos la pasiva
        if (getVidaActual() <= 0) {
            activarPasiva();
        }
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



