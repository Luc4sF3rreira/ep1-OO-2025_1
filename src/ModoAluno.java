import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.io.*;

public class ModoAluno {
        List<Aluno> alunos = new ArrayList<>();
        List<Aluno> matriculas = new ArrayList<>();
        List<Aluno> disciplinas_feitas = new ArrayList<>();
        boolean respostas = true;   
        private static final String ARQUIVO_DADOS = "DadosAlunos.txt";  
    
    public void cadastrarAlunos() {    
        while (respostas) {
            String nome = JOptionPane.showInputDialog(null, "Digite seu nome:");
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
            StringBuilder dadosAluno = new StringBuilder();
            dadosAluno.append("Aluno cadastrado com sucesso!\n");
            dadosAluno.append("\n");
            dadosAluno.append("Dados do Aluno:\n");
            dadosAluno.append("     Nome: ").append(novoAluno.getNome()).append("\n");
            dadosAluno.append("     Matrícula: ").append(novoAluno.getMatricula()).append("\n");
            dadosAluno.append("     Curso: ").append(novoAluno.getCurso()).append("\n");
            dadosAluno.append("     Tipo: ").append(tipoAluno).append("\n");
            dadosAluno.append("     Disciplinas já feitas: ").append(disciplinasFeitas);      
            JOptionPane.showMessageDialog(null, dadosAluno.toString());
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

    public void matricularAlunoTurma(List<Turmas> turmasDisponiveis) {
        if (alunos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum aluno cadastrado.");
            return;
        }
        if (turmasDisponiveis == null || turmasDisponiveis.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma turma cadastrada.");
            return;
        }
        // Seleção de aluno
        String[] nomesAlunos = alunos.stream().map(Aluno::getNome).toArray(String[]::new);
        String nomeSelecionado = (String) JOptionPane.showInputDialog(
            null,
            "Selecione o aluno para matricular:",
            "Selecionar Aluno",
            JOptionPane.PLAIN_MESSAGE,
            null,
            nomesAlunos,
            nomesAlunos[0]
        );
        if (nomeSelecionado == null) {
            return; // Usuário cancelou
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
        // Seleção de turma
        String[] nomesTurmas = turmasDisponiveis.stream().map(Turmas::getNome).toArray(String[]::new);
        String turmaSelecionada = (String) JOptionPane.showInputDialog(
            null,
            "Selecione a turma para matrícula:",
            "Selecionar Turma",
            JOptionPane.PLAIN_MESSAGE,
            null,
            nomesTurmas,
            nomesTurmas[0]
        );
        if (turmaSelecionada == null) {
            return; // Usuário cancelou
        }
        Turmas turmaObj = null;
        for (Turmas t : turmasDisponiveis) {
            if (t.getNome().equals(turmaSelecionada)) {
                turmaObj = t;
                break;
            }
        }
        if (turmaObj == null) {
            JOptionPane.showMessageDialog(null, "Turma não encontrada.");
            return;
        }
        alunoSelecionado.matricularTurma(turmaObj);
        JOptionPane.showMessageDialog(null, "Aluno matriculado na turma " + turmaSelecionada + " com sucesso!");
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
        StringBuilder dadosEditados = new StringBuilder();
        dadosEditados.append("Nome: ").append(alunoEncontrado.getNome()).append("\n");
        dadosEditados.append("Matrícula: ").append(alunoEncontrado.getMatricula()).append("\n");
        dadosEditados.append("Curso: ").append(alunoEncontrado.getCurso()).append("\n");
        dadosEditados.append("Tipo: ").append(alunoEncontrado.getTipoAluno()).append("\n");
        dadosEditados.append("Disciplinas já feitas: ").append(alunoEncontrado.getDisciplinasFeitas());
        JOptionPane.showMessageDialog(null, dadosEditados.toString());
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
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVO_DADOS))) {
            for (Aluno aluno : alunos) {
                // nome;matricula;curso;tipo;disciplina1,disciplina2,...
                writer.println(
                    aluno.getNome() + ";" +
                    aluno.getMatricula() + ";" +
                    aluno.getCurso() + ";" +
                    aluno.getTipoAluno() + ";" +
                    String.join(",", aluno.getDisciplinasFeitas())
                );
            }
            JOptionPane.showMessageDialog(null, "Dados dos alunos salvos com sucesso em " + ARQUIVO_DADOS);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar os dados dos alunos: " + e.getMessage());
        }
    }

    public void carregarDadosAlunos() {
        alunos.clear();
        File arquivo = new File(ARQUIVO_DADOS);
        if (!arquivo.exists() || arquivo.length() == 0) {
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_DADOS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;
                String[] partes = linha.split(";", -1);
                if (partes.length < 5) continue;
                String nome = partes[0];
                String matricula = partes[1];
                String curso = partes[2];
                String tipoAluno = partes[3];
                List<String> disciplinasFeitas = new ArrayList<>();
                if (!partes[4].isEmpty()) {
                    for (String d : partes[4].split(",")) {
                        disciplinasFeitas.add(d.trim());
                    }
                }
                alunos.add(new Aluno(nome, matricula, curso, tipoAluno, disciplinasFeitas));
            }
            JOptionPane.showMessageDialog(null, "Dados dos alunos carregados com sucesso de " + ARQUIVO_DADOS);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Arquivo não encontrado: " + ARQUIVO_DADOS);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar os dados dos alunos: " + e.getMessage());
        }
    }
}
