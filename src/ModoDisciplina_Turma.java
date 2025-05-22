import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ModoDisciplina_Turma {
    List<Disciplina> disciplinas = new ArrayList<>();
    List<String> preRequisitos = new ArrayList<>();
    boolean resposta = true;

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
    
    public void salvarDisciplinas() {
        try (java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(new java.io.FileOutputStream("disciplinas_turmas.txt"))) {
            oos.writeObject(disciplinas);
            JOptionPane.showMessageDialog(null, "Disciplinas salvas com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar disciplinas: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void carregarDisciplinas() {
        try (java.io.ObjectInputStream ois = new java.io.ObjectInputStream(new java.io.FileInputStream("disciplinas_turmas.txt"))) {
            disciplinas = (List<Disciplina>) ois.readObject();
            JOptionPane.showMessageDialog(null, "Disciplinas carregadas com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar disciplinas: " + e.getMessage());
        }
    }

}           
      
