package com.example.briix.customcomponentlayout;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.briix.customcomponentlayout.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mBinding;
    String mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        //Metodo construido en la clase CustomView
        mBinding.editEmail.setText("heder.ithamar.hr@gmail.com");
        //Obtener la instancia del Textinputlayout
        mBinding.editCellphone.getTextInputLayout().requestFocus();
        //Obtener la instancia del EditText
        mBinding.editLocation.getEditText().setEnabled(false);

        mBinding.buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mensaje = mBinding.editEmail.getText();
                Toast.makeText(MainActivity.this, mensaje, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
