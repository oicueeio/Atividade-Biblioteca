package flores.michelle.atv;


import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DAO {
    private static DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    public static RecyclerView.Adapter subscribedAdapter = null;
    public static List<Livro> books = new ArrayList<Livro>();

    public static void addBook(@NotNull Livro book) {
        mDatabase.child("books").child(book.nome).setValue(book);
    }

    public static void updateBook(@NotNull Livro book) {
        mDatabase.child("books").child(book.nome).updateChildren(book.toMap());
    }

    public static void deleteBook(@NotNull Livro book) {
        mDatabase.child("books").child(book.nome).removeValue();
    }


    public static void getBooks() {
        mDatabase.child("books").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                books = new ArrayList<>();

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Livro book = child.getValue(Livro.class);
                    books.add(book);
                }

                subscribe();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("Error", "loadPost:onCancelled", databaseError.toException());
            }
        });
    }

    public static void subscribe() {
        subscribedAdapter.notifyDataSetChanged();
    }
}
