package com.example.conversor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.example.conversor.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ActivityMainViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(ActivityMainViewModel.class);

        binding.rgMoneda.setOnCheckedChangeListener((group, checkedId) -> {
            if(checkedId == R.id.rbDolares){
                viewModel.setTipoCambio(true);
            } else {
                viewModel.setTipoCambio(false);
            }
        });

        viewModel.getTipoCambio().observe(this, cambio -> {
            if(cambio == true){
                binding.etDolares.setEnabled(true);
                binding.etEuros.setEnabled(false);
            } else {
                binding.etEuros.setEnabled(true);
                binding.etDolares.setEnabled(false);
            }
        });

        binding.btConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.setCambioMutable(binding.etDolares.getText().toString(), binding.etEuros.getText().toString());
            }
        });

        viewModel.getCambioMutable().observe(this, cambio -> {
            binding.tvCambio.setText(String.valueOf(cambio));
        });

    }
}