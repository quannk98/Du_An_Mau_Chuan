package quannkph29999.fpoly.du_an_mau_quannkph29999.Model;

public class TopBook {
    private String tensach;
    private int soluong;

    public TopBook(String tensach, int soluong) {
        this.tensach = tensach;
        this.soluong = soluong;
    }

    public TopBook() {
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
