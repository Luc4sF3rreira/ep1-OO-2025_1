import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class JanelaDisciplina_Turma extends JFrame {   
    ModoDisciplina_Turma modoDT;
 
    public JanelaDisciplina_Turma() {
        super("Modo Disicplina/Turma");
        modoDT = new ModoDisciplina_Turma();
        modoDT.carregarTudo();
        setSize(450,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel (new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
        JPanel botao_painel = new JPanel(new GridLayout(5,1,5,5));

        JButton botaoCadastrarDisciplina = new JButton("Cadastrar disciplina");
        botaoCadastrarDisciplina.setFont(botaoCadastrarDisciplina.getFont().deriveFont(14.0f));

        JButton botaoListarDisciplinas = new JButton("Listar disciplinas cadastradas");
        botaoListarDisciplinas.setFont(botaoListarDisciplinas.getFont().deriveFont(14.0f));

        JButton botaoCriarTurmas = new JButton("Criar turma");
        botaoCriarTurmas.setFont(botaoCriarTurmas.getFont().deriveFont(14.0f));

        JButton botaoListarTurmas = new JButton("Listar turmas cadastradas");        
        botaoListarTurmas.setFont(botaoListarTurmas.getFont().deriveFont(14.0f));
        
        JButton botaoListarAlunosTurmas  = new JButton("Listar alunos por turmas");
        botaoListarAlunosTurmas.setFont(botaoListarAlunosTurmas.getFont().deriveFont(14.0f));

        JButton botaoVoltar = new JButton("Voltar ao Menu Principal");
        botaoVoltar.setFont(botaoVoltar.getFont().deriveFont(14.0f));

        botaoCadastrarDisciplina.addActionListener(e -> modoDT.cadastrarDisciplinas());
        botaoListarDisciplinas.addActionListener(e -> listarDisciplinas());
        botaoCriarTurmas.addActionListener(e -> modoDT.cadastrarTurmas());
        botaoListarTurmas.addActionListener(e -> listarTurmas());
        botaoVoltar.addActionListener(e -> {modoDT.salvarTudo(); dispose(); });
        
        botao_painel.add(botaoCadastrarDisciplina);
        botao_painel.add(botaoListarDisciplinas);
        botao_painel.add(botaoCriarTurmas);
        botao_painel.add(botaoListarTurmas);
        botao_painel.add(botaoVoltar);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                modoDT.salvarTudo();
                super.windowClosing(e);
            }
        });
        panel.add(botao_painel, BorderLayout.CENTER);
        add(panel);
    }
    private void listarDisciplinas() {
        modoDT.listarDisciplinas();
    }
    private void listarTurmas() {
        modoDT.listarTurmas();
    }
}
