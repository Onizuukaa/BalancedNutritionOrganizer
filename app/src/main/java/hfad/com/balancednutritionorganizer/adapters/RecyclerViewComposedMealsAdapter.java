package hfad.com.balancednutritionorganizer.adapters;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hfad.com.balancednutritionorganizer.BottomSheetDialog;
import hfad.com.balancednutritionorganizer.ComposedMealsActivity;
import hfad.com.balancednutritionorganizer.R;
import hfad.com.balancednutritionorganizer.ReturnItem;
import hfad.com.balancednutritionorganizer.ReturnItemComposedMeals;
import hfad.com.balancednutritionorganizer.database_things.ComposedMealsColumns;

public class RecyclerViewComposedMealsAdapter extends RecyclerView.Adapter<RecyclerViewComposedMealsAdapter.RecyclerViewComposedMealsViewHolder> implements BottomSheetDialog.BottomSheetListener, Filterable, ComposedMealsActivity.testMetodyInterface {

    private ArrayList<String> arrayListProductIncluded = new ArrayList<>();

    private ArrayList<ReturnItemComposedMeals> arrayListNameMealForSearch;
    private ArrayList<ReturnItemComposedMeals> arrayListNameMealForSearchFull;

    private Context mContext;
    private Cursor mCursor;
    Bundle bundleWithMacros;
    public String odebraneZAktywnosci;
    Boolean whatToReturn = false;

    public RecyclerViewComposedMealsAdapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;

        arrayListNameMealForSearch = new ArrayList<>();

        while (mCursor.moveToNext()) {
            arrayListNameMealForSearch.add(new ReturnItemComposedMeals(mCursor.getString(1), mCursor.getString(2),
                    mCursor.getString(3), mCursor.getString(4),
                    mCursor.getString(5), mCursor.getString(6),
                    mCursor.getString(7), mCursor.getString(8), mCursor.getString(9)));
        }
        arrayListNameMealForSearchFull = new ArrayList<>(arrayListNameMealForSearch);
    }

    @Override
    public void onButtonClicked(String text) {

    }

    @Override
    public void testMetody(String test) {
        odebraneZAktywnosci = test;
        System.out.println(odebraneZAktywnosci + "DZIALA CZY NIE");
    }

    public class RecyclerViewComposedMealsViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewComposedMealsName, textViewComposedMealsKcal, textViewComposedMealsGram,
                textViewComposedMealsCarbohydrates, textViewComposedMealsFats, textViewComposedMealsProtein,
                textViewComposedMealsSugar, textViewComposedMealsSaturatedFats, textViewProductsIncludedComposedMeal;
        CardView parentLayout;
        CheckBox checkBox_ComposedMeals;

        public RecyclerViewComposedMealsViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewComposedMealsName = itemView.findViewById(R.id.textViewComposedMealsName);
            textViewComposedMealsKcal = itemView.findViewById(R.id.textViewComposedMealsKcal);
            textViewComposedMealsGram = itemView.findViewById(R.id.textViewComposedMealsGram);
            textViewComposedMealsCarbohydrates = itemView.findViewById(R.id.textViewComposedMealsCarbohydrates);
            textViewComposedMealsFats = itemView.findViewById(R.id.textViewComposedMealsFats);
            textViewComposedMealsProtein = itemView.findViewById(R.id.textViewComposedMealsProtein);
            textViewComposedMealsSugar = itemView.findViewById(R.id.textViewComposedMealsSugar);
            textViewComposedMealsSaturatedFats = itemView.findViewById(R.id.textViewComposedMealsSaturatedFats);
            textViewProductsIncludedComposedMeal = itemView.findViewById(R.id.textViewProductsIncludedComposedMeal);
            parentLayout = itemView.findViewById(R.id.scheme_composed_meals);
            checkBox_ComposedMeals = itemView.findViewById(R.id.checkBox_composedMeals);
        }
    }

    @NonNull
    @Override
    public RecyclerViewComposedMealsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext); //dodałem bo w GroceryAdapter, tym nowszym, tak jest.
        View view = inflater.inflate(R.layout.scheme_composed_meals, parent, false);
        return new RecyclerViewComposedMealsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewComposedMealsViewHolder holder, final int position) {

        final ReturnItemComposedMeals currentItem = arrayListNameMealForSearch.get(position);

        if (!mCursor.moveToPosition(position)) {
            return;
        }

        long id = mCursor.getLong(mCursor.getColumnIndex(ComposedMealsColumns.ComposedMealsColumnsEntry._ID));

//        String mealName = mCursor.getString(mCursor.getColumnIndex(ComposedMealsColumns.ComposedMealsColumnsEntry.COLUMN_MEALNAME));
//        String mealKcal = mCursor.getString(mCursor.getColumnIndex(ComposedMealsColumns.ComposedMealsColumnsEntry.COLUMN_CALORIES));
//        String mealGram = mCursor.getString(mCursor.getColumnIndex(ComposedMealsColumns.ComposedMealsColumnsEntry.COLUMN_WEIGHT));
//        String mealCarbohydrates = mCursor.getString(mCursor.getColumnIndex(ComposedMealsColumns.ComposedMealsColumnsEntry.COLUMN_CARBO));
//        String mealFats = mCursor.getString(mCursor.getColumnIndex(ComposedMealsColumns.ComposedMealsColumnsEntry.COLUMN_FATS));
//        String mealProtein = mCursor.getString(mCursor.getColumnIndex(ComposedMealsColumns.ComposedMealsColumnsEntry.COLUMN_PROTEIN));
//        String mealSugar = mCursor.getString(mCursor.getColumnIndex(ComposedMealsColumns.ComposedMealsColumnsEntry.COLUMN_SUGAR));
//        String mealSaturatedFats = mCursor.getString(mCursor.getColumnIndex(ComposedMealsColumns.ComposedMealsColumnsEntry.COLUMN_SATURATEDFATS));
//        String productsIncludedComposedMeal = mCursor.getString(mCursor.getColumnIndex(ComposedMealsColumns.ComposedMealsColumnsEntry.COLUMN_PRODUCTSINCLUDED));

        holder.itemView.setTag(id);

//        holder.textViewComposedMealsName.setText(position + 1 + ".  " + mealName);
//        holder.textViewComposedMealsKcal.setText(mealKcal + "\ncalories");
//        holder.textViewComposedMealsGram.setText(mealGram + "g\nweight");
//        holder.textViewComposedMealsCarbohydrates.setText(mealCarbohydrates + "g\ncarbohydrates");
//        holder.textViewComposedMealsFats.setText(mealFats + "g\nfats");
//        holder.textViewComposedMealsProtein.setText(mealProtein + "g\nprotein");
//        holder.textViewComposedMealsSugar.setText(mealSugar + "g\nsugar");
//        holder.textViewComposedMealsSaturatedFats.setText(mealSaturatedFats + "g\nsaturated fats");
//        holder.textViewProductsIncludedComposedMeal.setText(productsIncludedComposedMeal + "");

        holder.textViewComposedMealsName.setText(position + 1 + ".  " + currentItem.getProductName());
        holder.textViewComposedMealsKcal.setText(currentItem.getProductCalories() + "\ncalories");
        holder.textViewComposedMealsGram.setText(currentItem.getProductWeight() + "g\nweight");
        holder.textViewComposedMealsCarbohydrates.setText(currentItem.getProductCarbohydrates() + "g\ncarbohydrates");
        holder.textViewComposedMealsFats.setText(currentItem.getProductFats() + "g\nfats");
        holder.textViewComposedMealsProtein.setText(currentItem.getProductProtein() + "g\nprotein");
        holder.textViewComposedMealsSugar.setText(currentItem.getProductSugar() + "g\nsugar");
        holder.textViewComposedMealsSaturatedFats.setText(currentItem.getProductSaturatedFats() + "g\nsaturated fats");
        holder.textViewProductsIncludedComposedMeal.setText(currentItem.getProductMacros() + "");

//        arrayListNameMealForSearch_BeforeFilter.add(mealName);
//        arrayListNameMealForSearch_BeforeFilter.add(mealKcal);
//        arrayListNameMealForSearch_BeforeFilter.add(mealGram);
//        arrayListNameMealForSearch_BeforeFilter.add(mealCarbohydrates);
//        arrayListNameMealForSearch_BeforeFilter.add(mealFats);
//        arrayListNameMealForSearch_BeforeFilter.add(mealProtein);
//        arrayListNameMealForSearch_BeforeFilter.add(mealSugar);
//        arrayListNameMealForSearch_BeforeFilter.add(mealSaturatedFats);
//        arrayListNameMealForSearch_BeforeFilter.add(productsIncludedComposedMeal);


        //bottomSheetDialogArrayList.add(new BottomSheetDialogArrayList(productsIncludedComposedMeal));
        //arrayListProductIncluded.add(productsIncludedComposedMeal);
        arrayListProductIncluded.add(currentItem.getProductMacros());

        bundleWithMacros = new Bundle();

        //System.out.println(dzialajno.get(0) + " CZY DZIAŁA?");
        bundleWithMacros.putStringArrayList("key", arrayListProductIncluded);

        //bottomSheetDialogArrayList.get(position)

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheet = new BottomSheetDialog();
                bottomSheet.show(((ComposedMealsActivity) mContext).getSupportFragmentManager(), bottomSheet.getTag());
                bundleWithMacros.putInt("key2", position);
                bottomSheet.setArguments(bundleWithMacros);
            }
        });

        ComposedMealsActivity obiekt = new ComposedMealsActivity();


        holder.checkBox_ComposedMeals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//               if( holder.checkBox_ComposedMeals.isChecked() ) {
//                   String test = "test 2";
//               }
                Toast.makeText(mContext, odebraneZAktywnosci, Toast.LENGTH_SHORT).show();
//                String test = "test 3";
//                ComposedMealsActivity obiekt = new ComposedMealsActivity();
//                obiekt.test();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (whatToReturn == true){
            return arrayListNameMealForSearch.size();
        }else{
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

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }
    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<ReturnItemComposedMeals> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(arrayListNameMealForSearchFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (ReturnItemComposedMeals item : arrayListNameMealForSearchFull) {
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
            arrayListNameMealForSearch.clear();
            arrayListNameMealForSearch.addAll((ArrayList) results.values);
            whatToReturn = true;
            notifyDataSetChanged();
        }
    };
}
