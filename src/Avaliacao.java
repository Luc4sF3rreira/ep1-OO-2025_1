public class Avaliacao {
    private String tipo;
    private double prova1;
    private double prova2;
    private double prova3;
    private double listasExercicios;
    private double trabalhos;
    private int totalAulas;
    private int presenca;

    public Avaliacao(Turmas tipo, double prova1, double prova2, double prova3, double listasExercicios, double trabalhos, int totalAulas, int presenca) {
        this.tipo = tipo.getTipoAvaliacao();
        this.prova1 = prova1;
        this.prova2 = prova2;
        this.prova3 = prova3;
        this.listasExercicios = listasExercicios;
        this.trabalhos = trabalhos;
        this.totalAulas = totalAulas;
        this.presenca = presenca;
    }
    public String getTipo() {return tipo;}
    public double getProva1() {return prova1;}
    public double getProva2() {return prova2;}
    public double getProva3() {return prova3;}
    public double getListasExercicios() {return listasExercicios;}
    public double getTrabalhos() {return trabalhos;}
    public int getTotalAulas() {return totalAulas;}
    public int getPresenca() {return presenca;}
    
    public double calcularMediaFinal() {         
        if (tipo.equals("A")) {
            return (prova1) + (prova2) + (prova3) + (listasExercicios) + (trabalhos) / 5;
        } else if (tipo.equals("B")) {
            return (prova1) + (prova2 * 2) + (prova3 * 3) + (listasExercicios) + (trabalhos) / 8;
        } else {
            return 0.0; 
        }
    }
    
    public double calcularFrequencia() {
        if (totalAulas == 0) return 0;
        return (presenca * 100.0) / totalAulas;
    }

    public String consultarSituacao() {
        double mediaFinal = calcularMediaFinal();
        double frequencia = calcularFrequencia();
        if (mediaFinal >= 5.0 && frequencia >= 75.0) {
            return "Aprovado";
        } else if (mediaFinal >= 5.0 && frequencia < 75.0) {
            return "Reprovado por falta";
        } else if (mediaFinal < 5.0 && frequencia >= 75.0) {
            return "Reprovado por nota";
        } else {
            return "Reprovado";
        }
    }
}

