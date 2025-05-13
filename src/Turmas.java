import java.util.List;

public class Turmas extends Disciplina {
    private String professor;
    private String semestre;
    private String avaliação;
    private boolean status;
    private String sala;
    private String horario;
    private Integer maxAlunos;
    private List<Integer> alunosMatriculados;

    public Turmas(String nome, String codigo, int cargaHoraria, List<String> preRequisitos, String professor, String semestre, String avaliação, boolean status, String sala, String horario, Integer maxAlunos) {
        super(nome, codigo, cargaHoraria, preRequisitos);
        this.professor = professor;
        this.semestre = semestre;
        this.avaliação = avaliação;
        this.status = status;
        this.sala = sala;
        this.horario = horario;
        this.maxAlunos = maxAlunos;
    }

    public String getProfessor() { return professor; }
    public String getSemestre() { return semestre; }  
    public String getAvaliação() { return avaliação; }
    public boolean isStatus() { return status; }
    public String getSala() { return sala; }
    public String getHorario() { return horario; }
    public Integer getMaxAlunos() { return maxAlunos; }
    public List<Integer> getAlunosMatriculados() { return alunosMatriculados; }    
}