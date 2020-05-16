package compiler.syntax.nonTerminal;

import es.uned.lsi.compiler.semantic.type.TypeIF;

public class Sentencias 
	extends NonTerminal 
{
	private TypeIF returnType;
	
    /**
     * Constructor for Sentencias.
     */
    public Sentencias ()
    {
        super ();
        returnType = null;
    }
    
    public Sentencias (TypeIF type)
    {
    	this ();
    	returnType =type;
    }
    
    public TypeIF getReturnType ()
    {
    	return returnType;
    }
    
    public long hashcode ()
    {
    	return 17 * 17 * this.getIntermediateCode().hashCode()+
    			17 * returnType.hashCode() + this.getTemp().hashCode();
    }
    
    public boolean equals (Object o)
    {
    	if (!(o instanceof Sentencias)) return false;
    	else {
    		Sentencias e = (Sentencias) o;
    		return e.getIntermediateCode().equals(this.getIntermediateCode()) &&
    				e.getReturnType().equals(this.returnType) &&
    				e.getTemp().equals(this.getTemp());
    	}
    }
    
    public String toString ()
    {
    	String s = returnType.toString();
    	s += getIntermediateCode ();
    	return s;
    }

}
