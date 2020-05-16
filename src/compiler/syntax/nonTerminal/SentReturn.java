package compiler.syntax.nonTerminal;

import es.uned.lsi.compiler.semantic.type.TypeIF;

public class SentReturn 
	extends NonTerminal 
{
	private TypeIF returnType;
	
    /**
     * Constructor for CabModule.
     */
    public SentReturn ()
    {
        super (); 
    }
    
    public SentReturn (TypeIF type) 
    {
    	this ();
    	returnType = type;
    }
    
    public TypeIF getReturnType ()
    {
    	return returnType;
    }

}
