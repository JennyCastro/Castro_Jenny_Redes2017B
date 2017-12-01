/*Librerias necesarias para ejecución del programa*/
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;

//9091 puerto del servidor
//Clase con funciones de Servidor
public class ServerUDP {
    private static int PORT =9091;
    public static void main(String[] args)throws IOException {
        DatagramSocket serverSocket = new DatagramSocket(PORT);                             //Creación de un socket de conexión
        System.out.println("Server listening on port "+ PORT+ " using UDP connection\n");
        long initialTime = System.currentTimeMillis();                                      //Inicialización del tiempo de conexión
        System.out.println("Tiempo inicial: "+initialTime+ "\n");
        
        try {
            while(true){
                //Receive packet
                byte bufferReceive[] = new byte [128];
                DatagramPacket receivePacket = new DatagramPacket(bufferReceive, bufferReceive.length);     //Creacion de datagramas para recibir los paquetes del cliente
                serverSocket.receive(receivePacket);                                                        //los paquetes recibidos se direccionan a un socket
                InetAddress clieAddress = receivePacket.getAddress();                                       // Información del cliente port
                int clientPort = receivePacket.getPort();
                System.out.println("Client port: "+clientPort+"\n");
                
                //Send packet
                String msg = "Message from Jenny";                                                          //Envio de requerimientos por parte del cliente
                byte bufferSend[] = msg.getBytes();                                                        
                DatagramPacket sendPacket = new DatagramPacket(bufferSend, bufferSend.length, clieAddress,clientPort);   //Creación de datagrama para enviar las solicitudes correspondientes
                serverSocket.send(sendPacket);
                
            }
        }finally {
            serverSocket.close();       //cierre del socket al final del requerimiento
        }
    }
    
}
