package incrementocompartidosemáforos;

import java.util.concurrent.Semaphore;

class Wrapper {
    public static int valor = 0;
}

class Sumador extends Thread {
    private static final Semaphore sem = new Semaphore(1);
    
    @Override
    public void run() {
        for (int i=0;i<5000; i++) {
            try {
                sem.acquire();
                Wrapper.valor++;
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                sem.release();
            }
        }      
    }
}

public class IncrementoCompartidoSemáforos {
  
    public static void main(String[] args) throws InterruptedException {
        
        Sumador hilo1 = new Sumador();
        Sumador hilo2 = new Sumador();
        Sumador hilo3 = new Sumador();
        Sumador hilo4 = new Sumador();
        
        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        
        hilo1.join();
        hilo2.join();
        hilo3.join();
        hilo4.join();
        
        System.out.println("El valor de la variable compartida es: " + Wrapper.valor); 
    }
}
