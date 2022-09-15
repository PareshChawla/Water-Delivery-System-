package com.example.notworking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CartActivity extends AppCompatActivity {

    RadioButton r1,r2,r3;
    Button order;
    ProgressBar progressBar;
    TextView textView,textView2,txtFlat,txtName;
    private ProgressDialog working_dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        showData();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Jagdamba Enterprises");

        r1 = (RadioButton) findViewById(R.id.r1);
        r2 = (RadioButton) findViewById(R.id.r2);
        r3 = (RadioButton) findViewById(R.id.r3);
        order=(Button) findViewById(R.id.order);
        textView=findViewById(R.id.textView);
        textView2=findViewById(R.id.textView2);
        txtFlat=findViewById(R.id.txtFlat);
        txtName=findViewById(R.id.txtName);

        showWorkingDialog();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String quantity = bundle.getString("Quantity");
        String price = bundle.getString("Price");
        textView.setText(quantity);
        textView2.setText(price+" Rupees");



        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (r1.isChecked() || r2.isChecked()) {
                    Toast.makeText(CartActivity.this, "Order Placed", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CartActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
                else
                    Toast.makeText(CartActivity.this, "Go to Payment app and fill info", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void showWorkingDialog() {
        working_dialog = ProgressDialog.show(this, "","Working please wait...", true);
    }

    private void removeWorkingDialog() {
        if (working_dialog != null) {
            working_dialog.dismiss();
            working_dialog = null;
        }
    }

    private void showData() {
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference("User")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        ValueEventListener postListener = new ValueEventListener() {

            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                txtName.setText(user.getUserName());
                txtFlat.setText(user.getFlatNo());
                removeWorkingDialog();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(CartActivity.this, "Couldn't get data.", Toast.LENGTH_SHORT).show();
            }
        };
        mDatabase.addValueEventListener(postListener);
    }
}
