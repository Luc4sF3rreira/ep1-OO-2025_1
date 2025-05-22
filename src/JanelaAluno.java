import javax.swing.*;
import java.awt.*;

public class JanelaAluno extends JFrame {    
    private ModoAluno modoAluno;
    
    public JanelaAluno() {
        super("Modo Aluno");
        modoAluno = new ModoAluno();
        setSize(450,600);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JPanel botaoPainel = new JPanel(new GridLayout(7,1,5,5));

        JButton botaoCadastrarAlunos = new JButton("Cadastrar alunos");
        botaoCadastrarAlunos.setFont(botaoCadastrarAlunos.getFont().deriveFont(14.0f));

        JButton botaoEditarCadastro = new JButton ("Editar cadastro");
        botaoEditarCadastro.setFont(botaoEditarCadastro.getFont().deriveFont(14.0f));

        JButton botaoListarAluno = new JButton("Listar alunos");
        botaoListarAluno.setFont(botaoListarAluno.getFont().deriveFont(14.0f));

        JButton botaoMatricularTurma = new JButton("Matricular em turma");
        botaoMatricularTurma.setFont(botaoMatricularTurma.getFont().deriveFont(14.0f));

        JButton botaoTrancarDisciplina = new JButton("Trancar disciplina");
        botaoTrancarDisciplina.setFont(botaoTrancarDisciplina.getFont().deriveFont(14.0f));

        JButton botaoTrancarSemestre = new JButton("Trancar semestre");
        botaoTrancarSemestre.setFont(botaoTrancarSemestre.getFont().deriveFont(14.0f));

        JButton botaoVoltar = new JButton("Voltar ao menu principal");
        botaoVoltar.setFont(botaoVoltar.getFont().deriveFont(14.0f));

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

        panel.add(botaoPainel, BorderLayout.CENTER);
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



