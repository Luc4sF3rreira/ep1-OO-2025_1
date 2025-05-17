import java.util.List;

public class Turmas extends Disciplina {
    private String professor;
    private String semestre;
    private Integer numeroTurma;
    private FormadeAvaliação avaliação;
    private String modalidade;
    private String sala;
    private String turmaHorario;
    private Integer maxAlunos;
    private Integer vagasDisponiveis;
    private Integer vagasOcupadas;
    private List<Aluno> alunosMatriculados;

    public Turmas(Disciplina disciplina, String professor, String semestre, Integer numeroTurma, FormadeAvaliação avaliação, String modalidade, String sala, String turmaHorario, Integer maxAlunos) {
        super(disciplina.getNome(), disciplina.getCodigo(), disciplina.getCargaHoraria(), disciplina.getPreRequisitos()); 
        this.professor = professor;
        this.semestre = semestre;
        this.numeroTurma = numeroTurma;
        this.avaliação = avaliação;
        this.modalidade = modalidade;
        this.sala = sala;
        this.turmaHorario = turmaHorario;
        this.maxAlunos = maxAlunos;
    }

    public String getProfessor() { return professor; }
    public String getSemestre() { return semestre; }  
    public Integer getNumeroTurma() { return numeroTurma; }
    public FormadeAvaliação getAvaliação() { return avaliação; }
    public String getModalidade() { return modalidade; }
    public String getSala() { return sala; }
    public String getHorario() { return turmaHorario; }
    public Integer getMaxAlunos() { return maxAlunos; }
    public Integer getVagasOcupadas() { return vagasOcupadas; }
    public void setVagasOcupadas(Integer vagasOcupadas) {this.vagasOcupadas = alunosMatriculados.size(); }
    public List<Aluno> getAlunosMatriculados() { return alunosMatriculados; }
    public Integer getVagasDisponiveis() { return maxAlunos - vagasOcupadas; }

    public void adicionarAluno(Aluno aluno) {
        if (vagasOcupadas < maxAlunos) {
            alunosMatriculados.add(aluno);
            vagasDisponiveis--;
        } else {
            System.out.println("Não há vagas disponíveis na turma " + numeroTurma + ".");
        }
    }

}