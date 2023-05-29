package quannkph29999.fpoly.du_an_mau_quannkph29999.DAO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import quannkph29999.fpoly.du_an_mau_quannkph29999.DataBase.DBHelper;
import quannkph29999.fpoly.du_an_mau_quannkph29999.Model.Sach;

public class ThongKeDAO {
    private DBHelper dbHelper;
    private Context context;
    private SQLiteDatabase db;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public ThongKeDAO(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    @SuppressLint("Range")
    public List<Sach> getTop() {
        String sqlTop = "SELECT TenS,count(TenS) as soluong FROM phieumuon GROUP BY TenS ORDER BY soluong DESC LIMIT 10";
        List<Sach> list = new ArrayList<>();
        SachDAO sachDAO = new SachDAO(context);
        Cursor cursor = db.rawQuery(sqlTop, null);
        while (cursor.moveToNext()) {
           list.add(new Sach(cursor.getString(0),cursor.getInt(1)));
        }
        return list;
    }

    @SuppressLint("Range")
    public int getDoanhThu(String tuNgay, String denNgay) {
        String sqlDoanhThu = "SELECT SUM(GiathueS) as doanhThu FROM phieumuon WHERE ngaythue BETWEEN ? AND ?";
        List<Integer> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sqlDoanhThu, new String[]{tuNgay, denNgay});
        while (cursor.moveToNext()) {
            try {
                list.add(Integer.parseInt(cursor.getString(cursor.getColumnIndex("doanhThu"))));
            } catch (Exception e) {
                list.add(0);
            }

        }
        return list.get(0);
    }
}
