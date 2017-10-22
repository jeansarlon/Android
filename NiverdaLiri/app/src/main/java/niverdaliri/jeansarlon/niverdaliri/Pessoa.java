package niverdaliri.jeansarlon.niverdaliri;

/**
 * Created by jeansarlon on 25/09/16.
 */

public class Pessoa {
    private String nome;
    private String mesa;
    private boolean isChecked;

    public Pessoa(String nome, String mesa){
        this.mesa = mesa;
        this.nome = nome;
        this.isChecked = false;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMesa() {
        return this.mesa;
    }

    public void setMesa(String mesa) {
        this.mesa = mesa;
    }

    public boolean isChecked() {
        return this.isChecked;
    }

    public void setChecked(boolean checked) {
        this.isChecked = checked;
    }
}
