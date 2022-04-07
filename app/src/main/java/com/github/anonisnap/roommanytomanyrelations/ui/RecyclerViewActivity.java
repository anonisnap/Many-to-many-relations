package com.github.anonisnap.roommanytomanyrelations.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.anonisnap.roommanytomanyrelations.R;
import com.github.anonisnap.roommanytomanyrelations.data.percistence.ExampleRepository;
import com.github.anonisnap.roommanytomanyrelations.data.percistence.MyBasketRepository;
import com.github.anonisnap.roommanytomanyrelations.data.percistence.MyItemRepository;
import com.github.anonisnap.roommanytomanyrelations.ui.adapters.MyBasketAdapter;
import com.github.anonisnap.roommanytomanyrelations.ui.adapters.MyItemAdapter;

public class RecyclerViewActivity extends AppCompatActivity {
	private RecyclerView topRecyclerView, bottomRecyclerView;
	private MyBasketRepository basketRepository;
	private MyItemRepository itemRepository;

	private EditText topFilterText, bottomFilterText;

	private MyItemAdapter itemAdapter;
	private MyBasketAdapter basketAdapter;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_basket_and_item_list_view);

		// Bind Classes to Views
		topRecyclerView = findViewById(R.id.recycler_view_top);
		bottomRecyclerView = findViewById(R.id.recycler_view_bottom);
		topFilterText = findViewById(R.id.edit_select_top_id);
		bottomFilterText = findViewById(R.id.edit_select_bottom_id);

		// Define Button Actions
		findViewById(R.id.btn_select_from_top_id).setOnClickListener(this::filterByBasketId);
		findViewById(R.id.btn_select_from_bottom_id).setOnClickListener(this::filterByItemId);

		// Instantiate Repositories
		basketRepository = ExampleRepository.getInstance(getApplication()).getBasketRepository();
		itemRepository = ExampleRepository.getInstance(getApplication()).getItemRepository();

		// Instantiate Adapters
		basketAdapter = new MyBasketAdapter();
		itemAdapter = new MyItemAdapter();

		// Bind Adapters to LiveData
		basketRepository.getAllBaskets().observe(this, basketAdapter::setBasketList);
		itemRepository.getAllItems().observe(this, itemAdapter::setItemList);

		// Set Layout Managers
		topRecyclerView.setLayoutManager(new LinearLayoutManager(this));
		bottomRecyclerView.setLayoutManager(new LinearLayoutManager(this));

		// Set Adapters
		topRecyclerView.setAdapter(basketAdapter);
		bottomRecyclerView.setAdapter(itemAdapter);
	}

	private void filterByBasketId(View view) {
		if (topFilterText.getText().toString().equals("")) {
			clearFilters();
			return;
		}
		itemRepository.getAllItems().removeObserver(itemAdapter::setItemList);
		itemRepository.getFilteredItems(Integer.parseInt(topFilterText.getText().toString())).observe(this, itemAdapter::setItemList);
		// TODO: Implement Filter through a Mixed Object Repository
	}

	private void filterByItemId(View view) {
		if (bottomFilterText.getText().toString().equals("")) {
			clearFilters();
			return;
		}
		basketRepository.getAllBaskets().removeObserver(basketAdapter::setBasketList);
		basketRepository.getFilteredItems(Integer.parseInt(bottomFilterText.getText().toString()))
		                .observe(this, basketAdapter::setBasketList);
		// TODO: Implement Filter through a Mixed Object Repository
	}

	private void clearFilters() {
		basketRepository.getAllBaskets().observe(this, basketAdapter::setBasketList);
		itemRepository.getAllItems().observe(this, itemAdapter::setItemList);
	}
}
