import java.util.List;
import javax.swing.JOptionPane;

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

    @Override
    public void setAvaliacao(Avaliacao avaliacao) {
        JOptionPane.showMessageDialog(null, "AlunoEspecial não recebe notas, somente presença.");
    }
}


