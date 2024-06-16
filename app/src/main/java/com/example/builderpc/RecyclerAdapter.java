package com.example.builderpc;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private Context Context;
    private Map<String, List<Component>> componentsMap;
    private List<String> componentTypes;
    private BuildPC buildPC;
    private OnComponentSelectedListener listener;

    public RecyclerAdapter(Context context, Map<String, List<Component>> componentsMap, OnComponentSelectedListener listener, BuildPC buildPC){
        this.Context = context;
        this.componentsMap = componentsMap;
        this.componentTypes = new ArrayList<>(componentsMap.keySet());
        this.buildPC=buildPC;
        this.listener=listener;
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dropdown_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {
        if (position < componentTypes.size()) {
            String componentType = componentTypes.get(position);
            List<Component> components = componentsMap.get(componentType);

            holder.componentTextView.setText(componentType);

            // Set the ArrayAdapter for the Spinner
            ArrayAdapter<Component> adapter = new ArrayAdapter<>(Context, android.R.layout.simple_spinner_item, components);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            holder.componentSpinner.setAdapter(adapter);

            holder.componentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                Component previousComponent = null;
                boolean userSelect = false;

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (userSelect) {
                        Component selectedComponent = (Component) parent.getItemAtPosition(position);
                        if (selectedComponent.getId() != -1) { // Ignore default "SELECT ITEM" message
                            if (previousComponent != null) {
                                buildPC.updateTotalPrice(-previousComponent.getPrice()); // Subtract price of previously selected component
                            }
                            buildPC.updateTotalPrice(selectedComponent.getPrice()); // Add price of newly selected component
                            previousComponent = selectedComponent; // Update previously selected component

                        }
                    } else {
                        userSelect = true;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // Do nothing
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return componentsMap.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView componentTextView;
        Spinner componentSpinner;

        public MyViewHolder(View itemView) {
            super(itemView);
            componentTextView = itemView.findViewById(R.id.categoryTextView);
            componentSpinner = itemView.findViewById(R.id.componentSpinner);
        }
    }



}






