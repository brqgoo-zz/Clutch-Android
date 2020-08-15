package app.clutch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CollectiblesRecViewAdapter extends RecyclerView.Adapter<CollectiblesRecViewAdapter.ViewHolder> {

    private ArrayList<Collectible> collectibles = new ArrayList<>();
    private Context context;


    public CollectiblesRecViewAdapter (Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.collectibles_item_list,parent,false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.txtItemName.setText(collectibles.get(position).getName());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, collectibles.get(position).getImageUrl() + " seelected", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return collectibles.size();
    }


    public void setCollectibles(ArrayList<Collectible> collectibles) {
        this.collectibles = collectibles;
        notifyDataSetChanged();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtItemName;
        private RelativeLayout parent;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtItemName = itemView.findViewById(R.id.txtItemName);
            parent = itemView.findViewById(R.id.parent);
        }
    }
}
