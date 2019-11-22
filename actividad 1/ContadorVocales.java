package contadorvocales;

class Contador {
    private int contador[] = new int[5];
    private int total;
    private final String vocales = "aeiou";
    
    public void incrementoVocal(char letra){
        letra = Character.toLowerCase(letra);
        int pos = vocales.indexOf(letra);
        contador[pos]++;
        incrementoTotal();
//        if (letra == 'a')
//            vocales[0]++;
//        else
//        if (letra == 'e')
//            vocales[1]++;
//        else
//        if (letra == 'i')
//            vocales[2]++;
//        else
//        if (letra == 'o')
//            vocales[3]++;
//        else
//        if (letra == 'u')
//            vocales[4]++;
    }
    
    private synchronized void incrementoTotal () {
        total++;
    }
    
    public int valorVocal(char letra){
        letra = Character.toLowerCase(letra);
        int pos = vocales.indexOf(letra); 
        return contador[pos];
    }
    
    public int valorTotal() {
        return total;
    }
}


class Hilo extends Thread {
    char vocal;
    String texto;
    Contador contador;
    
    Hilo (char vocal, String texto, Contador contador){
        this.vocal = vocal;
        this.texto = texto;
        this.contador = contador;
    }
    
    @Override
    public void run() {
        for (int i=0;i<texto.length();i++){
            if (texto.charAt(i) == vocal) {
                contador.incrementoVocal(texto.charAt(i));
            }
        }
    }
}

public class ContadorVocales {

    public static void main(String[] args) throws InterruptedException{
        String texto = "Este es un texto de prueba pata contar vocales";
        Contador contador = new Contador();
        
        Hilo h1 = new Hilo('a', texto, contador);
        Hilo h2 = new Hilo('e', texto, contador);
        Hilo h3 = new Hilo('i', texto, contador);
        Hilo h4 = new Hilo('o', texto, contador);
        Hilo h5 = new Hilo('u', texto, contador);
        
        h1.start();
        h2.start();
        h3.start();
        h4.start();
        h5.start();
        
        h1.join();
        h2.join();
        h3.join();
        h4.join();
        h5.join();
        
        System.out.println("Vocal a: " + contador.valorVocal('a'));
        System.out.println("Vocal e: " + contador.valorVocal('e'));
        System.out.println("Vocal i: " + contador.valorVocal('i'));
        System.out.println("Vocal o: " + contador.valorVocal('o'));
        System.out.println("Vocal u: " + contador.valorVocal('u'));
        System.out.println("Total " + contador.valorTotal());
    }
    
}
