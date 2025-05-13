import java.util.ArrayList;
import java.util.List;

public class AlunoEspecial extends Aluno {

    private List<String> turmas;

    public AlunoEspecial(String nome, String matricula, String curso) {
        super(nome, matricula, curso);
        this.turmas = new ArrayList<>();
    }

    public boolean adicionarTurma(String turma) {
        if (turmas.size() < 2) {
            turmas.add(turma);
            return true;
        } else {
            System.out.println("Aluno Especial só pode cursar no máximo 2 turmas.");
            return false;
        }
    }

    public List<String> getTurmas() {
        return turmas;
    }

    @Override
    public void setNota(double nota) {
        System.out.println("Aluno Especial não recebe notas, apenas presença.");
    }
}


