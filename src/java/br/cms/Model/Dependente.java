package br.cms.Model;

import java.util.Date;

public class Dependente {
    private long idDependente;
    private String nomeDependente;
    private Date nascimentoDependente;
    private String sexoDependente;
    private Parentesco parentescoDependente;
    private String nacionalidadeDependente;
    private String naturalidadeDependente;
    private String estadoCivilDependente;
    private String statusDependente;
    private Socio socioDependente;
  
    public long getIdDependente() {
        return idDependente;
    }

    public void setIdDependente(long idDependente) {
        this.idDependente = idDependente;
    }

    public String getNomeDependente() {
        return nomeDependente;
    }

    public void setNomeDependente(String nomeDependente) {
        this.nomeDependente = nomeDependente;
    }

    public Date getNascimentoDependente() {
        return nascimentoDependente;
    }

    public void setNascimentoDependente(Date nascimentoDependente) {
        this.nascimentoDependente = nascimentoDependente;
    }

    public String getSexoDependente() {
        return sexoDependente;
    }

    public void setSexoDependente(String sexoDependente) {
        this.sexoDependente = sexoDependente;
    }
    
    public Parentesco getParentescoDependente() {
        return parentescoDependente;
    }

    public void setParentescoDependente(Parentesco parentescoDependente) {
        this.parentescoDependente = parentescoDependente;
    }
    
    
    public String getNacionalidadeDependente() {
        return nacionalidadeDependente;
    }

    public void setNacionalidadeDependente(String nacionalidadeDependente) {
        this.nacionalidadeDependente = nacionalidadeDependente;
    }

    public String getNaturalidadeDependente() {
        return naturalidadeDependente;
    }

    public void setNaturalidadeDependente(String naturalidadeDependente) {
        this.naturalidadeDependente = naturalidadeDependente;
    }

    public String getEstadoCivilDependente() {
        return estadoCivilDependente;
    }

    public void setEstadoCivilDependente(String estadoCivilDependente) {
        this.estadoCivilDependente = estadoCivilDependente;
    }

    public String getStatusDependente() {
        return statusDependente;
    }

    public void setStatusDependente(String statusDependente) {
        this.statusDependente = statusDependente;
    }

    public Socio getSocioDependente() {
        return socioDependente;
    }

    public void setSocioDependente(Socio socioDependente) {
        this.socioDependente = socioDependente;
    }
}
