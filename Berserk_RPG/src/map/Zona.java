package map;

import java.util.ArrayList;
import java.util.List;
import enemies.Enemigo; 

public abstract class Zona {
    private String nombre;
    private List<Enemigo> enemigos;

    public Zona(String nombre) {
        this.nombre = nombre;
        this.enemigos = new ArrayList<>();
        inicializarEnemigos();  // Cada Senda define sus propios monstruos
    }

 // Método que cada Senda rellenará con sus 3 combates
    
    protected abstract void inicializarEnemigos();
    
 // Métodos para que el Narrador obtenga los textos
    
    public abstract String getIntroduccion();
    public abstract String getDialogoIntermedio(); // Para presentar al 2º enemigo
    public abstract String getDialogoFinal();      // Para el Jefe/Horda de la senda

    public List<Enemigo> getEnemigos() { 
    	return enemigos; }
    
    public String getNombre() { 
    	return nombre; }
}