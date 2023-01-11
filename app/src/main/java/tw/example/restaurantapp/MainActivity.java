package tw.example.restaurantapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import tw.example.restaurantapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.btnABControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActionBar actionBar = MainActivity.this.getSupportActionBar();
                if(actionBar.isShowing()) {
                    Toast.makeText(MainActivity.this, "ActiocBar 目前顯示中，現在關閉", Toast.LENGTH_SHORT).show();
                    actionBar.hide();
                }else{
                    Toast.makeText(MainActivity.this,"ActionBar 已關閉，現在打開",Toast.LENGTH_SHORT).show();
                    actionBar.show();
                }
            }
        });
    }

    //建立 OptionMenu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 請出 Menu Inflater 出來製作選單
        // 須提供 xml
        this.getMenuInflater().inflate(R.menu.menu_123,menu);
        return super.onCreateOptionsMenu(menu);
    }
    // 接受並處理 OptionMenu 中的 OptionItem 的 click 事件


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch( item.getItemId() ) {
            case R.id.main_load:
                // 開啟 資料更新 Activity
                // 顯式意圖 : 從 MainActivity 跳至 DataMaintainActivity
                // 為何必須宣告 MainActivity.this ?  因為 DataMaintain 按下 back 鍵必須返回上一層
                Intent intentMaintain = new Intent(MainActivity.this, DataMaintainActivity.class);
                // intentMaintain.setAction(Intent.ACTION_VIEW);
                startActivity(intentMaintain);
                break;
            case R.id.main_list:
                // 開啟 餐廳 RecyclerView
                Intent intentRest = new Intent(MainActivity.this, RestaurantListActivity.class);
                startActivity(intentRest);
                break;
            case R.id.main_exit:
                //關閉
                this.finish();
                break;
            case android.R.id.home:
                onBackPressed();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}