import java.util.List;
import java.util.Scanner;

public class ModoDisciplina_Turma {

    public void cadastrarDisciplina() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome da disciplina: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o código da disciplina: ");
        String codigo = scanner.nextLine();

        System.out.print("Digite a carga horária da disciplina: ");
        int cargaHoraria = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Digite os pré-requisitos da disciplina (separados por vírgula): ");
        String preRequisitosInput = scanner.nextLine();
        List<String> preRequisitos = List.of(preRequisitosInput.split(","));

        Disciplina novaDisciplina = new Disciplina(nome, codigo, cargaHoraria, preRequisitos);
        System.out.println("---Disciplina cadastrada com sucesso!--- ");
        System.out.println("Nome: " + novaDisciplina.getNome());
        System.out.println("Código: " + novaDisciplina.getCodigo());
        System.out.println("Carga Horária: " + novaDisciplina.getCargaHoraria());
        System.out.println("Pré-requisitos: " + novaDisciplina.getPreRequisitos());

        System.out.print("Deseja cadastrar mais disciplinas? (s/n): ");
        String resposta = scanner.nextLine();
        List<Disciplina> disciplinas = List.of(novaDisciplina);

        // Loop para cadastrar mais disciplinas
        while (resposta.equalsIgnoreCase("s")) {
            System.out.print("Digite o nome da disciplina: ");
            nome = scanner.nextLine();

            System.out.print("Digite o código da disciplina: ");
            codigo = scanner.nextLine();

            System.out.print("Digite a carga horária da disciplina: ");
            cargaHoraria = scanner.nextInt();
            scanner.nextLine(); 

            System.out.print("Digite os pré-requisitos da disciplina (separados por vírgula): ");
            preRequisitosInput = scanner.nextLine();
            preRequisitos = List.of(preRequisitosInput.split(","));

            Disciplina disciplina = new Disciplina(nome, codigo, cargaHoraria, preRequisitos);
            disciplinas.add(disciplina);
            System.out.println("---Disciplina cadastrada com sucesso!--- ");
            System.out.println("Nome: " + disciplina.getNome());
            System.out.println("Código: " + disciplina.getCodigo());
            System.out.println("Carga Horária: " + disciplina.getCargaHoraria());
            System.out.println("Pré-requisitos: " + disciplina.getPreRequisitos());

            System.out.print("Deseja cadastrar mais disciplinas? (s/n): ");
            resposta = scanner.nextLine();
        }

    }

}
