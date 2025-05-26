import javax.swing.JOptionPane;
import java.util.List;

public class ModoAvaliacao_Frequencia {    
    public List<Aluno> carregarDadosAlunos() {
        ModoAluno modoAluno = new ModoAluno();
        modoAluno.carregarDadosAlunos();
        return modoAluno.getAlunos();    
    }
    public List<Disciplina> carregarDisciplinas() {
        ModoDisciplina_Turma modoDT = new ModoDisciplina_Turma();
        modoDT.carregarDadosDisciplinas();
        return modoDT.getDisciplinas();
    }
    public List<Turmas> carregarDadosTurmas() {
        ModoDisciplina_Turma modoDT = new ModoDisciplina_Turma();
        modoDT.carregarDadosTurmas();
        return modoDT.getTurmas();
    }   
    
    public void lancarNotasParaTurma(List<Turmas> turmas, Aluno aluno) {
        for (Turmas turma : turmas) {
            if(turma.getAlunosMatriculados().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum aluno matriculado na turma: " + turma.getNome());
            return;
        }
     }
        String[] nomesTurmas = turmas.stream()
            .map(t -> t.getNome() + " | Professor: " + t.getProfessor() + " | Número da turma: " + t.getNumeroTurma())
            .toArray(String[]::new); 
        String turmaSelecionadaStr = (String) JOptionPane.showInputDialog(
            null,
            "Selecionar a turma para lançar notas:",
            "Lançamento de Notas",
            JOptionPane.QUESTION_MESSAGE,
            null,
            nomesTurmas,
            nomesTurmas[0]);
        if (turmaSelecionadaStr == null) {
            return;
        }
        String turmaSelecionada = turmaSelecionadaStr.split("\\|")[0].trim();
        Turmas turma = null;
        for (Turmas t : turmas) {
            if (t.getNome().equalsIgnoreCase(turmaSelecionada)) {
                turma = t;
                break;
            }
        }
        if (turma == null) {
            JOptionPane.showMessageDialog(null, "Turma não encontrada.");
            return;
        }
        List<Aluno> alunosMatriculados = turma.getAlunosMatriculados();
        if (alunosMatriculados.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum aluno matriculado nesta turma.");
            return;
        }
        String[] nomesAlunos = alunosMatriculados.stream().map(Aluno::getNome).toArray(String[]::new);
        String alunoSelecionado = (String) JOptionPane.showInputDialog(
            null,
            "Selecionar o aluno para lançar notas:",
            "Lançamento de Notas",
            JOptionPane.QUESTION_MESSAGE,
            null,
            nomesAlunos,
            nomesAlunos[0]);
        if (alunoSelecionado == null) {
            return;
        }
        Aluno alunoSelecionadoObj = null;
        for (Aluno a : alunosMatriculados) {
            if (a.getNome().equalsIgnoreCase(alunoSelecionado)) {
            alunoSelecionadoObj = a;
            break;
            }
        }
        if (alunoSelecionadoObj == null) {
            JOptionPane.showMessageDialog(null, "Aluno não encontrado na turma selecionada.");
            return;
        }
        Double prova1 = Double.parseDouble(JOptionPane.showInputDialog("Digite a nota da Prova 1:"));
        Double prova2 = Double.parseDouble(JOptionPane.showInputDialog("Digite a nota da Prova 2:"));
        Double prova3 = Double.parseDouble(JOptionPane.showInputDialog("Digite a nota da Prova 3:"));
        Double listasExercicios = Double.parseDouble(JOptionPane.showInputDialog("Digite a nota das Listas de Exercícios:"));
        Double trabalhos = Double.parseDouble(JOptionPane.showInputDialog("Digite a nota do Seminário/Trabalho:"));
        Integer frequencia = Integer.parseInt(JOptionPane.showInputDialog("Digite a frequência do aluno (em %):"));
        Avaliacao avaliacao = new Avaliacao(turma.getTipoAvaliacao(), prova1, prova2, prova3, listasExercicios, trabalhos, frequencia, turma.getTotalAulas());
        alunoSelecionadoObj.setAvaliacao(avaliacao);
        JOptionPane.showMessageDialog(null, "Notas lançadas com sucesso para o aluno: " + alunoSelecionadoObj.getNome() + " na turma: " + turma.getNome());
    }
    
    public void situacaoAluno(Turmas turma, Aluno aluno) {
        Avaliacao avaliacao = aluno.getAvaliacao();
        if (avaliacao != null) {
            StringBuilder situacao = new StringBuilder();
            situacao.append("Situação do Aluno: ").append(aluno.getNome()).append("\n");
            situacao.append("Média Final: ").append(avaliacao.calcularMediaFinal()).append("\n");
            situacao.append("Frequência: ").append(avaliacao.calcularFrequencia()).append("%\n");
            situacao.append("Situação: ").append(avaliacao.consultarSituacao()).append("\n");
            JOptionPane.showMessageDialog(null, situacao.toString());
        } else {
            JOptionPane.showMessageDialog(null, "Avaliação não lançada para o aluno: " + aluno.getNome());
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
