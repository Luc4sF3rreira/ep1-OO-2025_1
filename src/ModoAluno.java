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
        System.out.println("---Aluno cadastrado com sucesso!--- ");
        System.out.println("Nome: " + novoAluno.getNome());
        System.out.println("Matrícula: " + novoAluno.getMatricula());
        System.out.println("Curso: " + novoAluno.getCurso());

        System.out.print("Deseja cadastrar mais alunos? (s/n): ");
        String resposta = scanner.nextLine();
        List<Aluno> alunos = new ArrayList<>();
        while (resposta.equalsIgnoreCase("s")) {
            System.out.print("Digite o nome do aluno: ");
            nome = scanner.nextLine();

            System.out.print("Digite a matrícula do aluno: ");
            matricula = scanner.nextLine();

            System.out.print("Digite o curso do aluno: ");
            curso = scanner.nextLine();

            Aluno aluno = new Aluno(nome, matricula, curso);
            alunos.add(aluno);
            System.out.println("---Aluno cadastrado com sucesso!--- ");
            System.out.println("Nome: " + aluno.getNome());
            System.out.println("Matrícula: " + aluno.getMatricula());
            System.out.println("Curso: " + aluno.getCurso());

            System.out.print("Deseja cadastrar mais alunos? (s/n): ");
            resposta = scanner.nextLine();
            
        }
    System.out.println("Cadastro finalizado.");
}

    public void listarAlunos(List<Aluno> alunos) {
        System.out.println("Lista de Alunos:");
        for (Aluno aluno : alunos) {
            System.out.println("Nome: " + aluno.getNome());
            System.out.println("Matrícula: " + aluno.getMatricula());
            System.out.println("Curso: " + aluno.getCurso());
            System.out.println("-------------------------");
        }
    }
}
    
