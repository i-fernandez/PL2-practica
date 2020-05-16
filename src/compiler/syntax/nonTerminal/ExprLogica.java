package compiler.syntax.nonTerminal;

import compiler.semantic.type.TypeSimple;
import es.uned.lsi.compiler.semantic.type.TypeIF;
import es.uned.lsi.compiler.semantic.ScopeIF;

public class ExprLogica 
	extends NonTerminal 
{
	private TypeIF type;
	
    /**
     * Constructor for ExprLogica.
     */
    public ExprLogica ()
    {
        super (); 
    }
    
    public ExprLogica (ScopeIF scope)
    {
    	this();
    	this.type = new TypeSimple(scope, "BOOLEANO");
    }
    
    public TypeIF getType()
    {
    	return this.type;
    }
    
    public long hashcode ()
    {
    	return 59 * 59 * this.getIntermediateCode().hashCode()+
    			59 * type.hashCode() + this.getTemp().hashCode();
    }
    
    public boolean equals (Object o)
    {
    	if (!(o instanceof ExprLogica)) return false;
    	else {
    		ExprLogica e = (ExprLogica) o;
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
