package enemies;

public class JefeFinalFase2 extends Enemigo {

    private String ataqueEspecial;
    private boolean enFuria = false;

    public JefeFinalFase2(String nombre, int vidaMax, int danioBase, int defensa, int velocidad, String ataqueEspecial) {
        super(nombre, vidaMax * 2, danioBase * 2, defensa * 2, velocidad);
        this.ataqueEspecial = ataqueEspecial;
    }

    
    @Override
    public int atacar() {
        int opcion = (int)(Math.random() * 3) + 1;

        int danio;

        if (opcion == 1 || opcion == 2) {
            danio = getDanioBase();
            System.out.println(getNombre() + " golpea con furia desatada.");
        } else {
            danio = getDanioBase() + 25;
            System.out.println(getNombre() + " usa " + ataqueEspecial + " devastador.");
        }

        danio = aplicarModoEnfado(danio);

        System.out.println("Daño total: " + danio);
        return danio;
    }

    //  MODO FURIA  (50%)
    private int aplicarModoEnfado(int danio) {
        if (!enFuria && getVidaAct() < getVidaMax() * 0.5) {
            enFuria = true;
            System.out.println( getNombre() + " entra en FURIA DESCONTROLADA!");
        }

        if (enFuria) {
            danio += 20; // más bonus que el jefe normal
        }

        return danio;
    }

    // 💀 MENSAJE MUERTE
    @Override
	public void mensajeMuerteJefe() {
        System.out.println(getNombre() + " ha sido completamente destruido...");
    }

    // 💰 RECOMPENSAS MÁS ALTAS
    @Override
    public int dropearOro() {
        return (int)(Math.random() * 40) + 50;
    }

    @Override
    public int dropearXp() {
        return (int)(Math.random() * 50) + 60;
    }

    // 🎬 PRESENTACIÓN
    public void presentarse() {
        System.out.println( getNombre() + " NO MUERAS!");
    }
}