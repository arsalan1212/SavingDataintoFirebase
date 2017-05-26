package com.example.arsalankhan.savingdataintofirebase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    String art_array[];
    Spinner spinner;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        art_array=getResources().getStringArray(R.array.art_array);
        spinner= (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,art_array);
        spinner.setAdapter(arrayAdapter);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Artists");

    }

    //btn onclick

    public void AddData(View view){
        final TextView tv_name= (TextView) findViewById(R.id.editText_name);
        final String name=tv_name.getText().toString().trim();
        final String art=spinner.getSelectedItem().toString();

        if(!TextUtils.isEmpty(name)){
//            databaseReference.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                   String id=databaseReference.push().getKey();
//                    Artist artist=new Artist(id,name,art);
//                    databaseReference.child(id).setValue(artist);
//                    tv_name.setText("");
//                    Toast.makeText(MainActivity.this, "Artists Added", Toast.LENGTH_SHORT).show();
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//
//                }
//            });
            String id=databaseReference.push().getKey();
            Artist artist=new Artist(id,name,art);
            databaseReference.child(id).setValue(artist);
            tv_name.setText("");
            Toast.makeText(MainActivity.this, "Artists Added", Toast.LENGTH_SHORT).show();


        }else{
            tv_name.setError("Please Fill the Field");
        }


    }
}
