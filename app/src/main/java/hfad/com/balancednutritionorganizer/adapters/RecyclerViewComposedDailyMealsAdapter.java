package hfad.com.balancednutritionorganizer.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import hfad.com.balancednutritionorganizer.R;
import hfad.com.balancednutritionorganizer.ReturnItemComposedDailyMeals;
import hfad.com.balancednutritionorganizer.ReturnItemComposedMeals;
import hfad.com.balancednutritionorganizer.database_things.ComposedDailyMealsColumns;

public class RecyclerViewComposedDailyMealsAdapter extends RecyclerView.Adapter<RecyclerViewComposedDailyMealsAdapter.RecyclerViewComposedDailyMealsViewHolder> implements Filterable {

    private ArrayList<ReturnItemComposedDailyMeals> arrayListNameDailyMealForSearch;
    private ArrayList<ReturnItemComposedDailyMeals> arrayListNameDailyMealForSearchFull;

    private Context mContext;
    private Cursor cursor;
    private ArrayList<String> arrayListForTable = new ArrayList<>();
    Boolean whatToReturn = false;

    public RecyclerViewComposedDailyMealsAdapter(Context context, Cursor cursor) {
        mContext = context;
        this.cursor = cursor;

        arrayListNameDailyMealForSearch = new ArrayList<>();

        while (cursor.moveToNext()) {
            arrayListNameDailyMealForSearch.add(new ReturnItemComposedDailyMeals(cursor.getString(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6),
                    cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10),
                    cursor.getString(11), cursor.getString(12), cursor.getString(13), cursor.getString(14),
                    cursor.getString(15), cursor.getString(16), cursor.getString(17), cursor.getString(18),
                    cursor.getString(19), cursor.getString(20), cursor.getString(21), cursor.getString(22),
                    cursor.getString(23), cursor.getString(24), cursor.getString(25), cursor.getString(26),
                    cursor.getString(27), cursor.getString(28), cursor.getString(29), cursor.getString(30),
                    cursor.getString(31), cursor.getString(32), cursor.getString(33), cursor.getString(34),
                    cursor.getString(35), cursor.getString(36), cursor.getString(37), cursor.getString(38),
                    cursor.getString(39), cursor.getString(40), cursor.getString(41), cursor.getString(42),
                    cursor.getString(43), cursor.getString(44), cursor.getString(45), cursor.getString(46),
                    cursor.getString(47), cursor.getString(48), cursor.getString(49), cursor.getString(50),
                    cursor.getString(51), cursor.getString(52), cursor.getString(53)));

        }
        arrayListNameDailyMealForSearchFull = new ArrayList<>(arrayListNameDailyMealForSearch);
    }

    public class RecyclerViewComposedDailyMealsViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewComposedDailyMealsName, textViewComposedDailyMeals_KCAL_WEIGHT_SUM, textViewComposedDailyMeals_CARBO_SUGAR_SUM,
                textViewComposedDailyMeals_FATS_sFATS_SUM, textViewComposedDailyMeals_PROTEIN_SUM, textViewComposedDailyMealsName_FirstProduct,
                textViewComposedDailyMeals_KCAL_WEIGHT_FirstProduct, textViewComposedDailyMeals_CARBO_SUGAR_FirstProduct,
                textViewComposedDailyMeals_FATS_sFats_FirstProduct, textViewComposedDailyMeals_PROTEIN_FirstProduct, textViewComposedDailyMealsName_SecondProduct,
                textViewComposedDailyMeals_KCAL_WEIGHT_SecondProduct, textViewComposedDailyMeals_CARBO_SUGAR_SecondProduct,
                textViewComposedDailyMeals_FATS_sFats_SecondProduct, textViewComposedDailyMeals_PROTEIN_SecondProduct, textViewComposedDailyMealsName_ThirdProduct,
                textViewComposedDailyMeals_KCAL_WEIGHT_ThirdProduct, textViewComposedDailyMeals_CARBO_SUGAR_ThirdProduct, textViewComposedDailyMeals_FATS_sFats_ThirdProduct,
                textViewComposedDailyMeals_PROTEIN_ThirdProduct, textViewComposedDailyMealsName_FourthProduct, textViewComposedDailyMeals_KCAL_WEIGHT_FourthProduct,
                textViewComposedDailyMeals_CARBO_SUGAR_FourthProduct, textViewComposedDailyMeals_FATS_sFats_FourthProduct, textViewComposedDailyMeals_PROTEIN_FourthProduct,
                textViewComposedDailyMealsName_FifthProduct, textViewComposedDailyMeals_KCAL_WEIGHT_FifthProduct, textViewComposedDailyMeals_CARBO_SUGAR_FifthProduct,
                textViewComposedDailyMeals_FATS_sFats_FifthProduct, textViewComposedDailyMeals_PROTEIN_FifthProduct, textViewProductsIncludedComposedDailyMeal_FirstProduct,
                textViewProductsIncludedComposedDailyMeal_SecondProduct,textViewProductsIncludedComposedDailyMeal_ThirdProduct,textViewProductsIncludedComposedDailyMeal_FourthProduct,
                textViewProductsIncludedComposedDailyMeal_FifthProduct;

        CardView parentLayout;

        public RecyclerViewComposedDailyMealsViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewComposedDailyMealsName = itemView.findViewById(R.id.textViewComposedDailyMealsName);
            textViewComposedDailyMeals_KCAL_WEIGHT_SUM = itemView.findViewById(R.id.textViewComposedDailyMeals_KCAL_WEIGHT_SUM);
            textViewComposedDailyMeals_CARBO_SUGAR_SUM = itemView.findViewById(R.id.textViewComposedDailyMeals_CARBO_SUGAR_SUM);
            textViewComposedDailyMeals_FATS_sFATS_SUM = itemView.findViewById(R.id.textViewComposedDailyMeals_FATS_sFATS_SUM);
            textViewComposedDailyMeals_PROTEIN_SUM = itemView.findViewById(R.id.textViewComposedDailyMeals_PROTEIN_SUM);

            textViewComposedDailyMealsName_FirstProduct = itemView.findViewById(R.id.textViewComposedDailyMealsName_FirstProduct);
            textViewComposedDailyMeals_KCAL_WEIGHT_FirstProduct = itemView.findViewById(R.id.textViewComposedDailyMeals_KCAL_WEIGHT_FirstProduct);
            textViewComposedDailyMeals_CARBO_SUGAR_FirstProduct = itemView.findViewById(R.id.textViewComposedDailyMeals_CARBO_SUGAR_FirstProduct);
            textViewComposedDailyMeals_FATS_sFats_FirstProduct = itemView.findViewById(R.id.textViewComposedDailyMeals_FATS_sFats_FirstProduct);
            textViewComposedDailyMeals_PROTEIN_FirstProduct = itemView.findViewById(R.id.textViewComposedDailyMeals_PROTEIN_FirstProduct);

            textViewComposedDailyMealsName_SecondProduct = itemView.findViewById(R.id.textViewComposedDailyMealsName_SecondProduct);
            textViewComposedDailyMeals_KCAL_WEIGHT_SecondProduct = itemView.findViewById(R.id.textViewComposedDailyMeals_KCAL_WEIGHT_SecondProduct);
            textViewComposedDailyMeals_CARBO_SUGAR_SecondProduct = itemView.findViewById(R.id.textViewComposedDailyMeals_CARBO_SUGAR_SecondProduct);
            textViewComposedDailyMeals_FATS_sFats_SecondProduct = itemView.findViewById(R.id.textViewComposedDailyMeals_FATS_sFats_SecondProduct);
            textViewComposedDailyMeals_PROTEIN_SecondProduct = itemView.findViewById(R.id.textViewComposedDailyMeals_PROTEIN_SecondProduct);

            textViewComposedDailyMealsName_ThirdProduct = itemView.findViewById(R.id.textViewComposedDailyMealsName_ThirdProduct);
            textViewComposedDailyMeals_KCAL_WEIGHT_ThirdProduct = itemView.findViewById(R.id.textViewComposedDailyMeals_KCAL_WEIGHT_ThirdProduct);
            textViewComposedDailyMeals_CARBO_SUGAR_ThirdProduct = itemView.findViewById(R.id.textViewComposedDailyMeals_CARBO_SUGAR_ThirdProduct);
            textViewComposedDailyMeals_FATS_sFats_ThirdProduct = itemView.findViewById(R.id.textViewComposedDailyMeals_FATS_sFats_ThirdProduct);
            textViewComposedDailyMeals_PROTEIN_ThirdProduct = itemView.findViewById(R.id.textViewComposedDailyMeals_PROTEIN_ThirdProduct);

            textViewComposedDailyMealsName_FourthProduct = itemView.findViewById(R.id.textViewComposedDailyMealsName_FourthProduct);
            textViewComposedDailyMeals_KCAL_WEIGHT_FourthProduct = itemView.findViewById(R.id.textViewComposedDailyMeals_KCAL_WEIGHT_FourthProduct);
            textViewComposedDailyMeals_CARBO_SUGAR_FourthProduct = itemView.findViewById(R.id.textViewComposedDailyMeals_CARBO_SUGAR_FourthProduct);
            textViewComposedDailyMeals_FATS_sFats_FourthProduct = itemView.findViewById(R.id.textViewComposedDailyMeals_FATS_sFats_FourthProduct);
            textViewComposedDailyMeals_PROTEIN_FourthProduct = itemView.findViewById(R.id.textViewComposedDailyMeals_PROTEIN_FourthProduct);

            textViewComposedDailyMealsName_FifthProduct = itemView.findViewById(R.id.textViewComposedDailyMealsName_FifthProduct);
            textViewComposedDailyMeals_KCAL_WEIGHT_FifthProduct = itemView.findViewById(R.id.textViewComposedDailyMeals_KCAL_WEIGHT_FifthProduct);
            textViewComposedDailyMeals_CARBO_SUGAR_FifthProduct = itemView.findViewById(R.id.textViewComposedDailyMeals_CARBO_SUGAR_FifthProduct);
            textViewComposedDailyMeals_FATS_sFats_FifthProduct = itemView.findViewById(R.id.textViewComposedDailyMeals_FATS_sFats_FifthProduct);
            textViewComposedDailyMeals_PROTEIN_FifthProduct = itemView.findViewById(R.id.textViewComposedDailyMeals_PROTEIN_FifthProduct);

            textViewProductsIncludedComposedDailyMeal_FirstProduct = itemView.findViewById(R.id.textViewProductsIncludedComposedDailyMeal_FirstProduct);
            textViewProductsIncludedComposedDailyMeal_SecondProduct = itemView.findViewById(R.id.textViewProductsIncludedComposedDailyMeal_SecondProduct);
            textViewProductsIncludedComposedDailyMeal_ThirdProduct = itemView.findViewById(R.id.textViewProductsIncludedComposedDailyMeal_ThirdProduct);
            textViewProductsIncludedComposedDailyMeal_FourthProduct = itemView.findViewById(R.id.textViewProductsIncludedComposedDailyMeal_FourthProduct);
            textViewProductsIncludedComposedDailyMeal_FifthProduct = itemView.findViewById(R.id.textViewProductsIncludedComposedDailyMeal_FifthProduct);

            parentLayout = itemView.findViewById(R.id.scheme_composed_daily_meals);
        }
    }

    @NonNull
    @Override
    public RecyclerViewComposedDailyMealsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.scheme_composed_daily_meals, parent, false);
        return new RecyclerViewComposedDailyMealsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewComposedDailyMealsViewHolder holder, final int position) {

        final ReturnItemComposedDailyMeals currentItem = arrayListNameDailyMealForSearch.get(position);

        if (!cursor.moveToPosition(position)) {
            return;
        }
        long id = cursor.getLong(cursor.getColumnIndex(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry._ID));
        holder.itemView.setTag(id);

        holder.textViewComposedDailyMealsName.setText(position + 1 + ".  " + currentItem.getComposedDailyMeals_Name());
        holder.textViewComposedDailyMeals_KCAL_WEIGHT_SUM.setText("KCAL "+currentItem.getComposedDailyMeals_KCAL_SUM()+"/"+currentItem.getComposedDailyMeals_WEIGHT_SUM()+"G");
        holder.textViewComposedDailyMeals_CARBO_SUGAR_SUM.setText("carbo "+currentItem.getComposedDailyMeals_CARBOHYDRATES_SUM()+"G | sugar "+currentItem.getComposedDailyMeals_SUGAR_SUM()+"G");
        holder.textViewComposedDailyMeals_FATS_sFATS_SUM.setText("fats "+currentItem.getComposedDailyMeals_FATS_SUM()+"G | s. fats "+currentItem.getComposedDailyMeals_saturatedFATS_SUM()+"G");
        holder.textViewComposedDailyMeals_PROTEIN_SUM.setText("protein "+currentItem.getComposedDailyMeals_PROTEIN_SUM()+"G");

        holder.textViewComposedDailyMealsName_FirstProduct.setText(currentItem.getFirstProduct_NAME());
        holder.textViewComposedDailyMeals_KCAL_WEIGHT_FirstProduct.setText("KCAL "+currentItem.getFirstProduct_KCAL()+"/"+currentItem.getFirstProduct_WEIGHT()+"G");
        holder.textViewComposedDailyMeals_CARBO_SUGAR_FirstProduct.setText("carbo "+currentItem.getFirstProduct_CARBOHYDRATES()+"G | sugar "+currentItem.getFirstProduct_SUGAR()+"G");
        holder.textViewComposedDailyMeals_FATS_sFats_FirstProduct.setText("fats "+currentItem.getFirstProduct_FATS()+"G | s. fats "+currentItem.getFirstProduct_saturatedFATS()+"G");
        holder.textViewComposedDailyMeals_PROTEIN_FirstProduct.setText("protein "+currentItem.getFirstProduct_PROTEIN()+"G");

        holder.textViewComposedDailyMealsName_SecondProduct.setText(currentItem.getSecondProduct_NAME());
        holder.textViewComposedDailyMeals_KCAL_WEIGHT_SecondProduct.setText("KCAL "+currentItem.getSecondProduct_KCAL()+"/"+currentItem.getSecondProduct_WEIGHT()+"G");
        holder.textViewComposedDailyMeals_CARBO_SUGAR_SecondProduct.setText("carbo "+currentItem.getSecondProduct_CARBOHYDRATES()+"G | sugar "+currentItem.getSecondProduct_SUGAR()+"G");
        holder.textViewComposedDailyMeals_FATS_sFats_SecondProduct.setText("fats "+currentItem.getSecondProduct_FATS()+"G | s. fats "+currentItem.getSecondProduct_saturatedFATS()+"G");
        holder.textViewComposedDailyMeals_PROTEIN_SecondProduct.setText("protein "+currentItem.getSecondProduct_PROTEIN()+"G");

        holder.textViewComposedDailyMealsName_ThirdProduct.setText(currentItem.getThirdProduct_NAME());
        holder.textViewComposedDailyMeals_KCAL_WEIGHT_ThirdProduct.setText("KCAL "+currentItem.getThirdProduct_KCAL()+"/"+currentItem.getThirdProduct_WEIGHT()+"G");
        holder.textViewComposedDailyMeals_CARBO_SUGAR_ThirdProduct.setText("carbo "+currentItem.getThirdProduct_CARBOHYDRATES()+"G | sugar "+currentItem.getThirdProduct_SUGAR()+"G");
        holder.textViewComposedDailyMeals_FATS_sFats_ThirdProduct.setText("fats "+currentItem.getThirdProduct_FATS()+"G | s. fats "+currentItem.getThirdProduct_saturatedFATS()+"G");
        holder.textViewComposedDailyMeals_PROTEIN_ThirdProduct.setText("protein "+currentItem.getThirdProduct_PROTEIN()+"G");

        holder.textViewComposedDailyMealsName_FourthProduct.setText(currentItem.getFourthProduct_NAME());
        holder.textViewComposedDailyMeals_KCAL_WEIGHT_FourthProduct.setText("KCAL "+currentItem.getFourthProduct_KCAL()+"/"+currentItem.getFourthProduct_WEIGHT()+"G");
        holder.textViewComposedDailyMeals_CARBO_SUGAR_FourthProduct.setText("carbo "+currentItem.getFourthProduct_CARBOHYDRATES()+"G | sugar "+currentItem.getFourthProduct_SUGAR()+"G");
        holder.textViewComposedDailyMeals_FATS_sFats_FourthProduct.setText("fats "+currentItem.getFourthProduct_FATS()+"G | s. fats "+currentItem.getFourthProduct_saturatedFATS()+"G");
        holder.textViewComposedDailyMeals_PROTEIN_FourthProduct.setText("protein "+currentItem.getFourthProduct_PROTEIN()+"G");

        holder.textViewComposedDailyMealsName_FifthProduct.setText(currentItem.getFifthProduct_NAME());
        holder.textViewComposedDailyMeals_KCAL_WEIGHT_FifthProduct.setText("KCAL "+currentItem.getFifthProduct_KCAL()+"/"+currentItem.getFifthProduct_WEIGHT()+"G");
        holder.textViewComposedDailyMeals_CARBO_SUGAR_FifthProduct.setText("carbo "+currentItem.getFifthProduct_CARBOHYDRATES()+"G | sugar "+currentItem.getFifthProduct_SUGAR()+"G");
        holder.textViewComposedDailyMeals_FATS_sFats_FifthProduct.setText("fats "+currentItem.getFifthProduct_FATS()+"G | s. fats "+currentItem.getFifthProduct_saturatedFATS()+"G");
        holder.textViewComposedDailyMeals_PROTEIN_FifthProduct.setText("protein "+currentItem.getFifthProduct_PROTEIN()+"G");

        holder.textViewProductsIncludedComposedDailyMeal_FirstProduct.setText(currentItem.getProductsIncludedFirstProduct());
        holder.textViewProductsIncludedComposedDailyMeal_SecondProduct.setText(currentItem.getProductsIncludedSecondProduct());
        holder.textViewProductsIncludedComposedDailyMeal_ThirdProduct.setText(currentItem.getProductsIncludedThirdProduct());
        holder.textViewProductsIncludedComposedDailyMeal_FourthProduct.setText(currentItem.getProductsIncludedFourthProduct());
        holder.textViewProductsIncludedComposedDailyMeal_FifthProduct.setText(currentItem.getProductsIncludedFifthProduct());

    }

    @Override
    public int getItemCount() {
        if (whatToReturn == true){
            return arrayListNameDailyMealForSearch.size();
        } else {
            return cursor.getCount();
        }
    }

    public void swapCursor(Cursor newCursor) {
        whatToReturn = false;
        if (this.cursor != null) {
            this.cursor.close();
        }

        this.cursor = newCursor;

        if (newCursor != null) {
            notifyDataSetChanged();
        }
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }
    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<ReturnItemComposedDailyMeals> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(arrayListNameDailyMealForSearchFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (ReturnItemComposedDailyMeals item : arrayListNameDailyMealForSearchFull) {
                    if (item.getComposedDailyMeals_Name().toLowerCase().contains(filterPattern)) {
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
            arrayListNameDailyMealForSearch.clear();
            arrayListNameDailyMealForSearch.addAll((ArrayList) results.values);
            whatToReturn = true;
            notifyDataSetChanged();
        }
    };
}