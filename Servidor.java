import java.net.*;
import java.io.*;

class Servidor {
    public static void main( String args[] ) throws IOException{ 
        ServerSocket s = (ServerSocket)null;
        Socket s1;
        int a = 1;

        try {
            s = new ServerSocket(4321);
        }
        catch( IOException e ) {
            System.out.println( e );
        }
        while(a == 1) {
            try {
                s1 = s.accept();
                ServidorThread serviceThread = new ServidorThread(s1);
                serviceThread.start();
                if(!s1.isClosed()){
                    a = 0;  
                }
            }
            catch( IOException e ){
                System.out.println( e );
            }
        }
        s.close();
    }
}