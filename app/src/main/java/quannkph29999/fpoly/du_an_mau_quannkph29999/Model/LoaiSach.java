package quannkph29999.fpoly.du_an_mau_quannkph29999.Model;

public class LoaiSach {
    private int mals;
    private String tenls;

    public LoaiSach(int mals, String tenls) {
        this.mals = mals;
        this.tenls = tenls;
    }

    public LoaiSach(String tenls) {
        this.tenls = tenls;
    }

    public LoaiSach() {
    }

    public int getMals() {
        return mals;
    }

    public void setMals(int mals) {
        this.mals = mals;
    }

    public String getTenls() {
        return tenls;
    }

    public void setTenls(String tenls) {
        this.tenls = tenls;
    }
}
