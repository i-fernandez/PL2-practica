package compiler.syntax.nonTerminal;

import es.uned.lsi.compiler.semantic.type.TypeIF;
import es.uned.lsi.compiler.semantic.symbol.SymbolIF;

public class Variables 
	extends NonTerminal 
{
	private TypeIF type;
	private SymbolIF symbol;
	private String name;
	
    /**
     * Constructor for Variables.
     */
    public Variables ()
    {
        super (); 
    }
    
    public Variables (TypeIF type)
    {
    	this();
    	this.type = type;
    }
    
    public TypeIF getType()
    {
    	return this.type;
    }
    
    public void setSymbol (SymbolIF s)
    {
    	symbol = s;
    }
    
    public SymbolIF getSymbol ()
    {
    	return this.symbol;
    }
    
    public void setName (String name)
    {
    	this.name = name;
    }
    
    public String getName ()
    {
    	return name;
    }
    
    public long hashcode ()
    {
    	return 47 * 47 * this.getIntermediateCode().hashCode()+
    			47 * type.hashCode() + this.getTemp().hashCode();
    }
    
    public boolean equals (Object o)
    {
    	if (!(o instanceof Variables)) return false;
    	else {
    		Variables e = (Variables) o;
    		return e.getIntermediateCode().equals(this.getIntermediateCode()) &&
    				e.getType().equals(this.getType()) &&
    				e.getTemp().equals(this.getTemp()) &&
    				e.getName().equals(this.name);
    	}
    }
    
    public String toString ()
    {
    	String s = name;
    	s += type.toString();
    	s += getIntermediateCode ();
    	return s;
    }

}
