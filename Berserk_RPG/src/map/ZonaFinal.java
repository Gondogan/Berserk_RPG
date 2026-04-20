package map;

import java.util.Scanner;

import characters.Personajes;
import enemies.Enemigo;
import enemies.EnemigoBasico;
import enemies.JefeFinalFase2;
import game.EstadoJuego;
import store.GestorRecompensa;

public class ZonaFinal {

    private final JefeFinalFase2[] jefes = {
        new JefeFinalFase2("Horda Muerta", 400, 55, 25, 10, "Asalto brutal"),
        new JefeFinalFase2("Skull Knight", 500, 55, 50, 10, "Corte espectral")
    };

   

	public Enemigo[] getEnemigos() {
        return jefes;
    }
    private Personajes jugador;
    private EstadoJuego estadoJuego;
    private GestorRecompensa gestorRecompensa;
    private Scanner scanner;

    private static final int TOTAL_COMBATES = 3;

    public ZonaFinal(Personajes jugador, EstadoJuego estadoJuego) {
        this.jugador = jugador;
        this.estadoJuego = estadoJuego;
        this.gestorRecompensa = new GestorRecompensa();
        this.scanner = new Scanner(System.in);
    }

	public void iniciarSenda() {
		// TODO Auto-generated method stub
		
	}

    /*
    @Override
    public String getIntroduccion() {
        return "El tiempo parece detenerse. Te encuentras en una llanura infinita de espadas clavadas en el suelo, " +
               "bajo una luna de sangre que nunca se oculta. El destino te ha traído ante su guardián.";
    }

    @Override
    public String getDialogoIntermedio() {
        return "De la tierra removida, cientos de manos esqueléticas emergen. No tienen voluntad propia, " +
               "solo el mandato de probar si eres digno de enfrentarte al soberano de este reino.";
    }

    @Override
    public String getDialogoFinal() {
        return "El caballero sobre el corcel negro desenvaina su espada envuelta en espinas. Su voz suena como " +
               "mil tumbas abriéndose: 'Luchador contra el destino... para desafiar a la Mano de Dios, " +
               "primero debes demostrar que puedes vencer a la Muerte misma. ¡En guardia!'";
    }

    
    //Diálogo especial para el momento del renacimiento de Skull Knight.
     
    public String getDialogoFase2() {
        return "El Caballero se levanta de nuevo, su armadura emite un brillo espectral y los muertos " +
               "se reensamblan a su lado formando una guardia pretoriana. 'No está mal, forcejeador. " +
               "Pero la verdadera lucha contra la causalidad nunca es solitaria. ¡Siente el peso de la eternidad!'";
    }
    */
}