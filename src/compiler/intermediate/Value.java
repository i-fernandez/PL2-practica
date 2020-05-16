package compiler.intermediate;

import es.uned.lsi.compiler.intermediate.ValueIF;

/**
 * Class for literal values within intermediate code.
 */

public class Value
    implements ValueIF 
{
    private int value;
      
    /**
     * Constructor for Value.
     * @param value The value.
     */
    public Value (Object value)
    {
        super ();
        if (value instanceof Boolean) {
        	boolean v = (boolean) value;
        	if (v) this.value = -1;
        	else this.value = 0;
        } else {
        	this.value = (Integer) value;
        }
    }
    
    public Value (boolean value)
    {
    	super ();
    	if (value) this.value = -1;
    	else this.value = 0;
    }

    /**
     * Returns the value.
     * @return Returns the value.
     */
    @Override
    public final Object getValue ()
    {
        return value;
    }

    /**
     * Compares this object with another one.
     * @param other the other object.
     * @return true if both objects has the same properties.
     */
    @Override
    public final boolean equals (Object other)
    {
        if (other == null) 
        {
            return false;
        }
        if (this == other)
        {
            return true;
        }
        if (!(other instanceof Value))
        {
            return false;
        }
        
        final Value aValue = (Value) other;
        return (value == aValue.value);
    }

    /**
     * Returns a hash code for the object.
     */
    @Override
    public final int hashCode ()
    {
        return 31 * value;
    } 

    /**
     * Return a string representing the object.
     * @return a string representing the object.
     */
    @Override
    public final String toString ()
    {        
        return Integer.toString(value);
    }
}
