/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.1</a>, using an XML
 * Schema.
 * $Id: DetalleRecapType.java,v 1.1 2014/03/28 18:06:35 xrf Exp $
 */

package com.spirit.sri.at;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import java.math.BigDecimal;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.xml.sax.ContentHandler;

/**
 * Class DetalleRecapType.
 * 
 * @version $Revision: 1.1 $ $Date: 2014/03/28 18:06:35 $
 */
public class DetalleRecapType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _establecimientoRecap
     */
    private java.lang.String _establecimientoRecap;

    /**
     * Field _identificacionRecap
     */
    private java.lang.String _identificacionRecap;

    /**
     * Field _tipoComprobante
     */
    private int _tipoComprobante;

    /**
     * keeps track of state for field: _tipoComprobante
     */
    private boolean _has_tipoComprobante;

    /**
     * Field _numeroRecap
     */
    private java.lang.String _numeroRecap;

    /**
     * Field _fechaPago
     */
    private java.lang.String _fechaPago;

    /**
     * Field _tarjetaCredito
     */
    private java.lang.String _tarjetaCredito;

    /**
     * Field _fechaEmisionRecap
     */
    private java.lang.String _fechaEmisionRecap;

    /**
     * Field _consumoCero
     */
    private java.math.BigDecimal _consumoCero;

    /**
     * Field _consumoGravado
     */
    private java.math.BigDecimal _consumoGravado;

    /**
     * Field _totalConsumo
     */
    private java.math.BigDecimal _totalConsumo;

    /**
     * Field _montoIva
     */
    private java.math.BigDecimal _montoIva;

    /**
     * Field _comision
     */
    private java.math.BigDecimal _comision;

    /**
     * Field _numeroVouchers
     */
    private int _numeroVouchers;

    /**
     * keeps track of state for field: _numeroVouchers
     */
    private boolean _has_numeroVouchers;

    /**
     * Field _valorRetBienes
     */
    private java.math.BigDecimal _valorRetBienes;

    /**
     * Field _valorRetServicios
     */
    private java.math.BigDecimal _valorRetServicios;

    /**
     * Field _valRetServ100
     */
    private java.math.BigDecimal _valRetServ100;

    /**
     * Field _air
     */
    private com.spirit.sri.at.Air _air;

    /**
     * Field _establecimiento
     */
    private java.lang.String _establecimiento;

    /**
     * Field _puntoEmision
     */
    private java.lang.String _puntoEmision;

    /**
     * Field _secuencial
     */
    private int _secuencial;

    /**
     * keeps track of state for field: _secuencial
     */
    private boolean _has_secuencial;

    /**
     * Field _autorizacion
     */
    private java.lang.String _autorizacion;

    /**
     * Field _fechaEmision
     */
    private java.lang.String _fechaEmision;


      //----------------/
     //- Constructors -/
    //----------------/

    public DetalleRecapType() 
     {
        super();
    } //-- com.spirit.sri.at.DetalleRecapType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method deleteNumeroVouchers
     * 
     */
    public void deleteNumeroVouchers()
    {
        this._has_numeroVouchers= false;
    } //-- void deleteNumeroVouchers() 

    /**
     * Method deleteSecuencial
     * 
     */
    public void deleteSecuencial()
    {
        this._has_secuencial= false;
    } //-- void deleteSecuencial() 

    /**
     * Method deleteTipoComprobante
     * 
     */
    public void deleteTipoComprobante()
    {
        this._has_tipoComprobante= false;
    } //-- void deleteTipoComprobante() 

    /**
     * Returns the value of field 'air'.
     * 
     * @return Air
     * @return the value of field 'air'.
     */
    public com.spirit.sri.at.Air getAir()
    {
        return this._air;
    } //-- com.spirit.sri.at.Air getAir() 

    /**
     * Returns the value of field 'autorizacion'.
     * 
     * @return String
     * @return the value of field 'autorizacion'.
     */
    public java.lang.String getAutorizacion()
    {
        return this._autorizacion;
    } //-- java.lang.String getAutorizacion() 

    /**
     * Returns the value of field 'comision'.
     * 
     * @return BigDecimal
     * @return the value of field 'comision'.
     */
    public java.math.BigDecimal getComision()
    {
        return this._comision;
    } //-- java.math.BigDecimal getComision() 

    /**
     * Returns the value of field 'consumoCero'.
     * 
     * @return BigDecimal
     * @return the value of field 'consumoCero'.
     */
    public java.math.BigDecimal getConsumoCero()
    {
        return this._consumoCero;
    } //-- java.math.BigDecimal getConsumoCero() 

    /**
     * Returns the value of field 'consumoGravado'.
     * 
     * @return BigDecimal
     * @return the value of field 'consumoGravado'.
     */
    public java.math.BigDecimal getConsumoGravado()
    {
        return this._consumoGravado;
    } //-- java.math.BigDecimal getConsumoGravado() 

    /**
     * Returns the value of field 'establecimiento'.
     * 
     * @return String
     * @return the value of field 'establecimiento'.
     */
    public java.lang.String getEstablecimiento()
    {
        return this._establecimiento;
    } //-- java.lang.String getEstablecimiento() 

    /**
     * Returns the value of field 'establecimientoRecap'.
     * 
     * @return String
     * @return the value of field 'establecimientoRecap'.
     */
    public java.lang.String getEstablecimientoRecap()
    {
        return this._establecimientoRecap;
    } //-- java.lang.String getEstablecimientoRecap() 

    /**
     * Returns the value of field 'fechaEmision'.
     * 
     * @return String
     * @return the value of field 'fechaEmision'.
     */
    public java.lang.String getFechaEmision()
    {
        return this._fechaEmision;
    } //-- java.lang.String getFechaEmision() 

    /**
     * Returns the value of field 'fechaEmisionRecap'.
     * 
     * @return String
     * @return the value of field 'fechaEmisionRecap'.
     */
    public java.lang.String getFechaEmisionRecap()
    {
        return this._fechaEmisionRecap;
    } //-- java.lang.String getFechaEmisionRecap() 

    /**
     * Returns the value of field 'fechaPago'.
     * 
     * @return String
     * @return the value of field 'fechaPago'.
     */
    public java.lang.String getFechaPago()
    {
        return this._fechaPago;
    } //-- java.lang.String getFechaPago() 

    /**
     * Returns the value of field 'identificacionRecap'.
     * 
     * @return String
     * @return the value of field 'identificacionRecap'.
     */
    public java.lang.String getIdentificacionRecap()
    {
        return this._identificacionRecap;
    } //-- java.lang.String getIdentificacionRecap() 

    /**
     * Returns the value of field 'montoIva'.
     * 
     * @return BigDecimal
     * @return the value of field 'montoIva'.
     */
    public java.math.BigDecimal getMontoIva()
    {
        return this._montoIva;
    } //-- java.math.BigDecimal getMontoIva() 

    /**
     * Returns the value of field 'numeroRecap'.
     * 
     * @return String
     * @return the value of field 'numeroRecap'.
     */
    public java.lang.String getNumeroRecap()
    {
        return this._numeroRecap;
    } //-- java.lang.String getNumeroRecap() 

    /**
     * Returns the value of field 'numeroVouchers'.
     * 
     * @return int
     * @return the value of field 'numeroVouchers'.
     */
    public int getNumeroVouchers()
    {
        return this._numeroVouchers;
    } //-- int getNumeroVouchers() 

    /**
     * Returns the value of field 'puntoEmision'.
     * 
     * @return String
     * @return the value of field 'puntoEmision'.
     */
    public java.lang.String getPuntoEmision()
    {
        return this._puntoEmision;
    } //-- java.lang.String getPuntoEmision() 

    /**
     * Returns the value of field 'secuencial'.
     * 
     * @return int
     * @return the value of field 'secuencial'.
     */
    public int getSecuencial()
    {
        return this._secuencial;
    } //-- int getSecuencial() 

    /**
     * Returns the value of field 'tarjetaCredito'.
     * 
     * @return String
     * @return the value of field 'tarjetaCredito'.
     */
    public java.lang.String getTarjetaCredito()
    {
        return this._tarjetaCredito;
    } //-- java.lang.String getTarjetaCredito() 

    /**
     * Returns the value of field 'tipoComprobante'.
     * 
     * @return int
     * @return the value of field 'tipoComprobante'.
     */
    public int getTipoComprobante()
    {
        return this._tipoComprobante;
    } //-- int getTipoComprobante() 

    /**
     * Returns the value of field 'totalConsumo'.
     * 
     * @return BigDecimal
     * @return the value of field 'totalConsumo'.
     */
    public java.math.BigDecimal getTotalConsumo()
    {
        return this._totalConsumo;
    } //-- java.math.BigDecimal getTotalConsumo() 

    /**
     * Returns the value of field 'valRetServ100'.
     * 
     * @return BigDecimal
     * @return the value of field 'valRetServ100'.
     */
    public java.math.BigDecimal getValRetServ100()
    {
        return this._valRetServ100;
    } //-- java.math.BigDecimal getValRetServ100() 

    /**
     * Returns the value of field 'valorRetBienes'.
     * 
     * @return BigDecimal
     * @return the value of field 'valorRetBienes'.
     */
    public java.math.BigDecimal getValorRetBienes()
    {
        return this._valorRetBienes;
    } //-- java.math.BigDecimal getValorRetBienes() 

    /**
     * Returns the value of field 'valorRetServicios'.
     * 
     * @return BigDecimal
     * @return the value of field 'valorRetServicios'.
     */
    public java.math.BigDecimal getValorRetServicios()
    {
        return this._valorRetServicios;
    } //-- java.math.BigDecimal getValorRetServicios() 

    /**
     * Method hasNumeroVouchers
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasNumeroVouchers()
    {
        return this._has_numeroVouchers;
    } //-- boolean hasNumeroVouchers() 

    /**
     * Method hasSecuencial
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasSecuencial()
    {
        return this._has_secuencial;
    } //-- boolean hasSecuencial() 

    /**
     * Method hasTipoComprobante
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasTipoComprobante()
    {
        return this._has_tipoComprobante;
    } //-- boolean hasTipoComprobante() 

    /**
     * Method isValid
     * 
     * 
     * 
     * @return boolean
     */
    public boolean isValid()
    {
        try {
            validate();
        }
        catch (org.exolab.castor.xml.ValidationException vex) {
            return false;
        }
        return true;
    } //-- boolean isValid() 

    /**
     * Method marshal
     * 
     * 
     * 
     * @param out
     */
    public void marshal(java.io.Writer out)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        
        Marshaller.marshal(this, out);
    } //-- void marshal(java.io.Writer) 

    /**
     * Method marshal
     * 
     * 
     * 
     * @param handler
     */
    public void marshal(org.xml.sax.ContentHandler handler)
        throws java.io.IOException, org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        
        Marshaller.marshal(this, handler);
    } //-- void marshal(org.xml.sax.ContentHandler) 

    /**
     * Sets the value of field 'air'.
     * 
     * @param air the value of field 'air'.
     */
    public void setAir(com.spirit.sri.at.Air air)
    {
        this._air = air;
    } //-- void setAir(com.spirit.sri.at.Air) 

    /**
     * Sets the value of field 'autorizacion'.
     * 
     * @param autorizacion the value of field 'autorizacion'.
     */
    public void setAutorizacion(java.lang.String autorizacion)
    {
        this._autorizacion = autorizacion;
    } //-- void setAutorizacion(java.lang.String) 

    /**
     * Sets the value of field 'comision'.
     * 
     * @param comision the value of field 'comision'.
     */
    public void setComision(java.math.BigDecimal comision)
    {
        this._comision = comision;
    } //-- void setComision(java.math.BigDecimal) 

    /**
     * Sets the value of field 'consumoCero'.
     * 
     * @param consumoCero the value of field 'consumoCero'.
     */
    public void setConsumoCero(java.math.BigDecimal consumoCero)
    {
        this._consumoCero = consumoCero;
    } //-- void setConsumoCero(java.math.BigDecimal) 

    /**
     * Sets the value of field 'consumoGravado'.
     * 
     * @param consumoGravado the value of field 'consumoGravado'.
     */
    public void setConsumoGravado(java.math.BigDecimal consumoGravado)
    {
        this._consumoGravado = consumoGravado;
    } //-- void setConsumoGravado(java.math.BigDecimal) 

    /**
     * Sets the value of field 'establecimiento'.
     * 
     * @param establecimiento the value of field 'establecimiento'.
     */
    public void setEstablecimiento(java.lang.String establecimiento)
    {
        this._establecimiento = establecimiento;
    } //-- void setEstablecimiento(java.lang.String) 

    /**
     * Sets the value of field 'establecimientoRecap'.
     * 
     * @param establecimientoRecap the value of field
     * 'establecimientoRecap'.
     */
    public void setEstablecimientoRecap(java.lang.String establecimientoRecap)
    {
        this._establecimientoRecap = establecimientoRecap;
    } //-- void setEstablecimientoRecap(java.lang.String) 

    /**
     * Sets the value of field 'fechaEmision'.
     * 
     * @param fechaEmision the value of field 'fechaEmision'.
     */
    public void setFechaEmision(java.lang.String fechaEmision)
    {
        this._fechaEmision = fechaEmision;
    } //-- void setFechaEmision(java.lang.String) 

    /**
     * Sets the value of field 'fechaEmisionRecap'.
     * 
     * @param fechaEmisionRecap the value of field
     * 'fechaEmisionRecap'.
     */
    public void setFechaEmisionRecap(java.lang.String fechaEmisionRecap)
    {
        this._fechaEmisionRecap = fechaEmisionRecap;
    } //-- void setFechaEmisionRecap(java.lang.String) 

    /**
     * Sets the value of field 'fechaPago'.
     * 
     * @param fechaPago the value of field 'fechaPago'.
     */
    public void setFechaPago(java.lang.String fechaPago)
    {
        this._fechaPago = fechaPago;
    } //-- void setFechaPago(java.lang.String) 

    /**
     * Sets the value of field 'identificacionRecap'.
     * 
     * @param identificacionRecap the value of field
     * 'identificacionRecap'.
     */
    public void setIdentificacionRecap(java.lang.String identificacionRecap)
    {
        this._identificacionRecap = identificacionRecap;
    } //-- void setIdentificacionRecap(java.lang.String) 

    /**
     * Sets the value of field 'montoIva'.
     * 
     * @param montoIva the value of field 'montoIva'.
     */
    public void setMontoIva(java.math.BigDecimal montoIva)
    {
        this._montoIva = montoIva;
    } //-- void setMontoIva(java.math.BigDecimal) 

    /**
     * Sets the value of field 'numeroRecap'.
     * 
     * @param numeroRecap the value of field 'numeroRecap'.
     */
    public void setNumeroRecap(java.lang.String numeroRecap)
    {
        this._numeroRecap = numeroRecap;
    } //-- void setNumeroRecap(java.lang.String) 

    /**
     * Sets the value of field 'numeroVouchers'.
     * 
     * @param numeroVouchers the value of field 'numeroVouchers'.
     */
    public void setNumeroVouchers(int numeroVouchers)
    {
        this._numeroVouchers = numeroVouchers;
        this._has_numeroVouchers = true;
    } //-- void setNumeroVouchers(int) 

    /**
     * Sets the value of field 'puntoEmision'.
     * 
     * @param puntoEmision the value of field 'puntoEmision'.
     */
    public void setPuntoEmision(java.lang.String puntoEmision)
    {
        this._puntoEmision = puntoEmision;
    } //-- void setPuntoEmision(java.lang.String) 

    /**
     * Sets the value of field 'secuencial'.
     * 
     * @param secuencial the value of field 'secuencial'.
     */
    public void setSecuencial(int secuencial)
    {
        this._secuencial = secuencial;
        this._has_secuencial = true;
    } //-- void setSecuencial(int) 

    /**
     * Sets the value of field 'tarjetaCredito'.
     * 
     * @param tarjetaCredito the value of field 'tarjetaCredito'.
     */
    public void setTarjetaCredito(java.lang.String tarjetaCredito)
    {
        this._tarjetaCredito = tarjetaCredito;
    } //-- void setTarjetaCredito(java.lang.String) 

    /**
     * Sets the value of field 'tipoComprobante'.
     * 
     * @param tipoComprobante the value of field 'tipoComprobante'.
     */
    public void setTipoComprobante(int tipoComprobante)
    {
        this._tipoComprobante = tipoComprobante;
        this._has_tipoComprobante = true;
    } //-- void setTipoComprobante(int) 

    /**
     * Sets the value of field 'totalConsumo'.
     * 
     * @param totalConsumo the value of field 'totalConsumo'.
     */
    public void setTotalConsumo(java.math.BigDecimal totalConsumo)
    {
        this._totalConsumo = totalConsumo;
    } //-- void setTotalConsumo(java.math.BigDecimal) 

    /**
     * Sets the value of field 'valRetServ100'.
     * 
     * @param valRetServ100 the value of field 'valRetServ100'.
     */
    public void setValRetServ100(java.math.BigDecimal valRetServ100)
    {
        this._valRetServ100 = valRetServ100;
    } //-- void setValRetServ100(java.math.BigDecimal) 

    /**
     * Sets the value of field 'valorRetBienes'.
     * 
     * @param valorRetBienes the value of field 'valorRetBienes'.
     */
    public void setValorRetBienes(java.math.BigDecimal valorRetBienes)
    {
        this._valorRetBienes = valorRetBienes;
    } //-- void setValorRetBienes(java.math.BigDecimal) 

    /**
     * Sets the value of field 'valorRetServicios'.
     * 
     * @param valorRetServicios the value of field
     * 'valorRetServicios'.
     */
    public void setValorRetServicios(java.math.BigDecimal valorRetServicios)
    {
        this._valorRetServicios = valorRetServicios;
    } //-- void setValorRetServicios(java.math.BigDecimal) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return DetalleRecapType
     */
    public static com.spirit.sri.at.DetalleRecapType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.spirit.sri.at.DetalleRecapType) Unmarshaller.unmarshal(com.spirit.sri.at.DetalleRecapType.class, reader);
    } //-- com.spirit.sri.at.DetalleRecapType unmarshal(java.io.Reader) 

    /**
     * Method validate
     * 
     */
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}
