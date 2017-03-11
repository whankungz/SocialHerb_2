package com.example.whankung.navigity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static android.view.View.GONE;

/**
 * Created by Whankung on 15/2/2560.
 */
public class Login extends AppCompatActivity{
    private Typeface font;

    EditText edtuserid, edtpass;


    TextView login, skip, head, regis;
    ProgressBar progressBar;

    ConnectionClass connectionClass;
    private boolean ifNotLogIn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

//            navigationView.getMenu().getItem(0).setChecked(false);


        connectionClass = new ConnectionClass();
        setView();
       // navigationView.getMenu().getItem(0).setChecked(false);
//        if(ifNotLogIn){
//            navigationView.getMenu().getItem(0).setVisible(false);
//        }
    }

    private void setView() {


        ImageView img = (ImageView)findViewById(R.id.action_login);
        img.setVisibility(View.VISIBLE);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //กลับไปยังหน้าเลือกเภสัช
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        });

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(GONE);
     //   skip = (TextView) findViewById(R.id.skip);
     //   head = (TextView) findViewById(R.id.textView4);
        login = (TextView) findViewById(R.id.textView);
        regis = (TextView) findViewById(R.id.textView2);
        edtuserid = (EditText) findViewById(R.id.user);
        edtpass = (EditText) findViewById(R.id.password);

//        เปลี่ยนfont
        font = Typeface.createFromAsset(getAssets(), "tmedium.ttf");
     //  skip.setTypeface(font);
      //  head.setTypeface(font);
        edtuserid.setTypeface(font);
        edtpass.setTypeface(font);
        login.setTypeface(font);
        regis.setTypeface(font);

        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);

            }
        });
        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                CheckLogin checkLogin = new CheckLogin();// this is the Asynctask, which is used to process in background to reduce load on app process
                checkLogin.execute("");






//       new MainActivity();
//                NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//                navigationView.getMenu().removeItem(0);
//               navigationView.getMenu().findItem(R.id.nav_login).setVisible(false);
//                    navigationView.getMenu().findItem(R.id.nav_logout).setVisible(true);
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(intent);

            }


        });


////หน้าแรก
//        skip.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), Menu.class);
//                startActivity(intent);
////                getActivity().finish();
//            }
//        });
    }



    public class CheckLogin extends AsyncTask<String, String, String> implements NavigationView.OnNavigationItemSelectedListener {
        String z = "";
        Boolean isSuccess = false;

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);

        }

        @Override
        protected void onPostExecute(String r) {
            progressBar.setVisibility(GONE);
            Toast.makeText(Login.this, r, Toast.LENGTH_SHORT).show();
            if (isSuccess) {

             //   Toast.makeText(Login.this, "Login Successfull", Toast.LENGTH_LONG).show();
//                    //finish();
//                }
            }
        }
        @Override
        protected String doInBackground(String... params) {
            String userid = edtuserid.getText().toString();
            String password = edtpass.getText().toString();

//            login.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//
//
////                    navigationView.getMenu().findItem(R.id.nav_login).setVisible(false);
////                    navigationView.getMenu().findItem(R.id.nav_logout).setVisible(true);
//                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                    startActivity(intent);
//
//                }
//            });


            if (userid.trim().equals("") || password.trim().equals(""))
                z = "กรอกให้ครบสิ!!!!!";
            else {
                try {
                    Connection con = connectionClass.connection();

                    if (con == null) {
                        z = "Check Your Internet Access!";

                    } else {

                        String query = "select * from Pharmacist where username= '" + userid.toString() + "' and password = '" + password.toString() + "'  ";
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(query);
                        if (rs.next()) {
                            z = "อิอิอิอิอิอิ";
                            isSuccess = true;


//
//
//                           intent.putExtra("FLAG", R.id.nav_login);
////                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//
//                            navigationView.setNavigationItemSelectedListener(this);
//                            navigationView.getMenu().findItem(R.id.nav_login).setVisible(false);


                            AppState.getSingleInstance().setLogin(true);
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
//                            finish();
//
//                            Intent intent = new Intent(Login.this, MainActivity.class);
//                            Bundle b = new Bundle();
//                            b.putInt("key", 1); //Your id
//                            intent.putExtras(b); //Put your id to your next Intent
//                            startActivity(intent);
//                            finish();

//
//                            navigationView.getMenu().findItem(R.id.nav_login).setVisible(false);
//                            navigationView.getMenu().findItem(R.id.nav_logout).setVisible(true);
//                            navigationView.getMenu().findItem(R.id.nav_login).setEnabled(false);

                         //   con.close();
                        } else {
                            z = "ผิด!";
                            isSuccess = false;

                        }
                    }
                } catch (Exception ex) {
                    isSuccess = false;
                    z = ex.getMessage();
                }
            }

            return z;

        }

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            return false;
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CONTEXT_INCLUDE_CODE) {

            if (!AppState.getSingleInstance().isLoggingOut()) {
                finish();
            } else {
                AppState.getSingleInstance().setLoggingOut(false);
                super.onActivityResult(requestCode, resultCode, data);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

    }

}

