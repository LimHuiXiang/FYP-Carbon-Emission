package sg.edu.rp.c346.id20006757.carbonemission;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import java.util.ArrayList;


public class HistoryListAdapter extends ArrayAdapter<Emission> {
    Context parent_context;
    int layout_id;
    ArrayList<Emission> alEmission;

    public HistoryListAdapter(Context context, int resource, ArrayList<Emission> objects){
        super(context, resource, objects);

        parent_context = context;layout_id = resource;
        alEmission = objects;

    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object - Get
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row - Read
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding - Find and Bind
        TextView ID = rowView.findViewById(R.id.textviewNo);
        TextView Date = rowView.findViewById(R.id.textviewDate);
        TextView Vehicle = rowView.findViewById(R.id.textViewVehicle);
        TextView Fuel = rowView.findViewById(R.id.textViewFuel);
        TextView Distance = rowView.findViewById(R.id.textviewDistance);
        TextView Emission = rowView.findViewById(R.id.textviewCO2e);


        // Obtain the Android Version information based on the position - Find
        Emission currentEmission = alEmission.get(position);




        // Set values to the TextView to display the corresponding information - Populate
        ID.setText(currentEmission.getId() +"");
        Date.setText(currentEmission.getDate());
        Vehicle.setText(currentEmission.getVehicle_code());
        Fuel.setText(currentEmission.getFuel());
        Double finaldistance =(currentEmission.getDistance());
        String finaldistance2 = String.format("%.2f", finaldistance);
        Distance.setText(finaldistance2+"km");
        Emission.setText(Double.toString(currentEmission.getCO2e()));


        return rowView;

    }
}
