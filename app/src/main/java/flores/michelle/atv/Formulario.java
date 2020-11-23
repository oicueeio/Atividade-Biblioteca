package flores.michelle.atv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Formulario extends AppCompatActivity {

    TextView nome_livro= null;
    TextView title_livro = null;
    TextView qtd_livro = null;
    TextView autores_livro= null;
    Spinner status_livro = null;
    ArrayList<String> statuses = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        Intent intent = getIntent();

        final Integer bookId = ((Intent) intent).getIntExtra("position", -1);
        nome_livro = findViewById(R.id.nome_camp);
        title_livro = findViewById(R.id.title_camp);
        qtd_livro = findViewById(R.id.qts_camp);
        autores_livro = findViewById(R.id.autores_camp);
        status_livro = findViewById(R.id.status_camp);

        if (bookId > -1) {
            nome_livro.setEnabled(false);
        }

        statuses = new ArrayList<String>();

        statuses.add("Não iniciado");
        statuses.add("Em leitura");
        statuses.add("Finalizado");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, statuses);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        status_livro.setAdapter(arrayAdapter);

        Button btnSave = findViewById(R.id.but_salvar);

        btnSave.setOnClickListener(view -> {
            String nome = nome_livro.getText().toString();
            String title = title_livro.getText().toString();
            String quantidade = qtd_livro.getText().toString();
            String autores = autores_livro.getText().toString();
            String status = status_livro.getSelectedItem().toString();

            Livro book = new Livro(nome, title, quantidade, autores, status);

            if (bookId > -1) {
                DAO.updateBook(book);
            } else {
                DAO.addBook(book);
            }

            Toast toast = Toast.makeText(this, "Livro adicionado/atualizado com sucesso", Toast.LENGTH_SHORT);

            toast.show();

            finish();

        });
        if (bookId > -1) {
            loadBook(bookId);
        }
    }
    private void loadBook(int position) {
        Livro book = DAO.books.get(position);

        nome_livro.setText(book.nome);
        title_livro.setText(book.title);
        qtd_livro.setText(book.quantidade);
        autores_livro.setText(book.autores);
        status_livro.setSelection(statuses.indexOf(book.status));
    }
}

