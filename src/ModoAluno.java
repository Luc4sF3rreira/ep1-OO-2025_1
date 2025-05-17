import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ModoAluno {
        List<Aluno> alunos = new ArrayList<>();
        List<Aluno> matriculas = new ArrayList<>();
        List<Aluno> disciplinas_feitas = new ArrayList<>();
        boolean respostas = true;     
    
    public void cadastrarAlunos() {
        Scanner scanner = new Scanner(System.in);

        while (respostas) {
            System.out.print("Digite seu nome: ");
            String nome = scanner.nextLine();

            System.out.print("Digite sua matrícula, precisa conter 9 dígitos: ");
            String matricula = scanner.nextLine();
            while (matricula.length() != 9) {
                System.out.println("Matrícula inválida. A matrícula deve conter 9 dígitos.");
                System.out.print("Digite sua matrícula novamente: ");
                matricula = scanner.nextLine();
            }           
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

            System.out.print("Tipo de aluno (Comum/Especial): ");
            String tipoAluno = scanner.nextLine();
            while (!tipoAluno.equalsIgnoreCase("comum") && !tipoAluno.equalsIgnoreCase("especial")) {
                System.out.println("Tipo de aluno inválido. Digite 'Comum' ou 'Especial': ");
                tipoAluno = scanner.nextLine();
            }

            System.out.print("Digite as disciplinas já feitas (separadas por vírgula): ");
            String disciplinasFeitas = scanner.nextLine();
        
            Aluno novoAluno = new Aluno(nome, matricula, curso, tipoAluno, disciplinasFeitas);
            System.out.println("-------------------------------------------------");
            System.out.println("Aluno cadastrado com sucesso!"); 
            System.out.println("Nome: " + novoAluno.getNome());
            System.out.println("Matrícula: " + novoAluno.getMatricula());
            System.out.println("Curso: " + novoAluno.getCurso());
            System.out.println("Tipo: " + tipoAluno);
            System.out.println("Disciplinas já feitas: " + disciplinasFeitas);
            alunos.add(novoAluno);
            System.out.print("Deseja cadastrar mais alunos? (S/N): ");
            String resposta = scanner.nextLine();
            if (resposta.equalsIgnoreCase("N")) {
                respostas = false;
            } else if (resposta.equalsIgnoreCase("S")) {
                respostas = true;
            } else {
                System.out.println("Resposta inválida. Tente novamente.");       
            }
        }
    }

    public void editarCadastroAluno() {
        Scanner edição = new Scanner(System.in);
        System.out.print("Digite a matrícula do aluno que deseja editar: ");
        String matricula = edição.nextLine();
        Aluno alunoEncontrado = null;
        for (Aluno aluno : alunos) {
            if (aluno.getMatricula().equals(matricula)) {
                alunoEncontrado = aluno;
                break;            
            }
        }
        if (alunoEncontrado == null) {
                System.out.println("Aluno não encontrado.");
                return;
            }

        System.out.println("O que deseja editar?");
        System.out.println("Nome");
        System.out.println("Matrícula");
        System.out.println("Curso");
        System.out.println("Tipo de aluno");
        System.out.println("Disciplinas já feitas");
        System.out.print("Digite a opção desejada: ");
        String opcao = edição.nextLine();
        switch (opcao.toLowerCase()) {
            case "nome":
                System.out.print("Digite o novo nome: ");
                String novoNome = edição.nextLine();
                alunoEncontrado.setNome(novoNome);
                break;
            case "matrícula":
                System.out.print("Digite a nova matrícula: ");
                String novaMatricula = edição.nextLine();
                alunoEncontrado.setMatricula(novaMatricula);
                break;
            case "curso":
                System.out.print("Digite o novo curso: ");
                String novoCurso = edição.nextLine();
                alunoEncontrado.setCurso(novoCurso);
                break;
            case "tipo de aluno":
                System.out.print("Digite o novo tipo de aluno (Comum/Especial): ");
                String novoTipoAluno = edição.nextLine();
                alunoEncontrado.setTipoAluno(novoTipoAluno);
                break;
            case "disciplinas já feitas":
                System.out.print("Digite as novas disciplinas já feitas (separadas por vírgula): ");
                String novasDisciplinasFeitas = edição.nextLine();
                List<String> listaDisciplinas = new ArrayList<>();
                for (String disciplina : novasDisciplinasFeitas.split(",")) {
                    listaDisciplinas.add(disciplina.trim());
            }
                alunoEncontrado.setDisciplinasFeitas(listaDisciplinas);
                break;
            default:
                System.out.println("Opção inválida.");    
            
            }
        
        System.out.println("-------------------------------------------------");
        System.out.println("Edição feita com sucesso!");
        System.out.println("Nome: " + alunoEncontrado.getNome());
        System.out.println("Matrícula: " + alunoEncontrado.getMatricula());
        System.out.println("Curso: " + alunoEncontrado.getCurso());
        System.out.println("Tipo: " + alunoEncontrado.getTipoAluno());
        System.out.println("Disciplinas já feitas: " + alunoEncontrado.getDisciplinasFeitas());
    }
        
    
    public void listarAlunos() {
        System.out.println("---Lista de Alunos---");
        for (Aluno aluno : alunos) {    
            System.out.println("Nome: " + aluno.getNome() + ", Matrícula: " + aluno.getMatricula() + ", Curso: " + aluno.getCurso());
        }
    }
}
