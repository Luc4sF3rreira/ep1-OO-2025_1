public class Avaliacao {
    private String tipoAvaliacao;
    private double prova1;
    private double prova2;
    private double prova3;
    private double listasExercicios;
    private double trabalhos;
    private int totalAulas;
    private int frequencia;

    public Avaliacao(String tipoAvaliacao, double prova1, double prova2, double prova3, double listasExercicios, double trabalhos, int presenca, int totalAulas) {
        this.tipoAvaliacao = tipoAvaliacao;
        this.prova1 = prova1;
        this.prova2 = prova2;
        this.prova3 = prova3;
        this.listasExercicios = listasExercicios;
        this.trabalhos = trabalhos;
        this.totalAulas = totalAulas;
        this.frequencia = presenca;
    }
    public String getTipo() {return tipoAvaliacao;}
    public double getProva1() {return prova1;}
    public double getProva2() {return prova2;}
    public double getProva3() {return prova3;}
    public double getListasExercicios() {return listasExercicios;}
    public double getTrabalhos() {return trabalhos;}
    public int getTotalAulas() {return totalAulas;}
    public int getPresenca() {return frequencia;}
    public double getMediaFinal() {return calcularMediaFinal();}
    public int getFrequencia() {return calcularFrequencia();}
    public String getSituacao() {return consultarSituacao();}
    public void setProva1(double prova1) {this.prova1 = prova1;}
    public void setProva2(double prova2) {this.prova2 = prova2;}
    public void setProva3(double prova3) {this.prova3 = prova3;}
    public void setListasExercicios(double listasExercicios) {this.listasExercicios = listasExercicios;}
    public void setTrabalhos(double trabalhos) {this.trabalhos = trabalhos;}
    public void setFrequencia(int frequencia) {this.frequencia = frequencia;}
    public void setMediaFinal(double mediaFinal) {}
    public void setSituacao(String situacao) {}

    public double calcularMediaFinal() {         
        if (tipoAvaliacao.equals("A")) {
            return ((prova1 + prova2 + prova3 + listasExercicios + trabalhos)) / 5;
        } else if (tipoAvaliacao.equals("B")) {
            return ((prova1 + (prova2 * 2) + (prova3 * 3) + listasExercicios + trabalhos)) / 8;
        } else {
            return 0.0; 
        }
    }
    
    public int calcularFrequencia() {
        return frequencia;
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

