
    package servidor;

    import java.net.*;
    import java.util.Scanner;


    public class ServidorClass {
        final int porta;
        ServerSocket servidor;
        Socket cliente;
        
        //construtor inicializando as variaveis e inicia o servidor
        public ServidorClass() {
        this.porta = 12345;
        try{
        this.servidor = new ServerSocket(porta);
        System.out.println("O servidor foi inializado...");
        System.out.println("Inicializado na porta: " + servidor.getLocalPort());
        }catch(Exception e){
            System.out.println("Erro ao iniciar o servidor");
            System.out.println("Erro: " + e.getMessage());
        }
    }
  
        //fornecer conexão para o cliente 
        public void ConexaoSocket(){
          
            try{
                //O socket do cliente recebe a aceitação do servidor para se comunicar                 
                cliente = servidor.accept();  
                System.out.println("Novo Socket conectado no IP: " + cliente.getInetAddress().getHostAddress());
            }catch(Exception e){
                System.out.println("Não foi possivel estabelecer comunicação com o socket do cliente");
                System.out.println("Erro: " + e.getMessage());
            }
           
        }
    
        //lê as informações que o cliente envia  
        public void RecebeMensagem(){        
       
              try{
                    Scanner ler = new Scanner(cliente.getInputStream());
                    while(ler.hasNextLine()){
                    String entrada = ler.nextLine();
                    System.out.println("Recebi: "+ entrada);
                    }
                    
                }catch(Exception e){
                    System.out.println("Erro de comunicação");
                    System.out.println("Erro:"+e.getMessage());
                    return;
                }
       
        
        }
            public void FecharConexao(){
            //fecha conexão do servidor            
            try{
            System.out.println("Fechando conexão do servidor");
            servidor.close();
            System.out.println("Conexão fechada!");
            }catch(Exception e){
                System.out.println("Erro ao encerrar conexão do servidor!");
                System.out.println("Erro: " + e.getMessage());
            }
             
        
        }  
        public static void main(String arg[]){
            
        ServidorClass servidor = new ServidorClass();
        servidor.ConexaoSocket();
        servidor.RecebeMensagem();
        servidor.FecharConexao();
       
    }
    }