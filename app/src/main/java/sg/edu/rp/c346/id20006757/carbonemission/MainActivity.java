package sg.edu.rp.c346.id20006757.carbonemission;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity {
    EditText vehicleCode,distanceInput,pno;
    Button reset,calculation,history,change,moreInfo;
    TextView output;
    RadioButton diesel,petrol,electric;
    RadioGroup fuel;
    boolean checked,VC ;
    @SuppressLint("SimpleDateFormat")
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    String data = df.format(new Date());



    @SuppressWarnings("Convert2Lambda")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_main);
        //Change actionbar title
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));

        //Linking of the UI items to variable name
        distanceInput = findViewById(R.id.distanceID);
        reset = findViewById(R.id.resetbutton);
        calculation = findViewById(R.id.calculatebutton);
        history = findViewById(R.id.historybutton);
        output = findViewById(R.id.tvdisplay);
        vehicleCode = findViewById(R.id.vehicleID);
        diesel = findViewById(R.id.DieselRadioButton);
        petrol = findViewById(R.id.PetrolRadioButton);
        electric = findViewById(R.id.ElectricRadioButton);
        fuel = findViewById(R.id.fuelradiogroup);
        pno = findViewById(R.id.passengerID);
        moreInfo = findViewById(R.id.redirect);
        registerForContextMenu(moreInfo);
        change = findViewById(R.id.languagebutton);



        moreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }


        });


        calculation.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("RedundantIfStatement")
            @SuppressLint({"SetTextI18n", "DefaultLocale"})
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(MainActivity.this);
                // Code for the action
                String R0 = vehicleCode.getText().toString();
                String R1 = pno.getText().toString();
                String R2 = distanceInput.getText().toString();


                //Checks if radio button is selected
                if (fuel.getCheckedRadioButtonId() == -1) {
                    //No Radio button is clicked
                    checked = false;
                } else {
                    //Radio button is clicked
                    checked = true;
                }

                // Checks for correct vehicle code
                if (R0.equalsIgnoreCase("BS") || R0.equalsIgnoreCase("SMB")
                        || R0.equalsIgnoreCase("MMB") || R0.equalsIgnoreCase("LMB")
                        || R0.equalsIgnoreCase("MC") || R0.equalsIgnoreCase("SMC")
                        || R0.equalsIgnoreCase("AC") || R0.equalsIgnoreCase("MPV")
                        || R0.equalsIgnoreCase("LC") || R0.equalsIgnoreCase("SC")) {
                    VC = true;

                } else {
                    VC = false;
                }


                if (electric.isChecked()) {
                    if (R0.equalsIgnoreCase("BS") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("en")) {

                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 47.4;
                        output.setText(getResources().getText(R.string.output).toString() + " " + getResources().getText(R.string.EB).toString() + " " +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "BS";
                        String finalF = "Electric";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("BS") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("ms")) {
                        double finalR1 = Double.parseDouble((R2));
                        double CO2 = finalR1 * 47.4;
                        output.setText(getResources().getText(R.string.EB).toString() + " " + getResources().getText(R.string.output).toString() +
                                " " + getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "BS";
                        String finalF = "Elektrik";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);


                    } else if (R0.equalsIgnoreCase("BS") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("zh")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 47.4;
                        output.setText(getResources().getText(R.string.EB).toString() + " " + getResources().getText(R.string.output).toString() +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "BS";
                        String finalF ="电动";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("SMB") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("en")) {
                        output.setText("No formulae for electric motorbike, please try other options!");
                    } else if (R0.equalsIgnoreCase("SMB") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("ms")) {
                        output.setText("Tiada formula untuk motosikal elektrik, sila cuba pilihan lain!");
                    } else if (R0.equalsIgnoreCase("MMB") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("zh")) {
                        output.setText("电动摩托车没有公式，请尝试其他选项！");
                    } else if (R0.equalsIgnoreCase("MMB") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("en")) {
                        output.setText("No formulae for electric motorbike, please try other options!");
                    } else if (R0.equalsIgnoreCase("MMB") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("ms")) {
                        output.setText("Tiada formula untuk motosikal elektrik, sila cuba pilihan lain!");
                    } else if (R0.equalsIgnoreCase("MMB") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("zh")) {
                        output.setText("电动摩托车没有公式，请尝试其他选项！");
                    } else if (R0.equalsIgnoreCase("LMB") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("en")) {
                        output.setText("No formulae for electric motorbike, please try other options!");
                    } else if (R0.equalsIgnoreCase("LMB") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("ms")) {
                        output.setText("Tiada formula untuk motosikal elektrik, sila cuba pilihan lain!");
                    } else if (R0.equalsIgnoreCase("LMB") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("zh")) {
                        output.setText("电动摩托车没有公式，请尝试其他选项！");
                    } else if (R0.equalsIgnoreCase("MC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("en")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 43.91;
                        output.setText(getResources().getText(R.string.output).toString() + " " + getResources().getText(R.string.EMC).toString() + " " +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "MC";
                        String finalF = "Electric";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("MC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("ms")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 43.91;
                        output.setText(getResources().getText(R.string.EMC).toString() + " " + getResources().getText(R.string.output).toString() +
                                " " + getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "MC";
                        String finalF = "Elektrik";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("MC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("zh")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 43.91;
                        output.setText(getResources().getText(R.string.EMC).toString() + " " + getResources().getText(R.string.output).toString() +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "MC";
                        String finalF ="电动";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);


                    } else if (R0.equalsIgnoreCase("SMC") && pno.getText().toString().trim().length() > 0
                            && distanceInput.getText().toString().trim().length() > 0 && Locale.getDefault().getLanguage().contentEquals("en")) {
                        double finalR1 = Double.parseDouble(String.valueOf(distanceInput.getText()));
                        double CO2 = finalR1 * 46.16;
                        output.setText(getResources().getText(R.string.output).toString() + " " + getResources().getText(R.string.ESMC).toString() + " " +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "SMC";
                        String finalF = "Electric";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("SMC") && pno.getText().toString().trim().length() > 0
                            && distanceInput.getText().toString().trim().length() > 0 && Locale.getDefault().getLanguage().contentEquals("ms")) {
                        double finalR1 = Double.parseDouble(String.valueOf(distanceInput.getText()));
                        double CO2 = finalR1 * 46.16;
                        output.setText(getResources().getText(R.string.ESMC).toString() + " " + getResources().getText(R.string.output).toString() +
                                " " + getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "SMC";
                        String finalF = "Elektrik";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("SMC") && pno.getText().toString().trim().length() > 0
                            && distanceInput.getText().toString().trim().length() > 0 && Locale.getDefault().getLanguage().contentEquals("zh")) {
                        double finalR1 = Double.parseDouble(String.valueOf(distanceInput.getText()));
                        double CO2 = finalR1 * 46.16;
                        output.setText(getResources().getText(R.string.ESMC).toString() + " " + getResources().getText(R.string.output).toString() + " " +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "SMC";
                        String finalF ="电动";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);


                    } else if (R0.equalsIgnoreCase("AC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("en")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 54.77;
                        output.setText(getResources().getText(R.string.output).toString() + " " + getResources().getText(R.string.EAC).toString() + " " +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "AC";
                        String finalF = "Electric";
                        dbh.InsertEmission(data,finalVC,finalF,R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("AC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("ms")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 54.77;
                        output.setText(getResources().getText(R.string.EAC).toString() + " " + getResources().getText(R.string.output).toString() +
                                " " + getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "AC";
                        String finalF = "Elektrik";
                        dbh.InsertEmission(data,finalVC,finalF,R1, R2, CO3);
                    } else if (R0.equalsIgnoreCase("AC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("zh")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 54.77;
                        output.setText(getResources().getText(R.string.EAC).toString() + " " + getResources().getText(R.string.output).toString() +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "AC";
                        String finalF ="电动";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("MPV") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("en")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 69.07;
                        output.setText(getResources().getText(R.string.output).toString() + " " + getResources().getText(R.string.EMPV).toString() + " " +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "MPV";
                        String finalF = "Electric";
                        dbh.InsertEmission(data,finalVC,finalF,R1, R2, CO3);
                    } else if (R0.equalsIgnoreCase("MPV") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("ms")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 69.07;
                        output.setText(getResources().getText(R.string.EMPV).toString() + " " + getResources().getText(R.string.output).toString() +
                                " " + getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "MPV";
                        String finalF = "Elektrik";
                        dbh.InsertEmission(data,finalVC,finalF,R1, R2, CO3);
                    } else if (R0.equalsIgnoreCase("MPV") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("zh")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 69.07;
                        output.setText(getResources().getText(R.string.EMPV).toString() + " " + getResources().getText(R.string.output).toString() +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "MPV";
                        String finalF ="电动";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("LC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("en")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 59.83;
                        output.setText(getResources().getText(R.string.output).toString() + " " + getResources().getText(R.string.ELC).toString() + " " +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "LC";
                        String finalF = "Electric";
                        dbh.InsertEmission(data,finalVC,finalF,R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("LC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("ms")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 59.83;
                        output.setText(getResources().getText(R.string.EMC).toString() + " " + getResources().getText(R.string.output).toString() +
                                " " + getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "LC";
                        String finalF = "Elektrik";
                        dbh.InsertEmission(data,finalVC,finalF,R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("LC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("zh")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 59.83;
                        output.setText(getResources().getText(R.string.ELC).toString() + " " + getResources().getText(R.string.output).toString() +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "LC";
                        String finalF ="电动";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);


                    } else if (R0.equalsIgnoreCase("SC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("en")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 74.74;
                        output.setText(getResources().getText(R.string.output).toString() + " " + getResources().getText(R.string.ESC).toString() + " " +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "SC";
                        String finalF = "Electric";
                        dbh.InsertEmission(data,finalVC,finalF,R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("SC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("ms")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 74.74;
                        output.setText(getResources().getText(R.string.ESC).toString() + " " + getResources().getText(R.string.output).toString() +
                                " " + getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "SC";
                        String finalF = "Elektrik";
                        dbh.InsertEmission(data,finalVC,finalF,R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("SC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("zh")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 74.74;
                        output.setText(getResources().getText(R.string.ESC).toString() + " " + getResources().getText(R.string.output).toString() +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "SC";
                        String finalF ="电动";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);

                    }
                } else if (petrol.isChecked()) {
                    if (R0.equalsIgnoreCase("MC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("en")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 136.1;
                        output.setText(getResources().getText(R.string.output).toString() + " " + getResources().getText(R.string.PMC).toString() + " " +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "MC";
                        String finalF = "Petrol";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);


                    } else if (R0.equalsIgnoreCase("MC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("ms")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 136.1;
                        output.setText(getResources().getText(R.string.PMC).toString() + " " + getResources().getText(R.string.output).toString() +
                                " " + getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "MC";
                        String finalF = "Petrol";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);


                    } else if (R0.equalsIgnoreCase("MC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("zh")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 136.1;
                        output.setText(getResources().getText(R.string.PMC).toString() + " " + getResources().getText(R.string.output).toString() +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "MC";
                        String finalF ="汽油";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);


                    } else if (R0.equalsIgnoreCase("SMC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("en")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 151.3;
                        output.setText(getResources().getText(R.string.output).toString() + " " + getResources().getText(R.string.PSMC).toString() + " " +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "SMC";
                        String finalF = "Petrol";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);


                    } else if (R0.equalsIgnoreCase("SMC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("ms")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 151.3;
                        output.setText(getResources().getText(R.string.PSMC).toString() + " " + getResources().getText(R.string.output).toString() +
                                " " + getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "SMC";
                        String finalF = "Petrol";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);


                    } else if (R0.equalsIgnoreCase("SMC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("zh")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 151.3;
                        output.setText(getResources().getText(R.string.PSMC).toString() + " " + getResources().getText(R.string.output).toString() +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "SMC";
                        String finalF ="汽油";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);


                    } else if (R0.equalsIgnoreCase("AC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("en")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 174.31;
                        output.setText(getResources().getText(R.string.output).toString() + " " + getResources().getText(R.string.PAC).toString() + " " +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "AC";
                        String finalF = "Petrol";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);


                    } else if (R0.equalsIgnoreCase("AC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("ms")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 174.31;
                        output.setText(getResources().getText(R.string.PAC).toString() + " " + getResources().getText(R.string.output).toString() +
                                " " + getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "AC";
                        String finalF = "Petrol";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);


                    } else if (R0.equalsIgnoreCase("AC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("zh")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 174.31;
                        output.setText(getResources().getText(R.string.PAC).toString() + " " + getResources().getText(R.string.output).toString() +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "AC";
                        String finalF ="汽油";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);


                    } else if (R0.equalsIgnoreCase("MPV") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("en")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 194.79;
                        output.setText(getResources().getText(R.string.output).toString() + " " + getResources().getText(R.string.PMPV).toString() + " " +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "MPV";
                        String finalF = "Petrol";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("MPV") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("ms")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 194.79;
                        output.setText(getResources().getText(R.string.PMPV).toString() + " " + getResources().getText(R.string.output).toString() +
                                " " + getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "MPV";
                        String finalF = "Petrol";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("MPV") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("zh")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 194.79;
                        output.setText(getResources().getText(R.string.PMPV).toString() + " " + getResources().getText(R.string.output).toString() +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "MPV";
                        String finalF ="汽油";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);


                    } else if (R0.equalsIgnoreCase("LC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("en")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 325.86;
                        output.setText(getResources().getText(R.string.output).toString() + " " + getResources().getText(R.string.PLC).toString() + " " +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "LC";
                        String finalF = "Petrol";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);
                    } else if (R0.equalsIgnoreCase("LC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("ms")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 325.86;
                        output.setText(getResources().getText(R.string.PLC).toString() + " " + getResources().getText(R.string.output).toString() +
                                " " + getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "LC";
                        String finalF = "Petrol";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("LC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("zh")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 325.86;
                        output.setText(getResources().getText(R.string.PLC).toString() + " " + getResources().getText(R.string.output).toString() +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "LC";
                        String finalF ="汽油";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);


                    } else if (R0.equalsIgnoreCase("SC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("en")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 242.66;
                        output.setText(getResources().getText(R.string.output).toString() + " " + getResources().getText(R.string.PSC).toString() + " " +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "SC";
                        String finalF = "Petrol";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("SC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("ms")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 242.66;
                        output.setText(getResources().getText(R.string.PSC).toString() + " " + getResources().getText(R.string.output).toString() +
                                " " + getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "SC";
                        String finalF = "Petrol";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("SC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("zh")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 242.66;
                        output.setText(getResources().getText(R.string.PSC).toString() + " " + getResources().getText(R.string.output).toString() +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "SC";
                        String finalF ="汽油";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);


                    } else if (R0.equalsIgnoreCase("BS") && distanceInput.getText().toString().trim().length() > 0
                            && pno.getText().toString().trim().length() > 0 && Locale.getDefault().getLanguage().contentEquals("en")) {
                        double finalR1 = Double.parseDouble(R1);
                        double finalR2 = Double.parseDouble(R2);
                        double total = finalR1 * finalR2;
                        double CO2 = total * 174.31;
                        output.setText(getResources().getText(R.string.output).toString() + " " + getResources().getText(R.string.PB).toString() + " " +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "BS";
                        String finalF = "Petrol";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("BS") && distanceInput.getText().toString().trim().length() > 0
                            && pno.getText().toString().trim().length() > 0 && Locale.getDefault().getLanguage().contentEquals("ms")) {
                        double finalR1 = Double.parseDouble(R2);
                        double finalR2 = Double.parseDouble(R1);
                        double total = finalR1 * finalR2;
                        double CO2 = total * 174.31;
                        output.setText(getResources().getText(R.string.PB).toString() + " " + getResources().getText(R.string.output).toString() +
                                " " + getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "BS";
                        String finalF = "Petrol";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("BS") && distanceInput.getText().toString().trim().length() > 0
                            && pno.getText().toString().trim().length() > 0 && Locale.getDefault().getLanguage().contentEquals("zh")) {
                        double finalR1 = Double.parseDouble(R2);
                        double finalR2 = Double.parseDouble(R1);
                        double total = finalR1 * finalR2;
                        double CO2 = total * 174.31;
                        output.setText(getResources().getText(R.string.PB).toString() + " " + getResources().getText(R.string.output).toString() +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "BS";
                        String finalF ="汽油";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);


                    } else if (R0.equalsIgnoreCase("SMB") && distanceInput.getText().toString().trim().length() > 0
                            && pno.getText().toString().trim().length() > 0 && Locale.getDefault().getLanguage().contentEquals("en")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 82.77;
                        output.setText(getResources().getText(R.string.output).toString() + " " + getResources().getText(R.string.PSMB).toString() + " " +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "SMB";
                        String finalF = "Petrol";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);


                    } else if (R0.equalsIgnoreCase("SMB") && distanceInput.getText().toString().trim().length() > 0
                            && pno.getText().toString().trim().length() > 0 && Locale.getDefault().getLanguage().contentEquals("ms")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 82.77;
                        output.setText(getResources().getText(R.string.PSMB).toString() + " " + getResources().getText(R.string.output).toString() +
                                " " + getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "SMB";
                        String finalF = "Petrol";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("SMB") && distanceInput.getText().toString().trim().length() > 0
                            && pno.getText().toString().trim().length() > 0 && Locale.getDefault().getLanguage().contentEquals("zh")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 82.77;
                        output.setText(getResources().getText(R.string.PSMB).toString() + " " + getResources().getText(R.string.output).toString() +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "SMB";
                        String finalF ="汽油";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);


                    } else if (R0.equalsIgnoreCase("MMB") && distanceInput.getText().toString().trim().length() > 0
                            && pno.getText().toString().trim().length() > 0 && Locale.getDefault().getLanguage().contentEquals("en")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 100.86;
                        output.setText(getResources().getText(R.string.output).toString() + " " + getResources().getText(R.string.PMMB).toString() + " " +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "MMB";
                        String finalF = "Petrol";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("MMB") && distanceInput.getText().toString().trim().length() > 0
                            && pno.getText().toString().trim().length() > 0 && Locale.getDefault().getLanguage().contentEquals("ms")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 100.86;
                        output.setText(getResources().getText(R.string.PMMB).toString() + " " + getResources().getText(R.string.output).toString() +
                                " " + getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "MMB";
                        String finalF = "Petrol";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("MMB") && distanceInput.getText().toString().trim().length() > 0
                            && pno.getText().toString().trim().length() > 0 && Locale.getDefault().getLanguage().contentEquals("zh")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 100.86;
                        output.setText(getResources().getText(R.string.PMMB).toString() + " " + getResources().getText(R.string.output).toString() +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "MMB";
                        String finalF ="汽油";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);


                    } else if (R0.equalsIgnoreCase("LMB") && distanceInput.getText().toString().trim().length() > 0
                            && pno.getText().toString().trim().length() > 0 && Locale.getDefault().getLanguage().contentEquals("en")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 132.37;
                        output.setText(getResources().getText(R.string.output).toString() + " " + getResources().getText(R.string.PLMB).toString() + " " +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "LMB";
                        String finalF = "Petrol";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("LMB") && distanceInput.getText().toString().trim().length() > 0
                            && pno.getText().toString().trim().length() > 0 && Locale.getDefault().getLanguage().contentEquals("ms")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 132.37;
                        output.setText(getResources().getText(R.string.PLMB).toString() + " " + getResources().getText(R.string.output).toString() +
                                " " + getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "LMB";
                        String finalF = "Petrol";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("LMB") && distanceInput.getText().toString().trim().length() > 0
                            && pno.getText().toString().trim().length() > 0 && Locale.getDefault().getLanguage().contentEquals("zh")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 132.37;
                        output.setText(getResources().getText(R.string.PLMB).toString() + " " + getResources().getText(R.string.output).toString() +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "LMB";
                        String finalF ="汽油";
                       dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);

                    }
                } else if (diesel.isChecked()) {

                    if (R0.equalsIgnoreCase("SMB") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("en")) {
                        output.setText("No formulae for diesel motorbike, please try other options!");
                    } else if (R0.equalsIgnoreCase("SMB") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("ms")) {
                        output.setText("Tiada formula untuk motosikal diesel, sila cuba pilihan lain!");
                    } else if (R0.equalsIgnoreCase("SMB") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("zh")) {
                        output.setText("没有柴油摩托车的配方，请尝试其他选项");
                    } else if (R0.equalsIgnoreCase("MMB") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("en")) {
                        output.setText("No formulae for diesel motorbike, please try other options!");
                    } else if (R0.equalsIgnoreCase("MMB") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("ms")) {
                        output.setText("Tiada formula untuk motosikal diesel, sila cuba pilihan lain!");
                    } else if (R0.equalsIgnoreCase("MMB") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("zh")) {
                        output.setText("没有柴油摩托车的配方，请尝试其他选项");
                    } else if (R0.equalsIgnoreCase("LMB") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("en")) {
                        output.setText("No formulae for diesel motorbike, please try other options!");
                    } else if (R0.equalsIgnoreCase("LMB") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("ms")) {
                        output.setText("Tiada formula untuk motosikal diesel, sila cuba pilihan lain!");
                    } else if (R0.equalsIgnoreCase("LMB") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("zh")) {
                        output.setText("没有柴油摩托车的配方，请尝试其他选项");
                    } else if (R0.equalsIgnoreCase("MC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("en")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 106.3;
                        output.setText(getResources().getText(R.string.output).toString() + " " + getResources().getText(R.string.DMC).toString() + " " +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC="MC";
                        String finalF = "Diesel";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("MC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("ms")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 106.3;
                        output.setText(getResources().getText(R.string.DMC).toString() + " " + getResources().getText(R.string.output).toString() +
                                " " + getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "MC";
                        String finalF = "Diesel";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("MC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("zh")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 106.3;
                        output.setText(getResources().getText(R.string.DMC).toString() + " " + getResources().getText(R.string.output).toString() +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "MC";
                        String finalF ="柴油";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);


                    } else if (R0.equalsIgnoreCase("SMC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("en")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 130.78;
                        output.setText(getResources().getText(R.string.output).toString() + " " + getResources().getText(R.string.DSMC).toString() + " " +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "SMC";
                        String finalF = "Diesel";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("SMC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("ms")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 130.78;
                        output.setText(getResources().getText(R.string.DSMC).toString() + " " + getResources().getText(R.string.output).toString() +
                                " " + getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "SMC";
                        String finalF = "Diesel";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("SMC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("zh")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 130.78;
                        output.setText(getResources().getText(R.string.DSMC).toString() + " " + getResources().getText(R.string.output).toString() +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "SMC";
                        String finalF ="柴油";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);


                    } else if (R0.equalsIgnoreCase("AC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("en")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 163.43;
                        output.setText(getResources().getText(R.string.output).toString() + " " + getResources().getText(R.string.DAC).toString() + " " +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "AC";
                        String finalF = "Diesel";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("AC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("ms")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 163.43;
                        output.setText(getResources().getText(R.string.DAC).toString() + " " + getResources().getText(R.string.output).toString() +
                                " " + getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "AC";
                        String finalF = "Diesel";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("AC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("zh")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 163.43;
                        output.setText(getResources().getText(R.string.DAC).toString() + " " + getResources().getText(R.string.output).toString() +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "AC";
                        String finalF ="柴油";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);


                    } else if (R0.equalsIgnoreCase("MPV") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("en")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 175.03;
                        output.setText(getResources().getText(R.string.output).toString() + " " + getResources().getText(R.string.DMPV).toString() + " " +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "MPV";
                        String finalF = "Diesel";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("MPV") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("ms")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 175.03;
                        output.setText(getResources().getText(R.string.DMPV).toString() + " " + getResources().getText(R.string.output).toString() +
                                " " + getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "MPV";
                        String finalF = "Diesel";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("MPV") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("zh")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 175.03;
                        output.setText(getResources().getText(R.string.DMPV).toString() + " " + getResources().getText(R.string.output).toString() +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "MPV";
                        String finalF ="柴油";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);


                    } else if (R0.equalsIgnoreCase("LC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("en")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 211.74;
                        output.setText(getResources().getText(R.string.output).toString() + " " + getResources().getText(R.string.DLC).toString() + " " +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "LC";
                        String finalF = "Diesel";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("LC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("ms")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 211.74;
                        output.setText(getResources().getText(R.string.DLC).toString() + " " + getResources().getText(R.string.output).toString() +
                                " " + getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "LC";
                        String finalF = "Diesel";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("LC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("zh")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 211.74;
                        output.setText(getResources().getText(R.string.DLC).toString() + " " + getResources().getText(R.string.output).toString() +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "LC";
                        String finalF ="柴油";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);


                    } else if (R0.equalsIgnoreCase("SC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("en")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 166.64;
                        output.setText(getResources().getText(R.string.output).toString() + " " + getResources().getText(R.string.DSC).toString() + " " +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "SC";
                        String finalF = "Diesel";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("SC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("ms")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 166.64;
                        output.setText(getResources().getText(R.string.DSC).toString() + " " + getResources().getText(R.string.output).toString() +
                                " " + getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "SC";
                        String finalF = "Diesel";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("SC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("zh")) {
                        double finalR1 = Double.parseDouble(R2);
                        double CO2 = finalR1 * 166.64;
                        output.setText(getResources().getText(R.string.DSC).toString() + " " + getResources().getText(R.string.output).toString() +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "SC";
                        String finalF ="柴油";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);


                    } else if (R0.equalsIgnoreCase("BS") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("en")) {
                        double finalR1 = Double.parseDouble(R2);
                        double finalR2 = Double.parseDouble(R1);
                        double total = finalR1 * finalR2;
                        double CO2 = total * 106.3;
                        output.setText(getResources().getText(R.string.output).toString() + " " + getResources().getText(R.string.DBS).toString() + " " +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "BS";
                        String finalF = "Diesel";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("BS") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("ms")) {
                        double finalR1 = Double.parseDouble(R2);
                        double finalR2 = Double.parseDouble(R1);
                        double total = finalR1 * finalR2;
                        double CO2 = total * 106.3;
                        output.setText(getResources().getText(R.string.DBS).toString() + " " + getResources().getText(R.string.output).toString() +
                                " " + getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "BS";
                        String finalF = "Diesel";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("BS") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("zh")) {
                        double finalR1 = Double.parseDouble(R2);
                        double finalR2 = Double.parseDouble(R1);
                        double total = finalR1 * finalR2;
                        double CO2 = total * 106.3;
                        output.setText(getResources().getText(R.string.DBS).toString() + " " + getResources().getText(R.string.output).toString() +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f",CO2));
                        String finalVC = "BS";
                        String finalF ="柴油";
                        dbh.InsertEmission(data,finalVC,finalF, R1, R2, CO3);

                    }


                    //If langauge is english and radio button is not checked
                } else if (Locale.getDefault().getLanguage().contentEquals("en") && !checked) {

                    // 1)OK
                    if (R0.isEmpty() && R1.isEmpty() && R2.isEmpty()) {
                        Toasty.error(MainActivity.this, "Please enter Vehicle code, Passenger no, Distance & Choose fuel type! "
                                , Toast.LENGTH_LONG, true).show();
                    }
                    // 2)OK
                    if (R0.isEmpty() && R1.isEmpty() && !R2.isEmpty()) {
                        Toasty.error(MainActivity.this, "Please enter Vehicle code, Passenger no & Choose fuel type! "
                                , Toast.LENGTH_LONG, true).show();
                    }

                    // 3)OK
                    else if (VC && R1.isEmpty() && R2.isEmpty()) {
                        Toasty.error(MainActivity.this, "Please enter Passenger no, Distance & Choose fuel type"
                                , Toast.LENGTH_LONG, true).show();
                    }
                    // 4)OK
                    else if (VC && !R1.isEmpty() && R2.isEmpty()) {
                        Toasty.error(MainActivity.this, " Please enter Distance & Choose fuel type!"
                                , Toast.LENGTH_LONG, true).show();
                    }
                    // 5)OK
                    else if (VC && !R1.isEmpty() && !R2.isEmpty()) {
                        Toasty.error(MainActivity.this, "Please Choose fuel type! !"
                                , Toast.LENGTH_LONG, true).show();
                    }
                    // 6)OK
                    else if (!VC && R1.isEmpty() && R2.isEmpty()) {
                        Toasty.error(MainActivity.this, "Please enter VALID vehicle code, passenger no, distance & Choose fuel type!"
                                , Toast.LENGTH_LONG, true).show();
                    }
                    // 7)OK
                    else if (!VC && !R1.isEmpty() && R2.isEmpty()) {
                        Toasty.error(MainActivity.this, "Please enter VALID vehicle code, Distance & Choose fuel type!"
                                , Toast.LENGTH_LONG, true).show();
                    }
                    // 8) OK
                    else if (!VC && !R1.isEmpty() && !R2.isEmpty()) {
                        Toasty.error(MainActivity.this, "Please enter VALID vehicle code & Choose fuel type!"
                                , Toast.LENGTH_LONG, true).show();
                    }
                    //If radio button is selected and langauge is english but no other inputs are entered
                    else if (Locale.getDefault().getLanguage().contentEquals("en") && checked ) {
                        Toasty.error(MainActivity.this, "Please enter all required information!"
                                , Toast.LENGTH_LONG, true).show();

                    }


                    //Error message for malay language and radio button is not selected
                } else if (Locale.getDefault().getLanguage().contentEquals("ms") && !checked) {

                    // 1)OK
                    if (R0.isEmpty() && R1.isEmpty() && R2.isEmpty()) {
                        Toasty.error(MainActivity.this, "Sila masukkan kod Kenderaan, No Penumpang, Jarak & Pilih jenis bahan api! "
                                , Toast.LENGTH_LONG, true).show();
                    }
                    // 2) OK
                    if (R0.isEmpty() && R1.isEmpty() && !R2.isEmpty()) {
                        Toasty.error(MainActivity.this, "Sila masukkan kod kenderaan, no penumpang & Pilih jenis bahan api! "
                                , Toast.LENGTH_LONG, true).show();
                    }
                    // 3)OK
                    else if (VC && R1.isEmpty() && R2.isEmpty()) {
                        Toasty.error(MainActivity.this, "Sila masukkan no Penumpang, Jarak & Pilih jenis bahan api!"
                                , Toast.LENGTH_LONG, true).show();
                    }
                    // 4)OK
                    else if (VC && !R1.isEmpty() && R2.isEmpty()) {
                        Toasty.error(MainActivity.this, " Sila masukkan Jarak & Pilih jenis bahan api!"
                                , Toast.LENGTH_LONG, true).show();
                    }
                    // 5)OK
                    else if (VC && !R1.isEmpty() && !R2.isEmpty()) {
                        Toasty.error(MainActivity.this, "Sila Pilih jenis bahan api!"
                                , Toast.LENGTH_LONG, true).show();
                    }
                    // 6)OK
                    else if (!VC && R1.isEmpty() && R2.isEmpty()) {
                        Toasty.error(MainActivity.this, "Sila masukkan kod kenderaan yang SAH, no penumpang, jarak & Pilih jenis bahan api!"
                                , Toast.LENGTH_LONG, true).show();
                    }
                    // 7)OK
                    else if (!VC && !R1.isEmpty() && R2.isEmpty()) {
                        Toasty.error(MainActivity.this, "Sila masukkan kod kenderaan yang SAH, Jarak & Pilih jenis bahan api!"
                                , Toast.LENGTH_LONG, true).show();
                    }
                    // 8) OK
                    else if (!VC && !R1.isEmpty() && !R2.isEmpty()) {
                        Toasty.error(MainActivity.this, "Sila masukkan kod kenderaan yang SAH & Pilih jenis bahan api!"
                                , Toast.LENGTH_LONG, true).show();
                    }
                    //If radio button is selected and langauge is english but no other inputs are entered
                    else if (Locale.getDefault().getLanguage().contentEquals("en") && checked ) {
                        Toasty.error(MainActivity.this, "Sila masukkan semua maklumat yang diperlukan!"
                                , Toast.LENGTH_LONG, true).show();

                    }
                    //If langauge is chinese and radio button is not checked
                } else if (Locale.getDefault().getLanguage().contentEquals("zh") && !checked) {

                    // 1)OK
                    if (R0.isEmpty() && R1.isEmpty() && R2.isEmpty()) {
                        Toasty.error(MainActivity.this, "请输入车辆代码、乘客数量、距离和选择燃料类型！ "
                                , Toast.LENGTH_LONG, true).show();
                    }
                    // 2)OK
                    else if (VC && R1.isEmpty() && R2.isEmpty()) {
                        Toasty.error(MainActivity.this, "\n" +
                                        "请输入乘客数量、距离和选择燃料类型！"
                                , Toast.LENGTH_LONG, true).show();
                    }
                    // 3)OK
                    else if (VC && !R1.isEmpty() && R2.isEmpty()) {
                        Toasty.error(MainActivity.this, " 请输入距离并选择燃料类型！"
                                , Toast.LENGTH_LONG, true).show();
                    }
                    // 4)OK
                    else if (VC && !R1.isEmpty() && !R2.isEmpty()) {
                        Toasty.error(MainActivity.this, "请选择燃料类型！"
                                , Toast.LENGTH_LONG, true).show();
                    }
                    // 5)OK
                    else if (!VC && R1.isEmpty() && R2.isEmpty()) {
                        Toasty.error(MainActivity.this,  "请输入有效的车辆代码、乘客编号、距离和选择燃料类型！"
                                , Toast.LENGTH_LONG, true).show();
                    }
                    // 6)OK
                    else if (!VC && !R1.isEmpty() && R2.isEmpty()) {
                        Toasty.error(MainActivity.this, "请输入有效的车辆代码、距离和选择燃料类型！"
                                , Toast.LENGTH_LONG, true).show();
                    }
                    //OK
                    else if (!VC && !R1.isEmpty() && !R2.isEmpty()) {
                        Toasty.error(MainActivity.this, "请输入有效的车辆代码并选择燃料类型！"
                                , Toast.LENGTH_LONG, true).show();
                    }
                    //If radio button is selected and langauge is english but no other inputs are entered
                    else if (Locale.getDefault().getLanguage().contentEquals("en") && checked ) {
                        Toasty.error(MainActivity.this, "请输入所有必填信息！"
                                , Toast.LENGTH_LONG, true).show();

                    }
                        }

                    }




        });


        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Locale.getDefault().getLanguage().contentEquals("en")) {
                    // Code for the action
                    vehicleCode.setText(null);
                    pno.setText(null);
                    distanceInput.setText(null);
                    fuel.clearCheck();
                    output.setText(null);
                    Toasty.success(MainActivity.this, "RESET successful", Toast.LENGTH_SHORT).show();
                }
                if (Locale.getDefault().getLanguage().contentEquals("ms")) {
                    // Code for the action
                    vehicleCode.setText(null);
                    pno.setText(null);
                    distanceInput.setText(null);
                    fuel.clearCheck();
                    output.setText(null);
                    Toasty.success(MainActivity.this, "TETAPAN SEMULA berjaya", Toast.LENGTH_SHORT).show();
                }
                if (Locale.getDefault().getLanguage().contentEquals("zh")) {
                    // Code for the action
                    vehicleCode.setText(null);
                    pno.setText(null);
                    distanceInput.setText(null);
                    fuel.clearCheck();
                    output.setText(null);
                    Toasty.success(MainActivity.this, "重置成功", Toast.LENGTH_SHORT).show();
                }


            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(intent);
            }
        });


        change.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                showChangeLanguageDialog();
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_more, menu);
        return true;
    }




        public boolean onOptionsItemSelected (MenuItem item){
            // Handle action bar item clicks here.
            switch (item.getItemId()) {
                case R.id.contact:

                    Intent intentCall = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:88289502"));
                    startActivity(intentCall);
                    return true;
                case R.id.website:
                    Intent intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.google.com"));
                    startActivity(intent);

                    return true;


                default:
                    return super.onOptionsItemSelected(item);


            }
        }


    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_vehicle_code, contextMenu);
        super.onCreateContextMenu(contextMenu, view, contextMenuInfo);
    }

    public void showChangeLanguageDialog() {
        final String[] listItems = {"English","Bahasa Melayu","中文(简体)"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        mBuilder.setTitle("Choose language");
        mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i ==0){
                    setLocale("en");
                    recreate();
                }
                else if (i ==1){
                    setLocale("ms");
                    recreate();
                }
                else if (i ==2){
                    setLocale("zh");
                    recreate();
                }
                //dismiss alert dialog when language selected
                dialogInterface.dismiss();

            }
        });
        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }

    public void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale= locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString ("My Lang", lang);
        editor.apply();
    }
    public void loadLocale(){
        SharedPreferences prefn =getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefn.getString("My Lang", "");
    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause(){
        super.onPause();
    }
}
















