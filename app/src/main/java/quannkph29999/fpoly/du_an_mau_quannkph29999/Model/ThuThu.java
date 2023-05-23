package quannkph29999.fpoly.du_an_mau_quannkph29999.Model;

public class ThuThu {
    private int matt;
     private String tentt;

    public ThuThu(int matt, String tentt) {
        this.matt = matt;
        this.tentt = tentt;
    }

    public ThuThu() {
    }

    public int getMatt() {
        return matt;
    }

    public void setMatt(int matt) {
        this.matt = matt;
    }

    public String getTentt() {
        return tentt;
    }

    public void setTentt(String tentt) {
        this.tentt = tentt;
    }
}
