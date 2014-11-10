/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.1</a>, using an XML
 * Schema.
 * $Id: DetalleFideicomisosType.java,v 1.1 2014/03/28 18:06:35 xrf Exp $
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
 * Class DetalleFideicomisosType.
 * 
 * @version $Revision: 1.1 $ $Date: 2014/03/28 18:06:35 $
 */
public class DetalleFideicomisosType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _tipoBeneficiario
     */
    private java.lang.String _tipoBeneficiario;

    /**
     * Field _idBeneficiario
     */
    private java.lang.String _idBeneficiario;

    /**
     * Field _rucFideicomiso
     */
    private java.lang.String _rucFideicomiso;

    /**
     * Field _fValor
     */
    private com.spirit.sri.at.FValor _fValor;


      //----------------/
     //- Constructors -/
    //----------------/

    public DetalleFideicomisosType() 
     {
        super();
    } //-- com.spirit.sri.at.DetalleFideicomisosType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'fValor'.
     * 
     * @return FValor
     * @return the value of field 'fValor'.
     */
    public com.spirit.sri.at.FValor getFValor()
    {
        return this._fValor;
    } //-- com.spirit.sri.at.FValor getFValor() 

    /**
     * Returns the value of field 'idBeneficiario'.
     * 
     * @return String
     * @return the value of field 'idBeneficiario'.
     */
    public java.lang.String getIdBeneficiario()
    {
        return this._idBeneficiario;
    } //-- java.lang.String getIdBeneficiario() 

    /**
     * Returns the value of field 'rucFideicomiso'.
     * 
     * @return String
     * @return the value of field 'rucFideicomiso'.
     */
    public java.lang.String getRucFideicomiso()
    {
        return this._rucFideicomiso;
    } //-- java.lang.String getRucFideicomiso() 

    /**
     * Returns the value of field 'tipoBeneficiario'.
     * 
     * @return String
     * @return the value of field 'tipoBeneficiario'.
     */
    public java.lang.String getTipoBeneficiario()
    {
        return this._tipoBeneficiario;
    } //-- java.lang.String getTipoBeneficiario() 

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
     * Sets the value of field 'fValor'.
     * 
     * @param fValor the value of field 'fValor'.
     */
    public void setFValor(com.spirit.sri.at.FValor fValor)
    {
        this._fValor = fValor;
    } //-- void setFValor(com.spirit.sri.at.FValor) 

    /**
     * Sets the value of field 'idBeneficiario'.
     * 
     * @param idBeneficiario the value of field 'idBeneficiario'.
     */
    public void setIdBeneficiario(java.lang.String idBeneficiario)
    {
        this._idBeneficiario = idBeneficiario;
    } //-- void setIdBeneficiario(java.lang.String) 

    /**
     * Sets the value of field 'rucFideicomiso'.
     * 
     * @param rucFideicomiso the value of field 'rucFideicomiso'.
     */
    public void setRucFideicomiso(java.lang.String rucFideicomiso)
    {
        this._rucFideicomiso = rucFideicomiso;
    } //-- void setRucFideicomiso(java.lang.String) 

    /**
     * Sets the value of field 'tipoBeneficiario'.
     * 
     * @param tipoBeneficiario the value of field 'tipoBeneficiario'
     */
    public void setTipoBeneficiario(java.lang.String tipoBeneficiario)
    {
        this._tipoBeneficiario = tipoBeneficiario;
    } //-- void setTipoBeneficiario(java.lang.String) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return DetalleFideicomisosType
     */
    public static com.spirit.sri.at.DetalleFideicomisosType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.spirit.sri.at.DetalleFideicomisosType) Unmarshaller.unmarshal(com.spirit.sri.at.DetalleFideicomisosType.class, reader);
    } //-- com.spirit.sri.at.DetalleFideicomisosType unmarshal(java.io.Reader) 

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
