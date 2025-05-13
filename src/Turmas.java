import java.util.List;

public class Turmas extends Disciplina {
    private String professor;
    private String semestre;
    private FormadeAvaliação avaliação;
    private String modalidade;
    private String sala;
    private String horario;
    private Integer maxAlunos;
    private List<Integer> alunosMatriculados;

    public Turmas(String nome, String codigo, int cargaHoraria, List<String> preRequisitos, String professor, String semestre, FormadeAvaliação avaliação, boolean status, String sala, String horario, Integer maxAlunos, String modalidade) {
        super(nome, codigo, cargaHoraria, preRequisitos);
        this.professor = professor;
        this.semestre = semestre;
        this.avaliação = avaliação;
        this.modalidade = modalidade;
        this.sala = sala;
        this.horario = horario;
        this.maxAlunos = maxAlunos;
    }

    public String getProfessor() { return professor; }
    public String getSemestre() { return semestre; }  
    public FormadeAvaliação getAvaliação() { return avaliação; }
    public String modalidade() { return modalidade; }
    public String getSala() { return sala; }
    public String getHorario() { return horario; }
    public Integer getMaxAlunos() { return maxAlunos; }
    public List<Integer> getAlunosMatriculados() { return alunosMatriculados; }

    public int getVagasDisponiveis() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getVagasDisponiveis'");
    }

    public void adicionarAluno(Aluno aluno) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'adicionarAluno'");
    }

    public Disciplina getDisciplina() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDisciplina'");
    }    
}