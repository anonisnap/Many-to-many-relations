package com.github.anonisnap.roommanytomanyrelations.data.percistence.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.github.anonisnap.roommanytomanyrelations.data.entities.MyBasket;
import com.github.anonisnap.roommanytomanyrelations.data.entities.MyItem;
import com.github.anonisnap.roommanytomanyrelations.data.entities.MyItemBasketBinding;

import java.util.List;

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

	// Item with Baskets <- I know this one is a little weird, but bear with me
	@Query("SELECT sb.* FROM MyBasket sb JOIN MyItemBasketBinding b ON sb.basketId = b.basketId WHERE b.itemId = :itemId")
	LiveData<List<MyBasket>> getAllFilteredBaskets(int itemId);

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

	// Basket with Items
	@Query("SELECT si.* FROM MyItem si JOIN MyItemBasketBinding b ON si.itemId = b.itemId WHERE b.basketId = :basketId")
	LiveData<List<MyItem>> getAllFilteredItems(int basketId);

	// Item and Basket Bindings
	@Insert
	void addBinding(MyItemBasketBinding binding);

	@Query("DELETE FROM MyItemBasketBinding WHERE basketId = :basketId AND itemId = :itemId")
	void removeBinding(int basketId, int itemId);
}
