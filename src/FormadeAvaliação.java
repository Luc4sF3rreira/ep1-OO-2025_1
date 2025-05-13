
public class FormadeAvaliação {
    private String tipo;
    private int prova1;
    private int prova2;
    private int prova3;
    private int listasExercicios;
    private int trabalhos;
    private int presenca;

    public FormadeAvaliação(String tipo, int prova1, int prova2, int prova3, int listasExercicios, int trabalhos, int presenca) {
        this.tipo = tipo;
        this.prova1 = prova1;
        this.prova2 = prova2;
        this.prova3 = prova3;
        this.listasExercicios = listasExercicios;
        this.trabalhos = trabalhos;
        this.presenca = presenca;
    }

    public double calcularMediaFinal() {
        if (tipo.equalsIgnoreCase("A")) {
            return (prova1) + (prova2) + (prova3) + (listasExercicios) + (trabalhos) / 5;
        } else if (tipo.equalsIgnoreCase("B")) {
            return (prova1) + (prova2 * 2) + (prova3 * 3) + (listasExercicios) + (trabalhos) / 8;
        } else {
            throw new IllegalArgumentException("Tipo de avaliação inválido");
        }
    }

    public int getPresença() { return presenca; }

}

