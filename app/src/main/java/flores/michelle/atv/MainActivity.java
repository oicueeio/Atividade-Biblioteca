package flores.michelle.atv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LivroAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button add = findViewById(R.id.criarlivro);

        final Intent book = new Intent(this,Formulario.class);

        DAO.getBooks();

        add.setOnClickListener(view -> {
            startActivityForResult(book, RESULT_CANCELED);
        });

        recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new LivroAdapter();
        mAdapter.setContext(this);
        recyclerView.setAdapter(mAdapter);

        DAO.subscribedAdapter = mAdapter;
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mAdapter.notifyDataSetChanged();
    }
}