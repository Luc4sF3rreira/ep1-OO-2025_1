import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Aluno {
    private String nome;
    private String matricula;
    private String curso;
    private String tipoAluno;
    private List<Turmas> turmasMatriculadas;
    private List<String> disciplinasFeitas;
    private Avaliacao avaliacao;

    public Aluno(String nome, String matricula, String curso, String tipoAluno, List<String> disciplinasFeitas) {
        this.nome = nome;
        this.matricula = matricula;
        this.curso = curso;
        this.tipoAluno = tipoAluno;
        this.turmasMatriculadas = new ArrayList<>();
        this.disciplinasFeitas = new ArrayList<>(disciplinasFeitas);
    }
    public String getNome() {return nome; }
    public String getMatricula() {return matricula; } 
    public String getCurso() {return curso; }
    public String getTipoAluno() {return tipoAluno; }
    public List<Turmas> getTurmasMatriculadas() {return turmasMatriculadas; }
    public List<String> getDisciplinasFeitas() {return new ArrayList<>(disciplinasFeitas); }
    public Avaliacao getAvaliacao () {return avaliacao;}
    public void setNome(String nome) {this.nome = nome; }
    public void setMatricula(String matricula) {this.matricula = matricula; }
    public void setCurso(String curso) {this.curso = curso; }
    public void setTipoAluno(String tipoAluno) {this.tipoAluno = tipoAluno; }
    public void setTurmasMatriculadas(List<Turmas> turmasMatriculadas) {this.turmasMatriculadas = turmasMatriculadas; }
    public void setDisciplinasFeitas(List<String> disciplinasFeitas) {this.disciplinasFeitas = disciplinasFeitas; }
    public void setAvaliacao(Avaliacao avaliacao) {this.avaliacao = avaliacao;}   

    public void matricularTurma(Turmas turmaObj) {
        String nomeAluno = JOptionPane.showInputDialog(null, "Digite o nome do aluno que deseja matricular:");
        if (!this.nome.equalsIgnoreCase(nomeAluno)) {
            JOptionPane.showMessageDialog(null, "Aluno não encontrado.");
            return;
        }
        List<Turmas> turmasDisponiveis = new ArrayList<>();
        for (Turmas t : Turmas.getTodasTurmas()) { // Supondo que exista um método estático para obter todas as turmas
            if (!turmasMatriculadas.contains(t) && t.getVagasDisponiveis() > 0) {
            turmasDisponiveis.add(t);
            }
        }
        if (turmasDisponiveis.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há turmas disponíveis para matrícula.");
            return;
        }
        String[] nomesTurmas = new String[turmasDisponiveis.size()];
        for (int i = 0; i < turmasDisponiveis.size(); i++) {
            Turmas t = turmasDisponiveis.get(i);
            nomesTurmas[i] = t.getNome() + " (" + t.getSemestre() + ")";
        }
        String escolha = (String) JOptionPane.showInputDialog(
            null,
            "Selecione a turma para matrícula:",
            "Turmas Disponíveis",
            JOptionPane.QUESTION_MESSAGE,
            null,
            nomesTurmas,
            nomesTurmas[0]);
        if (escolha == null) {
            JOptionPane.showMessageDialog(null, "Matrícula cancelada.");
            return;
        }
        Turmas turmaEscolhida = null;
        for (Turmas t : turmasDisponiveis) {
            if ((t.getNome() + " (" + t.getSemestre() + ")").equals(escolha)) {
            turmaEscolhida = t;
            break;
            }
        }
        if (turmaEscolhida == null) {
            JOptionPane.showMessageDialog(null, "Turma não encontrada.");
            return;
        }
        boolean possuiTodosPreRequisitos = true;
        for (String preRequisito : turmaEscolhida.getPreRequisitos()) {
            boolean possuiPreRequisito = this.getDisciplinasFeitas().stream()
            .map(String::toLowerCase)
            .anyMatch(disciplina -> disciplina.equals(preRequisito.toLowerCase()));
            if (!possuiPreRequisito) {
            JOptionPane.showMessageDialog(null, "Aluno não pode se matricular na turma " + turmaEscolhida.getNome() + " porque não tem o pré-requisito: " + preRequisito);
            possuiTodosPreRequisitos = false;
            }
        }
        if (!possuiTodosPreRequisitos) {
            return;
        }
        turmaEscolhida.adicionarAluno(this);
        turmasMatriculadas.add(turmaEscolhida);
        turmaEscolhida.setVagasDisponiveis(turmaEscolhida.getVagasDisponiveis() - 1);
        turmaEscolhida.setVagasOcupadas(turmaEscolhida.getVagasOcupadas() + 1);
        JOptionPane.showMessageDialog(null, "Aluno " + this.getNome() + " matriculado na turma " + turmaEscolhida.getNome() + " com sucesso.");
    }

   public void trancarDisciplina (Turmas turma) {
        if (turmasMatriculadas.contains(turma)) {
            turmasMatriculadas.remove(turma);
            JOptionPane.showMessageDialog(null, "Disciplina " + turma.getNome() + " trancada com sucesso.");
            turma.setVagasDisponiveis(turma.getVagasDisponiveis() + 1);
            turma.setVagasOcupadas(turma.getVagasOcupadas() - 1);
        } else {
            JOptionPane.showMessageDialog(null, "Disciplina " + turma.getNome() + " não encontrada entre as turmas matriculadas.");
        }
    }
    
    public void trancarSemestre (String semestre) {
        if (turmasMatriculadas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Aluno não está matriculado em nenhuma turma." );;
        } else {
            turmasMatriculadas.clear();
            JOptionPane.showMessageDialog(null, "Semestre " + semestre + " trancado com sucesso.");
        }
    }
}
