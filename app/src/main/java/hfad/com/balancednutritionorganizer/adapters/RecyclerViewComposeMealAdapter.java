package hfad.com.balancednutritionorganizer.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import hfad.com.balancednutritionorganizer.database_things.ComposeMealColumns;
import hfad.com.balancednutritionorganizer.R;

public class RecyclerViewComposeMealAdapter extends RecyclerView.Adapter<RecyclerViewComposeMealAdapter.ComposeMealViewHolder> {
    private Context mContext;
    private Cursor mCursor;
    DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
    DecimalFormat format;
    public RecyclerViewComposeMealAdapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
    }

    public class ComposeMealViewHolder extends RecyclerView.ViewHolder {
        public TextView composeMealNameFood, composeMealCaloriesFood;
        public ComposeMealViewHolder(@NonNull View itemView) {
            super(itemView);
            composeMealNameFood = itemView.findViewById(R.id.composeMealNameFood);
            composeMealCaloriesFood = itemView.findViewById(R.id.composeMealCaloriesFood);
        }
    }

    @NonNull
    @Override
    public ComposeMealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.scheme_compose_meals, parent, false);
        return new ComposeMealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComposeMealViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position)) {
            return;
        }
        long id = mCursor.getLong(mCursor.getColumnIndex(ComposeMealColumns.ComposeMealColumnsEntry._ID));
        String name = mCursor.getString(mCursor.getColumnIndex(ComposeMealColumns.ComposeMealColumnsEntry.COLUMN_NAME));
        double amount = mCursor.getDouble(mCursor.getColumnIndex(ComposeMealColumns.ComposeMealColumnsEntry.COLUMN_AMOUNT));

        symbols.setDecimalSeparator('.');
        format = new DecimalFormat("#.#");
        format.setDecimalFormatSymbols(symbols);
        format.setMaximumFractionDigits(1);
        format.setDecimalSeparatorAlwaysShown(false);

        holder.itemView.setTag(id);
        holder.composeMealNameFood.setText(position+1 + ".  " + name);
        //holder.countText.setText(String.valueOf(amount));
        holder.composeMealCaloriesFood.setText(format.format(amount) + " KCAL");
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
