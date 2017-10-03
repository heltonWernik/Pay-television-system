package controledecanais;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Serializacao {
    
    /**
     * Método responsável por serializar objetos.
     * @param categoria
     * Recebe uma instancia da classe Categoria.
     * @throws IOException 
     * Lança uma exceção caso de algo errado na serialização.
     */
    public static void serializar(canais.Categoria categoria) throws IOException{
             //Gera o arquivo para armazenar o objeto
             FileOutputStream fileOutputStream = new FileOutputStream(categoria.getNome() + ".txt");

            //Classe responsavel por inserir os objetos
            ObjectOutputStream objGravar = new ObjectOutputStream(fileOutputStream);

            //Grava o objeto cliente no arquivo
            objGravar.writeObject(categoria);

            objGravar.flush();

            objGravar.close();

            fileOutputStream.flush();

            fileOutputStream.close();

        }
    
    /**
     * Método responsável por recuperar os objetos serializados.
     * @param nome
     * recebe o nome do arquivo.
     * @return
     * retorna a categoria que estava serializada.
     * @throws FileNotFoundException 
     * Lança uma exceção caso o arquivo com o nome especificado não for encontrado.
     */
    public static canais.Categoria recuperarObjetoSerializado(String nome) throws FileNotFoundException{
        canais.Categoria categoria = null;
        
        
        //Carrega o arquivo
        FileInputStream arquivoLeitura = new FileInputStream(nome + ".txt");

        //Classe responsavel por recuperar os objetos do arquivo
        ObjectInputStream objLeitura;
        try {
            objLeitura = new ObjectInputStream(arquivoLeitura);
            
            categoria = (canais.Categoria) objLeitura.readObject();

            objLeitura.close();

            arquivoLeitura.close();
        } catch (IOException ex) {
            Logger.getLogger(Serializacao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Serializacao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return categoria;
    }
    
}
