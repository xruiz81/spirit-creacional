/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.1</a>, using an XML
 * Schema.
 * $Id: TipoClienteType.java,v 1.1 2014/03/28 18:06:46 xrf Exp $
 */

package com.spirit.sri.at.types;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class TipoClienteType.
 * 
 * @version $Revision: 1.1 $ $Date: 2014/03/28 18:06:46 $
 */
public class TipoClienteType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * The R type
     */
    public static final int R_TYPE = 0;

    /**
     * The instance of the R type
     */
    public static final TipoClienteType R = new TipoClienteType(R_TYPE, "R");

    /**
     * The C type
     */
    public static final int C_TYPE = 1;

    /**
     * The instance of the C type
     */
    public static final TipoClienteType C = new TipoClienteType(C_TYPE, "C");

    /**
     * The P type
     */
    public static final int P_TYPE = 2;

    /**
     * The instance of the P type
     */
    public static final TipoClienteType P = new TipoClienteType(P_TYPE, "P");

    /**
     * Field _memberTable
     */
    private static java.util.Hashtable _memberTable = init();

    /**
     * Field type
     */
    private int type = -1;

    /**
     * Field stringValue
     */
    private java.lang.String stringValue = null;


      //----------------/
     //- Constructors -/
    //----------------/

    private TipoClienteType(int type, java.lang.String value) 
     {
        super();
        this.type = type;
        this.stringValue = value;
    } //-- com.spirit.sri.at.types.TipoClienteType(int, java.lang.String)


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method enumerate
     * 
     * Returns an enumeration of all possible instances of
     * TipoClienteType
     * 
     * @return Enumeration
     */
    public static java.util.Enumeration enumerate()
    {
        return _memberTable.elements();
    } //-- java.util.Enumeration enumerate() 

    /**
     * Method getType
     * 
     * Returns the type of this TipoClienteType
     * 
     * @return int
     */
    public int getType()
    {
        return this.type;
    } //-- int getType() 

    /**
     * Method init
     * 
     * 
     * 
     * @return Hashtable
     */
    private static java.util.Hashtable init()
    {
        Hashtable members = new Hashtable();
        members.put("R", R);
        members.put("C", C);
        members.put("P", P);
        return members;
    } //-- java.util.Hashtable init() 

    /**
     * Method readResolve
     * 
     *  will be called during deserialization to replace the
     * deserialized object with the correct constant instance.
     * <br/>
     * 
     * @return Object
     */
    private java.lang.Object readResolve()
    {
        return valueOf(this.stringValue);
    } //-- java.lang.Object readResolve() 

    /**
     * Method toString
     * 
     * Returns the String representation of this TipoClienteType
     * 
     * @return String
     */
    public java.lang.String toString()
    {
        return this.stringValue;
    } //-- java.lang.String toString() 

    /**
     * Method valueOf
     * 
     * Returns a new TipoClienteType based on the given String
     * value.
     * 
     * @param string
     * @return TipoClienteType
     */
    public static com.spirit.sri.at.types.TipoClienteType valueOf(java.lang.String string)
    {
        java.lang.Object obj = null;
        if (string != null) obj = _memberTable.get(string);
        if (obj == null) {
            String err = "'" + string + "' is not a valid TipoClienteType";
            throw new IllegalArgumentException(err);
        }
        return (TipoClienteType) obj;
    } //-- com.spirit.sri.at.types.TipoClienteType valueOf(java.lang.String) 

}
