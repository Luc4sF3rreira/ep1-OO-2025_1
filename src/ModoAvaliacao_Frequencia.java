import javax.swing.JOptionPane;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ModoAvaliacao_Frequencia {   
    public List<Aluno> salvarDadosAlunos() {
        ModoAluno modoAluno = new ModoAluno();
        modoAluno.salvarDadosAlunos();
        return modoAluno.getAlunos();
    }     
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
        if (turmas == null || turmas.isEmpty()) {
            turmas = carregarDadosTurmas();
            if (turmas == null || turmas.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nenhuma turma cadastrada.");
                return;
            }
        }
        for (Turmas t : turmas) {
            List<Aluno> alunosMatriculados = t.getAlunosMatriculados();
            if (alunosMatriculados == null || alunosMatriculados.isEmpty()) {
                List<Aluno> alunos = carregarDadosAlunos();
                t.setAlunosMatriculados(alunos);
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
        if (alunosMatriculados == null || alunosMatriculados.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum aluno matriculado nesta turma.");
            return;
        }
        String[] nomesAlunos = alunosMatriculados.stream()
            .map(a -> a.getNome() + " | Matrícula: " + a.getMatricula())
            .toArray(String[]::new);
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
        String matriculaSelecionada = alunoSelecionado.split("\\| Matrícula: ")[1].trim();
        for (Aluno a : alunosMatriculados) {
            if (a.getMatricula().equalsIgnoreCase(matriculaSelecionada)) {
                Avaliacao avaliacao = a.getAvaliacao();
                a.setAvaliacao(avaliacao);
                alunoSelecionadoObj = a;
                break;
            }
        }
        if (alunoSelecionadoObj == null) {
            JOptionPane.showMessageDialog(null, "Aluno não encontrado na turma selecionada.");
            return;
        }
        Avaliacao avaliacaoExistente = alunoSelecionadoObj.getAvaliacao();
        if (avaliacaoExistente != null) {
            StringBuilder info = new StringBuilder();
            info.append("Notas já cadastradas para este aluno nesta turma:\n");
            info.append("Prova 1: ").append(avaliacaoExistente.getProva1()).append("\n");
            info.append("Prova 2: ").append(avaliacaoExistente.getProva2()).append("\n");
            info.append("Prova 3: ").append(avaliacaoExistente.getProva3()).append("\n");
            info.append("Listas de Exercícios: ").append(avaliacaoExistente.getListasExercicios()).append("\n");
            info.append("Seminário/Trabalho: ").append(avaliacaoExistente.getTrabalhos()).append("\n");
            info.append("Frequência: ").append(avaliacaoExistente.calcularFrequencia()).append("%\n\n");
            info.append("As notas já foram cadastradas. Deseja editar as notas?");
            int opcao = JOptionPane.showConfirmDialog(null, info.toString(), "Notas já cadastradas", JOptionPane.YES_NO_OPTION);
            if (opcao != JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null, "Lançamento de notas cancelado.");
                return;
            }
        }
        Double prova1 = Double.parseDouble(JOptionPane.showInputDialog("Digite a nota da Prova 1:").replace(",", "."));
        Double prova2 = Double.parseDouble(JOptionPane.showInputDialog("Digite a nota da Prova 2:").replace(",", "."));
        Double prova3 = Double.parseDouble(JOptionPane.showInputDialog("Digite a nota da Prova 3:").replace(",", "."));
        Double listasExercicios = Double.parseDouble(JOptionPane.showInputDialog("Digite a nota das Listas de Exercícios:").replace(",", "."));
        Double trabalhos = Double.parseDouble(JOptionPane.showInputDialog("Digite a nota do Seminário/Trabalho:").replace(",", "."));
        Integer frequencia = Integer.parseInt(JOptionPane.showInputDialog("Digite a frequência do aluno (em %):"));
        Avaliacao avaliacao = new Avaliacao(turma.getTipoAvaliacao(), prova1, prova2, prova3, listasExercicios, trabalhos, frequencia, turma.getTotalAulas());
        alunoSelecionadoObj.setAvaliacao(avaliacao);
        JOptionPane.showMessageDialog(null, "Notas lançadas com sucesso!");
        Double mediaFinal = avaliacao.calcularMediaFinal();
        Integer frequenciaFinal = avaliacao.calcularFrequencia();

        JOptionPane.showMessageDialog(null, "Média Final: " + mediaFinal + "\n" +
            "Frequência: " + frequenciaFinal + "%\n");
        String situacao = avaliacao.consultarSituacao();
        JOptionPane.showMessageDialog(null, "Situação: " + situacao);

        alunoSelecionadoObj.setAvaliacao(avaliacao);
        alunoSelecionadoObj.setMediaFinal(mediaFinal);
        alunoSelecionadoObj.setFrequencia(frequencia);
        alunoSelecionadoObj.setSituacao(situacao);
        
        List<Aluno> alunos = carregarDadosAlunos();
        for (Aluno a : alunos) {
            if (a.getMatricula().equalsIgnoreCase(alunoSelecionadoObj.getMatricula())) {
            a.setAvaliacao(avaliacao);
            a.setMediaFinal(mediaFinal);
            a.setFrequencia(frequencia);
            a.setSituacao(situacao);
            break;
            }
        }
        ModoAluno modoAluno = new ModoAluno();
        modoAluno.setAlunos(alunos);
        modoAluno.salvarDadosAlunos();
    }  

    public void relatorioPorTurma(List<Turmas> turmas) {
        if (turmas == null || turmas.isEmpty()) {
            turmas = carregarDadosTurmas();
            if (turmas == null || turmas.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nenhuma turma cadastrada.");
                return;
            }
        }

        String[] nomesTurmas = turmas.stream()
            .map(t -> t.getNome() + " | Professor: " + t.getProfessor() + " | Número da turma: " + t.getNumeroTurma())
            .toArray(String[]::new);

        String turmaSelecionadaStr = (String) JOptionPane.showInputDialog(
            null,
            "Selecione a turma para exibir o relatório:",
            "Relatório por Turma",
            JOptionPane.QUESTION_MESSAGE,
            null,
            nomesTurmas,
            nomesTurmas[0]);
        if (turmaSelecionadaStr == null) return;

        String nomeTurmaSelecionada = turmaSelecionadaStr.split("\\|")[0].trim();
        Turmas turmaSelecionada = null;
        for (Turmas t : turmas) {
            if (t.getNome().equalsIgnoreCase(nomeTurmaSelecionada)) {
                turmaSelecionada = t;
                break;
            }
        }
        if (turmaSelecionada == null) {
            JOptionPane.showMessageDialog(null, "Turma não encontrada.");
            return;
        }
        StringBuilder relatorio = new StringBuilder();
        relatorio.append("Relatório da Turma: ").append(turmaSelecionada.getNome()).append("\n");
        relatorio.append("Professor: ").append(turmaSelecionada.getProfessor()).append("\n");
        relatorio.append("Número da Turma: ").append(turmaSelecionada.getNumeroTurma()).append("\n\n");

        List<Aluno> alunosMatriculados = turmaSelecionada.getAlunosMatriculados();
        if (alunosMatriculados == null || alunosMatriculados.isEmpty()) {
            alunosMatriculados = carregarDadosAlunos();
        }
        if (alunosMatriculados == null || alunosMatriculados.isEmpty()) {
            relatorio.append("Nenhum aluno matriculado nesta turma.\n");
        } else {
            for (Aluno aluno : alunosMatriculados) {
                relatorio.append("Aluno: ").append(aluno.getNome())
                        .append(" | Matrícula: ").append(aluno.getMatricula()).append("\n");
                if (aluno.getAvaliacao() != null) {
                    relatorio.append("  Média Final: ").append(aluno.getMediaFinal()).append("\n");
                    relatorio.append("  Frequência: ").append(aluno.getFrequencia()).append("%\n");
                    relatorio.append("  Situação: ").append(aluno.getSituacao()).append("\n");
                } else {
                    relatorio.append("  Avaliação não lançada.\n");
                }
                relatorio.append("\n");
            }
        }
        JOptionPane.showMessageDialog(null, relatorio.toString());
    }

    public void relatorioPorDisciplina(List<Turmas> turmas) {
        if (turmas == null || turmas.isEmpty()) {
            turmas = carregarDadosTurmas();
            if (turmas == null || turmas.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nenhuma turma cadastrada.");
                return;
            }
        }
        List<String> nomesDisciplinas = new ArrayList<>();
        for (Turmas t : turmas) {
            if (!nomesDisciplinas.contains(t.getNome())) {
                nomesDisciplinas.add(t.getNome());
            }
        }
        if (nomesDisciplinas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma disciplina encontrada.");
            return;
        }
        String disciplinaSelecionada = (String) JOptionPane.showInputDialog(
            null,
            "Selecione a disciplina para exibir o relatório:",
            "Relatório por Disciplina",
            JOptionPane.QUESTION_MESSAGE,
            null,
            nomesDisciplinas.toArray(),
            nomesDisciplinas.get(0));
        if (disciplinaSelecionada == null) return;

        StringBuilder relatorio = new StringBuilder();
        relatorio.append("Relatório da Disciplina: ").append(disciplinaSelecionada).append("\n\n");
        for (Turmas turma : turmas) {
            if (turma.getNome().equalsIgnoreCase(disciplinaSelecionada)) {
                relatorio.append("Turma: ").append(turma.getNumeroTurma()).append(" | Professor: ").append(turma.getProfessor()).append("\n");
                for (Aluno aluno : turma.getAlunosMatriculados()) {
                    Avaliacao avaliacao = aluno.getAvaliacao();
                    relatorio.append("Aluno: ").append(aluno.getNome()).append(" | Matrícula: ").append(aluno.getMatricula()).append("\n");
                    if (avaliacao != null) {
                        relatorio.append("  Média Final: ").append(avaliacao.calcularMediaFinal()).append("\n");
                        relatorio.append("  Frequência: ").append(avaliacao.calcularFrequencia()).append("%\n");
                        relatorio.append("  Situação: ").append(avaliacao.consultarSituacao()).append("\n");
                    } else {
                        relatorio.append("  Avaliação não lançada.\n");
                    }
                }
                relatorio.append("\n");
            }
        }
        JOptionPane.showMessageDialog(null, relatorio.toString());
    }

    public void relatorioPorProfessor(List<Turmas> turmas) {
        if (turmas == null || turmas.isEmpty()) {
            turmas = carregarDadosTurmas();
            if (turmas == null || turmas.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nenhuma turma cadastrada.");
                return;
            }
        }
        List<String> nomesProfessores = new ArrayList<>();
        for (Turmas t : turmas) {
            if (!nomesProfessores.contains(t.getProfessor())) {
                nomesProfessores.add(t.getProfessor());
            }
        }
        if (nomesProfessores.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum professor encontrado.");
            return;
        }
        String professorSelecionado = (String) JOptionPane.showInputDialog(
            null,
            "Selecione o professor para exibir o relatório:",
            "Relatório por Professor",
            JOptionPane.QUESTION_MESSAGE,
            null,
            nomesProfessores.toArray(),
            nomesProfessores.get(0));
        if (professorSelecionado == null) return;

        StringBuilder relatorio = new StringBuilder();
        relatorio.append("Relatório do Professor: ").append(professorSelecionado).append("\n\n");
        for (Turmas turma : turmas) {
            if (turma.getProfessor().equalsIgnoreCase(professorSelecionado)) {
                relatorio.append("Turma: ").append(turma.getNumeroTurma()).append(" | Disciplina: ").append(turma.getNome()).append("\n");
                for (Aluno aluno : turma.getAlunosMatriculados()) {
                    Avaliacao avaliacao = aluno.getAvaliacao();
                    relatorio.append("Aluno: ").append(aluno.getNome()).append(" | Matrícula: ").append(aluno.getMatricula()).append("\n");
                    if (avaliacao != null) {
                        relatorio.append("  Média Final: ").append(avaliacao.calcularMediaFinal()).append("\n");
                        relatorio.append("  Frequência: ").append(avaliacao.calcularFrequencia()).append("%\n");
                        relatorio.append("  Situação: ").append(avaliacao.consultarSituacao()).append("\n");
                    } else {
                        relatorio.append("  Avaliação não lançada.\n");
                    }
                }
                relatorio.append("\n");
            }
        }
        JOptionPane.showMessageDialog(null, relatorio.toString());
    }
    
    public void exibirBoletimPorAluno(List<Turmas> turmas, boolean exibirDadosTurma) {
        if (turmas == null || turmas.isEmpty()) {
            turmas = carregarDadosTurmas();
            if (turmas == null || turmas.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nenhuma turma cadastrada.");
                return;
            }
        }
        Map<String, Aluno> mapaAlunos = new java.util.HashMap<>();
        for (Turmas t : turmas) {
            List<Aluno> alunos = t.getAlunosMatriculados();
            if (alunos != null) {
                for (Aluno a : alunos) {
                    mapaAlunos.putIfAbsent(a.getMatricula(), a);
                }
            }
        }
        if (mapaAlunos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum aluno cadastrado.");
            return;
        }
        String[] nomesAlunos = mapaAlunos.values().stream()
            .map(a -> a.getNome() + " | Matrícula: " + a.getMatricula())
            .toArray(String[]::new);

        String alunoSelecionadoStr = (String) JOptionPane.showInputDialog(
            null,
            "Selecione o aluno para exibir o boletim:",
            "Boletim do Aluno",
            JOptionPane.QUESTION_MESSAGE,
            null,
            nomesAlunos,
            nomesAlunos[0]);
        if (alunoSelecionadoStr == null) return;
        String matriculaSelecionada = alunoSelecionadoStr.split("\\| Matrícula: ")[1].trim();

        StringBuilder boletim = new StringBuilder();
        boolean encontrou = false;
        for (Turmas turma : turmas) {
            for (Aluno aluno : turma.getAlunosMatriculados()) {
                if (aluno.getMatricula().equalsIgnoreCase(matriculaSelecionada)) {
                    Avaliacao avaliacao = aluno.getAvaliacao();
                    encontrou = true;
                    boletim.append("Turma: ").append(turma.getNome()).append(" | Professor: ").append(turma.getProfessor()).append("\n");
                    boletim.append("Semestre: ").append(turma.getSemestre()).append("\n");
                    if (exibirDadosTurma) {
                        boletim.append("Modalidade: ").append(turma.isPresencial() ? "Presencial" : "Remota").append("\n");
                        boletim.append("Carga Horária: ").append(turma.getCargaHoraria()).append(" horas").append("\n");
                    }
                    if (avaliacao != null) {
                        boletim.append("Notas do Aluno: ").append(aluno.getNome()).append("\n");
                        boletim.append("    Prova 1: ").append(avaliacao.getProva1()).append("\n");
                        boletim.append("    Prova 2: ").append(avaliacao.getProva2()).append("\n");
                        boletim.append("    Prova 3: ").append(avaliacao.getProva3()).append("\n");
                        boletim.append("    Listas de Exercícios: ").append(avaliacao.getListasExercicios()).append("\n");
                        boletim.append("    Seminário: ").append(avaliacao.getTrabalhos()).append("\n");
                        boletim.append("    Média Final: ").append(avaliacao.calcularMediaFinal()).append("\n");
                        boletim.append("    Frequência: ").append(avaliacao.calcularFrequencia()).append("%\n");
                        boletim.append("    Situação: ").append(avaliacao.consultarSituacao()).append("\n");
                    } else {
                        boletim.append("Avaliação não lançada para esta turma.\n");
                    }
                    boletim.append("\n");
                }
            }
        }
        if (encontrou) {
            JOptionPane.showMessageDialog(null, boletim.toString());
        } else {
            JOptionPane.showMessageDialog(null, "O aluno selecionado não está matriculado em nenhuma turma.");
        }
    }
}
