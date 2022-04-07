package com.github.anonisnap.roommanytomanyrelations.data.percistence.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.github.anonisnap.roommanytomanyrelations.data.entities.MyBasket;
import com.github.anonisnap.roommanytomanyrelations.data.entities.MyItem;

import java.util.List;
import java.util.Map;

@Dao
public interface ExampleDAO {
	// Items
	@Insert
	void insert(MyItem item);

	@Update
	void update(MyItem item);

	@Delete
	void delete(MyItem item);

	@Query("DELETE FROM MyItem")
	void deleteAllItems();

	@Query("SELECT * FROM MyItem")
	LiveData<List<MyItem>> getAllItems();

	// Basket
	@Insert
	void insert(MyBasket basket);

	@Update
	void update(MyBasket basket);

	@Delete
	void delete(MyBasket basket);

	@Query("DELETE FROM MyBasket")
	void deleteAllBaskets();

	@Query("SELECT * FROM MyBasket")
	LiveData<List<MyBasket>> getAllBaskets();

//	// Basket with Items
//	@Query("SELECT sb.*, si.*  FROM MyBasket sb JOIN MyItemBasketBinding b ON sb.basketId = b.basketId JOIN MyItem si ON si.itemId = b.itemId WHERE sb.basketId = :basketId")
//	LiveData<Map<MyBasket, List<MyItem>>> getAllItemsInBasket(int basketId);
}
