import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


public class ModoAluno {
    // Método para cadastrar um aluno
    public static void main(String[] args) {
        ModoAluno modoAluno = new ModoAluno();
        modoAluno.matricularTurma(null, null, null);
    }
    public void cadastrarAluno() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite seu nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite sua matrícula: ");
        String matricula = scanner.nextLine();        
        List<Aluno> alunos = new ArrayList<>();
        boolean matriculaExiste;
        do {
            matriculaExiste = false;
            for (Aluno aluno : alunos) {
            if (aluno.getMatricula().equals(matricula)) {
                matriculaExiste = true;
                System.out.println("Matrícula já cadastrada. Digite uma matrícula diferente.");
                System.out.print("Digite outra matrícula: ");
                matricula = scanner.nextLine();
                break;
            }
            }
        } while (matriculaExiste);

        System.out.print("Digite o curso que você faz: ");
        String curso = scanner.nextLine();

        System.out.print("Tipo de aluno (comum/especial): ");
        String tipoAluno = scanner.nextLine();
        while (!tipoAluno.equalsIgnoreCase("comum") && !tipoAluno.equalsIgnoreCase("especial")) {
            System.out.println("Tipo de aluno inválido. Digite 'comum' ou 'especial': ");
            tipoAluno = scanner.nextLine();
        }

        System.out.print("Digite as disciplinas já feitas (separadas por vírgula): ");
        String disciplinasFeitas = scanner.nextLine();

        Aluno novoAluno = new Aluno(nome, matricula, curso);
        System.out.println("---Aluno cadastrado com sucesso!--- ");
        System.out.println("Nome: " + novoAluno.getNome());
        System.out.println("Matrícula: " + novoAluno.getMatricula());
        System.out.println("Curso: " + novoAluno.getCurso());
        System.out.println("Tipo: " + tipoAluno);

        System.out.print("Deseja cadastrar mais alunos? (S/N): ");
        String resposta = scanner.nextLine();
        alunos.add(novoAluno);

        // Loop para cadastrar mais alunos
        while (resposta.equalsIgnoreCase("s")) {
            System.out.print("Digite seu nome: ");
            nome = scanner.nextLine();

            System.out.print("Digite sua matrícula: ");
            matricula = scanner.nextLine();

            do {
                matriculaExiste = false;
                for (Aluno aluno : alunos) {
                    if (aluno.getMatricula().equals(matricula)) {
                        matriculaExiste = true;
                        System.out.println("Matrícula já cadastrada. Digite uma matrícula diferente.");
                        System.out.print("Digite outra matrícula: ");
                        matricula = scanner.nextLine();
                        break;
                    }
                }
            } while (matriculaExiste);                     
                
                
            System.out.print("Digite o curso que você faz: ");
            curso = scanner.nextLine();

            System.out.print("Tipo de aluno (comum/especial): ");
            tipoAluno = scanner.nextLine();

            System.out.print("Digite as disciplinas já feitas (separadas por vírgula): ");
            String disciplinasFeitasInput = scanner.nextLine();
            List<Disciplina> disciplinas = new ArrayList<>();
            for (String nomeDisciplina : disciplinasFeitasInput.split(",")) {
                disciplinas.add(new Disciplina(nomeDisciplina.trim(), "", 0, new ArrayList<>()));
            }

            Aluno aluno = new Aluno(nome, matricula, curso);
            alunos.add(aluno);
            System.out.println("---Aluno cadastrado com sucesso!--- ");
            System.out.println("Nome: " + aluno.getNome());
            System.out.println("Matrícula: " + aluno.getMatricula());
            System.out.println("Curso: " + aluno.getCurso());
            System.out.println("Tipo: " + tipoAluno);

            System.out.print("Deseja cadastrar mais alunos? (S/N): ");
            resposta = scanner.nextLine();
            
        }
        System.out.println("Alunos cadastrado(s) com sucesso!");
        for (Aluno aluno : alunos) {
            System.out.println("Nome: " + aluno.getNome());
            System.out.println("Matrícula: " + aluno.getMatricula());
            System.out.println("Curso: " + aluno.getCurso());
            System.out.println("Tipo: " + tipoAluno);
        }
    }
    
    public void matricularTurma (Aluno aluno, Aluno matricula, Turmas turma) {
        if (turma.getVagasDisponiveis() > 0) {
            turma.adicionarAluno(aluno);
            aluno.getTurmasMatriculadas().add(turma);

            if (!turma.getDisciplina().getPreRequisitos().isEmpty()) {
                for (String preRequisito : turma.getDisciplina().getPreRequisitos()) {
                    if (aluno.getDisciplinasFeitas().stream().noneMatch(disciplina -> disciplina.getNome().equals(preRequisito))) {
                        System.out.println("Aluno " + aluno.getNome() + " não pode se matricular na turma " + turma.getNome() + 
                                           " porque não completou o pré-requisito: " + preRequisito + ".");
                        return;
                    }
                }
            }
       
            if (aluno instanceof AlunoEspecial) {
                if (aluno.getTurmasMatriculadas().size() >= 2) {
                    System.out.println("Aluno especial só pode se matricular em até 2 disciplinas.");
                    return;
                }
            }
            System.out.println("Aluno " + aluno.getNome() + " matriculado com sucesso na turma " + turma.getNome() + ".");
        } else {
            System.out.println("Não há vagas disponíveis na turma " + turma.getNome() + ".");
        }
    }

    public void trancarDisciplina(Aluno aluno, Disciplina disciplina) {
        if (aluno.getDisciplinasFeitas().contains(disciplina)) {
            aluno.getDisciplinasFeitas().remove(disciplina);
            System.out.println("Disciplina " + disciplina.getNome() + " trancada com sucesso.");
        } else {
            System.out.println("Disciplina " + disciplina.getNome() + " não encontrada entre as disciplinas feitas.");
        }
    }    

    public void trancarSemestre(Aluno aluno, String semestre) {
        if (aluno.getTurmasMatriculadas().stream().anyMatch(turma -> turma.getSemestre().equals(semestre))) {
            aluno.getTurmasMatriculadas().removeIf(turma -> turma.getSemestre().equals(semestre));
            System.out.println("Turmas do semestre " + semestre + " trancadas com sucesso.");
        } else {
            System.out.println("Nenhuma turma encontrada para o semestre " + semestre + ".");
        }
    }

    public void salvarDados(Aluno aluno) {
        System.out.println("Dados do aluno " + aluno.getNome() + " salvos com sucesso.");
    }

    public void carregarDados(Aluno aluno) {
        System.out.println("Dados do aluno " + aluno.getNome() + " carregados com sucesso.");
    }



}
    
