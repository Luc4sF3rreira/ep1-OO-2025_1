import java.util.ArrayList;
import java.util.List;


public class Aluno {
    private String nome;
    private String matricula;
    private String curso;
    private String tipoAluno;
    private List<Turmas> turmasMatriculadas;
    private List<Disciplina> disciplinasFeitas;

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
    public List<Disciplina> getDisciplinasFeitas() {return disciplinasFeitas; }


    
    public void matricularTurma (Aluno aluno, Aluno matricula, Turmas turma) {
        if (turma.getVagasDisponiveis() > 0) {
            turma.adicionarAluno(aluno);
            aluno.getTurmasMatriculadas().add(turma);

            if (!turma.getDisciplina().getPreRequisitos().isEmpty()) {
                for (String preRequisito : turma.getDisciplina().getPreRequisitos()) {
                    if (aluno.getDisciplinasFeitas().stream().noneMatch(disciplina -> disciplina.getNome().equals(preRequisito))) {
                        System.out.println("Aluno " + aluno.getNome() + " não pode se matricular na turma " + turma.getNome() + 
                                           " porque não completou o pré-requisito: " + preRequisito + ".");
                        return;
                    }
                }
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

    public void trancarDisciplina(Aluno aluno, Disciplina disciplina) {
        if (aluno.getDisciplinasFeitas().contains(disciplina)) {
            aluno.getDisciplinasFeitas().remove(disciplina);
            System.out.println("Disciplina " + disciplina.getNome() + " trancada com sucesso.");
        } else {
            System.out.println("Disciplina " + disciplina.getNome() + " não encontrada entre as disciplinas feitas.");
        }
    }    

    public void trancarSemestre(Aluno aluno, String semestre) {
        if (aluno.getTurmasMatriculadas().stream().anyMatch(turma -> turma.getSemestre().equals(semestre))) {
            aluno.getTurmasMatriculadas().removeIf(turma -> turma.getSemestre().equals(semestre));
            System.out.println("Turmas do semestre " + semestre + " trancadas com sucesso.");
        } else {
            System.out.println("Nenhuma turma encontrada para o semestre " + semestre + ".");
        }
    }

    public void salvarDados(Aluno aluno) {
        System.out.println("Dados do aluno " + aluno.getNome() + " salvos com sucesso.");
    }

    public void carregarDados(Aluno aluno) {
        System.out.println("Dados do aluno " + aluno.getNome() + " carregados com sucesso.");
    }




}
