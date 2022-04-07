package com.github.anonisnap.roommanytomanyrelations.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity()
public class MyItemBasketBinding {
	@PrimaryKey(autoGenerate = true)
	public int bindingId;
	public int basketId;
	public int itemId;
}
