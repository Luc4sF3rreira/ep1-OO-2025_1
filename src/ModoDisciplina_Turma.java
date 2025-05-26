import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.io.*;

public class ModoDisciplina_Turma {
    List<Disciplina> disciplinas = new ArrayList<>();
    List<String> preRequisitos = new ArrayList<>();
    List<Turmas> turmas = new ArrayList<>();
    boolean resposta = true;
    private static final String ARQUIVO_DADOS_DISCIPLINAS = "disciplinas.txt";
    private static final String ARQUIVOS_DADOS_TURMAS = "turmas.txt";

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
            while (cargaHoraria != 15 && cargaHoraria != 30 && cargaHoraria != 60 && cargaHoraria != 90) {
                cargaHoraria = Integer.parseInt(JOptionPane.showInputDialog(null, "Carga horária inválida. Digite um valor válido (15, 30, 60 ou 90): "));
            }

            String preRequisitosInput = JOptionPane.showInputDialog(null, "Digite os pré-requisitos da disciplina (sem abreviação e separados por vírgula): ");
            preRequisitos.add(preRequisitosInput);

            Disciplina novaDisciplina = new Disciplina(nome, codigo, cargaHoraria, preRequisitos);
            disciplinas.add(novaDisciplina);

            StringBuilder dadosDisciplina = new StringBuilder();
            dadosDisciplina.append("Nome: ").append(novaDisciplina.getNome()).append("\n");
            dadosDisciplina.append("Código: ").append(novaDisciplina.getCodigo()).append("\n");
            JOptionPane.showMessageDialog(null, dadosDisciplina.toString(), "Disciplina cadastrada", JOptionPane.INFORMATION_MESSAGE);
            String respostaInput = JOptionPane.showInputDialog(null, "Deseja cadastrar mais disciplinas? (S/N): ");
            if (respostaInput.equalsIgnoreCase("N")) {
                resposta = false;
            } else if (respostaInput.equalsIgnoreCase("S")) {
                resposta = true;
            }
        }
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

    public void editarDisciplina() {
        if (disciplinas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma disciplina cadastrada.");
            return;
        }
        String[] nomesDisciplinas = new String[disciplinas.size()];
        for (int i = 0; i < disciplinas.size(); i++) {
            nomesDisciplinas[i] = (i + 1) + ". " + disciplinas.get(i).getNome() + " (" + disciplinas.get(i).getCodigo() + ")";
        }
        String escolha = (String) JOptionPane.showInputDialog(null, "Selecione a disciplina a ser editada:", "Editar Disciplina", JOptionPane.PLAIN_MESSAGE, null, nomesDisciplinas, nomesDisciplinas[0]);
        if (escolha == null) {
            JOptionPane.showMessageDialog(null, "Edição de disciplina cancelada.");
            return;
        }
        int opcaoDisciplina = Integer.parseInt(escolha.substring(0, escolha.indexOf('.')));
        if (opcaoDisciplina < 1 || opcaoDisciplina > disciplinas.size()) {
            JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.");
            return;
        }
        Disciplina disciplina = disciplinas.get(opcaoDisciplina - 1);
        
        String novoNome = JOptionPane.showInputDialog(null, "Digite o novo nome da disciplina:", disciplina.getNome());
        if (novoNome == null || novoNome.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nome inválido. Edição cancelada.");
            return;
        }
        
        String novoCodigo = JOptionPane.showInputDialog(null, "Digite o novo código da disciplina:", disciplina.getCodigo());
        while (!novoCodigo.matches("FGA\\d{4}")) {
            novoCodigo = JOptionPane.showInputDialog(null, "Código inválido. Digite um código no formato FGAXXXX: ");
        }
        
            int novaCargaHoraria = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite a nova carga horária da disciplina:", disciplina.getCargaHoraria()));
            while (novaCargaHoraria != 15 && novaCargaHoraria != 30 && novaCargaHoraria != 60 && novaCargaHoraria != 90) {
                novaCargaHoraria = Integer.parseInt(JOptionPane.showInputDialog(null, "Carga horária inválida"
                    + " Digite um valor válido (15, 30, 60 ou 90): "));
            }
            disciplina.setNome(novoNome);
            disciplina.setCodigo(novoCodigo);
            disciplina.setCargaHoraria(novaCargaHoraria);
            JOptionPane.showMessageDialog(null, "Disciplina editada com sucesso!");
        }

    public void excluirDisciplina() {
        if (disciplinas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma disciplina cadastrada.");
            return;
        }
        String[] nomesDisciplinas = new String[disciplinas.size()];
        for (int i = 0; i < disciplinas.size(); i++) {
            nomesDisciplinas[i] = (i + 1) + ". " + disciplinas.get(i).getNome() + " (" + disciplinas.get(i).getCodigo() + ")";
        }
        String escolha = (String) JOptionPane.showInputDialog(null, "Selecione a disciplina a ser excluída:", "Excluir Disciplina", JOptionPane.PLAIN_MESSAGE, null, nomesDisciplinas, nomesDisciplinas[0]);
        if (escolha == null) {
            JOptionPane.showMessageDialog(null, "Exclusão de disciplina cancelada.");
            return;
        }
        int opcaoDisciplina = Integer.parseInt(escolha.substring(0, escolha.indexOf('.')));
        if (opcaoDisciplina < 1 || opcaoDisciplina > disciplinas.size()) {
            JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.");
            return;
        }
        disciplinas.remove(opcaoDisciplina - 1);
        JOptionPane.showMessageDialog(null, "Disciplina excluída com sucesso!");
    }
    
    public void salvarDadosDisciplinas() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVO_DADOS_DISCIPLINAS, false))) {
            for (Disciplina disciplina : disciplinas) {
                writer.println("Nome: " + disciplina.getNome()
                    + "; Código: " + disciplina.getCodigo()
                    + "; Carga horária: " + disciplina.getCargaHoraria()
                    + "; Pré-requisitos: " + String.join(",", disciplina.getPreRequisitos()));
            }
            JOptionPane.showMessageDialog(null, "Disciplinas salvas com sucesso em " + ARQUIVO_DADOS_DISCIPLINAS);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar disciplinas: " + e.getMessage());
        }
    }

    public void carregarDadosDisciplinas() {
        disciplinas.clear();
        File arquivo = new File(ARQUIVO_DADOS_DISCIPLINAS);
        if (!arquivo.exists() || arquivo.length() == 0) {
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_DADOS_DISCIPLINAS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;
                String[] partes = linha.split(";", -1);
                if (partes.length < 4) continue;
                String nome = partes[0].replace("Nome:", "").trim();
                String codigo = partes[1].replace("Código:", "").trim();
                int cargaHoraria = Integer.parseInt(partes[2].replace("Carga horária:", "").trim());
                List<String> preRequisitos = new ArrayList<>();
                String preRequisitosStr = partes[3].replace("Pré-requisitos:", "").trim();
                String[] preRequisitosArray = preRequisitosStr.split(",");
                for (String preReq : preRequisitosArray) {
                    preRequisitos.add(preReq.trim());
                }
                disciplinas.add(new Disciplina(nome, codigo, cargaHoraria, preRequisitos));
            }
        } catch(FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Arquivo não encontrado: " + ARQUIVO_DADOS_DISCIPLINAS);          
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar os dados das disciplinas: " + e.getMessage());
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

        String professor = JOptionPane.showInputDialog(null, "Digite o nome do(a) professor(a) da turma:");
        while (professor.isEmpty()) {
            professor = JOptionPane.showInputDialog(null, "Nome do professor inválido. Digite o nome do(a) professor(a) da turma:");
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
        String sala;
        if (modalidade.equalsIgnoreCase("presencial")) {
            sala = JOptionPane.showInputDialog(null, "Digite a sala da turma (I1 - I10 ou S1 - S10): ");
            while (!sala.matches("I[1-9]|I10|S[1-9]|S10")) {
                sala = JOptionPane.showInputDialog(null, "Sala inválida. Digite uma sala válida (I1 - I10 ou S1 - S10): ");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Turma online não requer sala.");
            sala = "N/A";
        }  
        String horario = JOptionPane.showInputDialog(null, "Digite o horário da turma (HH:MM): ");
        while (!horario.matches("\\d{2}:\\d{2}")) {
            horario = JOptionPane.showInputDialog(null, "Horário inválido. Digite um horário no formato HH:MM: ");
        }
        boolean horarioUnico;
        do {
            horarioUnico = true;
            for (Turmas turma : turmas) {
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
        JOptionPane.showMessageDialog(null, "Turma cadastrada com sucesso!");
        Turmas novaTurma = new Turmas(disciplina, professor, semestre, numeroTurma, formaAvaliacao, modalidade, sala, horario, maxAlunos, totalAulas);
        turmas.add(novaTurma);
    }
    private boolean turmaNumeroExiste(Disciplina disciplina, Integer numeroTurma) {
        for (Turmas turma : turmas) {
            if (turma.getDisciplina().equals(disciplina) && turma.getNumeroTurma().equals(numeroTurma)) {
                return true;
            }
        }
        return false;
    }
    
    public void listarTurmas() {
        if (turmas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma turma cadastrada.");
            return;
        }
        String[] turmasCadastradas = new String[turmas.size()];
        for (int i = 0; i < turmas.size(); i++) {
            Turmas turma = turmas.get(i);
            turmasCadastradas[i] = (i + 1) + ". " + turma.getDisciplina().getNome() + " - " + turma.getProfessor();
        }
        String escolha = (String) JOptionPane.showInputDialog(
            null,
            "Selecione uma turma para ver detalhes:",
            "Turmas Cadastradas",
            JOptionPane.PLAIN_MESSAGE,
            null,
            turmasCadastradas,
            turmasCadastradas[0]);
        if (escolha != null) {
            int indice = Integer.parseInt(escolha.substring(0, escolha.indexOf('.'))) - 1;
            Turmas turma = turmas.get(indice);
            StringBuilder detalhes = new StringBuilder();
            detalhes.append("Nome da Disciplina: ").append(turma.getDisciplina().getNome()).append("\n");
            detalhes.append("Código: ").append(turma.getDisciplina().getCodigo()).append("\n");
            detalhes.append("Professor(a): ").append(turma.getProfessor()).append("\n");
            detalhes.append("Número da Turma: ").append(turma.getNumeroTurma()).append("\n");
            detalhes.append("Modalidade: ").append(turma.getModalidade()).append("\n");
            detalhes.append("Sala: ").append(turma.getSala()).append("\n");
            detalhes.append("Horário: ").append(turma.getHorario()).append("\n");
            detalhes.append("Capacidade total: ").append(turma.getMaxAlunos()).append("\n");
            detalhes.append("Total de aulas: ").append(turma.getTotalAulas()).append("\n");
            JOptionPane.showMessageDialog(null, detalhes.toString(), "Detalhes da Turma", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void editarTurma() {
        if (turmas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma turma cadastrada.");
            return;
        }
        String[] turmasCadastradas = new String[turmas.size()];
        for (int i = 0; i < turmas.size(); i++) {
            Turmas turma = turmas.get(i);
            turmasCadastradas[i] = (i + 1) + ". " + turma.getDisciplina().getNome() + " - " + turma.getProfessor();
        }
        String escolha = (String) JOptionPane.showInputDialog(
            null,
            "Selecione a turma a ser editada:",
            "Editar Turma",
            JOptionPane.PLAIN_MESSAGE,
            null,
            turmasCadastradas,
            turmasCadastradas[0]);
        if (escolha == null) {
            JOptionPane.showMessageDialog(null, "Edição de turma cancelada.");
            return;
        }
        int indiceTurma = Integer.parseInt(escolha.substring(0, escolha.indexOf('.'))) - 1;
        Turmas turma = turmas.get(indiceTurma);

        String novoProfessor = JOptionPane.showInputDialog(null, "Digite o novo nome do(a) professor(a):", turma.getProfessor());
        if (novoProfessor == null || novoProfessor.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nome inválido. Edição cancelada.");
            return;
        }

        String novoSemestre = JOptionPane.showInputDialog(null, "Digite o novo semestre:", turma.getSemestre());
        
        Integer novoNumeroTurma = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o novo número da turma:", turma.getNumeroTurma()));
        while (novoNumeroTurma <= 0 || turmaNumeroExiste(turma.getDisciplina(), novoNumeroTurma)) {
            if (novoNumeroTurma <= 0) {
                novoNumeroTurma = Integer.parseInt(JOptionPane.showInputDialog(null, "Número da turma inválido. Digite um número maior que zero: "));
            } else {
                novoNumeroTurma = Integer.parseInt(JOptionPane.showInputDialog(null, "Já existe uma turma com esse número para essa disciplina. Digite outro número: "));
            }
        }

        String novaFormaAvaliacao = JOptionPane.showInputDialog(null, "Digite a nova forma de avaliação (A ou B):", turma.getTipoAvaliacao());
        while (!novaFormaAvaliacao.equalsIgnoreCase("A") && !novaFormaAvaliacao.equalsIgnoreCase("B")) {
            novaFormaAvaliacao = JOptionPane.showInputDialog(null, "Forma de Avaliação inválida. Digite 'A' ou 'B': ");
        }
    }

    public void excluirTurma() {
        if (turmas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma turma cadastrada.");
            return;
        }
        String[] turmasCadastradas = new String[turmas.size()];
        for (int i = 0; i < turmas.size(); i++) {
            Turmas turma = turmas.get(i);
            turmasCadastradas[i] = (i + 1) + ". " + turma.getDisciplina().getNome() + " - " + turma.getProfessor();
        }
        String escolha = (String) JOptionPane.showInputDialog(
            null,
            "Selecione a turma a ser excluída:",
            "Excluir Turma",
            JOptionPane.PLAIN_MESSAGE,
            null,
            turmasCadastradas,
            turmasCadastradas[0]);
        if (escolha == null) {
            JOptionPane.showMessageDialog(null, "Exclusão de turma cancelada.");
            return;
        }
        int indiceTurma = Integer.parseInt(escolha.substring(0, escolha.indexOf('.'))) - 1;
        turmas.remove(indiceTurma);
        JOptionPane.showMessageDialog(null, "Turma excluída com sucesso!");
    }
    
    public void salvarDadosTurmas() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVOS_DADOS_TURMAS, false))) {
            for (Turmas turma : turmas) {
                writer.println("Nome: " + turma.getDisciplina().getNome()
                + "; Código: " + turma.getDisciplina().getCodigo()
                + "; Professor responsável: " + turma.getProfessor()
                + "; Semestre: " + turma.getSemestre()
                + "; Número da turma: " + turma.getNumeroTurma()
                + "; Tipo de avaliação: " + turma.getTipoAvaliacao()
                + "; Modalidade: " + turma.getModalidade()
                + "; Sala: " + (turma.getModalidade().equalsIgnoreCase("presencial") ? turma.getSala() : "")
                + "; Horário: " + turma.getHorario()
                + "; Máximo de alunos: " + turma.getMaxAlunos()
                + "; Total de aulas: " + turma.getTotalAulas());
            }
            JOptionPane.showMessageDialog(null, "Turmas salvas com sucesso em " + ARQUIVOS_DADOS_TURMAS);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar turmas: " + e.getMessage());
        }
    }   

    public void carregarDadosTurmas() {
        turmas.clear();
        File arquivo = new File(ARQUIVOS_DADOS_TURMAS);
        if (!arquivo.exists() || arquivo.length() == 0) {
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVOS_DADOS_TURMAS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;
                String[] partes = linha.split(";", -1);
                if (partes.length < 11) continue; 
                String nomeDisciplina = partes[0].split(": ", 2)[1].trim();
                String codigoDisciplina = partes[1].split(": ", 2)[1].trim();
                String professor = partes[2].split(": ", 2)[1].trim();
                String semestre = partes[3].split(": ", 2)[1].trim();
                Integer numeroTurma = Integer.parseInt(partes[4].split(": ", 2)[1].trim());
                String tipoAvaliacao = partes[5].split(": ", 2)[1].trim();
                String modalidade = partes[6].split(": ", 2)[1].trim();
                String sala = partes[7].split(": ", 2)[1].trim();
                String horario = partes[8].split(": ", 2)[1].trim();
                Integer maxAlunos = Integer.parseInt(partes[9].split(": ", 2)[1].trim());
                Integer totalAulas = Integer.parseInt(partes[10].split(": ", 2)[1].trim());

                Disciplina disciplina = new Disciplina(nomeDisciplina, codigoDisciplina, 0, new ArrayList<>());
                Turmas turma = new Turmas(disciplina, professor, semestre, numeroTurma, tipoAvaliacao, modalidade, sala, horario, maxAlunos, totalAulas);
                turmas.add(turma);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar os dados das turmas: " + e.getMessage());
        }
    }

    public List<Turmas> getTurmas() {return turmas;}
            
    public void salvarTudo() {
        salvarDadosDisciplinas();
        salvarDadosTurmas();
    }

    public void carregarTudo() {
        carregarDadosDisciplinas(); 
        carregarDadosTurmas();
    }     


}
  

