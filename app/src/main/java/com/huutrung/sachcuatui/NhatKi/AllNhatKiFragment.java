package com.huutrung.sachcuatui.NhatKi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.huutrung.sachcuatui.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllNhatKiFragment extends Fragment {

    private ListView lvAllNhatKi;
    private ArrayAdapter<NhatKi> listViewAdapter;
    //    private ArrayList<String> mList;
    private final List<NhatKi> nhatKiList = new ArrayList<NhatKi>();
    private static final int MENU_ITEM_VIEW = 111;
    private static final int MENU_ITEM_EDIT = 222;
    private static final int MENU_ITEM_CREATE = 333;
    private static final int MENU_ITEM_DELETE = 444;


    private static final int MY_REQUEST_CODE = 1000;

    public AllNhatKiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_all_nhat_ki, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lvAllNhatKi = (ListView) view.findViewById(R.id.lvAllNhatKi);

        NhatKiDatabaseHelper db = new NhatKiDatabaseHelper(getContext());
        db.createDefaultNotesIfNeed();
        List<NhatKi> list = db.getAllNhatKi();
        this.nhatKiList.addAll(list);


//        mList = new ArrayList<>();
//        mAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, mList );
//        lvAllNhatKi.setAdapter(mAdapter);

        // Định nghĩa một Adapter.
        // 1 - Context
        // 2 - Layout cho các dòng.
        // 3 - ID của TextView mà dữ liệu sẽ được ghi vào
        // 4 - Danh sách dữ liệu.

        this.listViewAdapter = new ArrayAdapter<NhatKi>(getContext(),
                android.R.layout.simple_list_item_1, android.R.id.text1, this.nhatKiList);


        // Đăng ký Adapter cho ListView.
        this.lvAllNhatKi.setAdapter(this.listViewAdapter);

        // Đăng ký Context menu cho ListView.
        registerForContextMenu(this.lvAllNhatKi);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("All My Story");
        menu.add(0, MENU_ITEM_VIEW, 0, "View Note");
        menu.add(0, MENU_ITEM_CREATE, 1, "Create Note");
        menu.add(0, MENU_ITEM_EDIT, 2, "Edit Note");
        menu.add(0, MENU_ITEM_DELETE, 4, "Delete Note");
    }
//
//    @Override
//    public boolean onContextItemSelected(MenuItem item){
//        AdapterView.AdapterContextMenuInfo
//                info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
//
//        final NhatKi selectedNhatKi = (NhatKi) this.lvAllNhatKi.getItemAtPosition(info.position);
//
//        if(item.getItemId() == MENU_ITEM_VIEW){
//            Toast.makeText(getContext(),selectedNhatKi.getNhatKiContent(),Toast.LENGTH_LONG).show();
//        }
//        else if(item.getItemId() == MENU_ITEM_CREATE){
//            Intent intent = new Intent(this, AddEditNoteActivity.class);
//
//            // Start AddEditNoteActivity, có phản hồi.
//            this.startActivityForResult(intent, MY_REQUEST_CODE);
//        }
//        else if(item.getItemId() == MENU_ITEM_EDIT ){
//            Intent intent = new Intent(this, AddEditNoteActivity.class);
//            intent.putExtra("note", selectedNote);
//
//            // Start AddEditNoteActivity, có phản hồi.
//            this.startActivityForResult(intent,MY_REQUEST_CODE);
//        }
//        else if(item.getItemId() == MENU_ITEM_DELETE){
//            // Hỏi trước khi xóa.
//            new AlertDialog.Builder(this)
//                    .setMessage(selectedNote.getNoteTitle()+". Are you sure you want to delete?")
//                    .setCancelable(false)
//                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            deleteNhatKi(selectedNote);
//                        }
//                    })
//                    .setNegativeButton("No", null)
//                    .show();
//        }
//        else {
//            return false;
//        }
//        return true;
//    }

    // Người dùng đồng ý xóa một Note.
    private void deleteNhatKi(NhatKi nhatKi) {
        NhatKiDatabaseHelper db = new NhatKiDatabaseHelper(getContext());
        db.deleteNhatKi(nhatKi);
        this.nhatKiList.remove(nhatKi);
        // Refresh ListView.
        this.listViewAdapter.notifyDataSetChanged();
    }

    // Khi AddEditNoteActivity hoàn thành, nó gửi phản hồi lại.
    // (Nếu bạn đã start nó bằng cách sử dụng startActivityForResult()  )
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == MY_REQUEST_CODE) {
            boolean needRefresh = data.getBooleanExtra("needRefresh", true);
            // Refresh ListView
            if (needRefresh) {
                this.nhatKiList.clear();
                NhatKiDatabaseHelper db = new NhatKiDatabaseHelper(getContext());
                List<NhatKi> list = db.getAllNhatKi();
                this.nhatKiList.addAll(list);
                // Thông báo dữ liệu thay đổi (Để refresh ListView).
                this.listViewAdapter.notifyDataSetChanged();
            }
        }


    }
}
