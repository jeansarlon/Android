package aula5.jeansarlon.listascomplexas;

/**
 * Created by jeansarlon on 26/04/16.
 */
public class Pessoa {
    public String nome;
    public String email;

    public Pessoa(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Pessoa{" +

                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
