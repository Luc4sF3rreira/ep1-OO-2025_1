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


}





