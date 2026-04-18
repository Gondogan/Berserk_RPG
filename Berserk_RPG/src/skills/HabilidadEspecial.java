package skills;

public class HabilidadEspecial extends Habilidades {
    
    public HabilidadEspecial(String nombre, String descripcion, double mult, int coste) {
        // Aquí sí le pasamos la variable 'coste' al padre
        super(nombre, descripcion, mult, coste);
    }
}



//TENDRÍAMOS QUE CREAR UNA NUEVA CLASE HABILIDAD PASIVA PERO NO SÉ PORQUE NO ME DEJA


/*
         package skills;

         public class HabilidadPasiva extends Habilidades {
    
             public HabilidadPasiva(String nombre, String descripcion) {
               // Multiplicador 1.0 (no altera el daño si se llamara por error) y 0 de coste
               super(nombre, descripcion, 1.0, 0);
             }
         } 
 */ 
 
