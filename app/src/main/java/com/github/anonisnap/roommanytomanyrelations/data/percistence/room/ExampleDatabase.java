package com.github.anonisnap.roommanytomanyrelations.data.percistence.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.github.anonisnap.roommanytomanyrelations.data.entities.MyBasket;
import com.github.anonisnap.roommanytomanyrelations.data.entities.MyItem;

@Database(entities = {MyItem.class, MyBasket.class}, version = 3, exportSchema = false)
public abstract class ExampleDatabase extends RoomDatabase {
	private static ExampleDatabase instance;

	public abstract ExampleDAO exampleDAO();

	public static ExampleDatabase getInstance(Context context) {
		return instance = (instance != null) ? instance : Room.databaseBuilder(context.getApplicationContext(), ExampleDatabase.class, "item_basket_database").fallbackToDestructiveMigration().build();
	}
}
