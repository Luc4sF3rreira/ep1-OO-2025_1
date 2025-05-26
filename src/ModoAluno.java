import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.io.*;

public class ModoAluno {
        List<Aluno> alunos = new ArrayList<>();
        List<Aluno> matriculas = new ArrayList<>();
        List<Aluno> disciplinas_feitas = new ArrayList<>();
        boolean respostas = true;   
        private static final String ARQUIVO_DADOS_ALUNOS = "alunos.txt";  
    
    public void cadastrarAlunos() {    
        while (respostas) {
            String nome = JOptionPane.showInputDialog(null, "Digite seu nome completo:");
            while (nome.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nome inválido. Digite novamente.");
                nome = JOptionPane.showInputDialog(null, "Digite seu nome: ");
            }
            String matricula = JOptionPane.showInputDialog(null, "Digite sua matrícula, precisa conter 9 dígitos: ");
            while (matricula.length() != 9) {
                JOptionPane.showMessageDialog(null, "Matrícula inválida. A matrícula deve conter 9 dígitos.");
                matricula = JOptionPane.showInputDialog(null, "Digite sua matrícula novamente: "); 
            }           
            boolean matriculaExiste;
            do {
                matriculaExiste = false;
                for (Aluno aluno : alunos) {
                    if (aluno.getMatricula().equals(matricula)) {
                        matriculaExiste = true;
                        JOptionPane.showMessageDialog(null, "Matrícula já cadastrada. Digite uma matrícula diferente.");
                        matricula = JOptionPane.showInputDialog(null, "Digite outra matrícula:");
                        break;
                    }
                }
            } while (matriculaExiste);
            String curso = JOptionPane.showInputDialog(null, "Digite o curso que você faz: ");
            while (curso.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Curso inválido. Digite novamente.");
                curso = JOptionPane.showInputDialog(null, "Digite o curso que você faz: ");
            }
            String tipoAluno = JOptionPane.showInputDialog(null, "Especifique o tipo de aluno (Comum/Especial):");
            while (!tipoAluno.equalsIgnoreCase("comum") && !tipoAluno.equalsIgnoreCase("especial")) {
                JOptionPane.showMessageDialog(null, "Tipo de aluno inválido. Digite 'Comum' ou 'Especial'");
                tipoAluno = JOptionPane.showInputDialog(null, "Especifique novamente o tipo de aluno (Comum/Especial): ");
            }
                        
            String disciplinasFeitas = JOptionPane.showInputDialog(null, "Digite as disciplinas já feitas (Sem abreviações e separadas por vírgula):");
            List<String> listaDisciplinasFeitas = new ArrayList<>();
            if (disciplinasFeitas != null && !disciplinasFeitas.trim().isEmpty()) {
                for (String disciplina : disciplinasFeitas.split(",")) {
                    String d = disciplina.trim();
                    if (!d.isEmpty()) {
                        listaDisciplinasFeitas.add(d);
                    }
                }
            }

            Aluno novoAluno = new Aluno(nome, matricula, curso, tipoAluno, listaDisciplinasFeitas);
            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
            alunos.add(novoAluno);

            String resposta = JOptionPane.showInputDialog(null, "Deseja cadastrar mais alunos? (S/N): ");
            if (resposta.equalsIgnoreCase("N")) {
                respostas = false;
            } else if (resposta.equalsIgnoreCase("S")) {
                respostas = true;
            } else {
                JOptionPane.showMessageDialog(null, "Opção inválida. O cadastro será encerrado.");
                respostas = false;
                }
            }
        }

        public void listarAlunos() {
        if (alunos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum aluno cadastrado.");
            return;
        }
        for (Aluno aluno : alunos) {
            StringBuilder dadosAluno = new StringBuilder();
            dadosAluno.append("Nome: ").append(aluno.getNome()).append("\n");
            dadosAluno.append("Matrícula: ").append(aluno.getMatricula()).append("\n");
            dadosAluno.append("Curso: ").append(aluno.getCurso()).append("\n");
            dadosAluno.append("Tipo: ").append(aluno.getTipoAluno()).append("\n");
            dadosAluno.append("Disciplinas já feitas: ").append(aluno.getDisciplinasFeitas());
            JOptionPane.showMessageDialog(null, dadosAluno.toString());
        }
    }
        public void editarCadastroAluno() {
            if (alunos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum aluno cadastrado.");
            return;
            }
            String[] opcoesAlunos = alunos.stream()
            .map(a -> a.getNome() + " | Matrícula: " + a.getMatricula())
            .toArray(String[]::new);
            String alunoSelecionado = (String) JOptionPane.showInputDialog(
            null,
            "Selecione o aluno que deseja editar:",
            "Editar Aluno",
            JOptionPane.PLAIN_MESSAGE,
            null,
            opcoesAlunos,
            opcoesAlunos[0]);
            if (alunoSelecionado == null) {
            return;
            }
            Aluno alunoEncontrado = null;
            for (Aluno a : alunos) {
            String desc = a.getNome() + " | Matrícula: " + a.getMatricula();
            if (desc.equals(alunoSelecionado)) {
                alunoEncontrado = a;
                break;
            }
            }
            if (alunoEncontrado == null) {
            JOptionPane.showMessageDialog(null, "Aluno não encontrado.");
            return;
            }
            String[] opcoesEdicao = {
            "Nome",
            "Matrícula",
            "Curso",
            "Tipo de aluno",
            "Disciplinas já feitas"
            };
            String opcao = (String) JOptionPane.showInputDialog(
            null,
            "O que deseja editar?",
            "Editar Campo",
            JOptionPane.PLAIN_MESSAGE,
            null,
            opcoesEdicao,
            opcoesEdicao[0]);
            if (opcao == null) {
            return;
            }
            switch (opcao.toLowerCase()) {
            case "nome":
                String novoNome = JOptionPane.showInputDialog(null, "Digite o novo nome: ");
                while (novoNome == null || novoNome.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nome inválido. Digite novamente.");
                novoNome = JOptionPane.showInputDialog(null, "Digite o novo nome: ");
                }
                alunoEncontrado.setNome(novoNome);
                break;
            case "matrícula":
            case "matricula":
                String novaMatricula = JOptionPane.showInputDialog(null, "Digite a nova matrícula (9 dígitos): ");
                while (novaMatricula == null || novaMatricula.length() != 9) {
                JOptionPane.showMessageDialog(null, "Matrícula inválida. A matrícula deve conter 9 dígitos.");
                novaMatricula = JOptionPane.showInputDialog(null, "Digite a nova matrícula (9 dígitos): ");
                }
                boolean matriculaExiste;
                do {
                matriculaExiste = false;
                for (Aluno aluno : alunos) {
                    if (aluno != alunoEncontrado && aluno.getMatricula().equals(novaMatricula)) {
                    matriculaExiste = true;
                    JOptionPane.showMessageDialog(null, "Matrícula já cadastrada. Digite uma matrícula diferente.");
                    novaMatricula = JOptionPane.showInputDialog(null, "Digite outra matrícula (9 dígitos):");
                    break;
                    }
                }
                } while (matriculaExiste);
                alunoEncontrado.setMatricula(novaMatricula);
                break;
            case "curso":
                String novoCurso = JOptionPane.showInputDialog(null, "Digite o novo curso: ");
                while (novoCurso == null || novoCurso.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Curso inválido. Digite novamente.");
                novoCurso = JOptionPane.showInputDialog(null, "Digite o novo curso: ");
                }
                alunoEncontrado.setCurso(novoCurso);
                break;
            case "tipo de aluno":
                String novoTipoAluno = JOptionPane.showInputDialog(null, "Digite o novo tipo de aluno (Comum/Especial): ");
                while (novoTipoAluno == null || (!novoTipoAluno.equalsIgnoreCase("comum") && !novoTipoAluno.equalsIgnoreCase("especial"))) {
                JOptionPane.showMessageDialog(null, "Tipo de aluno inválido. Digite 'Comum' ou 'Especial'");
                novoTipoAluno = JOptionPane.showInputDialog(null, "Especifique novamente o tipo de aluno (Comum/Especial): ");
                }
                alunoEncontrado.setTipoAluno(novoTipoAluno);
                break;
            case "disciplinas já feitas":
                String novasDisciplinasFeitas = JOptionPane.showInputDialog(null, "Digite as novas disciplinas já feitas (Sem abreviações e separadas por vírgulas)");
                List<String> listaDisciplinas = new ArrayList<>();
                if (novasDisciplinasFeitas != null && !novasDisciplinasFeitas.trim().isEmpty()) {
                for (String disciplina : novasDisciplinasFeitas.split(",")) {
                    listaDisciplinas.add(disciplina.trim());
                }
                }
                alunoEncontrado.setDisciplinasFeitas(listaDisciplinas);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opção inválida");
            }
            JOptionPane.showMessageDialog(null, "Edição feita com sucesso!");
        }

    public void excluirAluno() {
        if (alunos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum aluno cadastrado.");
            return;
        }
        String[] opcoes = new String[alunos.size()];
        for (int i = 0; i < alunos.size(); i++) {
            opcoes[i] = alunos.get(i).getNome() + " | Matrícula: " + alunos.get(i).getMatricula();
        }
        String alunoSelecionado = (String) JOptionPane.showInputDialog(
            null,
            "Selecione o aluno que deseja excluir:",
            "Excluir Aluno",
            JOptionPane.PLAIN_MESSAGE,
            null,
            opcoes,
            opcoes[0]);
        if (alunoSelecionado == null) {
            return;
        }
        Aluno alunoParaRemover = null;
        for (Aluno a : alunos) {
            String desc = a.getNome() + " | Matrícula: " + a.getMatricula();
            if (desc.equals(alunoSelecionado)) {
                alunoParaRemover = a;
                break;
            }
        }
        if (alunoParaRemover != null) {
            alunos.remove(alunoParaRemover);
            JOptionPane.showMessageDialog(null, "O aluno: " + alunoParaRemover.getNome() + " foi excluído com sucesso!");
        }
    }
    public List<Turmas> salvarDadosTurmas () {
        ModoDisciplina_Turma modoDT = new ModoDisciplina_Turma();
        modoDT.salvarDadosTurmas();
        return modoDT.getTurmas();
    }

    public List<Turmas> carregarDadosTurmas() {
        ModoDisciplina_Turma modoDT = new ModoDisciplina_Turma();
        modoDT.carregarDadosTurmas();
        return modoDT.getTurmas();
        
    }
    public void matricularAlunoTurma(List<Turmas> turmas) {        
        if (turmas == null || turmas.isEmpty()) {
            turmas = carregarDadosTurmas();
            if (turmas == null || turmas.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nenhuma turma cadastrada.");
                return;
            }
        }
        if (alunos == null || alunos.isEmpty()) {
            carregarDadosAlunos();
            if (alunos == null || alunos.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nenhum aluno cadastrado.");
                return;
            }
        }
        String[] nomesAlunos = alunos.stream().map(Aluno::getNome).toArray(String[]::new);
        String nomeSelecionado = (String) JOptionPane.showInputDialog(
            null,
            "Selecione o aluno para matricular:",
            "Selecionar Aluno",
            JOptionPane.PLAIN_MESSAGE,
            null,
            nomesAlunos,
            nomesAlunos[0]);
        if (nomeSelecionado == null) {
            return; 
        }
        Aluno alunoSelecionado = null;
        for (Aluno aluno : alunos) {
            if (aluno.getNome().equals(nomeSelecionado)) {
                alunoSelecionado = aluno;
                break;
            }
        }
        if (alunoSelecionado == null) {
            JOptionPane.showMessageDialog(null, "Aluno não encontrado.");
            return;
        }
        if (alunoSelecionado.getTurmasMatriculadas() == null) {
            alunoSelecionado.setTurmasMatriculadas(new ArrayList<>());
        }
        List<Turmas> turmasMatriculadas = alunoSelecionado.getTurmasMatriculadas();

        String[] nomesTurmas = turmas.stream()
            .map(t -> t.getNome() + " | Professor: " + t.getProfessor() + " | Horário: " + t.getHorario())
            .toArray(String[]::new);
        String turmaSelecionadaStr = (String) JOptionPane.showInputDialog(
            null,
            "Selecione a turma para matrícula (Nome | Professor | Horário):",
            "Selecionar Turma",
            JOptionPane.PLAIN_MESSAGE,
            null,
            nomesTurmas,
            nomesTurmas[0]);
        if (turmaSelecionadaStr == null) {
            return; 
        }
        String turmaSelecionada = turmaSelecionadaStr.split("\\|")[0].trim();
        Turmas turmaObj = null;
        for (Turmas t : turmas) {
            if (t.getNome().equalsIgnoreCase(turmaSelecionada)) {
                turmaObj = t;
                break;
            }
        }
        if (turmaObj == null) {
            JOptionPane.showMessageDialog(null, "Turma não encontrada.");
            return;
        }
        for (Turmas t : turmasMatriculadas) {
            if (t.getNome().equalsIgnoreCase(turmaObj.getNome())) {
                JOptionPane.showMessageDialog(null, "O aluno já está matriculado nesta turma.");
                return;
            }
        }
        List<String> preRequisitos = turmaObj.getPreRequisitos();
        List<String> disciplinasFeitas = alunoSelecionado.getDisciplinasFeitas();
        boolean possuiTodosPreRequisitos = true;
        StringBuilder faltando = new StringBuilder();

        if (preRequisitos != null && !preRequisitos.isEmpty()) {
            List<String> disciplinasFeitasNormalizadas = new ArrayList<>();
            if (disciplinasFeitas != null) {
                for (String feita : disciplinasFeitas) {
                    disciplinasFeitasNormalizadas.add(feita.trim().toLowerCase());
                }
            }
            for (String pre : preRequisitos) {
                String preNormalizado = pre.trim().toLowerCase();
                if (!disciplinasFeitasNormalizadas.contains(preNormalizado)) {
                    possuiTodosPreRequisitos = false;
                    faltando.append(pre.trim()).append(", ");
                }
            }
        }

        if (!possuiTodosPreRequisitos) {
            String faltam = faltando.toString();
            if (faltam.endsWith(", ")) {
                faltam = faltam.substring(0, faltam.length() - 2);
            }
            JOptionPane.showMessageDialog(null, "O aluno não possui os seguintes pré-requisitos: " + faltam);
            return;
        }
        if (turmaObj.getAlunosMatriculados() == null) {
            turmaObj.setAlunosMatriculados(new ArrayList<>());
        }
        if (!turmaObj.getAlunosMatriculados().contains(alunoSelecionado)) {
            turmaObj.getAlunosMatriculados().add(alunoSelecionado);
        }

        if (!turmasMatriculadas.contains(turmaObj)) {
            turmasMatriculadas.add(turmaObj);
            alunoSelecionado.setTurmasMatriculadas(turmasMatriculadas);
        }

        JOptionPane.showMessageDialog(null, "Aluno matriculado na turma " + turmaSelecionada + " com sucesso!");
        salvarDadosAlunos();
        if (turmas != null && !turmas.isEmpty()) {
            ModoDisciplina_Turma modoDT = new ModoDisciplina_Turma();
            if (turmaObj.getAlunosMatriculados() == null) {
                turmaObj.setAlunosMatriculados(new ArrayList<>());
            }
            if (!turmaObj.getAlunosMatriculados().contains(alunoSelecionado)) {
                turmaObj.getAlunosMatriculados().add(alunoSelecionado);
            }
            modoDT.setTurmas(turmas);
            modoDT.salvarDadosTurmas();
        }
    }
    
    public void trancarDisciplinaAluno() {
        if (alunos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum aluno cadastrado.");
            return;
        }
        String matricula = JOptionPane.showInputDialog(null, "Digite a matrícula do aluno que deseja trancar uma disciplina: ");
        Aluno alunoEncontrado = null;
        for (Aluno aluno : alunos) {
            if (aluno.getMatricula().equals(matricula)) {
                alunoEncontrado = aluno;
                break;
            }
        }
        if (alunoEncontrado == null) {
            JOptionPane.showMessageDialog(null, "Aluno não encontrado.");
            return;
        }
        List<Turmas> turmasMatriculadas = alunoEncontrado.getTurmasMatriculadas();
        if (turmasMatriculadas == null || turmasMatriculadas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "O aluno não está matriculado em nenhuma disciplina.");
            return;
        }
        String[] nomesTurmas = turmasMatriculadas.stream()
            .map(Turmas::getNome)
            .toArray(String[]::new);
        String nomeDisciplina = (String) JOptionPane.showInputDialog(
            null,
            "Selecione a disciplina que deseja trancar:",
            "Trancar Disciplina",
            JOptionPane.PLAIN_MESSAGE,
            null,
            nomesTurmas,
            nomesTurmas[0]);
        if (nomeDisciplina == null) {
            return;
        }
        alunoEncontrado.trancarDisciplina();
        JOptionPane.showMessageDialog(null, "Disciplina " + nomeDisciplina + " trancada para o aluno " + alunoEncontrado.getNome() + "!");
    }

    public void trancarSemestreAluno() {
        if (alunos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum aluno cadastrado.");
            return;
        }
        String[] opcoesAlunos = alunos.stream()
            .map(a -> a.getNome() + " | Matrícula: " + a.getMatricula())
            .toArray(String[]::new);
        String alunoSelecionado = (String) JOptionPane.showInputDialog(
            null,
            "Selecione o aluno que deseja trancar o semestre:",
            "Trancar Semestre",
            JOptionPane.PLAIN_MESSAGE,
            null,
            opcoesAlunos,
            opcoesAlunos[0]);
        if (alunoSelecionado == null) {
            return;
        }
        Aluno alunoEncontrado = null;
        for (Aluno a : alunos) {
            String desc = a.getNome() + " | Matrícula: " + a.getMatricula();
            if (desc.equals(alunoSelecionado)) {
                alunoEncontrado = a;
                break;
            }
        }
        if (alunoEncontrado == null) {
            JOptionPane.showMessageDialog(null, "Aluno não encontrado.");
            return;
        }
        alunoEncontrado.trancarSemestre(alunoSelecionado);
        JOptionPane.showMessageDialog(null, "Semestre trancado para o aluno " + alunoEncontrado.getNome() + "!");
    }

    public void salvarDadosAlunos() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVO_DADOS_ALUNOS, false))) {
            for (Aluno aluno : alunos) {
                writer.println("Nome: " +
                    aluno.getNome() + "; Matrícula: " +
                    aluno.getMatricula() + "; Curso de graduação: " +
                    aluno.getCurso() + "; Tipo de aluno: " +
                    aluno.getTipoAluno() + "; Disciplinas feitas: " +
                    String.join(",", aluno.getDisciplinasFeitas())
                    + "; Turmas Matriculadas: "
                    + aluno.getTurmasMatriculadas().stream().map(Turmas::getNome).reduce((a, b) -> a + ", " + b).orElse("Nenhuma turma matriculada")
                    + "; Avaliação: " + aluno.getAvaliacao());
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar os dados dos alunos: " + e.getMessage());
        }
    }

    public void carregarDadosAlunos() {
        alunos.clear();
        File arquivo = new File(ARQUIVO_DADOS_ALUNOS);
        if (!arquivo.exists() || arquivo.length() == 0) {
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_DADOS_ALUNOS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;
                String[] partes = linha.split(";", -1);
                if (partes.length < 5) continue;
                String nome = partes[0].replace("Nome:", "").trim();
                String matricula = partes[1].replace("Matrícula:", "").trim();
                String curso = partes[2].replace("Curso de graduação:", "").trim();
                String tipoAluno = partes[3].replace("Tipo de aluno:", "").trim();
                List<String> disciplinasFeitas = new ArrayList<>();
                if (!partes[4].replace("Disciplinas feitas:", "").trim().isEmpty()) {
                    for (String d : partes[4].replace("Disciplinas feitas:", "").trim().split(",")) {
                        disciplinasFeitas.add(d.trim());
                    }
                }
                List<Turmas> turmasMatriculadas = new ArrayList<>();
                if (partes.length > 5 && !partes[5].replace("Turmas Matriculadas:", "").trim().isEmpty()) {
                    String turmasStr = partes[5].replace("Turmas Matriculadas:", "").trim();
                    if (!turmasStr.equals("Nenhuma turma matriculada")) {
                        String[] nomesTurmas = turmasStr.split(",");
                        List<Turmas> todasTurmas = carregarDadosTurmas();
                        for (String nomeTurma : nomesTurmas) {
                            String nomeTurmaTrim = nomeTurma.trim();
                            for (Turmas t : todasTurmas) {
                                if (t.getNome().equalsIgnoreCase(nomeTurmaTrim)) {
                                    turmasMatriculadas.add(t);
                                    break;
                                }
                            }
                        }
                    }
                }
                Aluno aluno = new Aluno(nome, matricula, curso, tipoAluno, disciplinasFeitas);
                aluno.setTurmasMatriculadas(turmasMatriculadas);
                alunos.add(aluno);
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Arquivo não encontrado: " + ARQUIVO_DADOS_ALUNOS);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar os dados dos alunos: " + e.getMessage());
        }
    }
    public List<Aluno> getAlunos() {return alunos;}
    public void setAlunos(List<Aluno> alunos) {this.alunos = alunos;}
}
