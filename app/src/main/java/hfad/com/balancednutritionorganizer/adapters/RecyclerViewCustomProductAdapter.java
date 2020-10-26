package hfad.com.balancednutritionorganizer.adapters;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import hfad.com.balancednutritionorganizer.AdvancedInformationAboutProductActivity;
import hfad.com.balancednutritionorganizer.BottomSheetDialog;
import hfad.com.balancednutritionorganizer.ComposedMealsActivity;
import hfad.com.balancednutritionorganizer.R;
import hfad.com.balancednutritionorganizer.ReturnItem;
import hfad.com.balancednutritionorganizer.database_things.ComposeMealColumns;
import hfad.com.balancednutritionorganizer.database_things.ComposedMealsColumns;
import hfad.com.balancednutritionorganizer.database_things.CustomProductsColumns;

public class RecyclerViewCustomProductAdapter extends RecyclerView.Adapter<RecyclerViewCustomProductAdapter.RecyclerViewCustomProductViewHolder> {

    private Context mContext;
    private Cursor mCursor;
    String productName, productCalories, productCarbohydrates, productSugar, productFats, productSaturatedFats, productProtein, productImage;

    public RecyclerViewCustomProductAdapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
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
            if (!mCursor.moveToPosition(position)) {
                return;
            }

            long id = mCursor.getLong(mCursor.getColumnIndex(CustomProductsColumns.CustomProductsColumnsEntry._ID));

            productName = mCursor.getString(mCursor.getColumnIndex(CustomProductsColumns.CustomProductsColumnsEntry.COLUMN_productName));

             productCalories = mCursor.getString(mCursor.getColumnIndex(CustomProductsColumns.CustomProductsColumnsEntry.COLUMN_productCalories));
            productCarbohydrates = mCursor.getString(mCursor.getColumnIndex(CustomProductsColumns.CustomProductsColumnsEntry.COLUMN_productCarbohydrates));
            productSugar = mCursor.getString(mCursor.getColumnIndex(CustomProductsColumns.CustomProductsColumnsEntry.COLUMN_productSugar));
            productFats = mCursor.getString(mCursor.getColumnIndex(CustomProductsColumns.CustomProductsColumnsEntry.COLUMN_productFats));
            productSaturatedFats = mCursor.getString(mCursor.getColumnIndex(CustomProductsColumns.CustomProductsColumnsEntry.COLUMN_productSaturatedFats));
            productProtein = mCursor.getString(mCursor.getColumnIndex(CustomProductsColumns.CustomProductsColumnsEntry.COLUMN_productProtein));
            productImage = mCursor.getString(mCursor.getColumnIndex(CustomProductsColumns.CustomProductsColumnsEntry.COLUMN_productImage));

            holder.itemView.setTag(id);

            holder.productName.setText(position + 1 + ".  " + productName);
            holder.productCalories.setText(productCalories);

            Glide.with(mContext)
                    .asBitmap()
                    .load(productImage)
                    .into(holder.productImage);

            holder.productCarbohydrates.setText(productCarbohydrates);
            holder.productSugar.setText(productSugar);
            holder.productFats.setText(productFats);
            holder.productSaturatedFats.setText(productSaturatedFats);
            holder.productProtein.setText(productProtein);

            holder.parentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, AdvancedInformationAboutProductActivity.class);
                    intent.putExtra("product_name", productName);
                    intent.putExtra("product_calories", productCalories);
                    //intent.putExtra("product_image", mProductImages.get(position));
                    intent.putExtra("product_carbohydrates", productCarbohydrates);
                    intent.putExtra("product_sugar", productSugar);
                    intent.putExtra("product_fats", productFats);
                    intent.putExtra("product_saturatedfats", productSaturatedFats);
                    intent.putExtra("product_protein", productProtein);
                    mContext.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mCursor.getCount();
        }

        public void swapCursor(Cursor newCursor) {
            if (mCursor != null) {
                mCursor.close();
            }

            mCursor = newCursor;

            if (newCursor != null) {
                notifyDataSetChanged();
            }
        }
    }
