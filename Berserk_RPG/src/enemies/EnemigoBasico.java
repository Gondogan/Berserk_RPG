package enemies;

public class EnemigoBasico extends Enemigo {

	 private String nombreAtaque;   // cada enemigo básico tendrá el suyo
	    private int bonusFuerte;       // para el ataque fuerte opcional
	    
	public EnemigoBasico(String nombre, int vidaAct, int vidaMax, int danioBase, int defensa, int velocidad,
			int ataque) {
		super(nombre, vidaAct, vidaMax, danioBase, defensa, velocidad, ataque);
		
		this.nombreAtaque = nombreAtaque;
        this.bonusFuerte = 5; // puedes dejarlo fijo
	}
	
	
	public EnemigoBasico(String nombre, int vidaMax, int danioBase, int defensa, int velocidad, String nombreAtaque) {
	    super(nombre, vidaMax, danioBase, defensa, velocidad);
	    this.nombreAtaque = nombreAtaque;
	}

	@Override
	public int atacar() {
		
	    int danio = getDanioBase();
	    System.out.println(getNombre() + " usa " + nombreAtaque + " e inflige " + danio + " de daño.");
	    return danio;
	
	        }
	
	@Override
	public int dropearOro() {
	    return (int)(Math.random() * 8) + 3; // 3..10
	}

	@Override
	public int dropearXp() {
	    return (int)(Math.random() * 11) + 5; // 5..15
	}
	
	public void mostrarInfo() {
	    System.out.println("ENEMIGO: " + getNombre()
	        + " | HP: " + getVidaAct() + "/" + getVidaMax()
	        + " | ATK: " + getDanioBase()
	        + " | DEF: " + getDefensa()
	        + " | VEL: " + getVelocidad());
	}

     
	
	
	
	
	
	
	
	
	
	
	
	    }
	