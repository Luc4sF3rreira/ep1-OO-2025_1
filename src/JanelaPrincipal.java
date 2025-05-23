import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JanelaPrincipal extends JFrame{
        public JanelaPrincipal() {
        super("Sistema Acadêmico FCTE");
        
        JPanel panel = new JPanel(new GridLayout(5,1,10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        JLabel mensagemTop = new JLabel("Bem-vindo ao Sistema Acadêmico FCTE", JLabel.CENTER);
        mensagemTop.setFont(mensagemTop.getFont().deriveFont(20.0f));
        panel.add(mensagemTop);

        JButton botaoModoAluno = new JButton("Modo Aluno");
        botaoModoAluno.setFont(botaoModoAluno.getFont().deriveFont(14.0f));
        
        JButton botaoModoDisciplina_Turnma =  new JButton("Modo Disciplina/Turma");
        botaoModoDisciplina_Turnma.setFont(botaoModoDisciplina_Turnma.getFont().deriveFont(14.0f));

        JButton botaoModoAvaliacao_Frequencia = new JButton("Modo Avaliação/Turma");
        botaoModoAvaliacao_Frequencia.setFont(botaoModoAvaliacao_Frequencia.getFont().deriveFont(14.0f));

        JButton botaoSair = new JButton("Sair e salvar dados");
        botaoSair.setFont(botaoSair.getFont().deriveFont(14.0f));

        botaoModoAluno.addActionListener(e -> new JanelaAluno().setVisible(true));
        botaoModoDisciplina_Turnma.addActionListener(e -> new JanelaDisciplina_Turma().setVisible(true));
        botaoModoAvaliacao_Frequencia.addActionListener(e -> new JanelaAvaliacao_Frequencia().setVisible(true));
        botaoSair.addActionListener(e -> System.exit(0));

        panel.add(botaoModoAluno);
        panel.add(botaoModoDisciplina_Turnma);
        panel.add(botaoModoAvaliacao_Frequencia);
        panel.add(botaoSair);
        add(panel);
    }
}
