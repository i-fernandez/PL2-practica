package compiler.syntax.nonTerminal;

import java.util.ArrayList;
import es.uned.lsi.compiler.semantic.type.TypeIF;

public class ParFuncion 
	extends NonTerminal 
{
	private ArrayList<TypeIF> params;
	
    /**
     * Constructor for CabModule.
     */
    public ParFuncion ()
    {
        super ();
        params = new ArrayList<TypeIF>();
    }
    
    public ParFuncion (Parametros p)
    {
    	this();
    	for (TypeIF item : p.getParams()) {
    		params.add(item);
    	}
    }
    
    public ArrayList<TypeIF> getParams ()
    {
    	return params;
    }

}
