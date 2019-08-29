package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.FormalParamDecl;
import rs.ac.bg.etf.pp1.ast.VarIdent;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;

public class CounterVisitor extends VisitorAdaptor {
	
	protected int count;
	
	public int getCount() {
		return count;
	}
	
	public static class FormParamCounter extends CounterVisitor {

		@Override
		public void visit(FormalParamDecl formParamDecl) {
			count++;
		}		
	}
	
	public static class VarCounter extends CounterVisitor {		
		@Override
		public void visit(VarIdent	varIdent) {
			count++;
		}
	}
}
