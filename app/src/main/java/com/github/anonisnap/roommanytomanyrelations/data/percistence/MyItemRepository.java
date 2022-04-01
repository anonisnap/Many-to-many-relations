package com.github.anonisnap.roommanytomanyrelations.data.percistence;

import androidx.lifecycle.LiveData;

import com.github.anonisnap.roommanytomanyrelations.data.entities.MyItem;

import java.util.List;

public interface MyItemRepository {
	/**
	 * Inserts a [MyItem] into Data Storage
	 *
	 * @param item [MyItem] which is to be Stored
	 */
	void insert(MyItem item);

	/**
	 * Updates an existing [MyItem] in Data Storage
	 * <br>
	 * Uses [itemId] to find [MyItem] object to Update
	 *
	 * @param item [MyItem] object with [itemId] matching the [MyItem] to update
	 */
	void update(MyItem item);

	/**
	 * Removes a [MyItem] from Data Storage
	 * <br>
	 * Uses [itemId] to find [MyItem] object to remove
	 *
	 * @param item [MyItem] object with [itemId] matching the [MyItem] to be removed
	 */
	void delete(MyItem item);

	/**
	 * Removes all [MyItem] objects from Data Storage
	 */
	void deleteAllItems();

	/**
	 * Get all [MyItem] objects in Data Storage
	 * <br>
	 * Can be Observed, and will send an Update to observers upon Data Storage having changes made
	 *
	 * @return [LiveData] of a [List] object, containing all [MyItem] in Data Storage
	 */
	LiveData<List<MyItem>> getAllItems();

	LiveData<List<MyItem>> getFilteredItems(int basketId);
}
