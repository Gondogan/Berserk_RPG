package game;

import java.util.Scanner;
import map.Zona;

   
    // Clase encargada de la interfaz de texto y la narrativa.
    // Su función es mostrar los mensajes de las zonas y pausar el flujo para el jugador.
    
public class Narrador {

    private static Scanner sc = new Scanner(System.in);

   //Presentacion de senda
    
    public void presentarZona(Zona zona) {
        System.out.println("\n====================================================");
        System.out.println("          " + zona.getNombre().toUpperCase());
        System.out.println("====================================================");
        imprimirEfectoNarrativo(zona.getIntroduccion());
        esperarEnter();
    }

    
     // Muestra un diálogo estándar durante el progreso de la senda.
     
    public void decirDialogo(String mensaje) {
        System.out.println("\n[NARRADOR]:");
        imprimirEfectoNarrativo(mensaje);
        esperarEnter();
    }

    // Dialogos especiales para enemigos improtantes
    
    public void hablarPersonaje(String nombre, String mensaje) {
        System.out.println("\n--- " + nombre.toUpperCase() + " ---");
        System.out.println("\"" + mensaje + "\"");
        esperarEnter();
    }

    
     // Imprime el texto con un pequeño retraso para simular una narración real.
     
    private void imprimirEfectoNarrativo(String texto) {
        // En lugar de un println a secas, añadimos un poco de margen
        System.out.println("  " + texto);
    }

    
     // Detiene el juego hasta que el jugador presiona la tecla Enter.
     
    public void esperarEnter() {
        System.out.println("\n(Presiona ENTER para continuar...)");
        sc.nextLine();
    }

    
     // Limpia visualmente la consola (simulado con saltos de línea).
     
    public void limpiarConsola() {
        for (int i = 0; i < 50; i++) System.out.println();
    }
}