import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.io.*;

public class ModoAluno {
        List<Aluno> alunos = new ArrayList<>();
        List<Aluno> matriculas = new ArrayList<>();
        List<Aluno> disciplinas_feitas = new ArrayList<>();
        boolean respostas = true;     
    
    public void cadastrarAlunos() {    
        while (respostas) {
            String nome = JOptionPane.showInputDialog(null, "Digite seu nome:");

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
            if (tipoAluno.equalsIgnoreCase("comum")) {
                tipoAluno = "Comum";
            } else {
                tipoAluno = "Especial";
            }

            String disciplinasFeitas = JOptionPane.showInputDialog(null, "Digite as disciplinas já feitas (Sem abreviações e separadas por vírgula):");
            
            List<String> listaDisciplinasFeitas = new ArrayList<>();
            for (String disciplinaFeita : disciplinasFeitas.split(",")) {
                listaDisciplinasFeitas.add(disciplinaFeita.trim());
            }
        
            Aluno novoAluno = new Aluno(nome, matricula, curso, tipoAluno, listaDisciplinasFeitas);
            StringBuilder dadosAluno = new StringBuilder();
            dadosAluno.append("Aluno cadastrado com sucesso!\n");
            dadosAluno.append("-------------------------------------------------\n");
            dadosAluno.append("Dados do Aluno:\n");
            dadosAluno.append("   Nome: ").append(novoAluno.getNome()).append("\n");
            dadosAluno.append("   Matrícula: ").append(novoAluno.getMatricula()).append("\n");
            dadosAluno.append("   Curso: ").append(novoAluno.getCurso()).append("\n");
            dadosAluno.append("   Tipo: ").append(tipoAluno).append("\n");
            dadosAluno.append("   Disciplinas já feitas: ").append(disciplinasFeitas);      
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

    public void matricularAlunoTurma() {
        String matricula = JOptionPane.showInputDialog(null, "Digite a matrícula do aluno que deseja matricular: ");
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
        String nomeTurma = JOptionPane.showInputDialog(null, "Digite o nome da turma para matrícula: ");
        alunoEncontrado.matricularTurma(nomeTurma);
        JOptionPane.showMessageDialog(null, "Aluno matriculado na turma " + nomeTurma + "com sucesso!");
    }

    public void editarCadastroAluno() {
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
                "1. Nome\n" +
                "2. Matrícula\n" +
                "3. Curso\n" +
                "4. Tipo de aluno\n" +
                "5. Disciplinas já feitas\n" +
                "Digite a opção desejada: ");
        switch (opcao.toLowerCase()) {
            case "nome":
                String novoNome = JOptionPane.showInputDialog(null, "Digite o novo nome: ");
                alunoEncontrado.setNome(novoNome);
                break;
            case "matrícula":
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
                JOptionPane.showMessageDialog(null, "Opção inválida" );            
            }
        
        JOptionPane.showMessageDialog(null, "-------------------------------------------------");
        JOptionPane.showMessageDialog(null, "Edição feita com sucesso!");
        JOptionPane.showMessageDialog(null, "Nome: " + alunoEncontrado.getNome());
        JOptionPane.showMessageDialog(null, "Matrícula: " + alunoEncontrado.getMatricula());
        JOptionPane.showMessageDialog(null, "Curso: " + alunoEncontrado.getCurso());
        JOptionPane.showMessageDialog(null, "Tipo: " + alunoEncontrado.getTipoAluno());
        JOptionPane.showMessageDialog(null, "Disciplinas já feitas: " + alunoEncontrado.getDisciplinasFeitas());
    }        
    
    public void listarAlunos() {
        if (alunos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum aluno cadastrado.");
            return;
        }
        JOptionPane.showMessageDialog(null, "---Lista de Alunos---");
        for (Aluno aluno : alunos) {
            JOptionPane.showMessageDialog(null, "Nome: " + aluno.getNome() + "\nMatricula: " + aluno.getMatricula() + "\nCurso: " + aluno.getCurso() + "\nTipo: " + aluno.getTipoAluno() + "\nDisciplinas já feitas: " + aluno.getDisciplinasFeitas());    
        }
    }

    public void trancarDisciplinaAluno() {
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
        JOptionPane.showMessageDialog(null,"Semestre trancado com sucesso para o aluno " + alunoEncontrado.getNome() + "!" );
    }






















    public void salvarAlunosEmArquivo(String caminhoArquivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminhoArquivo))) {
            oos.writeObject(alunos);
            System.out.println("Dados dos alunos salvos com sucesso em " + caminhoArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados dos alunos: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void carregarAlunosDeArquivo(String caminhoArquivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(caminhoArquivo))) {
            alunos = (List<Aluno>) ois.readObject();
            System.out.println("Dados dos alunos carregados com sucesso de " + caminhoArquivo);
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado. Nenhum dado carregado.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar os dados dos alunos: " + e.getMessage());
        }
    }
    

}
