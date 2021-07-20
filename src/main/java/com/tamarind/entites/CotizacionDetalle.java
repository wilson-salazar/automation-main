/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tamarind.entites;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SALAZAR
 */
@Entity
@Table(name = "cotizacion_detalle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CotizacionDetalle.findAll", query = "SELECT c FROM CotizacionDetalle c"),
    @NamedQuery(name = "CotizacionDetalle.findById", query = "SELECT c FROM CotizacionDetalle c WHERE c.id = :id"),
    @NamedQuery(name = "CotizacionDetalle.findByTarifa", query = "SELECT c FROM CotizacionDetalle c WHERE c.tarifa = :tarifa"),
    @NamedQuery(name = "CotizacionDetalle.findByFechaEfectiva", query = "SELECT c FROM CotizacionDetalle c WHERE c.fechaEfectiva = :fechaEfectiva"),
    @NamedQuery(name = "CotizacionDetalle.findByEstado", query = "SELECT c FROM CotizacionDetalle c WHERE c.estado = :estado")})
public class CotizacionDetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "tarifa")
    private BigDecimal tarifa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_efectiva")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEfectiva;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "estado")
    private String estado;
    @JoinColumn(name = "cotizacion", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cotizacion cotizacion;

    public CotizacionDetalle() {
    }

    public CotizacionDetalle(Long id) {
        this.id = id;
    }

    public CotizacionDetalle(Long id, BigDecimal tarifa, Date fechaEfectiva, String estado) {
        this.id = id;
        this.tarifa = tarifa;
        this.fechaEfectiva = fechaEfectiva;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTarifa() {
        return tarifa;
    }

    public void setTarifa(BigDecimal tarifa) {
        this.tarifa = tarifa;
    }

    public Date getFechaEfectiva() {
        return fechaEfectiva;
    }

    public void setFechaEfectiva(Date fechaEfectiva) {
        this.fechaEfectiva = fechaEfectiva;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Cotizacion getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(Cotizacion cotizacion) {
        this.cotizacion = cotizacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CotizacionDetalle)) {
            return false;
        }
        CotizacionDetalle other = (CotizacionDetalle) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tamarind.entites.CotizacionDetalle[ id=" + id + " ]";
    }
    
}
