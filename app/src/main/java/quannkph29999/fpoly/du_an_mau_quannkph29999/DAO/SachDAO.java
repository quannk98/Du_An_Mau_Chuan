package quannkph29999.fpoly.du_an_mau_quannkph29999.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import quannkph29999.fpoly.du_an_mau_quannkph29999.DataBase.DBHelper;
import quannkph29999.fpoly.du_an_mau_quannkph29999.Model.Sach;

public class SachDAO {
    DBHelper dbHelper;
    public SachDAO(Context context){
        dbHelper = new DBHelper(context);
    }

    public ArrayList<Sach> GetDSS(){
        ArrayList<Sach> listsach = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM sach",null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                Sach sach = new Sach();
                sach.setMasach(Integer.parseInt(cursor.getString(0)));
                sach.setTensach(cursor.getString(1));
                sach.setGiasach(Integer.parseInt(cursor.getString(2)));
                sach.setTenls(cursor.getString(3));
                listsach.add(sach);
            }while (cursor.moveToNext());

        }
        return  listsach;

    }
    public long ThemSach(Sach sach){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("TenS",sach.getTensach());
        contentValues.put("GiathueS",sach.getGiasach());
        contentValues.put("TenLS",sach.getTenls());

        return sqLiteDatabase.insert("sach",null,contentValues);
    }
    public long SuaSach(Sach sach){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("TenS",sach.getTensach());
        contentValues.put("GiathueS",sach.getGiasach());
        contentValues.put("TenLS",sach.getTenls());
        return  sqLiteDatabase.update("sach",contentValues,"MaS = ?",new String[]{String.valueOf(sach.getMasach())});
    }
    public long XoaSach(int masach){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        return sqLiteDatabase.delete("sach","MaS = ?",new String[]{String.valueOf(masach)});
    }
}
