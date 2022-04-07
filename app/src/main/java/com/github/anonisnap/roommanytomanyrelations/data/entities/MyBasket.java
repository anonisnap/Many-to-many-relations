package com.github.anonisnap.roommanytomanyrelations.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity()
public class MyBasket {
	@PrimaryKey(autoGenerate = true)
	public int basketId;
	public String basketName;
}
