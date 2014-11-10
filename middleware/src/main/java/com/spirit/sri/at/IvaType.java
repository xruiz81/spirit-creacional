/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.1</a>, using an XML
 * Schema.
 * $Id: IvaType.java,v 1.1 2014/03/28 18:06:35 xrf Exp $
 */

package com.spirit.sri.at;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.xml.sax.ContentHandler;

/**
 * Class IvaType.
 * 
 * @version $Revision: 1.1 $ $Date: 2014/03/28 18:06:35 $
 */
public class IvaType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _numeroRuc
     */
    private java.lang.String _numeroRuc;

    /**
     * Field _razonSocial
     */
    private java.lang.String _razonSocial;

    /**
     * Field _anio
     */
    private int _anio;

    /**
     * keeps track of state for field: _anio
     */
    private boolean _has_anio;

    /**
     * Field _mes
     */
    private java.lang.String _mes;

    /**
     * Field _compras
     */
    private com.spirit.sri.at.Compras _compras;

    /**
     * Field _ventas
     */
    private com.spirit.sri.at.Ventas _ventas;

    /**
     * Field _exportaciones
     */
    private com.spirit.sri.at.Exportaciones _exportaciones;

    /**
     * Field _recap
     */
    private com.spirit.sri.at.Recap _recap;

    /**
     * Field _fideicomisos
     */
    private com.spirit.sri.at.Fideicomisos _fideicomisos;

    /**
     * Field _anulados
     */
    private com.spirit.sri.at.Anulados _anulados;

    /**
     * Field _rendFinancieros
     */
    private com.spirit.sri.at.RendFinancieros _rendFinancieros;


      //----------------/
     //- Constructors -/
    //----------------/

    public IvaType() 
     {
        super();
    } //-- com.spirit.sri.at.IvaType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method deleteAnio
     * 
     */
    public void deleteAnio()
    {
        this._has_anio= false;
    } //-- void deleteAnio() 

    /**
     * Returns the value of field 'anio'.
     * 
     * @return int
     * @return the value of field 'anio'.
     */
    public int getAnio()
    {
        return this._anio;
    } //-- int getAnio() 

    /**
     * Returns the value of field 'anulados'.
     * 
     * @return Anulados
     * @return the value of field 'anulados'.
     */
    public com.spirit.sri.at.Anulados getAnulados()
    {
        return this._anulados;
    } //-- com.spirit.sri.at.Anulados getAnulados() 

    /**
     * Returns the value of field 'compras'.
     * 
     * @return Compras
     * @return the value of field 'compras'.
     */
    public com.spirit.sri.at.Compras getCompras()
    {
        return this._compras;
    } //-- com.spirit.sri.at.Compras getCompras() 

    /**
     * Returns the value of field 'exportaciones'.
     * 
     * @return Exportaciones
     * @return the value of field 'exportaciones'.
     */
    public com.spirit.sri.at.Exportaciones getExportaciones()
    {
        return this._exportaciones;
    } //-- com.spirit.sri.at.Exportaciones getExportaciones() 

    /**
     * Returns the value of field 'fideicomisos'.
     * 
     * @return Fideicomisos
     * @return the value of field 'fideicomisos'.
     */
    public com.spirit.sri.at.Fideicomisos getFideicomisos()
    {
        return this._fideicomisos;
    } //-- com.spirit.sri.at.Fideicomisos getFideicomisos() 

    /**
     * Returns the value of field 'mes'.
     * 
     * @return String
     * @return the value of field 'mes'.
     */
    public java.lang.String getMes()
    {
        return this._mes;
    } //-- java.lang.String getMes() 

    /**
     * Returns the value of field 'numeroRuc'.
     * 
     * @return String
     * @return the value of field 'numeroRuc'.
     */
    public java.lang.String getNumeroRuc()
    {
        return this._numeroRuc;
    } //-- java.lang.String getNumeroRuc() 

    /**
     * Returns the value of field 'razonSocial'.
     * 
     * @return String
     * @return the value of field 'razonSocial'.
     */
    public java.lang.String getRazonSocial()
    {
        return this._razonSocial;
    } //-- java.lang.String getRazonSocial() 

    /**
     * Returns the value of field 'recap'.
     * 
     * @return Recap
     * @return the value of field 'recap'.
     */
    public com.spirit.sri.at.Recap getRecap()
    {
        return this._recap;
    } //-- com.spirit.sri.at.Recap getRecap() 

    /**
     * Returns the value of field 'rendFinancieros'.
     * 
     * @return RendFinancieros
     * @return the value of field 'rendFinancieros'.
     */
    public com.spirit.sri.at.RendFinancieros getRendFinancieros()
    {
        return this._rendFinancieros;
    } //-- com.spirit.sri.at.RendFinancieros getRendFinancieros() 

    /**
     * Returns the value of field 'ventas'.
     * 
     * @return Ventas
     * @return the value of field 'ventas'.
     */
    public com.spirit.sri.at.Ventas getVentas()
    {
        return this._ventas;
    } //-- com.spirit.sri.at.Ventas getVentas() 

    /**
     * Method hasAnio
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasAnio()
    {
        return this._has_anio;
    } //-- boolean hasAnio() 

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
     * Sets the value of field 'anio'.
     * 
     * @param anio the value of field 'anio'.
     */
    public void setAnio(int anio)
    {
        this._anio = anio;
        this._has_anio = true;
    } //-- void setAnio(int) 

    /**
     * Sets the value of field 'anulados'.
     * 
     * @param anulados the value of field 'anulados'.
     */
    public void setAnulados(com.spirit.sri.at.Anulados anulados)
    {
        this._anulados = anulados;
    } //-- void setAnulados(com.spirit.sri.at.Anulados) 

    /**
     * Sets the value of field 'compras'.
     * 
     * @param compras the value of field 'compras'.
     */
    public void setCompras(com.spirit.sri.at.Compras compras)
    {
        this._compras = compras;
    } //-- void setCompras(com.spirit.sri.at.Compras) 

    /**
     * Sets the value of field 'exportaciones'.
     * 
     * @param exportaciones the value of field 'exportaciones'.
     */
    public void setExportaciones(com.spirit.sri.at.Exportaciones exportaciones)
    {
        this._exportaciones = exportaciones;
    } //-- void setExportaciones(com.spirit.sri.at.Exportaciones) 

    /**
     * Sets the value of field 'fideicomisos'.
     * 
     * @param fideicomisos the value of field 'fideicomisos'.
     */
    public void setFideicomisos(com.spirit.sri.at.Fideicomisos fideicomisos)
    {
        this._fideicomisos = fideicomisos;
    } //-- void setFideicomisos(com.spirit.sri.at.Fideicomisos) 

    /**
     * Sets the value of field 'mes'.
     * 
     * @param mes the value of field 'mes'.
     */
    public void setMes(java.lang.String mes)
    {
        this._mes = mes;
    } //-- void setMes(java.lang.String) 

    /**
     * Sets the value of field 'numeroRuc'.
     * 
     * @param numeroRuc the value of field 'numeroRuc'.
     */
    public void setNumeroRuc(java.lang.String numeroRuc)
    {
        this._numeroRuc = numeroRuc;
    } //-- void setNumeroRuc(java.lang.String) 

    /**
     * Sets the value of field 'razonSocial'.
     * 
     * @param razonSocial the value of field 'razonSocial'.
     */
    public void setRazonSocial(java.lang.String razonSocial)
    {
        this._razonSocial = razonSocial;
    } //-- void setRazonSocial(java.lang.String) 

    /**
     * Sets the value of field 'recap'.
     * 
     * @param recap the value of field 'recap'.
     */
    public void setRecap(com.spirit.sri.at.Recap recap)
    {
        this._recap = recap;
    } //-- void setRecap(com.spirit.sri.at.Recap) 

    /**
     * Sets the value of field 'rendFinancieros'.
     * 
     * @param rendFinancieros the value of field 'rendFinancieros'.
     */
    public void setRendFinancieros(com.spirit.sri.at.RendFinancieros rendFinancieros)
    {
        this._rendFinancieros = rendFinancieros;
    } //-- void setRendFinancieros(com.spirit.sri.at.RendFinancieros) 

    /**
     * Sets the value of field 'ventas'.
     * 
     * @param ventas the value of field 'ventas'.
     */
    public void setVentas(com.spirit.sri.at.Ventas ventas)
    {
        this._ventas = ventas;
    } //-- void setVentas(com.spirit.sri.at.Ventas) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return IvaType
     */
    public static com.spirit.sri.at.IvaType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.spirit.sri.at.IvaType) Unmarshaller.unmarshal(com.spirit.sri.at.IvaType.class, reader);
    } //-- com.spirit.sri.at.IvaType unmarshal(java.io.Reader) 

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
