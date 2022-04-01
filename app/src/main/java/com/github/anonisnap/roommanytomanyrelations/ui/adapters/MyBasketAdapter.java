package com.github.anonisnap.roommanytomanyrelations.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.anonisnap.roommanytomanyrelations.R;
import com.github.anonisnap.roommanytomanyrelations.data.entities.MyBasket;

import java.util.ArrayList;
import java.util.List;

public class MyBasketAdapter extends RecyclerView.Adapter<MyBasketAdapter.ViewHolder> {
	private List<MyBasket> basketList;

	public MyBasketAdapter() {
		basketList = new ArrayList<>();
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
		holder.txtId.setText(String.valueOf(basketList.get(position).basketId));
		holder.txtName.setText(basketList.get(position).basketName);
	}

	@Override
	public int getItemCount() {
		return basketList.size();
	}

	public void setBasketList(List<MyBasket> basketList) {
		this.basketList = (basketList != null) ? basketList : this.basketList;
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
