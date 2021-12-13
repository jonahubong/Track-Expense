package com.example.track_xpense;

import java.util.Date;
import java.util.List;

public class Expense {
    Float value;
    Date date;
    boolean recurring;
    String category;

    public Expense(Float value, Date date, Boolean recurring, String category) {
        this.value = value;
        this.date = date;
        this.recurring = recurring;
        this.category = category;
    }

    public Expense getExpense() {
        return this;
    }

    public String toString() {
        return this.value.toString() + " " + this.date.toString();
    }
}
