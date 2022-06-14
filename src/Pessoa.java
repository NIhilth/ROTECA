import java.util.ArrayList;

public abstract class Pessoa {

    static ArrayList<Pessoa> listaPessoas = new ArrayList<>();

    private String nome, sobrenome, cpf, email, genero, senha;

    public abstract String listarLivros();

    public abstract String listarAtividades();

    public abstract int checarLista(String isbn, int escolha);

    public abstract String[] opcoes();

    public abstract void editarStatus(int indiceLivro, int escolha);
    //public abstract void alterarStatus(int índice, int escolha, int status, int pagina)
    //perguntar de métodos/funções que tem em todas as classes mas tem parâmetros diferentes

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Pessoa(String nome, String sobrenome, String cpf, String email, String genero, String senha) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.email = email;
        this.genero = genero;
        this.senha = senha;
    }

    @Override
    public String toString() {
        return  "\nNome: " + nome +
                "\nSobrenome: " + sobrenome +
                "\nCpf: " + cpf +
                "\nEmail: " + email +
                "\nGênero: " + genero +
                "\nSenha: " + senha + "\n";
    }

    public int pegarTipo(int indice) {
        for(int i = 0; i < listaPessoas.size(); i++){
            if(i == indice){
                if(listaPessoas.get(i) instanceof Autor){
                    return 1;
                } else if(listaPessoas.get(i) instanceof Revisor){
                    return 2;
                } else {
                    return 3;
                }
            }
        }
        return -1;
    }
}
