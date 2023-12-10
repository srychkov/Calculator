package at.ac.tgm.hit.sew7.srychkov.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import android.view.MenuItem;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class MainActivity extends Activity {

    public EditText e1, e2 = null;
    TextView result = null;
    int num1, num2 = 0;
    RadioButton add, sub, mul, div = null;
    int welche = R.id.add;
    Button berechnen, ms, mr = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);

        e1 = findViewById(R.id.wert1);
        e2 = findViewById(R.id.wert2);
        add = findViewById(R.id.add);
        sub = findViewById(R.id.sub);
        mul = findViewById(R.id.mul);
        div = findViewById(R.id.div);
        berechnen = findViewById(R.id.berechnen);
        ms = findViewById(R.id.ms);
        mr = findViewById(R.id.mr);
        result = findViewById(R.id.result);
        onStart();
        add.setOnClickListener(new RadioButton.OnClickListener() {
            public void onClick(View v) {
                welche = R.id.add;
            }
        });
        sub.setOnClickListener(new RadioButton.OnClickListener() {
            public void onClick(View v) {
                welche = R.id.sub;
            }
        });
        mul.setOnClickListener(new RadioButton.OnClickListener() {
            public void onClick(View v) {
                welche = R.id.mul;
            }
        });
        div.setOnClickListener(new RadioButton.OnClickListener() {
            public void onClick(View v) {
                welche = R.id.div;
            }
        });
        berechnen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    double wert1 = Double.parseDouble(e1.getText().toString());
                    double wert2 = Double.parseDouble(e2.getText().toString());
                    double solution = wert1 + wert2;
                    Log.i("a",Double.toString(solution));
                    if (welche == R.id.add) {// Addition auswählen
                        solution = wert1 + wert2;
                        result.setText(Double.toString(solution));
                    } else if (welche==R.id.sub) {// Subtraktion auswählen
                        solution = (wert1 - wert2);
                        result.setText(Double.toString(solution));
                    } else if (welche==R.id.mul) {// Multiplikation auswählen
                        solution = (wert1 * wert2);
                        result.setText(Double.toString(solution));
                    } else if (welche==R.id.div) {
                        if (wert1 != 0 && wert2 != 0) {
                            solution = (wert1 / wert2);
                            result.setText(Double.toString(solution));
                            result.setText(Double.toString(solution));
                        } else {
                            result.setText("Error");
                            return;
                        }
                    } else {
                        result.setText("Error");
                        return;
                    }

                        //Farben
                        if (solution > 0) {
                            result.setTextColor(Color.parseColor("#000000"));
                            return;
                        } else if (solution < 0) {
                            result.setTextColor(Color.parseColor("#FF0000"));
                            return;
                        } else {
                            result.setTextColor(Color.parseColor("#FFFFFF"));
                            return;
                        }

                } catch (Exception exc) {
                    result.setText(exc.getLocalizedMessage());
                    System.out.println(exc.getLocalizedMessage());
                }
            }
        });

        SharedPreferences sharedPreferences;
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        Button msButton = findViewById(R.id.ms);

        //Beim Klick des MS-Buttons
        msButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Speichern der Werte
             * @param v das View
             */
            @Override
            public void onClick(View v) {
                // Get the values you want to save
                EditText text1 = findViewById(R.id.wert1);
                EditText text2 = findViewById(R.id.wert2);
                String wert1t = text1.getText().toString();
                String wert2t = text2.getText().toString();

                result.setOnTouchListener(new View.OnTouchListener() {
                    /**
                     * Löschen von Berechnung
                     * @param view Das View
                     * @param motionEvent Das Event des Klicks
                     * @return false
                     */
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        TextView answer = findViewById(R.id.result);
                        answer.setText("0");
                        answer.setTextColor(Color.parseColor("#FFFFFF"));
                        return false;
                    }
                });

                // Werte werden gespeichert
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Wert1", wert1t);
                editor.putString("Wert2", wert2t);
                editor.apply();

                // Nachricht das gespeichert wurde
                Toast.makeText(getApplicationContext(), "Saved!", Toast.LENGTH_SHORT).show();
            }
        });

        Button mrButton = findViewById(R.id.mr);
        mrButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Einsetzen der Werte
             * @param v das View
             */
            @Override
            public void onClick(View v) {
                // gespeicherte Werte ermitteln
                String wert1t = sharedPreferences.getString("Wert1", "");
                String wert2t = sharedPreferences.getString("Wert2", "");
                EditText text1 = findViewById(R.id.wert1);
                EditText text2 = findViewById(R.id.wert2);

                // Werte einsetzen
                text1.setText(wert1t);
                text2.setText(wert2t);
            }
        });
    }

    public void onStart(){
        super.onStart();
        berechnen.setActivated(true);
    }

    public void clear(){
        EditText text1 = findViewById(R.id.wert1);
        EditText text2 = findViewById(R.id.wert2);
        TextView answer = findViewById(R.id.result);
        text1.setText("");
        text2.setText("");
        answer.setText("0");
        answer.setTextColor(Color.parseColor("#FFFFFF"));
    }
}