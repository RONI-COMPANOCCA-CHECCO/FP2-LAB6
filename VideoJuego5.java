// RONI COMPANOCCA CHECCO
// CUI: 20210558
// LABORATORIO 06 - HASHMAP
// FUNDAMENTOS DE PROGRAMACION 
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
        ordenarPorVidaMetodoA(ejercito1);
        ordenarPorVidaMetodoB(ejercito2);
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

    // METODO PARA CREAR NUMEROS ALEATORIOS EN UN RANGO
	public static int aleatorio(int min, int max) {
		return(int)(Math.random()*(max-min+1)+min);
	}
    
    // METODO PARA INICIAR UN EJÉRCITO
    public static void inicializarEjercito(HashMap<String, Soldado> ejercito, int num) {
        for (int i = 0; i < num; i++) {
            ejercito.put("Soldado" + i, new Soldado());
        }
    }

	// METODO PARA GENERAR DATOS DEL OBJETO SOLDADO
	public static Soldado generarDatos() {
		Soldado soldadito = new Soldado();
		soldadito.setPuntos(aleatorio(1,5));
		soldadito.setColumna(aleatorio(1,10));
		soldadito.setFila(aleatorio(1,10));
		return soldadito;
	}

	// METODOS PARA GENERAR LOS EJERCITOS DE MANERA ALEATORIA
    public static void generarEjercitos(HashMap<String, Soldado> ejercito1, HashMap<String, Soldado> ejercito2, HashMap<String, Soldado> tablero) {
        ArrayList<String> posicionesOcupadas = new ArrayList<>();
        
        // Generar los soldados y asegurarse de que sus posiciones sean únicas
        for (int i = 0; i < ejercito1.size() + ejercito2.size(); i++) {
            Soldado soldado = generarDatos();
            String posicion = soldado.getFila() + "x" + soldado.getColumna();
            
            // Verificar que la posición no esté ocupada
            while (posicionesOcupadas.contains(posicion)) {
                soldado = generarDatos();
                posicion = soldado.getFila() + "x" + soldado.getColumna();
            }
            
            posicionesOcupadas.add(posicion);
            
            // Asignar soldado a ejército 1 o ejército 2
            if (i < ejercito1.size()) {
                ejercito1.put("Soldado" + i + "x1", soldado);
            } else {
                ejercito2.put("Soldado" + (i - ejercito1.size()) + "x2", soldado);
            }
            
            // Asignar soldado al tablero
            tablero.put(posicion, soldado);
        }
        
        // Actualizar las columnas de los soldados en ejército 1 y ejército 2
        for (String clave : ejercito1.keySet()) {
            Soldado soldado = ejercito1.get(clave);
            soldado.setColumn(soldado.getPuntos() + "[E1]");
        }
        for (String clave : ejercito2.keySet()) {
            Soldado soldado = ejercito2.get(clave);
            soldado.setColumn(soldado.getPuntos() + "[E2]");
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

    //METODO PARA IMPRIMIR LOS SOLDADOS DE MAYOR VIDA
	public static void SoldadoConMayorVida(HashMap<String, Soldado> soldados) {
        Soldado mayor = null;
    
        for (Soldado soldado : soldados.values()) {
            if (mayor == null || soldado.getPuntos() > mayor.getPuntos()) {
                mayor = soldado;
            }
        }
    
        if (mayor != null) {
            imprimir(mayor);
        } else {
            System.out.println("No se encontraron soldados.");
        }
    }
	
	// METODO PARA IMPRIMIR EL NOMBRE, LA POSICION Y NIVEL DE VIDA DEL SOLDADO
	public static void imprimir(Soldado soldadito) {
		System.out.println("Nombre: "+soldadito.getNombre()+"\nPosicion: "+soldadito.getColumna()+"X"+soldadito.getFila()+"\tVida: "+soldadito.getPuntos());
	}
	
	// METODO QUE NOS AYUDA A ORDENAR LOS SOLDADOS DE ACUERDO A SU NIVEL DE VIDA, USUANDO UN ALGORITMO DE ORDENAMIENTO DE BURBUJA
	public static void ordenarPorVidaMetodoA(HashMap<String, Soldado> soldados) {
        // Obtener los valores (los soldados) del HashMap y almacenarlos en una lista
        List<Soldado> listaSoldados = new ArrayList<>(soldados.values());
    
        Soldado aux = new Soldado();
        for (int i = 0; i < listaSoldados.size() - 1; i++) {
            for (int j = 0; j < listaSoldados.size() - i - 1; j++) {
                if (listaSoldados.get(j).getPuntos() < listaSoldados.get(j + 1).getPuntos()) {
                    aux = listaSoldados.get(j);
                    listaSoldados.set(j, listaSoldados.get(j + 1));
                    listaSoldados.set(j + 1, aux);
                }
            }
        }
    
        // Actualizar el HashMap con los soldados ordenados
        int index = 0;
        for (String clave : soldados.keySet()) {
            soldados.put(clave, listaSoldados.get(index));
            index++;
        }
    }

    // METODO QUE NOS AYUDA A ORDENAR LOS SOLDADOS DE ACUERDO A SU NIVEL DE VIDA, EN ESTA OCACION DIFERENTE A LA ANTERIOR QUE ERA ALGORITMO DE BURBUJA
    public static void ordenarPorVidaMetodoB(HashMap<String, Soldado> soldados) {
        // Obtener los valores (los soldados) del HashMap y almacenarlos en una lista
        List<Soldado> listaSoldados = new ArrayList<>(soldados.values());
    
        // Ordenar la lista en orden descendente por puntos de vida
        Collections.sort(listaSoldados, new Comparator<Soldado>() {
            public int compare(Soldado s1, Soldado s2) {
                // Orden descendente por puntos de vida
                return Integer.compare(s2.getPuntos(), s1.getPuntos());
            }
        });
    
        // Actualizar el HashMap con los soldados ordenados
        int index = 0;
        for (String clave : soldados.keySet()) {
            soldados.put(clave, listaSoldados.get(index));
            index++;
        }
    }
}
