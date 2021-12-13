package com.example.track_xpense;

import static java.lang.Float.parseFloat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewExpenseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_expense);
    }

    public void newExpense(View view) throws ParseException {

        Switch s = (Switch) findViewById(R.id.editRecurringSwitch);
        Boolean recurring;
        if (s != null) {
            recurring = true;
        } else {
            recurring = false;
        }
        EditText value = (EditText) findViewById(R.id.editExpenseFloat);
        EditText date = (EditText) findViewById(R.id.editExpenseDate);
        EditText category = (EditText) findViewById(R.id.editExpenseCategory);

        String expenseValue = value.getText().toString();
        String expenseDate = date.getText().toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date d = dateFormat.parse(expenseDate);
        String expenseCategory = category.getText().toString();

        Expense e = new Expense(parseFloat(expenseValue), d, recurring,expenseCategory);
        String je = new Gson().toJson(e);

        Intent intent = new Intent();
        intent.putExtra("expense", je);
        setResult(RESULT_OK, intent);
        finish();
    }
}
