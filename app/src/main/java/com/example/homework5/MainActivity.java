package com.example.homework5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    EditText FirstMess, SecondMess;
    EditText EnterEditText;
    TextView EnterTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirstMess = findViewById(R.id.FirstMess);
        SecondMess = findViewById(R.id.SecondMess);

        EnterEditText = findViewById(R.id.EnterEditText);
        EnterTextView = findViewById(R.id.EnterTextView);

//Метод для 2ого пункта, при изменении левого текста параллельно
//меняется правый текст
        FirstMess.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                SecondMess.setText(FirstMess.getText());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
//Конец
//OnCheckedChangeListener задание 7
        CheckBox enableBox = findViewById(R.id.enabled);
        enableBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    buttonView.setText("Включено");
                }
                else{
                    buttonView.setText("Выключено");
                }
            }
        });

        SeekBar seekBar = findViewById(R.id.seekBar);
        TextView textView = findViewById(R.id.seekBarValue);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               textView.setText(String.valueOf(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
       });



}
//Задание 3
    public void EnterOnClick(View view){
        EnterTextView.setText(EnterEditText.getText().toString());
    }
//Задание 4, вызов Toast
    public void SeeToastOnClick(View view){
        Toast toast = Toast.makeText(this,"Вызван Toast",Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP, 1000, 1000);
        toast.show();
    }

    public void SeeSnackBarOnClick(View view){
        Snackbar snackbar = Snackbar.make(view,"Вызван SnackBar", Snackbar.LENGTH_LONG);
        snackbar.setAction("Следующее действие", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(getApplicationContext(),"Вызван новый Toast",Toast.LENGTH_LONG);
                toast.show();
            }
        });
        snackbar.setBackgroundTint(0XFF555555);
        snackbar.setTextColor(0XFF81C784);
        snackbar.show();
    }

    public void onToggleClicked(View view) {
        // включена ли кнопка
        boolean on = ((ToggleButton) view).isChecked();
        if (on) {
            // действия если включена
            Toast.makeText(this, "Свет включен", Toast.LENGTH_SHORT).show();
        } else {
            // действия, если выключена
            Toast.makeText(this, "Свет выключен!",
                    Toast.LENGTH_SHORT).show();
        }
    }

//RadioButtons
    public void onRadioButtonClicked(View view) {
        // если переключатель отмечен
        boolean checked = ((RadioButton) view).isChecked();
        TextView selection = findViewById(R.id.Radioselection);
        // Получаем нажатый переключатель
        switch(view.getId()) {
            case R.id.java:
                if (checked){
                    selection.setText("Выбран Язык Java");
                }
                break;
            case R.id.cPlusPlus:
                if (checked){
                    selection.setText("Выбран Язык C++");
                }
                break;

            case R.id.python:
                if (checked){
                    selection.setText("Выбран Язык Python");
                }
                break;
        }
    }
    public void goToDatePicker(View view){
        Intent intent = new Intent(this, MyDatePicker.class);
        startActivity(intent);
    }

    public void goToTimePicker(View view){
        Intent intent = new Intent(this, MyTimePicker.class);
        startActivity(intent);
    }
}