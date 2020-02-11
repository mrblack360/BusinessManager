package com.bms.ui.purchases;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bms.DBManager;
import com.bms.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class ParticularFragment extends Fragment {
    public ListView listView;
    public void display(View root, DBManager dbManager){
        listView = root.findViewById(R.id.particularsList);
        Cursor particularCursor = dbManager.getParticularCursor();

        CursorAdapter particularsAdapter;
        particularsAdapter= new SimpleCursorAdapter (getContext(),
                R.layout.view_particulars,
                particularCursor,
                new String[]{"_id","description", "unit_price"},
                new int[]{R.id.particular_id, R.id.particular_name,
                        R.id.unit_price},
                0);
        listView.setAdapter(particularsAdapter);
    }

    public View onCreateView (@NonNull LayoutInflater inflater,
                              ViewGroup container, Bundle savedInstanceState) {
        final DBManager dbManager = new DBManager (getContext ());
        final View root = inflater.inflate (R.layout.fragment_particular, container, false);

        display (root, dbManager);

        FloatingActionButton fab = root.findViewById (R.id.fab_purchase);

        fab.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = requireActivity().getLayoutInflater();


                final View addParticular = inflater.inflate(R.layout.dialog_add_particular, null);

                final EditText particular_id = addParticular.findViewById (R.id.particular_id);
                final EditText particular_name = addParticular.findViewById (R.id.particular_name);
                final EditText unit_price = addParticular.findViewById (R.id.unit_price);

                builder.setTitle("Add Particular");
                builder.setView(addParticular);
                builder.setPositiveButton ("Add Particular", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String _id = particular_id.getText ().toString ();
                        String name = particular_name.getText ().toString ();
                        int price = Integer.parseInt (unit_price.getText ().toString ());
                        Particular particular = new Particular (_id, name, price);
                        dbManager.addParticular (particular);
                        display (root, dbManager);
                    }
                });
                builder.setNegativeButton ("Cancel", null);
                builder.show ();
            }
        });

        listView.setOnItemClickListener (new AdapterView.OnItemClickListener () {
            @Override
            public void onItemClick (AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder dialog = new AlertDialog.Builder (getContext ());
                LayoutInflater inflater = requireActivity().getLayoutInflater();
                final View editParticular = inflater.inflate(R.layout.dialog_add_particular, null);

                final EditText particular_id = editParticular.findViewById (R.id.particular_id);
                final EditText particular_name = editParticular.findViewById (R.id.particular_name);
                final EditText unit_price = editParticular.findViewById (R.id.unit_price);
                dialog.setView (editParticular);
                Cursor particularDetails = dbManager.readParticularDetails (position);
                particularDetails.moveToFirst ();
                particular_id.setText (particularDetails.getString (0));
                particular_name.setText (particularDetails.getString (1));
                unit_price.setText (particularDetails.getString (2));

                dialog.setTitle ("View Particular");
                dialog.setPositiveButton ("Update",
                        new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String _id = particular_id.getText ().toString ();
                        String name = particular_name.getText ().toString ();
                        int price = Integer.parseInt (unit_price.getText ().toString ());
                        Particular particular = new Particular (_id, name, price);
                        dbManager.updateParticular (particular);
                        display (root, dbManager);
                    }
                });
                dialog.setNegativeButton ("Delete",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String _id = particular_id.getText ().toString ();
                                String name = particular_name.getText ().toString ();
                                int price = Integer.parseInt (unit_price.getText ().toString ());
                                Particular particular = new Particular (_id, name, price);
                                dbManager.deleteParticular (particular);
                                display (root, dbManager);
                            }
                        });
                dialog.show ();
            }
        });
        return root;
    }
}