import java.util.List;

public class Turmas extends Disciplina {
    private String professor;
    private String semestre;
    private Integer numeroTurma;
    private FormadeAvaliação avaliação;
    private String modalidade;
    private String sala;
    private String horario;
    private Integer maxAlunos;
    private List<Integer> alunosMatriculados;

    public Turmas(Disciplina disciplina, String professor, String semestre, Integer numeroTurma, FormadeAvaliação avaliação, String modalidade, String sala, String horario, Integer maxAlunos) {
        super(disciplina.getNome(), disciplina.getCodigo(), disciplina.getCargaHoraria(), disciplina.getPreRequisitos()); // Adjust parameters as per Disciplina's constructor
        this.professor = professor;
        this.semestre = semestre;
        this.numeroTurma = numeroTurma;
        this.avaliação = avaliação;
        this.modalidade = modalidade;
        this.sala = sala;
        this.horario = horario;
        this.maxAlunos = maxAlunos;
    }

    public String getProfessor() { return professor; }
    public String getSemestre() { return semestre; }  
    public Integer getNumeroTurma() { return numeroTurma; }
    public FormadeAvaliação getAvaliação() { return avaliação; }
    public String getModalidade() { return modalidade; }
    public String getSala() { return sala; }
    public String getHorario() { return horario; }
    public Integer getMaxAlunos() { return maxAlunos; }
    public List<Integer> getAlunosMatriculados() { return alunosMatriculados; }

    public int getVagasDisponiveis() {
        throw new UnsupportedOperationException("Unimplemented method 'getVagasDisponiveis'");
    }

    public void adicionarAluno(Aluno aluno) {
        throw new UnsupportedOperationException("Unimplemented method 'adicionarAluno'");
    }

    public Disciplina getDisciplina() {
        throw new UnsupportedOperationException("Unimplemented method 'getDisciplina'");
    }

    public String getCapacidadeMaxima() {
        throw new UnsupportedOperationException("Unimplemented method 'getCapacidadeMaxima'");
    }

    public String getFormaAvaliacao() {
        throw new UnsupportedOperationException("Unimplemented method 'getFormaAvaliacao'");
    }    
}