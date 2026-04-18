package store;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import characters.Personajes;
import inventory.*;

public class GestorRecompensa {

    private int xpBase = 50;
    private int oroBase = 30;

    private int probOro = 60;
    private int probItem = 30;

    private Random rand;

    public GestorRecompensa() {
        this.rand = new Random();
    }

    // ================= MÉTODO PRINCIPAL =================

    public void generarRecompensa(Personajes personaje, int nivelEnemigo) {

        int xp = calcularXP(nivelEnemigo);
        personaje.ganarExperiencia(xp);

        String tipo = decidirTipoRecompensa();
        procesarRecompensa(tipo, personaje, nivelEnemigo, xp);
    }

    // ================= CÁLCULOS =================

    private int calcularXP(int nivel) {
        return xpBase * nivel;
    }

    private int calcularOro(int nivel) {
        return oroBase * nivel + rand.nextInt(20);
    }

    // ================= DECISIÓN =================

    private String decidirTipoRecompensa() {

        int tirada = rand.nextInt(100);

        if (tirada < probOro) {
            return "ORO";
        }

        if (tirada < probOro + probItem) {
            return "ITEM";
        }

        return "NADA";
    }

    // ================= PROCESAMIENTO =================

    private void procesarRecompensa(String tipo, Personajes personaje, int nivel, int xp) {

        switch (tipo) {

            case "ORO":
                int oro = calcularOro(nivel);
                personaje.ganarOro(oro);
                mostrarMensajeRecompensa(xp, oro, null);
                break;

            case "ITEM":
                Item item = generarItemAleatorio();
                personaje.getInventario().agregarItem(item);
                mostrarMensajeRecompensa(xp, 0, item.getNombre());
                break;

            case "NADA":
                mostrarMensajeRecompensa(xp, 0, null);
                break;
        }
    }

    // ================= GENERADOR DE ITEMS =================

    private Item generarItemAleatorio() {

        List<Item> drops = new ArrayList<>();

        drops.add(new Pocion("Poción pequeña", "Recupera 30 de vida", 0,
                Item.TipoItem.Pocion_pequenia, 30, 1));

        drops.add(new Pocion("Poción grande", "Recupera 80 de vida", 0,
                Item.TipoItem.Pocion_grande, 80, 1));

        drops.add(new MejoraArmadura("Refuerzo de armadura", "+5 defensa", 0, 5));
        drops.add(new MejoraArma("Afilado de espada", "+5 ataque", 0, 5));

        int indice = rand.nextInt(drops.size());
        return drops.get(indice);
    }

    // ================= MENSAJE =================

    private void mostrarMensajeRecompensa(int xp, int oro, String item) {

        System.out.println("\n── RECOMPENSA ───────────────");

        System.out.println("XP obtenida: " + xp);

        if (oro > 0) {
            System.out.println("Oro: " + oro);
        }

        if (item != null) {
            System.out.println("Item: " + item);
        }

        System.out.println("────────────────────────────");
    }
}