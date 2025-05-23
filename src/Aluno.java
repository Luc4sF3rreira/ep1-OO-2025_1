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

    public void matricularTurma(String nomeTurma) {
        String nomeAluno = JOptionPane.showInputDialog(null, "Digite o nome do aluno que deseja matricular:");
        if (!this.nome.equalsIgnoreCase(nomeAluno)) {
            JOptionPane.showMessageDialog(null, "Aluno não encontrado.");
            return;
        }
        StringBuilder turmasDisponiveis = new StringBuilder();
        for (int i = 0; i < turmasMatriculadas.size(); i++) {
            Turmas t = turmasMatriculadas.get(i);
            turmasDisponiveis.append((i + 1) + ". " + t.getNome() + " (" + t.getSemestre() + ")\n");
        }
        JOptionPane.showMessageDialog(null, "Turmas disponíveis para matrícula:\n" + turmasDisponiveis.toString());

        String escolha = JOptionPane.showInputDialog(null, "Digite a turma que deseja se matricular:");
        Turmas turmaEscolhida = null;
        for (Turmas t : turmasMatriculadas) {
            if (t.getNome().equalsIgnoreCase(escolha)) {
                turmaEscolhida = t;
                break;
            }
        }
        if (turmaEscolhida == null) {
            JOptionPane.showMessageDialog(null, "Turma não encontrada.");
            return;
        }
        if (turmasMatriculadas.contains(turmaEscolhida)) {
            JOptionPane.showMessageDialog(null, "Aluno já está matriculado na turma " + turmaEscolhida.getNome() + ".");
            return;
        }
        if (turmaEscolhida.getVagasDisponiveis() <= 0) {
            JOptionPane.showMessageDialog(null, "Não há vagas disponíveis na turma " + turmaEscolhida.getNome() + ".");
            return;
        }
        Aluno aluno = this;
        System.out.println("Turmas disponíveis para matrícula:");
        for (Turmas t : aluno.getTurmasMatriculadas()) {
            System.out.println("- " + t.getNome() + " (" + t.getSemestre() + ")");
        }
        boolean possuiTodosPreRequisitos = true;
        for (String preRequisito : turmaEscolhida.getPreRequisitos()) {
            boolean possuiPreRequisito = aluno.getDisciplinasFeitas().stream()
                .map(String::toLowerCase)
                .anyMatch(disciplina -> disciplina.equals(preRequisito.toLowerCase()));
            if (!possuiPreRequisito) {
                System.out.println("Aluno " + aluno.getNome() + " não pode se matricular na turma " + turmaEscolhida.getNome() + " porque não tem o pré-requisito: " + preRequisito);
                possuiTodosPreRequisitos = false;
            }
        }
        if (!possuiTodosPreRequisitos) {
            return;
        }
        if (turmaEscolhida.getVagasDisponiveis() > 0) {
            turmaEscolhida.adicionarAluno(aluno);
            aluno.turmasMatriculadas.add(turmaEscolhida);
            turmaEscolhida.setVagasDisponiveis(turmaEscolhida.getVagasDisponiveis() - 1);
            turmaEscolhida.setVagasOcupadas(turmaEscolhida.getVagasOcupadas() + 1);
            JOptionPane.showMessageDialog(null, "Aluno " + aluno.getNome() + " matriculado na turma " + turmaEscolhida.getNome() + " com sucesso.");      
        } else if (turmaEscolhida.getVagasDisponiveis() <= 0) {
            JOptionPane.showMessageDialog(null, "Não há vagas disponíveis na turma " + turmaEscolhida.getNome() + ".");
        } else if (turmaEscolhida.getVagasOcupadas() >= turmaEscolhida.getMaxAlunos()) {
            JOptionPane.showMessageDialog(null, "Turma " + turmaEscolhida.getNome() + " já está cheia.");
        }
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
