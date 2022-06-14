
public class Editora {

    private String nome;
    private int CNPJ;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(int CNPJ) {
        this.CNPJ = CNPJ;
    }

    public Editora(String nome, int CNPJ) {
        this.nome = nome;
        this.CNPJ = CNPJ;
    }

    @Override
    public String toString() {
        return "\nNome: " + nome +
                "\nCNPJ: " + CNPJ;
    }
}
