package hfad.com.balancednutritionorganizer;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static java.lang.Double.parseDouble;

public class RecyclerViewAdapter2 extends RecyclerView.Adapter<RecyclerViewAdapter2.ViewHolder> {
    private ArrayList<String> mProductNames = new ArrayList<>();
    private ArrayList<String> mProductCalories = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter2(Context mContext, ArrayList<String> mProductImages, ArrayList<String> mProductNames, ArrayList<String> mProductCalories,
                                ArrayList<String> mProductCarbohydrates, ArrayList<String> mProductSugar, ArrayList<String> mProductFats,
                                ArrayList<String> mProductSaturatedFats, ArrayList<String> mProductProtein) {

        this.mProductNames = mProductNames;
        this.mProductCalories = mProductCalories;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.composing_dishes, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

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
