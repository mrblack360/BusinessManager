package com.bms.ui.purchases;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bms.DBManager;
import com.bms.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class PurchaseFragment extends Fragment {
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
        final View root = inflater.inflate (R.layout.fragment_purchases, container, false);

        display (root, dbManager);

        FloatingActionButton fab = root.findViewById (R.id.fab_purchase);

        fab.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = requireActivity().getLayoutInflater();


                final View addPurchase = inflater.inflate(R.layout.dialog_add_purchase, null);

                final EditText particular_id = addPurchase.findViewById (R.id.particular_id);
                final EditText particular_name = addPurchase.findViewById (R.id.particular_name);
                final EditText unit_price = addPurchase.findViewById (R.id.unit_price);

                builder.setTitle("Add Particular");
                builder.setView(addPurchase);
                builder.setPositiveButton ("Add Particular", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String _id = particular_id.getText ().toString ();
                        String name = particular_name.getText ().toString ();
                        int price = Integer.parseInt (unit_price.getText ().toString ());
                        Purchase particular = new Purchase (_id, name, price);
                        dbManager.addParticular (particular);
                        display (root, dbManager);
                    }
                });
                builder.setNegativeButton ("Cancel", null);
                builder.show ();
            }
        });
        return root;
    }
}