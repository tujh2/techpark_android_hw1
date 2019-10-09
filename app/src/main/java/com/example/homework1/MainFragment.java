package com.example.homework1;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainFragment extends Fragment {
    private static final String STATE_ARRAY = "ARRAY";
    private MyDataAdapter mAdapter;

    static MainFragment newInstance(int defSize) {

        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        args.putInt(STATE_ARRAY, defSize);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mAdapter == null)
            mAdapter = new MyDataAdapter();
        Bundle args = getArguments();
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.list);
        int columns = getResources().getBoolean(R.bool.is_horizontal) ?
                4 : 3;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), columns));
        recyclerView.setAdapter(mAdapter);
        Button addButton = view.findViewById(R.id.add_butt);
        addButton.setOnClickListener(v -> mAdapter.addElement());
        if(savedInstanceState != null)
            mAdapter.setData(savedInstanceState.getInt(STATE_ARRAY));
        else if(args != null)
            mAdapter.setData(args.getInt(STATE_ARRAY));
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

   public void onSaveInstanceState(@NonNull  Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_ARRAY, mAdapter.getItemCount());
   }

    class MyDataAdapter extends RecyclerView.Adapter<MyViewHolder> {

        private final List<Integer> mData;

        MyDataAdapter() {
            mData = new ArrayList<>();
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            int data = mData.get(position), color;
            holder.textView.setText(String.valueOf(data));
            color = (position%2 == 0) ?
                    Color.BLUE : Color.RED;
            holder.textView.setTextColor(color);
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        void setData(int size) {
            for(int i = getItemCount() + 1; i <= size; i++)
                mData.add(i);
            notifyDataSetChanged();
        }
        void addElement() {
            int pos = getItemCount() + 1;
            mData.add(pos);
            notifyDataSetChanged();
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.number);
            textView.setOnClickListener( v -> {
                int pos = getAdapterPosition() + 1;
                FragmentTransaction transaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                Fragment top = getActivity().getSupportFragmentManager().findFragmentById(R.id.mainFrag);
                if(top != null) {
                    transaction.hide(top);
                    transaction.add(R.id.secFrag, SecondFragment.newInstance(pos, textView.getCurrentTextColor()));
                }
                transaction.addToBackStack(null);
                transaction.commit();
            });
        }
    }
}
