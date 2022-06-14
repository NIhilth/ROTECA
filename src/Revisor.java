import java.util.ArrayList;

public class Revisor extends Pessoa {

    ArrayList<Livro> listaLivros = new ArrayList<>();

    public Revisor(String nome, String sobrenome, String cpf, String email, String genero, String senha) {
        super(nome, sobrenome, cpf, email, genero, senha);
    }

    @Override
    public String toString() {
        return "Revisor: " + super.toString();
    }

    @Override
    public String listarLivros() {
        String tudo = "";
        for (Livro livro : Livro.listaLivros) {
            if (livro.getStatus() == 2) {
                tudo += livro.toString();
            }
        }

        return tudo;
    }

    @Override
    public String listarAtividades() {
        String tudo = "";
        for (Livro livro : listaLivros) {
            double porcentagem = (double) (livro.getPaginaRevisor() * 100) / livro.getQtdPagina();
            tudo += livro.toString() + "Procentagem de revisão: " + porcentagem + "% \n";
        }
        return tudo;
    }

    @Override
    public int checarLista(String isbn, int escolha) {
        if (escolha == 1) {
            for (int i = 0; i < listaLivros.size(); i++) {
                if (listaLivros.get(i).getStatus() == 3 && listaLivros.get(i).getISBN().equals(isbn)) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < Livro.listaLivros.size(); i++) {
                if (Livro.listaLivros.get(i).getStatus() == 2 && Livro.listaLivros.get(i).getISBN().equals(isbn)) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public String[] opcoes() {
        return new String[]{"1 - Listar livros", "2 - Editar livros", "3 - Listar Atividades", "4 - Deslogar", "5 - Encerrar programa"};
    }

    @Override
    public void editarStatus(int indice,int escolha) {
        int pagina = -1, status = 3;
        int opcao = Main.processoStatus();

        switch (opcao) {
            case 1 -> status = 4;
            case 2 -> status = 5;
            case 3 -> {
                pagina = Main.perguntaPagina();
            }
            case 4 -> status = 1;
            case 5 -> status = 2;
            default -> {
                Main.editarLivros();
            }
        }
        ((Revisor) Main.usuario).alterarStatus(indice, escolha, status, pagina);
    }

    public void alterarStatus(int indice, int escolha, int status, int pagina) {
        if(escolha == 1){
            listaLivros.get(indice).setStatus(status);
            if (status != 3) {
                listaLivros.remove(indice);
            } else {
                listaLivros.get(indice).setPaginaRevisor(pagina);
            }
        } else {
            Livro.listaLivros.get(indice).setStatus(status);
            if(pagina != -1){
                Livro.listaLivros.get(indice).setPaginaRevisor(pagina);
            }
            listaLivros.add(Livro.listaLivros.get(indice));
        }
    }
}
