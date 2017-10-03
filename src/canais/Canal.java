package canais;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe que contem atributos comuns em um canal de TV.
 * @Helton Wernik 
 */
public class Canal implements Serializable{
    private String nome;
    private int numero;
    
    private ArrayList<Programa> programas;

    //Construtor que inicia os atributos.
    public Canal(String nome, int numero) {
        programas = new ArrayList();
        this.nome = nome;
        this.numero = numero;
    }
    
    /**
     * Método responsável por listar os programas do canal.
     */
    public void listarProgramas(){
        if(!programas.isEmpty()){
            for(int i = 0; i < programas.size(); i++){
               System.out.println((i+1) +") Nome : " + programas.get(i).getNome() + " | Descrição : " + programas.get(i).getDescricao());
            }
        }else{
            System.out.println("Lista de programas vazia!");
        }
    }

    /**
     * Método que adiciona programas ao canal.
     * @param programa
     * Recebe uma instancia da classe Programa.
     * @return 
     * Retorno true caso a instancia não for nula.
     */
    public boolean adicionarProgramas(Programa programa){
       if(programa != null){
           programas.add(programa);
           return true;
       }else{
           return false;
       }
    }
    
    public ArrayList<Programa> getProgramas() {
        return programas;
    }
    
   
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}
