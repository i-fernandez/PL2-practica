package compiler.syntax.nonTerminal;

import java.util.ArrayList;

public class ProcListParam 
	extends NonTerminal 
{
	private ArrayList<ProcParam> paramList;
	
    /**
     * Constructor for CabModule.
     */
    public ProcListParam ()
    {
        super (); 
        paramList = new ArrayList<ProcParam>();
    }
    
    /**
     * Constructor with one param list
     * @param params
     */
    public ProcListParam(ProcParam params)
    {
    	this();
    	paramList.add(params);
    }
    
    /**
     * Copy constructor plus one ProcParam item
     */
    public ProcListParam(ProcListParam pl, ProcParam pp)
    {
    	this();
    	paramList.add(pp);
		for (ProcParam item : pl.getParamList()) {
			this.paramList.add(item);
		}
    }
    
    
    public ArrayList<ProcParam> getParamList()
    {
    	return this.paramList;
    }

}
