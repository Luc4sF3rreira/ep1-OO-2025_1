public class Notas {
    private String aluno;
    private Turmas turma;
    private double prova1;
    private double prova2;
    private double prova3;
    private double projeto1;
    private double projeto2;

    public Notas(String aluno, Turmas turma, double prova1, double prova2, double prova3, double projeto1, double projeto2) {
        this.aluno = aluno;
        this.turma = turma;
        this.prova1 = prova1;
        this.prova2 = prova2;
        this.prova3 = prova3;
        this.projeto1 = projeto1;
        this.projeto2 = projeto2;
    }

    public String getAluno() {return aluno; }
    public Turmas getTurma() {return turma; }    
    public double getProva1() {return prova1; }
    public double getProva2() {return prova2; }
    public double getProva3() {return prova3; }
    public double getProjeto1() {return projeto1; }
    public double getProjeto2() {return projeto2; }

    public double calcularMedia() {
        return (prova1 + prova2*2 + prova3*3 + projeto1 + projeto2) / 8;
    }
}
