package fr.diginamic.formation.firstappandroid;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class InfoActivity extends AppCompatActivity {


    private TextView textNom, textPrenom, textFormateur;
    private EditText editSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        textNom = findViewById(R.id.textview_nom);
        textPrenom = findViewById(R.id.textview_prenom);
        textFormateur = findViewById(R.id.textview_formateur);

        textNom.setText(getIntent().getStringExtra("nom"));
        textPrenom.setText(getIntent().getStringExtra("prenom"));
        textFormateur.setText(getIntent().getStringExtra("formateur"));

    }

    public void finishActivity(View view) {
        this.finish();
    }

    public void callNumber(View view) {
        Intent i = new Intent(Intent.ACTION_DIAL);
        i.setData(Uri.parse("tel:+33612324585"));
        startActivity(i);
    }

    public void sendMail(View view) {
        String uriText =
                "mailto:seigle.mathis@gmail.com" +
                        "?subject=" + Uri.encode("envoi depuis app android") +
                        "&body=" + Uri.encode("j'ai réussi !!!!");

        Uri uri = Uri.parse(uriText);
        Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
        sendIntent.setData(uri);
        startActivity(Intent.createChooser(sendIntent, "Send email"));
    }

    public void startSearch(View view) {
        editSearch = findViewById(R.id.editText_search);
        String search = editSearch.getText().toString();

        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(SearchManager.QUERY, search); // query contains search string
        startActivity(intent);
    }
}
