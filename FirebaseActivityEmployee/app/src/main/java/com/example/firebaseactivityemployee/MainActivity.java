package com.example.firebaseactivityemployee;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
   private EditText firstName;
   private EditText lastName;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      FirebaseApp.initializeApp(this);
      setContentView(R.layout.activity_main);

      this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

      Button add = findViewById(R.id.add);
      firstName = findViewById(R.id.edit_first_name);
      lastName = findViewById(R.id.edit_last_name);
      ListView listView = findViewById(R.id.listView);

      add.setOnClickListener(new View.OnClickListener() {

         @Override
         public void onClick(View v) {

            String fName = firstName.getText().toString();
            String lName = lastName.getText().toString();

            if (fName.isEmpty() || lName.isEmpty()){
               Toast.makeText(MainActivity.this, "Enter both fields!", Toast.LENGTH_SHORT).show();
            } else {
               DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("employees");

               Employee emp = new Employee(fName, lName);
               myRef.push().setValue(emp);

               clear(firstName);
               clear(lastName);
            }
            try {
               InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
               imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            } catch (Exception e) {
               System.out.println(e.getMessage());
            }
         }
      });

      final ArrayList<String> list = new ArrayList<>();
      final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.employee, list);
      listView.setAdapter(adapter);

      DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("employees");
      reference.addValueEventListener(new ValueEventListener() {
         @Override
         public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            list.clear();
            for (DataSnapshot snapshot : dataSnapshot.getChildren()){
               Employee info = snapshot.getValue(Employee.class);
               assert info != null;
               String txt = "First Name: " + info.getFirstName() + " \nLast Name: " + info.getLastName();
               list.add(txt);
            }
            adapter.notifyDataSetChanged();
         }

         @Override
         public void onCancelled(@NonNull DatabaseError databaseError) {
         }
      });
   }
   public void clear(View v) {
      firstName.setText("");
      lastName.setText("");
   }
   @Override
   protected void onPause(){
      super.onPause();
   }

   @Override
   protected void onResume(){
      super.onResume();
   }

   @Override
   protected void onStop(){
      super.onStop();
   }
}