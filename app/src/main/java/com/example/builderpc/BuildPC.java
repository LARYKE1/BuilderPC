package com.example.builderpc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class BuildPC extends AppCompatActivity implements OnComponentSelectedListener {

    private RecyclerView rv;
    private RecyclerAdapter adapter;
    private AppDatabase database;
    private ComponentDAO componentDao;
    private Executor executor = Executors.newSingleThreadExecutor();
    private Map<String, List<Component>> componentsMap;
    private Button btn,btnClean;
    private SaveBuildDao saveBuildDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_pc);

        database = AppDatabase.getInstance(this); // Initialize database here
        componentDao = database.componentDao();

        rv = findViewById(R.id.recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(this));

        componentsMap = new HashMap<>(); // Initialize componentsMap

        btn=findViewById(R.id.saveButton);

        btn.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {

                                   }
                               });
        btn.setOnClickListener(v -> clearConfiguration());

        getData();// Fetch data from database

        // Note: RecyclerView and adapter setup will happen asynchronously in getData()
    }



    private void getData() {
        executor.execute(() -> {
            List<Component> components = componentDao.getAllComponents();

            Log.d("BuildPC", "Number of components fetched from database: " + components.size());

            for (Component component : components) {
                String type = component.getType();

                if (componentsMap.containsKey(type)) {
                    componentsMap.get(type).add(component);
                } else {
                    List<Component> list = new ArrayList<>();
                    list.add(component);
                    componentsMap.put(type, list);
                }
            }
            Log.d("BuildPC", "Number of component types in componentsMap: " + componentsMap.size());

            // Update UI on the main thread
            runOnUiThread(() -> {
                adapter = new RecyclerAdapter(this, componentsMap, (OnComponentSelectedListener) this, this);
                rv.setAdapter(adapter);
            });
        });
    }

    private void clearConfiguration() {
        // Reset the total price
        TextView totalPriceTextView = findViewById(R.id.totalPriceTextView);
        totalPriceTextView.setText("Total Price: $0.00");

        // Reset the spinner to its default position
        // This depends on how you're storing the current configuration
        // Assuming you have a reference to your adapter

    }

    public void updateTotalPrice(double price) {
        TextView totalPriceTextView = findViewById(R.id.totalPriceTextView);
        String currentTotalString = totalPriceTextView.getText().toString().replaceAll("[^\\d.]", "");
        double currentTotal = Double.parseDouble(currentTotalString);
        double newTotal = currentTotal + price;
        DecimalFormat df = new DecimalFormat("#.##");
        totalPriceTextView.setText("Total Price: $" + df.format(newTotal));
    }

    @Override
    public void onComponentSelected(String componentType, Component selectedComponent) {
        // Filter components of subsequent spinners based on selected component
        for (Map.Entry<String, List<Component>> entry : componentsMap.entrySet()) {
            String nextComponentType = entry.getKey();
            List<Component> nextComponents = entry.getValue();
// If nextComponentType is either "GPU" or "Power Supply", skip the filtering process
            if (nextComponentType.equals("GPU") || nextComponentType.equals("Power Supply")) {
                continue;
            }


        }

    }


}


