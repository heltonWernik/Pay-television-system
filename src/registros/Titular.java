package registros;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe que contem atributos comuns de um titular.
 * @Helton Wernik
 */
public class Titular extends Usuario {
    private String senha;
    private double valorGasto;
    private  DecimalFormat decimal;
    
    public Titular(String nome, String telefone, String endereco,String senha, double valorGasto) {
        super(nome, telefone, endereco);
        this.senha = senha;
        this.valorGasto = valorGasto;
        decimal = new DecimalFormat();
        decimal.applyPattern("¤#,##0.00");
    }
    //Sobrecarga de construtores.
    public Titular(int id,String nome, String telefone, String endereco,String senha, double valorGasto) {
        super(nome, telefone, endereco);
        setId(id);
        this.senha = senha;
        this.valorGasto = valorGasto;
        decimal = new DecimalFormat();
        decimal.applyPattern("¤#,##0.00");
    }
    
    /**
     * Método sobrescrito que printa as informações do dependente.
     */
    @Override
    public void consultarDados() {
        super.consultarDados();
        StringBuilder exibe = new StringBuilder();
        exibe.append("Senha : ");
        exibe.append(this.senha.replaceAll(".", "*"));
        exibe.append("\nValor gasto na plataforma : ");
        exibe.append(decimal.format(this.valorGasto));
        System.out.println(exibe);
    }
    
    /**
     * Método responsável por efetuar o login do titular. 
     * @param nome
     * Recebe o nome do titular.
     * @param senha
     * Recebe uma String com os caracteres para serem comparados com as informações do banco de dados.
     * @param connection
     * Recebe uma instancia da classe Connection para executar as querys.
     * @return 
     * Retorno uma instancia da classe Titular com os dados referentes do usuário logado.
     * Retorno null caso nenhum usuário seja encontrado.
     */
    public static Titular efetuarLogin(String nome, String senha, Connection connection){
        StringBuilder query = new StringBuilder("SELECT * FROM titulares where senha = ? and nome = ?;");
        Titular titular = null;
        
        try {
            PreparedStatement preparedStatiment = connection.prepareStatement(query.toString());
            preparedStatiment.setString(1, senha);
            preparedStatiment.setString(2, nome);
            
            ResultSet resultSet = preparedStatiment.executeQuery();
            
            while(resultSet.next()){
                if(senha.equals(resultSet.getString("senha"))){
                    titular = new Titular(resultSet.getInt("id"),resultSet.getString("nome"),resultSet.getString("telefone"),
                    resultSet.getString("endereco"),resultSet.getString("senha"),resultSet.getDouble("valorGasto"));
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Titular.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return titular;
    }
    
    /**
     * Método responsável por salvar as informações do Titular no banco de dados.
     * @param coonection
     * Recebe uma instancia da classe Connection com os dados do banco.
     * @return 
     * Retorno true se tudo estiver ok.
     */
    public boolean salvarCliente(Connection coonection){
        StringBuilder query = new StringBuilder("INSERT INTO `db_sistema`.`titulares` (`nome`, `telefone`, `endereco`, `senha`, `valorGasto`) VALUES (?,?,?,?,?);");
        boolean retorno = true;
        
        try {
            PreparedStatement preparedStatiment = coonection.prepareStatement(query.toString());
            preparedStatiment.setString(1, this.getNome());
            preparedStatiment.setString(2, this.getTelefone());
            preparedStatiment.setString(3, this.getEndereco());
            preparedStatiment.setString(4, this.getSenha());
            preparedStatiment.setDouble(5, this.getValorGasto());
            preparedStatiment.executeUpdate();
        } catch (SQLException ex) {
            retorno = false;
            Logger.getLogger(Titular.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return retorno;
    }
    
    /**
     * Método responsável pela edição das informações dos titulares.
     * @param coonection
     * Recebe uma instancia da classe Connection com os dados do banco.
     * @return 
     * Retorno true se tudo estiver ok.
     */
    public boolean editarClientes(Connection coonection){
        StringBuilder query = new StringBuilder("UPDATE `titulares` SET `nome`=?, `telefone`=?, `endereco`=?, `senha`=?, `valorGasto`=? WHERE `id`=?;");
        boolean retorno = true;
        try {
            PreparedStatement preparedStatiment = coonection.prepareStatement(query.toString());
            preparedStatiment.setString(1, this.getNome());
            preparedStatiment.setString(2, this.getTelefone());
            preparedStatiment.setString(3, this.getEndereco());
            preparedStatiment.setString(4, this.getSenha());
            preparedStatiment.setDouble(5, this.getValorGasto());
            preparedStatiment.setInt(6, getId());
            preparedStatiment.executeUpdate();
        } catch (SQLException ex) {
            retorno = false;
            Logger.getLogger(Titular.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    /**
     * Método responsável por contabilizar os castos na plataforma.
     * @param valorDoCanal
     * Recebe o valor que será adicionado.
     * @param connection
     * Recebe uma instancia da classe Connection com os dados do banco.
     * @return 
     * Retorno true se tudo estiver ok.
     */
    public boolean contabilizarGasto(double valorDoCanal, Connection connection){
        StringBuilder query = new StringBuilder("UPDATE `titulares` SET `valorGasto`= ? WHERE `id`= ?;");
        boolean retorno = true;
        
        try {
               PreparedStatement preparedStatiment = connection.prepareStatement(query.toString());
               preparedStatiment.setDouble(1, (getValorGasto() + valorDoCanal));
               preparedStatiment.setDouble(2, getId());
               preparedStatiment.executeUpdate();
        } catch (SQLException ex) {
               retorno = false;
               Logger.getLogger(Titular.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    public double getValorGasto() {
        return valorGasto;
    }

    public void setValorGasto(double valorGasto) {
        this.valorGasto = valorGasto;
    }
    
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
