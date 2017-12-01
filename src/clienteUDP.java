/*Librerias necesarias para ejecución del programa*/
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import javax.swing.JOptionPane;

//Clase con funciones de Cliente
public class clienteUDP {
    private static int SERVER_PORT=9091;
    
    public static void main(String[] args) throws IOException {
        String serverAddress = JOptionPane.showInputDialog("Enter IP Address of a machine that is \n"+
                                " running the date service on port "+SERVER_PORT+":");
        
        //send packet (Request) -->Envio de paquete de requerimiento
        DatagramSocket clientSocket = new DatagramSocket();         //creación de un socket para el cliente
        byte bufferSend[] =serverAddress.getBytes();                //transforma la direccion IP a bytes (string->byte)
        DatagramPacket sendPacket= new DatagramPacket(bufferSend, bufferSend.length,InetAddress.getByName(serverAddress),SERVER_PORT);
        clientSocket.send(sendPacket);                              // se encuentra enviando un request al servidor 
        
        //Receive Packet
        byte bufferReceive[] = new byte[128];
        DatagramPacket receivePacket = new DatagramPacket(bufferReceive, bufferReceive.length);     //Creacion de Paquete datagrama para recibir el paquete
        clientSocket.receive(receivePacket);
        
        //transforma bytes a string
        InputStream myImputStream = new ByteArrayInputStream(receivePacket.getData());
        BufferedReader input= new BufferedReader(new InputStreamReader(myImputStream));
        String answer = input.readLine();
        
        //Despliega mensaje recibido desde el servidor
        JOptionPane.showMessageDialog(null, answer);
        clientSocket.close();
        System.exit(0);
                
    }
    
}
