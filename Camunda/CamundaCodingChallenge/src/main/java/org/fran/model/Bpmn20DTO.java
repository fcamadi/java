/**********************************************************************************************/
/*                                                                                            */
/*  Camunda Coding Challenge                                                                  */
/*                                                                                            */
/*  Francisco Camacho - April 2021                                                            */
/*                                                                                            */
/**********************************************************************************************/
package org.fran.model;

public class Bpmn20DTO {

    private String id;

    private String bpmn20Xml;

    public Bpmn20DTO(String id, String bpmn20Xml) {
        this.id = id;
        this.bpmn20Xml = bpmn20Xml;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBpmn20Xml() {
        return bpmn20Xml;
    }

    public void setBpmn20Xml(String bpmn20Xml) {
        this.bpmn20Xml = bpmn20Xml;
    }
}
