package compiler.semantic.type;

import java.util.ArrayList;

import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.type.TypeBase;
import es.uned.lsi.compiler.semantic.type.TypeIF;

/**
 * Class for TypeProcedure.
 */

// TODO: Student work
//       Include properties to characterize procedure declarations

public class TypeProcedure
    extends TypeBase
{
	private ArrayList<TypeIF> parameterTypes;
	
   /**
     * Constructor for TypeProcedure.
     * @param scope The declaration scope.
     */
    public TypeProcedure (ScopeIF scope)
    {
        super (scope);
        this.parameterTypes = new ArrayList<TypeIF>();
    }

    /**
     * Constructor for TypeProcedure.
     * @param scope The declaration scope
     * @param name The name of the procedure.
     */
    public TypeProcedure (ScopeIF scope, String name)
    {
        super (scope, name);
        this.parameterTypes = new ArrayList<TypeIF>();
    }
    
    public void addParameterType(TypeIF param)
    {
    	this.parameterTypes.add(param);
    }
    
    public ArrayList<TypeIF> getParametersType ()
    {
    	return this.parameterTypes;
    }
    
    /**
     * Comprueba si los parametros pasados son iguales a los del objeto
     * @param par Lista de parametros a comprobar
     * @return true si los parametros coinciden en numero y tipo
     */
    public boolean checkParameters (ArrayList<TypeIF> par)
    {
    	if (parameterTypes.size() != par.size())  return false;
    	
    	for (int i = 0; i < parameterTypes.size(); i++)
    	{
    		String parA = parameterTypes.get(i).getName();
    		String parB = par.get(i).getName();
    		if (!parA.equals(parB))	return false;
    	}
    	return true;
    }
    
    /**
     * Returns the size of the type.
     * @return the size of the type.
     */
    @Override
    public int getSize ()
    {
        // TODO: Student work
        return 1;
    }
    
    /**
     * @return Numero de parametros
     */
    public int getParametersCount ()
    {
    	return this.parameterTypes.size();
    }
}
