package com.github.anonisnap.roommanytomanyrelations.ui.putitemintobasket;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.anonisnap.roommanytomanyrelations.R;
import com.github.anonisnap.roommanytomanyrelations.data.percistence.ExampleRepository;
import com.github.anonisnap.roommanytomanyrelations.data.percistence.MyItemBasketBindingRepository;
import com.github.anonisnap.roommanytomanyrelations.data.percistence.MyItemRepository;
import com.github.anonisnap.roommanytomanyrelations.ui.adapters.MyItemAdapter;

public class AddItemToBasketActivity extends AppCompatActivity {
	private RecyclerView itemRecyclerView;
	private MyItemRepository itemRepository;
	private MyItemBasketBindingRepository bindingRepository;

	private EditText basketId, itemId;

	private MyItemAdapter itemAdapter;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_items_to_basket);

		// Bind Classes to Views
		itemRecyclerView = findViewById(R.id.recycler_view_items);
		basketId = findViewById(R.id.edit_basket_id);
		itemId = findViewById(R.id.edit_item_id);

		// Define Button Actions
		findViewById(R.id.btn_add_item_to_basket).setOnClickListener(this::addItemToBasket);

		// Instantiate Repositories
		itemRepository = ExampleRepository.getInstance(getApplication()).getItemRepository();
		bindingRepository = ExampleRepository.getInstance(getApplication()).getBindingRepository();

		// Instantiate Adapters
		itemAdapter = new MyItemAdapter();

		// Bind Adapters to LiveData
		itemRepository.getAllItems().observe(this, itemAdapter::setItemList);

		// Set Layout Managers
		itemRecyclerView.setLayoutManager(new LinearLayoutManager(this));

		// Set Adapters
		itemRecyclerView.setAdapter(itemAdapter);

	}

	private void addItemToBasket(View v) {
		bindingRepository.addBinding(Integer.parseInt(basketId.getText().toString()), Integer.parseInt(itemId.getText().toString()));
		itemId.setText("");
		Toast.makeText(this, "Added Item to Basket", Toast.LENGTH_SHORT).show();
	}
}
