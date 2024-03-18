package main.exameninterfaces;

public class Alumno {
    private int id;
    private String nombre;
    private String apellidos;
    private Double ad;
    private Double sge;
    private Double di;
    private Double pmdm;
    private Double psp;
    private Double eie;
    private Double hlc;

    public Alumno(Integer id, String nombre, String apellidos, Double ad, Double sge, Double di, Double pmdm, Double psp, Double eie, Double hlc) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.ad = ad;
        this.sge = sge;
        this.di = di;
        this.pmdm = pmdm;
        this.psp = psp;
        this.eie = eie;
        this.hlc = hlc;
    }

    public Alumno () {
        
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public Double getAd() {
        return ad;
    }

    public Double getSge() {
        return sge;
    }

    public Double getDi() {
        return di;
    }

    public Double getPmdm() {
        return pmdm;
    }

    public Double getPsp() {
        return psp;
    }

    public Double getEie() {
        return eie;
    }

    public Double getHlc() {
        return hlc;
    }

    public Double getNotaMedia() {
        Double[] notas = {ad, sge, di, pmdm, psp, eie, hlc};
        Double suma = 0.0;
        Double numAprobados = 0.0;
        for (Double nota : notas) {
            suma += nota;
            if (nota >= 5) {
                numAprobados++;
            }
        }
        Double media = suma / notas.length;
        return numAprobados == notas.length ? media : -1;
    }

    public Double getNumModulosSuspensos() {
        Double[] notas = {ad, sge, di, pmdm, psp, eie, hlc};
        Double numSuspensos = 0.0;
        for (Double nota : notas) {
            if (nota < 5) {
                numSuspensos++;
            }
        }
        return numSuspensos;
    }

    public void setNombre (String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos (String apellidos) {
        this.apellidos = apellidos;
    }

    public void setAd (Double ad) {
        this.ad = ad;
    }

    public void setSge (Double sge) {
        this.sge = sge;
    }

    public void setDi (Double di) {
        this.di = di;
    }

    public void setPmdm (Double pmdm) {
        this.pmdm = pmdm;
    }

    public void setPsp (Double psp) {
        this.psp = psp;
    }

    public void setEie (Double eie) {
        this.eie = eie;
    }

    public void setHlc (Double hlc) {
        this.hlc = hlc;
    }

    public static boolean comprobacionNotas (Double ad, Double sge, Double di, Double pmdm, Double psp, Double eie, Double hlc) {
        Double[] notas = {ad, sge, di, pmdm, psp, eie, hlc};
        for (Double nota : notas) {
            if (nota < 0 || nota > 10) {
                return false;
            }
        }
        return true;
    }

    public void setId (int id) {
        this.id = id;
    }
}
