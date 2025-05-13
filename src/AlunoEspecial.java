import java.util.ArrayList;
import java.util.List;

public class AlunoEspecial extends Aluno {

    private List<String> turmas;

    public AlunoEspecial(String nome, String matricula, String curso) {
        super(nome, matricula, curso);
        this.turmas = new ArrayList<>();
    }



    public List<String> getTurmas() {
        return turmas;
    }

    public void adicionarNota(String disciplina, double nota) {
        System.out.println("Aluno Especial não recebe notas.");
    }

    public double calcularMediaFinal() {
        System.out.println("Aluno Especial não possui média, apenas presença.");
        return 0.0;
    }
 }



