/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.1</a>, using an XML
 * Schema.
 * $Id: DetalleVentasType.java,v 1.1 2014/03/28 18:06:35 xrf Exp $
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
 * Class DetalleVentasType.
 * 
 * @version $Revision: 1.1 $ $Date: 2014/03/28 18:06:35 $
 */
public class DetalleVentasType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _tpIdCliente
     */
    private java.lang.String _tpIdCliente;

    /**
     * Field _idCliente
     */
    private java.lang.String _idCliente;

    /**
     * Field _tipoComprobante
     */
    private int _tipoComprobante;

    /**
     * keeps track of state for field: _tipoComprobante
     */
    private boolean _has_tipoComprobante;

    /**
     * Field _numeroComprobantes
     */
    private int _numeroComprobantes;

    /**
     * keeps track of state for field: _numeroComprobantes
     */
    private boolean _has_numeroComprobantes;

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
     * Field _montoIva
     */
    private java.math.BigDecimal _montoIva;

    /**
     * Field _valorRetIva
     */
    private java.math.BigDecimal _valorRetIva;

    /**
     * Field _valorRetRenta
     */
    private java.math.BigDecimal _valorRetRenta;


      //----------------/
     //- Constructors -/
    //----------------/

    public DetalleVentasType() 
     {
        super();
    } //-- com.spirit.sri.at.DetalleVentasType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method deleteNumeroComprobantes
     * 
     */
    public void deleteNumeroComprobantes()
    {
        this._has_numeroComprobantes= false;
    } //-- void deleteNumeroComprobantes() 

    /**
     * Method deleteTipoComprobante
     * 
     */
    public void deleteTipoComprobante()
    {
        this._has_tipoComprobante= false;
    } //-- void deleteTipoComprobante() 

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
     * Returns the value of field 'idCliente'.
     * 
     * @return String
     * @return the value of field 'idCliente'.
     */
    public java.lang.String getIdCliente()
    {
        return this._idCliente;
    } //-- java.lang.String getIdCliente() 

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
     * Returns the value of field 'numeroComprobantes'.
     * 
     * @return int
     * @return the value of field 'numeroComprobantes'.
     */
    public int getNumeroComprobantes()
    {
        return this._numeroComprobantes;
    } //-- int getNumeroComprobantes() 

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
     * Returns the value of field 'tpIdCliente'.
     * 
     * @return String
     * @return the value of field 'tpIdCliente'.
     */
    public java.lang.String getTpIdCliente()
    {
        return this._tpIdCliente;
    } //-- java.lang.String getTpIdCliente() 

    /**
     * Returns the value of field 'valorRetIva'.
     * 
     * @return BigDecimal
     * @return the value of field 'valorRetIva'.
     */
    public java.math.BigDecimal getValorRetIva()
    {
        return this._valorRetIva;
    } //-- java.math.BigDecimal getValorRetIva() 

    /**
     * Returns the value of field 'valorRetRenta'.
     * 
     * @return BigDecimal
     * @return the value of field 'valorRetRenta'.
     */
    public java.math.BigDecimal getValorRetRenta()
    {
        return this._valorRetRenta;
    } //-- java.math.BigDecimal getValorRetRenta() 

    /**
     * Method hasNumeroComprobantes
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasNumeroComprobantes()
    {
        return this._has_numeroComprobantes;
    } //-- boolean hasNumeroComprobantes() 

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
     * Sets the value of field 'idCliente'.
     * 
     * @param idCliente the value of field 'idCliente'.
     */
    public void setIdCliente(java.lang.String idCliente)
    {
        this._idCliente = idCliente;
    } //-- void setIdCliente(java.lang.String) 

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
     * Sets the value of field 'numeroComprobantes'.
     * 
     * @param numeroComprobantes the value of field
     * 'numeroComprobantes'.
     */
    public void setNumeroComprobantes(int numeroComprobantes)
    {
        this._numeroComprobantes = numeroComprobantes;
        this._has_numeroComprobantes = true;
    } //-- void setNumeroComprobantes(int) 

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
     * Sets the value of field 'tpIdCliente'.
     * 
     * @param tpIdCliente the value of field 'tpIdCliente'.
     */
    public void setTpIdCliente(java.lang.String tpIdCliente)
    {
        this._tpIdCliente = tpIdCliente;
    } //-- void setTpIdCliente(java.lang.String) 

    /**
     * Sets the value of field 'valorRetIva'.
     * 
     * @param valorRetIva the value of field 'valorRetIva'.
     */
    public void setValorRetIva(java.math.BigDecimal valorRetIva)
    {
        this._valorRetIva = valorRetIva;
    } //-- void setValorRetIva(java.math.BigDecimal) 

    /**
     * Sets the value of field 'valorRetRenta'.
     * 
     * @param valorRetRenta the value of field 'valorRetRenta'.
     */
    public void setValorRetRenta(java.math.BigDecimal valorRetRenta)
    {
        this._valorRetRenta = valorRetRenta;
    } //-- void setValorRetRenta(java.math.BigDecimal) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return DetalleVentasType
     */
    public static com.spirit.sri.at.DetalleVentasType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.spirit.sri.at.DetalleVentasType) Unmarshaller.unmarshal(com.spirit.sri.at.DetalleVentasType.class, reader);
    } //-- com.spirit.sri.at.DetalleVentasType unmarshal(java.io.Reader) 

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
