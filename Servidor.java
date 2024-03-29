import java.net.*;
import java.io.*;

class Servidor {
    private static ServerSocket s;

    public static void main( String args[] ){ 
        s = (ServerSocket)null;
        Socket s1;
        int porta = 0;

        try {
            s = new ServerSocket(porta = 4321);
        }
        catch( IOException e ) {
            System.out.println( e );
        }
        while(true) {
            try {
                System.out.println("Aguardando Conexão na porta: " + porta + "...");
                s1 = s.accept();
                System.out.println("Cliente Conectado..." + " IP: "+ InetAddress.getLocalHost().getHostAddress());
                ServidorThread serviceThread = new ServidorThread(s1);
                serviceThread.start();
            }
            catch(IOException e){
                System.out.println( e );
            }
        }
    }
}