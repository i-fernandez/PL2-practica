package compiler.syntax.nonTerminal;

import es.uned.lsi.compiler.semantic.type.TypeIF;

public class Cuerpo 
	extends NonTerminal 
{
	private String endId;
	private TypeIF returnType;
	
    /**
     * Constructor for Cuerpo.
     */
    public Cuerpo ()
    {
        super ();
        this.returnType = null;
    }
    
    public Cuerpo (String endId, TypeIF type)
    {
    	this ();
    	this.endId = endId;
    	returnType = type;
    }
    
    public String getEndId()
    {
    	return this.endId;
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
    
    public long hashcode ()
    {
    	return 71 * 71 * this.getIntermediateCode().hashCode()+
    			71 * endId.hashCode() + this.getTemp().hashCode();
    }
    
    public boolean equals (Object o)
    {
    	if (!(o instanceof Cuerpo)) return false;
    	else {
    		Cuerpo e = (Cuerpo) o;
    		return e.getIntermediateCode().equals(getIntermediateCode()) &&
    				e.getTemp().equals(getTemp()) &&
    				e.getEndId().equals(endId) ;
    	}
    }
    
    public String toString ()
    {
    	String s = endId;
    	s += getIntermediateCode ();
    	return s;
    }

}
