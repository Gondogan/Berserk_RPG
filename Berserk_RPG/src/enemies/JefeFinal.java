package enemies;

public class JefeFinal extends Enemigo {

    private String ataqueEspecial;
    private boolean furia = false;

    public JefeFinal(String nombre, int vidaMax, int danioBase, int defensa, int velocidad, String ataqueEspecial) {
        super(nombre, vidaMax, danioBase, defensa, velocidad);
        this.ataqueEspecial = ataqueEspecial;
        this.furia = furia;
    }
    
    


    // Creamos un ataque con mas daño que los básicos
    @Override
    public int atacar() {
        int opcion = (int)(Math.random() * 3) + 1;

        int danio; 

        if (opcion == 1 || opcion == 2) {
            // Ataque normal
            danio = getDanioBase();
            System.out.println(getNombre() + " golpea con fuerza e inflige " + danio + " de daño.");
        } else {
            // Ataque especial
            danio = getDanioBase() + 15;
            System.out.println(getNombre() + " usa " + ataqueEspecial + " e inflige " + danio + " de daño.");
        }

       
        danio = aplicarModoEnfado(danio);

        System.out.println("Daño total: " + danio);

        return danio; 
    }

    // 
    @Override
    public void mensajeMuerteJefe() {
        System.out.println( getNombre() + " cae derrotado tras una dura batalla...");
    }

    // Le ponemos un numero mas alto para que te de mas xp
    @Override
    public int dropearOro() {
        return (int)(Math.random() * 20) + 15; // 15..34
    }

    @Override
    public int dropearXp() {
        return (int)(Math.random() * 30) + 20; // 20..49
    }

    // Hacemos una entrada para los jefes
    public void presentarse() {
        System.out.println(" ¡EL JEFE " + getNombre() + " HA APARECIDO! TEN CUIDADO");
    }
    
    
    
    
    private int aplicarModoEnfado(int danio) {
        if (!furia && getVidaAct() < getVidaMax() * 0.25) {
            furia = true;
            System.out.println( getNombre() + " entra en MODO FURIA! ");
        }

        if (furia) {
            danio += 10;
        }

        return danio;
    }
    
    
    
    
}
