package compiler.syntax.nonTerminal;

import es.uned.lsi.compiler.semantic.type.TypeIF;

public class SentFor
	extends NonTerminal 
{
	private TypeIF returnType;
	
    /**
     * Constructor for SentFor.
     */
    public SentFor ()
    {
        super (); 
    }
    
    public SentFor (TypeIF type) 
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
    	return 19 * 19 * this.getIntermediateCode().hashCode()+
    			19 * returnType.hashCode() + this.getTemp().hashCode();
    }
    
    public boolean equals (Object o)
    {
    	if (!(o instanceof SentFor)) return false;
    	else {
    		SentFor e = (SentFor) o;
    		return e.getIntermediateCode().equals(this.getIntermediateCode()) &&
    				e.getReturnType().equals(this.returnType) &&
    				e.getTemp().equals(this.getTemp());
    	}
    }
    
    public String toString ()
    {
    	String s = returnType.toString ();
    	s += getIntermediateCode ();
    	return s;
    }

}
