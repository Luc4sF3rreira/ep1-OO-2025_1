import java.util.ArrayList;
import java.util.List;


public class Aluno {
    private String nome;
    private String matricula;
    private String curso;
    private String tipoAluno;
    private List<Turmas> turmasMatriculadas;
    private List<String> disciplinasFeitas;

    public Aluno(String nome, String matricula, String curso, String tipoAluno, String disciplinasFeitas) {
        this.nome = nome;
        this.matricula = matricula;
        this.curso = curso;
        this.turmasMatriculadas = new ArrayList<>();
        this.disciplinasFeitas = new ArrayList<>();
    }

    public String getNome() {return nome; }
    public String getMatricula() {return matricula; } 
    public String getCurso() {return curso; }
    public String getTipoAluno() {return tipoAluno; }
    public List<Turmas> getTurmasMatriculadas() {return turmasMatriculadas; }
    public List<String> getDisciplinasFeitas() {return disciplinasFeitas; }
    public void setNome(String nome) {this.nome = nome; }
    public void setMatricula(String matricula) {this.matricula = matricula; }
    public void setCurso(String curso) {this.curso = curso; }
    public void setTipoAluno(String tipoAluno) {this.tipoAluno = tipoAluno; }
    public void setTurmasMatriculadas(List<Turmas> turmasMatriculadas) {this.turmasMatriculadas = turmasMatriculadas; }
    public void setDisciplinasFeitas(List<String> disciplinasFeitas) {this.disciplinasFeitas = disciplinasFeitas; }
    
    //Métodos
    public void matricularTurma (Aluno aluno, Aluno matricula, Turmas turma) {
        System.out.println("Turmas disponíveis para matrícula:");
        for (Turmas t : aluno.getTurmasMatriculadas()) {
            System.out.println("- " + t.getNome() + " (" + t.getSemestre() + ")");
        }
        
        if (turma.getVagasDisponiveis() > 0) {
            turma.adicionarAluno(aluno);
            aluno.getTurmasMatriculadas().add(turma);

            if (!turma.getPreRequisitos().isEmpty()) {
                for (String preRequisito : turma.getPreRequisitos())
                    if (aluno.getDisciplinasFeitas().stream().noneMatch(disciplina -> disciplina.equals(preRequisito)))
                    System.out.println("Aluno" + aluno.getNome() + "não pode se matricular na turma" + turma.getNome() + "porque não tem os pré-requisitos: " + preRequisito);
                    return;
                    }          
    
            if (aluno instanceof AlunoEspecial) {
                if (aluno.getTurmasMatriculadas().size() >= 2) {
                    System.out.println("Aluno especial só pode se matricular em até 2 disciplinas.");
                    return;
                }
            }
            System.out.println("Aluno " + aluno.getNome() + " matriculado com sucesso na turma " + turma.getNome() + ".");
        } else {
            System.out.println("Não há vagas disponíveis na turma " + turma.getNome() + ".");
        }
    }      
    
    public void trancarDisciplina (Turmas turma) {
        if (turmasMatriculadas.contains(turma)) {
            turmasMatriculadas.remove(turma);
            System.out.println("Disciplina " + turma.getNome() + " trancada com sucesso.");
        } else {
            System.out.println("Disciplina não encontrada entre as turmas matriculadas.");
        }
    }

    public void trancarSemestre (String semestre) {
        if (turmasMatriculadas.isEmpty()) {
            turmasMatriculadas.clear();
            System.out.println("Semestre " + semestre + " trancado com sucesso.");
        } else {
            System.out.println("Não é possível trancar o semestre, pois há turmas matriculadas.");
        }
    }
}
