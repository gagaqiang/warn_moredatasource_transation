/**
 * NotifyTodoGetContext.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.utils.aowsdl;

public class NotifyTodoGetContext  implements java.io.Serializable {
    private java.lang.String otherCond;

    private int pageNo;

    private int rowSize;

    private java.lang.String targets;

    private int type;

    public NotifyTodoGetContext() {
    }

    public NotifyTodoGetContext(
           java.lang.String otherCond,
           int pageNo,
           int rowSize,
           java.lang.String targets,
           int type) {
           this.otherCond = otherCond;
           this.pageNo = pageNo;
           this.rowSize = rowSize;
           this.targets = targets;
           this.type = type;
    }


    /**
     * Gets the otherCond value for this NotifyTodoGetContext.
     * 
     * @return otherCond
     */
    public java.lang.String getOtherCond() {
        return otherCond;
    }


    /**
     * Sets the otherCond value for this NotifyTodoGetContext.
     * 
     * @param otherCond
     */
    public void setOtherCond(java.lang.String otherCond) {
        this.otherCond = otherCond;
    }


    /**
     * Gets the pageNo value for this NotifyTodoGetContext.
     * 
     * @return pageNo
     */
    public int getPageNo() {
        return pageNo;
    }


    /**
     * Sets the pageNo value for this NotifyTodoGetContext.
     * 
     * @param pageNo
     */
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }


    /**
     * Gets the rowSize value for this NotifyTodoGetContext.
     * 
     * @return rowSize
     */
    public int getRowSize() {
        return rowSize;
    }


    /**
     * Sets the rowSize value for this NotifyTodoGetContext.
     * 
     * @param rowSize
     */
    public void setRowSize(int rowSize) {
        this.rowSize = rowSize;
    }


    /**
     * Gets the targets value for this NotifyTodoGetContext.
     * 
     * @return targets
     */
    public java.lang.String getTargets() {
        return targets;
    }


    /**
     * Sets the targets value for this NotifyTodoGetContext.
     * 
     * @param targets
     */
    public void setTargets(java.lang.String targets) {
        this.targets = targets;
    }


    /**
     * Gets the type value for this NotifyTodoGetContext.
     * 
     * @return type
     */
    public int getType() {
        return type;
    }


    /**
     * Sets the type value for this NotifyTodoGetContext.
     * 
     * @param type
     */
    public void setType(int type) {
        this.type = type;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof NotifyTodoGetContext)) return false;
        NotifyTodoGetContext other = (NotifyTodoGetContext) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.otherCond==null && other.getOtherCond()==null) || 
             (this.otherCond!=null &&
              this.otherCond.equals(other.getOtherCond()))) &&
            this.pageNo == other.getPageNo() &&
            this.rowSize == other.getRowSize() &&
            ((this.targets==null && other.getTargets()==null) || 
             (this.targets!=null &&
              this.targets.equals(other.getTargets()))) &&
            this.type == other.getType();
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getOtherCond() != null) {
            _hashCode += getOtherCond().hashCode();
        }
        _hashCode += getPageNo();
        _hashCode += getRowSize();
        if (getTargets() != null) {
            _hashCode += getTargets().hashCode();
        }
        _hashCode += getType();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(NotifyTodoGetContext.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.notify.sys.kmss.landray.com/", "notifyTodoGetContext"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("otherCond");
        elemField.setXmlName(new javax.xml.namespace.QName("", "otherCond"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pageNo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pageNo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rowSize");
        elemField.setXmlName(new javax.xml.namespace.QName("", "rowSize"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("targets");
        elemField.setXmlName(new javax.xml.namespace.QName("", "targets"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("type");
        elemField.setXmlName(new javax.xml.namespace.QName("", "type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
