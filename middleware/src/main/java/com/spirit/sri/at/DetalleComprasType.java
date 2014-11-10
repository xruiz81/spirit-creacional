/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.1</a>, using an XML
 * Schema.
 * $Id: DetalleComprasType.java,v 1.1 2014/03/28 18:06:35 xrf Exp $
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
 * Class DetalleComprasType.
 * 
 * @version $Revision: 1.1 $ $Date: 2014/03/28 18:06:35 $
 */
public class DetalleComprasType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _codSustento
     */
    private java.lang.String _codSustento;

    /**
     * Field _tpIdProv
     */
    private java.lang.String _tpIdProv;

    /**
     * Field _idProv
     */
    private java.lang.String _idProv;

    /**
     * Field _tipoComprobante
     */
    private int _tipoComprobante;

    /**
     * keeps track of state for field: _tipoComprobante
     */
    private boolean _has_tipoComprobante;

    /**
     * Field _fechaRegistro
     */
    private java.lang.String _fechaRegistro;

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
     * Field _fechaEmision
     */
    private java.lang.String _fechaEmision;

    /**
     * Field _autorizacion
     */
    private java.lang.String _autorizacion;

    /**
     * Field _baseNoGraIva
     */
    private java.math.BigDecimal _baseNoGraIva;

    /**
     * Field _baseImponible
     */
    private java.math.BigDecimal _baseImponible;

    /**
     * Field _baseImpGrav
     */
    private java.math.BigDecimal _baseImpGrav;

    /**
     * Field _montoIce
     */
    private java.math.BigDecimal _montoIce;

    /**
     * Field _montoIva
     */
    private java.math.BigDecimal _montoIva;

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
     * Field _estabRetencion1
     */
    private java.lang.String _estabRetencion1;

    /**
     * Field _ptoEmiRetencion1
     */
    private java.lang.String _ptoEmiRetencion1;

    /**
     * Field _secRetencion1
     */
    private int _secRetencion1;

    /**
     * keeps track of state for field: _secRetencion1
     */
    private boolean _has_secRetencion1;

    /**
     * Field _autRetencion1
     */
    private java.lang.String _autRetencion1;

    /**
     * Field _fechaEmiRet1
     */
    private java.lang.String _fechaEmiRet1;

    /**
     * Field _estabRetencion2
     */
    private java.lang.String _estabRetencion2;

    /**
     * Field _ptoEmiRetencion2
     */
    private java.lang.String _ptoEmiRetencion2;

    /**
     * Field _secRetencion2
     */
    private int _secRetencion2;

    /**
     * keeps track of state for field: _secRetencion2
     */
    private boolean _has_secRetencion2;

    /**
     * Field _autRetencion2
     */
    private java.lang.String _autRetencion2;

    /**
     * Field _fechaEmiRet2
     */
    private java.lang.String _fechaEmiRet2;

    /**
     * Field _docModificado
     */
    private int _docModificado;

    /**
     * keeps track of state for field: _docModificado
     */
    private boolean _has_docModificado;

    /**
     * Field _estabModificado
     */
    private java.lang.String _estabModificado;

    /**
     * Field _ptoEmiModificado
     */
    private java.lang.String _ptoEmiModificado;

    /**
     * Field _secModificado
     */
    private int _secModificado;

    /**
     * keeps track of state for field: _secModificado
     */
    private boolean _has_secModificado;

    /**
     * Field _autModificado
     */
    private java.lang.String _autModificado;


      //----------------/
     //- Constructors -/
    //----------------/

    public DetalleComprasType() 
     {
        super();
    } //-- com.spirit.sri.at.DetalleComprasType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method deleteDocModificado
     * 
     */
    public void deleteDocModificado()
    {
        this._has_docModificado= false;
    } //-- void deleteDocModificado() 

    /**
     * Method deleteSecModificado
     * 
     */
    public void deleteSecModificado()
    {
        this._has_secModificado= false;
    } //-- void deleteSecModificado() 

    /**
     * Method deleteSecRetencion1
     * 
     */
    public void deleteSecRetencion1()
    {
        this._has_secRetencion1= false;
    } //-- void deleteSecRetencion1() 

    /**
     * Method deleteSecRetencion2
     * 
     */
    public void deleteSecRetencion2()
    {
        this._has_secRetencion2= false;
    } //-- void deleteSecRetencion2() 

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
     * Returns the value of field 'autModificado'.
     * 
     * @return String
     * @return the value of field 'autModificado'.
     */
    public java.lang.String getAutModificado()
    {
        return this._autModificado;
    } //-- java.lang.String getAutModificado() 

    /**
     * Returns the value of field 'autRetencion1'.
     * 
     * @return String
     * @return the value of field 'autRetencion1'.
     */
    public java.lang.String getAutRetencion1()
    {
        return this._autRetencion1;
    } //-- java.lang.String getAutRetencion1() 

    /**
     * Returns the value of field 'autRetencion2'.
     * 
     * @return String
     * @return the value of field 'autRetencion2'.
     */
    public java.lang.String getAutRetencion2()
    {
        return this._autRetencion2;
    } //-- java.lang.String getAutRetencion2() 

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
     * Returns the value of field 'baseImpGrav'.
     * 
     * @return BigDecimal
     * @return the value of field 'baseImpGrav'.
     */
    public java.math.BigDecimal getBaseImpGrav()
    {
        return this._baseImpGrav;
    } //-- java.math.BigDecimal getBaseImpGrav() 

    /**
     * Returns the value of field 'baseImponible'.
     * 
     * @return BigDecimal
     * @return the value of field 'baseImponible'.
     */
    public java.math.BigDecimal getBaseImponible()
    {
        return this._baseImponible;
    } //-- java.math.BigDecimal getBaseImponible() 

    /**
     * Returns the value of field 'baseNoGraIva'.
     * 
     * @return BigDecimal
     * @return the value of field 'baseNoGraIva'.
     */
    public java.math.BigDecimal getBaseNoGraIva()
    {
        return this._baseNoGraIva;
    } //-- java.math.BigDecimal getBaseNoGraIva() 

    /**
     * Returns the value of field 'codSustento'.
     * 
     * @return String
     * @return the value of field 'codSustento'.
     */
    public java.lang.String getCodSustento()
    {
        return this._codSustento;
    } //-- java.lang.String getCodSustento() 

    /**
     * Returns the value of field 'docModificado'.
     * 
     * @return int
     * @return the value of field 'docModificado'.
     */
    public int getDocModificado()
    {
        return this._docModificado;
    } //-- int getDocModificado() 

    /**
     * Returns the value of field 'estabModificado'.
     * 
     * @return String
     * @return the value of field 'estabModificado'.
     */
    public java.lang.String getEstabModificado()
    {
        return this._estabModificado;
    } //-- java.lang.String getEstabModificado() 

    /**
     * Returns the value of field 'estabRetencion1'.
     * 
     * @return String
     * @return the value of field 'estabRetencion1'.
     */
    public java.lang.String getEstabRetencion1()
    {
        return this._estabRetencion1;
    } //-- java.lang.String getEstabRetencion1() 

    /**
     * Returns the value of field 'estabRetencion2'.
     * 
     * @return String
     * @return the value of field 'estabRetencion2'.
     */
    public java.lang.String getEstabRetencion2()
    {
        return this._estabRetencion2;
    } //-- java.lang.String getEstabRetencion2() 

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
     * Returns the value of field 'fechaEmiRet1'.
     * 
     * @return String
     * @return the value of field 'fechaEmiRet1'.
     */
    public java.lang.String getFechaEmiRet1()
    {
        return this._fechaEmiRet1;
    } //-- java.lang.String getFechaEmiRet1() 

    /**
     * Returns the value of field 'fechaEmiRet2'.
     * 
     * @return String
     * @return the value of field 'fechaEmiRet2'.
     */
    public java.lang.String getFechaEmiRet2()
    {
        return this._fechaEmiRet2;
    } //-- java.lang.String getFechaEmiRet2() 

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
     * Returns the value of field 'fechaRegistro'.
     * 
     * @return String
     * @return the value of field 'fechaRegistro'.
     */
    public java.lang.String getFechaRegistro()
    {
        return this._fechaRegistro;
    } //-- java.lang.String getFechaRegistro() 

    /**
     * Returns the value of field 'idProv'.
     * 
     * @return String
     * @return the value of field 'idProv'.
     */
    public java.lang.String getIdProv()
    {
        return this._idProv;
    } //-- java.lang.String getIdProv() 

    /**
     * Returns the value of field 'montoIce'.
     * 
     * @return BigDecimal
     * @return the value of field 'montoIce'.
     */
    public java.math.BigDecimal getMontoIce()
    {
        return this._montoIce;
    } //-- java.math.BigDecimal getMontoIce() 

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
     * Returns the value of field 'ptoEmiModificado'.
     * 
     * @return String
     * @return the value of field 'ptoEmiModificado'.
     */
    public java.lang.String getPtoEmiModificado()
    {
        return this._ptoEmiModificado;
    } //-- java.lang.String getPtoEmiModificado() 

    /**
     * Returns the value of field 'ptoEmiRetencion1'.
     * 
     * @return String
     * @return the value of field 'ptoEmiRetencion1'.
     */
    public java.lang.String getPtoEmiRetencion1()
    {
        return this._ptoEmiRetencion1;
    } //-- java.lang.String getPtoEmiRetencion1() 

    /**
     * Returns the value of field 'ptoEmiRetencion2'.
     * 
     * @return String
     * @return the value of field 'ptoEmiRetencion2'.
     */
    public java.lang.String getPtoEmiRetencion2()
    {
        return this._ptoEmiRetencion2;
    } //-- java.lang.String getPtoEmiRetencion2() 

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
     * Returns the value of field 'secModificado'.
     * 
     * @return int
     * @return the value of field 'secModificado'.
     */
    public int getSecModificado()
    {
        return this._secModificado;
    } //-- int getSecModificado() 

    /**
     * Returns the value of field 'secRetencion1'.
     * 
     * @return int
     * @return the value of field 'secRetencion1'.
     */
    public int getSecRetencion1()
    {
        return this._secRetencion1;
    } //-- int getSecRetencion1() 

    /**
     * Returns the value of field 'secRetencion2'.
     * 
     * @return int
     * @return the value of field 'secRetencion2'.
     */
    public int getSecRetencion2()
    {
        return this._secRetencion2;
    } //-- int getSecRetencion2() 

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
     * Returns the value of field 'tpIdProv'.
     * 
     * @return String
     * @return the value of field 'tpIdProv'.
     */
    public java.lang.String getTpIdProv()
    {
        return this._tpIdProv;
    } //-- java.lang.String getTpIdProv() 

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
     * Method hasDocModificado
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasDocModificado()
    {
        return this._has_docModificado;
    } //-- boolean hasDocModificado() 

    /**
     * Method hasSecModificado
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasSecModificado()
    {
        return this._has_secModificado;
    } //-- boolean hasSecModificado() 

    /**
     * Method hasSecRetencion1
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasSecRetencion1()
    {
        return this._has_secRetencion1;
    } //-- boolean hasSecRetencion1() 

    /**
     * Method hasSecRetencion2
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasSecRetencion2()
    {
        return this._has_secRetencion2;
    } //-- boolean hasSecRetencion2() 

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
     * Sets the value of field 'autModificado'.
     * 
     * @param autModificado the value of field 'autModificado'.
     */
    public void setAutModificado(java.lang.String autModificado)
    {
        this._autModificado = autModificado;
    } //-- void setAutModificado(java.lang.String) 

    /**
     * Sets the value of field 'autRetencion1'.
     * 
     * @param autRetencion1 the value of field 'autRetencion1'.
     */
    public void setAutRetencion1(java.lang.String autRetencion1)
    {
        this._autRetencion1 = autRetencion1;
    } //-- void setAutRetencion1(java.lang.String) 

    /**
     * Sets the value of field 'autRetencion2'.
     * 
     * @param autRetencion2 the value of field 'autRetencion2'.
     */
    public void setAutRetencion2(java.lang.String autRetencion2)
    {
        this._autRetencion2 = autRetencion2;
    } //-- void setAutRetencion2(java.lang.String) 

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
     * Sets the value of field 'baseImpGrav'.
     * 
     * @param baseImpGrav the value of field 'baseImpGrav'.
     */
    public void setBaseImpGrav(java.math.BigDecimal baseImpGrav)
    {
        this._baseImpGrav = baseImpGrav;
    } //-- void setBaseImpGrav(java.math.BigDecimal) 

    /**
     * Sets the value of field 'baseImponible'.
     * 
     * @param baseImponible the value of field 'baseImponible'.
     */
    public void setBaseImponible(java.math.BigDecimal baseImponible)
    {
        this._baseImponible = baseImponible;
    } //-- void setBaseImponible(java.math.BigDecimal) 

    /**
     * Sets the value of field 'baseNoGraIva'.
     * 
     * @param baseNoGraIva the value of field 'baseNoGraIva'.
     */
    public void setBaseNoGraIva(java.math.BigDecimal baseNoGraIva)
    {
        this._baseNoGraIva = baseNoGraIva;
    } //-- void setBaseNoGraIva(java.math.BigDecimal) 

    /**
     * Sets the value of field 'codSustento'.
     * 
     * @param codSustento the value of field 'codSustento'.
     */
    public void setCodSustento(java.lang.String codSustento)
    {
        this._codSustento = codSustento;
    } //-- void setCodSustento(java.lang.String) 

    /**
     * Sets the value of field 'docModificado'.
     * 
     * @param docModificado the value of field 'docModificado'.
     */
    public void setDocModificado(int docModificado)
    {
        this._docModificado = docModificado;
        this._has_docModificado = true;
    } //-- void setDocModificado(int) 

    /**
     * Sets the value of field 'estabModificado'.
     * 
     * @param estabModificado the value of field 'estabModificado'.
     */
    public void setEstabModificado(java.lang.String estabModificado)
    {
        this._estabModificado = estabModificado;
    } //-- void setEstabModificado(java.lang.String) 

    /**
     * Sets the value of field 'estabRetencion1'.
     * 
     * @param estabRetencion1 the value of field 'estabRetencion1'.
     */
    public void setEstabRetencion1(java.lang.String estabRetencion1)
    {
        this._estabRetencion1 = estabRetencion1;
    } //-- void setEstabRetencion1(java.lang.String) 

    /**
     * Sets the value of field 'estabRetencion2'.
     * 
     * @param estabRetencion2 the value of field 'estabRetencion2'.
     */
    public void setEstabRetencion2(java.lang.String estabRetencion2)
    {
        this._estabRetencion2 = estabRetencion2;
    } //-- void setEstabRetencion2(java.lang.String) 

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
     * Sets the value of field 'fechaEmiRet1'.
     * 
     * @param fechaEmiRet1 the value of field 'fechaEmiRet1'.
     */
    public void setFechaEmiRet1(java.lang.String fechaEmiRet1)
    {
        this._fechaEmiRet1 = fechaEmiRet1;
    } //-- void setFechaEmiRet1(java.lang.String) 

    /**
     * Sets the value of field 'fechaEmiRet2'.
     * 
     * @param fechaEmiRet2 the value of field 'fechaEmiRet2'.
     */
    public void setFechaEmiRet2(java.lang.String fechaEmiRet2)
    {
        this._fechaEmiRet2 = fechaEmiRet2;
    } //-- void setFechaEmiRet2(java.lang.String) 

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
     * Sets the value of field 'fechaRegistro'.
     * 
     * @param fechaRegistro the value of field 'fechaRegistro'.
     */
    public void setFechaRegistro(java.lang.String fechaRegistro)
    {
        this._fechaRegistro = fechaRegistro;
    } //-- void setFechaRegistro(java.lang.String) 

    /**
     * Sets the value of field 'idProv'.
     * 
     * @param idProv the value of field 'idProv'.
     */
    public void setIdProv(java.lang.String idProv)
    {
        this._idProv = idProv;
    } //-- void setIdProv(java.lang.String) 

    /**
     * Sets the value of field 'montoIce'.
     * 
     * @param montoIce the value of field 'montoIce'.
     */
    public void setMontoIce(java.math.BigDecimal montoIce)
    {
        this._montoIce = montoIce;
    } //-- void setMontoIce(java.math.BigDecimal) 

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
     * Sets the value of field 'ptoEmiModificado'.
     * 
     * @param ptoEmiModificado the value of field 'ptoEmiModificado'
     */
    public void setPtoEmiModificado(java.lang.String ptoEmiModificado)
    {
        this._ptoEmiModificado = ptoEmiModificado;
    } //-- void setPtoEmiModificado(java.lang.String) 

    /**
     * Sets the value of field 'ptoEmiRetencion1'.
     * 
     * @param ptoEmiRetencion1 the value of field 'ptoEmiRetencion1'
     */
    public void setPtoEmiRetencion1(java.lang.String ptoEmiRetencion1)
    {
        this._ptoEmiRetencion1 = ptoEmiRetencion1;
    } //-- void setPtoEmiRetencion1(java.lang.String) 

    /**
     * Sets the value of field 'ptoEmiRetencion2'.
     * 
     * @param ptoEmiRetencion2 the value of field 'ptoEmiRetencion2'
     */
    public void setPtoEmiRetencion2(java.lang.String ptoEmiRetencion2)
    {
        this._ptoEmiRetencion2 = ptoEmiRetencion2;
    } //-- void setPtoEmiRetencion2(java.lang.String) 

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
     * Sets the value of field 'secModificado'.
     * 
     * @param secModificado the value of field 'secModificado'.
     */
    public void setSecModificado(int secModificado)
    {
        this._secModificado = secModificado;
        this._has_secModificado = true;
    } //-- void setSecModificado(int) 

    /**
     * Sets the value of field 'secRetencion1'.
     * 
     * @param secRetencion1 the value of field 'secRetencion1'.
     */
    public void setSecRetencion1(int secRetencion1)
    {
        this._secRetencion1 = secRetencion1;
        this._has_secRetencion1 = true;
    } //-- void setSecRetencion1(int) 

    /**
     * Sets the value of field 'secRetencion2'.
     * 
     * @param secRetencion2 the value of field 'secRetencion2'.
     */
    public void setSecRetencion2(int secRetencion2)
    {
        this._secRetencion2 = secRetencion2;
        this._has_secRetencion2 = true;
    } //-- void setSecRetencion2(int) 

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
     * Sets the value of field 'tpIdProv'.
     * 
     * @param tpIdProv the value of field 'tpIdProv'.
     */
    public void setTpIdProv(java.lang.String tpIdProv)
    {
        this._tpIdProv = tpIdProv;
    } //-- void setTpIdProv(java.lang.String) 

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
     * @return DetalleComprasType
     */
    public static com.spirit.sri.at.DetalleComprasType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.spirit.sri.at.DetalleComprasType) Unmarshaller.unmarshal(com.spirit.sri.at.DetalleComprasType.class, reader);
    } //-- com.spirit.sri.at.DetalleComprasType unmarshal(java.io.Reader) 

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
