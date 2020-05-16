package compiler.syntax.nonTerminal;

import es.uned.lsi.compiler.semantic.type.TypeIF;

public class SentElse 
	extends NonTerminal 
{
	private TypeIF returnType;
	
    /**
     * Constructor for SentElse.
     */
    public SentElse ()
    {
        super (); 
    }
    
    public SentElse (TypeIF type) 
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
    	return 11 * 11 * this.getIntermediateCode().hashCode()+
    			11 * returnType.hashCode() + this.getTemp().hashCode();
    }
    
    public boolean equals (Object o)
    {
    	if (!(o instanceof SentElse)) return false;
    	else {
    		SentElse e = (SentElse) o;
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
