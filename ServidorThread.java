import java.net.*;
import java.io.*;

public class ServidorThread extends Thread {
    
    public Socket s1;
        int opcao;
        double n1, n2, radiano;
        double soma, subtracao, multiplicacao, divisao, raiz, seno, cosseno;
        DataInputStream socketInput1;
        DataOutputStream socketOutput1;

    public ServidorThread(Socket s1) {
        super();
        this.s1 = s1;
    }

    @Override
    public void run() {
        
        try {
                socketOutput1 = new DataOutputStream(s1.getOutputStream());
                socketInput1 = new DataInputStream(s1.getInputStream());
           
                do {

                opcao = socketInput1.readInt();
                
                if(opcao >= 1 && opcao <= 4){
                    n1 = socketInput1.readDouble();
                    n2 = socketInput1.readDouble();
                }else if(opcao >= 5){
                    n1 = socketInput1.readDouble();
                }
                
                switch(opcao){
                    case 1:
                        soma = n1 + n2;
                        socketOutput1.writeUTF("Soma: " + soma);
                        break;
                       
                    case 2:
                        subtracao = n1 - n2;
                        socketOutput1.writeUTF("Subtracao: " + subtracao);
                        break;
                       
                    case 3:
                        multiplicacao = n1 * n2;
                        socketOutput1.writeUTF("Multiplicacao: " + multiplicacao);
                        break;
                       
                    case 4:
                        divisao = n1 / n2;
                        if(n1 == 0){
                            socketOutput1.writeUTF("Impossivel de realizar o calculo");
                            break;
                        }else{
                            socketOutput1.writeUTF("Divisao: " + divisao);
                            break;
                        }

                    case 5:
                        raiz = Math.sqrt(n1);
                        socketOutput1.writeUTF("Raiz quadrada de: " + n1 + " = " + raiz);
                        break;
    
                    case 6:
                        radiano = Math.toRadians(n1); 
                        seno = Math.sin(radiano);
                        socketOutput1.writeUTF("Seno de: " + n1 + " = " + seno);
                        break;
    
                    case 7:
                        radiano = Math.toRadians(n1); //n1 é o valor em graus
                        cosseno = Math.cos(radiano);
                        socketOutput1.writeUTF("Cosseno de: " + n1 + " = " + cosseno);
                        break;
                    
                    case 0:
                        System.out.println("Conexão com o cliente encerrada!");
                        s1.close();
                        break;
                }

            }while(opcao != 0);
        }catch(IOException e) {
            System.out.println(e);
        }
    }
}
