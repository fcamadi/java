/**********************************************************************************************/
/*                                                                                            */
/*  Camunda Coding Challenge                                                                  */
/*                                                                                            */
/*  Francisco Camacho - April 2021                                                            */
/*                                                                                            */
/**********************************************************************************************/
package org.fran.main;

import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.Query;
import org.camunda.bpm.model.bpmn.instance.FlowNode;
import org.camunda.bpm.model.bpmn.instance.SequenceFlow;
import org.camunda.bpm.model.xml.instance.ModelElementInstance;
import org.camunda.bpm.model.xml.type.ModelElementType;
import org.fran.http.XmlFetcher;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class BPMDiagramParser {

	
    private String startNodeId;
    private String endNodeId;
    private BpmnModelInstance modelInstance;
    private String xml;
    
    
    
    public BPMDiagramParser(String xml) {
		this.xml = xml;
	}


	//recursive function
    private List<String> findPath(List<String> path) {
    	
    	//check if path contains a solution
        if (startNodeId.equalsIgnoreCase(path.get(0)) && endNodeId.equalsIgnoreCase(path.get(path.size()-1))) {
        	printPath(startNodeId,endNodeId,path);
        }
        
        // check if we have reached an "end event" node 
        // (I could not find the API method to collect all the "end event" nodes, 
        //  so I have hard coded the two existing in the given diagram)
        if ("invoiceProcessed".equalsIgnoreCase(path.get(path.size()-1))
                || "invoiceNotProcessed".equalsIgnoreCase(path.get(path.size()-1))) {
            //System.out.println("Dead end");
            return null;
        }
        
        //start by the last node in the path
        FlowNode flowNodeStart = (FlowNode)modelInstance.getModelElementById(path.get(path.size() - 1));
        Query<FlowNode> succeedingNodesQuery = flowNodeStart.getSucceedingNodes();
        List<FlowNode> succeedingFlowNodesList = succeedingNodesQuery.list();

        //to avoid infinite loops, remove already visited nodes (the ones contained in the path being built)
        List<String> succeedingNodesList = succeedingFlowNodesList
                .stream().map(flowNode -> flowNode.getId()).collect(Collectors.toList());
        succeedingNodesList.removeAll(path);

        //call the function for all the new paths
        for (String node : succeedingNodesList) {
        	path.add(node);
        	findPath(path);            
        }
        return null;
    }


    /**
     * main method
     *
     * @param startNodeId
     * @param endNodeId
     * @return
     */
    public void findPathInBpm20XmlForNodes(String startNodeId,String endNodeId) {

        this.startNodeId = startNodeId;
        this.endNodeId = endNodeId;

        try {
            InputStream stream = new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));
            modelInstance = Bpmn.readModelFromStream(stream);

            List<String> path = new ArrayList<>();
            path.add(startNodeId);
            //find path
            findPath(path);
        }
        catch (Exception e) {
            //System.out.println("Error: " + e.getLocalizedMessage());
            System.exit(-1);
        }
    }



    private void printPath(String startNodeId, String endNodeId, List<String> foundPath) {
        System.out.println(String.format("The path from %s to %s is: ",startNodeId,endNodeId));
        System.out.print("[");
        System.out.print(String.join(", ", foundPath));
        System.out.println("]");
     }


    public static void main(String[] args) {

        //read and check arguments
        if (args.length!=2) {
            System.out.println("Usage: java -jar BPMDiagramParser-1.0.jar <start node id> <end node id>");
            System.exit(-1);
        }
        String startNodeId = args[0];
        String endNodeId =  args[1];

        //fetch the XML representation
        XmlFetcher xmlFetcher = new XmlFetcher();
        String xml = xmlFetcher.getBpmn20Xml(xmlFetcher.BPM_SOURCE);

        //parse the XML into a traversable data structure.
        //find path
        BPMDiagramParser diagramParser = new BPMDiagramParser(xml);
        diagramParser.findPathInBpm20XmlForNodes(startNodeId,endNodeId);

    }

}
