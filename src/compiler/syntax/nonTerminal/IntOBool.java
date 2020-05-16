package compiler.syntax.nonTerminal;

import compiler.semantic.type.TypeSimple;
//import es.uned.lsi.compiler.semantic.ScopeIF;
//import es.uned.lsi.compiler.semantic.type.TypeIF;

public class IntOBool 
	extends NonTerminal 
{
	private TypeSimple type;
	
    /**
     * Constructor for IntOBool.
     */
    public IntOBool ()
    {
        super (); 
    }
    
    public IntOBool (TypeSimple type)
    {
    	this();
    	this.type = type;
    }
    
    
    public TypeSimple getType()
    {
    	return type;
    }
    
    public long hashcode ()
    {
    	return 3 * type.hashCode();
    }
    
    public boolean equals (Object o)
    {
    	if (!(o instanceof IntOBool)) return false;
    	else {
    		IntOBool e = (IntOBool) o;
    		return e.getType().equals(type);
    	}
    }
    
    public String toString ()
    {
    	return type.toString();
    }

}
