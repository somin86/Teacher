package tw.example.restaurantapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import tw.example.restaurantapp.databinding.ActivityRestaurantListBinding;


public class RestaurantListActivity extends AppCompatActivity {

    ActivityRestaurantListBinding binding;
    RestaurantItemClickListener restaurantItemClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRestaurantListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.rest_list);
        getSupportActionBar().setSubtitle("花蓮縣");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.teal_700)));

        // 預先綁定 RecyclerView 內的清單被點選後的處理機制
        restaurantItemClickListener = new RestaurantItemClickListener() {
            @Override
            public void onClick(int position, String restaurantName) {
                Toast.makeText(RestaurantListActivity.this, "資料位置: " + position, Toast.LENGTH_SHORT).show();
                Toast.makeText(RestaurantListActivity.this, "餐廳名稱: " + restaurantName, Toast.LENGTH_SHORT).show();
                // 此處 可以將畫面 轉換到 RestaurantDetailActivity 負責顯示餐廳詳細資訊的畫面 ( ui 轉 ui )
            }
        };

        //每一個 UI元件都必須要與上層綁定　( Layout 則必須與有關的 Activity 綁定 )
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.restaurantListView.setLayoutManager(linearLayoutManager);
        // 資料的來源 ?  RecyclerView 指定需要與 RecyclerView.Adapter 物件綁定 提供資料與 細部的
        // ViewHolder Layout 一起呈現
        // Adapter 要負責 載入所有資料 並建立一個 UI View 容器 並把指定的資料填入 UI後 交給 RecyclerView顯示
        SQLiteDatabase db = openOrCreateDatabase("restaurants",MODE_PRIVATE,null);
        binding.restaurantListView.setAdapter( new RestaurantAdapter(db,restaurantItemClickListener));
    }

    // 處理 Home鍵返回上一個Activity
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch ( item.getItemId()){
            case android.R.id.home:
                // finish 只關閉(destroy 目前這個 Activity)
                // finish()
                // 只是要模擬 使用者按下返回鍵 back
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}