/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.1</a>, using an XML
 * Schema.
 * $Id: DetRet.java,v 1.1 2014/03/28 18:06:35 xrf Exp $
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
 * Class DetRet.
 * 
 * @version $Revision: 1.1 $ $Date: 2014/03/28 18:06:35 $
 */
public class DetRet implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _estabRetencion
     */
    private java.lang.String _estabRetencion;

    /**
     * Field _ptoEmiRetencion
     */
    private java.lang.String _ptoEmiRetencion;

    /**
     * Field _secRetencion
     */
    private int _secRetencion;

    /**
     * keeps track of state for field: _secRetencion
     */
    private boolean _has_secRetencion;

    /**
     * Field _autRetencion
     */
    private java.lang.String _autRetencion;

    /**
     * Field _fechaEmiRet
     */
    private java.lang.String _fechaEmiRet;

    /**
     * Field _airRend
     */
    private com.spirit.sri.at.AirRend _airRend;


      //----------------/
     //- Constructors -/
    //----------------/

    public DetRet() 
     {
        super();
    } //-- com.spirit.sri.at.DetRet()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method deleteSecRetencion
     * 
     */
    public void deleteSecRetencion()
    {
        this._has_secRetencion= false;
    } //-- void deleteSecRetencion() 

    /**
     * Returns the value of field 'airRend'.
     * 
     * @return AirRend
     * @return the value of field 'airRend'.
     */
    public com.spirit.sri.at.AirRend getAirRend()
    {
        return this._airRend;
    } //-- com.spirit.sri.at.AirRend getAirRend() 

    /**
     * Returns the value of field 'autRetencion'.
     * 
     * @return String
     * @return the value of field 'autRetencion'.
     */
    public java.lang.String getAutRetencion()
    {
        return this._autRetencion;
    } //-- java.lang.String getAutRetencion() 

    /**
     * Returns the value of field 'estabRetencion'.
     * 
     * @return String
     * @return the value of field 'estabRetencion'.
     */
    public java.lang.String getEstabRetencion()
    {
        return this._estabRetencion;
    } //-- java.lang.String getEstabRetencion() 

    /**
     * Returns the value of field 'fechaEmiRet'.
     * 
     * @return String
     * @return the value of field 'fechaEmiRet'.
     */
    public java.lang.String getFechaEmiRet()
    {
        return this._fechaEmiRet;
    } //-- java.lang.String getFechaEmiRet() 

    /**
     * Returns the value of field 'ptoEmiRetencion'.
     * 
     * @return String
     * @return the value of field 'ptoEmiRetencion'.
     */
    public java.lang.String getPtoEmiRetencion()
    {
        return this._ptoEmiRetencion;
    } //-- java.lang.String getPtoEmiRetencion() 

    /**
     * Returns the value of field 'secRetencion'.
     * 
     * @return int
     * @return the value of field 'secRetencion'.
     */
    public int getSecRetencion()
    {
        return this._secRetencion;
    } //-- int getSecRetencion() 

    /**
     * Method hasSecRetencion
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasSecRetencion()
    {
        return this._has_secRetencion;
    } //-- boolean hasSecRetencion() 

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
     * Sets the value of field 'airRend'.
     * 
     * @param airRend the value of field 'airRend'.
     */
    public void setAirRend(com.spirit.sri.at.AirRend airRend)
    {
        this._airRend = airRend;
    } //-- void setAirRend(com.spirit.sri.at.AirRend) 

    /**
     * Sets the value of field 'autRetencion'.
     * 
     * @param autRetencion the value of field 'autRetencion'.
     */
    public void setAutRetencion(java.lang.String autRetencion)
    {
        this._autRetencion = autRetencion;
    } //-- void setAutRetencion(java.lang.String) 

    /**
     * Sets the value of field 'estabRetencion'.
     * 
     * @param estabRetencion the value of field 'estabRetencion'.
     */
    public void setEstabRetencion(java.lang.String estabRetencion)
    {
        this._estabRetencion = estabRetencion;
    } //-- void setEstabRetencion(java.lang.String) 

    /**
     * Sets the value of field 'fechaEmiRet'.
     * 
     * @param fechaEmiRet the value of field 'fechaEmiRet'.
     */
    public void setFechaEmiRet(java.lang.String fechaEmiRet)
    {
        this._fechaEmiRet = fechaEmiRet;
    } //-- void setFechaEmiRet(java.lang.String) 

    /**
     * Sets the value of field 'ptoEmiRetencion'.
     * 
     * @param ptoEmiRetencion the value of field 'ptoEmiRetencion'.
     */
    public void setPtoEmiRetencion(java.lang.String ptoEmiRetencion)
    {
        this._ptoEmiRetencion = ptoEmiRetencion;
    } //-- void setPtoEmiRetencion(java.lang.String) 

    /**
     * Sets the value of field 'secRetencion'.
     * 
     * @param secRetencion the value of field 'secRetencion'.
     */
    public void setSecRetencion(int secRetencion)
    {
        this._secRetencion = secRetencion;
        this._has_secRetencion = true;
    } //-- void setSecRetencion(int) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return DetRet
     */
    public static com.spirit.sri.at.DetRet unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.spirit.sri.at.DetRet) Unmarshaller.unmarshal(com.spirit.sri.at.DetRet.class, reader);
    } //-- com.spirit.sri.at.DetRet unmarshal(java.io.Reader) 

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
