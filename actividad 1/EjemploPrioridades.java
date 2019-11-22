package ejemploprioridades;

class Contador extends Thread {
    public Contador (String nombre) {
        super(nombre);
    }
    
    @Override
    public void run () {
        int contador = 0;
        while (contador <= 1000) {
//            try {
//                sleep(10);
//            }
//            catch(InterruptedException ex) {
//                ex.printStackTrace();
//            }
            System.out.println(this.getName() + ": " + contador++);
        }
    }
}


public class EjemploPrioridades {

    public static void main(String[] args) {
        Contador hilo1 = new Contador("hilo 1");
        hilo1.setPriority(Thread.MAX_PRIORITY);
        
        Contador hilo2 = new Contador("hilo 2");
        hilo2.setPriority(Thread.MIN_PRIORITY);
        
        hilo1.start();
        hilo2.start();
    }
}
