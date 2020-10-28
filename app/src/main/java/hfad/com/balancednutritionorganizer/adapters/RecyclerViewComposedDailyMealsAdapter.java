package hfad.com.balancednutritionorganizer.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hfad.com.balancednutritionorganizer.R;
import hfad.com.balancednutritionorganizer.database_things.ComposedDailyMealsColumns;

public class RecyclerViewComposedDailyMealsAdapter extends RecyclerView.Adapter<RecyclerViewComposedDailyMealsAdapter.RecyclerViewComposedDailyMealsViewHolder>{

    private Context context;
    private Cursor cursor;

    private ArrayList<String> arrayListForTable = new ArrayList<>();

//    public RecyclerViewComposedDailyMealsAdapter(Context context, Cursor cursor) {
//        context = context;
//        cursor = cursor;
//    }

    public class RecyclerViewComposedDailyMealsViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewComposedDailyMealsName, textViewComposedDailyMealsKcal_Sum,
                textViewComposedDailyMealsFirstProduct, textViewComposedDailyMealsKcal_FirstProduct_Sum,
                textViewComposedDailyMealsSecondProduct, textViewComposedDailyMealsKcal_SecondProduct_Sum,
                textViewComposedDailyMealsThirdProduct, textViewComposedDailyMealsKcal_ThirdProduct_Sum,
                textViewComposedDailyMealsFourthProduct, textViewComposedDailyMealsKcal_FourthProduct_Sum,
                textViewComposedDailyMealsFifthProduct, textViewComposedDailyMealsKcal_FifthProduct_Sum;
        CardView parentLayout;

        public RecyclerViewComposedDailyMealsViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewComposedDailyMealsName = itemView.findViewById(R.id.textViewComposedDailyMealsName);
            textViewComposedDailyMealsKcal_Sum = itemView.findViewById(R.id.textViewComposedDailyMealsKcal_Sum);
            textViewComposedDailyMealsFirstProduct = itemView.findViewById(R.id.textViewComposedDailyMealsFirstProduct);
            textViewComposedDailyMealsKcal_FirstProduct_Sum = itemView.findViewById(R.id.textViewComposedDailyMealsKcal_FirstProduct_Sum);
            textViewComposedDailyMealsSecondProduct = itemView.findViewById(R.id.textViewComposedDailyMealsSecondProduct);
            textViewComposedDailyMealsKcal_SecondProduct_Sum = itemView.findViewById(R.id.textViewComposedDailyMealsKcal_SecondProduct_Sum);
            textViewComposedDailyMealsThirdProduct = itemView.findViewById(R.id.textViewComposedDailyMealsThirdProduct);
            textViewComposedDailyMealsKcal_ThirdProduct_Sum = itemView.findViewById(R.id.textViewComposedDailyMealsKcal_ThirdProduct_Sum);
            textViewComposedDailyMealsFourthProduct = itemView.findViewById(R.id.textViewComposedDailyMealsFourthProduct);
            textViewComposedDailyMealsKcal_FourthProduct_Sum = itemView.findViewById(R.id.textViewComposedDailyMealsKcal_FourthProduct_Sum);
            textViewComposedDailyMealsFifthProduct = itemView.findViewById(R.id.textViewComposedDailyMealsFifthProduct);
            textViewComposedDailyMealsKcal_FifthProduct_Sum = itemView.findViewById(R.id.textViewComposedDailyMealsKcal_FifthProduct_Sum);
            parentLayout = itemView.findViewById(R.id.scheme_composed_daily_meals);
        }
    }

    @NonNull
    @Override
    public RecyclerViewComposedDailyMealsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.scheme_composed_daily_meals_stare, parent, false);
        return new RecyclerViewComposedDailyMealsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewComposedDailyMealsViewHolder holder, final int position) {
        if (!cursor.moveToPosition(position)) {
            return;
        }

        long id = cursor.getLong(cursor.getColumnIndex(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry._ID));

        String nameComposedDailyMeals = cursor.getString(cursor.getColumnIndex(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_nameComposedDailyMeals));
        String nameComposedDailyMeals_KCAL_SUM = cursor.getString(cursor.getColumnIndex(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_nameComposedDailyMeals_KCAL_SUM));
        String nameFirstProduct = cursor.getString(cursor.getColumnIndex(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_nameFirstProduct));
        String nameSecondProduct = cursor.getString(cursor.getColumnIndex(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_nameSecondProduct));
        String nameThirdProduct = cursor.getString(cursor.getColumnIndex(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_nameThirdProduct));
        String nameFourthProduct = cursor.getString(cursor.getColumnIndex(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_nameFourthProduct));
        String nameFifthProduct = cursor.getString(cursor.getColumnIndex(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_nameFifthProduct));
        String macrosFirstProductsKcalWeight = cursor.getString(cursor.getColumnIndex(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_macrosFirstProductsKcalWeight));
        String macrosSecondProductsKcalWeight = cursor.getString(cursor.getColumnIndex(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_macrosSecondProductsKcalWeight));
        String macrosThirdProductsKcalWeight = cursor.getString(cursor.getColumnIndex(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_macrosThirdProductsKcalWeight));
        String macrosFourthProductsKcalWeight = cursor.getString(cursor.getColumnIndex(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_macrosFourthProductsKcalWeight));
        String macrosFifthProductsKcalWeight = cursor.getString(cursor.getColumnIndex(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_macrosFifthProductsKcalWeight));
        String productsIncludedFirstProduct = cursor.getString(cursor.getColumnIndex(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_productsIncludedFirstProduct));
        String productsIncludedSecondProduct = cursor.getString(cursor.getColumnIndex(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_productsIncludedSecondProduct));
        String productsIncludedThirdProduct = cursor.getString(cursor.getColumnIndex(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_productsIncludedThirdProduct));
        String productsIncludedFourthProduct = cursor.getString(cursor.getColumnIndex(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_productsIncludedFourthProduct));
        String productsIncludedFifthProduct = cursor.getString(cursor.getColumnIndex(ComposedDailyMealsColumns.ComposedDailyMealsColumnsEntry.COLUMN_productsIncludedFifthProduct));
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        if (cursor != null) {
            cursor.close();
        }

        cursor = cursor;

        if (newCursor != null) {
            notifyDataSetChanged();
        }
    }
}
