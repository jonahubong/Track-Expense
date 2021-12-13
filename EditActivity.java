package com.example.track_xpense;

import static java.lang.Float.parseFloat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EditActivity extends AppCompatActivity {

    List<Expense> editExpenseList = new ArrayList<>();
    Expense e = new Expense(Float.valueOf(0), Calendar.getInstance().getTime(), false, "Category");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_expense);
        Intent intent = getIntent();
        String expense = intent.getExtras().getString("expense");
        e = new Gson().fromJson(expense, Expense.class);
        String array = intent.getExtras().getString("array");
        Type type = new TypeToken<List<Expense>>(){}.getType();
        editExpenseList = new Gson().fromJson(array, type);

        EditText value = findViewById(R.id.editExpenseFloat);
        EditText date = findViewById(R.id.editExpenseDate);
        EditText category = findViewById(R.id.editExpenseCategory);

        value.setText(String.valueOf(e.value));
        date.setText(String.valueOf(e.date));
        category.setText(e.category);

        final Button button = findViewById(R.id.expenseEdit);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Log.i("HELP", "Edit button clicked.");
                // Code here executes on main thread after user presses button
                Switch s = findViewById(R.id.editRecurringSwitch);
                boolean recurring;
                recurring = s != null;
                EditText value = findViewById(R.id.editExpenseFloat);
                EditText date = findViewById(R.id.editExpenseDate);
                EditText category = findViewById(R.id.editExpenseCategory);

                String expenseValue = value.getText().toString();
                String expenseDate = date.getText().toString();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date d = null;
                try {
                    d = dateFormat.parse(expenseDate);
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }
                String expenseCategory = category.getText().toString();

                e.value = parseFloat(expenseValue);
                e.date = d;
                e.category = expenseCategory;
                e.recurring = recurring;

                editExpenseList.add(e);
                saveData();

            }
        });
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(editExpenseList);
        editor.putString("expenseList", json);
        editor.apply();
    }

}
