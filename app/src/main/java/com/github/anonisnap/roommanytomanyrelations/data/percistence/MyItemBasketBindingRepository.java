package com.github.anonisnap.roommanytomanyrelations.data.percistence;

public interface MyItemBasketBindingRepository {
	void addBinding(int basketId, int itemId);

	void removeBinding(int basketId, int itemId);
}
