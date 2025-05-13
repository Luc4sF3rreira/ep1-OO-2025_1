import java.util.ArrayList;
import java.util.List;


public class Aluno {
    private String nome;
    private String matricula;
    private String curso;
    private List<Turmas> turmasMatriculadas;
    private List<Disciplina> disciplinasFeitas;

    public Aluno(String nome, String matricula, String curso) {
        this.nome = nome;
        this.matricula = matricula;
        this.curso = curso;
        this.turmasMatriculadas = new ArrayList<>();
        this.disciplinasFeitas = new ArrayList<>();
    }

    public String getNome() {return nome; }
    public String getMatricula() {return matricula; } 
    public String getCurso() {return curso; }
    public List<Turmas> getTurmasMatriculadas() {return turmasMatriculadas; }
    public List<Disciplina> getDisciplinasFeitas() {return disciplinasFeitas; }

}
