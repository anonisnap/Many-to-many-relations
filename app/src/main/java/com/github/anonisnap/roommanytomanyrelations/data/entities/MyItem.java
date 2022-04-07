package com.github.anonisnap.roommanytomanyrelations.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity()
public class MyItem {
	@PrimaryKey(autoGenerate = true)
	public int itemId;
	public String itemName;
}
