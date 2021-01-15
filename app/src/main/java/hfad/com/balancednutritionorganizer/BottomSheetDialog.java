package hfad.com.balancednutritionorganizer;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

public class BottomSheetDialog extends BottomSheetDialogFragment {
    private BottomSheetListener mListener;
    String textWithMacros, mealName;
    TextView textViewBottomSheet, textViewMealNameBottomSheet;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getArguments() != null) {
            textWithMacros = getArguments().getString("key");
            mealName = getArguments().getString("key2");
        }
        View v = inflater.inflate(R.layout.bottom_sheet_layout, container, false);

        Button button_Exit_BottomSheet = v.findViewById(R.id.button_Exit_BottomSheet);
        button_Exit_BottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        textViewBottomSheet = v.findViewById(R.id.textViewBottomSheet);
        textViewMealNameBottomSheet = v.findViewById(R.id.textViewMealNameBottomSheet);
        textViewBottomSheet.setText(textWithMacros);
        textViewMealNameBottomSheet.setText(mealName);

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
