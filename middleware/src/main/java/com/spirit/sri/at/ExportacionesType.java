/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.1</a>, using an XML
 * Schema.
 * $Id: ExportacionesType.java,v 1.1 2014/03/28 18:06:35 xrf Exp $
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
 * Class ExportacionesType.
 * 
 * @version $Revision: 1.1 $ $Date: 2014/03/28 18:06:35 $
 */
public class ExportacionesType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _detalleExportacionesList
     */
    private java.util.ArrayList _detalleExportacionesList;


      //----------------/
     //- Constructors -/
    //----------------/

    public ExportacionesType() 
     {
        super();
        _detalleExportacionesList = new ArrayList();
    } //-- com.spirit.sri.at.ExportacionesType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addDetalleExportaciones
     * 
     * 
     * 
     * @param vDetalleExportaciones
     */
    public void addDetalleExportaciones(com.spirit.sri.at.DetalleExportaciones vDetalleExportaciones)
        throws java.lang.IndexOutOfBoundsException
    {
        _detalleExportacionesList.add(vDetalleExportaciones);
    } //-- void addDetalleExportaciones(com.spirit.sri.at.DetalleExportaciones) 

    /**
     * Method addDetalleExportaciones
     * 
     * 
     * 
     * @param index
     * @param vDetalleExportaciones
     */
    public void addDetalleExportaciones(int index, com.spirit.sri.at.DetalleExportaciones vDetalleExportaciones)
        throws java.lang.IndexOutOfBoundsException
    {
        _detalleExportacionesList.add(index, vDetalleExportaciones);
    } //-- void addDetalleExportaciones(int, com.spirit.sri.at.DetalleExportaciones) 

    /**
     * Method clearDetalleExportaciones
     * 
     */
    public void clearDetalleExportaciones()
    {
        _detalleExportacionesList.clear();
    } //-- void clearDetalleExportaciones() 

    /**
     * Method enumerateDetalleExportaciones
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateDetalleExportaciones()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_detalleExportacionesList.iterator());
    } //-- java.util.Enumeration enumerateDetalleExportaciones() 

    /**
     * Method getDetalleExportaciones
     * 
     * 
     * 
     * @param index
     * @return DetalleExportaciones
     */
    public com.spirit.sri.at.DetalleExportaciones getDetalleExportaciones(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _detalleExportacionesList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.spirit.sri.at.DetalleExportaciones) _detalleExportacionesList.get(index);
    } //-- com.spirit.sri.at.DetalleExportaciones getDetalleExportaciones(int) 

    /**
     * Method getDetalleExportaciones
     * 
     * 
     * 
     * @return DetalleExportaciones
     */
    public com.spirit.sri.at.DetalleExportaciones[] getDetalleExportaciones()
    {
        int size = _detalleExportacionesList.size();
        com.spirit.sri.at.DetalleExportaciones[] mArray = new com.spirit.sri.at.DetalleExportaciones[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.spirit.sri.at.DetalleExportaciones) _detalleExportacionesList.get(index);
        }
        return mArray;
    } //-- com.spirit.sri.at.DetalleExportaciones[] getDetalleExportaciones() 

    /**
     * Method getDetalleExportacionesCount
     * 
     * 
     * 
     * @return int
     */
    public int getDetalleExportacionesCount()
    {
        return _detalleExportacionesList.size();
    } //-- int getDetalleExportacionesCount() 

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
     * Method removeDetalleExportaciones
     * 
     * 
     * 
     * @param vDetalleExportaciones
     * @return boolean
     */
    public boolean removeDetalleExportaciones(com.spirit.sri.at.DetalleExportaciones vDetalleExportaciones)
    {
        boolean removed = _detalleExportacionesList.remove(vDetalleExportaciones);
        return removed;
    } //-- boolean removeDetalleExportaciones(com.spirit.sri.at.DetalleExportaciones) 

    /**
     * Method setDetalleExportaciones
     * 
     * 
     * 
     * @param index
     * @param vDetalleExportaciones
     */
    public void setDetalleExportaciones(int index, com.spirit.sri.at.DetalleExportaciones vDetalleExportaciones)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _detalleExportacionesList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _detalleExportacionesList.set(index, vDetalleExportaciones);
    } //-- void setDetalleExportaciones(int, com.spirit.sri.at.DetalleExportaciones) 

    /**
     * Method setDetalleExportaciones
     * 
     * 
     * 
     * @param detalleExportacionesArray
     */
    public void setDetalleExportaciones(com.spirit.sri.at.DetalleExportaciones[] detalleExportacionesArray)
    {
        //-- copy array
        _detalleExportacionesList.clear();
        for (int i = 0; i < detalleExportacionesArray.length; i++) {
            _detalleExportacionesList.add(detalleExportacionesArray[i]);
        }
    } //-- void setDetalleExportaciones(com.spirit.sri.at.DetalleExportaciones) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return ExportacionesType
     */
    public static com.spirit.sri.at.ExportacionesType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.spirit.sri.at.ExportacionesType) Unmarshaller.unmarshal(com.spirit.sri.at.ExportacionesType.class, reader);
    } //-- com.spirit.sri.at.ExportacionesType unmarshal(java.io.Reader) 

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
