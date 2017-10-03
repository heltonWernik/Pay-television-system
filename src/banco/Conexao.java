
package banco;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Classe responsável pela conexão com banco de dados. 
 * @Helton Wernik 
 */
public class Conexao {

    private static Connection connection;
    private static  String url;
    private static  String username;
    private static  String password;
    
    public static Connection getConnection() {
        return connection;
    }
    /**
     * Método responsável pela conexão com o banco de dados
     * @return 
     * Retorno true caso tudo estiver ok.
     */
    public static boolean connect(){
        boolean retorno = false;
        
        if(getDadosBanco()){
        
        try {
            connection = DriverManager.getConnection(url, username, password);
            retorno = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Não foi possível conectar ao banco de dados.\n"
                    + "Verifique o arquivo 'dados.txt' e certifique-se que está tudo em ordem.\n"
                    + "(URL = primeira linha, USERNAME = segunda linha, SENHA = terceira linha). \nSe tudo estiver ok verifique o banco de dados.","ERRO",JOptionPane.ERROR_MESSAGE);
        System.exit(0);
            
        }
        
        }else{
            System.exit(0);
        }
        
        return retorno;
    }
    /**
     * Este método e responsável por criar ou extrair as informações do arquivo 'dados.txt'
     * que serve como fonte de dados com as informações para acessar o banco de dados.
     * @return 
     * true se o arquivo existir.
     */
    private static boolean getDadosBanco(){
        boolean isArquivoExiste = true;
        boolean retorno = true;
        ArrayList<String> conteudoDoArquivo = new ArrayList();
        
        if(!new File("dados.txt").exists()){
            JOptionPane.showMessageDialog(null,"O arquivo 'dados.txt' foi criado no local da execução, por favor preencha-o \ncom os dados rederentes ao banco de dados MySql!","ERRO",JOptionPane.ERROR_MESSAGE);
            isArquivoExiste = false;
            retorno = false;
        }
       
        if(!isArquivoExiste){
            Formatter arquivo;
            try {
                arquivo = new Formatter("dados.txt");
                arquivo.format("jdbc:mysql://localhost:3306/db_sistema%nusername%nsenha");
                arquivo.close();
            } catch (IOException ex) {
                Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
                retorno = false;
            }
            
       }else{
           
            try {
                Scanner leitura = new Scanner(new File("dados.txt"));
                
                while(leitura.hasNext()){
                    conteudoDoArquivo.add(leitura.next());
                }
                
                if(conteudoDoArquivo.size() != 3){
                    JOptionPane.showMessageDialog(null, "Algo de errado com o arquivo dados.txt. Por favor delete-o e reinicie a aplicação.", "ERRO!", JOptionPane.ERROR_MESSAGE);
                    retorno = false;
                }else{
                    Conexao.url = conteudoDoArquivo.get(0);
                    Conexao.username = conteudoDoArquivo.get(1);
                    Conexao.password = conteudoDoArquivo.get(2);
                }
                
                leitura.close();
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            }
       }
        
        return retorno;
    }
}
 