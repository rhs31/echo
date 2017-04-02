package com.stetsonhacks.echo.daos;

import android.location.Location;
import android.util.Log;

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
    private static final String TAG = "FirebaseMessageDAOImpl";
    private DatabaseReference mMessageRef;

    public FirebaseMessageDAOImpl() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mMessageRef = database.getReference();
    }

    @Override
    public void post(Message message) {
        mMessageRef.child("messages").child(String.valueOf(UUID.randomUUID())).setValue(message);
    }

    @Override
    public void getAtLocation(final Location location, final double radius, final Callback<List<Message>> callback) {
        mMessageRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Message> l = new ArrayList<>();
                for (DataSnapshot item : dataSnapshot.child("messages").getChildren()) {
                    Log.d(TAG, "new message");
                    Message m = item.getValue(Message.class);
//                    Location msgLoc = new Location("gps");
//                    msgLoc.setLongitude(m.location.longitude);
//                    msgLoc.setLatitude(m.location.latitude);
                    Log.d(TAG, "\tloc of msg : " + m.location);
                    Log.d(TAG, "loc of user : " + location);
                    float [] results = new float[5];
                    Location.distanceBetween(m.location.latitude, m.location.longitude,
                            location.getLatitude(), location.getLongitude(), results);
                    Log.d(TAG, "\tdistance : " + results[0]);
                    if (results[0] < radius)
                        l.add(m);
                }
                callback.apply(l);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
