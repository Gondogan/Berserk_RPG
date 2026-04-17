package skills;

import characters.Personajes;


 // Hechizo que hace daño (base para magia)

//  EXPLICAR PARA QUE SE UTILIZA
 
public class Hechizo extends Habilidades {

    public Hechizo(String nombre, String descripcion, double multiplicador, int costeEnergia) {
        super(nombre, descripcion, multiplicador, costeEnergia);
    }

    @Override
    public void usar(Personajes atacante, Personajes objetivo) {

        if (!atacante.gastarEnergia(getCosteEnergia())) {
            return;
        }

        int danio = (int) (atacante.getDanioBase() * getMultiplicadorDanio());

        System.out.println(atacante.getName() + " lanza " + getNombre());

        objetivo.recibirDanio(danio);
    }
}