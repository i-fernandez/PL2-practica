package compiler.syntax.nonTerminal;

import java.util.ArrayList;
import es.uned.lsi.compiler.semantic.type.TypeIF;

public class Parametros 
	extends NonTerminal 
{
	private ArrayList<TypeIF> params;
	
    /**
     * Constructor for CabModule.
     */
    public Parametros ()
    {
        super (); 
        params = new ArrayList<TypeIF>();
    }
    
    public Parametros (TypeIF t)
    {
    	this();
    	params.add(t);
    }
    
    
    /**
     * Copy constructor plus one ProcParam item
     */
    public Parametros (Parametros p, TypeIF t)
    {
    	this();
		for (TypeIF item : p.getParams()) {
			params.add(item);
		}
		params.add(t);
    }
    
    
    
    public ArrayList<TypeIF> getParams ()
    {
    	return params;
    }

}
