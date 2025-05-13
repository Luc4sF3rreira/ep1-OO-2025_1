public class Turmas {
    private String nome;
    private String codigo;
    private String professor;
    private String horario;
    private String sala;

    public Turmas(String nome, String codigo, String professor, String horario, String sala) {
        this.nome = nome;
        this.codigo = codigo;
        this.professor = professor;
        this.horario = horario;
        this.sala = sala;
    }

    public String getNome() {return nome; }
    public String getCodigo() {return codigo; }
    public String getProfessor() {return professor; }
    public String getHorario() {return horario; }
    public String getSala() {return sala;}
    public void setNome(String nome) {this.nome = nome; }
    public void setCodigo(String codigo) {this.codigo = codigo; }
    public void setProfessor(String professor) {this.professor = professor; }
    public void setHorario(String horario) {this.horario = horario; }
    public void setSala(String sala) {this.sala = sala; }
}
