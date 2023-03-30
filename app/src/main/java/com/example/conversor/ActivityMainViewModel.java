package com.example.conversor;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class ActivityMainViewModel extends AndroidViewModel {

    private MutableLiveData<Boolean> tipoCambio;
    private MutableLiveData<Double> cambioMutable;
    private Context context;

    public ActivityMainViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
        cambioMutable = new MutableLiveData<>();
        tipoCambio = new MutableLiveData<>();
    }

    public LiveData<Double> getCambioMutable() {
        if(cambioMutable == null){
            cambioMutable = new MutableLiveData<>();
        }

        return cambioMutable;
    }

    public LiveData<Boolean> getTipoCambio() {
        if (tipoCambio == null) {
            tipoCambio = new MutableLiveData<>();
        }
        return tipoCambio;
    }

    public void setTipoCambio(boolean tipoCambio) {
        this.tipoCambio.setValue(tipoCambio);
    }

    public void setCambioMutable(String dolar, String euros) {

        if(dolar.isEmpty() && euros.isEmpty()){
            alerta();
            return;
        }

        double dolarDouble = 0;
        double eurosDouble = 0;

        if(!dolar.isEmpty()){
            dolarDouble = Double.parseDouble(dolar);
        } else if (!euros.isEmpty()){
            eurosDouble = Double.parseDouble(euros);
        }
        
        if(tipoCambio.getValue()){
            double cambio = dolarDouble * 0.93;
            cambioMutable.setValue(cambio);
        } else {
            double cambio = eurosDouble / 0.93;
            cambioMutable.setValue(cambio);
        }
    }

    public void alerta(){
        Toast.makeText(context, "Debe cargar un valor antes de convertirlo", Toast.LENGTH_LONG).show();
    }

}
