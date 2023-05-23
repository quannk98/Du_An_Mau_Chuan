package quannkph29999.fpoly.du_an_mau_quannkph29999.Model;

public class ThanhVien {
    private int matv;
    private String tentv;
    private String cccd;


    public ThanhVien(int matv, String tentv, String cccd) {
        this.matv = matv;
        this.tentv = tentv;
        this.cccd = cccd;

    }

    public ThanhVien() {
    }

    public ThanhVien(String tentv, String cccd) {
        this.tentv = tentv;
        this.cccd = cccd;
    }



    public int getMatv() {
        return matv;
    }

    public void setMatv(int matv) {
        this.matv = matv;
    }

    public String getTentv() {
        return tentv;
    }

    public void setTentv(String tentv) {
        this.tentv = tentv;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }


}
