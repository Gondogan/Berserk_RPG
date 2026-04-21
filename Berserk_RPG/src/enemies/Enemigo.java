package enemies;

public abstract class Enemigo {

			private String nombre;
			private int vidaAct;
			private int vidaMax;
			private int ataque;
			private int defensa;
			private int velocidad;
			private int danioBase;
			private boolean vivo;
			
			
			 public Enemigo(String nombre,int vidaAct, int vidaMax, int danioBase, int defensa, int velocidad, int ataque) {
			        this.nombre = nombre;
			        this.vidaMax = vidaMax;
			        this.vidaAct = vidaAct;
			        this.danioBase = danioBase;
			        this.defensa = defensa;
			        this.velocidad = velocidad;
			        this.vivo = true;	
			 }
			 
			 public Enemigo(String nombre, int vidaMax, int danioBase, int defensa, int velocidad) {
				    this.nombre = nombre;
				    this.vidaMax = vidaMax;
				    this.vidaAct = vidaMax;      // empieza con la vida al máximo
				    this.danioBase = danioBase;
				    this.defensa = defensa;
				    this.velocidad = velocidad;
			 }
			 
			 
			 public String getNombre() {
				 return nombre; }
			    public int getVidaAct() {
			    	return vidaAct; }
			    public int getVidaMax(){
			    	return vidaMax; }
			    public int getDanioBase() {
			    	return danioBase; }
			    public int getDefensa() {
			    	return defensa; }
			    public int getVelocidad() {
			    	return velocidad; }
			    public boolean isVivo() {
			    	return vivo; }
			    
			    public void setVidaAct(int VidaAct) {
			    	this.vidaAct = vidaAct;
			    }
			    public void setDanioBase(int DanioBase) {
			    	this.danioBase = danioBase;
			    } 
			    public void setDefensa(int defensa) {
			    	this.defensa = defensa;
			    }
			    public void setVelocida(int velocidad) {
			    	this.velocidad = velocidad;
			    }
			 
			 // Saber si está vivo
			    public boolean estaVivo() {
			        return vidaAct > 0;
			    }
			    // Restamos el ataque que se recibe con la defensa de nuestro enemigo
			    private int calcularDanioRecibido(int ataqueEntrante) {
			        return Math.max(0, ataqueEntrante - defensa);
			    }
			    
			    
			    //  El enemigo recibe daño (defensa > ataque => 0)
			    public void recibirDanio(int ataqueEntrante) {
			        int danioReal = calcularDanioRecibido(ataqueEntrante); 
			        vidaAct = Math.max(0, vidaAct - danioReal);

			        System.out.println(nombre + " recibe " + danioReal + " de daño. (HP " + vidaAct + "/" + vidaMax + ")");

			        if (vidaAct == 0) {
			            System.out.println( nombre + " ha sido derrotado.");
			        }
			    }

			    //  Crítico  (10% -> si sale 1 en 1..10)
			    private int aplicarCritico(int danio) {
			        int numero = (int)(Math.random() * 10) + 1; // 1..10

			        if (numero == 1) {
			            System.out.println("¡Crítico!");
			            danio = danio * 2; // x2
			        }

			        return danio;
			    }
			  
			    // Ataque del enemigo (devuelve el daño para que Combate lo aplique)
			    public int atacar() {
			        int danio = danioBase;
			        danio = aplicarCritico(danio);

			        System.out.println(nombre + " ataca e intenta hacer " + danio + " de daño.");
			        return danio;
			    }

			   

			    // Oro aleatorio (5..15)
			    public int dropearOro() {
			        int min = 5;
			        int max = 15;
			        return (int)(Math.random() * (max - min + 1)) + min;
			    }

			    // XP aleatorio (10..25)
			    public int dropearXp() {
			        int min = 10;
			        int max = 25; 
			        return (int)(Math.random() * (max - min + 1)) + min;
			    }
			 
			    //Creo un mensaje de muerte solo para los jefes finales
			    public void mensajeMuerteJefe() {
			    	
			    }
			 
			 
			    // ================= TO STRING =================

			    @Override
			    public String toString() {

			        return nombre + " | HP: " + vidaAct + "/" + vidaMax
			                + " | ATK: " + danioBase
			                + " | DEF: " + defensa
			                + " | VEL: " + velocidad;
			 
			    }
				
			 
			 
			 
			 
}

