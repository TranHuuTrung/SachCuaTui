package com.huutrung.sachcuatui.Book;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huutrung.sachcuatui.R;

import java.util.ArrayList;



public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<Book> arrayList;
    BookAdapter bookAdapter;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);

        //tuy chinh recycler view
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        //tao du lieu mang arrayList
        arrayList = new ArrayList<Book>();
        arrayList.add(new Book(R.drawable.icon_book, "Đắc Nhân Tâm", 5));
        arrayList.add(new Book(R.drawable.icon_book, "Đủ Xa Sẽ Đủ Lạ Sẽ Quên", 4));
        arrayList.add(new Book(R.drawable.icon_book, "Nhà Giả Kim", 4));
        arrayList.add(new Book(R.drawable.icon_book, "Tuổi Trẻ Đáng Giá Bao Nhiêu", 5));
        //tao adapter
        bookAdapter = new BookAdapter(arrayList, getActivity());
        recyclerView.setAdapter(bookAdapter);
        return  view;
    }

}