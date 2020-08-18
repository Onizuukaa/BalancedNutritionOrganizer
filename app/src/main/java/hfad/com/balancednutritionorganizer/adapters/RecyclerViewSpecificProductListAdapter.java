package hfad.com.balancednutritionorganizer.adapters;

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
import hfad.com.balancednutritionorganizer.AdvancedInformationAboutProductActivity;
import hfad.com.balancednutritionorganizer.ReturnItem;
import hfad.com.balancednutritionorganizer.R;

public class RecyclerViewSpecificProductListAdapter extends RecyclerView.Adapter<RecyclerViewSpecificProductListAdapter.ViewHolder> {

    private ArrayList<ReturnItem> mExampleList;
    private Context mContext;

    public RecyclerViewSpecificProductListAdapter(Context mContext, ArrayList<ReturnItem> exampleList) {
        this.mExampleList = exampleList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.scheme_specific_product_list, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final ReturnItem currentItem = mExampleList.get(position);

        Glide.with(mContext)
                .asBitmap()
                .load(currentItem.getProductImage())
                .into(holder.productImage);

        holder.productName.setText(currentItem.getProductName());
        holder.productCalories.setText(currentItem.getProductCalories());
        holder.productCarbohydrates.setText(currentItem.getProductCarbohydrates());
        holder.productSugar.setText(currentItem.getProductSugar());
        holder.productFats.setText(currentItem.getProductFats());
        holder.productSaturatedFats.setText(currentItem.getProductSaturatedFats());
        holder.productProtein.setText(currentItem.getProductProtein());

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // To działa
                System.out.println("WYNIK " + currentItem.getProductName());

                Intent intent = new Intent(mContext, AdvancedInformationAboutProductActivity.class);
                intent.putExtra("product_name", currentItem.getProductName());
                intent.putExtra("product_calories", currentItem.getProductCalories());
                //intent.putExtra("product_image", mProductImages.get(position));
                intent.putExtra("product_carbohydrates", currentItem.getProductCarbohydrates());
                intent.putExtra("product_sugar", currentItem.getProductSugar());
                intent.putExtra("product_fats", currentItem.getProductFats());
                intent.putExtra("product_saturatedfats", currentItem.getProductSaturatedFats());
                intent.putExtra("product_protein", currentItem.getProductProtein());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
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
            parentLayout = itemView.findViewById(R.id.schemeSpecificProductList);
        }
    }
    public void filterList(ArrayList<ReturnItem> filteredList) {
        mExampleList = filteredList;
        notifyDataSetChanged();
    }
}
