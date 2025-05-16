public class AlunoEspecial extends Aluno {

    public AlunoEspecial(String nome, String matricula, String curso, String tipoAluno, String disciplinasFeitas) {
        super(nome, matricula, curso, tipoAluno, disciplinasFeitas);
    }

    @Override
    public void matricularTurma(Aluno aluno, Aluno matricula, Turmas turma) {
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
}


