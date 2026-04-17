package characters;

import java.util.ArrayList;
import java.util.List;

import inventory.Inventario;
import skills.Habilidades;

public abstract class Personajes {

    // Atributos de los Personajes
    
    private String name;
    private int vidaActual;
    private int vidaMaxima;
    private int danioBase;
    private int defensa;
    private int velocidad;
    private int energiaActual;
    private int energiaMaxima;
    private int nivel;
    private int experiencia;
    private int oro;
    private List<Habilidades> habilidades;
    private Inventario inventario;
    private EstadoPersonaje estadoActual;
    private boolean vivo;
    private boolean pasivaUsada = false;

    // Constructor
    public Personajes(String name, int vidaMaxima, int danioBase, int defensa, int velocidad, int energiaMaxima) {

        this.name = name;
        this.vidaMaxima = vidaMaxima;
        this.vidaActual = vidaMaxima;            // comienza con la vida al máximo
        this.danioBase = danioBase;
        this.defensa = defensa;
        this.velocidad = velocidad;
        this.energiaMaxima = energiaMaxima;
        this.energiaActual = energiaMaxima;      // comienza con la energía al máximo

        this.nivel = 1;
        this.experiencia = 0;
        this.oro = 0;
        this.vivo = true;
        this.pasivaUsada = false;
        
        this.estadoActual = EstadoPersonaje.NORMAL; //prueba

        // inicializa habilidades e inventario
        this.inventario = new Inventario();
        this.habilidades = new ArrayList<>();

        inicializarHabilidades();
    }

    // Métodos abstractos
    protected abstract void inicializarHabilidades();
    protected abstract void aplicarBonusDeSubidaNivel();
    public abstract void activarPasiva();

    // ================= VIDA =================

    public void curar(int cantidad) {

        if (cantidad <= 0) {
            return;
        }

        // nos aseguramos que la vida actual nunca supere la vida máxima del personaje evitando crear método extra
        vidaActual = Math.min(vidaActual + cantidad, vidaMaxima);
    }

    public void recibirDanio(int danio) {

        int danioReal = calcularDanioReal(danio);            // calculamos el daño real teniendo en cuenta defensa

        danioReal = aplicarModificadorEstado(danioReal);     // comprobamos si el estado modifica el daño (ej: vulnerable)

        vidaActual = Math.max(0, vidaActual - danioReal);    // evitamos que la vida sea negativa

        if (vidaActual == 0) {
            this.vivo = false;                               // comprobamos si el personaje ha muerto
        }

        System.out.println(name + " recibe " + danioReal + " puntos de daÃ±o");

        if (!this.vivo) {
            System.out.println(name + " ha sido derrotado...");
        }
    }

    private int calcularDanioReal(int danio) {

        // evitamos que el daño sea 0 â†’ mánimo 1
        return Math.max(1, danio - this.defensa);
    }

    private int aplicarModificadorEstado(int danio) {

        // si está vulnerable recibe más daño
        if (this.estadoActual == EstadoPersonaje.VULNERABLE) {
            return (int) (danio * 1.5);      // casteamos a int tras multiplicar
        }

        return danio;
    }

 // ================= HABILIDADES =================

    public void mostrarHabilidades() {

        System.out.println("\n--- HABILIDADES ---");

        for (int i = 0; i < habilidades.size(); i++) {
            System.out.println((i + 1) + ". " + habilidades.get(i));
        }
    }

    public void usarHabilidadContra(int indice, Personajes objetivo) {

        if (indice < 0 || indice >= habilidades.size()) {
            System.out.println("Selección inválida.");
            return;
        }

        Habilidades habilidad = habilidades.get(indice);

        habilidad.usar(this, objetivo);
    }
    
    // ================= ENERGÃ�A =================

    public boolean gastarEnergia(int coste) {

        // comprobamos que tenga suficiente energía para usar la habilidad
        if (energiaActual < coste) {
            System.out.println(name + " no tiene suficiente energÃ­a.");
            return false;
        }

        energiaActual -= coste;      // restamos energÃ­a
        return true;
    }

    public void recuperarEnergia(int cantidad) {

        // controlamos que no supere el máximo ni sea negativa
        if (cantidad <= 0) {
            return;
        }

        energiaActual = Math.min(energiaActual + cantidad, energiaMaxima);
    }
    
    public void resetearPasiva() {
        this.pasivaUsada = false;
    }

    // ================= EXPERIENCIA =================

    public void ganarExperiencia(int xp) {

        if (xp <= 0) {
            return;
        }

        this.experiencia += xp;
        System.out.println(name + " gana " + xp + " puntos de experiencia.");

        // comprobamos si sube de nivel
        if (experiencia >= calcularXpNecesaria()) {
            subirNivel();
        }
    }

    private int calcularXpNecesaria() {

        // fórmula simple de subida de nivel
        return nivel * 100;
    }

    private void subirNivel() {
        this.nivel++;
        this.experiencia = 0;

        aplicarBonusDeSubidaNivel(); // 1º Crecemos los stats (Ej: Max HP pasa de 100 a 125)
        
        restaurarVidaCompleta();     // 2º Curamos al tope (Te curas hasta los 125)
        restaurarEnergiaCompleta();

        mostrarMensajeSubidaNivel();
    }

    private void restaurarVidaCompleta() {

        this.vidaActual = this.vidaMaxima;     // restaura vida al máximo
    }

    private void restaurarEnergiaCompleta() {

        this.energiaActual = this.energiaMaxima;  // restaura energía al máximo
    }

    private void mostrarMensajeSubidaNivel() {

        System.out.println("-- " + name + " ha subido de nivel! \nSu nivel actual es de " + nivel + "! --");
    }

    // ================= ORO =================

    public void ganarOro(int cantidad) {

        if (cantidad <= 0) {
            return;
        }

        this.oro += cantidad;
        System.out.println(name + " gana " + cantidad + " puntos de oro.");
    }
    
    public boolean perderOro(int cantidad) {
        if (cantidad <= 0) return false;
        
        // Si el personaje tiene oro suficiente...
        if (this.oro >= cantidad) {
            this.oro -= cantidad; // Le restamos el dinero
            
            System.out.println(this.getName() + " ha gastado " + cantidad + " de oro en la tienda.");
            
            return true;
        }
        
        // Si llega hasta aquí, es que no tenía dinero suficiente
        return false;
    }
    
    
 // ================= EQUIPAMIENTO ================= HAY QUE REPASARLOOOOO
    
    //HACER ESTA PARTE CUANDO ENTENEDAMOS LA LOGICA DE EUIPAMIENTO Y TIENDA
    
    
 // ================= GETTERS =================

    public String getName() {
        return name;
    }

    public int getVidaActual() {
        return vidaActual;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public int getDanioBase() {
        return danioBase;
    }

    public int getDefensa() {
        return defensa;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public int getEnergiaActual() {
        return energiaActual;
    }

    public int getEnergiaMaxima() {
        return energiaMaxima;
    }

    public int getNivel() {
        return nivel;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public int getOro() {
        return oro;
    }
    
    public void getVidaMaxima(int vidaMaxima) {
        this.vidaMaxima = vidaMaxima;
    }

    public void getEnergiaMaxima(int energiaMaxima) {
        this.energiaMaxima = energiaMaxima;
    }

    public List<Habilidades> getHabilidades() {
        return habilidades;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public EstadoPersonaje getEstadoActual() {
        return estadoActual;
    }

    public boolean isVivo() {
        return vivo;
    }

    public boolean getPasivaUsada() {
        return pasivaUsada;
    }
    // ================= SETTERS =================

    public void setName(String name) {
        this.name = name;
    }
    
    public void setVidaActual(int vidaActual) {
    	this.vidaActual = vidaActual;
    }

    public void setDanioBase(int danioBase) {
        this.danioBase = danioBase;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }
    
    public void setVidaMaxima(int vidaMaxima) {
        this.vidaMaxima = vidaMaxima;
    }

    public void setEnergiaMaxima(int energiaMaxima) {
        this.energiaMaxima = energiaMaxima;
    }

    public void setOro(int oro) {
        this.oro = oro;
    }

    public void setHabilidades(List<Habilidades> habilidades) {
        this.habilidades = habilidades;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public void setEstadoActual(EstadoPersonaje estadoActual) {
        this.estadoActual = estadoActual;
    }
    
    public void setPasivaUsada(boolean pasivaUsada) {
        this.pasivaUsada = pasivaUsada;
    }

    // ================= TO STRING =================

    @Override
    public String toString() {

        return name + " | HP: " + vidaActual + "/" + vidaMaxima
                + " | STMN: " + energiaActual + "/" + energiaMaxima
                + " | ATK: " + danioBase
                + " | DEF: " + defensa
                + " | VEL: " + velocidad;
    }
     
}