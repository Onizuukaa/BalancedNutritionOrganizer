package hfad.com.balancednutritionorganizer.adapters;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hfad.com.balancednutritionorganizer.BottomSheetDialog;
import hfad.com.balancednutritionorganizer.ComposedMealsActivity;
import hfad.com.balancednutritionorganizer.R;
import hfad.com.balancednutritionorganizer.database_things.ComposedMealsColumns;

public class RecyclerViewComposedMealsAdapter extends RecyclerView.Adapter<RecyclerViewComposedMealsAdapter.RecyclerViewComposedMealsViewHolder> implements BottomSheetDialog.BottomSheetListener {

    //private ArrayList<BottomSheetDialogArrayList> bottomSheetDialogArrayList = new ArrayList<>();
    private ArrayList<String> dzialajno = new ArrayList<>();
    private Context mContext;
    private Cursor mCursor;
    Bundle bundleWithMacros;

    String aaa;

    //public RecyclerViewComposedMealsAdapter(Context mContext, ArrayList<String> mProductNames, ArrayList<String> mProductCalories) {
    public RecyclerViewComposedMealsAdapter(Context context, Cursor cursor) {
        //this.mProductNames = mProductNames;
        //this.mProductCalories = mProductCalories;
        //this.mContext = mContext;
        mContext = context;
        mCursor = cursor;
    }

    @Override
    public void onButtonClicked(String text) {

    }

    public class RecyclerViewComposedMealsViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewComposedMealsName, textViewComposedMealsKcal, textViewComposedMealsGram,
                textViewComposedMealsCarbohydrates, textViewComposedMealsFats, textViewComposedMealsProtein,
                textViewComposedMealsSugar, textViewComposedMealsSaturatedFats, textViewProductsIncludedComposedMeal;
        CardView parentLayout;

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
        }
    }

    @NonNull
    @Override
    public RecyclerViewComposedMealsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext); //dodałem bo w GroceryAdapter, tym nowszym, tak jest.
        View view = inflater.inflate(R.layout.scheme_composed_meals, parent, false);
        //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.scheme_composed_meals, parent, false);
        //RecyclerViewComposedMealsAdapter.ViewHolder holder = new RecyclerViewComposedMealsAdapter.ViewHolder(view);
        //return holder;
        return new RecyclerViewComposedMealsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewComposedMealsViewHolder holder, final int position) {

        if (!mCursor.moveToPosition(position)) {
            return;
        }

        long id = mCursor.getLong(mCursor.getColumnIndex(ComposedMealsColumns.ComposedMealsColumnsEntry._ID));

        String mealName = mCursor.getString(mCursor.getColumnIndex(ComposedMealsColumns.ComposedMealsColumnsEntry.COLUMN_MEALNAME));
        String mealKcal = mCursor.getString(mCursor.getColumnIndex(ComposedMealsColumns.ComposedMealsColumnsEntry.COLUMN_CALORIES));
        String mealGram = mCursor.getString(mCursor.getColumnIndex(ComposedMealsColumns.ComposedMealsColumnsEntry.COLUMN_WEIGHT));
        String mealCarbohydrates = mCursor.getString(mCursor.getColumnIndex(ComposedMealsColumns.ComposedMealsColumnsEntry.COLUMN_CARBO));
        String mealFats = mCursor.getString(mCursor.getColumnIndex(ComposedMealsColumns.ComposedMealsColumnsEntry.COLUMN_FATS));
        String mealProtein = mCursor.getString(mCursor.getColumnIndex(ComposedMealsColumns.ComposedMealsColumnsEntry.COLUMN_PROTEIN));
        String mealSugar = mCursor.getString(mCursor.getColumnIndex(ComposedMealsColumns.ComposedMealsColumnsEntry.COLUMN_SUGAR));
        String mealSaturatedFats = mCursor.getString(mCursor.getColumnIndex(ComposedMealsColumns.ComposedMealsColumnsEntry.COLUMN_SATURATEDFATS));
        String productsIncludedComposedMeal = mCursor.getString(mCursor.getColumnIndex(ComposedMealsColumns.ComposedMealsColumnsEntry.COLUMN_PRODUCTSINCLUDED));

//aaa = productsIncludedComposedMeal;

        holder.itemView.setTag(id);
        holder.textViewComposedMealsName.setText(position + 1 + ".  " + mealName);
        holder.textViewComposedMealsKcal.setText(mealKcal + "\ncalories");
        holder.textViewComposedMealsGram.setText(mealGram + "g\nweight");
        holder.textViewComposedMealsCarbohydrates.setText(mealCarbohydrates + "g\ncarbohydrates");
        holder.textViewComposedMealsFats.setText(mealFats + "g\nfats");
        holder.textViewComposedMealsProtein.setText(mealProtein + "g\nprotein");
        holder.textViewComposedMealsSugar.setText(mealSugar + "g\nsugar");
        holder.textViewComposedMealsSaturatedFats.setText(mealSaturatedFats + "g\nsaturated fats");
        holder.textViewProductsIncludedComposedMeal.setText(productsIncludedComposedMeal + "");

        //bottomSheetDialogArrayList.add(new BottomSheetDialogArrayList(productsIncludedComposedMeal));
        dzialajno.add(productsIncludedComposedMeal);

        bundleWithMacros = new Bundle();

        //System.out.println(dzialajno.get(0) + " CZY DZIAŁA?");
        bundleWithMacros.putStringArrayList("key", dzialajno);

        //bottomSheetDialogArrayList.get(position)

//        aaa = " | "+ mealName + " | calories " + mealKcal
//                + " | carbo " + mealCarbohydrates + "g | "
//                + " | sugar " + mealSugar + "g | "
//                + " | fats " + mealFats + "g | "
//                + " | saturated fats " + mealSaturatedFats + "g | "
//                + " | protein " + mealProtein + "g | "
//                + " | weight " + mealGram + "g | \n";


        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheet = new BottomSheetDialog();
                //bottomSheet.show(getSupportFragmentManager(), "exampleBottomSheet");
                bottomSheet.show(((ComposedMealsActivity) mContext).getSupportFragmentManager(), bottomSheet.getTag());
                bundleWithMacros.putInt("key2", position);

                bottomSheet.setArguments(bundleWithMacros);

                //BottomSheetDialogArrayList currentItem = new BottomSheetDialogArrayList();


            }
        });
    }

    @Override
    public int getItemCount() {
        //return mProductNames.size();
        //return 3;
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
