import javax.swing.JOptionPane;
import java.util.List;

public class ModoAvaliacao_Frequencia {  
    public void lancarNotasParaTurma(Turmas turma) {
            for (Aluno aluno : turma.getAlunosMatriculados()) {
                JOptionPane.showMessageDialog(null, "Lançando notas para o aluno: " + aluno.getNome());
                
                double prova1 = Double.parseDouble(JOptionPane.showInputDialog(null,"Nota da prova 1: "));
                double prova2 = Double.parseDouble(JOptionPane.showInputDialog(null,"Nota da prova 2: "));
                double prova3 = Double.parseDouble(JOptionPane.showInputDialog(null,"Nota da prova 3: "));
                double listasExercicios = Double.parseDouble(JOptionPane.showInputDialog(null,"Nota das listas de Exercícios: "));
                double seminario = Double.parseDouble(JOptionPane.showInputDialog(null,"Nota do seminário: "));
                int presenca = Integer.parseInt(JOptionPane.showInputDialog(null,"Presença do aluno: "));   
    
                Avaliacao avaliacao = new Avaliacao(turma, prova1, prova2, prova3, listasExercicios, seminario, presenca, turma.getTotalAulas());               
                aluno.setAvaliacao(avaliacao);
                double mediaFinal = avaliacao.calcularMediaFinal();
                double frequencia = avaliacao.calcularFrequencia();

                StringBuilder notas = new StringBuilder();
                notas.append("Média final do aluno" + aluno.getNome() + ": ").append(mediaFinal).append("\n");
                notas.append("Frequência: " + aluno.getNome() + ": ").append(frequencia).append("\n");
                notas.append("Situação: " + aluno.getNome() + ":").append(avaliacao.consultarSituacao()).append("\n");
                notas.append("Notas lançadas com sucesso!");
                JOptionPane.showMessageDialog(null, notas.toString());  
        }
    }
    public void relatorioPorTurma(Turmas turma) {
        JOptionPane.showMessageDialog(null, "Relatório da turma: " + turma.getNome());
        for (Aluno aluno : turma.getAlunosMatriculados()) {
            Avaliacao avaliacao = aluno.getAvaliacao();
            if (avaliacao != null) {
                
                StringBuilder relatorioTurma = new StringBuilder();
                relatorioTurma.append("Aluno: ").append(aluno.getNome()).append("\n");
                relatorioTurma.append("Média Final: ").append(avaliacao.calcularMediaFinal()).append("\n");
                relatorioTurma.append("Frequência: ").append(avaliacao.calcularFrequencia()).append("\n");
                relatorioTurma.append("Situação: ").append(avaliacao.consultarSituacao()).append("\n");
                JOptionPane.showMessageDialog(null, relatorioTurma.toString());
            } else {
                JOptionPane.showMessageDialog(null, "Aluno: " + aluno.getNome() + " - Avaliação não lançada.");
            }
        }
    }

    public void relatorioPorDisciplina(List<Turmas> turmas, String nome) {
        JOptionPane.showMessageDialog(null, "Relatório da Disciplina: " + nome);
        for (Turmas turma : turmas) {
            if (turma.getNome().equalsIgnoreCase(nome)) {
                relatorioPorTurma(turma);
            }
        }
    }

    public void relatorioPorProfessor(List<Turmas> turmas, String professor) {
        JOptionPane.showMessageDialog(null, "Relatório do Professor: " + professor);
        for (Turmas turma : turmas) {
            if (turma.getProfessor().equalsIgnoreCase(professor)) {
                relatorioPorTurma(turma);
            }
        }
    }
    
    public void exibirBoletimPorAluno(List<Turmas> turmas, String nomeAluno, boolean exibirDadosTurma) {
        JOptionPane.showMessageDialog(null, "Boletim do Aluno: " + nomeAluno);
        StringBuilder boletim = new StringBuilder();
        for (Turmas turma : turmas) {
            for (Aluno aluno : turma.getAlunosMatriculados()) {
                if (aluno.getNome().equalsIgnoreCase(nomeAluno)) {
                    Avaliacao avaliacao = aluno.getAvaliacao();
                    if (avaliacao != null) {
                        JOptionPane.showMessageDialog(null, "Semestre: " + turma.getSemestre());
                        if (exibirDadosTurma) {                            
                            boletim.append("Disciplina: ").append(turma.getNome()).append("\n");
                            boletim.append("Professor: ").append(turma.getProfessor()).append("\n");
                            boletim.append("Modalidade: ").append(turma.isPresencial() ? "Presencial" : "Remota").append("\n");
                            boletim.append("Carga Horária: ").append(turma.getCargaHoraria()).append(" horas").append("\n");
                        }
                        boletim.append("Notas do Aluno: ").append(aluno.getNome()).append("\n");
                        boletim.append("    Prova 1: ").append(avaliacao.getProva1()).append("\n");
                        boletim.append("    Prova 2: ").append(avaliacao.getProva2()).append("\n");
                        boletim.append("    Prova 3: ").append(avaliacao.getProva3()).append("\n");
                        boletim.append("    Listas de Exercícios: ").append(avaliacao.getListasExercicios()).append("\n");
                        boletim.append("    Seminário: ").append(avaliacao.getTrabalhos()).append("\n");
                        boletim.append("    Média Final: ").append(avaliacao.calcularMediaFinal()).append("\n");
                        boletim.append("    Frequência: ").append(avaliacao.calcularFrequencia()).append("\n");
                        boletim.append("    Situação: ").append(avaliacao.consultarSituacao()).append("\n");
                        JOptionPane.showMessageDialog(null, boletim.toString());

                    } else {
                        JOptionPane.showMessageDialog(null, "Avaliação não lançada para o aluno: " + aluno.getNome());
                    }
                }
            }
        }
    }
}
