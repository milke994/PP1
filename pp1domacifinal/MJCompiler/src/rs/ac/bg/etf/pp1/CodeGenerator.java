package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.CounterVisitor.FormParamCounter;
import rs.ac.bg.etf.pp1.CounterVisitor.VarCounter;
import rs.ac.bg.etf.pp1.ast.AddopPlus;
import rs.ac.bg.etf.pp1.ast.BoolFactor;
import rs.ac.bg.etf.pp1.ast.CharFactor;
import rs.ac.bg.etf.pp1.ast.DesigExprBegin;
import rs.ac.bg.etf.pp1.ast.DesigFactor;
import rs.ac.bg.etf.pp1.ast.DesignatorStDecrement;
import rs.ac.bg.etf.pp1.ast.DesignatorStEqualExpr;
import rs.ac.bg.etf.pp1.ast.DesignatorStIncrement;
import rs.ac.bg.etf.pp1.ast.ExprAddopTerm;
import rs.ac.bg.etf.pp1.ast.ExprEqualsMinusTerm;
import rs.ac.bg.etf.pp1.ast.MethodDecl;
import rs.ac.bg.etf.pp1.ast.MulopDiv;
import rs.ac.bg.etf.pp1.ast.MulopFactors;
import rs.ac.bg.etf.pp1.ast.MulopMul;
import rs.ac.bg.etf.pp1.ast.NewFactor;
import rs.ac.bg.etf.pp1.ast.NumFactor;
import rs.ac.bg.etf.pp1.ast.OptionNumConstYes;
import rs.ac.bg.etf.pp1.ast.OptionSBracketExprYes;
import rs.ac.bg.etf.pp1.ast.PrintStatement;
import rs.ac.bg.etf.pp1.ast.ReadDesignator;
import rs.ac.bg.etf.pp1.ast.SyntaxNode;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.ac.bg.etf.pp1.ast.VoidMethodName;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;


public class CodeGenerator extends VisitorAdaptor {
	
	private int mainPc;
	
	public int getMainPc() {
		return mainPc;
	}
	
	
	public void visit(PrintStatement printStatement) {
		int width = 5;
		if(printStatement.getOptionNumConst() instanceof OptionNumConstYes) {
			OptionNumConstYes optionNumConstYes = (OptionNumConstYes) printStatement.getOptionNumConst();
			width = optionNumConstYes.getNum();
		}
		Code.loadConst(width);
		if(printStatement.getExpr().struct == Tab.charType) {
			Code.put(Code.bprint);
		} else {
			Code.put(Code.print);
		}
	}
	
	public void visit(ReadDesignator readDesignator) {
		if(readDesignator.getDesignator().obj.getType().equals(Tab.charType)) {
			Code.put(Code.bread);
		} else {
			Code.put(Code.read);
		}
		Code.store(readDesignator.getDesignator().obj);
	}
	
	public void visit(NumFactor numFactor) {
		Code.loadConst(numFactor.getNum());
	}
	
	public void visit(CharFactor charFactor) {
		Code.loadConst(charFactor.getCharr());
	}
	
	public void visit(BoolFactor boolFactor) {
		if(boolFactor.getBool()) {
			Code.loadConst(1);
		} else {
			Code.loadConst(0);
		}
	}
	
	public void visit(VoidMethodName voidMethodName) {
		if("main".equals(voidMethodName.getMethName())) {
			mainPc = Code.pc;
		}
		voidMethodName.obj.setAdr(Code.pc);
		
		
		// Collect arguments and local variables
		SyntaxNode methodNode = voidMethodName.getParent();
		
		VarCounter varCounter = new VarCounter();
		methodNode.traverseTopDown(varCounter);
		
		FormParamCounter formParamCounter = new FormParamCounter();
		methodNode.traverseTopDown(formParamCounter);
		
		// Generate entry
		Code.put(Code.enter);
		Code.put(formParamCounter.getCount());
		Code.put(formParamCounter.getCount() + varCounter.getCount());
	}
	
	public void visit(MethodDecl methodDecl) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(DesignatorStEqualExpr designatorStEqualExpr) {
		Code.store(designatorStEqualExpr.getDesignator().obj);
	}
	
	public void visit(DesigFactor desigFactor) {
		Code.load(desigFactor.getDesignator().obj);
	}
	
	public void visit(DesignatorStIncrement designatorStIncrement) {
		if(designatorStIncrement.getDesignator().obj.getKind() == Obj.Elem) {
			Code.put(Code.dup2);			
		}
		Code.load(designatorStIncrement.getDesignator().obj);
		Code.loadConst(1);
		Code.put(Code.add);
		Code.store(designatorStIncrement.getDesignator().obj);
	}
	
	public void visit(DesignatorStDecrement designatorStDecrement) {
		if(designatorStDecrement.getDesignator().obj.getKind() == Obj.Elem) {
			Code.put(Code.dup2);
		}
		Code.load(designatorStDecrement.getDesignator().obj);
		Code.loadConst(1);
		Code.put(Code.sub);
		Code.store(designatorStDecrement.getDesignator().obj);
	}
	
	public void visit(ExprAddopTerm exprAddopTerm) {
		if(exprAddopTerm.getAddop() instanceof AddopPlus) {
			Code.put(Code.add);
		} else {
			Code.put(Code.sub);
		}
	}
	
	public void visit(MulopFactors mulopFactors) {
		if(mulopFactors.getMulop() instanceof MulopMul) {
			Code.put(Code.mul);
		}
		else if(mulopFactors.getMulop() instanceof MulopDiv) {
			Code.put(Code.div);
		} else {
			Code.put(Code.rem);
		}
	}
	
	public void visit(DesigExprBegin desigExprBegin) {
		Code.load(desigExprBegin.getDesignator().obj);
	}
	
	public void visit(NewFactor newFactor) {
		if(newFactor.getOptionSBracketExpr() instanceof OptionSBracketExprYes) {
			Code.put(Code.newarray);
			if(newFactor.getType().struct.equals(Tab.charType)) {
				Code.put(0);
			} else {
				Code.put(1);
			}
		}
	}
	
	public void visit(ExprEqualsMinusTerm equalsMinusTerm) {
		Code.put(Code.neg);
	}
}
