package compiler.syntax.nonTerminal;

import compiler.semantic.type.TypeSimple;
import es.uned.lsi.compiler.semantic.type.TypeIF;
import es.uned.lsi.compiler.semantic.ScopeIF;


public class ExprArit 
	extends NonTerminal 
{
	private TypeIF type;
	
    /**
     * Constructor for ExprArit.
     */
    public ExprArit ()
    {
        super ();
    }
    
    public ExprArit (ScopeIF scope)
    {
    	this();
    	this.type = new TypeSimple(scope, "ENTERO");
    }
    
    public TypeIF getType()
    {
    	return this.type;
    }
    
    public long hashcode ()
    {
    	return 61 * 61 * this.getIntermediateCode().hashCode()+
    			61 * type.hashCode() + this.getTemp().hashCode();
    }
    
    public boolean equals (Object o)
    {
    	if (!(o instanceof ExprArit)) return false;
    	else {
    		ExprArit e = (ExprArit) o;
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
