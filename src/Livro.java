import java.util.ArrayList;

public class Livro {

    static ArrayList<Livro> listaLivros = new ArrayList<>();

    private Autor autor;
    private Editora editora;
    private String titulo, ISBN;
    private int status, qtdPagina, paginaRevisor;

    public int getPaginaRevisor() {
        return paginaRevisor;
    }

    public void setPaginaRevisor(int paginaRevisor) {
        this.paginaRevisor = paginaRevisor;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getQtdPagina() {
        return qtdPagina;
    }

    public void setQtdPagina(int qtdPagina) {
        this.qtdPagina = qtdPagina;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Livro(Autor autor, String titulo, int qtdPagina, String ISBN, int status) {
        this.autor = autor;
        this.titulo = titulo;
        this.qtdPagina = qtdPagina;
        this.ISBN = ISBN;
        this.status = status;
    }

    @Override
    public String toString() {
        return  "\nLivro: " +
                "\nAutor: " + autor.getNome() +
                "\nEditora:" + editora +
                "\nTítulo: " + titulo +
                "\nQuantidade de páginas: " + qtdPagina +
                "\nISBN: " + ISBN +
                "\nStatus: " + status + "\n";
    }

    public String toString(int i) {
        return  "\nLivro: " +
                "\nEditora:" + editora +
                "\nTítulo: " + titulo +
                "\nQuantidade de páginas: " + qtdPagina +
                "\nISBN: " + ISBN +
                "\nStatus: " + status + "\n";
    }

}
