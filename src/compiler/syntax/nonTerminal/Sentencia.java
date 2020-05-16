package compiler.syntax.nonTerminal;

import es.uned.lsi.compiler.semantic.type.TypeIF;

public class Sentencia 
	extends NonTerminal 
{
	private TypeIF returnType;
	
    /**
     * Constructor for CabModule.
     */
    public Sentencia ()
    {
        super ();
        returnType = null;
    }
    
    public Sentencia (TypeIF type) 
    {
    	this ();
    	returnType = type;
    }
    
    public TypeIF getReturnType ()
    {
    	return returnType;
    }
    
    public long hashcode ()
    {
    	return 13 * 13 * this.getIntermediateCode().hashCode()+
    			13 * returnType.hashCode() + this.getTemp().hashCode();
    }
    
    public boolean equals (Object o)
    {
    	if (!(o instanceof Sentencia)) return false;
    	else {
    		Sentencia e = (Sentencia) o;
    		return e.getIntermediateCode().equals(this.getIntermediateCode()) &&
    				e.getReturnType().equals(returnType) &&
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
