public class Main {
    public static void main(String[] args) {
        ModoAluno modoAluno = new ModoAluno();
        ModoDisciplina_Turma modoDT = new ModoDisciplina_Turma();
        modoAluno.cadastrarAlunos();
        modoAluno.listarAlunos();
        modoDT.cadastrarDisciplinas();
        modoDT.listarDisciplinas(modoDT.getDisciplinas());
    }

}
