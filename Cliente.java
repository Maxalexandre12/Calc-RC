import java.net.*;
import java.io.*;
import javax.swing.JOptionPane;

class Cliente{
    public static void main( String args[] ) throws IOException {
        int porta, operador; 
        double n1, n2;
        String ip, opcao, resultado;
        Socket socket;
        DataInputStream socketInput;
        DataOutputStream socketOutput;
    
        try {
            
            socket = new Socket();
            ip = JOptionPane.showInputDialog("Digite o ip: ");
            porta = Integer.parseInt(JOptionPane.showInputDialog("Digite a porta: "));
            InetSocketAddress endereco = new InetSocketAddress(ip, porta);
            socket.connect(endereco,1000);  
            
            socketInput = new DataInputStream(socket.getInputStream());
            socketOutput = new DataOutputStream(socket.getOutputStream());
            
        while(true){

            opcao = JOptionPane.showInputDialog(null, "Digite o operador que deseja usar:\n" + 
            "[1] - Adicao\n" + 
            "[2] - Subtracao\n" + 
            "[3] - Multiplicacao\n" + 
            "[4] - Divisao\n" + 
            "[5] - Raiz quadrada\n" + 
            "[6] - Seno\n" + 
            "[7] - Cosseno\n" + 
            "[0] - Sair");
            
            if(opcao == null || Integer.parseInt(opcao) == JOptionPane.CLOSED_OPTION){ 
                JOptionPane.showMessageDialog(null, "Conexão cancelada!");
                System.out.println("Conexão cancelada!");
                socket.close();
                break;
            }
            
           operador = Integer.parseInt(opcao);
           socketOutput.writeInt(operador);

            if(operador	== 0){
                socket.close();
                System.out.println("Conexão encerrada");
                break;
            }else if(operador > 7){
                socket.close();
                System.out.println("Operação invalida! Encerrando conexão...");
                break;
            }
            
            switch(operador){
                case 1: case 2: case 3: case 4:
                    n1 = Double.parseDouble(JOptionPane.showInputDialog(null,"Informe o primeiro valor: "));
                    socketOutput.writeDouble(n1); 
                    
                    n2 = Double.parseDouble(JOptionPane.showInputDialog(null,"Informe o segundo valor: "));
                    socketOutput.writeDouble(n2);
                    break;
                
                case 5: case 6: case 7:
                    n1 = Double.parseDouble(JOptionPane.showInputDialog(null,"Informe um valor: "));
                    socketOutput.writeDouble(n1);
                    break;
                }
                
            resultado = socketInput.readUTF();
            System.out.println("\nResultado da " + resultado);
        }
    }catch(NumberFormatException e) {
        System.out.println(e);
    }
  }
}

