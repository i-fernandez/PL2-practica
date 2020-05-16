package compiler.semantic.type;

import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.type.TypeBase;

/**
 * Class for TypeArray.
 */

// TODO: Student work
//       Include properties to characterize array type

public class TypeArray
    extends TypeBase
{   
    private TypeBase typeBase;
	private int minIndex;
	private int maxIndex;
	
	/**
     * Constructor for TypeArray.
     * @param scope The declaration scope.
     */
    public TypeArray (ScopeIF scope)
    {
        super (scope);
    }

    /**
     * Constructor for TypeArray.
     * @param scope The declaration scope.
     * @param name The name of the type.
     */
    public TypeArray (ScopeIF scope, String name)
    {
        super (scope, name);
    }
    
    public TypeArray (ScopeIF scope, String name, int min, int max, TypeBase type)
    {
    	this(scope, name);
    	this.typeBase = type;
    	this.minIndex = min;
    	this.maxIndex = max;
    }
    
    /**
     * Returns the size of the type.
     * @return the size of the type.
     */
    @Override
    public int getSize ()
    {
    	int itemCount = maxIndex-minIndex+1;
        return typeBase.getSize() * itemCount;
    }
    
    /**
     * @param n Integer to check
     * @return true if n is between min and max, otherwise returns false
     */
    public boolean isInRange (int n)
    {
    	return (n >= minIndex && n <= maxIndex);
    }
    
    
    public int getMinIndex()
    {
    	return this.minIndex;
    }
    public int getMaxIndex()
    {
    	return this.maxIndex;
    }
    
    
    public TypeBase getTypeBase()
    {
    	return this.typeBase;
    }

}
