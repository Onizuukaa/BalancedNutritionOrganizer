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
    ImageView imageViewBottomSheet;
    //int srcImage, widthImage, heightImage;
    View v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(getArguments() != null) {
            //textWithMacros = getArguments().getString("key");
            //srcImage = getArguments().getInt("key2");
            //widthImage = getArguments().getInt("key3");
            //heightImage = getArguments().getInt("key4");
            whichLayoutOpen = getArguments().getString("key5");
        }
        if (whichLayoutOpen.equals("calories")){
            v = inflater.inflate(R.layout.calories_bottom_sheet_layout, container, false);
        }
        if (whichLayoutOpen.equals("carbohydrates")){
            v = inflater.inflate(R.layout.carbohydrates_bottom_sheet_layout, container, false);
        }

        Button button_Exit_BottomSheet = v.findViewById(R.id.button_Exit_BottomSheet);
        button_Exit_BottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        //imageViewBottomSheet = v.findViewById(R.id.imageViewBottomSheet);
        //imageViewBottomSheet.setImageResource(srcImage);

        //LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(widthImage, heightImage);
        //imageViewBottomSheet.setLayoutParams(layoutParams);

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
