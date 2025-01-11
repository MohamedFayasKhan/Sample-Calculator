package com.mohamedfayaskhan.mycalculator;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView textOutput = findViewById(R.id.text_output);
        RecyclerView recyclerView = findViewById(R.id.recycler_input);
        List<String> items = new ArrayList<>();
        items.add("+");
        items.add("-");
        items.add("*");
        items.add("/");
        for (int i = 1; i < 10; i++) items.add(String.valueOf(i));
        items.add("0");
        items.add("C");
        items.add("=");
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        CalculatorInputAdapter adapter = new CalculatorInputAdapter(items, item -> {
            switch (item) {
                case "=":
                    String output = calculate(textOutput.getText().toString());
                    if (output != null) {
                        textOutput.setText(output);
                    }
                    break;
                case "C":
                    textOutput.setText("");
                    break;
                default:
                    String value = textOutput.getText().toString() + item;
                    textOutput.setText(value);
                    break;
            }
        });

        recyclerView.setAdapter(adapter);
    }

    private String calculate(String data) {
        List<Integer> numbers = new ArrayList<>();
        List<String> operators = new ArrayList<>();
        StringBuilder number = new StringBuilder();
        for (char c : data.toCharArray()) {
            if (!Arrays.asList("+", "-", "*", "/").contains(String.valueOf(c))) {
                number.append(c);
            } else {
                numbers.add(Integer.parseInt(number.toString()));
                number.setLength(0);
                operators.add(String.valueOf(c));
            }
        }
        if (!number.toString().isEmpty()) {
            numbers.add(Integer.parseInt(number.toString()));
        }
        if ((numbers.size() - 1) == operators.size()) {
            int d = numbers.get(0);
            for (int i = 0; i < operators.size(); i++) {
                switch (operators.get(i)) {
                    case "+":
                        d += numbers.get(i+1);
                        break;
                    case "-":
                        d -= numbers.get(i+1);
                        break;
                    case "*":
                        d *= numbers.get(i+1);
                        break;
                    case "/":
                        d /= numbers.get(i+1);
                        break;
                    default:
                        Toast.makeText(this, "Invalid operator", Toast.LENGTH_SHORT).show();
                        return null;
                }
            }
            return String.valueOf(d);
        } else {
            Toast.makeText(this, "Invalid calculation", Toast.LENGTH_SHORT).show();
        }
        return null;
    }
}