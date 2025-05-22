import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class JanelaAvaliacao_Frequencia extends JFrame{
    public JanelaAvaliacao_Frequencia() {
        super("Modo Avaliação/Frequência");
        setSize(700,500);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JPanel botao_painel = new JPanel(new GridLayout(6,1,5,5));
        JButton botaoLancarNotas = new JButton("Lançar notas");
        JButton botaoLancarFrequencia = new JButton("Lançar frequência");
        JButton botaoMediaFinal = new JButton("Calcular média final");
        JButton botaoSituacao = new JButton("Situação do aluno");
        JButton botaoGerarRelatorios = new JButton("Gerar relatórios");
        JButton botaoMostrarBoletins = new JButton("Exibir boletins");
        JButton botaoVoltar = new JButton("Voltar ao menu principal");

        botaoLancarNotas.addActionListener(e -> lancarNotas());
        botaoLancarFrequencia.addActionListener(e -> lancarFrequencia());

        botao_painel.add(botaoLancarNotas);
        botao_painel.add(botaoLancarFrequencia);
        botao_painel.add(botaoMediaFinal);
        botao_painel.add(botaoSituacao);
        botao_painel.add(botaoGerarRelatorios);
        botao_painel.add(botaoMostrarBoletins);
        botao_painel.add(botaoVoltar);

        panel.add(botao_painel, BorderLayout.WEST);

        String[] columnNames = {"Matricula", "Aluno", "P1", "P2", "P3", "Listas", "Seminário", "Média", "Frequência"};
        Object[][] data = {};

        JTable table = new JTable(data, columnNames);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        add(panel);

    }

}
