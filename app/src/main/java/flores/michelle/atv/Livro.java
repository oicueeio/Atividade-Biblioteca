package flores.michelle.atv;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Livro {

    public String nome;
    public String title;
    public String autores;
    public String status;
    public String quantidade;

    public Livro() {

    }

    public Livro(String nome, String title, String quantidade, String autores, String status) {
        this.nome = nome;
        this.title = title;
        this.quantidade = quantidade;
        this.autores = autores;
        this.status = status;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("nome", nome);
        result.put("title", title);
        result.put("quantidade", quantidade);
        result.put("autores", autores);
        result.put("status", status);

        return result;
    }
}