package sg.edu.rp.c346.id20006757.carbonemission;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import es.dmoral.toasty.Toasty;

public class EditActivity extends AppCompatActivity {
    EditText vehicleCode,distanceInput,pno,ID;
    Button reset,calculation,history,back;
    TextView output;
    RadioButton diesel,petrol,electric;
    RadioGroup fuel;
    boolean checked,VC ;
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    String data = df.format(new Date());
    ArrayList<String> alID;
    ArrayList<Emission> alEmission;
    HistoryListAdapter caEmission;
    DBHelper dbh = new DBHelper(EditActivity.this);
    Emission data2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        alID = dbh.GetID();


        //initialize the variables with UI here
        vehicleCode = findViewById(R.id.vehicleIDEdit);
        distanceInput = findViewById(R.id.distanceIDEdit);
        pno = findViewById(R.id.passengerIDEdit);
        calculation = findViewById(R.id.updatetebuttonEdit);
        reset = findViewById(R.id.ButtonResetEdit);
        history = findViewById(R.id.ButtonHistoryEdit);
        back = findViewById(R.id.backbuttonEdit);
        output = findViewById(R.id.tvdisplayEdit);
        diesel = findViewById(R.id.DieselRadioButtonEdit);
        petrol = findViewById(R.id.PetrolRadioButtonEdit);
        electric = findViewById(R.id.ElectricRadioButtonEdit);
        ID = findViewById(R.id.IDEdit);
        fuel = findViewById(R.id.fuelradiogroupEdit);

        Intent i = getIntent();
        data2 = (Emission) i.getSerializableExtra("data");
        boolean checkID = findID();







        calculation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);

                // Code for the action
                String R00 = ID.getText().toString();
                String R0 = vehicleCode.getText().toString();
                String R1 = pno.getText().toString();
                String R2 = distanceInput.getText().toString();
                Intent i = new Intent(EditActivity.this, HistoryActivity.class);
                DecimalFormat df = new DecimalFormat("#.00");


                //Checks if radio button is selected
                if (fuel.getCheckedRadioButtonId() == -1) {
                    checked = false;
                } else {
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

                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 47.4;
                        output.setText(getResources().getText(R.string.output).toString() + " " + getResources().getText(R.string.EB).toString() + " " +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        //String CO3 = df.format(String.valueOf(CO2));
                        String finalVC = "BS";
                        String finalF = "Electric";
                        if (checkID == true ) {
                            AlertDialog diaBox = AskOption();
                            diaBox.show();
                        } else  {
                            Toasty.error(EditActivity.this, "ID does not exist!!", Toast.LENGTH_SHORT).show();

                        }
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("BS") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("ms")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 47.4;
                        output.setText(getResources().getText(R.string.EB).toString() + " " + getResources().getText(R.string.output).toString() +
                                " " + getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "BS";
                        String finalF = "Elektrik";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);


                    } else if (R0.equalsIgnoreCase("BS") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("zh")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 47.4;
                        output.setText(getResources().getText(R.string.EB).toString() + " " + getResources().getText(R.string.output).toString() +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "BS";
                        String finalF = "电动";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);

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
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 43.91;
                        output.setText(getResources().getText(R.string.output).toString() + " " + getResources().getText(R.string.EMC).toString() + " " +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "MC";
                        String finalF = "Electric";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("MC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("ms")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 43.91;
                        output.setText(getResources().getText(R.string.EMC).toString() + " " + getResources().getText(R.string.output).toString() +
                                " " + getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "MC";
                        String finalF = "Elektrik";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("MC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("zh")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 43.91;
                        output.setText(getResources().getText(R.string.EMC).toString() + " " + getResources().getText(R.string.output).toString() +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "MC";
                        String finalF = "电动";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);


                    } else if (R0.equalsIgnoreCase("SMC") && pno.getText().toString().trim().length() > 0
                            && distanceInput.getText().toString().trim().length() > 0 && Locale.getDefault().getLanguage().contentEquals("en")) {
                        double finalR1 = Double.parseDouble(String.valueOf(distanceInput.getText()));
                        double CO2 = finalR1 * 46.16;
                        output.setText(getResources().getText(R.string.output).toString() + " " + getResources().getText(R.string.ESMC).toString() + " " +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "SMC";
                        String finalF = "Electric";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("SMC") && pno.getText().toString().trim().length() > 0
                            && distanceInput.getText().toString().trim().length() > 0 && Locale.getDefault().getLanguage().contentEquals("ms")) {
                        double finalR1 = Double.parseDouble(String.valueOf(distanceInput.getText()));
                        double CO2 = finalR1 * 46.16;
                        output.setText(getResources().getText(R.string.ESMC).toString() + " " + getResources().getText(R.string.output).toString() +
                                " " + getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "SMC";
                        String finalF = "Elektrik";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("SMC") && pno.getText().toString().trim().length() > 0
                            && distanceInput.getText().toString().trim().length() > 0 && Locale.getDefault().getLanguage().contentEquals("zh")) {
                        double finalR1 = Double.parseDouble(String.valueOf(distanceInput.getText()));
                        double CO2 = finalR1 * 46.16;
                        output.setText(getResources().getText(R.string.ESMC).toString() + " " + getResources().getText(R.string.output).toString() + " " +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "SMC";
                        String finalF = "电动";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);


                    } else if (R0.equalsIgnoreCase("AC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("en")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 54.77;
                        output.setText(getResources().getText(R.string.output).toString() + " " + getResources().getText(R.string.EAC).toString() + " " +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        ;
                        String finalVC = "AC";
                        String finalF = "Electric";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("AC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("ms")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 54.77;
                        output.setText(getResources().getText(R.string.EAC).toString() + " " + getResources().getText(R.string.output).toString() +
                                " " + getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "AC";
                        String finalF = "Elektrik";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);
                    } else if (R0.equalsIgnoreCase("AC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("zh")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 54.77;
                        output.setText(getResources().getText(R.string.EAC).toString() + " " + getResources().getText(R.string.output).toString() +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "AC";
                        String finalF = "电动";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("MPV") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("en")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 69.07;
                        output.setText(getResources().getText(R.string.output).toString() + " " + getResources().getText(R.string.EMPV).toString() + " " +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "MPV";
                        String finalF = "Electric";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);
                    } else if (R0.equalsIgnoreCase("MPV") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("ms")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 69.07;
                        output.setText(getResources().getText(R.string.EMPV).toString() + " " + getResources().getText(R.string.output).toString() +
                                " " + getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "MPV";
                        String finalF = "Elektrik";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);
                    } else if (R0.equalsIgnoreCase("MPV") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("zh")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 69.07;
                        output.setText(getResources().getText(R.string.EMPV).toString() + " " + getResources().getText(R.string.output).toString() +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "MPV";
                        String finalF = "电动";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("LC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("en")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 59.83;
                        output.setText(getResources().getText(R.string.output).toString() + " " + getResources().getText(R.string.ELC).toString() + " " +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "LC";
                        String finalF = "Electric";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("LC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("ms")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 59.83;
                        output.setText(getResources().getText(R.string.EMC).toString() + " " + getResources().getText(R.string.output).toString() +
                                " " + getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "LC";
                        String finalF = "Elektrik";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("LC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("zh")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 59.83;
                        output.setText(getResources().getText(R.string.ELC).toString() + " " + getResources().getText(R.string.output).toString() +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "LC";
                        String finalF = "电动";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);


                    } else if (R0.equalsIgnoreCase("SC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("en")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 74.74;
                        output.setText(getResources().getText(R.string.output).toString() + " " + getResources().getText(R.string.ESC).toString() + " " +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "SC";
                        String finalF = "Electric";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("SC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("ms")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 74.74;
                        output.setText(getResources().getText(R.string.ESC).toString() + " " + getResources().getText(R.string.output).toString() +
                                " " + getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "SC";
                        String finalF = "Elektrik";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("SC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("zh")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 74.74;
                        output.setText(getResources().getText(R.string.ESC).toString() + " " + getResources().getText(R.string.output).toString() +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "SC";
                        String finalF = "电动";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);

                    }
                } else if (petrol.isChecked()) {
                    if (R0.equalsIgnoreCase("MC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("en")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 136.1;
                        output.setText(getResources().getText(R.string.output).toString() + " " + getResources().getText(R.string.PMC).toString() + " " +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "MC";
                        String finalF = "Petrol";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);


                    } else if (R0.equalsIgnoreCase("MC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("ms")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 136.1;
                        output.setText(getResources().getText(R.string.PMC).toString() + " " + getResources().getText(R.string.output).toString() +
                                " " + getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "MC";
                        String finalF = "Petrol";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);


                    } else if (R0.equalsIgnoreCase("MC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("zh")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 136.1;
                        output.setText(getResources().getText(R.string.PMC).toString() + " " + getResources().getText(R.string.output).toString() +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "MC";
                        String finalF = "汽油";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);


                    } else if (R0.equalsIgnoreCase("SMC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("en")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 151.3;
                        output.setText(getResources().getText(R.string.output).toString() + " " + getResources().getText(R.string.PSMC).toString() + " " +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "SMC";
                        String finalF = "Petrol";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);


                    } else if (R0.equalsIgnoreCase("SMC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("ms")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 151.3;
                        output.setText(getResources().getText(R.string.PSMC).toString() + " " + getResources().getText(R.string.output).toString() +
                                " " + getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "SMC";
                        String finalF = "Petrol";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);


                    } else if (R0.equalsIgnoreCase("SMC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("zh")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 151.3;
                        output.setText(getResources().getText(R.string.PSMC).toString() + " " + getResources().getText(R.string.output).toString() +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "SMC";
                        String finalF = "汽油";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);


                    } else if (R0.equalsIgnoreCase("AC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("en")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 174.31;
                        output.setText(getResources().getText(R.string.output).toString() + " " + getResources().getText(R.string.PAC).toString() + " " +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "AC";
                        String finalF = "Petrol";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);


                    } else if (R0.equalsIgnoreCase("AC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("ms")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 174.31;
                        output.setText(getResources().getText(R.string.PAC).toString() + " " + getResources().getText(R.string.output).toString() +
                                " " + getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "AC";
                        String finalF = "Petrol";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);


                    } else if (R0.equalsIgnoreCase("AC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("zh")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 174.31;
                        output.setText(getResources().getText(R.string.PAC).toString() + " " + getResources().getText(R.string.output).toString() +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "AC";
                        String finalF = "汽油";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);


                    } else if (R0.equalsIgnoreCase("MPV") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("en")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 194.79;
                        output.setText(getResources().getText(R.string.output).toString() + " " + getResources().getText(R.string.PMPV).toString() + " " +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "MPV";
                        String finalF = "Petrol";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("MPV") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("ms")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 194.79;
                        output.setText(getResources().getText(R.string.PMPV).toString() + " " + getResources().getText(R.string.output).toString() +
                                " " + getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "MPV";
                        String finalF = "Petrol";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("MPV") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("zh")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 194.79;
                        output.setText(getResources().getText(R.string.PMPV).toString() + " " + getResources().getText(R.string.output).toString() +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "MPV";
                        String finalF = "汽油";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);


                    } else if (R0.equalsIgnoreCase("LC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("en")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 325.86;
                        output.setText(getResources().getText(R.string.output).toString() + " " + getResources().getText(R.string.PLC).toString() + " " +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "LC";
                        String finalF = "Petrol";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);
                    } else if (R0.equalsIgnoreCase("LC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("ms")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 325.86;
                        output.setText(getResources().getText(R.string.PLC).toString() + " " + getResources().getText(R.string.output).toString() +
                                " " + getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "LC";
                        String finalF = "Petrol";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("LC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("zh")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 325.86;
                        output.setText(getResources().getText(R.string.PLC).toString() + " " + getResources().getText(R.string.output).toString() +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "LC";
                        String finalF = "汽油";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);


                    } else if (R0.equalsIgnoreCase("SC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("en")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 242.66;
                        output.setText(getResources().getText(R.string.output).toString() + " " + getResources().getText(R.string.PSC).toString() + " " +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "SC";
                        String finalF = "Petrol";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("SC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("ms")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 242.66;
                        output.setText(getResources().getText(R.string.PSC).toString() + " " + getResources().getText(R.string.output).toString() +
                                " " + getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "SC";
                        String finalF = "Petrol";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("SC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("zh")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 242.66;
                        output.setText(getResources().getText(R.string.PSC).toString() + " " + getResources().getText(R.string.output).toString() +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "SC";
                        String finalF = "汽油";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);


                    } else if (R0.equalsIgnoreCase("BS") && distanceInput.getText().toString().trim().length() > 0
                            && pno.getText().toString().trim().length() > 0 && Locale.getDefault().getLanguage().contentEquals("en")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double finalR2 = Double.parseDouble(String.valueOf(R1));
                        double total = finalR1 * finalR2;
                        double CO2 = total * 174.31;
                        output.setText(getResources().getText(R.string.output).toString() + " " + getResources().getText(R.string.PB).toString() + " " +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "BS";
                        String finalF = "Petrol";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("BS") && distanceInput.getText().toString().trim().length() > 0
                            && pno.getText().toString().trim().length() > 0 && Locale.getDefault().getLanguage().contentEquals("ms")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double finalR2 = Double.parseDouble(String.valueOf(R1));
                        double total = finalR1 * finalR2;
                        double CO2 = total * 174.31;
                        output.setText(getResources().getText(R.string.PB).toString() + " " + getResources().getText(R.string.output).toString() +
                                " " + getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "BS";
                        String finalF = "Petrol";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("BS") && distanceInput.getText().toString().trim().length() > 0
                            && pno.getText().toString().trim().length() > 0 && Locale.getDefault().getLanguage().contentEquals("zh")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double finalR2 = Double.parseDouble(String.valueOf(R1));
                        double total = finalR1 * finalR2;
                        double CO2 = total * 174.31;
                        output.setText(getResources().getText(R.string.PB).toString() + " " + getResources().getText(R.string.output).toString() +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "BS";
                        String finalF = "汽油";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);


                    } else if (R0.equalsIgnoreCase("SMB") && distanceInput.getText().toString().trim().length() > 0
                            && pno.getText().toString().trim().length() > 0 && Locale.getDefault().getLanguage().contentEquals("en")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 82.77;
                        output.setText(getResources().getText(R.string.output).toString() + " " + getResources().getText(R.string.PSMB).toString() + " " +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "SMB";
                        String finalF = "Petrol";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);


                    } else if (R0.equalsIgnoreCase("SMB") && distanceInput.getText().toString().trim().length() > 0
                            && pno.getText().toString().trim().length() > 0 && Locale.getDefault().getLanguage().contentEquals("ms")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 82.77;
                        output.setText(getResources().getText(R.string.PSMB).toString() + " " + getResources().getText(R.string.output).toString() +
                                " " + getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "SMB";
                        String finalF = "Petrol";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("SMB") && distanceInput.getText().toString().trim().length() > 0
                            && pno.getText().toString().trim().length() > 0 && Locale.getDefault().getLanguage().contentEquals("zh")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 82.77;
                        output.setText(getResources().getText(R.string.PSMB).toString() + " " + getResources().getText(R.string.output).toString() +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "SMB";
                        String finalF = "汽油";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);


                    } else if (R0.equalsIgnoreCase("MMB") && distanceInput.getText().toString().trim().length() > 0
                            && pno.getText().toString().trim().length() > 0 && Locale.getDefault().getLanguage().contentEquals("en")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 100.86;
                        output.setText(getResources().getText(R.string.output).toString() + " " + getResources().getText(R.string.PMMB).toString() + " " +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "MMB";
                        String finalF = "Petrol";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("MMB") && distanceInput.getText().toString().trim().length() > 0
                            && pno.getText().toString().trim().length() > 0 && Locale.getDefault().getLanguage().contentEquals("ms")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 100.86;
                        output.setText(getResources().getText(R.string.PMMB).toString() + " " + getResources().getText(R.string.output).toString() +
                                " " + getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "MMB";
                        String finalF = "Petrol";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("MMB") && distanceInput.getText().toString().trim().length() > 0
                            && pno.getText().toString().trim().length() > 0 && Locale.getDefault().getLanguage().contentEquals("zh")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 100.86;
                        output.setText(getResources().getText(R.string.PMMB).toString() + " " + getResources().getText(R.string.output).toString() +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "MMB";
                        String finalF = "汽油";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);


                    } else if (R0.equalsIgnoreCase("LMB") && distanceInput.getText().toString().trim().length() > 0
                            && pno.getText().toString().trim().length() > 0 && Locale.getDefault().getLanguage().contentEquals("en")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 132.37;
                        output.setText(getResources().getText(R.string.output).toString() + " " + getResources().getText(R.string.PLMB).toString() + " " +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "LMB";
                        String finalF = "Petrol";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("LMB") && distanceInput.getText().toString().trim().length() > 0
                            && pno.getText().toString().trim().length() > 0 && Locale.getDefault().getLanguage().contentEquals("ms")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 132.37;
                        output.setText(getResources().getText(R.string.PLMB).toString() + " " + getResources().getText(R.string.output).toString() +
                                " " + getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "LMB";
                        String finalF = "Petrol";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("LMB") && distanceInput.getText().toString().trim().length() > 0
                            && pno.getText().toString().trim().length() > 0 && Locale.getDefault().getLanguage().contentEquals("zh")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 132.37;
                        output.setText(getResources().getText(R.string.PLMB).toString() + " " + getResources().getText(R.string.output).toString() +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "LMB";
                        String finalF = "汽油";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);

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
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 106.3;
                        output.setText(getResources().getText(R.string.output).toString() + " " + getResources().getText(R.string.DMC).toString() + " " +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "MC";
                        String finalF = "Diesel";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("MC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("ms")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 106.3;
                        output.setText(getResources().getText(R.string.DMC).toString() + " " + getResources().getText(R.string.output).toString() +
                                " " + getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "MC";
                        String finalF = "Diesel";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("MC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("zh")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 106.3;
                        output.setText(getResources().getText(R.string.DMC).toString() + " " + getResources().getText(R.string.output).toString() +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "MC";
                        String finalF = "柴油";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);


                    } else if (R0.equalsIgnoreCase("SMC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("en")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 130.78;
                        output.setText(getResources().getText(R.string.output).toString() + " " + getResources().getText(R.string.DSMC).toString() + " " +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "SMC";
                        String finalF = "Diesel";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("SMC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("ms")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 130.78;
                        output.setText(getResources().getText(R.string.DSMC).toString() + " " + getResources().getText(R.string.output).toString() +
                                " " + getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "SMC";
                        String finalF = "Diesel";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("SMC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("zh")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 130.78;
                        output.setText(getResources().getText(R.string.DSMC).toString() + " " + getResources().getText(R.string.output).toString() +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "SMC";
                        String finalF = "柴油";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);


                    } else if (R0.equalsIgnoreCase("AC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("en")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 163.43;
                        output.setText(getResources().getText(R.string.output).toString() + " " + getResources().getText(R.string.DAC).toString() + " " +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "AC";
                        String finalF = "Diesel";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("AC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("ms")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 163.43;
                        output.setText(getResources().getText(R.string.DAC).toString() + " " + getResources().getText(R.string.output).toString() +
                                " " + getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "AC";
                        String finalF = "Diesel";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("AC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("zh")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 163.43;
                        output.setText(getResources().getText(R.string.DAC).toString() + " " + getResources().getText(R.string.output).toString() +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "AC";
                        String finalF = "柴油";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);


                    } else if (R0.equalsIgnoreCase("MPV") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("en")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 175.03;
                        output.setText(getResources().getText(R.string.output).toString() + " " + getResources().getText(R.string.DMPV).toString() + " " +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "MPV";
                        String finalF = "Diesel";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("MPV") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("ms")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 175.03;
                        output.setText(getResources().getText(R.string.DMPV).toString() + " " + getResources().getText(R.string.output).toString() +
                                " " + getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "MPV";
                        String finalF = "Diesel";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("MPV") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("zh")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 175.03;
                        output.setText(getResources().getText(R.string.DMPV).toString() + " " + getResources().getText(R.string.output).toString() +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "MPV";
                        String finalF = "柴油";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);


                    } else if (R0.equalsIgnoreCase("LC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("en")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 211.74;
                        output.setText(getResources().getText(R.string.output).toString() + " " + getResources().getText(R.string.DLC).toString() + " " +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "LC";
                        String finalF = "Diesel";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("LC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("ms")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 211.74;
                        output.setText(getResources().getText(R.string.DLC).toString() + " " + getResources().getText(R.string.output).toString() +
                                " " + getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "LC";
                        String finalF = "Diesel";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("LC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("zh")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 211.74;
                        output.setText(getResources().getText(R.string.DLC).toString() + " " + getResources().getText(R.string.output).toString() +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "LC";
                        String finalF = "柴油";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);


                    } else if (R0.equalsIgnoreCase("SC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("en")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 166.64;
                        output.setText(getResources().getText(R.string.output).toString() + " " + getResources().getText(R.string.DSC).toString() + " " +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "SC";
                        String finalF = "Diesel";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("SC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("ms")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 166.64;
                        output.setText(getResources().getText(R.string.DSC).toString() + " " + getResources().getText(R.string.output).toString() +
                                " " + getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "SC";
                        String finalF = "Diesel";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("SC") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("zh")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double CO2 = finalR1 * 166.64;
                        output.setText(getResources().getText(R.string.DSC).toString() + " " + getResources().getText(R.string.output).toString() +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "SC";
                        String finalF = "柴油";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);


                    } else if (R0.equalsIgnoreCase("BS") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("en")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double finalR2 = Double.parseDouble(String.valueOf(R1));
                        double total = finalR1 * finalR2;
                        double CO2 = total * 106.3;
                        output.setText(getResources().getText(R.string.output).toString() + " " + getResources().getText(R.string.DBS).toString() + " " +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "BS";
                        String finalF = "Diesel";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("BS") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("ms")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double finalR2 = Double.parseDouble(String.valueOf(R1));
                        double total = finalR1 * finalR2;
                        double CO2 = total * 106.3;
                        output.setText(getResources().getText(R.string.DBS).toString() + " " + getResources().getText(R.string.output).toString() +
                                " " + getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "BS";
                        String finalF = "Diesel";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);

                    } else if (R0.equalsIgnoreCase("BS") && distanceInput.getText().toString().trim().length() > 0
                            && Locale.getDefault().getLanguage().contentEquals("zh")) {
                        double finalR1 = Double.parseDouble(String.valueOf(R2));
                        double finalR2 = Double.parseDouble(String.valueOf(R1));
                        double total = finalR1 * finalR2;
                        double CO2 = total * 106.3;
                        output.setText(getResources().getText(R.string.DBS).toString() + " " + getResources().getText(R.string.output).toString() +
                                getResources().getText(R.string.IS).toString() + String.format("  %.2f", CO2) + "g CO2/km.");
                        String CO3 = (String.format("%.2f", CO2));
                        String finalVC = "BS";
                        String finalF = "柴油";
                        long inserted_id = dbh.InsertEmission(data, finalVC, finalF, R1, R2, CO3);

                    }
                }
                else {
                    if (Locale.getDefault().getLanguage().contentEquals("en")) {
                        Toasty.error(EditActivity.this, "Please enter all required and valid fields!  "
                                , Toast.LENGTH_LONG, true).show();
                    }
                    else if (Locale.getDefault().getLanguage().contentEquals("ms")){
                        Toasty.error(EditActivity.this, "\n" +
                                        "Sila masukkan semua medan yang diperlukan dan sah!  "
                                , Toast.LENGTH_LONG, true).show();

                    }
                    else if (Locale.getDefault().getLanguage().contentEquals("zh")){
                        Toasty.error(EditActivity.this, "\n" +
                                        "\n" +
                                        "请输入所有必填和有效的字段！  "
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
                    Toasty.success(EditActivity.this, "RESET successful", Toast.LENGTH_SHORT).show();
                }
                if (Locale.getDefault().getLanguage().contentEquals("ms")) {
                    // Code for the action
                    vehicleCode.setText(null);
                    pno.setText(null);
                    distanceInput.setText(null);
                    fuel.clearCheck();
                    output.setText(null);
                    Toasty.success(EditActivity.this, "TETAPAN SEMULA berjaya", Toast.LENGTH_SHORT).show();
                }
                if (Locale.getDefault().getLanguage().contentEquals("zh")) {
                    // Code for the action
                    vehicleCode.setText(null);
                    pno.setText(null);
                    distanceInput.setText(null);
                    fuel.clearCheck();
                    output.setText(null);
                    Toasty.success(EditActivity.this, "重置成功", Toast.LENGTH_SHORT).show();
                }


            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditActivity.this, HistoryActivity.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        }

    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_vehicle_code, contextMenu);
        super.onCreateContextMenu(contextMenu, view, contextMenuInfo);
    }

    private boolean findID() {
        boolean found = false;
        for (int i=0;i<alID.size();i++) {
            String IDCheck =ID.getText().toString();
            if (IDCheck.contains (alID.get(i))) {
                found = true;

            }

        }
        return found;
    }

    private AlertDialog AskOption()
    {
        AlertDialog myQuittingDialogBox = new AlertDialog.Builder(this)
                // set message, title, and icon
                .setTitle("Edit")
                .setMessage("Are you sure you want to edit ID "+ ID.getText().toString()+"?")
                .setIcon(R.drawable.delete)

                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        //your deleting code
                        DBHelper db = new DBHelper(EditActivity.this);
                        db.deleteEmission(Integer.parseInt(ID.getText().toString()));
                        Toasty.success(EditActivity.this, "EDIT successful!", Toast.LENGTH_SHORT).show();
                        alEmission.clear();
                        alEmission.addAll(dbh.getAllEmission());
                        caEmission.notifyDataSetChanged();
                        dialog.dismiss();

                    }

                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                })
                .create();

        return myQuittingDialogBox;
    }

    public void onCreateContextMenu2(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_vehicle_code, contextMenu);
        super.onCreateContextMenu(contextMenu, view, contextMenuInfo);
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



