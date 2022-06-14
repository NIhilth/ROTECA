import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static Pessoa usuario;
    static Editora editora = new Editora("Librae", 1);

    public static void main(String[] args) {
        Pessoa diretor = new Diretor("Honório", "Silva", "12345678910", "horario@gmail.com", "Masculino", "123");
        Pessoa.listaPessoas.add(diretor);
        Pessoa revisor = new Revisor("Honórinho", "Silveira", "98765432101", "honorinho@gmail.com", "Masculino", "321");
        Pessoa.listaPessoas.add(revisor);
        Pessoa autor = new Autor("Aldir", "Almeida", "19283746510", "aldoOtario@gmail.com", "Masculino", "987");
        Pessoa autor2 = new Autor("Vanessa", "Carvalho", "91827364501", "vavaXXX@gmail.com", "Feminino", "789");
        Pessoa autor3 = new Autor("Dilma", "Mendez", "56473829101", "dilminhaLula@gmail.com", "Feminino", "879");
        Livro livro1 = new Livro((Autor) autor2, "jorginho da luz", 280, "123", 1);
        Livro livro2 = new Livro((Autor) autor, "versinhos", 540, "132", 1);
        Livro livro3 = new Livro((Autor) autor2, "Amapá 98", 290, "213", 2);
        Livro livro4 = new Livro((Autor) autor3, "Um conto de causos causadores de causos", 620, "231", 4);
        Livro livro5 = new Livro((Autor) autor, "Michael Jackson e os filhos do Jackson", 310, "312", 2);
        ((Autor) autor).listaLivros.add(livro2);
        ((Autor) autor).listaLivros.add(livro5);
        ((Autor) autor2).listaLivros.add(livro1);
        ((Autor) autor2).listaLivros.add(livro3);
        ((Autor) autor3).listaLivros.add(livro4);


        Pessoa.listaPessoas.add(autor3);
        Pessoa.listaPessoas.add(autor2);
        Pessoa.listaPessoas.add(autor);
        Livro.listaLivros.add(livro1);
        Livro.listaLivros.add(livro2);
        Livro.listaLivros.add(livro3);
        Livro.listaLivros.add(livro4);
        Livro.listaLivros.add(livro5);
        try {
            login();
        }catch (LoginInvalidoException idiota){
            System.out.println(idiota.getClass().getSimpleName() + ": " + idiota.getMessage() + "\n");
            login();
        }
    }

    private static void login() {
        System.out.println("Já possui cadastro? \n1 - Sim \n2 - Não");
        int escolha = sc.nextInt();

        if (escolha == 2) {
            try {
                Pessoa.listaPessoas.add(cadastroPessoa(1));
            } catch (OpcaoInvalidaException idiota){
                System.out.println(idiota.getClass().getSimpleName() + ": " + idiota.getMessage() + "\n");
                login();
            }
            System.out.println("Cadastro efetuado com sucesso!");
        }

        System.out.println("----- LOGIN ------");

        System.out.println("Informe seu Email:");
        String email = sc.next();

        System.out.println("Informe sua senha:");
        String senha = sc.next();

        for (int i = 0; i < Pessoa.listaPessoas.size(); i++) {
            if (Pessoa.listaPessoas.get(i).getEmail().equals(email) && Pessoa.listaPessoas.get(i).getSenha().equals(senha)) {
                usuario = Pessoa.listaPessoas.get(i);
            }
        }

        if (usuario == null) {
            throw new LoginInvalidoException();
        } else {
            System.out.println("Login efetuado com sucesso!");
            menu();
        }
    }

    private static Pessoa cadastroPessoa(int i) {
        System.out.println("----- CADASTRO ------");
        System.out.println("Nome:");
        String nome = sc.next();

        System.out.println("Sobrenome:");
        String sobre = sc.next();

        System.out.println("CPF:");
        String cpf = sc.next();

        System.out.println("Email:");
        String email = sc.next();

        System.out.println("Gênero:");
        String genero = sc.next();

        System.out.println("Senha:");
        String senha = sc.next();

        if (i == 1) {
            int opcao;
            System.out.println("Cargo: \n1 - Autor \n2 - Revisor \n3 - Diretor");
            opcao = sc.nextInt();
            try {
                return switch (opcao) {
                    case 1 -> new Autor(nome, sobre, cpf, email, genero, senha);
                    case 2 -> new Revisor(nome, sobre, cpf, email, genero, senha);
                    case 3 -> new Diretor(nome, sobre, cpf, email, genero, senha);
                    default -> throw new OpcaoInvalidaException();
                };
            }catch (OpcaoInvalidaException idiota){
                System.out.println(idiota.getClass().getSimpleName() + ": " + idiota.getMessage() + "\n");
                cadastroPessoa(i);
            }
            return null;
        } else {
            return new Revisor(nome, sobre, cpf, email, genero, senha);
        }
    }

    private static void menu() {
        System.out.println("----- MENU ------");
        String[] opcoes = usuario.opcoes();
        for (String opcao : opcoes) {
            System.out.println(opcao);
        }
        int opcao = sc.nextInt();

        if (opcao == opcoes.length) {
            System.exit(0);
        } else if (opcao == opcoes.length - 1) {
            login();
        } else if (opcao < 1 || opcao > opcoes.length) {
            throw new OpcaoInvalidaException();
        }
        menu();
        try {
            switch (opcao) {
                case 1:
                    listarLivros();
                    break;
                case 2:
                    editarLivros();
                    break;
                case 3:
                    listarAtividades();
                    System.out.println("Listagem concluída");
                    break;
                case 4:
                    if (usuario instanceof Autor) {
                        ((Autor) usuario).listaLivros.add(cadastrarLivro());
                    } else {
                        Pessoa.listaPessoas.add(cadastroPessoa(2));
                        System.out.println("Cadastro concluído!");
                    }
                    break;
            }
        } catch (RuntimeException idiota) {
            System.out.println(idiota.getClass().getSimpleName() + ": " + idiota.getMessage() + "\n");
        } finally {
            menu();
        }
    }

    public static void editarLivros() {
        System.out.println("----- EDIÇÃO DE LIVROS -----");
        int checar, escolha = -1;
        if (usuario instanceof Revisor) {
            System.out.println("Quer editar um livro já iniciado a revisão ou começar um novo? \n1 - Continuar uma revisão \n2 - Iniciar nova revisão");
            escolha = sc.nextInt();
            if (escolha < 1 || escolha > 2) {
                throw new OpcaoInvalidaException();
            }
        }

        System.out.println("Qual o ISBN desejado?");
        String isbn = sc.next();

        checar = usuario.checarLista(isbn, escolha);

        if (checar == -1) {
            System.out.println("IBSN inválido ou livro não está disponível para edição!");
        } else {
            System.out.println("*passa o tempo do cara lendo/revisando/blablabla*");
            usuario.editarStatus(checar, escolha);
        }
        System.out.println("Edição completa!");
        menu();
    }

    private static void listarAtividades() {
        if (usuario.listarAtividades().equals("")) {
            System.out.println("Nenhuma atividade iniciada!");
        } else {
            System.out.println(usuario.listarAtividades());
            System.out.println("Gostaria de editar algum livro?\n1 - Sim \n2 - Não");
            if (sc.nextInt() == 1) {
                editarLivros();
            }
        }

        menu();
    }

    private static void listarLivros() {
        System.out.println(usuario.listarLivros());

        System.out.println("Gostaria de editar algum livro?\n1 - Sim \n2 - Não");
        if (sc.nextInt() == 1) {
            editarLivros();
        }

        System.out.println("Listagem completa!");
        menu();
    }

    private static Livro cadastrarLivro() {
        System.out.println("------ CADASTRO LIVRO ------");
        System.out.println("Título:");
        String titulo = sc.next();

        System.out.println("Quantidade de páginas:");
        int qtdPagina = sc.nextInt();

        if(qtdPagina < 0){
            throw  new QuantidadeDePaginasInvalidaException();
        }

        System.out.println("ISBN:");
        String isbn = sc.next();

        for(int i = 0; i < Livro.listaLivros.size(); i++){
            if(Livro.listaLivros.get(i).getISBN().equals(isbn)){
                throw new LivroExistenteException();
            }
        }

        return new Livro(((Autor) usuario), titulo, qtdPagina, isbn, 2);
    }

    public static int processoStatus() {
        if (usuario instanceof Autor) {
            System.out.println("Já finalizou sua edição? \n1 - Sim \n2 - Não");
        } else if (usuario instanceof Revisor) {
            System.out.println("Para qual status colocar a obra? \n1 - Aprovado \n2 - Reprovado \n3 - Em revisão \n4 - Aguardando edição \n5 - Continuar revisando-o");
        } else {
            System.out.println("Para qual status colocar a obra? \n1 - Publicado  \n2 - Reprovado  \n3 - Aguardando revisão \n4 - Continuar aprovado");
        }
        return sc.nextInt();
    }

    public static int perguntaPagina() {
        System.out.println("Em qual página você está?");
        return sc.nextInt();
    }
}
