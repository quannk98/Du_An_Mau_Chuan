package quannkph29999.fpoly.du_an_mau_quannkph29999.Model;

public class ThuThu {
    private String matt;
     private String tentt;
     private String matkhau;

    public ThuThu(String matt, String tentt, String matkhau) {
        this.matt = matt;
        this.tentt = tentt;
        this.matkhau = matkhau;
    }

    public ThuThu(String matt, String matkhau) {
        this.matt = matt;
        this.matkhau = matkhau;
    }

    public ThuThu() {
    }

    public String getMatt() {
        return matt;
    }

    public void setMatt(String matt) {
        this.matt = matt;
    }

    public String getTentt() {
        return tentt;
    }

    public void setTentt(String tentt) {
        this.tentt = tentt;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }
}
