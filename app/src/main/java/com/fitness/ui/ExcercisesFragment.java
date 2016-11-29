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
import com.fitness.model.MuscleGroup;
import com.fitness.model.MuscleGroupViewHolder;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExcercisesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExcercisesFragment extends Fragment {

    private DatabaseReference mFirebaseDatabaseReference;
    private FirebaseRecyclerAdapter<MuscleGroup, MuscleGroupViewHolder>
            mFirebaseAdapter;
    public static final String MUSCLE_GROUP_CHILD = "muscle_group";
    private LinearLayoutManager mLinearLayoutManager;
    private RecyclerView mMessageRecyclerView;
    public ExcercisesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters
     * @return A new instance of fragment ExcercisesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExcercisesFragment newInstance() {
        ExcercisesFragment fragment = new ExcercisesFragment();
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

        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mFirebaseAdapter = new FirebaseRecyclerAdapter<MuscleGroup,
                MuscleGroupViewHolder>(
                MuscleGroup.class,
                R.layout.item_muscle_group,
                MuscleGroupViewHolder.class,
                mFirebaseDatabaseReference.child(MUSCLE_GROUP_CHILD)) {
            @Override
            protected void populateViewHolder(MuscleGroupViewHolder viewHolder, MuscleGroup model, int position) {
                viewHolder.nameTextView.setText(model.getName());
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
            }

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
        };

        mFirebaseAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                int friendlyMessageCount = mFirebaseAdapter.getItemCount();
                int lastVisiblePosition =
                        mLinearLayoutManager.findLastCompletelyVisibleItemPosition();
                // If the recycler view is initially being loaded or the
                // user is at the bottom of the list, scroll to the bottom
                // of the list to show the newly added message.
                if (lastVisiblePosition == -1 ||
                        (positionStart >= (friendlyMessageCount - 1) &&
                                lastVisiblePosition == (positionStart - 1))) {
                    mMessageRecyclerView.scrollToPosition(positionStart);
                }
            }
        });

        mMessageRecyclerView.setLayoutManager(mLinearLayoutManager);
        mMessageRecyclerView.setAdapter(mFirebaseAdapter);

        return view;
    }

}
