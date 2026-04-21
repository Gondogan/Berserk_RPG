package game;

/**
 * Clase que gestiona el estado global del juego
 */
public class EstadoJuego {

    private FaseJuego faseActual;
    private int combatesGanados;
    private boolean sendaFinalDesbloqueada;

    private static final int COMBATES_PARA_FINAL = 4;

    public EstadoJuego() {
        this.faseActual = FaseJuego.INICIO;
        this.combatesGanados = 0;
        this.sendaFinalDesbloqueada = false;
    }

    /**
     * Se llama cuando el jugador gana una senda o combate importante
     */
    public void registrarVictoria() {
        combatesGanados++;
        comprobarDesbloqueos();
    }

    /**
     * Comprueba si se desbloquea la senda final
     */
    private void comprobarDesbloqueos() {
        if (!sendaFinalDesbloqueada && combatesGanados >= COMBATES_PARA_FINAL) {
            sendaFinalDesbloqueada = true;
            System.out.println("\n🔥 Has desbloqueado la SENDA FINAL 🔥");
        }
    }

    public void iniciarSendaNormal() {
        faseActual = FaseJuego.SENDA1;
    }

    public void iniciarSendaFinal() {
        if (sendaFinalDesbloqueada) {
            faseActual = FaseJuego.SENDA_FINAL;
        } else {
            System.out.println("La senda final aún está bloqueada.");
        }
    }

    public void marcarVictoriaFinal() {
        faseActual = FaseJuego.VICTORIA;
    }

    /**
     * Marca el juego como terminado por derrota
     */
    public void marcarDerrota() {
        faseActual = FaseJuego.DERROTA;
    }

    public FaseJuego getFaseActual() {
        return faseActual;
    }

    public int getCombatesGanados() {
        return combatesGanados;
    }

    public boolean isSendaFinalDesbloqueada() {
        return sendaFinalDesbloqueada;
    }

    // reinicia las stats del gameplay
	public void reiniciar() {
		this.faseActual = FaseJuego.INICIO;
		this.combatesGanados = 0;
		this.sendaFinalDesbloqueada = false;
		
	}
}