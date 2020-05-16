package compiler.semantic.type;

import compiler.CompilerContext;
import es.uned.lsi.compiler.code.ExecutionEnvironmentIF;
import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.type.TypeBase;

/**
 * Class for TypeSimple.
 */

public class TypeSimple
    extends TypeBase
{
    
	private String typeName;
	
    /**
     * Constructor for TypeSimple.
     * @param scope The declaration scope.
     */
    public TypeSimple (ScopeIF scope)
    {
        super (scope);
    }
    
    public TypeSimple (ScopeIF scope, String name)
    {
    	super(scope,name);
    	setTypeName(name);
    }
    
    /**
     * Returns the size of the type.
     * @return the size of the type.
     */
    @Override
    public int getSize ()
    {
    	ExecutionEnvironmentIF environment = CompilerContext.getExecutionEnvironment ();
        return environment.getTypeSize (this);
    }
    
    
    public void setTypeName (String name)
    {
    	this.typeName = name;
    }
    
    
    public String getTypeName ()
    {
    	return typeName;
    }
     
    

    /**
     * Comprueba si dos tipos con compatibles frente a una operacion
     * @param type objeto a comparar
     * @param op operacion
     * @return true si los tipos son compatibles respecto a la operacion
     */
    public boolean isCompatible (TypeSimple type, String op)
    {
    	switch (op)
    	{
    	case "MENOS": 
    	case "PROD":
    	case "MAYOR":
    		// Ambos tipos entero
    		return (typeName.equals(type.getName()) && typeName.equals("ENTERO"));
    	case "IGUAL":
    		// Ambos tipos iguales
    		return typeName.equals(type.getName());
    	case "OR":
    		// Ambos tipos booleano
    		return (typeName.equals(type.getName()) && typeName.equals("BOOLEANO"));
    	default: return false;
    	}
    }
}
