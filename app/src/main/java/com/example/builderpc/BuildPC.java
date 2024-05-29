package com.example.builderpc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class BuildPC extends AppCompatActivity {

    private Spinner spinnerGPU, spinnerCPU, spinnerMotherboard, spinnerSSD, spinnerRAM, spinnerPowerSupply, spinnerCPUFan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_pc);

        // Initialize the spinners
        spinnerGPU = findViewById(R.id.spinner_gpu);
        spinnerCPU = findViewById(R.id.spinner_cpu);
        spinnerMotherboard = findViewById(R.id.spinner_motherboard);
        spinnerSSD = findViewById(R.id.spinner_ssd);
        spinnerRAM = findViewById(R.id.spinner_ram);
        spinnerPowerSupply = findViewById(R.id.spinner_power_supply);
        spinnerCPUFan = findViewById(R.id.spinner_cpu_fan);

        // Populate the spinners with example data
        String[] gpuOptions = {"NVIDIA RTX 3080 - $699", "AMD Radeon RX 6800 XT - $649", "NVIDIA GTX 1660 Super - $229"};
        String[] cpuOptions = {"Intel i9-11900K - $539", "AMD Ryzen 9 5900X - $549", "Intel i5-11600K - $262"};
        String[] motherboardOptions = {"ASUS ROG Strix Z590-E - $379", "MSI MPG B550 Gaming Edge - $189", "Gigabyte Z590 AORUS - $329"};
        String[] ssdOptions = {"Samsung 970 EVO 1TB - $139", "WD Black SN850 1TB - $149", "Crucial P5 1TB - $134"};
        String[] ramOptions = {"Corsair Vengeance 16GB - $84", "G.Skill Trident Z 16GB - $99", "Kingston HyperX Fury 16GB - $79"};
        String[] powerSupplyOptions = {"Corsair RM850x - $129", "EVGA SuperNOVA 750 G5 - $139", "Seasonic Focus GX-750 - $119"};
        String[] cpuFanOptions = {"Noctua NH-D15 - $89", "Cooler Master Hyper 212 - $34", "be quiet! Dark Rock Pro 4 - $89"};

        setupSpinner(spinnerGPU, gpuOptions);
        setupSpinner(spinnerCPU, cpuOptions);
        setupSpinner(spinnerMotherboard, motherboardOptions);
        setupSpinner(spinnerSSD, ssdOptions);
        setupSpinner(spinnerRAM, ramOptions);
        setupSpinner(spinnerPowerSupply, powerSupplyOptions);
        setupSpinner(spinnerCPUFan, cpuFanOptions);
    }

    private void setupSpinner(Spinner spinner, String[] options) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}