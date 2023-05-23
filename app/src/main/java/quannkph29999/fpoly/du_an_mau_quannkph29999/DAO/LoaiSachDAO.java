package quannkph29999.fpoly.du_an_mau_quannkph29999.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import quannkph29999.fpoly.du_an_mau_quannkph29999.DataBase.DBHelper;
import quannkph29999.fpoly.du_an_mau_quannkph29999.Model.LoaiSach;

public class LoaiSachDAO {
    DBHelper dbHelper;
    public LoaiSachDAO(Context context){
        dbHelper = new DBHelper(context);
    }
    public ArrayList<LoaiSach> GetDSLS(){
        ArrayList<LoaiSach> listLS = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase =dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM loaisach",null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                LoaiSach loaiSach = new LoaiSach();
                loaiSach.setMals(Integer.parseInt(cursor.getString(0)));
                loaiSach.setTenls(cursor.getString(1));
                listLS.add(loaiSach);
            }while (cursor.moveToNext());
        }
        return listLS;
    }
    public long ThemLS(LoaiSach loaiSach){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("TenLS",loaiSach.getTenls());
        return sqLiteDatabase.insert("loaisach",null,contentValues);
    }
    public long SuaLS(LoaiSach loaiSach){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("TenLS",loaiSach.getTenls());

        return  sqLiteDatabase.update("loaisach",contentValues,"MaLS = ?",new String[]{String.valueOf(loaiSach.getMals())});
    }
    public long XoaLS(int MaLS){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        return  sqLiteDatabase.delete("loaisach","MaLS = ?",new String[]{String.valueOf(MaLS)});
    }
}
