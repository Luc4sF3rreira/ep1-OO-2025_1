import javax.swing.*;
import java.awt.*;

public class SistemaAcademico {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            new JanelaPrincipal().setVisible(true);
        });
    }

}

class JanelaPrincipal extends JFrame {
    public JanelaPrincipal() {
        super("Sistema Acadêmico FCTE");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3,1,10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        JButton botaoModoAluno = new JButton("Modo Aluno");
        JButton botaoModoDisciplina_Turnma =  new JButton("Modo Disciplina/Turma");
        JButton botaoModoAvaliacao_Frequencia = new JButton("Modo Avaliação/Turma");

        botaoModoAluno.addActionListener(e -> new JanelaAluno().setVisible(true));
        botaoModoDisciplina_Turnma.addActionListener(e -> new JanelaDisciplina_Turma().setVisible(true));
        botaoModoAvaliacao_Frequencia.addActionListener(e -> new JanelaAvaliacao_Frequencia().setVisible(true));

        panel.add(botaoModoAluno);
        panel.add(botaoModoDisciplina_Turnma);
        panel.add(botaoModoAvaliacao_Frequencia);

        add(panel);
    }
}

