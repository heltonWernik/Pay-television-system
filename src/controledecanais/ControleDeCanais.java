package controledecanais;


import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ControleDeCanais {
    private static Scanner entrada;
    private static registros.Titular usuarioLogado;
    private static registros.Dependente[] dependentes;
    private static canais.Categoria[] categorias;
    private static canais.Canal[] canaisNoPlano;
    
    /**
     * Construtor da classe que inicia alguns componentes.
     */
    public ControleDeCanais(){
        banco.Conexao.connect();//Chamando o método responsável pela conexão com o banco de dados. 
        entrada = new Scanner(System.in);//Iniciando o objeto/instancia.
        categorias = new canais.Categoria[4];//Iniciando o array de Categorias.
        
        //Categorias////////////////////////////////////////////////////////////
        canais.Categoria filmesSeries = new canais.Categoria("Filmes e Series");
        canais.Categoria esportes = new canais.Categoria("Esportes");
        canais.Categoria infantis = new canais.Categoria("Infantis");
        canais.Categoria cultura = new canais.Categoria("Cultura");
        ////////////////////////////////////////////////////////////////////////
        
        try {
            filmesSeries = (canais.Categoria) Serializacao.recuperarObjetoSerializado(filmesSeries.getNome()); //Tentando obter o objeto serializado.
            categorias[0] = filmesSeries;
        } catch (Exception ex) {//Caso o arquivo não for encontrado será criado os objetos(canais) canais novamente.
            //Canais filmes e series////////////////////////////////////////////////
            canais.Canal fox = new canais.Canal("FOX HD", 1);
            fox.adicionarProgramas(new canais.Programa("The Walking Dead", "Serie com temática de zumbis."));
            fox.adicionarProgramas(new canais.Programa("CSI", "Serie de investigação e ficção cientifica.."));
        
            canais.Canal megapix  = new canais.Canal("Megapix HD", 2);
            megapix.adicionarProgramas(new canais.Programa("Transformers - a vingança dos derrotados", "Filme de ficção cientifica."));
            megapix.adicionarProgramas(new canais.Programa("Kung Fu Panda 2", "Filme de animação focado no humor."));
        
            canais.Canal hbo = new canais.Canal("HBO HD", 3);
            hbo.adicionarProgramas(new canais.Programa("Breaking bad", "Serie de ficção cientifica."));
            hbo.adicionarProgramas(new canais.Programa("Jogos Vorazes", "Filme de drama e ação."));
        
            canais.Canal maxPrime = new canais.Canal("Max Prime e", 4);
            maxPrime.adicionarProgramas(new canais.Programa("Vingadores 2", "Filme de ação."));
            maxPrime.adicionarProgramas(new canais.Programa("O Jogo da Imitação", "Filme de drama e fatos históricos."));
            ////////////////////////////////////////////////////////////////////////
            
            //Adicionando os canais as suas respectiva categoria.
            filmesSeries.adicionarCanais(fox);
            filmesSeries.adicionarCanais(megapix);
            filmesSeries.adicionarCanais(hbo);
            filmesSeries.adicionarCanais(maxPrime);
            
            categorias[0] = filmesSeries;
            try {
                Serializacao.serializar(filmesSeries);//Serializando o objeto(categoria)
            } catch (IOException ex1) {
                Logger.getLogger(ControleDeCanais.class.getName()).log(Level.SEVERE, null, ex1);
            }
        
        }
        
        try {
            esportes = (canais.Categoria) Serializacao.recuperarObjetoSerializado(esportes.getNome());//Tentando obter o objeto serializado.
            categorias[1] = esportes;
        } catch (Exception ex) {//Caso o arquivo não for encontrado será criado os objetos(canais) canais novamente.
            //Canais Esporte////////////////////////////////////////////////////////
            canais.Canal sportv = new canais.Canal("Sportv HD", 5);
            sportv.adicionarProgramas(new canais.Programa("Brasileirão 2", "Campeonato brasileiro de futebol."));
            sportv.adicionarProgramas(new canais.Programa("UFC", "Campeonato de luta."));
        
            canais.Canal sportv2 = new canais.Canal("Sportv 2 HD", 6);
            sportv2.adicionarProgramas(new canais.Programa("WWE", "Campeonato de luta livre."));
            sportv2.adicionarProgramas(new canais.Programa("UFC", "Campeonato de luta."));
        
            canais.Canal sportv3 = new canais.Canal("Sportv 3 HD", 7);
            sportv3.adicionarProgramas(new canais.Programa("LNB", "Campeonato de basquete."));
            sportv3.adicionarProgramas(new canais.Programa("CBV", "Campeonato de vólei."));
        
            canais.Canal woohoo = new canais.Canal("Woohoo", 8);
            woohoo.adicionarProgramas(new canais.Programa("CBJ", "Campeonato de judô."));
            woohoo.adicionarProgramas(new canais.Programa("UCI", "Campeonato de bike."));
            ////////////////////////////////////////////////////////////////////////
            
            //Adicionando os canais as suas respectiva categoria.
            esportes.adicionarCanais(sportv);
            esportes.adicionarCanais(sportv2);
            esportes.adicionarCanais(sportv3);
            esportes.adicionarCanais(woohoo);
            
            categorias[1] = esportes;
            try {
                Serializacao.serializar(esportes);//Serializando o objeto(categoria)
            } catch (IOException ex1) {
                Logger.getLogger(ControleDeCanais.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        
        try {
            infantis = (canais.Categoria) Serializacao.recuperarObjetoSerializado(infantis.getNome());//Tentando obter o objeto serializado.
            categorias[2] = infantis;
        } catch (Exception ex) {//Caso o arquivo não for encontrado será criado os objetos(canais) canais novamente.
            //Canais Infantis////////////////////////////////////////////////////////
            canais.Canal discoveryKids = new canais.Canal("Discovery Kids HD", 9);
            discoveryKids.adicionarProgramas(new canais.Programa("Bob esponja.", "Animação recomentada para crianças de 3 a 12 anos."));
            discoveryKids.adicionarProgramas(new canais.Programa("Bem 10", "Animação recomentada para crianças de 8 a 14 anos."));
        
            canais.Canal disneyChannel = new canais.Canal("Disney Channel HD", 10);
            disneyChannel.adicionarProgramas(new canais.Programa("Casa do Mickey", "Animação recomentada para crianças de 2 a 8 anos."));
            disneyChannel.adicionarProgramas(new canais.Programa("Looney Tunes", "Animação recomentada para crianças de 2 a 9 anos."));
        
            canais.Canal disneyJunior = new canais.Canal("Disney Junior", 11);
            disneyJunior.adicionarProgramas(new canais.Programa("Peppa Pig", "Animação recomentada para crianças de 2 a 5 anos."));
            disneyJunior.adicionarProgramas(new canais.Programa("Dora Aventureira", "Animação recomentada para crianças de 2 a 7 anos."));
        
            canais.Canal gloob = new canais.Canal("Gloob HD", 12);
            gloob.adicionarProgramas(new canais.Programa("Peixonauta ", "Animação recomentada para crianças de 2 a 6 anos."));
            gloob.adicionarProgramas(new canais.Programa("Princesinha Sofia", "Animação recomentada para crianças de 6 a 13 anos."));
            ////////////////////////////////////////////////////////////////////////
            
            //Adicionando os canais as suas respectiva categoria.
            infantis.adicionarCanais(discoveryKids);
            infantis.adicionarCanais(disneyChannel);
            infantis.adicionarCanais(disneyJunior);
            infantis.adicionarCanais(gloob);
            
            categorias[2] = infantis;
            try {
                Serializacao.serializar(infantis);//Serializando o objeto(categoria)
            } catch (IOException ex1) {
                Logger.getLogger(ControleDeCanais.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        
        try {
            cultura = (canais.Categoria) Serializacao.recuperarObjetoSerializado(cultura.getNome());
            categorias[3] = cultura;
        } catch (Exception ex) {
            //Canais Cultura////////////////////////////////////////////////////////
            canais.Canal discoveryChannel = new canais.Canal("Discovery Channel HD", 13);
            discoveryChannel.adicionarProgramas(new canais.Programa("Largados e Pelados", "Programa de sobrevivência."));
            discoveryChannel.adicionarProgramas(new canais.Programa("Febre do Ouro.","Programa de grama."));
        
            canais.Canal historyChannel = new canais.Canal("History Channel HD", 14);
            historyChannel.adicionarProgramas(new canais.Programa("Guerras","Programa com conteúdo histórico."));
            historyChannel.adicionarProgramas(new canais.Programa("Alienígenas do passado.","Programa de ficção cientifica."));
        
            canais.Canal h2 = new canais.Canal("H2 HD", 15);
            h2.adicionarProgramas(new canais.Programa("Perdidos no Deserto","Programa de sobrevivência"));
            h2.adicionarProgramas(new canais.Programa("Protetores de Baleias","Programa baseado em imagens reais."));
        
            canais.Canal nationalGeographic = new canais.Canal("National Geographic HD", 16);
            nationalGeographic.adicionarProgramas(new canais.Programa("Desastres Naturais","Programa baseado em fatos reais."));
            nationalGeographic.adicionarProgramas(new canais.Programa("Monstros do Mar","Programa de relatos e fatos sem fundamento."));
            ////////////////////////////////////////////////////////////////////////
        
            cultura.adicionarCanais(discoveryChannel);
            cultura.adicionarCanais(historyChannel);
            cultura.adicionarCanais(h2);
            cultura.adicionarCanais(nationalGeographic);
            categorias[3] = cultura;
            try {
                Serializacao.serializar(cultura);
            } catch (IOException ex1) {
                Logger.getLogger(ControleDeCanais.class.getName()).log(Level.SEVERE, null, ex1);
            }
        
        }
    }
    
    /**
     * Método responsável por exibir a tela inicial o console.
     * @return 
     * Retorno um numero inteiro correspondente a opção escolhida. 
     */
    private static int exibirTelaInicial(){
        int opcao = 0; //Variável inteira que armazena o numero correspondente a opção que o usuário digitar.
        boolean controleLoop = false; //Variável logica que controla o looping do-while.
        System.out.println("------------BEM VINDO------------ \n1) Logar \n2) Registrar\n"); 
        
        
        do{
            
            controleLoop = false;//A cada volta no loop essa variável volta a ser false.
            System.out.print("Opção desejada: ");
            
            try{
                
                opcao = Integer.valueOf(entrada.nextLine());//Esperando o input do usuário
                 
                 if(opcao < 1 || opcao > 2){//Condição que verifica se o valor digitado e menor que 1 ou maior que 2.
                    System.out.println("Opção invalida !(digite 1 ou 2)\n");
                    controleLoop = true;//A variável controleLoop passa a ser true, isso quer dizer que o looping deverá ser executado novamente.
                }
                 
            }catch(NumberFormatException e){//Caso o usuário não digite um número inteiro esse bloco de comando será executado.
                System.out.println("Opção invalida! (digite valores numéricos )\n");
                controleLoop = true;//A variável controleLoop passa a ser true, isso quer dizer que o looping deverá ser executado novamente.
            }
           
        }while(controleLoop);
        
        return opcao;//Retornondo o valor.
    }
    
    /**
     * Método responsável por exibir e efetuar o login do titular.
     * @return 
     * Retorno um objeto/instancia da classe tirular.
     */
    private static registros.Titular exibirLogin(){
        StringBuilder senha = new StringBuilder();//Armazena a senha que o usuário digitar
        StringBuilder nome = new StringBuilder();//Armazena o nome que o usuário digitar
        boolean controleLoop = false; //Variável logica que controla o looping do-while.
        registros.Titular titular = null;//objeto/instancia usada para retorno.
        
        System.out.println("\n-------------LOGIN-------------\n");
        
        do{
        
           do{
             controleLoop = false;
             System.out.print("NOME : " );
           
             nome.append(entrada.nextLine());
           
              if(nome.length() > 50){
                  System.out.println("\nO nome deve conter menos de 50 caracteres!\n");
                  controleLoop = true;
                  nome.delete(0, nome.length());
              }
           
           }while(controleLoop);
        
           do{
              controleLoop = false;//A cada volta no loop essa variável volta a ser false.
              System.out.print("SENHA :");
           
              try{
                   senha.append(entrada.nextLine());//Esperando o imput do usuário
               
               }catch(InputMismatchException e){//Caso o usuário não digite caracteres validos esse bloco será executado.
                  System.out.println("CARACTERES INVALIDOS!");
                  controleLoop = true;//A variável controleLoop passa a ser true, isso quer dizer que o looping deverá ser executado novamente.
                  senha.delete(0, senha.length());//Deletando os dados da String.
               }
           }while(controleLoop);
        
            titular = registros.Titular.efetuarLogin(nome.toString(),senha.toString(), banco.Conexao.getConnection());//Chamando o método estático efetuarLogin() da classe Titular.
                   
            if(titular == null){//Se o objeto titular for null isso quer dizer que nenhum titular foi encontrado com aquela senha.
                    System.out.println("\nVERIFIQUE AS INFORMAÇÕES E TENTE NOVAMENTE!\n\n");
            }else{
                   System.out.println("\n\nBEM VINDO " + titular.getNome());
            }
           
        }while(titular == null);
        
        //Iniciando o objeto/instancia através do método consultarDependentes().
        dependentes = registros.Dependente.consultarDependentes(titular, banco.Conexao.getConnection());
        //Iniciando o objeto/instancia através do método consultarCanaisNoPlano().
        canaisNoPlano = canais.Plano.consultarCanaisNoPlano(titular, banco.Conexao.getConnection());
        
        return titular;
    }
    
    /**
     * Método responsável por exibir a tela de registro de titulares.
     * @return 
     * Retorno true caso tudo estiver ok.
     */
    private static boolean exibirTelaDeRegistroTitular(){
        StringBuilder nome = new StringBuilder();
        StringBuilder telefone = new StringBuilder();
        StringBuilder endereco = new StringBuilder();
        StringBuilder senha = new StringBuilder();
        boolean retorno = false;
        
       boolean controleLoop = false;
        
        System.out.println("\n------------------TELA DE CADASTRO------------------\n");
        
        do{
           controleLoop = false;
           System.out.print("Nome: ");
           
           //entrada.next();
           
           nome.append(entrada.nextLine());
           
           if(nome.length() > 50){
               System.out.println("\nO nome deve conter menos de 50 caracteres!\n");
               controleLoop = true;
               nome.delete(0, nome.length());
           }
           
        }while(controleLoop);
        
        do{
           controleLoop = false;
           System.out.print("Telefone: ");
           try{
               telefone.append(Integer.valueOf(entrada.nextLine()));
               
               if(telefone.length() != 9){
                   System.out.println("\nTelefone invalido! (digite 9 números)\n");
                   controleLoop = true;
                   telefone.delete(0, telefone.length());
               }
           }catch(NumberFormatException e){
               System.out.println("\nTelefone invalido! (digite 9 números)\n");
               controleLoop = true;
               telefone.delete(0, telefone.length());
           }
               
        }while(controleLoop);
       
        do{
           controleLoop = false;
           System.out.print("Endereço: ");
           endereco.append(entrada.nextLine());
           
           if(endereco.length() > 50){
               System.out.println("\nO endereço deve conter menos de 50 caracteres!\n");
               controleLoop = true;
               endereco.delete(0, telefone.length());
           }
        }while(controleLoop);
        
        do{
           controleLoop = false;
           System.out.print("Senha: ");
           senha.append(entrada.nextLine());
           
           if(senha.length() > 50){
               System.out.println("\nA senha deve conter menos de 50 caracteres!\n");
               controleLoop = true;
               senha.delete(0, telefone.length());
           }
        }while(controleLoop);
       
        if(new registros.Titular(nome.toString(), telefone.toString(), endereco.toString(), senha.toString(), 0).salvarCliente(banco.Conexao.getConnection())){
            System.out.println("\nCliente registrado com sucesso!\n");
            retorno = true;
        }else{
            System.out.println("Algum erro impediu o registro do cliente, verifique o banco de dados.");
        }
        
        return retorno;
    }
    
    /**
     * Método responsável por exibir o menu principal da aplicação.
     * @return 
     * Retorno um inteiro correspondente a opção selecionada pelo usuário.
     */
    private static int exibirMenu(){
        boolean controleLoop = false;
        int opcao = 0;
        
        System.out.println("\n----------------------MENU---------------------- Data : "+ Datas.getDataAtual() + "\n1)  Consultar dados de cliente  \n2)  Consultar canais no plano \n3)  Consultar programas de um canal "
                + "\n4)  Adquirir canal \n5)  Sair.\n");
    
        do{
            controleLoop = false;
            System.out.print("Opção desejada: ");
            
            try{
                opcao = Integer.valueOf(entrada.nextLine());
                
                if(opcao < 1 || opcao > 5){
                    System.out.println("Opção invalida !(digite valores entre 1 e 5)");
                    controleLoop = true;
                }
            }catch(NumberFormatException e){
                System.out.println("Opção invalida! (digite valores numéricos )");
                controleLoop = true;
            }
            
        }while(controleLoop);
        
        return opcao;
    }
    
    /**
     * Método responsável por exibir a tela de informações do Titular/cliente.
     */
    private static void consultarInformacoesDoCliente(){
        int opcao = 0;//Variável inteira que armazena o numero correspondente a opção que o usuário digitar.
        boolean controleLoop = false;//Variável logica que controla o looping do-while.
        
        System.out.println("\n\n--------------------INFORMAÇÕES DO CLIENTE--------------------");
        usuarioLogado.consultarDados();//Chamando o método consultarDados() para exibir os dados do titular/cliente. 
        
        if(dependentes.length > 0){//Condição que verifica se a lista de dependente não estar vazia.
            
           System.out.println("\n--------------------DEPENDENTES--------------------");
        
           for(int i = 0; i < dependentes.length; i++){//Looping para listar os dependentes.
             System.out.println("Dependente " + (i+1));  
             dependentes[i].consultarDados();
             System.out.println("\n");
           }
        }else{
            System.out.println("\n\n");
        }  
           
        
        System.out.println("1) Editar Informações do cliente.\n2) Cadastrar Dependente.\n3) Deletar Dependente.\n4) voltar\n");//Exibindo menu.
        
        do{
          controleLoop = false;
          System.out.print("Opção desejada: ");
          try{
              opcao = Integer.valueOf(entrada.nextLine());
          
          if(opcao < 1 || opcao > 4){
              System.out.println("Opção invalida !(digite valores entre 1 e 4)");
              controleLoop = true;
          }
          
          }catch(NumberFormatException e){
                System.out.println("Opção invalida! (digite valores numéricos )");
                controleLoop = true;
           }
          
        }while(controleLoop);
        
        switch(opcao){
            case 1:
                
                StringBuilder nome = new StringBuilder();
                StringBuilder telefone = new StringBuilder();
                StringBuilder endereco = new StringBuilder();
                StringBuilder senha = new StringBuilder();
                registros.Titular titular;
        
                boolean controleLoop1 = false,controleLoop2 = false, controleLoop3 = false, controleLoop4 = false;
        
                System.out.println("\n------------------TELA DE EDIÇÃO------------------\n");
        
                do{
                   controleLoop1 = false;
                   System.out.print("Nome: ");
                   nome.append(entrada.nextLine());
           
                   if(nome.length() > 50){
                       System.out.println("\nO nome deve conter menos de 50 caracteres!\n");
                       controleLoop1 = true;
                       nome.delete(0, nome.length());
                   }
           
                }while(controleLoop1);
        
                do{
                   controleLoop2 = false;
                   System.out.print("Telefone: ");
                   try{
                       telefone.append(Integer.valueOf(entrada.nextLine()));
               
                       if(telefone.length() != 9){
                           System.out.println("\nTelefone invalido! (digite 9 números)\n");
                           controleLoop2 = true;
                           telefone.delete(0, telefone.length());
                       }
                   }catch(NumberFormatException e){
                       System.out.println("\nTelefone invalido! (digite 9 números)\n");
                       controleLoop2 = true;
                       telefone.delete(0, telefone.length());
                   }
               
                }while(controleLoop2);
       
                do{
                   controleLoop3 = false;
                   System.out.print("Endereço: ");
                   endereco.append(entrada.nextLine());
           
                   if(endereco.length() > 50){
                       System.out.println("\nO endereço deve conter menos de 50 caracteres!\n");
                       controleLoop3 = true;
                       endereco.delete(0, endereco.length());
                   }
                }while(controleLoop3);
        
                do{
                   controleLoop4 = false;
                   System.out.print("Senha: ");
                   senha.append(entrada.nextLine());
           
                   if(senha.length() > 50){
                       System.out.println("\nA senha deve conter menos de 50 caracteres!\n");
                       controleLoop4 = true;
                       senha.delete(0, senha.length());
                   }
                }while(controleLoop4);
       
                if(new registros.Titular(usuarioLogado.getId(),nome.toString(), telefone.toString(), endereco.toString(), senha.toString(), usuarioLogado.getValorGasto()).editarClientes(banco.Conexao.getConnection())){
                    System.out.println("\nCliente editado com sucesso!\n");
                    usuarioLogado = new registros.Titular(usuarioLogado.getId(),nome.toString(), telefone.toString(), endereco.toString(), senha.toString(), usuarioLogado.getValorGasto());
                    dependentes = registros.Dependente.consultarDependentes(usuarioLogado, banco.Conexao.getConnection());
                }else{
                    System.out.println("Algum erro impediu a ediçao do cliente, verifique o banco de dados.");
                }
                
                break;
            case 2:
                telefone = new StringBuilder();
                System.out.println("\n------------------TELA DE CADASTRO DE DEPENDENTE------------------\n");
                
                nome = new StringBuilder();
                
                do{
                   controleLoop1 = false;
                   System.out.print("Nome: ");
                   nome.append(entrada.nextLine());
           
                    if(nome.length() > 50){
                      System.out.println("\nO nome deve conter menos de 50 caracteres!\n");
                      controleLoop1 = true;
                      nome.delete(0, nome.length());
                    }
           
                }while(controleLoop1);
                
                do{
                   controleLoop2 = false;
                   System.out.print("Telefone: ");
                   telefone = new StringBuilder();
                   
                   try{
                       telefone.append(Integer.valueOf(entrada.nextLine()));
               
                        if(telefone.length() != 9){
                           System.out.println("\nTelefone invalido! (digite 9 números)\n");
                           controleLoop2 = true;
                           telefone.delete(0, telefone.length());
                        }
                    }catch(NumberFormatException e){
                       System.out.println("\nTelefone invalido! (digite 9 números)\n");
                       controleLoop2 = true;
                   }
               
                }while(controleLoop2);
                
                endereco = new StringBuilder();
                
                do{
                   controleLoop3 = false;
                   System.out.print("Endereço: ");
                   endereco.append(entrada.nextLine());
           
                    if(endereco.length() > 50){
                      System.out.println("\nO endereço deve conter menos de 50 caracteres!\n");
                      controleLoop3 = true;
                      endereco.delete(0, endereco.length());
                    }
                }while(controleLoop3); 
                
                if(new registros.Dependente(nome.toString(), telefone.toString(), endereco.toString(), usuarioLogado.getId()).salvarDependente(banco.Conexao.getConnection())){
                    System.out.println("\nDependente  registrado com sucesso!\n");
                    dependentes = registros.Dependente.consultarDependentes(usuarioLogado, banco.Conexao.getConnection());
                }else{
                    System.out.println("Algum erro impediu o registro do Dependente, verifique o banco de dados.");
                }
                
                break;
            case 3 :
                
                if(dependentes.length > 0){
                
                   int numeroDoDependente; 
                   boolean controleLoopExclusao = false;
                
                   do{
                       controleLoopExclusao = false;
                       System.out.print("\nNúmero do dependente: ");
                       try{
                          numeroDoDependente = Integer.valueOf(entrada.nextLine());
                          if(numeroDoDependente < 1 || numeroDoDependente > dependentes.length){
                              System.out.println("Opção invalida !(digite valores entre 1 e " + dependentes.length + ")");
                              controleLoopExclusao = true;
                          }else{
                              if(registros.Dependente.deletarDependente(dependentes[numeroDoDependente - 1], banco.Conexao.getConnection())){
                                  System.out.println("\nDependente deletado com sucesso!\n");
                                  dependentes = registros.Dependente.consultarDependentes(usuarioLogado, banco.Conexao.getConnection());
                              }else{
                                  System.out.println("Algum erro impediu a exclusão do dependente, verifique o banco de dados.");
                              }
                          }
                       
                       }catch(NumberFormatException e){
                          System.out.println("Opção invalida! (digite valores numéricos )");
                          controleLoopExclusao = true;
                       }
                   }while(controleLoopExclusao);
                
                }else{
                    System.out.println("\nNenhum dependente cadastrado!\n");
                }
                
                break;
            case 4 :
                break;
        }
    }
    
    /**
     * Método responsável por exibir a tela de consulta de programas.
     */
    private static void consultarProgramasDeUmCanal(){
         System.out.println("----------------------CONSULTAR PROGRAMAS----------------------\n\n-----------CATEGORIAS-----------\n");
         boolean controleLoop = false;
         int numeroCategoria = 0;
         int numeroCanal = 0;
         
         for(int i = 0; i < categorias.length; i++){
             System.out.println((i+1) + ") " + categorias[i].getNome());
         }
         
         
         
         do{
            System.out.print("\nOpção desejada: "); 
             
            controleLoop = false;
            try{
                 numeroCategoria = Integer.valueOf(entrada.nextLine());
                 
                 if(numeroCategoria < 1 || numeroCategoria > categorias.length){
                    System.out.println("Opção invalida !(digite valores entre 1 e 4)");
                    controleLoop = true;
                }
                 
            }catch(InputMismatchException e){
                System.out.println("Opção invalida! (digite valores numéricos )");
                controleLoop = true;
            }
           
        }while(controleLoop);
         
        System.out.println("\n-----------CANAIS-----------\n");
        
        
        categorias[numeroCategoria - 1].listarCanais();
        
        
        
        do{
            System.out.print("\nOpção desejada: ");
            
            controleLoop = false;
            try{
                 numeroCanal = Integer.valueOf(entrada.nextLine());
                 
                 if(numeroCanal < 1 || numeroCanal > categorias.length){
                    System.out.println("Opção invalida !(digite valores entre 1 e 4)");
                    controleLoop = true;
                }
                 
            }catch(NumberFormatException e){
                System.out.println("Opção invalida! (digite valores numéricos )");
                controleLoop = true;
            }
           
        }while(controleLoop);
    
        System.out.println("\n-----------PROGRAMAS-----------\n");
        
        categorias[numeroCategoria - 1].getCanais().get((numeroCanal - 1)).listarProgramas();
    }
    
    /**
     * Método responsável por exibir a tela de consulta canais no plano do titular.
     */
    private static void consultarCanaisNoPlano(){
         
         if(canaisNoPlano != null){
             System.out.println("\n------------------CANAIS NO PLANO------------------\n");
             for(int i = 0; i < canaisNoPlano.length; i++){
                 System.out.println("Nome: " + canaisNoPlano[i].getNome() + " | Número: " + canaisNoPlano[i].getNumero());
             }
         }else{
             System.out.println("\nVocê ainda não adquiriu nenhum canal.\n");
         }
    }
    
    /**
     * Método responsável por exibir a tela de contrato de canais..
     */
    private static void adquirirCanal(){
        System.out.println("----------------------ADQUIRIR CANAIS----------------------\nCADA CANAL CUSTA R$50\n\n-----------CATEGORIAS-----------\n");
         boolean controleLoopCategoria = false;
         boolean controleLoopCanais = false;
         int numeroCategoria = 0;
         int numeroCanal = 0;
         
         for(int i = 0; i < categorias.length; i++){
             System.out.println((i+1) + ") " + categorias[i].getNome());
         }
         
         do{
            System.out.print("\nOpção desejada: "); 
             
            controleLoopCategoria = false;
            try{
                 numeroCategoria = Integer.valueOf(entrada.nextLine());
                 
                 if(numeroCategoria < 1 || numeroCategoria > categorias.length){
                    System.out.println("Opção invalida !(digite valores entre 1 e 4)");
                    controleLoopCategoria = true;
                }
                 
            }catch(NumberFormatException e){
                System.out.println("Opção invalida! (digite valores numéricos )");
                controleLoopCategoria = true;
            }
           
        }while(controleLoopCategoria);
         
        System.out.println("\n-----------CANAIS-----------\n");
        categorias[numeroCategoria - 1].listarCanais();
        
        do{
            System.out.print("\nOpção desejada: ");
            
            controleLoopCanais = false;
            try{
                 numeroCanal = Integer.valueOf(entrada.nextLine());
                 
                 if(numeroCanal < 1 || numeroCanal > categorias.length){
                    System.out.println("Opção invalida !(digite valores entre 1 e 4)");
                    controleLoopCanais = true;
                }
                 
            }catch(NumberFormatException e){
                System.out.println("Opção invalida! (digite valores numéricos )");
                controleLoopCanais = true;
            }
           
        }while(controleLoopCanais);
        
        if(canais.Plano.adicionarCanalAoPlano(categorias[numeroCategoria - 1].getCanais().get((numeroCanal - 1)), usuarioLogado, banco.Conexao.getConnection())){
            System.out.println("\nCanal adquirido com sucesso.\n");
            canaisNoPlano = canais.Plano.consultarCanaisNoPlano(usuarioLogado, banco.Conexao.getConnection());
            usuarioLogado.contabilizarGasto(50,banco.Conexao.getConnection());
            usuarioLogado.setValorGasto(usuarioLogado.getValorGasto() + 50);
        }else{
             System.out.println("Algum erro impediu a finalização do processo, verifique o banco de dados.");
        }
    }
    
    /**
     * Método principal. 
     */
    public static void main(String[] args) {
            new ControleDeCanais();//Chamando o método construtor da classe.
            
            switch(exibirTelaInicial()){
               case 1:
                 usuarioLogado = exibirLogin();
                 break;
               case 2:
                 exibirTelaDeRegistroTitular();
                 usuarioLogado = exibirLogin();
                 break;
            }
            
            while(true){
                switch(exibirMenu()){
                    case 1:
                        consultarInformacoesDoCliente();
                        break;
                    case 2:
                         consultarCanaisNoPlano();
                         break;
                    case 3:
                         consultarProgramasDeUmCanal();
                         break;
                    case 4:
                       adquirirCanal();
                       break;
                    case 5:
                      System.exit(0);
                      break;
                }
            }
    }
}
