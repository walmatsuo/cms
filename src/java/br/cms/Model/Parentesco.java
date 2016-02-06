package br.cms.Model;

public class Parentesco {
    private int idParentesco;
    private int grauParentesco;
    private String nomeParentesco;
    private String statusParentesco;

    public int getIdParentesco() {
        return idParentesco;
    }

    public void setIdParentesco(int idParentesco) {
        this.idParentesco = idParentesco;
    }

    public int getGrauParentesco() {
        return grauParentesco;
    }

    public void setGrauParentesco(int grauParentesco) {
        this.grauParentesco = grauParentesco;
    }

    public String getNomeParentesco() {
        return nomeParentesco;
    }

    public void setNomeParentesco(String nomeParentesco) {
        this.nomeParentesco = nomeParentesco;
    }

    public String getStatusParentesco() {
        return statusParentesco;
    }

    public void setStatusParentesco(String statusParentesco) {
        this.statusParentesco = statusParentesco;
    }
}
