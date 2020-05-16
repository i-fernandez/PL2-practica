package compiler.syntax.nonTerminal;

import java.util.ArrayList;
import es.uned.lsi.compiler.semantic.type.TypeIF;


public class ProcParam 
	extends NonTerminal 
{
	private ArrayList<String> params;
	private TypeIF type;
	
    /**
     * Constructor for CabModule.
     */
    public ProcParam ()
    {
        super (); 
        params = new ArrayList<String>();
    }
    
    /**
     * Constructor with parameters
     */
    public ProcParam (ArrayList<String> ids, TypeIF type)
    {
    	this();
    	this.type = type;
    	
		for (String id : ids) {
			params.add(id);
		}
		
    }
    
    public TypeIF getType()
    {
    	return this.type;
    }
    
    public ArrayList<String> getParams()
    {
    	return this.params;
    }
    
    public String toString ()
    {
    	String out = "PARAMS: ";
    	for (String p : params) {
    		out += p;
    		out += " ";
    	}
    	out += "TYPE: ";
    	out += type.getName();
    	return out;
    }

}
