package com.github.anonisnap.roommanytomanyrelations.data.percistence;

import androidx.lifecycle.LiveData;

import com.github.anonisnap.roommanytomanyrelations.data.entities.MyBasket;

import java.util.List;

public interface MyBasketRepository {
	/**
	 * Inserts a [MyBasket] into Data Storage
	 *
	 * @param basket [MyBasket] which is to be Stored
	 */
	void insert(MyBasket basket);

	/**
	 * Updates an existing [MyBasket] in Data Storage
	 * <br>
	 * Uses [basketId] to find [MyBasket] object to Update
	 *
	 * @param basket [MyBasket] object with [basketId] matching the [MyBasket] to update
	 */
	void update(MyBasket basket);

	/**
	 * Removes a [MyBasket] from Data Storage
	 * <br>
	 * Uses [basketId] to find [MyBasket] object to remove
	 *
	 * @param basket [MyBasket] object with [basketId] matching the [MyBasket] to be removed
	 */
	void delete(MyBasket basket);

	/**
	 * Removes all [MyBasket] objects from Data Storage
	 */
	void deleteAllBaskets();

	/**
	 * Get all [MyBasket] objects in Data Storage
	 * <br>
	 * Can be Observed, and will send an Update to observers upon Data Storage having changes made
	 *
	 * @return [LiveData] of a [List] object, containing all [MyBasket] in Data Storage
	 */
	LiveData<List<MyBasket>> getAllBaskets();

	LiveData<List<MyBasket>> getFilteredItems(int itemId);
}
