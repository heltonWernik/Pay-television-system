package canais;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe que contem atributos comuns em uma categoria de canais de TV.
 * @Helton Wernik 
 */
public class Categoria implements Serializable{
    private String nome;
    private ArrayList<Canal> canais;

    //Construtor que inicia os atributos.
    public Categoria(String nome) {
        canais = new ArrayList();
        this.nome = nome;
    }
    
    /**
     * Método que adiciona canais a categoria.
     * @param canal
     * Recebe uma instancia da classe Canal.
     * @return 
     * Retorno true caso a instancia não for nula.
     */
    public boolean adicionarCanais(Canal canal){
        if(canal != null){
            canais.add(canal);
            return true;
        }else{
            return false;
        }
    }
    /**
     * Método responsável por listar os canais da categoria.
     */
    public void listarCanais(){
        if(!canais.isEmpty()){
            for(int i = 0; i < canais.size(); i++){
                System.out.println((i + 1) + ") Nome : " + canais.get(i).getNome() + " | Número : " + canais.get(i).getNumero());
            }
        }else{
            System.out.println("Lista de canais vazia!");
        }
    }
    
    public ArrayList<Canal> getCanais() {
        return canais;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

