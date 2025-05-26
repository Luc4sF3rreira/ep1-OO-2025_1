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
            for (String disciplina : disciplinasFeitas.split(",")) {
                listaDisciplinasFeitas.add(disciplina.trim());          
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
        String matricula = JOptionPane.showInputDialog(null, "Digite a matrícula do aluno que deseja editar: ");
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
        String opcao = JOptionPane.showInputDialog(null, "O que deseja editar?\n" +
                "       Nome\n" +
                "       Matrícula\n" +
                "       Curso\n" +
                "       Tipo de aluno\n" +
                "       Disciplinas já feitas\n" +
                "       Digite a opção desejada: ");
        switch (opcao.toLowerCase()) {
            case "nome":
                String novoNome = JOptionPane.showInputDialog(null, "Digite o novo nome: ");
                while (novoNome.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Nome inválido. Digite novamente.");
                    novoNome = JOptionPane.showInputDialog(null, "Digite o novo nome: ");
                }
                alunoEncontrado.setNome(novoNome);
                break;
            case "matrícula":
            case "matricula":
                String novaMatricula = JOptionPane.showInputDialog(null, "Digite a nova matrícula: ");
                alunoEncontrado.setMatricula(novaMatricula);
                boolean matriculaExiste;
                do {
                    matriculaExiste = false;
                    for (Aluno aluno : alunos) {
                        if (aluno.getMatricula().equals(novaMatricula)) {
                            matriculaExiste = true;
                            JOptionPane.showMessageDialog(null, "Matrícula já cadastrada. Digite uma matrícula diferente.");
                            novaMatricula = JOptionPane.showInputDialog(null, "Digite outra matrícula:");
                            break;
                        }
                    }
                } while (matriculaExiste);
                break;
            case "curso":
                String novoCurso = JOptionPane.showInputDialog(null, "Digite o novo curso: ");
                while (novoCurso.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Curso inválido. Digite novamente.");
                    novoCurso = JOptionPane.showInputDialog(null, "Digite o novo curso: ");
                }
                alunoEncontrado.setCurso(novoCurso);
                break;
            case "tipo de aluno":
                String novoTipoAluno = JOptionPane.showInputDialog(null, "Digite o novo tipo de aluno (Comum/Especial): ");
                while (!novoTipoAluno.equalsIgnoreCase("comum") && !novoTipoAluno.equalsIgnoreCase("especial")) {
                    JOptionPane.showMessageDialog(null, "Tipo de aluno inválido. Digite 'Comum' ou 'Especial'");
                    novoTipoAluno = JOptionPane.showInputDialog(null, "Especifique novamente o tipo de aluno (Comum/Especial): ");
                }
                alunoEncontrado.setTipoAluno(novoTipoAluno);
                break;
            case "disciplinas já feitas":
                String novasDisciplinasFeitas = JOptionPane.showInputDialog(null, "Digite as novas disciplinas já feitas (Sem abreviações e separadas por vírgulas)");
                List<String> listaDisciplinas = new ArrayList<>();
                for (String disciplina : novasDisciplinasFeitas.split(",")) {
                    listaDisciplinas.add(disciplina.trim());
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
        String matricula = JOptionPane.showInputDialog(null, "Digite a matrícula do aluno que deseja excluir: ");
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
        alunos.remove(alunoEncontrado);
        JOptionPane.showMessageDialog(null, "O aluno: " + alunoEncontrado.getNome() + " foi excluído com sucesso!");
    }

    public List<Turmas> carregarDadosTurmas() {return Turmas.getTodasTurmas();}

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
        String[] nomesTurmas = turmas.stream().map(Turmas::getNome).toArray(String[]::new);
        String turmaSelecionada = (String) JOptionPane.showInputDialog(
            null,
            "Selecione a turma para matrícula:",
            "Selecionar Turma",
            JOptionPane.PLAIN_MESSAGE,
            null,
            nomesTurmas,
            nomesTurmas[0]);
        if (turmaSelecionada == null) {
            return; 
        }
        Turmas turmaObj = null;
        for (Turmas t : turmas) {
            if (t.getNome().equals(turmaSelecionada)) {
                turmaObj = t;
                break;
            }
        }
        if (turmaObj == null) {
            JOptionPane.showMessageDialog(null, "Turma não encontrada.");
            return;
        }
        if (alunoSelecionado.getTurmasMatriculadas() == null) {
            alunoSelecionado.setTurmasMatriculadas(new ArrayList<>());
        }
        List<Turmas> turmasMatriculadas = alunoSelecionado.getTurmasMatriculadas();
        for (Turmas t : turmasMatriculadas) {
            if (t.getNome().equalsIgnoreCase(turmaObj.getNome())) {
                JOptionPane.showMessageDialog(null, "O aluno já está matriculado nesta turma.");
                return;
            }
        }
        turmasMatriculadas.add(turmaObj);
        JOptionPane.showMessageDialog(null, "Aluno matriculado na turma " + turmaSelecionada + " com sucesso!");
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
            JOptionPane.showMessageDialog(null,"Aluno não encontrado.");
            return;
        }
        List<Turmas> turmasMatriculadas = alunoEncontrado.getTurmasMatriculadas();
        if (turmasMatriculadas == null || turmasMatriculadas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "O aluno não está matriculado em nenhuma disciplina.");
            return;
        }
        JOptionPane.showMessageDialog(null, "Disciplinas em que o aluno está matriculado:");
        for (Turmas turma : turmasMatriculadas) {
            JOptionPane.showMessageDialog(null, "- " + turma);
        }
        String nomeDisciplina = JOptionPane.showInputDialog(null, "Digite o nome da disciplina que deseja trancar: ");
        Turmas disciplina = null;
        for (Turmas t : turmasMatriculadas) {
            if (t.getNome().equalsIgnoreCase(nomeDisciplina)) {
                disciplina = t;
                break;
            }
        }
        if (disciplina == null) {
            JOptionPane.showConfirmDialog(null, "O aluno não está matriculado nesta disciplina.");
            return;
        }
        alunoEncontrado.trancarDisciplina(disciplina);
        JOptionPane.showMessageDialog(null, "Disciplina " + disciplina.getNome() + " trancada com sucesso para o aluno " + alunoEncontrado.getNome() + "!");
    }

    public void trancarSemestreAluno() {
        if (alunos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum aluno cadastrado.");
            return;
        }
        String matricula = JOptionPane.showInputDialog(null, "Digite a matrícula do aluno que deseja trancar o semestre: ");
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
        alunoEncontrado.trancarSemestre(alunoEncontrado.getMatricula());       
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
                );
            }
            JOptionPane.showMessageDialog(null, "Dados dos alunos salvos com sucesso em " + ARQUIVO_DADOS_ALUNOS);
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
                List<String> turmasMatriculadas = new ArrayList<>();
                if (partes.length > 5 && !partes[5].replace("Turmas Matriculadas:", "").trim().isEmpty()) {
                    for (String t : partes[5].replace("Turmas Matriculadas:", "").trim().split(",")) {
                        turmasMatriculadas.add(t.trim());
                    }
                }
                alunos.add(new Aluno(nome, matricula, curso, tipoAluno, disciplinasFeitas));
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Arquivo não encontrado: " + ARQUIVO_DADOS_ALUNOS);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar os dados dos alunos: " + e.getMessage());
        }
    }
}
