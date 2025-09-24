public final class LivroDigital extends Livro {

    public LivroDigital(String titulo, String autor, int anoPublicacao, int numeroPaginas) {
        super(titulo, autor, anoPublicacao, numeroPaginas);
    }

    @Override
    public String getFormato() {
        return "Digital";
    }

    @Override
    public String toString() {
        return super.toString() + ", formato=" + getFormato();
    }
}