public class MainTeste {
    public static void main(String[] args) {
        ModoAluno modoAluno = new ModoAluno();
        ModoDisciplina_Turma modoDT = new ModoDisciplina_Turma();
        ModoAvaliacao_Frequencia modoAF = new ModoAvaliacao_Frequencia();
        modoAluno.cadastrarAlunos();
        modoAluno.listarAlunos();
        modoDT.cadastrarDisciplinas();
        modoDT.listarDisciplinas(modoDT.getDisciplinas());
    }

}
