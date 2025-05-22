import javax.swing.JFrame;

public class SistemaAcademico {
    public static void main(String[] args) {
        JanelaPrincipal janelaPrincipal = new JanelaPrincipal();
        janelaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janelaPrincipal.setVisible(true);
        janelaPrincipal.setResizable(false);
        janelaPrincipal.setLocationRelativeTo(null);
        janelaPrincipal.setTitle("Sistema Acadêmico FCTE");
        janelaPrincipal.setSize(400,600);
        janelaPrincipal.setLocationRelativeTo(null);
    }
}
