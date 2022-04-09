package com.github.anonisnap.roommanytomanyrelations.data.percistence.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.github.anonisnap.roommanytomanyrelations.data.entities.MyBasket;
import com.github.anonisnap.roommanytomanyrelations.data.entities.MyItem;
import com.github.anonisnap.roommanytomanyrelations.data.entities.MyItemBasketBinding;

@Database(entities = {MyItem.class, MyBasket.class, MyItemBasketBinding.class}, version = 1, exportSchema = false)
public abstract class ExampleDatabase extends RoomDatabase {
	private static ExampleDatabase instance;

	public static ExampleDatabase getInstance(Context context) {
		return instance = (instance != null) ? instance : Room.databaseBuilder(context.getApplicationContext(), ExampleDatabase.class, "database")
		                                                      .fallbackToDestructiveMigration()
		                                                      .build();
	}

	public abstract ExampleDAO exampleDAO();
}
