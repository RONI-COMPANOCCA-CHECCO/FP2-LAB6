public class Map01 {
    public static void main(String[] args){
        ColeccionesMap coleccion01 = new ColeccionesMap();
        System.out.println("----------------------");
        System.out.println("COLECCION HASHMAP");
        System.out.println("----------------------");
        coleccion01.llenarHashMap();
        coleccion01.buscarEnHashMap();
        coleccion01.reemplazarEnHashMap();
        coleccion01.eliminarEnHashMap();
    }
}
