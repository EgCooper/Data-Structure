import java.util.*;

public class IndiceInvertido{
    public static void main(String[] args){
        //documentos de ejemplo
        Map<Integer,String>documentos = new HashMap<>();
        documentos.put(1,"Hola como estas");
        documentos.put(2,"Bien y tu como estas");

        //construimos indice invertido
        Map<String,List<Integer>> indice = construirIndice(documentos);

        // Mostramos el indice x consola
        for (Map.Entry<String,List<Integer>> entrada : indice.entrySet()) {
            System.out.println(entrada.getKey()+ "->"+ entrada.getValue());
        }
    }
    // Construimos indice
    public static Map<String, List<Integer>> construirIndice(Map<Integer, String> docs) {
        Map<String, List<Integer>> indice = new HashMap<>();

        for (Map.Entry<Integer, String> doc : docs.entrySet()) {
            int docId = doc.getKey();
            // Limpiar texto: minúsculas y quitar puntos/comas
            String textoLimpio = doc.getValue().toLowerCase().replaceAll("[.,]", "");
            // Dividir en palabras (Tokenización)
            String[] palabras = textoLimpio.split("\\s+");
            // Bucle 
            for (String palabra : palabras) {
                // Crear la lista si la palabra no existe en el índice
                indice.putIfAbsent(palabra, new ArrayList<>());
                
                // Evitar duplicados del mismo documento en la lista
                if (!indice.get(palabra).contains(docId)) {
                    indice.get(palabra).add(docId);
                }
            }
        }
        return indice;
    }
}