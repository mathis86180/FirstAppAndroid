package fr.diginamic.formation.firstappandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    /**
     * constantes
     */

    private final String RESULT_KEY = "result";
    private final String FORMATION_KEY = "formation";

    /**
     * variables
     */

    /**
     * les deux zones de texte de l'application
     *
     */
    private TextView textViewResult, textViewCalcul;

    /**
     * saisie de l'utilisateur stockée après avir appuyer sur un opérateur
     */
    private String numberMemory;

    /**
     * stockage de l'opérateur selectionné pour effectué le calcul
     */
    private String sign;

    private boolean stateButton;


    private Button buttonMultiply, buttonLess, buttonPLus, buttonDivide, buttonModulo, buttonEquals;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(FORMATION_KEY,"Creation MainActivity");
        textViewResult = findViewById(R.id.textview_result);
        textViewCalcul = findViewById(R.id.textview_calcul);
        buttonDivide = findViewById(R.id.button_divide);
        buttonEquals = findViewById(R.id.button_equals);
        buttonLess = findViewById(R.id.button_substract);
        buttonModulo = findViewById(R.id.button_modulo);
        buttonMultiply = findViewById(R.id.button_multiply);
        buttonPLus = findViewById(R.id.button_add);
        if(savedInstanceState != null) {

            textViewResult.setText(savedInstanceState.getString(RESULT_KEY));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(RESULT_KEY,textViewResult.getText().toString());
    }

    /**
     * Ajout à la textview de résultat les chiffres tapés par l'utilisateur ainsi que le point pour les nombres à virgule
     * @param view
     */
     public void addCharToText(View view) {
        Button buttonNumber = (Button)view;
        String buttonText = buttonNumber.getText().toString();
        if(textViewResult.getText().equals("0")) {
            textViewResult.setText("");
        }
        textViewResult.setText(textViewResult.getText() + buttonText);
        if(textViewCalcul.getText().equals("0")) {
             textViewCalcul.setText("");
         }
        textViewCalcul.setText(textViewCalcul.getText()+buttonText);
        stateButton = false;
        changeButtonState(stateButton);

    }

    /**
     * stockage de l'opérateur et du nombre saisi
     * @param view
     */
    public void chooseOperator(View view){
        Button buttonOperator = (Button)view;
        sign = buttonOperator.getText().toString();
        numberMemory = textViewResult.getText().toString();
        textViewResult.setText("0");
        textViewCalcul.setText(textViewCalcul.getText()+sign);
        stateButton = true;
        changeButtonState(stateButton);

    }

    /**
     * calcul en fonction de l'operateur, du nombre stocké et du nombre actuellement affiché
     * @param view
     */
    public void calculate(View view){
        double resultat = 0;
        double textViewToDouble;
        textViewToDouble = Double.parseDouble(textViewResult.getText().toString());
        double numberMemoryDouble = Double.parseDouble(numberMemory);
        switch(sign){
            case "+":
                IOperation operationPlus = (a, b) ->  a + b;
                resultat = operationPlus.doOperation(numberMemoryDouble, textViewToDouble);
                break;
            case "-":
                IOperation operationLess = (a, b) ->  a - b;
                resultat = operationLess.doOperation(numberMemoryDouble, textViewToDouble);
                break;
            case "x":
                IOperation operationMultiply = (a, b) ->  a * b;
                resultat = operationMultiply.doOperation(numberMemoryDouble, textViewToDouble);
                break;
            case "/":
                IOperation operationDivide = (a, b) ->  a / b;
                resultat = operationDivide.doOperation(numberMemoryDouble, textViewToDouble);
                break;
            case "%":
                IOperation operationModulo = (a, b) ->  a % b;
                resultat = operationModulo.doOperation(numberMemoryDouble, textViewToDouble);
                break;
        }

        textViewResult.setText(String.valueOf(resultat));
        numberMemory = textViewResult.getText().toString();
        stateButton = true;
        changeButtonState(stateButton);

    }

    /**
     * remise à zéro pour commencer un nouveau calcul
     * @param view
     */
    public void clearResultat(View view){
        numberMemory = "";
        sign = "";
        textViewResult.setText("0");
        textViewCalcul.setText("0");
        stateButton = true;
        changeButtonState(stateButton);
    }

    public void changeButtonState(boolean stateButton){
        if(!stateButton) {
            buttonDivide.setEnabled(true);
            buttonEquals.setEnabled(true);
            buttonLess.setEnabled(true);
            buttonModulo.setEnabled(true);
            buttonMultiply.setEnabled(true);
            buttonPLus.setEnabled(true);
        }else{
            buttonDivide.setEnabled(false);
            buttonEquals.setEnabled(false);
            buttonLess.setEnabled(false);
            buttonModulo.setEnabled(false);
            buttonMultiply.setEnabled(false);
            buttonPLus.setEnabled(false);
        }
    }

    public void openInfoActivity(View view){
        Intent i = new Intent(this,InfoActivity.class);
        i.putExtra("nom","Seigle");
        i.putExtra("prenom","Mathis");
        i.putExtra("formateur","Vincent");
        this.startActivity(i);
    }

}
