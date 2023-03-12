package sk.umb.example.library.borrowings.service;

public class BorrowingRequestDTO {

    private String zakaznik;

    private String kniha;

    public String getzakaznik() {return zakaznik;}

    public String getkniha() {return kniha;}

    public void setzakaznik(String meno) { this.zakaznik = meno;}

    public void setkniha (String nazov) { this.kniha = nazov;}
}
