import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ModoDisciplina_Turma {
    Scanner scanner = new Scanner(System.in);
    List<Disciplina> disciplinas = new ArrayList<>();
    List<String> preRequisitos = new ArrayList<>();
    boolean resposta = true;

    public void cadastrarDisciplinas() {
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
            scanner.nextLine(); 
            
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

    public List<Disciplina> getDisciplinas() {return disciplinas;}

    public void listarDisciplinas(List<Disciplina> disciplinas) {
        System.out.println("-------------------------------------------------");
        System.out.println("Disciplinas cadastradas:");
        for (Disciplina disciplina : disciplinas) {
            System.out.println("Nome: " + disciplina.getNome());
            System.out.println("Código: " + disciplina.getCodigo());
            System.out.println("Carga Horária: " + disciplina.getCargaHoraria());
            System.out.println("Pré-requisitos: " + disciplina.getPreRequisitos());
            System.out.println("-------------------------------------------------");
        }
    }

    public void cadastrarTurmas() {
        if (disciplinas.isEmpty()) {
            System.out.println("Nenhuma disciplina cadastrada. Cadastre uma disciplina antes de criar uma turma.");
            return;
        }

        System.out.println("Selecione a disciplina para a qual deseja cadastrar uma turma:");
        for (int i = 0; i < disciplinas.size(); i++) {
            Disciplina disciplina = disciplinas.get(i);
            System.out.println((i + 1) + ". " + disciplina.getNome() + " (" + disciplina.getCodigo() + ")");
        }

        int opcaoDisciplina = -1;
        while (opcaoDisciplina < 1 || opcaoDisciplina > disciplinas.size()) {
            System.out.print("Digite o número da disciplina: ");
            if (scanner.hasNextInt()) {
                opcaoDisciplina = scanner.nextInt();
                scanner.nextLine();
                if (opcaoDisciplina < 1 || opcaoDisciplina > disciplinas.size()) {
                    System.out.println("Opção inválida. Tente novamente.");
                }
            } else {
                System.out.println("Entrada inválida. Digite um número.");
                scanner.nextLine(); 
            }

        List<Turmas> turmas = new ArrayList<>();
        List<Turmas> salas = new ArrayList<>();                   

        System.out.print("Digite o professor da turma: ");
        String professor = scanner.nextLine();

        System.out.print("Digite o semestre da turma: ");
        String semestre = scanner.nextLine();

        System.out.print("Digite o número da turma: ");
        int numeroTurma = scanner.nextInt();        

        System.out.print("Digite a forma de avaliação (A ou B): ");
        String formaAvaliacao = scanner.nextLine();
            if(formaAvaliacao.equalsIgnoreCase("A")) {
                System.out.println("Forma de Avaliação A selecionada.");
            } else if (formaAvaliacao.equalsIgnoreCase("B")) {
                System.out.println("Forma de Avaliação B selecionada.");
            } else {
                System.out.println("Forma de Avaliação inválida. Digite 'A' ou 'B': ");
                formaAvaliacao = scanner.nextLine();
            }

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
            // Verifica se o horário já está cadastrado em determinada sala
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
        
        System.out.print("Digite o número total de aulas que serão dadas: ");

    }

    public void salvarDados(String caminhoArquivo) {
        try (java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(new java.io.FileOutputStream(caminhoArquivo))) {
            oos.writeObject(disciplinas);
            System.out.println("Dados salvos com sucesso em " + caminhoArquivo);
        } catch (Exception e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void carregarDados(String caminhoArquivo) {
        try (java.io.ObjectInputStream ois = new java.io.ObjectInputStream(new java.io.FileInputStream(caminhoArquivo))) {
            disciplinas = (List<Disciplina>) ois.readObject();
            System.out.println("Dados carregados com sucesso de " + caminhoArquivo);
        } catch (Exception e) {
            System.out.println("Erro ao carregar dados: " + e.getMessage());
        }
    }
}           
      
