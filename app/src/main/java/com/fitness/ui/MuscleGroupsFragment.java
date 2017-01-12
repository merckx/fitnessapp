package com.fitness.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.fitness.R;
import com.fitness.model.Exercise;
import com.fitness.model.MuscleGroup;
import com.fitness.model.MuscleGroupViewHolder;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MuscleGroupsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MuscleGroupsFragment extends Fragment {

    private DatabaseReference mFirebaseDatabaseReference;
//    private FirebaseRecyclerAdapter<MuscleGroup, MuscleGroupViewHolder>
//            mFirebaseAdapter;
    public static final String MUSCLE_GROUP_CHILD = "muscle_group";
    private LinearLayoutManager mLinearLayoutManager;
    private RecyclerView mMessageRecyclerView;
    public MuscleGroupsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters
     * @return A new instance of fragment MuscleGroupsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MuscleGroupsFragment newInstance() {
        MuscleGroupsFragment fragment = new MuscleGroupsFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mLinearLayoutManager.setStackFromEnd(false);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_excercises, container, false);
        mMessageRecyclerView = (RecyclerView) view.findViewById(R.id.messageRecyclerView);
        StringBuilder buf = new StringBuilder();
        BufferedReader in = null;
        try {
            InputStream json = getActivity().getAssets().open("muscle_groups.json");
            in = new BufferedReader(new InputStreamReader(json, "UTF-8"));
            String str;

            while ((str = in.readLine()) != null) {
                buf.append(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null)
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }
        Gson gson = new GsonBuilder().create();
        Type listType = new TypeToken<ArrayList<MuscleGroup>>(){}.getType();
        List<MuscleGroup> muscleGroups = gson.fromJson(buf.toString(), listType);
        MuscleGroupAdapter adapter = new MuscleGroupAdapter(muscleGroups);
        mMessageRecyclerView.setAdapter(adapter);
        mMessageRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }
//        mFirebaseAdapter = new FirebaseRecyclerAdapter<MuscleGroup,
//                MuscleGroupViewHolder>(
//                MuscleGroup.class,
//                R.layout.item_muscle_group,
//                MuscleGroupViewHolder.class,
//                mFirebaseDatabaseReference.child(MUSCLE_GROUP_CHILD)) {
//            @Override
//            protected void populateViewHolder(MuscleGroupViewHolder viewHolder, MuscleGroup model, int position) {
//                viewHolder.nameTextView.setText(model.getName());
//                viewHolder.nameTextView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                       Query query = mFirebaseDatabaseReference.child(Exercise.MUSCLE_GROUP_CHILD).orderByKey();
//                        query.addValueEventListener(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(DataSnapshot dataSnapshot) {
//                                Exercise exercise = dataSnapshot.getValue(Exercise.class);
//                            }
//
//                            @Override
//                            public void onCancelled(DatabaseError databaseError) {
//
//                            }
//                        });
//                    }
//                });
//                viewHolder.messengerTextView.setText(friendlyMessage.getName());
//                if (friendlyMessage.getPhotoUrl() == null) {
//                    viewHolder.messengerImageView
//                            .setImageDrawable(ContextCompat
//                                    .getDrawable(MainActivity.this,
//                                            R.drawable.ic_account_circle_black_36dp));
//                } else {
//                    Glide.with(MainActivity.this)
//                            .load(friendlyMessage.getPhotoUrl())
//                            .into(viewHolder.messengerImageView);
//                }
//            }

//            @Override
//            protected void populateViewHolder(MessageViewHolder viewHolder,
//                                              FriendlyMessage friendlyMessage, int position) {
//                mProgressBar.setVisibility(ProgressBar.INVISIBLE);
//                viewHolder.messageTextView.setText(friendlyMessage.getText());
//                viewHolder.messengerTextView.setText(friendlyMessage.getName());
//                if (friendlyMessage.getPhotoUrl() == null) {
//                    viewHolder.messengerImageView
//                            .setImageDrawable(ContextCompat
//                                    .getDrawable(MainActivity.this,
//                                            R.drawable.ic_account_circle_black_36dp));
//                } else {
//                    Glide.with(MainActivity.this)
//                            .load(friendlyMessage.getPhotoUrl())
//                            .into(viewHolder.messengerImageView);
//                }
//            }
//        };

//        mFirebaseAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
//            @Override
//            public void onItemRangeInserted(int positionStart, int itemCount) {
//                super.onItemRangeInserted(positionStart, itemCount);
//                int friendlyMessageCount = mFirebaseAdapter.getItemCount();
//                int lastVisiblePosition =
//                        mLinearLayoutManager.findLastCompletelyVisibleItemPosition();
//                // If the recycler view is initially being loaded or the
//                // user is at the bottom of the list, scroll to the bottom
//                // of the list to show the newly added message.
//                if (lastVisiblePosition == -1 ||
//                        (positionStart >= (friendlyMessageCount - 1) &&
//                                lastVisiblePosition == (positionStart - 1))) {
//                    mMessageRecyclerView.scrollToPosition(positionStart);
//                }
//            }
//        });
//
//        mMessageRecyclerView.setLayoutManager(mLinearLayoutManager);
//        mMessageRecyclerView.setAdapter(mFirebaseAdapter);
//
//        return view;
//    }

}
