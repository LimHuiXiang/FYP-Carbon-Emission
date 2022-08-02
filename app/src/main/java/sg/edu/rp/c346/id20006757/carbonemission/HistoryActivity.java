package sg.edu.rp.c346.id20006757.carbonemission;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import es.dmoral.toasty.Toasty;


public class HistoryActivity extends AppCompatActivity {
    ArrayList<Emission> alEmission;
    HistoryListAdapter caEmission;
    ArrayList<String>alID;
    ListView lv;
    Button delete,back;
    Emission data;
    DBHelper dbh = new DBHelper(HistoryActivity.this);
    EditText et1;
    Spinner filter;
    TextView tvFilter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        boolean found = false;

        //Getting data from DBHelper methods
        alEmission = dbh.getAllEmission();
        alID = dbh.GetID();

        //Getting the data from Emission Java class
        Intent i = getIntent();
        data = (Emission) i.getSerializableExtra("data");

        //Linking the UI variables
        lv = findViewById(R.id.ListViewHistory);
        delete = findViewById(R.id.ButtonDelete);
        back = findViewById(R.id.ButtonBack);
        et1 = findViewById(R.id.etDelete);
        filter = findViewById(R.id.spinnerFilter);
        tvFilter = findViewById(R.id.textViewFilter);

        //Loading of relevant data
        load();
        loadSpinnerData();

        //Setting the adapter in this class and linking it to the xml file and ArrayList
        caEmission = new HistoryListAdapter(this, R.layout.row, alEmission);
        if (lv == null) {

            Log.d("ERROR", "LV null");

        } else if (caEmission == null) {

            Log.d("ERROR", "CA null");

        }

        lv.setAdapter(caEmission);



            delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checkID = findID();

                    if (checkID == false && Locale.getDefault().getLanguage().contentEquals("en")) {
                        Toasty.error(HistoryActivity.this, "ID does not exist!", Toast.LENGTH_SHORT).show();

                    }
                    else if (checkID == false && Locale.getDefault().getLanguage().contentEquals("ms")) {
                        Toasty.error(HistoryActivity.this, "ID tidak wujud!", Toast.LENGTH_SHORT).show();

                    }
                    else if (checkID == false && Locale.getDefault().getLanguage().contentEquals("zh")) {
                        Toasty.error(HistoryActivity.this, "ID 不存在！", Toast.LENGTH_SHORT).show();

                    } else {
                        AlertDialog diaBox = AskOption();
                        diaBox.show();

                    }


                }

       });




        back.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(HistoryActivity.this, MainActivity.class);
            startActivity(intent);
        }
    });










        filter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String vcLabel = adapterView.getItemAtPosition(position).toString();
                alEmission.clear();
                if(!vcLabel.equals("NONE")){
                    alEmission.addAll(dbh.getAllEmissionByVehicleCode(vcLabel));

                } else {
                    alEmission.addAll(dbh.getAllEmission());
                }

                caEmission.notifyDataSetChanged();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private AlertDialog AskOption()
    {
        AlertDialog myQuittingDialogBox = new AlertDialog.Builder(this)
                // set message, title, and icon

                .setTitle("Delete")
                .setMessage("Are you sure you want to delete ID "+ et1.getText().toString()+"?")
                .setIcon(R.drawable.delete)

                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        //your deleting code
                        DBHelper db = new DBHelper(HistoryActivity.this);
                        db.deleteEmission(Integer.parseInt(et1.getText().toString()));
                            Toasty.success(HistoryActivity.this, "Delete successful!", Toast.LENGTH_SHORT).show();
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
    private void loadSpinnerData() {
        DBHelper dbh = new DBHelper(this);
        ArrayList<String> vcfilter = dbh.getVCLabels();
        ArrayAdapter<String> vcAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,vcfilter);

        // attaching data adapter to spinner
        filter.setAdapter(vcAdapter);
    }
    private boolean findID() {
        boolean found = false;
        for (int i=0;i<alID.size();i++) {
            String IDCheck =et1.getText().toString();
            int ID =Integer.parseInt(IDCheck);
            if (IDCheck.contains (alID.get(i))) {
                found = true;

            }

        }
        return found;
    }



    private void load()
    {
        alEmission = dbh.getAllEmission();
        caEmission = new HistoryListAdapter(this,
                R.layout.row, alEmission);
        lv.setAdapter(caEmission);

    }



        @Override
        protected void onResume() {
            super.onResume();
            Log.d("OnResume", "Data changed");
            DBHelper dbh = new DBHelper(this);
            alEmission.clear();
            alEmission.addAll(dbh.getAllEmission());
            caEmission.notifyDataSetChanged();

        }

    }

