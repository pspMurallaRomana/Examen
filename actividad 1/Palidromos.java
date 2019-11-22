package palídromos;

public class Palídromos  {

    public static void main(String[] args) throws InterruptedException{
        Palabra pal1 = new Palabra();
        Palabra pal2 = new Palabra();
        Palabra pal3 = new Palabra();
        
        pal1.setPalabra("casa");
        pal2.setPalabra("aaosoaa");
        pal3.setPalabra("ala");
        
        pal1.start();
        pal2.start();
        pal3.start();
        
        pal1.join();
        pal2.join();
        pal3.join();
        
        System.out.println(pal1.getPalabra() + " " + pal1.getPalíndromo());
        System.out.println(pal2.getPalabra() + " " + pal2.getPalíndromo());
        System.out.println(pal3.getPalabra() + " " + pal3.getPalíndromo());

        System.out.println("hilos terminados");
    }   
}
