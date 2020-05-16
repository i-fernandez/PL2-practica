package compiler.syntax.nonTerminal;

import es.uned.lsi.compiler.semantic.type.TypeIF;

public class Expresion 
	extends NonTerminal 
{
	private TypeIF type;
	
    /**
     * Constructor for Expresion.
     */
    public Expresion ()
    {
        super (); 
    }
    
    public Expresion (TypeIF type)
    {
    	this();
    	this.type = type;
    }
    
    public TypeIF getType ()
    {
    	return this.type;
    }
    
    public long hashcode ()
    {
    	return 67 * 67 * this.getIntermediateCode().hashCode()+
    			67 * type.hashCode() + this.getTemp().hashCode();
    }
    
    public boolean equals (Object o)
    {
    	if (!(o instanceof Expresion)) return false;
    	else {
    		Expresion e = (Expresion) o;
    		return e.getIntermediateCode().equals(this.getIntermediateCode()) &&
    				e.getType().equals(this.getType()) &&
    				e.getTemp().equals(this.getTemp());
    	}
    }
    
    public String toString ()
    {
    	String s = type.toString();
    	s += getIntermediateCode ();
    	return s;
    }
}
