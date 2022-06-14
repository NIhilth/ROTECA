import java.util.ArrayList;

public class Autor extends Pessoa{

    ArrayList<Livro> listaLivros = new ArrayList<>();

    public Autor(String nome, String sobrenome, String cpf, String email, String genero, String senha) {
        super(nome, sobrenome, cpf, email, genero, senha);
    }

    @Override
    public String toString() {
        return "Autor: " + super.toString();
    }

    @Override
    public String listarLivros(){
        String tudo = "";
        for(Livro livro: listaLivros){
            tudo += livro.toString(1);
        }

        return tudo;
    }

    @Override
    public String listarAtividades() {
        String tudo = "";
        for(Livro livro: listaLivros){
            if(livro.getStatus() == 1){
                tudo += livro.toString(1);
            }
        }
        return tudo;
    }

    @Override
    public int checarLista(String isbn, int escolha) {
        for(int i = 0; i < listaLivros.size(); i++){
            if(listaLivros.get(i).getISBN().equals(isbn) && listaLivros.get(i).getStatus() == 1){
                return i;
            }
        }
        return -1;
    }

    @Override
    public String[] opcoes() {
        return new String[]{"1 - Listar livros", "2 - Editar livros" ,"3 - Listar Atividades" ,"4 - Cadastrar livros", "5 - Deslogar", "6 - Encerrar programa"};
    }

    @Override
    public void editarStatus(int indice, int escolha) {
        int opcao = Main.processoStatus();
        if(opcao == 1){
            ((Autor) Main.usuario).alterarStatus(indice);
        }
    }

    public void alterarStatus(int indice) {
        listaLivros.get(indice).setStatus(2);
    }
}
