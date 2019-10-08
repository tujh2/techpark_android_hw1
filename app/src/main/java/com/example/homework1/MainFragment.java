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
    private static final String STATE_SIZE = "maxSize";
    private static final int DEFAULT_SIZE = 100;
    private MyDataAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null) {
            mAdapter = new MyDataAdapter(savedInstanceState.getInt(STATE_SIZE));
        }
        else
            mAdapter = new MyDataAdapter(DEFAULT_SIZE);

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.list);
        int columns = getResources().getBoolean(R.bool.is_horizontal) ?
                4 : 3;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), columns));
        recyclerView.setAdapter(mAdapter);
        Button addButton = view.findViewById(R.id.add_butt);
        addButton.setOnClickListener(v -> mAdapter.addElement());
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull  Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_SIZE, mAdapter.getItemCount());
    }


    class MyDataAdapter extends RecyclerView.Adapter<MyViewHolder> {

        private List<Integer> mData;

        MyDataAdapter(int n) {
            mData = new ArrayList<>();
            for (int i = 1; i <= n; i++)
                mData.add(i);
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
            if(position%2 == 0)
                color = Color.BLUE;
            else
                color = Color.RED;
            holder.textView.setTextColor(color);
            //Log.d("BindHolder", "pos =  " + position);
        }

        @Override
        public int getItemCount() {
            return mData.size();
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
                    transaction.remove(top);
                    transaction.add(R.id.secFrag, new SecondFragment(pos));
                }
                transaction.addToBackStack(null);
                transaction.commit();
            });
        }
    }
}
