package quannkph29999.fpoly.du_an_mau_quannkph29999.Model;

public class Sach {
    private int masach;
    private String tensach;
    private int giasach;
    private String tenls;
    int soluong;

    public Sach(int masach, String tensach, int giasach, String tenls) {
        this.masach = masach;
        this.tensach = tensach;
        this.giasach = giasach;
        this.tenls = tenls;
    }

    public Sach(String tensach, int giasach, String tenls, int soluong) {
        this.tensach = tensach;
        this.giasach = giasach;
        this.tenls = tenls;
        this.soluong = soluong;
    }

    public Sach(String tensach, int giasach, String tenls) {
        this.tensach = tensach;
        this.giasach = giasach;
        this.tenls = tenls;
    }

    public Sach() {
    }

    public Sach(String tensach, int soluong) {
        this.tensach = tensach;
        this.soluong = soluong;
    }

    public int getMasach() {
        return masach;
    }

    public void setMasach(int masach) {
        this.masach = masach;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public int getGiasach() {
        return giasach;
    }

    public void setGiasach(int giasach) {
        this.giasach = giasach;
    }

    public String getTenls() {
        return tenls;
    }

    public void setTenls(String tenls) {
        this.tenls = tenls;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
