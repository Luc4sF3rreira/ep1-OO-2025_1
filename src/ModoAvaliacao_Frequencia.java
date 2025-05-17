import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class ModoAvaliacao_Frequencia {  
    public void lancarNotasParaTurma(Turmas turma) {
        Scanner scanner = new Scanner(System.in);    
            for (Aluno aluno : turma.getAlunosMatriculados()) {
                System.out.println("Lançando notas para o aluno: " + aluno.getNome());
    
                System.out.print("Prova 1: ");
                double prova1 = scanner.nextDouble();
    
                System.out.print("Prova 2: ");
                double prova2 = scanner.nextDouble();
    
                System.out.print("Prova 3: ");
                double prova3 = scanner.nextDouble();
    
                System.out.print("Listas de Exercícios: ");
                double listasExercicios = scanner.nextDouble();
    
                System.out.print("Seminário: ");
                double trabalhos = scanner.nextDouble();

                System.out.print("Aulas presentes: ");
                int presenca = scanner.nextInt();
    
                Avaliacao avaliacao = new Avaliacao(turma, prova1, prova2, prova3, listasExercicios, trabalhos, presenca, turma.getTotalAulas());               
                aluno.setAvaliacao(avaliacao);

                double mediaFinal = avaliacao.calcularMediaFinal();
                System.out.println("Média final do aluno " + aluno.getNome() + ": " + mediaFinal);                

                double frequencia = avaliacao.calcularFrequencia();
                System.out.println("A frequência do aluno foi " + aluno.getNome() + ": " + frequencia);
                System.out.println("---------------------------------------------");

                String situacao = avaliacao.consultarSituacao();
                System.out.println("O aluno foi: " + situacao);
        }
    }

    public void relatorioPorTurma(Turmas turma) {
        System.out.println("Relatório da Turma: " + turma.getNome());
        for (Aluno aluno : turma.getAlunosMatriculados()) {
            Avaliacao avaliacao = aluno.getAvaliacao();
            if (avaliacao != null) {
                System.out.println("Aluno: " + aluno.getNome());
                System.out.println("Média Final: " + avaliacao.calcularMediaFinal());
                System.out.println("Frequência: " + avaliacao.calcularFrequencia());
                System.out.println("Situação: " + avaliacao.consultarSituacao());
                System.out.println("---------------------------------------------");
            } else {
                System.out.println("Aluno: " + aluno.getNome() + " - Avaliação não lançada.");
            }
        }
    }

    public void relatorioPorDisciplina(List<Turmas> turmas, String nome) {
        System.out.println("Relatório da Disciplina: " + nome);
        for (Turmas turma : turmas) {
            if (turma.getNome().equalsIgnoreCase(nome)) {
                relatorioPorTurma(turma);
            }
        }
    }

    public void relatorioPorProfessor(List<Turmas> turmas, String professor) {
        System.out.println("Relatório do Professor: " + professor);
        for (Turmas turma : turmas) {
            if (turma.getProfessor().equalsIgnoreCase(professor)) {
                relatorioPorTurma(turma);
            }
        }
    }
    
    public void exibirBoletimPorAluno(List<Turmas> turmas, String nomeAluno, boolean exibirDadosTurma) {
        System.out.println("Boletim do Aluno: " + nomeAluno);
        for (Turmas turma : turmas) {
            for (Aluno aluno : turma.getAlunosMatriculados()) {
                if (aluno.getNome().equalsIgnoreCase(nomeAluno)) {
                    Avaliacao avaliacao = aluno.getAvaliacao();
                    if (avaliacao != null) {
                        System.out.println("Semestre: " + turma.getSemestre());
                        if (exibirDadosTurma) {
                            System.out.println("Disciplina: " + turma.getNome());
                            System.out.println("Professor: " + turma.getProfessor());
                            System.out.println("Modalidade: " + (turma.isPresencial() ? "Presencial" : "Remota"));
                            System.out.println("Carga Horária: " + turma.getCargaHoraria() + " horas");
                        }
                        System.out.println("Média Final: " + avaliacao.calcularMediaFinal());
                        System.out.println("Frequência: " + avaliacao.calcularFrequencia());
                        System.out.println("Situação: " + avaliacao.consultarSituacao());
                        System.out.println("---------------------------------------------");
                    } else {
                        System.out.println("Semestre: " + turma.getSemestre());
                        System.out.println("Disciplina: " + turma.getNome());
                        System.out.println("Avaliação não lançada.");
                        System.out.println("---------------------------------------------");
                    }
                }
            }
        }
    }
}
