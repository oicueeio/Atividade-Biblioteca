package flores.michelle.atv;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

public class LivroAdapter extends RecyclerView.Adapter<LivroHolder> {

    private FragmentActivity context;

    @NonNull
    @Override

    public LivroHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LivroHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_livro, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LivroHolder holder, final int position) {

        holder.nome.setText(DAO.books.get(position).nome);
        holder.title.setText(DAO.books.get(position).title);
        holder.autores.setText(DAO.books.get(position).autores);
        holder.qatd.setText(DAO.books.get(position).quantidade);
        holder.status_livros.setText(DAO.books.get(position).status);
        holder.edit.setOnClickListener((view) -> {
            editBook(position);
        });
        holder.delete.setOnClickListener(view -> {
            deleteBook(position);
        });
    }
    @Override

    public int getItemCount() {
        return DAO.books.size();
    }

    private void editBook(int position) {
        final Intent book = new Intent(context, Formulario.class);
        book.putExtra("position", position);
        context.startActivity(book);
    }

    private void deleteBook(int position) {
        try {
            Livro book = DAO.books.get(position);

            DAO.deleteBook(book);

            Toast toast = Toast.makeText(context, "Livro removido com sucesso", Toast.LENGTH_LONG);

            toast.show();
        } catch (Exception e) {
            Toast toast = Toast.makeText(context, "Erro ao remover livro", Toast.LENGTH_LONG);

            toast.show();
        }
    }

    public void setContext(FragmentActivity context) {
        this.context = context;
    }
}