package compiler.syntax.nonTerminal;

import java.util.ArrayList;

public class ProcParenParam 
	extends NonTerminal 
{
	private ArrayList<ProcParam> paramList;
	
    /**
     * Constructor for ProcParenParam.
     */
    public ProcParenParam ()
    {
        super ();
        paramList = new ArrayList<ProcParam>();
    }
    
    
    public ProcParenParam (ProcListParam pl)
    {
    	this ();
    	this.paramList = pl.getParamList();
    }
    

    public ArrayList<ProcParam> getParamList()
    {
    	return this.paramList;
    }
    
    /*
    public int getSize ()
    {
    	return paramList.size();
    }
    */
}
