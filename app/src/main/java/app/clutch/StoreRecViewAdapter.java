package app.clutch;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class StoreRecViewAdapter extends RecyclerView.Adapter<StoreRecViewAdapter.ViewHolder> {

    private ArrayList<StoreItem> storeItems = new ArrayList<>();
    private Context context;


    public StoreRecViewAdapter (Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_item_list,parent,false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.txtItemPrice.setText(storeItems.get(position).getPrice());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, StoreItemActivity.class).putExtra("itemID",holder.txtItemTitle.getText()));
                Toast.makeText(context, storeItems.get(position).getImageUrl() + " seelected", Toast.LENGTH_SHORT).show();
            }
        });

        if (position == 0) {
            holder.txtItemTitle.setTextSize(23);
            holder.txtItemPrice.setTextSize(20);
        }
        else {
            holder.txtItemTitle.setTextSize(15);
            holder.txtItemPrice.setTextSize(13);
        }


        Glide.with(context)
                .asBitmap()
                .load(storeItems.get(position).getImageUrl())
                .into(holder.image);



    }

    @Override
    public int getItemCount() {
        return storeItems.size();
    }


    public void setCollectibles(ArrayList<StoreItem> storeItems) {
        this.storeItems = storeItems;
        notifyDataSetChanged();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtItemPrice, txtItemTitle;
        private CardView parent;
        private ImageView image;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtItemTitle = itemView.findViewById(R.id.txtItemTitle);
            txtItemPrice = itemView.findViewById(R.id.txtItemPrice);
            parent = itemView.findViewById(R.id.parent);
            image = itemView.findViewById(R.id.image);

        }
    }
}
