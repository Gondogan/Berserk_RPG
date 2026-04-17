package skills;

public abstract class Habilidades {
    
    // ATRIBUTOS
	
    private String nombre;
    private String descripcion;
    private double multiplicadorDanio;
    private int costeEnergia;

    // CONSTRUCTOR 
    
    public Habilidades(String nombre, String descripcion, double multiplicadorDanio, int costeEnergia) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.multiplicadorDanio = multiplicadorDanio;
        this.costeEnergia = costeEnergia;
    }

    // GETTERS Y SETTERS 
    
    public String getNombre() { 
    	return nombre; }
    
    public String getDescripcion() { 
    	return descripcion; }
    
    public double getMultiplicadorDanio() { 
    	return multiplicadorDanio; }
    
    public int getCosteEnergia() { 
    	return costeEnergia; }

    // MÉTODOS VARIOS
    
    @Override
    public String toString() {
        return "[" + nombre + "] Coste: " + costeEnergia + " - " + descripcion;
    }
}