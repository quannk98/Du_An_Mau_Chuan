package quannkph29999.fpoly.du_an_mau_quannkph29999.Model;

public class PhieuMuon {
    private int mapm;
    private String ngaythue;
    private String trangthai;
    private String tentv;
    private String tens;
    private int giathue;
    private String tentt;
    private int mas;


    public PhieuMuon(int mapm, String ngaythue, String trangthai, String tentv, String tens, int giathue, String tentt) {
        this.mapm = mapm;
        this.ngaythue = ngaythue;
        this.trangthai = trangthai;
        this.tentv = tentv;
        this.tens = tens;
        this.giathue = giathue;
        this.tentt = tentt;
    }

    public PhieuMuon(String ngaythue, String trangthai, String tentv, String tens, int giathue, String tentt) {
        this.ngaythue = ngaythue;
        this.trangthai = trangthai;
        this.tentv = tentv;
        this.tens = tens;
        this.giathue = giathue;
        this.tentt = tentt;
    }

    public PhieuMuon(int mapm, String ngaythue, String trangthai, String tentv, String tens, int giathue) {
        this.mapm = mapm;
        this.ngaythue = ngaythue;
        this.trangthai = trangthai;
        this.tentv = tentv;
        this.tens = tens;
        this.giathue = giathue;
    }

    public PhieuMuon(String ngaythue, String trangthai, String tentv, String tens, int giathue, String tentt, int mas) {
        this.ngaythue = ngaythue;
        this.trangthai = trangthai;
        this.tentv = tentv;
        this.tens = tens;
        this.giathue = giathue;
        this.tentt = tentt;
        this.mas = mas;
    }

    public PhieuMuon() {
    }

    public PhieuMuon(String ngaythue, String trangthai, String tentv, String tens, int giathue) {
        this.ngaythue = ngaythue;
        this.trangthai = trangthai;
        this.tentv = tentv;
        this.tens = tens;
        this.giathue = giathue;
    }

    public PhieuMuon(int mapm, String ngaythue, String trangthai, String tentv, String tens) {
        this.mapm = mapm;
        this.ngaythue = ngaythue;
        this.trangthai = trangthai;
        this.tentv = tentv;
        this.tens = tens;
    }

    public int getMapm() {
        return mapm;
    }

    public void setMapm(int mapm) {
        this.mapm = mapm;
    }

    public String getNgaythue() {
        return ngaythue;
    }

    public void setNgaythue(String ngaythue) {
        this.ngaythue = ngaythue;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public String getTentv() {
        return tentv;
    }

    public void setTentv(String tentv) {
        this.tentv = tentv;
    }

    public String getTens() {
        return tens;
    }

    public void setTens(String tens) {
        this.tens = tens;
    }


    public int getGiathue() {
        return giathue;
    }

    public void setGiathue(int giathue) {
        this.giathue = giathue;
    }

    public String getTentt() {
        return tentt;
    }

    public void setTentt(String tentt) {
        this.tentt = tentt;
    }

    public int getMas() {
        return mas;
    }

    public void setMas(int mas) {
        this.mas = mas;
    }
}
