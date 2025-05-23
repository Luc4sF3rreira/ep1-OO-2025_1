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

    public ModoDisciplina_Turma() {
        carregarTudo();
    }

    public void cadastrarDisciplinas() {
        while (resposta) {
            String nome = JOptionPane.showInputDialog(null, "Digite o nome da disciplina (sem abreviações): ");

            String codigo = JOptionPane.showInputDialog(null, "Digite o código da disciplina (Ex: FGAXXXX): ");
            while (!codigo.matches("FGA\\d{4}")) {
                codigo = JOptionPane.showInputDialog(null, "Código inválido. Digite um código no formato FGAXXXX: ");
            }
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
            salvarDisciplinas(); // Salva imediatamente após adicionar

            StringBuilder dadosDisciplina = new StringBuilder();
            dadosDisciplina.append("Nome: ").append(novaDisciplina.getNome()).append("\n");
            dadosDisciplina.append("Código: ").append(novaDisciplina.getCodigo()).append("\n");
            String respostaInput = JOptionPane.showInputDialog(null, "Deseja cadastrar mais disciplinas? (S/N): ");
            if (respostaInput.equalsIgnoreCase("N")) {
                resposta = false;
            } else if (respostaInput.equalsIgnoreCase("S")) {
                resposta = true;
            } else {
                System.out.println("Opção inválida. Tente novamente."); 
            }
        }
                System.out.println("Opção inválida. Tente novamente."); 
            }
        
    
    public void listarDisciplinas() {
        if (disciplinas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma disciplina cadastrada.");
            return;
        }
        StringBuilder listaDisciplinas = new StringBuilder();
        for (int i = 0; i < disciplinas.size(); i++) {
            Disciplina disciplina = disciplinas.get(i);
            listaDisciplinas.append((i + 1)).append(". ").append(disciplina.getNome()).append(" (").append(disciplina.getCodigo()).append(")\n");
        }
        JOptionPane.showMessageDialog(null, listaDisciplinas.toString());      
    }
    
    public void salvarDisciplinas() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVO_DISCIPLINAS))) {
            for (Disciplina disciplina : disciplinas) {
                writer.write(disciplina.getNome() + ";" + disciplina.getCodigo() + ";" + disciplina.getCargaHoraria() + ";" + String.join(",", disciplina.getPreRequisitos()));
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
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length >= 4) {
                    List<String> preReqs = new ArrayList<>();
                    if (!dados[3].isEmpty()) {
                        for (String pre : dados[3].split(",")) {
                            preReqs.add(pre.trim());
                        }
                    }
                    Disciplina disciplina = new Disciplina(dados[0], dados[1], Integer.parseInt(dados[2]), preReqs);
                    disciplinas.add(disciplina);
                }
            }
        } catch (FileNotFoundException e) {
            // Arquivo não existe ainda, não faz nada
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar disciplinas: " + e.getMessage());
        }
    }
    
    public void cadastrarTurmas() {
        if (disciplinas.isEmpty()) {
            System.out.println("Nenhuma disciplina cadastrada. Cadastre uma disciplina antes de criar uma turma.");
            return;
        }
        String[] nomesDisciplinas = new String[disciplinas.size()];
        for (int i = 0; i < disciplinas.size(); i++) {
            nomesDisciplinas[i] = (i + 1) + ". " + disciplinas.get(i).getNome() + " (" + disciplinas.get(i).getCodigo() + ")";
        }
        String escolha = (String) JOptionPane.showInputDialog(null, "Selecione a disciplina para cadastrar a turma:", "Selecionar Disciplina", JOptionPane.PLAIN_MESSAGE, null, nomesDisciplinas, nomesDisciplinas[0]);
        if (escolha == null) {
            JOptionPane.showMessageDialog(null, "Cadastro de turma cancelado.");
            return;
        }
        int opcaoDisciplina = Integer.parseInt(escolha.substring(0, escolha.indexOf('.')));      
        if (opcaoDisciplina < 1 || opcaoDisciplina > disciplinas.size()) {
            JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.");
            return;
        }        
        Disciplina disciplina = disciplinas.get(opcaoDisciplina - 1);

        String professor = JOptionPane.showInputDialog(null, "Digite o nome do professor da turma:");
        while (professor.isEmpty()) {
            professor = JOptionPane.showInputDialog(null, "Nome do professor inválido. Digite o nome do professor da turma:");
        }

        String semestre = JOptionPane.showInputDialog(null, "Digite o semestre da turma:");

        Integer numeroTurma = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o número da turma: "));
        while (numeroTurma <= 0 || turmaNumeroExiste(disciplina, numeroTurma)) {
            if (numeroTurma <= 0) {
            numeroTurma = Integer.parseInt(JOptionPane.showInputDialog(null, "Número da turma inválido. Digite um número maior que zero: "));
            } else {
            numeroTurma = Integer.parseInt(JOptionPane.showInputDialog(null, "Já existe uma turma com esse número para essa disciplina. Digite outro número: "));
            }
        }        

        String formaAvaliacao = JOptionPane.showInputDialog(null, "Digite a forma de avaliação (A ou B): ");
            while (!formaAvaliacao.equalsIgnoreCase("A") && !formaAvaliacao.equalsIgnoreCase("B")) {
                formaAvaliacao = JOptionPane.showInputDialog(null, "Forma de Avaliação inválida. Digite 'A' ou 'B': ");
            }
            if(formaAvaliacao.equalsIgnoreCase("A")) {
            } else if (formaAvaliacao.equalsIgnoreCase("B")) {
            } else {
                JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.");
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
            JOptionPane.showMessageDialog(null, "Turma online não requer sala.");
        }
  
        String horario = JOptionPane.showInputDialog(null, "Digite o horário da turma (HH:MM): ");
        while (!horario.matches("\\d{2}:\\d{2}")) {
            horario = JOptionPane.showInputDialog(null, "Horário inválido. Digite um horário no formato HH:MM: ");
        }
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
        this.turmas.add(novaTurma);
        salvarTurmas(); 
    }

    
    
    public void salvarTurmas() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVO_TURMAS))) {
            for (Turmas turma : turmas) {
                // Salva os dados em formato CSV, fácil de ler e carregar depois
                writer.write(
                    turma.getDisciplina().getCodigo() + ";" +
                    turma.getProfessor() + ";" +
                    turma.getSemestre() + ";" +
                    turma.getNumeroTurma() + ";" +
                    turma.getTipoAvaliacao() + ";" +
                    turma.getModalidade() + ";" +
                    (turma.getSala() == null ? "" : turma.getSala()) + ";" +
                    turma.getHorario() + ";" +
                    turma.getMaxAlunos() + ";" +
                    turma.getTotalAulas()
                );
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
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length >= 10) {
                    // Busca a disciplina pelo código salvo
                    Disciplina disciplinaTurma = null;
                    for (Disciplina d : disciplinas) {
                        if (d.getCodigo().equals(dados[0])) {
                            disciplinaTurma = d;
                            break;
                        }
                    }
                    if (disciplinaTurma == null) {
                        // Se não encontrar, cria uma disciplina "fantasma"
                        disciplinaTurma = new Disciplina("Desconhecida", dados[0], 0, new ArrayList<>());
                    }
                    Turmas turma = new Turmas(
                        disciplinaTurma,
                        dados[1],
                        dados[2],
                        Integer.parseInt(dados[3]),
                        dados[4],
                        dados[5],
                        dados[6],
                        dados[7],
                        Integer.parseInt(dados[8]),
                        Integer.parseInt(dados[9])
                    );
                    turmas.add(turma);
                }
            }
        } catch (FileNotFoundException e) {
            // Arquivo não existe ainda, não faz nada
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar turmas: " + e.getMessage());
        }
    }
    
    public void salvarTudo() {
        salvarDisciplinas();
        salvarTurmas();
    }

    public void carregarTudo() {
        carregarDisciplinas(); // Carrega as disciplinas primeiro
        carregarTurmas();      // Depois carrega as turmas, que dependem das disciplinas
    }

    //Método de auxílio para verificar se o número da turma já existe
    private boolean turmaNumeroExiste(Disciplina disciplina, Integer numeroTurma) {
        for (Turmas turma : turmas) {
            if (turma.getDisciplina().equals(disciplina) && turma.getNumeroTurma().equals(numeroTurma)) {
                return true;
            }
        }
        return false;
    }

    // Novo método para listar turmas cadastradas
    public void listarTurmas() {
        if (turmas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma turma cadastrada.");
            return;
        }
        StringBuilder listaTurmas = new StringBuilder();
        for (int i = 0; i < turmas.size(); i++) {
            Turmas turma = turmas.get(i);
            listaTurmas.append((i + 1)).append(". ")
                .append(turma.getDisciplina().getNome()).append(" (").append(turma.getDisciplina().getCodigo()).append(") - ")
                .append("Turma: ").append(turma.getNumeroTurma()).append(", Professor: ").append(turma.getProfessor())
                .append(", Semestre: ").append(turma.getSemestre()).append(", Modalidade: ").append(turma.getModalidade())
                .append(", Sala: ").append(turma.getSala()).append(", Horário: ").append(turma.getHorario())
                .append(", Máx Alunos: ").append(turma.getMaxAlunos()).append(", Total Aulas: ").append(turma.getTotalAulas())
                .append("\n");
        }
        JOptionPane.showMessageDialog(null, listaTurmas.toString());
    }
}  

