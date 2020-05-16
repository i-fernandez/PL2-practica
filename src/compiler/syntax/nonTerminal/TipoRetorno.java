package compiler.syntax.nonTerminal;

import compiler.semantic.type.TypeSimple;

public class TipoRetorno 
	extends NonTerminal 
{
	private TypeSimple type;
	
    /**
     * Constructor for TipoRetorno.
     */
    public TipoRetorno ()
    {
        super (); 
    }
    
    public TipoRetorno (TypeSimple type)
    {
    	this();
    	this.type = type;
    }
    
    public TypeSimple getType()
    {
    	return type;
    }

}
