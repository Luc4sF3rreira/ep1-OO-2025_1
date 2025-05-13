public class ModoAvaliacao_Frequencia {

    private String turma;
    private double[] notas;
    private int[] presencas;

    public ModoAvaliacao_Frequencia(String turma, int numeroDeAlunos) {
        this.turma = turma;
        this.notas = new double[numeroDeAlunos];
        this.presencas = new int[numeroDeAlunos];
    }

    public void lancarNota(int alunoIndex, double nota) {
        if (alunoIndex >= 0 && alunoIndex < notas.length) {
            notas[alunoIndex] = nota;
        } else {
            System.out.println("Índice de aluno inválido.");
        }
    }

    public void lancarPresenca(int alunoIndex, int presenca) {
        if (alunoIndex >= 0 && alunoIndex < presencas.length) {
            presencas[alunoIndex] = presenca;
        } else {
            System.out.println("Índice de aluno inválido.");
        }
    }

    public double consultarNota(int alunoIndex) {
        if (alunoIndex >= 0 && alunoIndex < notas.length) {
            return notas[alunoIndex];
        } else {
            System.out.println("Índice de aluno inválido.");
            return -1;
        }
    }

    public int consultarPresenca(int alunoIndex) {
        if (alunoIndex >= 0 && alunoIndex < presencas.length) {
            return presencas[alunoIndex];
        } else {
            System.out.println("Índice de aluno inválido.");
            return -1;
        }
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }
}