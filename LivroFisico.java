public final class LivroFisico extends Livro {

    public LivroFisico(String titulo, String autor, int anoPublicacao, int numeroPaginas) {
        super(titulo, autor, anoPublicacao, numeroPaginas);
    }

    @Override
    public String getFormato() {
        return "Físico";
    }

    @Override
    public String toString() {
        return super.toString() + ", formato=" + getFormato();
    }
}