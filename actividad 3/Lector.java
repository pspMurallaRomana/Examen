package Ejercicio14.SimulaciónBD;

import java.util.concurrent.Semaphore;

public class Lector extends Thread {
    private final Semaphore sem;

    public Lector(String nombre, Semaphore sem) {
        super(nombre);
        this.sem = sem;
    }

    @Override
    public void run() {
        // Mensaje en consola para comprobar el funcionamiento
        System.out.println(getName() + " : Intentando leer");
        
        try {
           // Solicita un permiso para acceder a la BD a leer
           // Los otros 4 permisos, los pueden utilizar los otros hilos lectores
            sem.acquire();
            
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        System.out.println(getName() + " : Leyendo");
        try {
            //se duerme al hilo un tiempo aleatorio (para simular que tarda
            //en realizar su tarea y así otros hilos compiten por el acceso )
            sleep((int) (Math.random() * 50));
        } catch (InterruptedException e) {
            System.out.println(e);
        }
         // Mensaje en consola para comprobar el funcionamiento
        System.out.println(getName() + " : Ya he leido");
        
        sem.release();
       
    }
}
