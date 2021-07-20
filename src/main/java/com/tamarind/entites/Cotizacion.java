/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tamarind.entites;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SALAZAR
 */
@Entity
@Table(name = "cotizacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cotizacion.findAll", query = "SELECT c FROM Cotizacion c"),
    @NamedQuery(name = "Cotizacion.findById", query = "SELECT c FROM Cotizacion c WHERE c.id = :id"),
    @NamedQuery(name = "Cotizacion.findByUsuarioCreacion", query = "SELECT c FROM Cotizacion c WHERE c.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "Cotizacion.findByFechaCreacion", query = "SELECT c FROM Cotizacion c WHERE c.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "Cotizacion.findByActivo", query = "SELECT c FROM Cotizacion c WHERE c.activo = :activo")})
public class Cotizacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "usuario_creacion")
    private String usuarioCreacion;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    @JoinColumn(name = "cliete_destino_tarifa", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ClienteDestinoTarifa clieteDestinoTarifa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cotizacion")
    private List<CotizacionDetalle> cotizacionDetalleList;

    public Cotizacion() {
    }

    public Cotizacion(Long id) {
        this.id = id;
    }

    public Cotizacion(Long id, String usuarioCreacion, boolean activo) {
        this.id = id;
        this.usuarioCreacion = usuarioCreacion;
        this.activo = activo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public ClienteDestinoTarifa getClieteDestinoTarifa() {
        return clieteDestinoTarifa;
    }

    public void setClieteDestinoTarifa(ClienteDestinoTarifa clieteDestinoTarifa) {
        this.clieteDestinoTarifa = clieteDestinoTarifa;
    }

    @XmlTransient
    public List<CotizacionDetalle> getCotizacionDetalleList() {
        return cotizacionDetalleList;
    }

    public void setCotizacionDetalleList(List<CotizacionDetalle> cotizacionDetalleList) {
        this.cotizacionDetalleList = cotizacionDetalleList;
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
        if (!(object instanceof Cotizacion)) {
            return false;
        }
        Cotizacion other = (Cotizacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tamarind.entites.Cotizacion[ id=" + id + " ]";
    }
    
}
