package sensores;

import java.util.concurrent.Semaphore;

class Trabajador extends Thread {

    private final Semaphore semTrabajador;
    private final Semaphore[] semSensores;
    private final int[] datos;

    public Trabajador(Semaphore semTrabajador, Semaphore[] semSensores,
            int[] datos) {
        this.semTrabajador = semTrabajador;
        this.semSensores = semSensores;
        this.datos = datos;
    }

    @Override
    public void run() {
        while (true) {
            try {
                semTrabajador.acquire(3);

//                System.out.println("Temperatura: " + datos[0]);
//                System.out.println("Humedad: " + datos[1]);
//                System.out.println("Luz: " + datos[2]);
                System.out.println("Dispositivo trabajando..." + "\n");
                sleep((int) Math.random() * 500);  // tiempos de espera aleatorio simulando la tarea del trabajador 
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semSensores[0].release();
                semSensores[1].release();
                semSensores[2].release();
            }
        }
    }
}

class Sensor extends Thread {

    private final int id;
    private final Semaphore[] semSensores;
    private final Semaphore semTrabajador;
    private final int[] datos;

    public Sensor(int id, Semaphore[] semSensores,
            Semaphore semTrabajador, int[] datos) {
        this.id = id;
        this.semSensores = semSensores;
        this.semTrabajador = semTrabajador;
        this.datos = datos;
    }

    @Override
    public void run() {
        while (true) {
            try {
                semSensores[id].acquire();

                // tiempos de espera aleatorio simulando la tarea del sensor
                sleep((int) Math.random() * 500);
                // Se genera un valor aleatorio para simular la lectura
                datos[id] = (int) (Math.random() * 50);
                if (id == 0) {
                    System.out.println("Temperatura: " + datos[id]);
                } else if (id == 1) {
                    System.out.println("Humedad: " + datos[id]);
                } else {
                    System.out.println("Luz: " + datos[id]);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semTrabajador.release();
            }
        }
    }
}

public class Sensores {

    private void go() {
        // Se declaran los semáforos para el trabajador y para los sensores
        Semaphore semTrabajador = new Semaphore(0);
        Semaphore[] semSensores = new Semaphore[3];
        // Vector para almacenar las lecturas de los sensonres
        int[] datos = new int[3];

        Trabajador trabajador = new Trabajador(semTrabajador, semSensores, datos);
        Sensor sensor0 = new Sensor(0, semSensores, semTrabajador, datos);
        Sensor sensor1 = new Sensor(1, semSensores, semTrabajador, datos);
        Sensor sensor2 = new Sensor(2, semSensores, semTrabajador, datos);

        semSensores[0] = new Semaphore(1);
        semSensores[1] = new Semaphore(1);
        semSensores[2] = new Semaphore(1);

        trabajador.start();
        sensor0.start();
        sensor1.start();
        sensor2.start();
    }

    public static void main(String[] args) {
        Sensores sensores = new Sensores();
        sensores.go();
    }
}
