package hfad.com.balancednutritionorganizer;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private ArrayList<String> mProductNames = new ArrayList<>();
    private ArrayList<String> mProductCalories = new ArrayList<>();
    private ArrayList<String> mProductImages = new ArrayList<>();
    private ArrayList<String> mProductCarbohydrates = new ArrayList<>();
    private ArrayList<String> mProductSugar = new ArrayList<>();
    private ArrayList<String> mProductFats = new ArrayList<>();
    private ArrayList<String> mProductSaturatedFats = new ArrayList<>();
    private ArrayList<String> mProductProtein = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(Context mContext, ArrayList<String> mProductImages, ArrayList<String> mProductNames, ArrayList<String> mProductCalories,
                               ArrayList<String> mProductCarbohydrates, ArrayList<String> mProductSugar, ArrayList<String> mProductFats,
                               ArrayList<String> mProductSaturatedFats, ArrayList<String> mProductProtein) {
        this.mProductImages = mProductImages;
        this.mProductNames = mProductNames;
        this.mProductCalories = mProductCalories;
        this.mProductCarbohydrates = mProductCarbohydrates;
        this.mProductSugar = mProductSugar;
        this.mProductFats = mProductFats;
        this.mProductSaturatedFats = mProductSaturatedFats;
        this.mProductProtein = mProductProtein;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        Glide.with(mContext)
                .asBitmap()
                .load(mProductImages.get(position))
                .into(holder.productImage);

        holder.productName.setText(mProductNames.get(position));
        holder.productCalories.setText(mProductCalories.get(position));
        holder.productCarbohydrates.setText(mProductCarbohydrates.get(position));
        holder.productSugar.setText(mProductSugar.get(position));
        holder.productFats.setText(mProductFats.get(position));
        holder.productSaturatedFats.setText(mProductSaturatedFats.get(position));
        holder.productProtein.setText(mProductProtein.get(position));

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, AdvancedInformationAboutProductActivity.class);
                intent.putExtra("product_name", mProductNames.get(position));
                intent.putExtra("product_calories", mProductCalories.get(position));
                intent.putExtra("product_image", mProductImages.get(position));
                intent.putExtra("product_carbohydrates", mProductCarbohydrates.get(position));
                intent.putExtra("product_sugar", mProductSugar.get(position));
                intent.putExtra("product_fats", mProductFats.get(position));
                intent.putExtra("product_saturatedfats", mProductSaturatedFats.get(position));
                intent.putExtra("product_protein", mProductProtein.get(position));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mProductNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView productImage;
        TextView productName, productCalories, productCarbohydrates, productSugar, productFats, productSaturatedFats, productProtein;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.imageViewProductImage);
            productName = itemView.findViewById(R.id.textViewProductName);
            productCalories = itemView.findViewById(R.id.textViewProductCaloriesFor100Gram);
            productCarbohydrates = itemView.findViewById(R.id.textViewProductCarbohydratesFor100Gram);
            productSugar = itemView.findViewById(R.id.textViewProductSugarFor100Gram);
            productFats = itemView.findViewById(R.id.textViewProductFatsFor100Gram);
            productSaturatedFats = itemView.findViewById(R.id.textViewProductSaturatedFatsFor100Gram);
            productProtein = itemView.findViewById(R.id.textViewProductProteinFor100Gram);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
