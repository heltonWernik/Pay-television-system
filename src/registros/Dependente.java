
package registros;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Dependente extends Usuario {
    private int idTitular;
    
    public Dependente(String nome, String telefone, String endereco, int idTitular) {
        super(nome, telefone, endereco);
        this.idTitular = idTitular;
    }
     //Sobrecarda de construtores.
    public Dependente(int id, String nome, String telefone, String endereco, int idTitular) {
        super(nome, telefone, endereco);
        setId(id);
        this.idTitular = idTitular;
    }
    
    /**
     * Método responsável por salvar as informações do Dependente no banco de dados.
     * @param connection
     * Recebe uma instancia da classe Connection com os dados do banco.
     * @return 
     * Retorno true se tudo estiver ok.
     */
    public boolean salvarDependente(Connection connection){
        StringBuilder query = new StringBuilder("INSERT INTO `db_sistema`.`dependetes` (`idTitular`, `nome`, `telefone`, `endereco`) VALUES (?,?,?,?);");
        boolean retorno = true;
        
        try {
            PreparedStatement preparedStatiment = connection.prepareStatement(query.toString());
            preparedStatiment.setInt(1, this.idTitular);
            preparedStatiment.setString(2, this.getNome());
            preparedStatiment.setString(3, this.getTelefone());
            preparedStatiment.setString(4, this.getEndereco());
            preparedStatiment.executeUpdate();
        } catch (SQLException ex) {
            retorno = false;
            Logger.getLogger(Dependente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    /**
     * Método de consulta de dependentes vinculados a algum titular.
     * @param titular
     * Recebe uma instancia da classe Titular para que seja obtido o ID do mesmo.
     * @param connection
     * Recebe uma instancia da classe Connection com os dados do banco.
     * @return 
     * Retorno true se tudo estiver ok.
     */
    public static Dependente[] consultarDependentes(Titular titular, Connection connection){
        StringBuilder query = new StringBuilder("SELECT * FROM dependetes where idTitular = ?;");
        Dependente[] arrayDeDependentes = null;
        ArrayList<Dependente> arrayListDeDependentes = new ArrayList();
        
        try {
            PreparedStatement preparedStatiment = connection.prepareStatement(query.toString());
            preparedStatiment.setInt(1, titular.getId());
            ResultSet resultSet = preparedStatiment.executeQuery();
            
            while(resultSet.next()){
                arrayListDeDependentes.add(new Dependente(resultSet.getInt("id"), resultSet.getString("nome"), resultSet.getString("telefone"), resultSet.getString("endereco"),
                resultSet.getInt("idTitular")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Dependente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        arrayDeDependentes = new Dependente[arrayListDeDependentes.size()];
        
        for(int i = 0; i < arrayListDeDependentes.size(); i++){
            arrayDeDependentes[i] = arrayListDeDependentes.get(i);
        }
        
        return arrayDeDependentes;
    }
    
    /**
     * Método que deleta algum determinado dependente.
     * @param dependente
     * Recebe umas instancia da classe Dependente.
     * @param connection
     * Recebe uma instancia da classe Connection com os dados do banco.
     * @return 
     * Retorno true se tudo estiver ok.
     */
    public static boolean deletarDependente(Dependente dependente, Connection connection){
        StringBuilder query = new StringBuilder("DELETE FROM `dependetes` WHERE `id`=?;");
        
        boolean retorno = true;
        
        try {
            PreparedStatement preparedStatiment = connection.prepareStatement(query.toString());
            preparedStatiment.setInt(1, dependente.getId());
            preparedStatiment.executeUpdate();
        } catch (SQLException ex) {
            retorno = false;
            Logger.getLogger(Dependente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    /**
     *Método sobrescrito que printa as informações do dependente.
     */
    @Override
    public void consultarDados() {
        super.consultarDados();
    }

    public int getIdTutular() {
        return idTitular;
    }

    public void setIdTutular(int idTutular) {
        this.idTitular = idTutular;
    }
}
