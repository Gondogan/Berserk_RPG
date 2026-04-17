package skills;

public class Ataque extends Habilidades {
	
	// Le pasamos al padre: nombre, descripción, multiplicador, y 0 de coste de energía.
    
    public Ataque(String nombre, String descripcion, double mult) {
        
        super(nombre, descripcion, mult, 0); 
    }
}