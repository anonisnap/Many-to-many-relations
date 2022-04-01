package com.github.anonisnap.roommanytomanyrelations.data.percistence.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.github.anonisnap.roommanytomanyrelations.data.entities.MyBasket;
import com.github.anonisnap.roommanytomanyrelations.data.entities.MyItem;

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

	@Query("DELETE FROM item_table")
	void deleteAllItems();

	@Query("SELECT * FROM item_table")
	LiveData<List<MyItem>> getAllItems();

	// Basket
	@Insert
	void insert(MyBasket basket);

	@Update
	void update(MyBasket basket);

	@Delete
	void delete(MyBasket basket);

	@Query("DELETE FROM basket_table")
	void deleteAllBaskets();

	@Query("SELECT * FROM basket_table")
	LiveData<List<MyBasket>> getAllBaskets();
}
