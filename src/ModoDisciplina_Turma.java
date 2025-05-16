import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ModoDisciplina_Turma {

    public void cadastrarDisciplina() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Disciplina: ");
        String nome = scanner.nextLine();

        System.out.print("Código da disciplina: ");
        String codigo = scanner.nextLine();

        System.out.print("Carga horária: ");
        int cargaHoraria = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Pré-requisitos da disciplina (separados por vírgula): ");
        String preRequisitosInput = scanner.nextLine();
        List<String> preRequisitos = List.of(preRequisitosInput.split(","));

        Disciplina novaDisciplina = new Disciplina(nome, codigo, cargaHoraria, preRequisitos);
        System.out.println("---Disciplina cadastrada com sucesso!--- ");
        System.out.println("Nome: " + novaDisciplina.getNome());
        System.out.println("Código: " + novaDisciplina.getCodigo());
        System.out.println("Carga Horária: " + novaDisciplina.getCargaHoraria());
        System.out.println("Pré-requisitos: " + novaDisciplina.getPreRequisitos());

        System.out.print("Deseja cadastrar mais disciplinas? (S/N): ");
        String resposta = scanner.nextLine();
        List<Disciplina> disciplinas = new ArrayList<>();
        disciplinas.add(novaDisciplina);

        // Loop para cadastrar mais disciplinas
        while (resposta.equalsIgnoreCase("S")) {
            System.out.print("Disciplina: ");
            nome = scanner.nextLine();

            System.out.print("Código da disciplina: ");
            codigo = scanner.nextLine();

            System.out.print("Carga horária: ");
            cargaHoraria = scanner.nextInt();
            scanner.nextLine(); 

            System.out.print("Pré-requisitos da disciplina (separados por vírgula): ");
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
            disciplinas.add(disciplina);
       }
    }

    public void cadastrarTurma(List<Disciplina> disciplinas) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Disciplinas disponíveis para cadastro de turmas:");
        for (int i = 0; i < disciplinas.size(); i++) {
            System.out.println((i + 1) + ". " + disciplinas.get(i).getNome() + " (" + disciplinas.get(i).getCodigo() + ")");
        }

        System.out.print("Escolha o número da disciplina para a qual deseja cadastrar uma turma: ");
        int escolhaDisciplina = scanner.nextInt();
        scanner.nextLine(); 

        if (escolhaDisciplina < 1 || escolhaDisciplina > disciplinas.size()) {
            System.out.println("Escolha inválida.");
            return;
        }

        Disciplina disciplinaSelecionada = disciplinas.get(escolhaDisciplina - 1);

        System.out.print("Digite o nome do professor responsável: ");
        String professor = scanner.nextLine();

        System.out.print("Digite o semestre: ");
        String semestre = scanner.nextLine();

        System.out.print("Digite o número da turma: ");
        int numeroTurma = scanner.nextInt();

        System.out.print("Digite a forma de avaliação 1 ou 2:");
        int avaliacao = scanner.nextInt();
        FormadeAvaliação formaAvaliacao;
        if (avaliacao == 1) {
                formaAvaliacao = FormadeAvaliação.A;
        } else if (avaliacao == 2) {
            formaAvaliacao = FormadeAvaliação.B;
        } else {
            System.out.println("Forma de avaliação inválida.");
            return;
        }

        System.out.print("A turma será presencial ou remota? (presencial/remota): ");
        String modalidade = scanner.nextLine();

        String sala = null;
        if (modalidade.equalsIgnoreCase("presencial")) {
            System.out.print("Digite a sala: ");
            sala = scanner.nextLine();
        }

        System.out.print("Digite o horário da turma: ");
        String horario = scanner.nextLine();

        List<Turmas> horarios = new ArrayList<>();
        boolean horarioOcupado;

        do {
            horarioOcupado = false;
            for (Turmas turmaHorario : horarios) {
                if (turmaHorario.getHorario().equals(horario)) {
                    horarioOcupado = true;
                    System.out.println("Horário já cadastrado. Digite um horário diferente.");
                    System.out.print("Digite o horário da turma: ");
                    horario = scanner.nextLine();
                    break;
                }
            }
        } while (horarioOcupado);

        System.out.print("Digite a capacidade máxima de alunos: ");
        int capacidadeMaxima = scanner.nextInt();
        scanner.nextLine(); 

        Turmas novaTurma = new Turmas(disciplinaSelecionada, professor, semestre, numeroTurma, formaAvaliacao, modalidade, sala, horario, capacidadeMaxima);
        System.out.println("---Turma cadastrada com sucesso!--- ");
        System.out.println("Disciplina: " + novaTurma.getDisciplina().getNome());
        System.out.println("Professor: " + novaTurma.getProfessor());
        System.out.println("Semestre: " + novaTurma.getSemestre());
        System.out.println("Número da Turma: " + novaTurma.getNumeroTurma());
        System.out.println("Forma de Avaliação: " + novaTurma.getFormaAvaliacao());
        System.out.println("Modalidade: " + novaTurma.getModalidade());
        if (novaTurma.getModalidade().equalsIgnoreCase("presencial")) {
            System.out.println("Sala: " + novaTurma.getSala());
        }
        System.out.println("Horário: " + novaTurma.getHorario());
        System.out.println("Capacidade Máxima: " + novaTurma.getCapacidadeMaxima());
        }        
    }
