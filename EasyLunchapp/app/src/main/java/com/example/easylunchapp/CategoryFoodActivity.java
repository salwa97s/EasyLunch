package com.example.easylunchapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easylunchapp.Adapter.RecviewadapterFood;
import com.example.easylunchapp.Classes.Food;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.List;

public class CategoryFoodActivity extends AppCompatActivity {

    private RecyclerView MyRecViewEvents ;
    private List<Food> Healthylst , FastFoodlst , Sandwitchlst , Bakinglst , Fruitlst , Drinkslst;
    private  int Type1;
    public static final String SHARED_TYPE = "TYPE" ;
    public static final String TYPE = "Type";
    private CollapsingToolbarLayout d ;
    private TextView CategoryTxt , DesTxt;
    private Toolbar toolbar ;
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodcategory);
        CreateData();

        toolbar = (Toolbar)findViewById(R.id.toolbarid);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        SharedPreferences sp = getSharedPreferences(SHARED_TYPE , MODE_PRIVATE);
        Type1 = sp.getInt(TYPE ,9);

        d=(CollapsingToolbarLayout)findViewById(R.id.Header_id);
        CategoryTxt=(TextView)findViewById(R.id.category_id);
        DesTxt=(TextView)findViewById(R.id.desc_id);

        if(Type1 == 0){
            //sets
            d.setBackgroundResource(R.drawable.bannerhealthy);
            d.setContentScrimColor(getResources().getColor(R.color.LightPink));
            d.setTitle("Healthy");
           // CategoryTxt.setText("Healthy");
           // DesTxt.setText("Let's be Healthy");
            //RecView
            MyRecViewEvents=(RecyclerView)findViewById(R.id.Foodrec_id);
            RecviewadapterFood recadapter = new RecviewadapterFood(this,Healthylst);
            MyRecViewEvents.setLayoutManager(new GridLayoutManager(this , 1));
            MyRecViewEvents.setAdapter(recadapter);
        }

        if(Type1 == 1){
            //sets
            d.setBackgroundResource(R.drawable.bannersandwitch);
            d.setContentScrimColor(getResources().getColor(R.color.LightPink));
            d.setTitle("Sandwiches");
            //CategoryTxt.setText("Sandwiches");
           // DesTxt.setText("Simple lunch <3 ");
            //RecView
            MyRecViewEvents=(RecyclerView)findViewById(R.id.Foodrec_id);
            RecviewadapterFood recadapter = new RecviewadapterFood(this,Sandwitchlst);
            MyRecViewEvents.setLayoutManager(new GridLayoutManager(this , 1));
            MyRecViewEvents.setAdapter(recadapter);
        }

        if(Type1==2){
            d.setBackgroundResource(R.drawable.bannerfast);
            d.setContentScrimColor(getResources().getColor(R.color.LightPink));
            d.setTitle("Warm Food");
           // CategoryTxt.setText("Warm Food");
           // DesTxt.setText("Don't Forget to drink water after this male");
            //RecView
            MyRecViewEvents=(RecyclerView)findViewById(R.id.Foodrec_id);
            RecviewadapterFood recadapter = new RecviewadapterFood(this,FastFoodlst);
            MyRecViewEvents.setLayoutManager(new GridLayoutManager(this , 1));
            MyRecViewEvents.setAdapter(recadapter);
        }

        if(Type1 == 3){
            d.setBackgroundResource(R.drawable.bannerpastry);
            d.setContentScrimColor(getResources().getColor(R.color.LightPink));
            d.setTitle("Pastry");
           // CategoryTxt.setText("Pastry");
           // DesTxt.setText("Yummy Yummy :D ");
            //RecView
            MyRecViewEvents=(RecyclerView)findViewById(R.id.Foodrec_id);
            RecviewadapterFood recadapter = new RecviewadapterFood(this,Bakinglst);
            MyRecViewEvents.setLayoutManager(new GridLayoutManager(this , 1));
            MyRecViewEvents.setAdapter(recadapter);
        }
        if(Type1 == 4){
            d.setBackgroundResource(R.drawable.bannerfruit);
            d.setContentScrimColor(getResources().getColor(R.color.green));
            d.setTitle("Fruit & Veg");
           // CategoryTxt.setText(" ");
           // DesTxt.setText("Super important");
            //RecView
            MyRecViewEvents=(RecyclerView)findViewById(R.id.Foodrec_id);
            RecviewadapterFood recadapter = new RecviewadapterFood(this,Fruitlst);
            MyRecViewEvents.setLayoutManager(new GridLayoutManager(this , 1));
            MyRecViewEvents.setAdapter(recadapter);
        }
        if(Type1 == 5){
            d.setBackgroundResource(R.drawable.bannerjuice);
            d.setContentScrimColor(getResources().getColor(R.color.red));
            d.setTitle("Drinks");
           // CategoryTxt.setText(" ");
          //  DesTxt.setText(" ");
            //RecView
            MyRecViewEvents=(RecyclerView)findViewById(R.id.Foodrec_id);
            RecviewadapterFood recadapter = new RecviewadapterFood(this,Drinkslst);
            MyRecViewEvents.setLayoutManager(new GridLayoutManager(this , 1));
            MyRecViewEvents.setAdapter(recadapter);
        }


    }

    public void CreateData(){
        Healthylst = new ArrayList<>();
        Healthylst.add(new Food("Yogurt","8",R.drawable.yogurt));
        Healthylst.add(new Food("salad","10",R.drawable.salad));
        Healthylst.add(new Food("FruitSalad","10",R.drawable.fruitsalad));
        Healthylst.add(new Food("omlet","12",R.drawable.omlet));
        Healthylst.add(new Food("potato","8",R.drawable.potato));
        //Healthylst.add(new Food());

        Sandwitchlst=new ArrayList<>();
        Sandwitchlst.add(new Food("Cheese" , "10" , R.drawable.cheese));
        Sandwitchlst.add(new Food("Avocado" , "12" , R.drawable.abokado));
        Sandwitchlst.add(new Food("White Cheese" , "8" , R.drawable.labane));
        Sandwitchlst.add(new Food("Bastrama" , "10" , R.drawable.bastrama));
        Sandwitchlst.add(new Food("Tuna" , "10" , R.drawable.tona));
        //Sandwitchlst.add(new Food());

        FastFoodlst = new ArrayList<>();
        FastFoodlst.add(new Food("Chicken","15",R.drawable.chicken));
        FastFoodlst.add(new Food("HotDog","14",R.drawable.hotdog));
        FastFoodlst.add(new Food("Potato","12",R.drawable.potatosand));
        FastFoodlst.add(new Food("Flafel","12",R.drawable.flafel));
        FastFoodlst.add(new Food("Tost","12",R.drawable.tost));
        //FastFoodlst.add(new Food());

        Bakinglst=new ArrayList<>();
        Bakinglst.add(new Food("Zaa'tar" , "5" , R.drawable.zaatar));
        Bakinglst.add(new Food("Pizza" , "5" , R.drawable.pizza));
        Bakinglst.add(new Food("Cheese" , "5" , R.drawable.bakingcheese));
        Bakinglst.add(new Food("Potato" , "5" , R.drawable.borks));
        //Bakinglst.add(new Food());

        Fruitlst = new ArrayList<>();
        Fruitlst.add(new Food("Apple" , "1",R.drawable.apple));
        Fruitlst.add(new Food("Banana" , "1",R.drawable.banana));
        Fruitlst.add(new Food("Strawberry" , "1",R.drawable.strawberry));
        Fruitlst.add(new Food("Carrots" , "1",R.drawable.carrots));
        Fruitlst.add(new Food("Cherry Tomato" , "1",R.drawable.cherry));

        Drinkslst = new ArrayList<>();
        Drinkslst.add(new Food ("Cola" , "8" , R.drawable.cola));
        Drinkslst.add(new Food ("Water" , "8" , R.drawable.water));
        Drinkslst.add(new Food ("Juice" , "8" , R.drawable.juice));
        Drinkslst.add(new Food ("Fresh orange" , "8" , R.drawable.orange));
        Drinkslst.add(new Food ("Milk" , "4" , R.drawable.milk));



    }
}
