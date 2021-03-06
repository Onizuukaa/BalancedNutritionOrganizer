package hfad.com.balancednutritionorganizer;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class HealthyInfoBottomSheetDialog extends BottomSheetDialogFragment {
    private BottomSheetListener mListener;
    String whichLayoutOpen;
    View v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(getArguments() != null) {
            whichLayoutOpen = getArguments().getString("key5");
        }
        if (whichLayoutOpen.equals("calories")){
            v = inflater.inflate(R.layout.calories_bottom_sheet_layout, container, false);
        }
        if (whichLayoutOpen.equals("carbohydrates")){
            v = inflater.inflate(R.layout.carbohydrates_bottom_sheet_layout, container, false);
        }
        if (whichLayoutOpen.equals("protein")){
            v = inflater.inflate(R.layout.protein_bottom_sheet_layout, container, false);
        }
        if (whichLayoutOpen.equals("fats")){
            v = inflater.inflate(R.layout.fats_bottom_sheet_layout, container, false);
        }
        if (whichLayoutOpen.equals("water")){
            v = inflater.inflate(R.layout.water_bottom_sheet_layout, container, false);
        }
        if (whichLayoutOpen.equals("meals")){
            v = inflater.inflate(R.layout.meals_bottom_sheet_layout, container, false);
        }

        Button button_Exit_BottomSheet = v.findViewById(R.id.button_Exit_BottomSheet);
        button_Exit_BottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return v;
    }

    public interface BottomSheetListener {
        void onButtonClicked(String text);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            mListener = (BottomSheetListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
            + " must implement BottomSheetListener");
        }
    }
}
