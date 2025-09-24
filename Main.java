import java.util.List;
import java.util.Scanner;

public class Main {
    
    private static Biblioteca biblioteca = new Biblioteca();
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        String menu = """
                === Sistema Biblioteca ===
                Escolha uma das opções abaixo:
                1 - Adicionar Livro
                2 - Listar Acervo
                3 - Pesquisar Livro
                4 - Remover Livro
                5 - Atualizar Livro
                6 - Listar quantidade de livros
                7 - Pesquisar por intervalo de anos
                8 - Mostrar livro mais antigo e mais novo
                0 - Sair
                """;
        int opcao;
        do {
            System.out.println(menu);
            opcao = Input.scanInt("Digite sua escolha: ", scan);
            switch (opcao) {
                case 1:
                    cadastrarLivro();
                    pausa();
                    break;
                case 2:
                    listarAcervo();
                    pausa();
                    break;
                case 3:
                    pesquisarLivro();
                    pausa();
                    break;
                case 4:
                    removerLivro(biblioteca.pesquisar());
                    pausa();
                    break;
                case 5:
                    atualizarLivro(biblioteca.pesquisar());
                    pausa();
                    break;
                case 6:
                    listarQntLivros(biblioteca.pesquisar());
                    pausa();
                    break;
                case 7:
                    int anoInicio = Input.scanInt("Ano inicial: ", scan);
                    int anoFim = Input.scanInt("Ano final: ", scan);
                    biblioteca.pesquisarPorAno(anoInicio, anoFim);
                    pausa();
                    break;
                case 8:
                    Livro antigo = biblioteca.livroMaisAntigo();
                    Livro novo = biblioteca.livroMaisNovo();
                    System.out.println("Mais antigo: " + (antigo == null ? "N/A" : antigo));
                    System.out.println("Mais novo: " + (novo == null ? "N/A" : novo));
                    pausa();
                    break;
                case 0:
                    System.out.println("Volte Sempre!!!");
                    break;
                default:
                    System.out.println("Opção Inválida!");
                    break;
            }
        } while (opcao != 0);
    }

    private static void cadastrarLivro() {
        System.out.println("Você deseja cadastrar um livro:");
        System.out.println("1 - Físico");
        System.out.println("2 - Digital");
        int tipo = Input.scanInt("Escolha: ", scan);

        String titulo = Input.scanString("Digite o Título: ", scan).trim();
        String autor = Input.scanString("Digite o Autor: ", scan).trim();
        int anoPublicacao = Input.scanInt("Digite o ano de publicação: ", scan);
        int numeroPaginas = Input.scanInt("Digite o número de páginas: ", scan);

        Livro novoLivro;
        if (tipo == 1) {
            novoLivro = new LivroFisico(titulo, autor, anoPublicacao, numeroPaginas);
        } else {
            novoLivro = new LivroDigital(titulo, autor, anoPublicacao, numeroPaginas);
        }

        try {
           biblioteca.adicionar(novoLivro); 
            System.out.println("Livro adicionado com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void listarAcervo() {
        var acervo = biblioteca.pesquisar();
        imprimirLista(acervo);
    }

    private static void pesquisarLivro() {
        String titulo = Input.scanString("Digite o título que procuras: ", scan);
        String pesquisaAutor = Input.scanString("Deseja pesquisar por autor? (S/N) ", scan);
        List<Livro> livros;
        if (pesquisaAutor.toLowerCase().charAt(0) == 's') {
            String autor = Input.scanString("Digite o nome do autor: ", scan);
            livros = biblioteca.pesquisar(titulo, autor);
        } else {
            livros = biblioteca.pesquisar(titulo);
        }
        imprimirLista(livros);
    }

    private static void imprimirLista(List<Livro> acervo) {
        if (acervo == null || acervo.isEmpty())
            System.out.println("Nenhum Livro Encontrado");
        else {
            System.out.println("Livros Encontrados:");
            for (int i = 0; i < acervo.size(); i++) {
                System.out.println("Livro " + (i + 1) + ": " + acervo.get(i));
            }
        }
    }

    private static void removerLivro(List<Livro> acervo) {
        if (acervo.isEmpty()) {
            System.out.println("Nenhum Livro Encontrado");
            return;
        }

        listarAcervo();
        int remover = Input.scanInt("Digite qual o índice do livro que deseja remover: ", scan) - 1;

        if (remover >= 0 && remover < acervo.size()) {
            Livro removido = acervo.remove(remover);
            System.out.println("Removido: " + removido);
        } else {
            System.out.println("Índice inválido.");
        }
    }

    private static void atualizarLivro(List<Livro> acervo) {
        if (acervo.isEmpty()) {
            System.out.println("A lista está vazia!");
            return;
        }

        listarAcervo();
        String nomeDoLivro = Input.scanString("Digite o nome do livro que deseja atualizar: ", scan).toLowerCase();
        boolean acharLivro = false;

        for (Livro t : acervo) {
            if (t.getTitulo().toLowerCase().equals(nomeDoLivro)) {
                t.setTitulo(Input.scanString("Digite o novo título: ", scan));
                t.setAnoPublicacao(Input.scanInt("Digite o novo ano de publicação: ", scan));
                t.setNumeroPaginas(Input.scanInt("Digite o novo número de páginas: ", scan));
                t.setAutor(Input.scanString("Digite o novo nome do autor: ", scan));
                System.out.println("Livro atualizado com sucesso!");
                acharLivro = true;
                break;
            }
        }
        if (!acharLivro) {
            System.out.println("Não existe livro com esse título!");
        }
    }

    private static void listarQntLivros(List<Livro> acervo) {
        if (acervo.isEmpty()) {
            System.out.println("A lista está vazia!");
            return;
        }

        int quantidade = acervo.size();
        System.out.println("O acervo tem: " + quantidade + " livros");
    }

    private static void pausa() {
        System.out.println("Pressione Enter para continuar");
        scan.nextLine();
    }
}