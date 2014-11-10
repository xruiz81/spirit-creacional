/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.1</a>, using an XML
 * Schema.
 * $Id: VentasType.java,v 1.1 2014/03/28 18:06:35 xrf Exp $
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
 * Class VentasType.
 * 
 * @version $Revision: 1.1 $ $Date: 2014/03/28 18:06:35 $
 */
public class VentasType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _detalleVentasList
     */
    private java.util.ArrayList _detalleVentasList;


      //----------------/
     //- Constructors -/
    //----------------/

    public VentasType() 
     {
        super();
        _detalleVentasList = new ArrayList();
    } //-- com.spirit.sri.at.VentasType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addDetalleVentas
     * 
     * 
     * 
     * @param vDetalleVentas
     */
    public void addDetalleVentas(com.spirit.sri.at.DetalleVentas vDetalleVentas)
        throws java.lang.IndexOutOfBoundsException
    {
        _detalleVentasList.add(vDetalleVentas);
    } //-- void addDetalleVentas(com.spirit.sri.at.DetalleVentas) 

    /**
     * Method addDetalleVentas
     * 
     * 
     * 
     * @param index
     * @param vDetalleVentas
     */
    public void addDetalleVentas(int index, com.spirit.sri.at.DetalleVentas vDetalleVentas)
        throws java.lang.IndexOutOfBoundsException
    {
        _detalleVentasList.add(index, vDetalleVentas);
    } //-- void addDetalleVentas(int, com.spirit.sri.at.DetalleVentas) 

    /**
     * Method clearDetalleVentas
     * 
     */
    public void clearDetalleVentas()
    {
        _detalleVentasList.clear();
    } //-- void clearDetalleVentas() 

    /**
     * Method enumerateDetalleVentas
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateDetalleVentas()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_detalleVentasList.iterator());
    } //-- java.util.Enumeration enumerateDetalleVentas() 

    /**
     * Method getDetalleVentas
     * 
     * 
     * 
     * @param index
     * @return DetalleVentas
     */
    public com.spirit.sri.at.DetalleVentas getDetalleVentas(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _detalleVentasList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.spirit.sri.at.DetalleVentas) _detalleVentasList.get(index);
    } //-- com.spirit.sri.at.DetalleVentas getDetalleVentas(int) 

    /**
     * Method getDetalleVentas
     * 
     * 
     * 
     * @return DetalleVentas
     */
    public com.spirit.sri.at.DetalleVentas[] getDetalleVentas()
    {
        int size = _detalleVentasList.size();
        com.spirit.sri.at.DetalleVentas[] mArray = new com.spirit.sri.at.DetalleVentas[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.spirit.sri.at.DetalleVentas) _detalleVentasList.get(index);
        }
        return mArray;
    } //-- com.spirit.sri.at.DetalleVentas[] getDetalleVentas() 

    /**
     * Method getDetalleVentasCount
     * 
     * 
     * 
     * @return int
     */
    public int getDetalleVentasCount()
    {
        return _detalleVentasList.size();
    } //-- int getDetalleVentasCount() 

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
     * Method removeDetalleVentas
     * 
     * 
     * 
     * @param vDetalleVentas
     * @return boolean
     */
    public boolean removeDetalleVentas(com.spirit.sri.at.DetalleVentas vDetalleVentas)
    {
        boolean removed = _detalleVentasList.remove(vDetalleVentas);
        return removed;
    } //-- boolean removeDetalleVentas(com.spirit.sri.at.DetalleVentas) 

    /**
     * Method setDetalleVentas
     * 
     * 
     * 
     * @param index
     * @param vDetalleVentas
     */
    public void setDetalleVentas(int index, com.spirit.sri.at.DetalleVentas vDetalleVentas)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _detalleVentasList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _detalleVentasList.set(index, vDetalleVentas);
    } //-- void setDetalleVentas(int, com.spirit.sri.at.DetalleVentas) 

    /**
     * Method setDetalleVentas
     * 
     * 
     * 
     * @param detalleVentasArray
     */
    public void setDetalleVentas(com.spirit.sri.at.DetalleVentas[] detalleVentasArray)
    {
        //-- copy array
        _detalleVentasList.clear();
        for (int i = 0; i < detalleVentasArray.length; i++) {
            _detalleVentasList.add(detalleVentasArray[i]);
        }
    } //-- void setDetalleVentas(com.spirit.sri.at.DetalleVentas) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return VentasType
     */
    public static com.spirit.sri.at.VentasType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.spirit.sri.at.VentasType) Unmarshaller.unmarshal(com.spirit.sri.at.VentasType.class, reader);
    } //-- com.spirit.sri.at.VentasType unmarshal(java.io.Reader) 

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
