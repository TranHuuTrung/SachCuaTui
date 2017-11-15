package com.huutrung.sachcuatui.NhatKi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 11/7/2017.\
 * dùng để khởi tạo bảng databse chứa các mẩu nhật kí
 */

public class NhatKiDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "SQLite";

    //khai bao phien ban
    private static final int DATABASE_VERSION  = 1;

    //khai bao ten co so du lieu
    private static final String DATABASE_NAME = "NhatKiManager";

    //bang NhatKi
    private static final String TABLE_NHATKI = "NhatKi_Table";

    private static final String COLUMN_NHATKI_ID = "NhatKi_Id";
    private static final String COLUMN_NHATKI_TITLE = "NhatKi_Title";
    private static final String COLUMN_NHATKI_CONTENT = "NhatKi_Content";

   //tao bang
    @Override
    public void onCreate(SQLiteDatabase db) {
          String sql = "CREATE TABLE " + TABLE_NHATKI +"(" +
                  COLUMN_NHATKI_ID + " INT PRIMARY KEY AUTOINCREMENT, "+
                  COLUMN_NHATKI_TITLE + " TEXT, "+ COLUMN_NHATKI_CONTENT + " TEXT);";
          db.execSQL(sql);//chay lenh tao bang
    }

    //thay doi cau truc bang them rang buoc du lieu ta dung onUpgrade()
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NHATKI;//xoa bang nhat ki neu ton tai
        db.execSQL(sql);
        onCreate(db); //sau khi xoa xong thi goi la ham onCreate de tao lai


    }

    public NhatKiDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


//    thêm vào Database
    public void addNhatKi(NhatKi nhatKi){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NHATKI_TITLE, nhatKi.getNhatKiTitle());
        values.put(COLUMN_NHATKI_CONTENT, nhatKi.getNhatKiContent());

        //chèn vào
        db.insert(TABLE_NHATKI, null, values);

        //dong
        db.close();
    }

    //đọc nhật kí theo id của nó
    public NhatKi getNhatKi(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NHATKI, new String[]{
                COLUMN_NHATKI_ID, COLUMN_NHATKI_TITLE, COLUMN_NHATKI_CONTENT},
                COLUMN_NHATKI_ID +"=?",new String[]{String.valueOf(id)},
                null, null, null, null);
        if(cursor !=null){
            cursor.moveToFirst();
        }
        NhatKi nhatKi = new NhatKi(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
        return nhatKi;
    }

    public List<NhatKi> getAllNhatKi(){
        List<NhatKi> nhatKiList = new ArrayList<NhatKi>();
        //lấy tất cả các trường từ bảng
        String selectQuery = "SELECT  * FROM " + TABLE_NHATKI;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Duyệt trên con trỏ, và thêm vào danh sách.
        if (cursor.moveToFirst()) {
            do {
                NhatKi nhatKi = new NhatKi();
                nhatKi.setNhatKiId(Integer.parseInt(cursor.getString(0)));
                nhatKi.setNhatKiTitle(cursor.getString(1));
                nhatKi.setNhatKiContent(cursor.getString(2));

                // Thêm vào danh sách.
                nhatKiList.add(nhatKi);
            } while (cursor.moveToNext());
        }

        // return nhatKiList list
        return nhatKiList;
    }

    public int getNhatKiCount() {

        String countQuery = "SELECT  * FROM " + TABLE_NHATKI;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }

    public int updateNhatKi(NhatKi nhatKi) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NHATKI_TITLE, nhatKi.getNhatKiTitle());
        values.put(COLUMN_NHATKI_CONTENT, nhatKi.getNhatKiContent());

        // updating row
        return db.update(TABLE_NHATKI, values, COLUMN_NHATKI_ID + " = ?",
                new String[]{String.valueOf(nhatKi.getNhatKiId())});
    }

    public void deleteNhatKi(NhatKi nhatKi) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NHATKI, COLUMN_NHATKI_ID + " = ?",
                new String[] { String.valueOf(nhatKi.getNhatKiId()) });
        db.close();
    }


    public void createDefaultNotesIfNeed() {
        int count = this.getNhatKiCount();
        if(count ==0 ) {
            NhatKi nhatki1 = new NhatKi("First your Story",
                    "You need write any one story to feeling so cool with ours app");
            this.addNhatKi(nhatki1);
        }
    }
}
