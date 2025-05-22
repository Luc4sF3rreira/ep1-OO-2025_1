import java.util.List;

public class AlunoEspecial extends Aluno {

    public AlunoEspecial(String nome, String matricula, String curso, String tipoAluno, List<String> disciplinasFeitas) {
        super(nome, matricula, curso, tipoAluno, disciplinasFeitas);
    }

    @Override
    public void matricularTurma(String nomeTurma) {
        if (this.getTurmasMatriculadas().size() < 2) {
            super.matricularTurma(nomeTurma);
        } else {
            System.out.println("AlunoEspecial só pode se matricular em até 2 turmas.");
        }        
    }
}


