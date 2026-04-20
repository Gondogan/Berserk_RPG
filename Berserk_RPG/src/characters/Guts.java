package characters;

import skills.Ataque;
import skills.HabilidadEspecial;

public class Guts extends Personajes {
	
	// Atributo específico de Guts: multiplicador para la Marca del Sacrificio
    
    private double bonoFuria = 1.25;

    public Guts() {
    	
    	// Stats de la carta: HP, ATK, DEF, VEL, ENERGÍA
        super("Guts", 250, 35, 45, 50, 100);
    }
	
    // Aquí es donde llenamos la caja vacía de habilidades y metemos las de Guts.
    @Override
    protected void inicializarHabilidades() {
    	this.getHabilidades().add(new Ataque("Cañón de Brazo", "Disparo a quemarropa.", 1.2));
    	this.getHabilidades().add(new HabilidadEspecial("Corte Ciclón", "Gira dañando con gran fuerza.", 1.8, 25));
    	this.getHabilidades().add(new HabilidadEspecial("Tajo Matadragones", "Un barrido horizontal muy pesado.",3.5, 45));

    }

    
     //Lógica de la pasiva: Marca del Sacrificio.
     //Se activa cuando la vida baja del 20%.
     
    @Override
    public void activarPasiva() {
        // Comprobamos si la vida es baja y si no se ha usado ya en este combate
        if (!getPasivaUsada() && getVidaActual() < (getVidaMaxima() * 0.20)) {
            
            // Aplicamos el bono de daño usando nuestro atributo específico
            int nuevoDanio = (int) (getDanioBase() * bonoFuria);
            setDanioBase(nuevoDanio);
            
            // Marcamos como usada para el control del combate
            setPasivaUsada(true);
            
            System.out.println("\n[PASIVA]: ¡Marca del Sacrificio!");
            System.out.println("Guts ignora el dolor y su ataque aumenta un 25%.");
        }
    }
    
    //Revertimos el bono al final del combate para no acumular daño infinito entre diferentes peleas.
    
    @Override
    public void resetearPasiva() {
        if (getPasivaUsada()) {
            // Dividimos por el bono para volver al daño base original
            setDanioBase((int) (getDanioBase() / bonoFuria));
        }
        // Llamamos al reset del padre para poner el booleano en false
        super.resetearPasiva();
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