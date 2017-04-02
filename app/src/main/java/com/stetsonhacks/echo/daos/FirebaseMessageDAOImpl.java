package com.stetsonhacks.echo.daos;

import android.location.Location;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.stetsonhacks.echo.models.Message;
import com.stetsonhacks.echo.utils.Callback;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FirebaseMessageDAOImpl implements MessageDAO {
    private DatabaseReference mMessageRef;

    public FirebaseMessageDAOImpl(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mMessageRef = database.getReference();
    }

    @Override
    public void post(Message message) {
        mMessageRef.child("messages").child(String.valueOf(UUID.randomUUID())).setValue(message);
    }

    @Override
    public void getAtLocation(Location location, final Callback<List<Message>> callback) {
        mMessageRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Message> l = new ArrayList<>();
                for(DataSnapshot item : dataSnapshot.child("messages").getChildren()){
                    l.add(item.getValue(Message.class));
                }
                callback.apply(l);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
