package com.example.notworking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class ClientDashboard extends AppCompatActivity {

    TextView editText2,money;
    Button plus,minus,order;
    int count=0;
    int rupee=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_dashboard);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Jagdamba Enterprises");

        editText2 =(TextView) findViewById(R.id.editText2);
        money =(TextView) findViewById(R.id.money);
        plus = (Button) findViewById(R.id.plus);
        minus = (Button) findViewById(R.id.minus);
        order = (Button) findViewById(R.id.checkout);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                rupee+=50;
                editText2.setText(String.valueOf(count));
                money.setText(String.valueOf(rupee));
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count--;
                rupee-=50;
                if(count<0) {
                    count=0;
                }
                if(rupee<0) {
                    rupee=0;
                }
                editText2.setText(String.valueOf(count));
                money.setText(String.valueOf(rupee));
            }
        });


        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rupee==0) {
                    Toast.makeText(ClientDashboard.this, "Order atleast 1 Jar", Toast.LENGTH_SHORT).show();
                }
                else {
                    Bundle bundle = new Bundle();
                    String quantity = editText2.getText().toString();
                    String price = money.getText().toString();
                    bundle.putString("Quantity", quantity);
                    bundle.putString("Price", price);
                    Intent intent = new Intent(ClientDashboard.this, CartActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.main_menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.logout:
//                Intent intent = new Intent(ClientDashboard.this,LogoutActivity.class);
//                startActivity(intent);
//                finish();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
}