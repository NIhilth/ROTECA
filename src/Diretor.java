public class Diretor extends Pessoa{

    public Diretor(String nome, String sobrenome, String cpf, String email, String genero, String senha) {
        super(nome, sobrenome, cpf, email, genero, senha);
    }

    @Override
    public String toString() {
        return "Diretor: " + super.toString();
    }

    @Override
    public String listarLivros(){
        String tudo = "";
        for(Livro livro : Livro.listaLivros){
            tudo += livro.toString();
        }
        return tudo;
    }

    @Override
    public String listarAtividades(){
        String tudo = "";
        for(Livro livro: Livro.listaLivros){
            if(livro.getStatus() == 4) {
                tudo += livro.toString();
            }
        }
        return tudo;
    }
    @Override
    public int checarLista(String isbn, int escolha) {
        for(int i = 0; i < Livro.listaLivros.size(); i++){
            if(Livro.listaLivros.get(i).getISBN().equals(isbn) && Livro.listaLivros.get(i).getStatus() == 4) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String[] opcoes() {
        return new String[]{"1 - Listar livros", "2 - Editar livros", "3 - Listar atividades", "4 - Cadastrar Revisor " , "5 - Deslogar", "6 - Encerrar programa"};
    }

    @Override
    public void editarStatus(int indice, int escolha) {
        int status = 0, opcao = Main.processoStatus();
        switch (opcao) {
            case 1 -> status = 6;
            case 2 -> status = 5;
            case 3 -> status = 2;
            case 4 -> status = 4;
            default -> {
                Main.editarLivros();
            }
        }
        ((Diretor) Main.usuario).alterarStatus(indice, status);
    }

    public void alterarStatus(int indice, int status) {
        Livro.listaLivros.get(indice).setStatus(status);
        if(status == 6){
            Livro.listaLivros.get(indice).setEditora(Main.editora);
        }
    }
}
