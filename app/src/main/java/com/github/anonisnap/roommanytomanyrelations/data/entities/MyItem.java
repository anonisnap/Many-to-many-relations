package com.github.anonisnap.roommanytomanyrelations.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "item_table")
public class MyItem {
	@PrimaryKey(autoGenerate = true)
	public int itemId;
	public String itemName;
}
