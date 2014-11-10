/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.1</a>, using an XML
 * Schema.
 * $Id: DetalleAirType.java,v 1.1 2014/03/28 18:06:46 xrf Exp $
 */

package com.spirit.sri.reoc;

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
 * Class DetalleAirType.
 * 
 * @version $Revision: 1.1 $ $Date: 2014/03/28 18:06:46 $
 */
public class DetalleAirType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _codRetAir
     */
    private java.lang.String _codRetAir;

    /**
     * Field _porcentaje
     */
    private java.math.BigDecimal _porcentaje;

    /**
     * Field _base0
     */
    private java.math.BigDecimal _base0;

    /**
     * Field _baseGrav
     */
    private java.math.BigDecimal _baseGrav;

    /**
     * Field _baseNoGrav
     */
    private java.math.BigDecimal _baseNoGrav;

    /**
     * Field _valRetAir
     */
    private java.math.BigDecimal _valRetAir;


      //----------------/
     //- Constructors -/
    //----------------/

    public DetalleAirType() 
     {
        super();
    } //-- com.spirit.sri.reoc.DetalleAirType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'base0'.
     * 
     * @return BigDecimal
     * @return the value of field 'base0'.
     */
    public java.math.BigDecimal getBase0()
    {
        return this._base0;
    } //-- java.math.BigDecimal getBase0() 

    /**
     * Returns the value of field 'baseGrav'.
     * 
     * @return BigDecimal
     * @return the value of field 'baseGrav'.
     */
    public java.math.BigDecimal getBaseGrav()
    {
        return this._baseGrav;
    } //-- java.math.BigDecimal getBaseGrav() 

    /**
     * Returns the value of field 'baseNoGrav'.
     * 
     * @return BigDecimal
     * @return the value of field 'baseNoGrav'.
     */
    public java.math.BigDecimal getBaseNoGrav()
    {
        return this._baseNoGrav;
    } //-- java.math.BigDecimal getBaseNoGrav() 

    /**
     * Returns the value of field 'codRetAir'.
     * 
     * @return String
     * @return the value of field 'codRetAir'.
     */
    public java.lang.String getCodRetAir()
    {
        return this._codRetAir;
    } //-- java.lang.String getCodRetAir() 

    /**
     * Returns the value of field 'porcentaje'.
     * 
     * @return BigDecimal
     * @return the value of field 'porcentaje'.
     */
    public java.math.BigDecimal getPorcentaje()
    {
        return this._porcentaje;
    } //-- java.math.BigDecimal getPorcentaje() 

    /**
     * Returns the value of field 'valRetAir'.
     * 
     * @return BigDecimal
     * @return the value of field 'valRetAir'.
     */
    public java.math.BigDecimal getValRetAir()
    {
        return this._valRetAir;
    } //-- java.math.BigDecimal getValRetAir() 

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
     * Sets the value of field 'base0'.
     * 
     * @param base0 the value of field 'base0'.
     */
    public void setBase0(java.math.BigDecimal base0)
    {
        this._base0 = base0;
    } //-- void setBase0(java.math.BigDecimal) 

    /**
     * Sets the value of field 'baseGrav'.
     * 
     * @param baseGrav the value of field 'baseGrav'.
     */
    public void setBaseGrav(java.math.BigDecimal baseGrav)
    {
        this._baseGrav = baseGrav;
    } //-- void setBaseGrav(java.math.BigDecimal) 

    /**
     * Sets the value of field 'baseNoGrav'.
     * 
     * @param baseNoGrav the value of field 'baseNoGrav'.
     */
    public void setBaseNoGrav(java.math.BigDecimal baseNoGrav)
    {
        this._baseNoGrav = baseNoGrav;
    } //-- void setBaseNoGrav(java.math.BigDecimal) 

    /**
     * Sets the value of field 'codRetAir'.
     * 
     * @param codRetAir the value of field 'codRetAir'.
     */
    public void setCodRetAir(java.lang.String codRetAir)
    {
        this._codRetAir = codRetAir;
    } //-- void setCodRetAir(java.lang.String) 

    /**
     * Sets the value of field 'porcentaje'.
     * 
     * @param porcentaje the value of field 'porcentaje'.
     */
    public void setPorcentaje(java.math.BigDecimal porcentaje)
    {
        this._porcentaje = porcentaje;
    } //-- void setPorcentaje(java.math.BigDecimal) 

    /**
     * Sets the value of field 'valRetAir'.
     * 
     * @param valRetAir the value of field 'valRetAir'.
     */
    public void setValRetAir(java.math.BigDecimal valRetAir)
    {
        this._valRetAir = valRetAir;
    } //-- void setValRetAir(java.math.BigDecimal) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return DetalleAirType
     */
    public static com.spirit.sri.reoc.DetalleAirType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.spirit.sri.reoc.DetalleAirType) Unmarshaller.unmarshal(com.spirit.sri.reoc.DetalleAirType.class, reader);
    } //-- com.spirit.sri.reoc.DetalleAirType unmarshal(java.io.Reader) 

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
