package com.example.chisobeophi;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainlayout);

        EditText heightInput = findViewById(R.id.heightInput);
        EditText weightInput = findViewById(R.id.weightInput);
        Button calculateButton = findViewById(R.id.calculateButton);
        TextView resultText = findViewById(R.id.resultText);
        ImageView imageViewLevel = findViewById(R.id.imageViewLevel);
        Button clearButton = findViewById(R.id.clearButton);


        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String heightStr = heightInput.getText().toString();
                String weightStr = weightInput.getText().toString();

                if (!heightStr.isEmpty() && !weightStr.isEmpty()) {

                    int heightInCm = Integer.parseInt(heightStr);
                    double weight = Double.parseDouble(weightStr);


                    double heightInMeters = heightInCm / 100.0;
                    double bmi = weight / (heightInMeters * heightInMeters);


                    int fractionalHeight = heightInCm % 100;
                    double idealWeight = fractionalHeight * 9 / 10.0;


                    String bmiResult;
                    if (bmi < 18.5) {
                        bmiResult = "Bạn gầy";
                        imageViewLevel.setImageResource(R.drawable.gay);
                    } else if (bmi < 24.9) {
                        bmiResult = "Bạn bình thường";
                        imageViewLevel.setImageResource(R.drawable.bth);
                    } else if (bmi < 29.9) {
                        bmiResult = "Bạn tăng cân";
                        imageViewLevel.setImageResource(R.drawable.tangcan);
                    } else if (bmi < 34.9) {
                        bmiResult = "Bạn bị béo phì độ 1";
                        imageViewLevel.setImageResource(R.drawable.lv1);
                    } else if (bmi < 39.9) {
                        bmiResult = "Bạn bị béo phì độ 2";
                        imageViewLevel.setImageResource(R.drawable.lv2);
                    } else {
                        bmiResult = "Bạn bị béo phì độ 3";
                        imageViewLevel.setImageResource(R.drawable.lv3);
                    }


                    String weightStatus;
                    if (weight > idealWeight) {
                        weightStatus = "Bạn thừa " + String.format("%.2f", (weight - idealWeight)) + " kg";
                    } else if (weight < idealWeight) {
                        weightStatus = "Bạn thiếu " + String.format("%.2f", (idealWeight - weight)) + " kg";
                    } else {
                        weightStatus = "Cân nặng của bạn đang lý tưởng. Cố gắng duy trì!";
                    }


                    resultText.setText("Chỉ số BMI của bạn là: " + String.format("%.2f", bmi) + "\n" + bmiResult + "\n" +
                            "Cân nặng lý tưởng: " + String.format("%.2f", idealWeight) + " kg\n" + weightStatus);
                } else {
                    resultText.setText("Vui lòng nhập đầy đủ chiều cao và cân nặng.");
                }
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xóa dữ liệu trong EditText và TextView
                heightInput.setText("");
                weightInput.setText("");
                resultText.setText("Kết quả sẽ hiển thị ở đây");

                // Xóa hình ảnh trong ImageView
                imageViewLevel.setImageResource(0);
            }
        });
    }
}
