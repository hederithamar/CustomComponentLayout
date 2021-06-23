package com.example.briix.customcomponentlayout;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.briix.customcomponentlayout.databinding.ActivityMainBinding;
import com.example.briix.customcomponentlayout.models.Information;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mBinding;
    String mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        Information user = new Information("heder.ithamar.hr@gmail.com");
        //Seteo de un objeto a la vista
        mBinding.editEmail.setModel(user);

        //Obtener la instancia del Textinputlayout
        mBinding.editCellphone.getTextInputLayout().requestFocus();
        //Obtener la instancia del EditText

        //Seteo de un objeto a la vista
        mBinding.editEmail.setModel(user);

        Information location = new Information("Avenida principal #123");
        mBinding.editLocation.setModel(location);
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
