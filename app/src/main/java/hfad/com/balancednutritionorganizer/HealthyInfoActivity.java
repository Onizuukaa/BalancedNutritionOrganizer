package hfad.com.balancednutritionorganizer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HealthyInfoActivity extends AppCompatActivity implements HealthyInfoBottomSheetDialog.BottomSheetListener{

    Bundle newBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healthy_info);
        setTitle(R.string.Healthy_Info);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        newBundle = new Bundle();
    }

    public void openInfoAboutCalories(View view){
        //newBundle.putInt("key2", R.drawable.calories);
        newBundle.putInt("key3", 1000);
        newBundle.putInt("key4", 212);
        newBundle.putString("key5", "calories");
        HealthyInfoBottomSheetDialog bottomSheet = new HealthyInfoBottomSheetDialog();
        bottomSheet.show(getSupportFragmentManager(), "exampleBottomSheet");
        bottomSheet.setArguments(newBundle);
    }

    public void openInfoAboutCarbohydrates(View view){
        newBundle.putString("key", "Węglowodany W naszej diecie węglowodany powinny stanowić od 55 do 60% zapotrzebowania kalorycznego. Węglowodany dzielą się na złożone i proste. Złone występują np. w pieczywie a proste to cukier. Spożywany cukier w diecie nie powinien przekraczać 10% naszego zapotrzebowania kalorycznego. Dla osoby potrzebującej 2000 kalori jest to 50 gram. Wiele produktów posiada za dużo cukru aby zachęcić konsumentów do kupowania określonego produktu. Cukier jest nawet bardziej uzależniający niż koka. W przykładowym keczupie na 100 gram produktu aż 34 gram to cukier! Więc zjadając 100g keczupu, osoba potrzebująca 2000 kcal dostarczyła dla organizu 68% dziennego limitu cukru. A to tylko keczup, nie wspominając, że czasami człowiek zje coś słodkiego co jest już bardzo naszpikowane cukrem. Jest to skaza na społeczeństwie niestety i dlatego teraz doszło w naszych czasach do absurdu, że człowiek umiera z tego, że je czegoś za dużo a nie za mało... ");
        newBundle.putInt("key2", R.drawable.carbohydrates);
        newBundle.putInt("key3", 1000);
        newBundle.putInt("key4", 389);
        newBundle.putString("key5", "carbohydrates");
        HealthyInfoBottomSheetDialog bottomSheet = new HealthyInfoBottomSheetDialog();
        bottomSheet.show(getSupportFragmentManager(), "exampleBottomSheet");
        bottomSheet.setArguments(newBundle);
    }

    public void openInfoAboutProtein(View view){
        newBundle.putString("key", "Nie mam jeszcze nic o Białku");
        newBundle.putInt("key2", R.drawable.protein);
        newBundle.putInt("key3", 1000);
        newBundle.putInt("key4", 379);
        newBundle.putString("key5", "protein");
        HealthyInfoBottomSheetDialog bottomSheet = new HealthyInfoBottomSheetDialog();
        bottomSheet.show(getSupportFragmentManager(), "exampleBottomSheet");
        bottomSheet.setArguments(newBundle);
    }

    public void openInfoAboutFats(View view){
        newBundle.putString("key", "Nie mam jeszcze nic o Tłuszczu");
        newBundle.putInt("key2", R.drawable.fats);
        newBundle.putInt("key3", 1000);
        newBundle.putInt("key4", 420);
        newBundle.putString("key5", "fats");
        HealthyInfoBottomSheetDialog bottomSheet = new HealthyInfoBottomSheetDialog();
        bottomSheet.show(getSupportFragmentManager(), "exampleBottomSheet");
        bottomSheet.setArguments(newBundle);
    }

    public void openInfoAboutWater(View view){
        newBundle.putString("key5", "water");
        HealthyInfoBottomSheetDialog bottomSheet = new HealthyInfoBottomSheetDialog();
        bottomSheet.show(getSupportFragmentManager(), "exampleBottomSheet");
        bottomSheet.setArguments(newBundle);
    }

    @Override
    public void onButtonClicked(String text) {

    }
}