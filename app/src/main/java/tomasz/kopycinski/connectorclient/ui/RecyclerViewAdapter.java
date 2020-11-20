package tomasz.kopycinski.connectorclient.ui;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tomasz.kopycinski.connectorclient.R;
import tomasz.kopycinski.connectorclient.api.model.Contact;
import tomasz.kopycinski.connectorclient.ui.activity.ContactDetailsActivity;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    private List<Contact> contacts;
    private Context context;

    public RecyclerViewAdapter(List<Contact> contacts, Context context) {
        this.contacts = contacts;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");
        String full_name = contacts.get(position).getFirst_name() + " " + contacts.get(position).getLast_name();
        holder.textView.setText(full_name);
        holder.constraintLayout.setOnClickListener(v -> {
            Intent intent = new Intent(context, ContactDetailsActivity.class);
            intent.putExtra(ContactDetailsActivity.CONTACT_ID, contacts.get(position).getId());
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            Toast.makeText(context, "Test", Toast.LENGTH_LONG).show();
        });
    }

    @Override
    public int getItemCount() {
        if (contacts == null){
            return 0;
        }else {
            return contacts.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        ConstraintLayout constraintLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.contact_name);
            constraintLayout = itemView.findViewById(R.id.item_parent);
        }
    }
}
