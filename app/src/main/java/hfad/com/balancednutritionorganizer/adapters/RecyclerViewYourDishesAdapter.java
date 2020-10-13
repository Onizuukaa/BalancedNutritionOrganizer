package hfad.com.balancednutritionorganizer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hfad.com.balancednutritionorganizer.R;

import static java.lang.Double.parseDouble;

public class RecyclerViewYourDishesAdapter extends RecyclerView.Adapter<RecyclerViewYourDishesAdapter.ViewHolder>{

    private ArrayList<String> mProductNames;
    private ArrayList<String> mProductCalories;
    private Context mContext;

    public RecyclerViewYourDishesAdapter(Context mContext, ArrayList<String> mProductNames, ArrayList<String> mProductCalories) {

        this.mProductNames = mProductNames;
        this.mProductCalories = mProductCalories;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerViewYourDishesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.scheme_composed_meals, parent, false);
        RecyclerViewYourDishesAdapter.ViewHolder holder = new RecyclerViewYourDishesAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewYourDishesAdapter.ViewHolder holder, final int position) {

        holder.productName.setText(mProductNames.get(position));

        double productCaloriesDouble = parseDouble(mProductCalories.get(position));
        String productCaloriesString = String.format("%.1f", productCaloriesDouble);
        holder.productCalories.setText(productCaloriesString + " KCAL");

        //holder.productCalories.setText(mProductCalories.get(position) + " KCAL");
    }

    @Override
    public int getItemCount() {
        return mProductNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView productName, productCalories;
        CardView parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.textViewProductLeft);
            productCalories = itemView.findViewById(R.id.textViewHowManyGramProduct);
            parentLayout = itemView.findViewById(R.id.sendedProduct);
        }
    }
}
