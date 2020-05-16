package compiler.syntax.nonTerminal;

import es.uned.lsi.compiler.semantic.type.TypeIF;

public class TipoVar 
	extends NonTerminal 
{
	private TypeIF type;
	
    /**
     * Constructor for TipoVar.
     */
    public TipoVar ()
    {
        super (); 
    }
    
    public TipoVar (TypeIF type)
    {
    	this();
    	this.type = type;
    }
    

    public TypeIF getType()
    {
    	return this.type;
    }
    
    public long hashcode ()
    {
    	return 41 * this.type.hashCode();
    }
    
    public boolean equals (Object o)
    {
    	if (!(o instanceof TipoVar)) return false;
    	else {
    		TipoVar e = (TipoVar) o;
    		return e.getType().equals(this.type);
    	}
    }
    
    public String toString ()
    {
    	return type.toString();
    }

}
