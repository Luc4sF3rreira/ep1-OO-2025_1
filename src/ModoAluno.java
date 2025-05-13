import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


public class ModoAluno {
    public static void main(String[] args) {
        ModoAluno modoAluno = new ModoAluno();
        modoAluno.cadastrarAluno();
    }

    public void cadastrarAluno() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome do aluno: ");
        String nome = scanner.nextLine();

        System.out.print("Digite a matrícula do aluno: ");
        String matricula = scanner.nextLine();

        System.out.print("Digite o curso do aluno: ");
        String curso = scanner.nextLine();

        Aluno novoAluno = new Aluno(nome, matricula, curso);
        System.out.println("Aluno cadastrado com sucesso: " + novoAluno);

        System.out.print("Deseja cadastrar outro aluno? (s/n): ");
        String resposta = scanner.nextLine();
        while (resposta.equalsIgnoreCase("s")) {
            System.out.print("Digite o nome do aluno: ");
            nome = scanner.nextLine();

            System.out.print("Digite a matrícula do aluno: ");
            matricula = scanner.nextLine();

            System.out.print("Digite o curso do aluno: ");
            curso = scanner.nextLine();

            novoAluno = new Aluno(nome, matricula, curso);
            System.out.println("Aluno cadastrado com sucesso: " + novoAluno);

            System.out.print("Deseja cadastrar outro aluno? (s/n): ");
            resposta = scanner.nextLine();
        }
        System.out.println("Cadastro de alunos finalizado.");
        
        List<Aluno> listaAlunos = new ArrayList<>();
        listaAlunos.add(novoAluno);

        scanner.close();
                    
    }

    public void listarAlunos(List<Aluno> listaAlunos) {
        System.out.println("Lista de Alunos:");
        for (Aluno aluno : listaAlunos) {
            System.out.println(aluno);
        }
    }

    public void matricularAlunoemTurma(Aluno aluno, Turmas turma) {
        aluno.getTurmasMatriculadas().add(turma);
        System.out.println("Aluno " + aluno.getNome() + " matriculado na turma " + turma.getNome());
    }
}
