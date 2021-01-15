package hfad.com.balancednutritionorganizer.adapters;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import hfad.com.balancednutritionorganizer.AdvancedInformationAboutProductActivity;
import hfad.com.balancednutritionorganizer.R;
import hfad.com.balancednutritionorganizer.ReturnItem;
import hfad.com.balancednutritionorganizer.database_things.CustomProductsColumns;

public class RecyclerViewCustomProductAdapter extends RecyclerView.Adapter<RecyclerViewCustomProductAdapter.RecyclerViewCustomProductViewHolder> implements Filterable {

    private ArrayList<ReturnItem> customProductArrayList;
    private ArrayList<ReturnItem> customProductArrayListFull;
    private Context mContext;
    private Cursor mCursor;
    Boolean whatToReturn = false;

    public RecyclerViewCustomProductAdapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;

        customProductArrayList = new ArrayList<>();

        while (mCursor.moveToNext()) {
            customProductArrayList.add(new ReturnItem(mCursor.getString(1), mCursor.getString(2),
                    mCursor.getString(3), mCursor.getString(4),
                    mCursor.getString(5), mCursor.getString(6),
                    mCursor.getString(7), mCursor.getString(8)));
        }
        customProductArrayListFull = new ArrayList<>(customProductArrayList);
    }

    public class RecyclerViewCustomProductViewHolder extends RecyclerView.ViewHolder {

        CircleImageView productImage;
        TextView productName, productCalories, productCarbohydrates, productSugar, productFats,
                productSaturatedFats, productProtein;
        RelativeLayout parentLayout;

        public RecyclerViewCustomProductViewHolder(@NonNull View itemView) {
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

    @NonNull
    @Override
    public RecyclerViewCustomProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.scheme_specific_product_list, parent, false);
        return new RecyclerViewCustomProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewCustomProductViewHolder holder, final int position) {


        final ReturnItem currentItem = customProductArrayList.get(position);

        if (!mCursor.moveToPosition(position)) {
            return;
        }
        long id = mCursor.getLong(mCursor.getColumnIndex(CustomProductsColumns.CustomProductsColumnsEntry._ID));

        holder.itemView.setTag(id);

        holder.productName.setText(position + 1 + ".  " + currentItem.getProductName());

        Glide.with(mContext)
                .asBitmap()
                .load(currentItem.getProductImage())
                .into(holder.productImage);

        holder.productCalories.setText(currentItem.getProductCalories());
        holder.productCarbohydrates.setText(currentItem.getProductCarbohydrates());
        holder.productSugar.setText(currentItem.getProductSugar());
        holder.productFats.setText(currentItem.getProductFats());
        holder.productSaturatedFats.setText(currentItem.getProductSaturatedFats());
        holder.productProtein.setText(currentItem.getProductProtein());

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

        if (whatToReturn == true){
            return customProductArrayList.size();
        } else {
            return mCursor.getCount();
        }
    }

    public void swapCursor(Cursor newCursor) {
        whatToReturn = false;
        if (mCursor != null) {
            mCursor.close();
        }

        mCursor = newCursor;

        if (newCursor != null) {
            notifyDataSetChanged();
        }
    }

    public void filterList(ArrayList<ReturnItem> filteredList) {
        customProductArrayList = filteredList;
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }
    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<ReturnItem> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(customProductArrayListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (ReturnItem item : customProductArrayListFull) {
                    if (item.getProductName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            customProductArrayList.clear();
            customProductArrayList.addAll((ArrayList) results.values);
            whatToReturn = true;
            notifyDataSetChanged();
        }
    };
}
