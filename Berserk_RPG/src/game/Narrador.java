package game;

import java.util.Scanner;
import map.Zona;

   
    // Clase encargada de la interfaz de texto y la narrativa.
    // Su función es mostrar los mensajes de las zonas y pausar el flujo para el jugador.
    
public class Narrador {

	private static Scanner sc = new Scanner(System.in);
	private static final String RED = "\u001B[31m";  //ASCII ROJO
    private static final String RESET = "\u001B[0m";  //RESETEO ASCII
    
    
     //Muestra un diálogo estándar con efecto de máquina de escribir.
     //Realiza una pausa automática al finalizar.
     
    public void decirDialogo(String mensaje) throws InterruptedException {
        System.out.println("\n[NARRADOR]:");
        imprimirEfectoNarrativo(mensaje);
        Thread.sleep(1200); 
    }

    // Diálogos de personajes con efecto de mñaquina de escribir
    
    public void hablarPersonaje(String nombre, String mensaje) throws InterruptedException {
        System.out.println("\n--- " + nombre.toUpperCase() + " ---");
        System.out.print("  \"");
        imprimirEfectoNarrativo(mensaje);
        System.out.println("\"");
        Thread.sleep(1500);
    }

    // Método para escribir normal los ASCIIS
    
    public void imprimirAscii(String ascii) {
        System.out.println(ascii);
    }

    
    // EFECTO MÁQUINA DE ESCRIBIR: con un retraso de 30ms.
     
    private void imprimirEfectoNarrativo(String texto) throws InterruptedException {
        System.out.print("  "); 
        for (int i = 0; i < texto.length(); i++) {
            System.out.print(texto.charAt(i));
            System.out.flush(); 
            Thread.sleep(30);   
        }
        System.out.println();
    }

    
     //Limpia la consola mediante saltos de línea.
     
    public void limpiarConsola() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
    
    
    
    public void imprimirAsciiRojo(String ascii) {
        System.out.println(RED + ascii + RESET);
    }
    
}