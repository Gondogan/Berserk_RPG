package characters;

import skills.Ataque;
import skills.HabilidadEspecial;

public class Griffith extends Personajes {
    
    // Atributo específico de Griffith: probabilidad de activar la Presencia Divina (15%)
    private double probPresencia = 0.15;

    public Griffith() {
    	
    	 // CONSTRUCTOR HP, ATK, DEF, VEL, ENERGÍA
        super("Griffith", 200, 45, 35, 50, 120);
    }
    
    // Aquí es donde llenamos la caja vacía de habilidades y metemos las de Griffith.
    @Override
    protected void inicializarHabilidades() {
        this.getHabilidades().add(new Ataque("Corte Invisible", "Ataque veloz casi imperceptible.", 1.1));
        this.getHabilidades().add(new HabilidadEspecial("Estocada Perforante", "Atraviesa defensas con precisión.", 1.8, 20));
        this.getHabilidades().add(new HabilidadEspecial("Activación de Behelit", "Desata una presencia oscura abrumadora.", 2.5, 60));
    }

    // Lógica de la pasiva: Presencia Divina.
    // Los enemigos tienen un 15% de probabilidad de perder su turno al inicio.
    
    @Override
    public void activarPasiva() {
        // Usamos Math.random() para calcular si la pasiva tiene éxito basándonos en nuestro atributo
        if (!getPasivaUsada() && Math.random() < probPresencia) {
            
            // Marcamos como usada para que el controlador de combate sepa que este turno el enemigo no ataca
            setPasivaUsada(true);
            
            System.out.println("\n[PASIVA]: ¡Presencia Divina!");
            System.out.println("El enemigo queda paralizado por el aura de Griffith.");
        }
    }
    
    // En Griffith, el resetearPasiva es vital para que cada turno tenga una nueva oportunidad.
    @Override
    public void resetearPasiva() {
        // Simplemente llamamos al padre para poner pasivaUsada en false y volver a intentar el 15% el próximo turno.
        super.resetearPasiva();
    }
    
    // Griffith prioriza Velocidad, Ataque y Energía máxima.
    @Override
    protected void aplicarBonusDeSubidaNivel() {
        this.setVidaMaxima(this.getVidaMaxima() + 15);
        this.setDanioBase(this.getDanioBase() + 8);
        this.setDefensa(this.getDefensa() + 2);
        this.setVelocidad(this.getVelocidad() + 6);
        this.setEnergiaMaxima(this.getEnergiaMaxima() + 10);
       
        System.out.println("La ambición de Griffith crece, volviéndolo más rápido y letal.");
    }
}