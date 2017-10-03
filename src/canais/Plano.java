package canais;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Plano {
    
    /**
     * Método responsável por regatar na base de dados os programas vinculados ao ID do titular.
     * @param titular
     * Recebe uma instancia da classe Titular para que seja obtido o ID do mesmo.
     * @param connection
     * Recebe uma instancia da classe Connection com os dados do banco.
     * @return 
     * Retorna um array de Canais;
     */
    public static Canal[] consultarCanaisNoPlano(registros.Titular titular, Connection connection){
        Canal[] retorno = null;
        ArrayList<Canal> arrayDeCanais = new ArrayList();
        StringBuilder query = new StringBuilder("SELECT * FROM db_sistema.planos where idTitular = ?;");
        
        try {
            PreparedStatement preparedStatiment = connection.prepareStatement(query.toString());
            preparedStatiment.setInt(1, titular.getId());
            ResultSet resultSet = preparedStatiment.executeQuery();
            
            while(resultSet.next()){
                arrayDeCanais.add(new Canal(resultSet.getString("nomeCanal"),resultSet.getInt("numero")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Plano.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(arrayDeCanais.size() > 0){
            retorno = new Canal[arrayDeCanais.size()];
        }
        
        for(int i = 0; i < arrayDeCanais.size(); i++){
            retorno[i] = arrayDeCanais.get(i);
        }
        
        return retorno;
    }
    
    /**
     * Método que vincula os canais aos titulares.
     * @param canal
     * Recebe uma instancia da classe Canal.
     * @param titular
     * Recebe uma instancia da classe Titular.
     * @param connection
     * Recebe uma instancia da classe Connection com os dados do banco.
     * @return 
     * Retorno true se tudo estiver ok.
     */
    public static boolean adicionarCanalAoPlano(Canal canal,registros.Titular titular, Connection connection){
        StringBuilder query = new StringBuilder("INSERT INTO `planos` (`nomeCanal`, `numero`, `idTitular`) VALUES (?, ?, ?);");
        boolean retorno = true;
        
        if(!verificarSeOCanalJaEstarNoPlano(canal,titular,connection)){
        
            try {
               PreparedStatement preparedStatiment = connection.prepareStatement(query.toString());
               preparedStatiment.setString(1, canal.getNome());
               preparedStatiment.setInt(2, canal.getNumero());
               preparedStatiment.setInt(3, titular.getId());
               preparedStatiment.executeUpdate();
            } catch (SQLException ex) {
               retorno = false;
               Logger.getLogger(Plano.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }else{
            retorno = false;
            System.out.println("Este canal já estar em seu plano.");
        }
        
        return retorno;
    }
    
    
    /**
     * Método que verifica se o canal já estar no plano do titilar.
     * @param canal
     * Recebe umas instancia da classe Canal.
     * @param titular
     * Recebe uma instancia da classe Titular.
     * @param connection
     * Recebe uma instancia da classe Connection com os dados do banco.
     * @return 
     * Retorno true se tudo estiver ok.
     */
    private static boolean verificarSeOCanalJaEstarNoPlano(Canal canal,registros.Titular titular, Connection connection){
        StringBuilder query = new StringBuilder("SELECT * FROM db_sistema.planos where idTitular = ?;");
        boolean retorno = false;
        
        try {
            PreparedStatement preparedStatiment = connection.prepareStatement(query.toString());
            preparedStatiment.setInt(1, titular.getId());
            ResultSet resultSet = preparedStatiment.executeQuery();
            
            while(resultSet.next()){
                if(resultSet.getInt("numero") == canal.getNumero()){
                    retorno = true;
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Plano.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return retorno;
    }
        
}
