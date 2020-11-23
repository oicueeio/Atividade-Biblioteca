package flores.michelle.atv;


import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class LivroHolder extends RecyclerView.ViewHolder {
    public TextView nome;
    public TextView title;
    public TextView qatd;
    public TextView autores;
    public TextView status_livros;
    public Button edit;
    public Button delete;

    public LivroHolder(View itemView) {
        super(itemView);
        this.nome = itemView.findViewById(R.id.nome);
        this.title = itemView.findViewById(R.id.title);
        this.qatd = itemView.findViewById(R.id.qatd);
        this.autores = itemView.findViewById(R.id.autores);
        this.status_livros = itemView.findViewById(R.id.status_livros);
        this.edit = itemView.findViewById(R.id.edit);
        this.delete = itemView.findViewById(R.id.delete);
    }
}