import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Turmas extends Disciplina {
    private String professor;
    private String semestre;
    private Integer numeroTurma;
    private String tipoAvaliação;
    private String modalidade;
    private String sala;
    private String turmaHorario;
    private Integer maxAlunos;
    private Integer totalAulas;
    private Integer vagasDisponiveis;
    private Integer vagasOcupadas;
    private List<Aluno> alunosMatriculados;
    private boolean presencial;
    private static List<Turmas> todasTurmas = new ArrayList<>();

    public Turmas(Disciplina disciplina, String professor, String semestre, Integer numeroTurma, String tipoAvaliação, String modalidade, String sala, String turmaHorario, Integer maxAlunos, Integer totalAulas) {
        super(disciplina.getNome(), disciplina.getCodigo(), disciplina.getCargaHoraria(), disciplina.getPreRequisitos()); 
        this.professor = professor;
        this.semestre = semestre;
        this.numeroTurma = numeroTurma;
        this.tipoAvaliação = tipoAvaliação;
        this.modalidade = modalidade;
        this.sala = sala;
        this.turmaHorario = turmaHorario;
        this.maxAlunos = maxAlunos;
        this.vagasDisponiveis = (vagasDisponiveis != null) ? vagasDisponiveis : 0;
        this.totalAulas = totalAulas;
        this.alunosMatriculados = new ArrayList<>();
        todasTurmas.add(this);
    }

    public String getProfessor() { return professor; }
    public String getSemestre() { return semestre; }  
    public Integer getNumeroTurma() { return numeroTurma; }
    public String getTipoAvaliacao() { return tipoAvaliação; }
    public String getModalidade() { return modalidade; }
    public boolean isPresencial() {return presencial;}
    public String getSala() { return sala; }
    public String getHorario() { return turmaHorario; }
    public Integer getMaxAlunos() { return maxAlunos; }
    public Integer getTotalAulas() {return totalAulas;}
    public Integer getVagasOcupadas() { return vagasOcupadas; }
    public static List<Turmas> getTodasTurmas() { return todasTurmas; }
    public void setVagasOcupadas(Integer vagasOcupadas) {this.vagasOcupadas = alunosMatriculados.size(); }
    public List<Aluno> getAlunosMatriculados() { return alunosMatriculados; }
    public Integer getVagasDisponiveis() { return maxAlunos - vagasOcupadas; }
    public Disciplina getDisciplina() { return new Disciplina(getNome(), getCodigo(), getCargaHoraria(), getPreRequisitos()); }
    public void setVagasDisponiveis(Integer vagasDisponiveis) { this.vagasDisponiveis = vagasDisponiveis; }
    public void setAlunosMatriculados(List<Aluno> alunosMatriculados) {
        this.alunosMatriculados = alunosMatriculados;
        this.vagasOcupadas = alunosMatriculados.size();
        this.vagasDisponiveis = maxAlunos - vagasOcupadas;
    }

    public void adicionarAluno(Aluno aluno) {
        if (vagasOcupadas < maxAlunos) {
            alunosMatriculados.add(aluno);
            vagasDisponiveis--;
        } else {
            JOptionPane.showMessageDialog(null, "Não há vagas disponíveis na turma " + numeroTurma + ".");
        }
    }

}