package quannkph29999.fpoly.du_an_mau_quannkph29999.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.cardview.widget.CardView;

import java.util.ArrayList;

import quannkph29999.fpoly.du_an_mau_quannkph29999.DataBase.DBHelper;
import quannkph29999.fpoly.du_an_mau_quannkph29999.Model.PhieuMuon;

public class PhieuMuonDAO {
    DBHelper dbHelper;

    public PhieuMuonDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    public ArrayList<PhieuMuon> GetDSPM() {
        ArrayList<PhieuMuon> listpm = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM phieumuon", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                PhieuMuon phieuMuon = new PhieuMuon();
                phieuMuon.setMapm(Integer.parseInt(cursor.getString(0)));
                phieuMuon.setNgaythue(cursor.getString(1));
                phieuMuon.setTrangthai(cursor.getString(2));
                phieuMuon.setTentv(cursor.getString(3));
                phieuMuon.setTens(cursor.getString(4));
                phieuMuon.setGiathue(Integer.parseInt(cursor.getString(5)));
                phieuMuon.setTentt(cursor.getString(cursor.getColumnIndex(COLUMN_TEN_THU_THU)));
                phieuMuon.setMas(Integer.parseInt(cursor.getString(7)));
                listpm.add(phieuMuon);
            } while (cursor.moveToNext());
        }
        return listpm;

    }

    private static final String COLUMN_NGAYTHUE = "ngaythue";
    private static final String COLUMN_TRANGTHAI = "trangthai";
    private static final String COLUMN_TEN_TV = "TenTV";
    private static final String COLUMN_TEN_SACH = "TenS";
    private static final String COLUMN_GIA_THUE_SACH = "GiathueS";
    private static final String COLUMN_TEN_THU_THU = "TenTT";
    private static final String COLUMN_MA_SACH = "MaS";


    public long Thempm(PhieuMuon phieuMuon) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NGAYTHUE, phieuMuon.getNgaythue());
        contentValues.put(COLUMN_TRANGTHAI, phieuMuon.getTrangthai());
        contentValues.put(COLUMN_TEN_TV, phieuMuon.getTentv());
        contentValues.put(COLUMN_TEN_SACH, phieuMuon.getTens());
        contentValues.put(COLUMN_GIA_THUE_SACH, phieuMuon.getGiathue());
        contentValues.put(COLUMN_TEN_THU_THU,phieuMuon.getTentt());
        contentValues.put(COLUMN_MA_SACH,phieuMuon.getMas());
        return sqLiteDatabase.insert("phieumuon", null, contentValues);

    }

    public long Suapm(PhieuMuon phieuMuon) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ngaythue", phieuMuon.getNgaythue());
        contentValues.put("trangthai", phieuMuon.getTrangthai());
        contentValues.put("TenTV", phieuMuon.getTentv());
        contentValues.put("TenS", phieuMuon.getTens());
        contentValues.put("GiathueS", phieuMuon.getGiathue());
        contentValues.put("TenTT",phieuMuon.getTentt());
        contentValues.put("MaS",phieuMuon.getMas());
        return sqLiteDatabase.update("phieumuon", contentValues, "MaPM = ?", new String[]{String.valueOf(phieuMuon.getMapm())});
    }

    public long Xoapm(int  mapm) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        return sqLiteDatabase.delete("phieumuon", "MaPM = ?", new String[]{String.valueOf(mapm)});
    }

}
