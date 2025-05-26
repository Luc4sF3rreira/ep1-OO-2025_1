import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class JanelaAvaliacao_Frequencia extends JFrame{
    ModoAvaliacao_Frequencia modoAF;

    public JanelaAvaliacao_Frequencia() {
        super("Modo Avaliação/Frequência");
        modoAF = new ModoAvaliacao_Frequencia();
        setSize(450,600);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JPanel botao_painel = new JPanel(new GridLayout(6,1,5,5));

        JButton botaoLancarNotas = new JButton("Lançar notas");
        botaoLancarNotas.setFont(botaoLancarNotas.getFont().deriveFont(14.0f));

        JButton botaoRelatorioTurma = new JButton("Relatório por turmas");
        botaoRelatorioTurma.setFont(botaoRelatorioTurma.getFont().deriveFont(14.0f));

        JButton botaoRelatorioDisciplina = new JButton("Relatório por disciplinas");
        botaoRelatorioDisciplina.setFont(botaoRelatorioDisciplina.getFont().deriveFont(14.0f));

        JButton botaoRelatorioProfessor = new JButton("Relatório por professores");
        botaoRelatorioProfessor.setFont(botaoRelatorioProfessor.getFont().deriveFont(14.0f));

        JButton botaoExibirBoletimAluno = new JButton("Boletim do aluno");
        botaoExibirBoletimAluno.setFont(botaoExibirBoletimAluno.getFont().deriveFont(14.0f));

        JButton botaoVoltar = new JButton("Voltar ao menu principal");
        botaoVoltar.setFont(botaoVoltar.getFont().deriveFont(14.0f));

        botaoLancarNotas.addActionListener(e -> lancarNotas());
        botaoRelatorioTurma.addActionListener(e -> relatorioTurmas());
        botaoRelatorioDisciplina.addActionListener(e -> relatorioDisciplinas());
        botaoRelatorioProfessor.addActionListener(e -> relatorioProfessores());
        botaoExibirBoletimAluno.addActionListener(e -> boletinsAlunos());
        botaoVoltar.addActionListener(e -> dispose());

        botao_painel.add(botaoLancarNotas);
        botao_painel.add(botaoRelatorioTurma);
        botao_painel.add(botaoRelatorioDisciplina);
        botao_painel.add(botaoRelatorioProfessor);
        botao_painel.add(botaoExibirBoletimAluno);
        botao_painel.add(botaoVoltar);

        panel.add(botao_painel, BorderLayout.CENTER);
        add(panel);
    }
    private void lancarNotas() {
        modoAF.lancarNotasParaTurma(null, null);
    }    
    private void relatorioTurmas() {
        modoAF.relatorioPorTurma(null);
    }
    private void relatorioDisciplinas() {
        modoAF.relatorioPorDisciplina(null);
    }
    private void relatorioProfessores() {
        modoAF.relatorioPorProfessor(null);
    }
    private void boletinsAlunos() {
        modoAF.exibirBoletimPorAluno(null, rootPaneCheckingEnabled);
    }
}


