import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class JanelaAvaliacao_Frequencia extends JFrame{
    ModoAvaliacao_Frequencia modoAF;

    public JanelaAvaliacao_Frequencia() {
        super("Modo Avaliação/Frequência");
        modoAF = new ModoAvaliacao_Frequencia();
        setSize(1050,500);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JPanel botao_painel = new JPanel(new GridLayout(6,1,5,5));
        JButton botaoLancarNotas = new JButton("Lançar notas");
        JButton botaoMediaFinal = new JButton("Calcular média final");
        JButton botaoRelatorioTurma = new JButton("Relatório por turmas");
        JButton botaoRelatorioDisciplina = new JButton("Relatório por disciplinas");
        JButton botaoRelatorioProfessor = new JButton("Relatório por professores");
        JButton botaoExibirBoletimAluno = new JButton("Boletim do aluno");
        JButton botaoVoltar = new JButton("Voltar ao menu principal");

        botaoLancarNotas.addActionListener(e -> lancarNotas());
        botaoRelatorioTurma.addActionListener(e -> relatorioTurmas());
        botaoRelatorioDisciplina.addActionListener(e -> relatorioDisciplinas());
        botaoRelatorioProfessor.addActionListener(e -> relatorioProfessores());
        botaoExibirBoletimAluno.addActionListener(e -> boletinsAlunos());
        botaoVoltar.addActionListener(e -> dispose());


        botao_painel.add(botaoLancarNotas);
        botao_painel.add(botaoMediaFinal);
        botao_painel.add(botaoRelatorioTurma);
        botao_painel.add(botaoRelatorioDisciplina);
        botao_painel.add(botaoRelatorioProfessor);
        botao_painel.add(botaoExibirBoletimAluno);
        botao_painel.add(botaoVoltar);

        panel.add(botao_painel, BorderLayout.WEST);

        String[] columnNames = {"Matricula", "Aluno", "P1", "P2", "P3", "Listas", "Seminário", "Média", "Frequência"};
        Object[][] data = {};

        JTable table = new JTable(data, columnNames);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);

        add(panel);
    }

    private void lancarNotas() {
        modoAF.lancarNotasParaTurma(null);
    }

    private void relatorioTurmas() {
        modoAF.relatorioPorTurma(null);
    }

    private void relatorioDisciplinas() {
        modoAF.relatorioPorDisciplina(null, getName());
    }

    private void relatorioProfessores() {
        modoAF.relatorioPorProfessor(null, getName());
    }

    private void boletinsAlunos() {
        modoAF.exibirBoletimPorAluno(null, getName(), true);
    }
}


