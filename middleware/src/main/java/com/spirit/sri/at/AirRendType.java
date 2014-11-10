/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.1</a>, using an XML
 * Schema.
 * $Id: AirRendType.java,v 1.1 2014/03/28 18:06:35 xrf Exp $
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
 * Class AirRendType.
 * 
 * @version $Revision: 1.1 $ $Date: 2014/03/28 18:06:35 $
 */
public class AirRendType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _detalleAirRenList
     */
    private java.util.ArrayList _detalleAirRenList;


      //----------------/
     //- Constructors -/
    //----------------/

    public AirRendType() 
     {
        super();
        _detalleAirRenList = new ArrayList();
    } //-- com.spirit.sri.at.AirRendType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addDetalleAirRen
     * 
     * 
     * 
     * @param vDetalleAirRen
     */
    public void addDetalleAirRen(com.spirit.sri.at.DetalleAirRen vDetalleAirRen)
        throws java.lang.IndexOutOfBoundsException
    {
        _detalleAirRenList.add(vDetalleAirRen);
    } //-- void addDetalleAirRen(com.spirit.sri.at.DetalleAirRen) 

    /**
     * Method addDetalleAirRen
     * 
     * 
     * 
     * @param index
     * @param vDetalleAirRen
     */
    public void addDetalleAirRen(int index, com.spirit.sri.at.DetalleAirRen vDetalleAirRen)
        throws java.lang.IndexOutOfBoundsException
    {
        _detalleAirRenList.add(index, vDetalleAirRen);
    } //-- void addDetalleAirRen(int, com.spirit.sri.at.DetalleAirRen) 

    /**
     * Method clearDetalleAirRen
     * 
     */
    public void clearDetalleAirRen()
    {
        _detalleAirRenList.clear();
    } //-- void clearDetalleAirRen() 

    /**
     * Method enumerateDetalleAirRen
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateDetalleAirRen()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_detalleAirRenList.iterator());
    } //-- java.util.Enumeration enumerateDetalleAirRen() 

    /**
     * Method getDetalleAirRen
     * 
     * 
     * 
     * @param index
     * @return DetalleAirRen
     */
    public com.spirit.sri.at.DetalleAirRen getDetalleAirRen(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _detalleAirRenList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.spirit.sri.at.DetalleAirRen) _detalleAirRenList.get(index);
    } //-- com.spirit.sri.at.DetalleAirRen getDetalleAirRen(int) 

    /**
     * Method getDetalleAirRen
     * 
     * 
     * 
     * @return DetalleAirRen
     */
    public com.spirit.sri.at.DetalleAirRen[] getDetalleAirRen()
    {
        int size = _detalleAirRenList.size();
        com.spirit.sri.at.DetalleAirRen[] mArray = new com.spirit.sri.at.DetalleAirRen[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.spirit.sri.at.DetalleAirRen) _detalleAirRenList.get(index);
        }
        return mArray;
    } //-- com.spirit.sri.at.DetalleAirRen[] getDetalleAirRen() 

    /**
     * Method getDetalleAirRenCount
     * 
     * 
     * 
     * @return int
     */
    public int getDetalleAirRenCount()
    {
        return _detalleAirRenList.size();
    } //-- int getDetalleAirRenCount() 

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
     * Method removeDetalleAirRen
     * 
     * 
     * 
     * @param vDetalleAirRen
     * @return boolean
     */
    public boolean removeDetalleAirRen(com.spirit.sri.at.DetalleAirRen vDetalleAirRen)
    {
        boolean removed = _detalleAirRenList.remove(vDetalleAirRen);
        return removed;
    } //-- boolean removeDetalleAirRen(com.spirit.sri.at.DetalleAirRen) 

    /**
     * Method setDetalleAirRen
     * 
     * 
     * 
     * @param index
     * @param vDetalleAirRen
     */
    public void setDetalleAirRen(int index, com.spirit.sri.at.DetalleAirRen vDetalleAirRen)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _detalleAirRenList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _detalleAirRenList.set(index, vDetalleAirRen);
    } //-- void setDetalleAirRen(int, com.spirit.sri.at.DetalleAirRen) 

    /**
     * Method setDetalleAirRen
     * 
     * 
     * 
     * @param detalleAirRenArray
     */
    public void setDetalleAirRen(com.spirit.sri.at.DetalleAirRen[] detalleAirRenArray)
    {
        //-- copy array
        _detalleAirRenList.clear();
        for (int i = 0; i < detalleAirRenArray.length; i++) {
            _detalleAirRenList.add(detalleAirRenArray[i]);
        }
    } //-- void setDetalleAirRen(com.spirit.sri.at.DetalleAirRen) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return AirRendType
     */
    public static com.spirit.sri.at.AirRendType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.spirit.sri.at.AirRendType) Unmarshaller.unmarshal(com.spirit.sri.at.AirRendType.class, reader);
    } //-- com.spirit.sri.at.AirRendType unmarshal(java.io.Reader) 

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
