package com.example.arsalankhan.savingdataintofirebase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String art_array[];
    Spinner spinner;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ListView listView;
    ArrayList<Artist> artistArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        art_array=getResources().getStringArray(R.array.art_array);
        spinner= (Spinner) findViewById(R.id.spinner);
        listView= (ListView) findViewById(R.id.listview);
        artistArrayList=new ArrayList<>();

        // for spinner
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,art_array);
        spinner.setAdapter(arrayAdapter);


        //for listview
        ArrayAdapter<Artist> artistArrayAdapter=new ArrayAdapter<Artist>(this,R.layout.single_row,artistArrayList);
        listView.setAdapter(artistArrayAdapter);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Artists");

    }

    @Override
    protected void onStart() {
        super.onStart();


        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                artistArrayList.clear();

                for(DataSnapshot data:dataSnapshot.getChildren()){
                    Artist artist=data.getValue(Artist.class);
                    artistArrayList.add(artist);

                }

                MyAdapter adapter=new MyAdapter(MainActivity.this,artistArrayList);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    //btn onclick

    public void AddData(View view){
        final TextView tv_name= (TextView) findViewById(R.id.editText_name);
        final String name=tv_name.getText().toString().trim();
        final String art=spinner.getSelectedItem().toString();

        if(!TextUtils.isEmpty(name)){
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
