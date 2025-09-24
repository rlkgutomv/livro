import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    public static final int ANO_PUBLICACAO_MINIMO = 1900;
    private List<Livro> acervo;

    public Biblioteca() {
        this.acervo = new ArrayList<>();
    }

    
    public Livro adicionar(Livro livro) throws Exception {
        if (livro == null)
            throw new Exception("Livro não pode ser nulo");

        if (livro.getTitulo() == null || livro.getTitulo().trim().isEmpty())
            throw new Exception("Título não pode ser em branco");

        if (livro.getAutor() == null || livro.getAutor().trim().isEmpty())
            throw new Exception("Autor não pode ser em branco");

        int anoAtual = LocalDate.now().getYear();
        if (livro.getAnoPublicacao() < ANO_PUBLICACAO_MINIMO || livro.getAnoPublicacao() > anoAtual)
            throw new Exception("Ano de publicação deve estar entre " + ANO_PUBLICACAO_MINIMO + " e o ano atual");

        if (livro.getNumeroPaginas() <= 0)
            throw new Exception("Número de páginas deve ser maior que zero");

        
        for (Livro l : acervo) {
            if (l.equals(livro)) {
                throw new Exception("Já existe um livro com este título, autor e ano no acervo.");
            }
        }

        acervo.add(livro);
        return livro;
    }

    
    public List<Livro> listarAcervo() {
        return new ArrayList<>(acervo);
    }

   
    public List<Livro> pesquisar() {
        return acervo;
    }

    
    public List<Livro> pesquisar(String titulo) {
        return pesquisar(titulo, null);
    }

    
    public List<Livro> pesquisar(String titulo, String autor) {
        List<Livro> livrosEncontrados = new ArrayList<>();
        for (Livro livro : acervo) {
            if (livro.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                if (autor == null || livro.getAutor().toLowerCase().contains(autor.toLowerCase())) {
                    livrosEncontrados.add(livro);
                }
            }
        }
        return livrosEncontrados;
    }

    
    public List<Livro> pesquisarPorAno(int anoInicio, int anoFim) {
        List<Livro> encontrados = new ArrayList<>();
        for (Livro livro : acervo) {
            if (livro.getAnoPublicacao() >= anoInicio && livro.getAnoPublicacao() <= anoFim) {
                encontrados.add(livro);
            }
        }
        return encontrados;
    }

    
    public boolean remover(int indice) {
        if (indice < 0 || indice >= acervo.size()) {
            return false;
        }
        acervo.remove(indice);
        return true;
    }

   
    public boolean atualizar(int indice, Livro novoLivro) throws Exception {
        if (indice < 0 || indice >= acervo.size()) {
            return false;
        }
        
        adicionarValidacao(novoLivro);

        
        for (int i = 0; i < acervo.size(); i++) {
            if (i == indice) continue;
            if (acervo.get(i).equals(novoLivro)) {
                throw new Exception("Atualização geraria duplicidade com outro livro.");
            }
        }

        acervo.set(indice, novoLivro);
        return true;
    }

    private void adicionarValidacao(Livro livro) throws Exception {
        if (livro == null) throw new Exception("Livro não pode ser nulo");

        if (livro.getTitulo() == null || livro.getTitulo().trim().isEmpty())
            throw new Exception("Título não pode ser em branco");

        if (livro.getAutor() == null || livro.getAutor().trim().isEmpty())
            throw new Exception("Autor não pode ser em branco");

        int anoAtual = LocalDate.now().getYear();
        if (livro.getAnoPublicacao() < ANO_PUBLICACAO_MINIMO || livro.getAnoPublicacao() > anoAtual)
            throw new Exception("Ano de publicação deve estar entre " + ANO_PUBLICACAO_MINIMO + " e o ano atual");

        if (livro.getNumeroPaginas() <= 0)
            throw new Exception("Número de páginas deve ser maior que zero");
    }

    
    public int contarLivros() {
        return acervo.size();
    }

   
    public Livro livroMaisAntigo() {
        if (acervo.isEmpty()) return null;
        Livro antigo = acervo.get(0);
        for (Livro livro : acervo) {
            if (livro.getAnoPublicacao() < antigo.getAnoPublicacao()) {
                antigo = livro;
            }
        }
        return antigo;
    }

    
    public Livro livroMaisNovo() {
        if (acervo.isEmpty()) return null;
        Livro novo = acervo.get(0);
        for (Livro livro : acervo) {
            if (livro.getAnoPublicacao() > novo.getAnoPublicacao()) {
                novo = livro;
            }
        }
        return novo;
    }
}