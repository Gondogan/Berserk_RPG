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

    /*   DIALOGOOS COMENTADOOOSSS
     * 
    public void iniciarSenda() throws InterruptedException {
        // --- 1. INTRODUCCIÓN DE LA ZONA FINAL ---
        narrador.limpiarConsola();
        narrador.decirDialogo("El tiempo se ha detenido aquí, en el nexo entre la vida y la condenación.");
        narrador.decirDialogo("Caminas sobre un mar de espadas oxidadas que brotan de una tierra saturada de sangre antigua.");
        narrador.decirDialogo("Sobre ti, un cielo de ébano es desgarrado por un eclipse eterno que no da luz, solo sombra.");
        narrador.decirDialogo("El aire es pesado, cargado con el lamento de millones de almas sacrificadas por la ambición.");

        // --- 2. DIÁLOGO ANTES DE LUCHAR CONTRA SKULL KNIGHT ---
        narrador.hablarPersonaje("Skull Knight", "Luchador contra el destino... Forcejeador que se arrastra en la oscuridad.");
        narrador.hablarPersonaje("Skull Knight", "Tu marca sangra con una intensidad que no veía desde hace eras. ¿Buscas desafiar la causalidad o simplemente has venido a que tu alma sea devorada por el abismo?");
        narrador.hablarPersonaje("Skull Knight", "Empuña tu acero, mortal, pues aquí la muerte no es el final, sino una eternidad de tormento. ¡En guardia!");

        // COMBATE FASE 1: Contra la Horda de Almas
        Combate combateFase1 = new Combate(jugador, new Enemigo[]{jefes[0]});
        ResultadoCombate resultado1 = combateFase1.iniciarCombate();

        if (resultado1 != ResultadoCombate.VICTORIA) {
            // --- 4. FRASE CUANDO ÉL ME MATE (DERROTA FASE 1) ---
            narrador.hablarPersonaje("Skull Knight", "Incluso aquel que lucha contra el flujo del río termina ahogándose en su corriente. Tu lucha termina aquí, un simple fragmento de cristal roto en el gran esquema de la causalidad.");
            estadoJuego.marcarDerrota();
            return;
        }

        // --- 3. DIÁLOGO CUANDO ACTIVA LA FASE 2 ---
        narrador.decirDialogo("El caballero sobre el corcel negro alza su espada, y el Intersticio parece agrietarse bajo su presencia.");
        narrador.hablarPersonaje("Skull Knight", "¡No está mal! Has resistido el embate de la horda, pero el verdadero peso del destino no se mide en carne, sino en voluntad.");
        narrador.hablarPersonaje("Skull Knight", "¡Observa cómo la realidad se doblega ante mi acero! ¡Siente el peso de la eternidad!");

        // COMBATE FASE 2: Contra Skull Knight real
        Combate combateFase2 = new Combate(jugador, new Enemigo[]{jefes[1]});
        ResultadoCombate resultado2 = combateFase2.iniciarCombate();

        if (resultado2 == ResultadoCombate.VICTORIA) {
            // --- 5. FRASE CUANDO YO LE DERROTE ---
            narrador.hablarPersonaje("Skull Knight", "Has forzado una grieta en lo absoluto. El destino ha sido herido, pero no te engañes... esto es solo el comienzo.");
            narrador.hablarPersonaje("Skull Knight", "Sigue adelante, forcejeador. El verdadero Eclipse apenas empieza a proyectar su sombra sobre este mundo.");
            
            narrador.decirDialogo("Skull Knight se desvanece en la niebla espectral, dejando tras de sí el eco de una batalla que resonará por siempre.");
            estadoJuego.registrarVictoria();
        } else {
            // --- 4. FRASE CUANDO ÉL ME MATE (DERROTA FASE 2) ---
            narrador.hablarPersonaje("Skull Knight", "Tu voluntad se ha quebrado antes que el destino. Descansa ahora en el olvido, pues tu nombre no será más que un susurro en la tormenta.");
            estadoJuego.marcarDerrota();
        }
    }
}
    */
}