import java.net.*;
import java.io.*;
import java.lang.Math;

public class ServidorThread extends Thread {
    
    public Socket s1;
        int opcao;
        double n1, n2, radiano;
        double soma, subtracao, multiplicacao, divisao, raiz, seno, cosseno;
        DataOutputStream s1out;
        DataInputStream s1In;

    public ServidorThread(Socket s1) {
        super();
        this.s1 = s1;
    }

    @Override
    public void run() {
        
        try {
                s1out = new DataOutputStream(s1.getOutputStream());
                s1In = new DataInputStream(s1.getInputStream());
           
                do {

                opcao = s1In.readInt();
                
                if(opcao >= 1 && opcao <= 4){
                    n1 = s1In.readDouble();
                    n2 = s1In.readDouble();
                }else if(opcao >= 5){
                    n1 = s1In.readDouble();
                }
                
                switch(opcao){
                    case 1:
                        soma = n1 + n2;
                        s1out.writeUTF("Soma: " + soma);
                        break;
                       
                    case 2:
                        subtracao = n1 - n2;
                        s1out.writeUTF("Subtracao: " + subtracao);
                        break;
                       
                    case 3:
                        multiplicacao = n1 * n2;
                        s1out.writeUTF("Multiplicacao: " + multiplicacao);
                        break;
                       
                    case 4:
                        divisao = n1 / n2;
                        if(n1 == 0){
                            s1out.writeUTF("Impossivel de realizar o calculo");
                            break;
                        }else{
                            s1out.writeUTF("Divisao: " + divisao);
                            break;
                        }

                    case 5:
                        raiz = Math.sqrt(n1);
                        s1out.writeUTF("Raiz quadrada de: " + n1 + " = " + raiz);
                        break;
    
                    case 6:
                        radiano = Math.toRadians(n1); 
                        seno = Math.sin(radiano);
                        s1out.writeUTF("Seno de: " + n1 + " = " + seno);
                        break;
    
                    case 7:
                        radiano = Math.toRadians(n1); //n1 é o valor em graus
                        cosseno = Math.cos(radiano);
                        s1out.writeUTF("Cosseno de: " + n1 + " = " + cosseno);
                        break;
                }

            }while(opcao != 0);
        }catch(IOException e) {
            System.out.println(e);
        }
    }
}
