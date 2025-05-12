import java.util.ArrayList;
import java.util.List;


public class Aluno {
    private String nome;
    private String matricula;
    private String curso;
    private List<Turmas> turmasMatriculadas;
    private List<Notas> notas;

    public Aluno(String nome, String matricula, String curso) {
        this.nome = nome;
        this.matricula = matricula;
        this.curso = curso;
        this.turmasMatriculadas = new ArrayList<>();
        this.notas = new ArrayList<>();
    }

    public String getNome() {return nome; }
    public String getMatricula() {return matricula; } 
    public String getCurso() {return curso; }
    public List<Turmas> getTurmasMatriculadas() {return turmasMatriculadas; }
    public List<Notas> getNotas() {return notas; }

    @Override
    public String toString() {
        return "Aluno{" +
                "nome='" + nome + '\'' +
                ", matricula='" + matricula + '\'' +
                ", curso='" + curso + '\'' +
                ", turmasMatriculadas=" + turmasMatriculadas +
                ", notas=" + notas +
                '}';
    }
}