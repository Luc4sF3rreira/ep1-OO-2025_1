
public class FormadeAvaliação {
    private int tipo;
    private int prova1;
    private int prova2;
    private int prova3;
    private int listasExercicios;
    private int trabalhos;
    private int presenca;

    public FormadeAvaliação(int tipo, int prova1, int prova2, int prova3, int listasExercicios, int trabalhos, int presenca) {
        this.tipo = tipo;
        this.prova1 = prova1;
        this.prova2 = prova2;
        this.prova3 = prova3;
        this.listasExercicios = listasExercicios;
        this.trabalhos = trabalhos;
        this.presenca = presenca;
    }

    public double calcularMediaFinal() {
       while (tipo != 1 && tipo != 2) {
            System.out.println("Tipo de avaliação inválido. Digite 1 ou 2.");
        }
        if (tipo == 1) {
            return (prova1) + (prova2) + (prova3) + (listasExercicios) + (trabalhos) / 5;
        } else if (tipo == 2) {
            return (prova1) + (prova2 * 2) + (prova3 * 3) + (listasExercicios) + (trabalhos) / 8;
        } else {
            return 0.0; 
        }
    }

    public int getPresença() { return presenca; }

}

