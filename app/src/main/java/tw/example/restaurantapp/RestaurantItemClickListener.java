package tw.example.restaurantapp;

//處理 當 餐廳資料被使用者點選後的程式邏輯
public interface RestaurantItemClickListener {
    void onClick(int position, String restaurantName);
}
