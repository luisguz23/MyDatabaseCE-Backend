package com.example.mydatabasece_backend.ConexionArduino;
import com.fazecast.jSerialComm.*;


/**
 * Clase que se encarga de la comunicacion serial del arduino
 */
public class SerialCommunication {

    private static SerialPort serialPort;
    private static StringBuilder concatenatedString = new StringBuilder();
    public static StringBuilder getConcatenatedString() {
        if (concatenatedString != null) {
            String data = concatenatedString.toString().replaceAll("\\r\\n|\\r|\\n", "");
            return new StringBuilder(data);
        } else {
            return new StringBuilder();
        }
    }

    /**
     * Lee el arduino, es decir el morse
     * @return un string que es la contra
     */
    public static String lectorArduino() {

        serialPort = SerialPort.getCommPort("COM3");

        // Configura los parámetros de la conexión serial
        serialPort.setComPortParameters(9600, 8, 1, SerialPort.NO_PARITY);

        // Abre el puerto serial
        if (serialPort.openPort()) {
            System.out.println("Puerto serial abierto correctamente.");

            // Crea un hilo para recibir datos continuamente
            Thread thread = new Thread(() -> {
                long startTime = System.currentTimeMillis();
                while (serialPort.isOpen()) {
                    // Verifica si ha pasado cierto tiempo y detiene el hilo de recepción
                    long elapsedTime = System.currentTimeMillis() - startTime;
                    if (elapsedTime >= 15000) { // Reemplaza TIEMPO_LIMITE con el tiempo deseado en milisegundos
                        break;
                    }

                    if (serialPort.bytesAvailable() > 0) {
                        // Lee los datos del puerto serial
                        byte[] buffer = new byte[serialPort.bytesAvailable()];
                        int numRead = serialPort.readBytes(buffer, buffer.length);


                        String data = new String(buffer, 0, numRead);


                        concatenatedString.append(data);
                        contraseña = String.valueOf(getConcatenatedString());
                    }
                }
            });

            // Inicia el hilo de recepción
            thread.start();

            // Espera al hilo
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            if (thread.isAlive()) {
                thread.interrupt();
            }

            serialPort.closePort();

            System.out.println("Puerto serial cerrado.");



            return contraseña;
        } else {
            System.out.println("No se pudo abrir el puerto serial.");
        }

        return null;
    }

    /**
     * Enviar un dato de lectura al arduino
     * @param dato envia un valor de 1, 2 o 3 que enciende diferentes cosas en el arduino
     */
    public static void enviarDato(String dato) {

        serialPort = SerialPort.getCommPort("COM3");

        serialPort.setComPortParameters(9600, 8, 1, SerialPort.NO_PARITY);
        // Abre el puerto serial si no está abierto
        if (!serialPort.isOpen()) {
            if (serialPort.openPort()) {
                System.out.println("Puerto serial abierto correctamente.");
                try {
                    Thread.sleep(2000); // Espera 2 segundos (puedes ajustar este valor según tus necesidades)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("No se pudo abrir el puerto serial.");
                return;
            }
        }

        String comando = dato;
        byte[] data = comando.getBytes();
        serialPort.writeBytes(data, data.length);
        System.out.println("Dato enviado: " + dato);
        serialPort.closePort();
        System.out.println("Puerto serial cerrado");

    }
    static String contraseña = "";

}


