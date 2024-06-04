package com.example.builderpc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private Context context;
    private List<String> componentTypes;



    public RecyclerAdapter(Context context, List<String> componentTypes) {
        this.context = context;
        this.componentTypes = componentTypes;

    }

    public void setComponentTypes(List<String> componentTypes) {
        this.componentTypes = componentTypes;
        notifyDataSetChanged(); // Notify RecyclerView that the data has changed
    }


    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.dropdown_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {
        String componentType = componentTypes.get(position);
        holder.textViewComponentType.setText(componentType);

        // Populate spinner with random data
        List<String> randomData = generateRandomData();
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, randomData);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.spinnerComponent.setAdapter(spinnerAdapter);
    }

    @Override
    public int getItemCount() {
        return componentTypes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView textViewComponentType;
        private Spinner spinnerComponent;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewComponentType = itemView.findViewById(R.id.text_view_component_type);
            spinnerComponent = itemView.findViewById(R.id.spinner_component);
        }
    }

    private List<String> generateRandomData() {
        List<String> randomData = new ArrayList<>();
        randomData.add("Option 1       $100");
        randomData.add("Option 2       $150");
        randomData.add("Option 3       $200");
        // Add more options as needed
        return randomData;
    }
}




