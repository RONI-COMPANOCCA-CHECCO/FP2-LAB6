import java.util.*;

public class VideoJuego5 {

    public static void main(String[] args) {

        // DECLARACIÓN DE VARIABLES Y ESTRUCTURAS DE DATOS NECESARIAS
        HashMap<String, Soldado> ejercito1 = new HashMap<>();
        HashMap<String, Soldado> ejercito2 = new HashMap<>();
        HashMap<String, Soldado> tablero = new HashMap<>();
        int batallon1, batallon2;
        int vidatotal1 = 0, vidatotal2 = 0;
        double promedioVida1 = 0, promedioVida2 = 0;

        // BUCLE PARA DESIGNAR LA CANTIDAD DE FILAS Y COLUMNAS DEL TABLERO
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                String posicion = i + "x" + j;
                tablero.put(posicion, new Soldado());
            }
        }

        // CREACIÓN DEL NÚMERO DE POSICIONES DE CADA EJÉRCITO
        batallon1 = aleatorio(1, 10);
        batallon2 = aleatorio(1, 10);

        // INICIALIZAR MAPAS DE EJÉRCITOS
        inicializarEjercito(ejercito1, batallon1);
        inicializarEjercito(ejercito2, batallon2);

        // GENERAR EJÉRCITOS VÁLIDOS
        generarEjercitos(ejercito1, ejercito2, tablero);

        // IMPRIMIR EL TABLERO
        imprimirTablero(tablero);

        // IMPRIMIR LOS SOLDADOS DE MAYOR VIDA DE CADA EJÉRCITO
        System.out.println("Soldado de mayor vida del ejército 1");
        SoldadoConMayorVida(ejercito1);
        System.out.println("Soldado de mayor vida del ejército 2");
        SoldadoConMayorVida(ejercito2);

        // IMPRIMIR LA VIDA TOTAL Y EL PROMEDIO DEL EJÉRCITO 1
        System.out.println("\nEJÉRCITO 1: ");
        for (Soldado soldado : ejercito1.values()) {
            vidatotal1 += soldado.getPuntos();
        }
        promedioVida1 = vidatotal1 / (double) ejercito1.size();
        System.out.println("Vida total: " + vidatotal1);
        System.out.println("Promedio de vida: " + promedioVida1);

        // IMPRIMIR LA VIDA TOTAL Y EL PROMEDIO DEL EJÉRCITO 2
        System.out.println("\nEJÉRCITO 2: ");
        for (Soldado soldado : ejercito2.values()) {
            vidatotal2 += soldado.getPuntos();
        }
        promedioVida2 = vidatotal2 / (double) ejercito2.size();
        System.out.println("Vida total: " + vidatotal2);
        System.out.println("Promedio de vida: " + promedioVida2);

        // IMPRIMIR LOS SOLDADOS CREADOS EN EL ORDEN POR DEFECTO
        System.out.println("\nLista ejército 1:");
        for (Soldado soldado : ejercito1.values()) {
            imprimir(soldado);
        }
        System.out.println("\nLista ejército 2:");
        for (Soldado soldado : ejercito2.values()) {
            imprimir(soldado);
        }

        // IMPRIMIR LOS DATOS DE LOS SOLDADOS ORDENADOS DE MAYOR A MENOR DEPENDIENDO DE SU NIVEL DE VIDA
        ArrayList<Soldado> listaSoldados1 = new ArrayList<>(ejercito1.values());
        ArrayList<Soldado> listaSoldados2 = new ArrayList<>(ejercito2.values());
        ordenarPorVidaMetodoA(listaSoldados1);
        ordenarPorVidaMetodoB(listaSoldados2);
        System.out.println("\nEjército 1 Ordenados por nivel de vida");
        for (Soldado soldado : listaSoldados1) {
            imprimir(soldado);
        }
        System.out.println("\nEjército 2 Ordenados por nivel de vida");
        for (Soldado soldado : listaSoldados2) {
            imprimir(soldado);
        }

        // MOSTRAR EJÉRCITO GANADOR LA MÉTRICA USADA PARA DESIGNAR AL GANADOR ES EL PROMEDIO DEL NIVEL DE VIDA DE CADA EJÉRCITO
        if (promedioVida1 > promedioVida2) {
            System.out.println("\nGANADOR ***EJÉRCITO 1***");
        } else if (promedioVida1 < promedioVida2) {
            System.out.println("\nGANADOR ***EJÉRCITO 2***");
        } else {
            System.out.print("\n***ES UN EMPATE***");
        }
    }

    // Resto del código permanece igual
    // ...

    // METODO PARA INICIAR UN EJÉRCITO
    public static void inicializarEjercito(HashMap<String, Soldado> ejercito, int num) {
        for (int i = 0; i < num; i++) {
            ejercito.put("Soldado" + i, new Soldado());
        }
    }

    // METODO PARA AÑADIR LOS EJÉRCITOS AL TABLERO
    public static void añadirTablero(HashMap<String, Soldado> ejercito, HashMap<String, Soldado> tablero) {
        for (String posicion : ejercito.keySet()) {
            tablero.put(posicion, ejercito.get(posicion));
        }
    }

    // METODO PARA IMPRIMIR EL TABLERO EN LA CUAL SE DESARROLLA EL JUEGO
    public static void imprimirTablero(HashMap<String, Soldado> tablero) {
        System.out.println("\tA\tB\tC\tD\tF\tG\tH\tI\tJ");
        for (int i = 1; i <= 10; i++) {
            System.out.print(i);
            for (int j = 1; j <= 10; j++) {
                String posicion = i + "x" + j;
                Soldado soldado = tablero.get(posicion);
                System.out.print("\t" + soldado.getColumn());
            }
            System.out.println("\n");
        }
    }

    // Resto del código permanece igual
    // ...
}
