package skills;

import characters.Personajes;

public class HabilidadEspecial extends Habilidades {

	public HabilidadEspecial(String nombre, String descripcion, double multiplicadorDanio, int costeEnergia) {
		super(nombre, descripcion, multiplicadorDanio, costeEnergia);
		
		
	}

    public void usar(Personajes atacante, Personajes objetivo) {
    	
    	// comprobamos que el atacante puede lanzar el hechizo mediante getCosteEnergia() de Habilidades
        if (!atacante.gastarEnergia(getCosteEnergia())) {
            return; //en caso de que no pueda, simplemente return, evitando errores nos saca al no poder usar el hechizo
        }
        
        // una vez comprobado que puede lanzarlo procedemos a calcular el daño realizado por el hechizo con su respectivo multiplicador, 
        // getMultiplicadorDanio() de Habilidades
        int danio = (int) (atacante.getDanioBase() * getMultiplicadorDanio());

        System.out.println(atacante.getName() + " lanza " + getNombre());

        objetivo.recibirDanio(danio);
    }

}
