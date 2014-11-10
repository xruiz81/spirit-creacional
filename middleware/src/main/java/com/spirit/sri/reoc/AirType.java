/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.1</a>, using an XML
 * Schema.
 * $Id: AirType.java,v 1.1 2014/03/28 18:06:46 xrf Exp $
 */

package com.spirit.sri.reoc;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import java.util.Enumeration;
import java.util.Vector;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.xml.sax.ContentHandler;

/**
 * Class AirType.
 * 
 * @version $Revision: 1.1 $ $Date: 2014/03/28 18:06:46 $
 */
public class AirType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _detalleAirList
     */
    private java.util.Vector _detalleAirList;


      //----------------/
     //- Constructors -/
    //----------------/

    public AirType() 
     {
        super();
        _detalleAirList = new Vector();
    } //-- com.spirit.sri.reoc.AirType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addDetalleAir
     * 
     * 
     * 
     * @param vDetalleAir
     */
    public void addDetalleAir(com.spirit.sri.reoc.DetalleAir vDetalleAir)
        throws java.lang.IndexOutOfBoundsException
    {
        _detalleAirList.addElement(vDetalleAir);
    } //-- void addDetalleAir(com.spirit.sri.reoc.DetalleAir) 

    /**
     * Method addDetalleAir
     * 
     * 
     * 
     * @param index
     * @param vDetalleAir
     */
    public void addDetalleAir(int index, com.spirit.sri.reoc.DetalleAir vDetalleAir)
        throws java.lang.IndexOutOfBoundsException
    {
        _detalleAirList.insertElementAt(vDetalleAir, index);
    } //-- void addDetalleAir(int, com.spirit.sri.reoc.DetalleAir) 

    /**
     * Method enumerateDetalleAir
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateDetalleAir()
    {
        return _detalleAirList.elements();
    } //-- java.util.Enumeration enumerateDetalleAir() 

    /**
     * Method getDetalleAir
     * 
     * 
     * 
     * @param index
     * @return DetalleAir
     */
    public com.spirit.sri.reoc.DetalleAir getDetalleAir(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _detalleAirList.size())) {
            throw new IndexOutOfBoundsException("getDetalleAir: Index value '"+index+"' not in range [0.."+(_detalleAirList.size() - 1) + "]");
        }
        
        return (com.spirit.sri.reoc.DetalleAir) _detalleAirList.elementAt(index);
    } //-- com.spirit.sri.reoc.DetalleAir getDetalleAir(int) 

    /**
     * Method getDetalleAir
     * 
     * 
     * 
     * @return DetalleAir
     */
    public com.spirit.sri.reoc.DetalleAir[] getDetalleAir()
    {
        int size = _detalleAirList.size();
        com.spirit.sri.reoc.DetalleAir[] mArray = new com.spirit.sri.reoc.DetalleAir[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.spirit.sri.reoc.DetalleAir) _detalleAirList.elementAt(index);
        }
        return mArray;
    } //-- com.spirit.sri.reoc.DetalleAir[] getDetalleAir() 

    /**
     * Method getDetalleAirCount
     * 
     * 
     * 
     * @return int
     */
    public int getDetalleAirCount()
    {
        return _detalleAirList.size();
    } //-- int getDetalleAirCount() 

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
     * Method removeAllDetalleAir
     * 
     */
    public void removeAllDetalleAir()
    {
        _detalleAirList.removeAllElements();
    } //-- void removeAllDetalleAir() 

    /**
     * Method removeDetalleAir
     * 
     * 
     * 
     * @param index
     * @return DetalleAir
     */
    public com.spirit.sri.reoc.DetalleAir removeDetalleAir(int index)
    {
        java.lang.Object obj = _detalleAirList.elementAt(index);
        _detalleAirList.removeElementAt(index);
        return (com.spirit.sri.reoc.DetalleAir) obj;
    } //-- com.spirit.sri.reoc.DetalleAir removeDetalleAir(int) 

    /**
     * Method setDetalleAir
     * 
     * 
     * 
     * @param index
     * @param vDetalleAir
     */
    public void setDetalleAir(int index, com.spirit.sri.reoc.DetalleAir vDetalleAir)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _detalleAirList.size())) {
            throw new IndexOutOfBoundsException("setDetalleAir: Index value '"+index+"' not in range [0.." + (_detalleAirList.size() - 1) + "]");
        }
        _detalleAirList.setElementAt(vDetalleAir, index);
    } //-- void setDetalleAir(int, com.spirit.sri.reoc.DetalleAir) 

    /**
     * Method setDetalleAir
     * 
     * 
     * 
     * @param detalleAirArray
     */
    public void setDetalleAir(com.spirit.sri.reoc.DetalleAir[] detalleAirArray)
    {
        //-- copy array
        _detalleAirList.removeAllElements();
        for (int i = 0; i < detalleAirArray.length; i++) {
            _detalleAirList.addElement(detalleAirArray[i]);
        }
    } //-- void setDetalleAir(com.spirit.sri.reoc.DetalleAir) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return AirType
     */
    public static com.spirit.sri.reoc.AirType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.spirit.sri.reoc.AirType) Unmarshaller.unmarshal(com.spirit.sri.reoc.AirType.class, reader);
    } //-- com.spirit.sri.reoc.AirType unmarshal(java.io.Reader) 

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
