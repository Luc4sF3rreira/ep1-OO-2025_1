import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ModoDisciplina_Turma {
    public void cadastrarDisciplinas() {
        Scanner scanner = new Scanner(System.in);
        List<Disciplina> disciplinas = new ArrayList<>();
        List<String> preRequisitos = new ArrayList<>();
        boolean resposta = true;

        while (resposta) {
            System.out.print("Digite o nome da disciplina: ");
            String nome = scanner.nextLine();

            System.out.print("Digite o código da disciplina: ");
            String codigo = scanner.nextLine();
            boolean codigoExiste;
            do {
                codigoExiste = false;
                for (Disciplina disciplina : disciplinas) {
                    if (disciplina.getCodigo().equals(codigo)) {
                        codigoExiste = true;
                        System.out.println("Código já cadastrado. Digite um código diferente.");
                        System.out.print("Digite outro código: ");
                        codigo = scanner.nextLine();
                        break;
                    }
                }
            } while (codigoExiste);

            System.out.print("Digite a carga horária da disciplina: ");
            int cargaHoraria = scanner.nextInt();
            
            System.out.print("Digite os pré-requisitos da disciplina (separados por vírgula): ");
            String preRequisitosInput = scanner.nextLine();
            preRequisitos.add(preRequisitosInput);

            Disciplina novaDisciplina = new Disciplina(nome, codigo, cargaHoraria, preRequisitos);
            disciplinas.add(novaDisciplina);

            System.out.println("-------------------------------------------------");
            System.out.println("Disciplina cadastrada com sucesso!");
            System.out.println("Nome: " + novaDisciplina.getNome());
            System.out.println("Código: " + novaDisciplina.getCodigo());
            System.out.println("Carga Horária: " + novaDisciplina.getCargaHoraria());
            System.out.println("Pré-requisitos: " + preRequisitosInput);
            System.out.println("-------------------------------------------------");
            System.out.print("Deseja cadastrar mais disciplinas? (S/N): ");
            String respostaInput = scanner.nextLine();
            if (respostaInput.equalsIgnoreCase("N")) {
                resposta = false;
            } else if (respostaInput.equalsIgnoreCase("S")) {
                resposta = true;
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    public void cadastrarTurmas() {
        Scanner scanner = new Scanner(System.in);
        List<Turmas> turmas = new ArrayList<>();
        List<Turmas> salas = new ArrayList<>();                   

        System.out.print("Digite o professor da turma: ");
        String professor = scanner.nextLine();

        System.out.print("Digite o semestre da turma: ");
        String semestre = scanner.nextLine();

        System.out.print("Digite o número da turma: ");
        int numeroTurma = scanner.nextInt();        

        System.out.print("Digite a forma de avaliação: ");
        String formaAvaliacao = scanner.nextLine();

        System.out.print("Digite a modalidade (presencial ou online): ");
        String modalidade = scanner.nextLine();
            while (!modalidade.equalsIgnoreCase("presencial") && !modalidade.equalsIgnoreCase("online")) {
                System.out.println("Modalidade inválida. Digite 'presencial' ou 'online': ");
                modalidade = scanner.nextLine();
        }

        System.out.print("Digite a sala da turma (I1 - I10 ou S1 - S10): ");
            if (modalidade.equalsIgnoreCase("presencial")) {
                System.out.println("Digite o número da sala: ");
                while (true) {
                    String sala = scanner.nextLine();
                    if (sala.matches("I[1-9]|I10|S[1-9]|S10")) {
                        break;
                    } else {
                        System.out.println("Sala inválida. Digite uma sala válida (I1 - I10 ou S1 - S10): ");
                    }
                }
                String sala = scanner.nextLine();
            } else {
                System.out.println("Turma online não requer sala.");
                String sala = "";
            }        
        
        System.out.print("Digite o horário da turma: ");
        String horario = scanner.nextLine();
        boolean horarioUnico;
        String sala = "";
        do {
            horarioUnico = true;
            for (Turmas turma : turmas) {
            // Verifica se o horário já está cadastrado para determinada sala
            if (modalidade.equalsIgnoreCase("presencial") &&
                turma.getSala() != null &&
                turma.getSala().equalsIgnoreCase(sala) &&
                turma.getHorario().equalsIgnoreCase(horario)) {
                horarioUnico = false;
                System.out.println("Horário já cadastrado para essa sala. Digite um horário diferente:");
                horario = scanner.nextLine();
                break;
            }
            }
        } while (!horarioUnico);

        System.out.print("Digite o número máximo de alunos na turma: ");
        int maxAlunos = scanner.nextInt();
        }              
}           
      
