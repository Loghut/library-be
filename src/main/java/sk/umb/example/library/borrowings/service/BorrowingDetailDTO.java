package sk.umb.example.library.borrowings.service;

public class BorrowingDetailDTO {

    private Long id;

    private String zakaznik;

    private String kniha;

    public Long getId() {return id;}

    public void setId(Long index) {this.id = index;}

    public String getzakaznik() {return zakaznik;}

    public String getkniha() {return kniha;}

    public void setzakaznik(String zakaznik) {this.zakaznik = zakaznik;}

    public void setkniha(String kniha) {this.kniha = kniha;}
}
