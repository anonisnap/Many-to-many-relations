package com.github.anonisnap.roommanytomanyrelations.data.percistence;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.github.anonisnap.roommanytomanyrelations.data.entities.MyBasket;
import com.github.anonisnap.roommanytomanyrelations.data.entities.MyItem;
import com.github.anonisnap.roommanytomanyrelations.data.percistence.room.ExampleDAO;
import com.github.anonisnap.roommanytomanyrelations.data.percistence.room.ExampleDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExampleRepository {
	private static ExampleRepository instance;
	private final Application app;

	private ExampleRepository(Application app) {
		this.app = app;
	}

	public static synchronized ExampleRepository getInstance(Application app) {
		return instance = (instance != null) ? instance : new ExampleRepository(app);
	}

	public ItemRepository getItemRepository() {
		return new ItemRepository();
	}

	public BasketRepository getBasketRepository() {
		return new BasketRepository();
	}

	private static class ItemRepository implements MyItemRepository {
		private final ExampleDAO dao;
		private final LiveData<List<MyItem>> allItems;
		private final ExecutorService executorService;

		public ItemRepository() {
			dao = ExampleDatabase.getInstance(instance.app).exampleDAO();
			allItems = dao.getAllItems();
			executorService = Executors.newFixedThreadPool(2);
		}

		/**
		 * Inserts a [MyItem] into Data Storage
		 *
		 * @param item [MyItem] which is to be Stored
		 */
		@Override
		public void insert(MyItem item) {
			executorService.execute(() -> dao.insert(item));
		}

		/**
		 * Updates an existing [MyItem] in Data Storage
		 * <br>
		 * Uses [itemId] to find [MyItem] object to Update
		 *
		 * @param item [MyItem] object with [itemId] matching the [MyItem] to update
		 */
		@Override
		public void update(MyItem item) {
			executorService.execute(() -> dao.update(item));
		}

		/**
		 * Removes a [MyItem] from Data Storage
		 * <br>
		 * Uses [itemId] to find [MyItem] object to remove
		 *
		 * @param item [MyItem] object with [itemId] matching the [MyItem] to be removed
		 */
		@Override
		public void delete(MyItem item) {
			executorService.execute(() -> dao.delete(item));
		}

		/**
		 * Removes all [MyItem] objects from Data Storage
		 */
		@Override
		public void deleteAllItems() {
			dao.deleteAllItems();
		}

		/**
		 * Get all [MyItem] objects in Data Storage
		 * <br>
		 * Can be Observed, and will send an Update to observers upon Data Storage having changes made
		 *
		 * @return [LiveData] of a [List] object, containing all [MyItem] in Data Storage
		 */
		@Override
		public LiveData<List<MyItem>> getAllItems() {
			return allItems;
		}

		@Override
		public LiveData<List<MyItem>> getFilteredItems(int basketId) {
			return dao.getAllFilteredItems(basketId);
		}
	}

	private static class BasketRepository implements MyBasketRepository {
		private final ExampleDAO dao;
		private final LiveData<List<MyBasket>> allBaskets;
		private final ExecutorService executorService;

		public BasketRepository() {
			dao = ExampleDatabase.getInstance(instance.app).exampleDAO();
			allBaskets = dao.getAllBaskets();
			executorService = Executors.newFixedThreadPool(2);
		}

		/**
		 * Inserts a [MyBasket] into Data Storage
		 *
		 * @param basket [MyBasket] which is to be Stored
		 */
		@Override
		public void insert(MyBasket basket) {
			executorService.execute(() -> dao.insert(basket));
		}

		/**
		 * Updates an existing [MyBasket] in Data Storage
		 * <br>
		 * Uses [basketId] to find [MyBasket] object to Update
		 *
		 * @param basket [MyBasket] object with [basketId] matching the [MyBasket] to update
		 */
		@Override
		public void update(MyBasket basket) {
			executorService.execute(() -> dao.update(basket));
		}

		/**
		 * Removes a [MyBasket] from Data Storage
		 * <br>
		 * Uses [basketId] to find [MyBasket] object to remove
		 *
		 * @param basket [MyBasket] object with [basketId] matching the [MyBasket] to be removed
		 */
		@Override
		public void delete(MyBasket basket) {
			executorService.execute(() -> dao.delete(basket));
		}

		/**
		 * Removes all [MyBasket] objects from Data Storage
		 */
		@Override
		public void deleteAllBaskets() {
			dao.deleteAllBaskets();
		}

		/**
		 * Get all [MyBasket] objects in Data Storage
		 * <br>
		 * Can be Observed, and will send an Update to observers upon Data Storage having changes made
		 *
		 * @return [LiveData] of a [List] object, containing all [MyBasket] in Data Storage
		 */
		@Override
		public LiveData<List<MyBasket>> getAllBaskets() {
			return allBaskets;
		}

		@Override
		public LiveData<List<MyBasket>> getFilteredItems(int itemId) {
			return dao.getAllFilteredBaskets(itemId);
		}
	}

}
