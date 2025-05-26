import java.util.List;

public class Disciplina {
    private String nome;
    private String codigo;
    private Integer cargaHoraria;
    private List<String> preRequisitos;

    public Disciplina(String nome, String codigo, Integer cargaHoraria, List<String> preRequisitos) {
        this.nome = nome;
        this.codigo = codigo;
        this.cargaHoraria = cargaHoraria;
        this.preRequisitos = preRequisitos;
    }

    public String getNome() { return nome; }
    public String getCodigo() {return codigo; }
    public Integer getCargaHoraria() {return cargaHoraria; }
    public List<String> getPreRequisitos() {return preRequisitos; }
    public void setNome(String nome) { this.nome = nome; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public void setCargaHoraria(Integer cargaHoraria) { this.cargaHoraria = cargaHoraria; }
    public void setPreRequisitos(List<String> preRequisitos) { this.preRequisitos = preRequisitos; }

    public void adicionarPreRequisito(String codigoDisciplina) {
        if (!preRequisitos.contains(codigoDisciplina)) {
            preRequisitos.add(codigoDisciplina);
        } else {
            System.out.println("Pré-requisito já existe.");
        }
    }

}





