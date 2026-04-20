package skills;

import characters.Personajes;

public class Ataque extends Habilidades {
	
	// Le pasamos al padre: nombre, descripción, multiplicador, y 0 de coste de energía.
    
    public Ataque(String nombre, String descripcion, double mult) {
        
        super(nombre, descripcion, mult, 0); 
    }
    
    @Override
    public void usar(Personajes atacante, Personajes objetivo) {
    	
    	int danio = (int) (atacante.getDanioBase() * getMultiplicadorDanio());

    	System.out.println(atacante.getName() + " lanza " + getNombre());

        objetivo.recibirDanio(danio);
    	
    }
}