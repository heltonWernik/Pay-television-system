package registros;

/**
 * Classe abstrata que comuns atributos comuns de um usuário.
 * @Helton Wernik
 */
public abstract class Usuario {
    private int id;
    private String nome;
    private String telefone;
    private String endereco;
    
    public Usuario(String nome, String telefone, String endereco) {
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
    }
    
    /**
     * Método  que printa as informações.
     */
    public void consultarDados(){
        StringBuilder exibe = new StringBuilder();
        exibe.append("Nome : ");
        exibe.append(this.nome);
        exibe.append("\nTelefone : ");
        exibe.append(this.telefone);
        exibe.append("\nEndereço : ");
        exibe.append(this.endereco);
        System.out.println(exibe);
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
