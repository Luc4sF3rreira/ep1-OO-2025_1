import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Aluno {
    private String nome;
    private String matricula;
    private String curso;
    private String tipoAluno;
    private List<Turmas> turmasMatriculadas;
    private List<String> disciplinasFeitas;
    private Avaliacao avaliacao;
    private static List<Aluno> listaDeAlunos = new ArrayList<>();

    public Aluno(String nome, String matricula, String curso, String tipoAluno, List<String> disciplinasFeitas) {
        this.nome = nome;
        this.matricula = matricula;
        this.curso = curso;
        this.tipoAluno = tipoAluno;
        this.turmasMatriculadas = new ArrayList<>();
        this.disciplinasFeitas = new ArrayList<>(disciplinasFeitas);
        listaDeAlunos.add(this);
        
    }
    public String getNome() {return nome; }
    public String getMatricula() {return matricula; } 
    public String getCurso() {return curso; }
    public String getTipoAluno() {return tipoAluno; }
    public List<Turmas> getTurmasMatriculadas() {return turmasMatriculadas; }
    public List<String> getDisciplinasFeitas() {return new ArrayList<>(disciplinasFeitas); }
    public Avaliacao getAvaliacao () {return avaliacao;}
    public static List<Aluno> getListaDeAlunos() { return listaDeAlunos; }
    public void setNome(String nome) {this.nome = nome; }
    public void setMatricula(String matricula) {this.matricula = matricula; }
    public void setCurso(String curso) {this.curso = curso; }
    public void setTipoAluno(String tipoAluno) {this.tipoAluno = tipoAluno; }
    public void setTurmasMatriculadas(List<Turmas> turmasMatriculadas) {this.turmasMatriculadas = turmasMatriculadas; }
    public void setDisciplinasFeitas(List<String> disciplinasFeitas) {this.disciplinasFeitas = disciplinasFeitas; }
    public void setAvaliacao(Avaliacao avaliacao) {this.avaliacao = avaliacao;}

    public Double getMediaFinal() {return avaliacao.calcularMediaFinal();}
    public int getFrequencia() {return avaliacao.calcularFrequencia();}
    public String getSituacao() {return avaliacao.consultarSituacao();}
    public void setMediaFinal(Double mediaFinal) {this.avaliacao.setMediaFinal(mediaFinal);}
    public void setFrequencia(int frequencia) {this.avaliacao.setFrequencia(frequencia);}
    public void setSituacao(String situacao) {this.avaliacao.setSituacao(situacao);}

    public void matricularTurma(Turmas turmas) {
        String[] alunosCadastrados = new String [Aluno.getListaDeAlunos().size()];
        for (int i = 0; i < Aluno.getListaDeAlunos().size(); i++) {
            Aluno aluno = Aluno.getListaDeAlunos().get(i);
            alunosCadastrados[i] = (i + 1) + ". " + aluno.getNome() + " - Matrícula: " + aluno.getMatricula();
        }
        String alunoSelecionado = (String) JOptionPane.showInputDialog(
            null,
            "Seleciona o aluno que deseja se matricular:",
            "Alunos Cadastrados",
            JOptionPane.QUESTION_MESSAGE,
            null,
            alunosCadastrados,
            alunosCadastrados[0]);
        int indiceAluno = Integer.parseInt(alunoSelecionado.split("\\.")[0]) - 1;
        Aluno alunoEscolhido = Aluno.getListaDeAlunos().get(indiceAluno);    
        
        String[] turmasDisponiveis = new String [Turmas.getTodasTurmas().size()];
        for (int i = 0; i < Turmas.getTodasTurmas().size(); i++) {
            Turmas turma = Turmas.getTodasTurmas().get(i);
            turmasDisponiveis[i] = (i + 1) + ". " + turma.getNome() + " - Semestre: " + turma.getSemestre();
        }
        String turmaSelecionada = (String) JOptionPane.showInputDialog(
            null,
            "Selecione a turma para se matricular:",
            "Turmas Disponíveis",
            JOptionPane.QUESTION_MESSAGE,
            null,
            turmasDisponiveis,
            turmasDisponiveis[0]); 
        int indiceTurma = Integer.parseInt(turmaSelecionada.split("\\.")[0]) - 1;
        Turmas turmaEscolhida = Turmas.getTodasTurmas().get(indiceTurma);
        if (turmaEscolhida.getVagasDisponiveis() <= 0) {
            JOptionPane.showMessageDialog(null, "Não há vagas disponíveis na turma " + turmaEscolhida.getNome() + ".");
            return;
        }
        if (turmaEscolhida.getAlunosMatriculados().contains(alunoEscolhido)) {
            JOptionPane.showMessageDialog(null, "Aluno já está matriculado na turma " + turmaEscolhida.getNome() + ".");
            return;
        }  
        List<String> preRequisitos = turmaEscolhida.getPreRequisitos();
        List<String> disciplinasFeitasAluno = alunoEscolhido.getDisciplinasFeitas();

        if (!preRequisitos.isEmpty() && (disciplinasFeitasAluno == null || disciplinasFeitasAluno.isEmpty())) {
            JOptionPane.showMessageDialog(null, "Aluno não pode se matricular na turma " + turmaEscolhida.getNome() + " porque não possui nenhum pré-requisito.");
            return;
        }

        boolean possuiTodosPreRequisitos = true;
        for (String preRequisito : preRequisitos) {
            boolean possuiPreRequisito = false;
            for (String disciplina : disciplinasFeitasAluno) {
                if (disciplina.equalsIgnoreCase(preRequisito)) {
                    possuiPreRequisito = true;
                    break;
                }
            }
            if (!possuiPreRequisito) {
                JOptionPane.showMessageDialog(null, "Aluno não pode se matricular na turma " + turmaEscolhida.getNome() + " porque não tem o pré-requisito: " + preRequisito);
                possuiTodosPreRequisitos = false;
            }
        }
        if (!possuiTodosPreRequisitos) {
            return;
        }
        turmaEscolhida.adicionarAluno(alunoEscolhido);
        alunoEscolhido.getTurmasMatriculadas().add(turmaEscolhida);
        turmaEscolhida.setVagasDisponiveis(turmaEscolhida.getVagasDisponiveis() - 1);
        turmaEscolhida.setVagasOcupadas(turmaEscolhida.getVagasOcupadas() + 1);
        JOptionPane.showMessageDialog(null, "Aluno " + alunoEscolhido.getNome() + " matriculado na turma " + turmaEscolhida.getNome() + " com sucesso.");
    }

public void trancarDisciplina() {
    if (turmasMatriculadas.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Aluno não está matriculado em nenhuma disciplina.");
        return;
    }
    String[] turmasArray = new String[turmasMatriculadas.size()];
    for (int i = 0; i < turmasMatriculadas.size(); i++) {
        Turmas turma = turmasMatriculadas.get(i);
        turmasArray[i] = (i + 1) + ". " + turma.getNome() + " - Semestre: " + turma.getSemestre();
    }
    String turmaSelecionada = (String) JOptionPane.showInputDialog(
        null,
        "Selecione a disciplina para trancar:",
        "Trancar Disciplina",
        JOptionPane.QUESTION_MESSAGE,
        null,
        turmasArray,
        turmasArray[0]);
    if (turmaSelecionada == null) {
        return;
    }
    int indiceTurma = Integer.parseInt(turmaSelecionada.split("\\.")[0]) - 1;
    Turmas turma = turmasMatriculadas.get(indiceTurma);

    turmasMatriculadas.remove(turma);
    JOptionPane.showMessageDialog(null, "Disciplina " + turma.getNome() + " trancada com sucesso.");
    turma.setVagasDisponiveis(turma.getVagasDisponiveis() + 1);
    turma.setVagasOcupadas(turma.getVagasOcupadas() - 1);
}
    public void trancarSemestre (String semestre) {
        if (turmasMatriculadas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Aluno não está matriculado em nenhuma turma." );;
        } else {
            turmasMatriculadas.clear();
            JOptionPane.showMessageDialog(null, "Semestre " + semestre + " trancado com sucesso.");
        }
    }
}
