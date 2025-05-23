import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        this.turmasMatriculadas = new ArrayList<>();
        this.disciplinasFeitas = new ArrayList<>();
    }
    public String getNome() {return nome; }
    public String getMatricula() {return matricula; } 
    public String getCurso() {return curso; }
    public String getTipoAluno() {return tipoAluno; }
    public List<Turmas> getTurmasMatriculadas() {return turmasMatriculadas; }
    public List<String> getDisciplinasFeitas() {return disciplinasFeitas; }
    public Avaliacao getAvaliacao () {return avaliacao;}
    public void setNome(String nome) {this.nome = nome; }
    public void setMatricula(String matricula) {this.matricula = matricula; }
    public void setCurso(String curso) {this.curso = curso; }
    public void setTipoAluno(String tipoAluno) {this.tipoAluno = tipoAluno; }
    public void setTurmasMatriculadas(List<Turmas> turmasMatriculadas) {this.turmasMatriculadas = turmasMatriculadas; }
    public void setDisciplinasFeitas(List<String> disciplinasFeitas) {this.disciplinasFeitas = disciplinasFeitas; }
    public void setAvaliacao(Avaliacao avaliacao) {this.avaliacao = avaliacao;}   

    public void matricularTurma(String nomeTurma) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome do aluno que deseja matricular: ");
        String nomeAluno = scanner.nextLine();
        if (!this.nome.equalsIgnoreCase(nomeAluno)) {
            System.out.println("Aluno não encontrado.");
            return;
        }
        System.out.println("Turmas disponíveis para matrícula:");
        for (int i = 0; i < turmasMatriculadas.size(); i++) {
            Turmas t = turmasMatriculadas.get(i);
            System.out.println((i + 1) + ". " + t.getNome() + " (" + t.getSemestre() + ")");
        }
        System.out.print("Digite a turma que deseja se matricular: ");
        int escolha = scanner.nextInt();
        scanner.nextLine();

        if (escolha < 1 || escolha > turmasMatriculadas.size()) {
            System.out.println("Opção inválida.");
            return;
        }
        Turmas turma = turmasMatriculadas.get(escolha - 1);
        Aluno aluno = this;
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
