import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.io.*;

public class ModoDisciplina_Turma {
    List<Disciplina> disciplinas = new ArrayList<>();
    List<String> preRequisitos = new ArrayList<>();
    List<Turmas> turmas = new ArrayList<>();
    boolean resposta = true;
    private static final String ARQUIVO_TURMAS = "turmas.txt";
    private static final String ARQUIVO_DISCIPLINAS = "disciplinas.txt";

    public void cadastrarDisciplinas() {
        while (resposta) {
            String nome = JOptionPane.showInputDialog(null, "Digite o nome da disciplina (sem abreviações): ");

            String codigo = JOptionPane.showInputDialog(null, "Digite o código da disciplina: ");
            boolean codigoExiste;
            do {
                codigoExiste = false;
                for (Disciplina disciplina : disciplinas) {
                    if (disciplina.getCodigo().equals(codigo)) {
                        codigoExiste = true;
                        codigo = JOptionPane.showInputDialog(null, "Código já cadastrado. Digite um código diferente: ");
                        break;
                    }
                }
            } while (codigoExiste);

            int cargaHoraria = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite a carga horária da disciplina: "));
            if (cargaHoraria <= 0) {
                JOptionPane.showMessageDialog(null, "Carga horária inválida. Digite um valor maior que zero.");
                continue;
            }

            String preRequisitosInput = JOptionPane.showInputDialog(null, "Digite os pré-requisitos da disciplina (sem abreviação e separados por vírgula): ");
            preRequisitos.add(preRequisitosInput);

            Disciplina novaDisciplina = new Disciplina(nome, codigo, cargaHoraria, preRequisitos);
            disciplinas.add(novaDisciplina);

            StringBuilder dadosDisciplina = new StringBuilder();
            dadosDisciplina.append("Nome: ").append(novaDisciplina.getNome()).append("\n");
            dadosDisciplina.append("Código: ").append(novaDisciplina.getCodigo()).append("\n");
            dadosDisciplina.append("Carga Horária: ").append(novaDisciplina.getCargaHoraria()).append("\n");
            dadosDisciplina.append("Pré-requisitos: ").append(preRequisitosInput).append("\n");
            JOptionPane.showMessageDialog(null, dadosDisciplina.toString());

            String respostaInput = JOptionPane.showInputDialog(null, "Deseja cadastrar mais disciplinas? (S/N): ");
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
        if (disciplinas == null || disciplinas.isEmpty()) {
            System.out.println("Nenhuma disciplina cadastrada.");
            return;
        }
        StringBuilder listaDisciplinas = new StringBuilder();
        listaDisciplinas.append("Disciplinas cadastradas:\n");
        for (Disciplina disciplina : disciplinas) {
            listaDisciplinas.append("   Nome: ").append(disciplina.getNome()).append("\n");
            listaDisciplinas.append("   Código: ").append(disciplina.getCodigo()).append("\n");
            listaDisciplinas.append("   Carga Horária: ").append(disciplina.getCargaHoraria()).append("\n");
            listaDisciplinas.append("   Pré-requisitos: ").append(disciplina.getPreRequisitos()).append("\n");
        }
        JOptionPane.showMessageDialog(null, listaDisciplinas.toString());        
    }
    
    public void salvarDisciplinas() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVO_DISCIPLINAS))) {
            for (Disciplina disciplina : disciplinas) {
                writer.write(disciplina.getNome() + "," + disciplina.getCodigo() + "," + disciplina.getCargaHoraria() + "," + String.join(";", disciplina.getPreRequisitos()));
                writer.println();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar disciplinas: " + e.getMessage());
        }
    }

    public void carregarDisciplinas() {
        disciplinas.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_DISCIPLINAS))) {
            String linha;
            while((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if(dados.length >= 4) {
                    Disciplina disciplina = new Disciplina(dados[0], dados[1], Integer.parseInt(dados[2]), List.of(dados[3].split(",")));

                    if(dados.length > 4 && !dados[4].isEmpty()) {
                        for (String preRequisito : dados[4].split(",")) {
                            disciplina.adicionarPreRequisito(preRequisito);
                        }
                    }
                    disciplinas.add(disciplina);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Arquivo de disciplinas não encontrado. Será criado um novo arquivo ao salvar.");
        }
    }

    public void cadastrarTurmas() {
        if (disciplinas.isEmpty()) {
            System.out.println("Nenhuma disciplina cadastrada. Cadastre uma disciplina antes de criar uma turma.");
            return;
        }
        JOptionPane.showMessageDialog(null,"Selecione a disciplina para a qual deseja cadastrar uma turma:");
        for (int i = 0; i < disciplinas.size(); i++) {
            Disciplina disciplina = disciplinas.get(i);
            JOptionPane.showMessageDialog(null, (i + 1) + ". " + disciplina.getNome() + " (" + disciplina.getCodigo() + ")");
        }
        Integer opcaoDisciplina = -1;
        while (opcaoDisciplina < 1 || opcaoDisciplina > disciplinas.size()) {
            try {
                opcaoDisciplina = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o número da disciplina:"));
                if (opcaoDisciplina < 1 || opcaoDisciplina > disciplinas.size()) {
                    JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada inválida. Digite um número.");
            }
        }
        Disciplina disciplina = disciplinas.get(opcaoDisciplina - 1);
        List<Turmas> turmas = new ArrayList<>();
        List<Turmas> salas = new ArrayList<>();


        String professor = JOptionPane.showInputDialog(null, "Digite o nome do professor da turma:");

        String semestre = JOptionPane.showInputDialog(null, "Digite o semestre da turma:");

        Integer numeroTurma = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o número da turma: "));

        String formaAvaliacao = JOptionPane.showInputDialog(null, "Digite a forma de avaliação (A ou B): ");
            while (!formaAvaliacao.equalsIgnoreCase("A") && !formaAvaliacao.equalsIgnoreCase("B")) {
                formaAvaliacao = JOptionPane.showInputDialog(null, "Forma de Avaliação inválida. Digite 'A' ou 'B': ");
            }
            if(formaAvaliacao.equalsIgnoreCase("A")) {
                System.out.println("Forma de Avaliação A selecionada.");
            } else if (formaAvaliacao.equalsIgnoreCase("B")) {
                System.out.println("Forma de Avaliação B selecionada.");
            }

        String modalidade = JOptionPane.showInputDialog(null, "Digite a modalidade (presencial ou online): ");
            while (!modalidade.equalsIgnoreCase("presencial") && !modalidade.equalsIgnoreCase("online")) {
                modalidade = JOptionPane.showInputDialog(null, "Modalidade inválida. Digite 'presencial' ou 'online': ");
        }

        if (modalidade.equalsIgnoreCase("presencial")) {
            String sala = JOptionPane.showInputDialog(null, "Digite a sala da turma (I1 - I10 ou S1 - S10): ");
            while (!sala.matches("I[1-9]|I10|S[1-9]|S10")) {
                sala = JOptionPane.showInputDialog(null, "Sala inválida. Digite uma sala válida (I1 - I10 ou S1 - S10): ");
            }
        } else {
            System.out.println("Turma online não requer sala.");
        }
  
        String horario = JOptionPane.showInputDialog(null, "Digite o horário da turma (HH:MM): ");
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
                horario = JOptionPane.showInputDialog(null, "Horário já cadastrado para essa sala. Digite um horário diferente:");
                break;
            }
            }
        } while (!horarioUnico);

        Integer maxAlunos = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o número máximo de alunos na turma: "));
        while (maxAlunos <= 0) {
            maxAlunos = Integer.parseInt(JOptionPane.showInputDialog(null, "Número máximo de alunos inválido. Digite um valor maior que zero: "));
        }

        Integer totalAulas = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o número total de aulas que serão dadas: "));
        while (totalAulas <= 0) {
            totalAulas = Integer.parseInt(JOptionPane.showInputDialog(null, "Número total de aulas inválido. Digite um valor maior que zero: "));
        }
        Turmas novaTurma = new Turmas(disciplina, professor, semestre, numeroTurma, formaAvaliacao, modalidade, sala, horario, maxAlunos, totalAulas);
        turmas.add(novaTurma);
    }
    
    public void salvarTurmas() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVO_TURMAS))) {
            for (Turmas turma : turmas) {
                writer.write(turma.getNome() + "," + turma.getProfessor() + "," + turma.getSemestre() + "," + turma.getNumeroTurma() + "," + turma.getTipoAvaliacao() + "," + turma.getModalidade() + "," + turma.getSala() + "," + turma.getHorario() + "," + turma.getMaxAlunos() + "," + turma.getTotalAulas());
                writer.println();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar turmas: " + e.getMessage());
        }
    }

    public void carregarTurmas() {
        turmas.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_TURMAS))) {
            String linha;
            while((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                if(dados.length >= 10) {
                    // Buscar a disciplina correspondente pelo nome (dados[0])
                    Disciplina disciplinaTurma = null;
                    for (Disciplina d : disciplinas) {
                        if (d.getNome().equals(dados[0])) {
                            disciplinaTurma = d;
                            break;
                        }
                    }
                    if (disciplinaTurma == null) {
                        disciplinaTurma = new Disciplina(dados[0], "SEM_CODIGO", 0, new ArrayList<>());
                    }
                    Turmas turma = new Turmas(disciplinaTurma, dados[1], dados[2], Integer.parseInt(dados[3]), dados[4], dados[5], dados[6], dados[7], Integer.parseInt(dados[8]), Integer.parseInt(dados[9]));
                    turmas.add(turma);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Arquivo de turmas não encontrado. Será criado um novo arquivo ao salvar.");
        } 
    }
    
    public void salvarTudo() {
        salvarDisciplinas();
        salvarTurmas();
    }

    public void carregarTudo() {
        carregarDisciplinas();
        carregarTurmas();
    }
}  
