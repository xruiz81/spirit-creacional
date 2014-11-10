/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.1</a>, using an XML
 * Schema.
 * $Id: FideicomisosType.java,v 1.1 2014/03/28 18:06:36 xrf Exp $
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
 * Class FideicomisosType.
 * 
 * @version $Revision: 1.1 $ $Date: 2014/03/28 18:06:36 $
 */
public class FideicomisosType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _detalleFideicomisosList
     */
    private java.util.ArrayList _detalleFideicomisosList;


      //----------------/
     //- Constructors -/
    //----------------/

    public FideicomisosType() 
     {
        super();
        _detalleFideicomisosList = new ArrayList();
    } //-- com.spirit.sri.at.FideicomisosType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addDetalleFideicomisos
     * 
     * 
     * 
     * @param vDetalleFideicomisos
     */
    public void addDetalleFideicomisos(com.spirit.sri.at.DetalleFideicomisos vDetalleFideicomisos)
        throws java.lang.IndexOutOfBoundsException
    {
        _detalleFideicomisosList.add(vDetalleFideicomisos);
    } //-- void addDetalleFideicomisos(com.spirit.sri.at.DetalleFideicomisos) 

    /**
     * Method addDetalleFideicomisos
     * 
     * 
     * 
     * @param index
     * @param vDetalleFideicomisos
     */
    public void addDetalleFideicomisos(int index, com.spirit.sri.at.DetalleFideicomisos vDetalleFideicomisos)
        throws java.lang.IndexOutOfBoundsException
    {
        _detalleFideicomisosList.add(index, vDetalleFideicomisos);
    } //-- void addDetalleFideicomisos(int, com.spirit.sri.at.DetalleFideicomisos) 

    /**
     * Method clearDetalleFideicomisos
     * 
     */
    public void clearDetalleFideicomisos()
    {
        _detalleFideicomisosList.clear();
    } //-- void clearDetalleFideicomisos() 

    /**
     * Method enumerateDetalleFideicomisos
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateDetalleFideicomisos()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_detalleFideicomisosList.iterator());
    } //-- java.util.Enumeration enumerateDetalleFideicomisos() 

    /**
     * Method getDetalleFideicomisos
     * 
     * 
     * 
     * @param index
     * @return DetalleFideicomisos
     */
    public com.spirit.sri.at.DetalleFideicomisos getDetalleFideicomisos(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _detalleFideicomisosList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.spirit.sri.at.DetalleFideicomisos) _detalleFideicomisosList.get(index);
    } //-- com.spirit.sri.at.DetalleFideicomisos getDetalleFideicomisos(int) 

    /**
     * Method getDetalleFideicomisos
     * 
     * 
     * 
     * @return DetalleFideicomisos
     */
    public com.spirit.sri.at.DetalleFideicomisos[] getDetalleFideicomisos()
    {
        int size = _detalleFideicomisosList.size();
        com.spirit.sri.at.DetalleFideicomisos[] mArray = new com.spirit.sri.at.DetalleFideicomisos[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.spirit.sri.at.DetalleFideicomisos) _detalleFideicomisosList.get(index);
        }
        return mArray;
    } //-- com.spirit.sri.at.DetalleFideicomisos[] getDetalleFideicomisos() 

    /**
     * Method getDetalleFideicomisosCount
     * 
     * 
     * 
     * @return int
     */
    public int getDetalleFideicomisosCount()
    {
        return _detalleFideicomisosList.size();
    } //-- int getDetalleFideicomisosCount() 

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
     * Method removeDetalleFideicomisos
     * 
     * 
     * 
     * @param vDetalleFideicomisos
     * @return boolean
     */
    public boolean removeDetalleFideicomisos(com.spirit.sri.at.DetalleFideicomisos vDetalleFideicomisos)
    {
        boolean removed = _detalleFideicomisosList.remove(vDetalleFideicomisos);
        return removed;
    } //-- boolean removeDetalleFideicomisos(com.spirit.sri.at.DetalleFideicomisos) 

    /**
     * Method setDetalleFideicomisos
     * 
     * 
     * 
     * @param index
     * @param vDetalleFideicomisos
     */
    public void setDetalleFideicomisos(int index, com.spirit.sri.at.DetalleFideicomisos vDetalleFideicomisos)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _detalleFideicomisosList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _detalleFideicomisosList.set(index, vDetalleFideicomisos);
    } //-- void setDetalleFideicomisos(int, com.spirit.sri.at.DetalleFideicomisos) 

    /**
     * Method setDetalleFideicomisos
     * 
     * 
     * 
     * @param detalleFideicomisosArray
     */
    public void setDetalleFideicomisos(com.spirit.sri.at.DetalleFideicomisos[] detalleFideicomisosArray)
    {
        //-- copy array
        _detalleFideicomisosList.clear();
        for (int i = 0; i < detalleFideicomisosArray.length; i++) {
            _detalleFideicomisosList.add(detalleFideicomisosArray[i]);
        }
    } //-- void setDetalleFideicomisos(com.spirit.sri.at.DetalleFideicomisos) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return FideicomisosType
     */
    public static com.spirit.sri.at.FideicomisosType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.spirit.sri.at.FideicomisosType) Unmarshaller.unmarshal(com.spirit.sri.at.FideicomisosType.class, reader);
    } //-- com.spirit.sri.at.FideicomisosType unmarshal(java.io.Reader) 

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
