import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class JanelaAluno extends JFrame {
    private ModoAluno modoAluno;
    
    public JanelaAluno() {
        super("Modo Aluno");
        ModoAluno modoAluno = new ModoAluno();
        Aluno aluno = new Aluno("Nome", "Matricula", "Curso", "Tipo de Aluno", new ArrayList<>());
        setSize(600,400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JPanel botaoPainel = new JPanel(new GridLayout(7,1,5,5));

        JButton botaoCadastrarAlunos = new JButton("Cadastrar Aluno");
        JButton botaoEditarCadastro = new JButton ("Editar cadastro");
        JButton botaoListarAluno = new JButton("Listar Alunos");
        JButton botaoMatricularTurma = new JButton("Matricular em turma");
        JButton botaoTrancarDisciplina = new JButton("Trancar disciplina");
        JButton botaoTrancarSemestre = new JButton("Trancar semestre");
        JButton botaoVoltar = new JButton("Voltar ao menu principal");

        botaoCadastrarAlunos.addActionListener(e -> cadastroAlunos());
        botaoEditarCadastro.addActionListener(e -> editarCadastro());
        botaoListarAluno.addActionListener(e -> listarAlunos());
        botaoMatricularTurma.addActionListener(e -> matricularTurma());
        botaoTrancarDisciplina.addActionListener(e -> trancarDisciplina());
        botaoTrancarSemestre.addActionListener(e -> trancarSemestre());
        botaoVoltar.addActionListener(e -> dispose());

        botaoPainel.add(botaoCadastrarAlunos);
        botaoPainel.add(botaoEditarCadastro);
        botaoPainel.add(botaoListarAluno);
        botaoPainel.add(botaoMatricularTurma);
        botaoPainel.add(botaoTrancarDisciplina);
        botaoPainel.add(botaoTrancarSemestre);
        botaoPainel.add(botaoVoltar);

        panel.add(botaoPainel, BorderLayout.WEST);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        add(panel);
    }

    private void cadastroAlunos() {
        modoAluno.cadastrarAlunos();
    }

    private void editarCadastro() {
        modoAluno.editarCadastroAluno();
    }

    private void listarAlunos() {
        modoAluno.listarAlunos();
    }

    private void matricularTurma () {
        modoAluno.matricularAlunoTurma();
    }

    private void trancarDisciplina () {
        modoAluno.trancarDisciplinaAluno();
    }

    private void trancarSemestre () {
        modoAluno.trancarSemestreAluno();
    }
}



