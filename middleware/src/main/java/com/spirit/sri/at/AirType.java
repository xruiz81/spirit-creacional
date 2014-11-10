/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.1</a>, using an XML
 * Schema.
 * $Id: AirType.java,v 1.1 2014/03/28 18:06:35 xrf Exp $
 */

package com.spirit.sri.at;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Enumeration;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.xml.sax.ContentHandler;

/**
 * Class AirType.
 * 
 * @version $Revision: 1.1 $ $Date: 2014/03/28 18:06:35 $
 */
public class AirType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _detalleAirList
     */
    private java.util.ArrayList _detalleAirList;


      //----------------/
     //- Constructors -/
    //----------------/

    public AirType() 
     {
        super();
        _detalleAirList = new ArrayList();
    } //-- com.spirit.sri.at.AirType()


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
    public void addDetalleAir(com.spirit.sri.at.DetalleAir vDetalleAir)
        throws java.lang.IndexOutOfBoundsException
    {
        _detalleAirList.add(vDetalleAir);
    } //-- void addDetalleAir(com.spirit.sri.at.DetalleAir) 

    /**
     * Method addDetalleAir
     * 
     * 
     * 
     * @param index
     * @param vDetalleAir
     */
    public void addDetalleAir(int index, com.spirit.sri.at.DetalleAir vDetalleAir)
        throws java.lang.IndexOutOfBoundsException
    {
        _detalleAirList.add(index, vDetalleAir);
    } //-- void addDetalleAir(int, com.spirit.sri.at.DetalleAir) 

    /**
     * Method clearDetalleAir
     * 
     */
    public void clearDetalleAir()
    {
        _detalleAirList.clear();
    } //-- void clearDetalleAir() 

    /**
     * Method enumerateDetalleAir
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateDetalleAir()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_detalleAirList.iterator());
    } //-- java.util.Enumeration enumerateDetalleAir() 

    /**
     * Method getDetalleAir
     * 
     * 
     * 
     * @param index
     * @return DetalleAir
     */
    public com.spirit.sri.at.DetalleAir getDetalleAir(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _detalleAirList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.spirit.sri.at.DetalleAir) _detalleAirList.get(index);
    } //-- com.spirit.sri.at.DetalleAir getDetalleAir(int) 

    /**
     * Method getDetalleAir
     * 
     * 
     * 
     * @return DetalleAir
     */
    public com.spirit.sri.at.DetalleAir[] getDetalleAir()
    {
        int size = _detalleAirList.size();
        com.spirit.sri.at.DetalleAir[] mArray = new com.spirit.sri.at.DetalleAir[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.spirit.sri.at.DetalleAir) _detalleAirList.get(index);
        }
        return mArray;
    } //-- com.spirit.sri.at.DetalleAir[] getDetalleAir() 

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
     * Method removeDetalleAir
     * 
     * 
     * 
     * @param vDetalleAir
     * @return boolean
     */
    public boolean removeDetalleAir(com.spirit.sri.at.DetalleAir vDetalleAir)
    {
        boolean removed = _detalleAirList.remove(vDetalleAir);
        return removed;
    } //-- boolean removeDetalleAir(com.spirit.sri.at.DetalleAir) 

    /**
     * Method setDetalleAir
     * 
     * 
     * 
     * @param index
     * @param vDetalleAir
     */
    public void setDetalleAir(int index, com.spirit.sri.at.DetalleAir vDetalleAir)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _detalleAirList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _detalleAirList.set(index, vDetalleAir);
    } //-- void setDetalleAir(int, com.spirit.sri.at.DetalleAir) 

    /**
     * Method setDetalleAir
     * 
     * 
     * 
     * @param detalleAirArray
     */
    public void setDetalleAir(com.spirit.sri.at.DetalleAir[] detalleAirArray)
    {
        //-- copy array
        _detalleAirList.clear();
        for (int i = 0; i < detalleAirArray.length; i++) {
            _detalleAirList.add(detalleAirArray[i]);
        }
    } //-- void setDetalleAir(com.spirit.sri.at.DetalleAir) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return AirType
     */
    public static com.spirit.sri.at.AirType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.spirit.sri.at.AirType) Unmarshaller.unmarshal(com.spirit.sri.at.AirType.class, reader);
    } //-- com.spirit.sri.at.AirType unmarshal(java.io.Reader) 

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
