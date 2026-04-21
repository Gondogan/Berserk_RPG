package game;

import java.util.Scanner;
import map.Zona;

   
    // Clase encargada de la interfaz de texto y la narrativa.
    // Su función es mostrar los mensajes de las zonas y pausar el flujo para el jugador.
    
public class Narrador {

	private static Scanner sc = new Scanner(System.in);
	
	// Las 4 variables exactas para la espada
    public static final String ANSI_RED = "\u001B[31m";        // Empuñadura
    public static final String ANSI_REDPASTEL = "\u001B[91m";  // Filo/Estructura
    public static final String ANSI_RESET = "\u001B[0m";       // Resetear color
    public static final String NEGRITA = "\u001B[1m";          // Resaltar título
    

    //Método para presentar la senda con ASCII
    
    public void presentarSenda(String titulo) {
        // Usamos ANSI_REDPASTEL para el metal y ANSI_RED para el centro
        System.out.println("\n" + ANSI_REDPASTEL + "                             />\r\n"
            + "                (           //--------------------------------------------------------_/\n"
            + "               (*)" + ANSI_RED + "OXOXOXOXO" + ANSI_RESET + ANSI_REDPASTEL + "(*>" + ANSI_RESET + "          " + NEGRITA + titulo + ANSI_REDPASTEL + "               __/\n"
            + "                (           \\\\----------------------------------------------------/\r\n"
            + "                             \\>\n" 
            + ANSI_RESET);
    }
    
    
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
    
    
    // Para título Comienzo 
    
    public void imprimirAsciiRojo(String ascii) {
        System.out.println(ANSI_RED + ascii + ANSI_RESET);
    }
    
}