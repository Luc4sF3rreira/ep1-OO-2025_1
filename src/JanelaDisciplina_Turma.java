import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

public class JanelaDisciplina_Turma extends JFrame {   
    ModoDisciplina_Turma modoDT = new ModoDisciplina_Turma();
 
    public JanelaDisciplina_Turma() {
        super("Modo Disicplina/Turma");
        setSize(600,400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel (new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
        JPanel botao_painel = new JPanel(new GridLayout(5,1,5,5));
        JButton botaoCadastrarDisciplina = new JButton("Cadastrar Disciplina");
        JButton botaoListarDisciplinas = new JButton("Listar Disciplinas cadastradas");
        JButton botaoCriarTurmas = new JButton("Criar turma");
        JButton botaoListarTurmas = new JButton("Listar Turmas Disponíveis");
        JButton botaoListarAlunosTurmas  = new JButton("Listar alunos por turmas");
        JButton botaoVoltar = new JButton("Voltar ao Menu Principal");

        botaoCadastrarDisciplina.addActionListener(e -> cadastrarDisciplina());
        botaoListarDisciplinas.addActionListener(e -> listarDisciplinas());
        botaoCriarTurmas.addActionListener(e -> criarTurma());
        botaoListarTurmas.addActionListener(e -> listarTurmas());
        botaoVoltar.addActionListener(e -> dispose());
        
        botao_painel.add(botaoCadastrarDisciplina);
        botao_painel.add(botaoListarDisciplinas);
        botao_painel.add(botaoCriarTurmas);
        botao_painel.add(botaoListarTurmas);
        botao_painel.add(botaoVoltar);

        String[] columnNames = {"Código, Nome, Professor, Horário, Vagas"};
        Object[][] data = {};

        JTable table = new JTable(data, columnNames);

        add(panel);
    }

    private void cadastrarDisciplina() {
        modoDT.cadastrarDisciplinas();
    }

    private void listarDisciplinas() {
        modoDT.listarDisciplinas(null);
    }

    private void criarTurma() {
        modoDT.cadastrarTurmas();
    }

    private void listarTurmas() {
        modoDT.listarDisciplinas(null);
    }


}
