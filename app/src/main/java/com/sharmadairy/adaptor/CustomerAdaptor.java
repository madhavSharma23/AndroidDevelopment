package com.sharmadairy.adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sharmadairy.R;
import com.sharmadairy.model.Customer;

import java.util.ArrayList;

public class CustomerAdaptor extends RecyclerView.Adapter<CustomerAdaptor.CustomerViewHolder> {

    ArrayList<Customer> dataHolder;

    public CustomerAdaptor(ArrayList<Customer> dataHolder) {
        this.dataHolder = dataHolder;
    }

    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
       return new CustomerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {
        holder.name.setText(dataHolder.get(position).getName());
        holder.shift.setText(dataHolder.get(position).getShift());
        holder.mobile.setText(dataHolder.get(position).getMobile());
        holder.quantity.setText(dataHolder.get(position).getQuantity());

        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentQuantity = Integer.parseInt(holder.quantity.getText().toString());
                holder.quantity.setText(String.valueOf(currentQuantity+1));
            }
        });

        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentQuantity = Integer.parseInt(holder.quantity.getText().toString());
                holder.quantity.setText(String.valueOf(currentQuantity-1));
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataHolder.size();
    }

    class CustomerViewHolder extends RecyclerView.ViewHolder{

        TextView name,mobile,shift,quantity;
        ImageButton plus,minus;

        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            mobile = itemView.findViewById(R.id.mobile);
            shift = itemView.findViewById(R.id.shift_s);
            quantity = itemView.findViewById(R.id.quantity_s);
            plus = itemView.findViewById(R.id.btnPlus);
            minus = itemView.findViewById(R.id.btnMinus);
        }
    }
}
