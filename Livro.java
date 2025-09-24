import java.time.Year;
import java.util.Objects;

public abstract class Livro {
    private String titulo;
    private String autor;
    private int anoPublicacao;
    private int numeroPaginas;

    public Livro() { }

    public Livro(String titulo, String autor, int anoPublicacao, int numeroPaginas) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.numeroPaginas = numeroPaginas;
    }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public int getAnoPublicacao() { return anoPublicacao; }
    public void setAnoPublicacao(int anoPublicacao) { this.anoPublicacao = anoPublicacao; }

    public int getNumeroPaginas() { return numeroPaginas; }
    public void setNumeroPaginas(int numeroPaginas) { this.numeroPaginas = numeroPaginas; }

   
    public abstract String getFormato();

    
    public final int calcularTempoPublicacao() {
        int anoAtual = Year.now().getValue();
        return anoAtual - anoPublicacao;
    }

    @Override
    public String toString() {
        return "Titulo=" + titulo 
                + ", autor=" + autor 
                + ", anoPublicacao=" + anoPublicacao 
                + ", numeroPaginas=" + numeroPaginas;
    }

   
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Livro)) return false;
        Livro livro = (Livro) o;
        return anoPublicacao == livro.anoPublicacao &&
                titulo.equalsIgnoreCase(livro.titulo) &&
                autor.equalsIgnoreCase(livro.autor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo.toLowerCase(), autor.toLowerCase(), anoPublicacao);
    }
}