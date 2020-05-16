package compiler.syntax.nonTerminal;

import es.uned.lsi.compiler.semantic.type.TypeIF;

public class SentIf 
	extends NonTerminal 
{
	private TypeIF returnType;
	
    /**
     * Constructor for SentIf.
     */
    public SentIf ()
    {
        super (); 
    }
    
    public SentIf (TypeIF type) 
    {
    	this ();
    	returnType = type;
    }
    
    public TypeIF getReturnType ()
    {
    	return returnType;
    }
    
    
    /**
     * Establece el tipo de retorno
     * @param type Tipo a establecer
     * @return true si el tipo ya existente y el nuevo son iguales
     */
    public boolean setReturnType (TypeIF type)
    {
    	if (returnType == null)		// Tipo actual nulo, se asigna el nuevo
    	{
    		returnType = type;
    		return true;
    	} else if (type == null)	// Nuevo tipo nulo, no hay conflicto de tipos
    	{
    		return true;
    	} else {					// Compara tipo de ambos
    		return (returnType.getName().equals(type.getName()));
    	}
    }
    
    public long hashcode ()
    {
    	return 23 * 23 * this.getIntermediateCode().hashCode()+
    			23 * returnType.hashCode() + this.getTemp().hashCode();
    }
    
    public boolean equals (Object o)
    {
    	if (!(o instanceof SentIf)) return false;
    	else {
    		SentIf e = (SentIf) o;
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
