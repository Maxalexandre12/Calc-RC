import java.net.*;
import java.io.*;
import javax.swing.JOptionPane;


class Cliente{
    public static void main( String args[] ) throws IOException {
        int porta,  operador; 
        double n1, n2;
        String ip, resultado;
        Socket s;
        DataInputStream sIn;
        DataOutputStream sOut;
    
        try {
            
            s = new Socket();
            ip = JOptionPane.showInputDialog("Digite o ip: ");
            porta = Integer.parseInt(JOptionPane.showInputDialog("Digite a porta: "));
            InetSocketAddress endereco = new InetSocketAddress(ip, porta);
            s.connect(endereco,1000);  
            
            sIn = new DataInputStream(s.getInputStream());
            sOut = new DataOutputStream(s.getOutputStream());
            
        while(true){
            String opcao;
            opcao = JOptionPane.showInputDialog(null,"Digite o operador que deseja usar:\n [1] - Adicao\n [2] - Subtracao\n [3] - Multiplicacao\n [4] - Divisao\n [5] - Raiz quadrada\n [6] - Seno\n [7] - Cosseno\n [0] - Sair");
            
            if(opcao == null ){ // opcao == "" tentar fazer
                s.close();
                JOptionPane.showMessageDialog(null, "Conexão cancelada!");
                break;
            }

           operador = Integer.parseInt(opcao);
           sOut.writeInt(operador);

            if(operador	== 0 || operador > 7 ){
                s.close();
                System.out.println("Conexão encerrada");
                break;
            }
            
            switch(operador){
                case 1: case 2: case 3: case 4:
                    n1 = Double.parseDouble(JOptionPane.showInputDialog(null,"Informe o primeiro valor: "));
                    sOut.writeDouble(n1); 
                    
                    n2 = Double.parseDouble(JOptionPane.showInputDialog(null,"Informe o segundo valor: "));
                    sOut.writeDouble(n2);
                    break;
                
                case 5: case 6: case 7:
                    n1 = Double.parseDouble(JOptionPane.showInputDialog(null,"Informe um valor: "));
                    sOut = new DataOutputStream(s.getOutputStream());
                    sOut.writeDouble(n1);
                    break;

            }
            resultado = sIn.readUTF();
            System.out.println("\nResultado da " + resultado);
        }
    }catch(NumberFormatException e) {
        System.out.println(e);
    }
  }
}

