package controledecanais;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe para manipulação de datas.
 * @author 
 */
public class Datas {
    
   /**
    * Método responsável por obter a data atual.
    * @return 
    * Retorna uma String com a tata atual.
    */
   public static String getDataAtual(){
       Date data = new Date();
       SimpleDateFormat formatacaoDeData = new SimpleDateFormat("dd/MM/yyyy");
       return formatacaoDeData.format(data);
   } 
}
