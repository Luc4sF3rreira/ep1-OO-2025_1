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

            System.out.print("Digite sua matrícula: ");
            String matricula = scanner.nextLine();
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

    public void listarAlunos() {
        System.out.println("---Lista de Alunos---");
        for (Aluno aluno : alunos) {    
            System.out.println("Nome: " + aluno.getNome() + ", Matrícula: " + aluno.getMatricula() + ", Curso: " + aluno.getCurso());
        }
    }

}
