package compiler.syntax.nonTerminal;

import es.uned.lsi.compiler.semantic.type.TypeIF;

public class CabProcedure 
	extends NonTerminal 
{
	private String id;
	private TypeIF returnType;
	
    /**
     * Constructor for CabProcedure.
     */
    public CabProcedure ()
    {
        super (); 
    }
    
    public CabProcedure(String id, TypeIF type)
    {
    	this ();
    	this.id = id;
    	this.returnType = type;
    }

    
    public String getId()
    {
    	return this.id;
    }
    
    public String getReturnTypeName ()
    {
    	if (returnType == null)
    	{
    		return "null";
    	} else {
    		return returnType.getName();
    	}
    }

}
