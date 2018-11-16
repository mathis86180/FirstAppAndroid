package fr.diginamic.formation.firstappandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {

    /**
     * les deux zones de texte de l'application
     * textViewCalcul est créé mais jamais utilisé pour le moment
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("formation","Creation MainActivity");
        textViewResult = findViewById(R.id.textview_result);
        textViewCalcul = findViewById(R.id.textview_calcul);
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
    }

    /**
     * calcul en fonction de l'operateur, du nombre stocké et du nombre actuellement affiché
     * @param view
     */
    public void calculate(View view){
        double resultat = 0;
        double textViewDouble;
        textViewDouble = Double.parseDouble(textViewResult.getText().toString());
        double numberMemoryDouble = Double.parseDouble(numberMemory);
        switch(sign){
            case "+":
                IOperation operationPlus = (a, b) ->  a + b;
                resultat = operationPlus.doOperation(numberMemoryDouble, textViewDouble);
                break;
            case "-":
                IOperation operationLess = (a, b) ->  a - b;
                resultat = operationLess.doOperation(numberMemoryDouble, textViewDouble);

                break;
            case "x":
                IOperation operationMultiply = (a, b) ->  a - b;
                resultat = operationMultiply.doOperation(numberMemoryDouble, textViewDouble);
                break;
            case "/":
                IOperation operationDivide = (a, b) ->  a - b;
                resultat = operationDivide.doOperation(numberMemoryDouble, textViewDouble);
                break;
            case "%":
                IOperation oerationModulo = (a, b) ->  a - b;
                resultat = oerationModulo.doOperation(numberMemoryDouble, textViewDouble);
                break;
        }
        textViewResult.setText(String.valueOf(resultat));
        numberMemory = textViewResult.getText().toString();
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
    }

}
