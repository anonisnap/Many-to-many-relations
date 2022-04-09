package com.github.anonisnap.roommanytomanyrelations.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.github.anonisnap.roommanytomanyrelations.R;
import com.github.anonisnap.roommanytomanyrelations.data.entities.MyBasket;
import com.github.anonisnap.roommanytomanyrelations.data.entities.MyItem;
import com.github.anonisnap.roommanytomanyrelations.data.percistence.ExampleRepository;
import com.github.anonisnap.roommanytomanyrelations.data.percistence.MyBasketRepository;
import com.github.anonisnap.roommanytomanyrelations.data.percistence.MyItemRepository;
import com.github.anonisnap.roommanytomanyrelations.ui.basketanditemlist.RecyclerViewActivity;
import com.github.anonisnap.roommanytomanyrelations.ui.putitemintobasket.AddItemToBasketActivity;

public class MainActivity extends AppCompatActivity {
	private MyItemRepository itemRepository;
	private MyBasketRepository basketRepository;

	private EditText textItemName;
	private EditText textBasketName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Instantiate Repositories
		itemRepository = ExampleRepository.getInstance(getApplication()).getItemRepository();
		basketRepository = ExampleRepository.getInstance(getApplication()).getBasketRepository();

		// Bind Editable Text Views
		textItemName = findViewById(R.id.edit_new_item_name);
		textBasketName = findViewById(R.id.edit_new_basket_name);

		// Set Button Actions
		findViewById(R.id.btn_new_create_item).setOnClickListener(this::createNewItem);
		findViewById(R.id.btn_new_create_basket).setOnClickListener(this::createNewBasket);

		findViewById(R.id.btn_navigate_to_list).setOnClickListener(this::navigateToList);
		findViewById(R.id.btn_navigate_to_add_item_to_basket).setOnClickListener(this::navigateToBinding);
	}

	private void createNewItem(View view) {
		if (textItemName.getText().toString().equals("")) {
			return;
		}
		MyItem _newItem = new MyItem();
		_newItem.itemName = textItemName.getText().toString();
		textItemName.setText("");
		itemRepository.insert(_newItem);
	}

	private void createNewBasket(View view) {
		if (textBasketName.getText().toString().equals("")) {
			return;
		}
		MyBasket _newBasket = new MyBasket();
		_newBasket.basketName = textBasketName.getText().toString();
		textBasketName.setText("");
		basketRepository.insert(_newBasket);
	}

	private void navigateToList(View view) {
		startActivity(new Intent(this, RecyclerViewActivity.class));
	}

	private void navigateToBinding(View view) {
		startActivity(new Intent(this, AddItemToBasketActivity.class));
	}
}
