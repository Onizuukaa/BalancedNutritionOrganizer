package hfad.com.balancednutritionorganizer.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import hfad.com.balancednutritionorganizer.database_things.ComposeMealColumns;
import hfad.com.balancednutritionorganizer.R;

public class RecyclerViewComposeMealAdapter extends RecyclerView.Adapter<RecyclerViewComposeMealAdapter.GroceryViewHolder> {
    private Context mContext;
    private Cursor mCursor;

    public RecyclerViewComposeMealAdapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
    }

    public class GroceryViewHolder extends RecyclerView.ViewHolder {
        public TextView nameText;
        public TextView countText;

        public GroceryViewHolder(@NonNull View itemView) {
            super(itemView);

            nameText = itemView.findViewById(R.id.textViewProductLeft);
            countText = itemView.findViewById(R.id.textViewHowManyGramProduct);
        }
    }

    @NonNull
    @Override
    public GroceryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.scheme_compose_meals, parent, false);
        return new GroceryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroceryViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position)) {
            return;
        }
        long id = mCursor.getLong(mCursor.getColumnIndex(ComposeMealColumns.GroceryEntry._ID));
        String name = mCursor.getString(mCursor.getColumnIndex(ComposeMealColumns.GroceryEntry.COLUMN_NAME));
        int amount = mCursor.getInt(mCursor.getColumnIndex(ComposeMealColumns.GroceryEntry.COLUMN_AMOUNT));

        holder.itemView.setTag(id);
        holder.nameText.setText(position+1 + ".  " + name);
        //holder.countText.setText(String.valueOf(amount));
        holder.countText.setText(amount + " KCAL");
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
