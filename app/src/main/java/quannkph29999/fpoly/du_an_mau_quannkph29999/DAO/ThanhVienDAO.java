package quannkph29999.fpoly.du_an_mau_quannkph29999.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import quannkph29999.fpoly.du_an_mau_quannkph29999.DataBase.DBHelper;
import quannkph29999.fpoly.du_an_mau_quannkph29999.Model.ThanhVien;

public class ThanhVienDAO {
    DBHelper dbHelper;

    public ThanhVienDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    public ArrayList<ThanhVien> GetDSTV() {
        ArrayList<ThanhVien> listtv = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM thanhvien", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                ThanhVien thanhVien = new ThanhVien();
                thanhVien.setMatv(Integer.parseInt(cursor.getString(0)));
                thanhVien.setTentv(cursor.getString(1));
                thanhVien.setCccd(cursor.getString(2));
                listtv.add(thanhVien);

            } while (cursor.moveToNext());
        }
        return listtv;

    }

    public long ThemTV(ThanhVien thanhVien) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("TenTV", thanhVien.getTentv());
        contentValues.put("CCCD", thanhVien.getCccd());
        return sqLiteDatabase.insert("thanhvien", null, contentValues);
    }

    public long SuaTv(ThanhVien thanhVien) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("TenTV", thanhVien.getTentv());
        contentValues.put("CCCD", thanhVien.getCccd());

        return sqLiteDatabase.update("thanhvien", contentValues, "MaTV = ?", new String[]{String.valueOf(thanhVien.getMatv())});
    }

//    public long SuaCCCD(ThanhVien thanhVien) {
//        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("CCCD", thanhVien.getCccd());
//
//        return sqLiteDatabase.update("thanhvien", contentValues, "MaTV = ?", new String[]{String.valueOf(thanhVien.getMatv())});
//    }

    public long XoaTv(int matv) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        return sqLiteDatabase.delete("thanhvien", "MaTV = ?", new String[]{String.valueOf(matv)});
    }

    public boolean CheckLogintv(String tentv, String cccd) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM thanhvien WHERE TenTV = ? AND CCCD = ?",
                new String[]{tentv, cccd});
        if (cursor.getCount() != 0) {
            return true;
        } else {
            return false;
        }
    }

}
