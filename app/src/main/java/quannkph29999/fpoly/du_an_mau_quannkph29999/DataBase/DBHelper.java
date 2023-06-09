package quannkph29999.fpoly.du_an_mau_quannkph29999.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context) {
        super(context, "PHUONGNAMLIBRARY", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
           String bangthanhvien = "CREATE TABLE thanhvien(MaTV integer primary key autoincrement ,TenTV text ,CCCD integer UNIQUE )";
         db.execSQL(bangthanhvien);
           String bangloaisach = "CREATE TABLE loaisach(MaLS integer primary key autoincrement,TenLS text UNIQUE )";
         db.execSQL(bangloaisach);
           String bangsach = "CREATE TABLE sach(MaS integer primary key autoincrement,TenS text  ,GiathueS integer ," +
                 "TenLS text  REFERENCES loaisach(TenLS))";
         db.execSQL(bangsach);
           String bangphieumuon = "CREATE TABLE phieumuon(MaPM integer primary key autoincrement,ngaythue text ,trangthai text ," +
                   "TenTV text  REFERENCES thanhvien(TenTV)," +
                 "TenS text  REFERENCES sach(TenS)," +
                 "GiathueS integer  REFERENCES sach(GiathueS),TenTT text REFERENCES thuthu(TenTT),MaS integer REFERENCES sach(MaS) )";
         db.execSQL(bangphieumuon);
           String bangthuthu = "CREATE TABLE thuthu(MaTT text primary key ,TenTT text ,matkhau text )";
         db.execSQL(bangthuthu);
         String them = "INSERT INTO thuthu VALUES('admin','Ngoc Trinh','1234')";
         db.execSQL(them);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String deletethanhvien = "DROP TABLE IF EXISTS thanhvien";
        db.execSQL(deletethanhvien);
        String deleteloaisach = "DROP TABLE IF EXISTS loaisach";
        db.execSQL(deleteloaisach);
        String deletesach = "DROP TABLE IF EXISTS sach";
        db.execSQL(deletesach);
        String deletephieumuon = "DROP TABLE IF EXISTS phieumuon";
        db.execSQL(deletephieumuon);
        String deletethuthu = "DROP TABLE IF EXISTS thuthu";
        db.execSQL(deletethuthu);
        onCreate(db);
    }
}
