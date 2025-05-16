import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Disciplina {
    protected String nome;
    private String codigo;
    private int cargaHoraria;
    private List<String> preRequisitos;

    public Disciplina(String nome, String codigo, int cargaHoraria, List<String> preRequisitos) {
        this.nome = nome;
        this.codigo = codigo;
        this.cargaHoraria = cargaHoraria;
        this.preRequisitos = preRequisitos;
    }

    public String getNome() { return nome; }
    public String getCodigo() {return codigo; }
    public int getCargaHoraria() {return cargaHoraria; }
    public List<String> getPreRequisitos() {return preRequisitos; }


}





