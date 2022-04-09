package com.github.anonisnap.roommanytomanyrelations.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.anonisnap.roommanytomanyrelations.R;
import com.github.anonisnap.roommanytomanyrelations.data.entities.MyItem;

import java.util.ArrayList;
import java.util.List;

public class MyItemAdapter extends RecyclerView.Adapter<MyItemAdapter.ViewHolder> {
	private List<MyItem> itemList;

	public MyItemAdapter() {
		itemList = new ArrayList<>();
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View view = inflater.inflate(R.layout.list_card_simple_template, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		holder.txtId.setText(String.valueOf(itemList.get(position).itemId));
		holder.txtName.setText(itemList.get(position).itemName);
	}

	@Override
	public int getItemCount() {
		return itemList.size();
	}

	public void setItemList(List<MyItem> itemList) {
		this.itemList = (itemList != null) ? itemList : this.itemList;
		notifyDataSetChanged();
	}

	public class ViewHolder extends RecyclerView.ViewHolder {

		private final TextView txtId, txtName;

		public ViewHolder(@NonNull View itemView) {
			super(itemView);
			this.txtId = itemView.findViewById(R.id.card_text_id);
			this.txtName = itemView.findViewById(R.id.card_text_name);
		}
	}
}
