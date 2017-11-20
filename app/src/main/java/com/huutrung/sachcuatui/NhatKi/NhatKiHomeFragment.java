package com.huutrung.sachcuatui.NhatKi;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.huutrung.sachcuatui.MainActivity;
import com.huutrung.sachcuatui.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NhatKiHomeFragment extends Fragment {
    NhatKi nhatKi;
    private Button btn_saveNhatki, btn_cancelNhatKi;
    private EditText edtTitle, edtContent;
    private static final int MODE_CREATE = 1;
    private static final int MODE_EDIT = 2;
    private int mode;

    public NhatKiHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nhat_ki_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        btn_saveNhatki = (Button) view.findViewById(R.id.btn_saveNhatKi);
        btn_cancelNhatKi = (Button) view.findViewById(R.id.btn_cancelNhatKi);

        edtTitle = (EditText) view.findViewById(R.id.edtTitle);
        edtContent = (EditText) view.findViewById(R.id.edtContent);

//        Intent intent = getContext().getIntent();
//        nhatKi = (NhatKi) intent.getSerializableExtra("nhatKi");
//        if(nhatKi== null)  {
//            this.mode = MODE_CREATE;
//        } else  {
//            this.mode = MODE_EDIT;
//            this.edtTitle.setText(nhatKi.getNhatKiTitle());
//            this.edtContent.setText(nhatKi.getNhatKiContent());
//        }
//
    }

    // Người dùng Click vào nút Save.
    public void buttonSaveClicked(View view)  {
        NhatKiDatabaseHelper db = new NhatKiDatabaseHelper(getContext());

        String title = this.edtTitle.getText().toString();
        String content = this.edtContent.getText().toString();

        if(title.equals("") || content.equals("")) {
            Toast.makeText(getContext(),
                    "Please enter title & content", Toast.LENGTH_LONG).show();
            return;
        }

        if(mode==MODE_CREATE ) {
            this.nhatKi= new NhatKi(title,content);
            db.addNhatKi(nhatKi);
        } else  {
            this.nhatKi.setNhatKiTitle(title);
            this.nhatKi.setNhatKiContent(content);
            db.updateNhatKi(nhatKi);
        }

    }
    public void cancleWrite(View view){
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
//        Toast.makeText(getContext(), "Cancle Write", Toast.LENGTH_SHORT).show();
    }

}
