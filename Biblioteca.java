import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Livro> acervo;
    public Biblioteca() {
        this.acervo = new ArrayList<>();
    }

    public Livro adicionar(Livro livro) throws Exception{
        if (livro == null)
            throw new Exception("Livro não pode ser nulo");

        livro.setTitulo(livro.getTitulo().trim());
        if (livro.getTitulo() == null || livro.getTitulo().isEmpty())
            throw new Exception("Título não pode ser em branco");
        
        livro.setAutor(livro.getAutor().trim());
        if (livro.getAutor() == null || livro.getAutor().isEmpty())
            throw new Exception("Autor não pode ser em branco");
        
        int anoAtual = LocalDate.now().getYear();
        if (livro.getAnoPublicacao() < 1900 
                || livro.getAnoPublicacao() > anoAtual)
            throw new Exception("Ano de publicação deve estar entre 1900 e o ano atual");

        if (livro.getNumeroPaginas() <= 0)
            throw new Exception("Número de páginas deve ser maior que zero");

        acervo.add(livro);
        return livro;
    }

    public List<Livro> pesquisar() {
        return acervo;
    }

    public List<Livro> pesquisar(String titulo){
        return pesquisar(titulo, null);
    }

    public List<Livro> pesquisar(String titulo, String autor) {
        List<Livro> livrosEncontrados = new ArrayList<>();
        for (Livro livro : acervo) {
            if (livro.getTitulo().toLowerCase().contains(titulo.toLowerCase())){
                if (autor == null || 
                        livro.getAutor().toLowerCase().contains(autor.toLowerCase()))
                    livrosEncontrados.add(livro);
            }
        }
        return livrosEncontrados;
    }
    public void excecao(Livro livro) throws Exception {
    if (livro == null) {
        throw new IllegalArgumentException("Livro nulo");
    }

    
    String tituloNovo = livro.getTitulo().trim().toLowerCase();
    String autorNovo = livro.getAutor().trim().toLowerCase();
    int anoNovo = livro.getAnoPublicacao();

    
    for (Livro l : acervo) {
        String tituloExistente = l.getTitulo().trim().toLowerCase();
        String autorExistente = l.getAutor().trim().toLowerCase();
        int anoExistente = l.getAnoPublicacao();

        if (tituloExistente.equals(tituloNovo)
            && autorExistente.equals(autorNovo)
            && anoExistente == anoNovo) {
            throw new Exception("Já existe um livro com este título, autor e ano no acervo.");
        }
    }


    acervo.add(livro);
}
    }
    



