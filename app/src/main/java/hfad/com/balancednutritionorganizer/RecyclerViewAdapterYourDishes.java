package hfad.com.balancednutritionorganizer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static java.lang.Double.parseDouble;

public class RecyclerViewAdapterYourDishes extends RecyclerView.Adapter<RecyclerViewAdapterYourDishes.ViewHolder>{

    private ArrayList<String> mProductNames;
    private ArrayList<String> mProductCalories;
    private Context mContext;

    public RecyclerViewAdapterYourDishes(Context mContext, ArrayList<String> mProductNames, ArrayList<String> mProductCalories) {

        this.mProductNames = mProductNames;
        this.mProductCalories = mProductCalories;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterYourDishes.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.scheme_your_dishes, parent, false);
        RecyclerViewAdapterYourDishes.ViewHolder holder = new RecyclerViewAdapterYourDishes.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterYourDishes.ViewHolder holder, final int position) {

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
