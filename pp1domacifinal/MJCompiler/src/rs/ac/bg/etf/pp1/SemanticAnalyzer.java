package rs.ac.bg.etf.pp1;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.BoolConst;
import rs.ac.bg.etf.pp1.ast.BoolFactor;
import rs.ac.bg.etf.pp1.ast.CharConst;
import rs.ac.bg.etf.pp1.ast.CharFactor;
import rs.ac.bg.etf.pp1.ast.DesigExpr;
import rs.ac.bg.etf.pp1.ast.DesigFactor;
import rs.ac.bg.etf.pp1.ast.DesignatorBegin;
import rs.ac.bg.etf.pp1.ast.DesignatorStActualPars;
import rs.ac.bg.etf.pp1.ast.DesignatorStDecrement;
import rs.ac.bg.etf.pp1.ast.DesignatorStEqualExpr;
import rs.ac.bg.etf.pp1.ast.DesignatorStIncrement;
import rs.ac.bg.etf.pp1.ast.DotIdent;
import rs.ac.bg.etf.pp1.ast.EnumDecl;
import rs.ac.bg.etf.pp1.ast.EnumIdent;
import rs.ac.bg.etf.pp1.ast.EnumIdentHaveValue;
import rs.ac.bg.etf.pp1.ast.ExprAddopTerm;
import rs.ac.bg.etf.pp1.ast.ExprEqualsMinusTerm;
import rs.ac.bg.etf.pp1.ast.ExprEqualsTerm;
import rs.ac.bg.etf.pp1.ast.FactorExpr;
import rs.ac.bg.etf.pp1.ast.MethodDecl;
import rs.ac.bg.etf.pp1.ast.MethodTypeN;
import rs.ac.bg.etf.pp1.ast.MulopFactor;
import rs.ac.bg.etf.pp1.ast.MulopFactors;
import rs.ac.bg.etf.pp1.ast.NewFactor;
import rs.ac.bg.etf.pp1.ast.NoFormParams;
import rs.ac.bg.etf.pp1.ast.NumConst;
import rs.ac.bg.etf.pp1.ast.NumFactor;
import rs.ac.bg.etf.pp1.ast.OptionParenActParsYes;
import rs.ac.bg.etf.pp1.ast.OptionSBracketExprYes;
import rs.ac.bg.etf.pp1.ast.PrintStatement;
import rs.ac.bg.etf.pp1.ast.ProgName;
import rs.ac.bg.etf.pp1.ast.Program;
import rs.ac.bg.etf.pp1.ast.ReadDesignator;
import rs.ac.bg.etf.pp1.ast.SBracketYes;
import rs.ac.bg.etf.pp1.ast.SyntaxNode;
import rs.ac.bg.etf.pp1.ast.Type;
import rs.ac.bg.etf.pp1.ast.VarIdent;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.ac.bg.etf.pp1.ast.VoidMethodName;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;
import rs.etf.pp1.symboltable.visitors.DumpSymbolTableVisitor;


public class SemanticAnalyzer extends VisitorAdaptor {

	boolean errorDetected = false;
	boolean mainFound = false;
	int nVars;
	Struct poslednjiTip = null;
	int currentValue = 0;
	ArrayList<Integer> enumValues = new ArrayList<>(); 
	boolean enumScopeOpened = false;
	Obj currentMethod = null;

	Logger log = Logger.getLogger(getClass());

	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.info(msg.toString());
	}
	
	public void visit(Program program) {		
		nVars = Tab.currentScope.getnVars();
		Tab.chainLocalSymbols(program.getProgName().obj);
		Tab.closeScope();
		if(!mainFound) {
			report_error("Semanticka greska, nije pronadjena main metoda tipa void i bez argumenata!", null);
		}
		mainFound = false;
	}

	public void visit(ProgName progName) {
		progName.obj = Tab.insert(Obj.Prog, progName.getPName(), Tab.noType);
		Tab.openScope();     	
	}

	public void visit(Type type) {
		Obj typeNode = Tab.find(type.getTypeName());
		if (typeNode == Tab.noObj) {
			report_error("Nije pronadjen tip " + type.getTypeName() + " u tabeli simbola", null);
			type.struct = Tab.noType;
		} 
		else {
			if (Obj.Type == typeNode.getKind()) {
				type.struct = typeNode.getType();
			} 
			else {
				report_error("Greska: Ime " + type.getTypeName() + " ne predstavlja tip ", type);
				type.struct = Tab.noType;
			}
		}
		poslednjiTip = type.struct;
		if(poslednjiTip.getKind() == Struct.Enum) {
			poslednjiTip = poslednjiTip.getElemType();
		}
	}
	
	public void visit(NumConst numConst) {
		if(!poslednjiTip.equals(Tab.intType)) report_error("Semanitcka greska, tipovi nisu kompatibilni pri inicijalizaciji konstante ", numConst);
		Obj obj = Tab.currentScope.findSymbol(numConst.getIdentName());
		if(obj != null) {
			report_error("Semanticka greska, simbol sa imenom " + numConst.getIdentName() + " je vec deklarisan u trenutnom scoupe-u ", numConst);
		} else {
			obj = Tab.insert(Obj.Con, numConst.getIdentName(), poslednjiTip);
			obj.setAdr(numConst.getNum());
		}
	}
	
	public void visit(CharConst charConst) {
		if(!poslednjiTip.equals(Tab.charType)) report_error("Semanitcka greska, tipovi nisu kompatibilni pri inicijalizaciji konstante ", charConst);
		Obj obj = Tab.currentScope.findSymbol(charConst.getIdentName());
		if(obj != null) {
			report_error("Semanticka greska, simbol sa imenom " + charConst.getIdentName() + " je vec deklarisan u trenutnom scoupe-u ", charConst);
		} else {
			obj = Tab.insert(Obj.Con, charConst.getIdentName(), poslednjiTip);
			obj.setAdr(charConst.getCharr());
		}
	}
	
	public void visit(BoolConst boolConst) {
		if(!poslednjiTip.equals(Compiler.boolType)) report_error("Semanitcka greska, tipovi nisu kompatibilni pri inicijalizaciji konstante ", boolConst);
		Obj obj = Tab.currentScope.findSymbol(boolConst.getIdentName());
		if(obj != null) {
			report_error("Semanticka greska, simbol sa imenom " + boolConst.getIdentName() + " je vec deklarisan u trenutnom scoupe-u ", boolConst);
		} else {
			obj = Tab.insert(Obj.Con, boolConst.getIdentName(), poslednjiTip);
			obj.setAdr(boolConst.getBool() ? 1 : 0);
		}
	}
	
	public void visit(EnumIdent enumIdent) {
		if(!enumScopeOpened) {
			Tab.openScope();
			enumScopeOpened = true;
		}
		Obj obj = Tab.currentScope.findSymbol(enumIdent.getIdentName());
		if(obj == null) {
			if(enumIdent.getEnumIdentValue() instanceof EnumIdentHaveValue) {
				EnumIdentHaveValue enumValue = (EnumIdentHaveValue) enumIdent.getEnumIdentValue();
				currentValue = enumValue.getN1();
			}
			if(enumValues.contains(currentValue)) {
				report_error("Semanticka greska, vrednost elementa nabrajanja sa imenom " + enumIdent.getIdentName() + " vec postoji u istom nabrajanju ", enumIdent);
			}
			obj = Tab.insert(Obj.Con, enumIdent.getIdentName(), Tab.intType);
			obj.setAdr(currentValue);
			obj.setLevel(0);
			//Tab.currentScope.addToLocals(obj);
			enumValues.add(currentValue);
			currentValue++;
		} else {
			report_error("Semanticka greska, element nabrajanja sa imenom " + enumIdent.getIdentName() + " vec postoji ", enumIdent);
		}
	}
	
	public void visit(EnumDecl enumDecl) {
		Struct struct = new Struct(Struct.Enum);
		struct.setMembers(Tab.currentScope.getLocals());
		struct.setElementType(Tab.intType);
		Tab.closeScope();
		enumScopeOpened = false;
		enumValues.clear();
		Obj obj = Tab.currentScope.findSymbol(enumDecl.getIdentName());
		if(obj == null) {
			obj = Tab.insert(Obj.Type, enumDecl.getIdentName(), struct);
		} else {
			report_error("Semanticka greska, simbol sa imenom " + enumDecl.getIdentName() + " vec postoji u trenutnom scope-u", enumDecl);
		}
	}
	
	public void visit(VarIdent varIdent) {
		Obj obj = Tab.currentScope.findSymbol(varIdent.getVarName());
		if(obj == null) {
			if(varIdent.getOptionSBracket() instanceof SBracketYes) {
				Struct temp = new Struct(Struct.Array, poslednjiTip);
				obj = Tab.insert(Obj.Var, varIdent.getVarName(), temp);
			}
			else {
				obj = Tab.insert(Obj.Var, varIdent.getVarName(), poslednjiTip);
			}
		} else {
			report_error("Semanticka greska, simbol sa imenom " + varIdent.getVarName() + " je vec deklarisan u trenutnom scope-u ", varIdent);
		}
	}
	
	public void visit(MethodTypeN methodTypeN) {
		currentMethod = Tab.insert(Obj.Meth, methodTypeN.getMethName(), methodTypeN.getType().struct);
		methodTypeN.obj = currentMethod;
		Tab.openScope();
	}
	
	public void visit(VoidMethodName voidMethodName) {
		currentMethod = Tab.insert(Obj.Meth, voidMethodName.getMethName(), Tab.noType);
		voidMethodName.obj = currentMethod;
		Tab.openScope();
	}
	
	public void visit(MethodDecl methodDecl) {
		
		if(methodDecl.getMethodTypeName().obj.getName().equals("main") && methodDecl.getMethodTypeName() instanceof VoidMethodName && methodDecl.getFormPars() instanceof NoFormParams) {
			mainFound = true;
		}
		
		Tab.chainLocalSymbols(currentMethod);
		Tab.closeScope();
		
		currentMethod = null;
	}
	
	public void visit(DesignatorBegin designatorBegin) {
		Obj obj = Tab.find(designatorBegin.getName());
		if(obj == Tab.noObj) {
			report_error("Semanticka greska, promenljiva sa imenom " + designatorBegin.getName() + " nije deklarisana, na liniji " + designatorBegin.getLine() , null);
		}
		designatorBegin.obj = obj;
		DumpSymbolTableVisitor tableVisitor = new DumpSymbolTableVisitor();
		tableVisitor.visitObjNode(obj);
		if(obj.getKind() == Obj.Con) {
			log.info("Detektovano koriscenje konstante ( "+ designatorBegin.getName() + ") na liniji " + designatorBegin.getLine() + " " + tableVisitor.getOutput());
		} else if(obj.getKind() == Obj.Var) {
			if(obj.getLevel() == 0) {
				log.info("Detektovano koriscenje globalne promenljive ( "+ designatorBegin.getName() + ") na liniji " + designatorBegin.getLine() + " " + tableVisitor.getOutput());
			} else {
				log.info("Detektovano koriscenje lokalne promenljive ( "+ designatorBegin.getName() + ") na liniji " + designatorBegin.getLine() + " " + tableVisitor.getOutput());
			}
		}
	}
	
	public void visit(DesigExpr desigExpr) {
		if(desigExpr.getDesigExprBegin().getDesignator().obj.getType().getKind() != Struct.Array) {
			report_error("Semanticka greska, designator mora biti tipa niz", desigExpr);
		}
		if(desigExpr.getExpr().struct.getKind() == Struct.Enum) {
			if(!desigExpr.getExpr().struct.getElemType().compatibleWith(Tab.intType)) {
				report_error("Semanticka greska, expr mora biti tipa int", desigExpr);
			}
		} else {
			if (!desigExpr.getExpr().struct.compatibleWith(Tab.intType)) {
				report_error("Semanticka greska, expr mora biti tipa int", desigExpr);
			}
		}
		desigExpr.obj = new Obj(Obj.Elem, "", desigExpr.getDesigExprBegin().getDesignator().obj.getType().getElemType());
	}
	
	public void visit(NumFactor numFactor) {
		numFactor.struct = Tab.intType;
	}
	
	public void visit(CharFactor charFactor) {
		charFactor.struct = Tab.charType;
	}
	
	public void visit(BoolFactor boolFactor) {
		boolFactor.struct = Compiler.boolType;
	}
	
	public void visit(FactorExpr factorExpr) {
		factorExpr.struct = factorExpr.getExpr().struct;
	}
	
	public void visit(DesignatorStEqualExpr designatorStEqualExpr) {
		if(designatorStEqualExpr.getDesignator().obj.getKind() != Obj.Var && designatorStEqualExpr.getDesignator().obj.getKind() != Obj.Elem && designatorStEqualExpr.getDesignator().obj.getKind() != Obj.Fld) {
			report_error("Semanticka greska, designator mora biti promenljiva, element niza ili polje objekta ", designatorStEqualExpr);
		}
		if(!designatorStEqualExpr.getExpr().struct.assignableTo(designatorStEqualExpr.getDesignator().obj.getType())) {
			report_error("Semanticka greska, designator i expression nisu istog tipa ", designatorStEqualExpr);
		}
	}
	
	public void visit(DesignatorStIncrement designatorStIncrement) {
		if(designatorStIncrement.getDesignator().obj.getKind() != Obj.Var && designatorStIncrement.getDesignator().obj.getKind() != Obj.Elem && designatorStIncrement.getDesignator().obj.getKind() != Obj.Fld) {
			report_error("Semanticka greska, designator mora biti promenljiva, element niza ili polje objekta ", designatorStIncrement);
		}
		if(!designatorStIncrement.getDesignator().obj.getType().equals(Tab.intType)) {
			report_error("Semanticka greska, designator nije tipa int ", designatorStIncrement);
		}
	}
	
	public void visit(DesignatorStDecrement designatorStDecrement) {
		if(designatorStDecrement.getDesignator().obj.getKind() != Obj.Var && designatorStDecrement.getDesignator().obj.getKind() != Obj.Elem && designatorStDecrement.getDesignator().obj.getKind() != Obj.Fld) {
			report_error("Semanticka greska, designator mora biti promenljiva, element niza ili polje objekta ", designatorStDecrement);
		}
		if(!designatorStDecrement.getDesignator().obj.getType().equals(Tab.intType)) {
			report_error("Semanticka greska, designator nije tipa int ", designatorStDecrement);
		}
	}
	
	public void visit(DesigFactor desigFactor) {
		if(desigFactor.getOptionParenActPars() instanceof OptionParenActParsYes && desigFactor.getDesignator().obj.getKind() != Obj.Meth) {
			report_error("Semanticka greska, designator mora biti tipa metode",desigFactor);
		}
		int kind = desigFactor.getDesignator().obj.getKind();
		if(kind == Obj.Var || kind == Obj.Elem || kind == Obj.Fld || kind == Obj.Meth || kind == Obj.Con) {
			desigFactor.struct = desigFactor.getDesignator().obj.getType();
		}
	}
	
	public void visit(DesignatorStActualPars designatorStActualPars) {
		if(designatorStActualPars.getDesignator().obj.getKind() != Obj.Meth) {
			report_error("Semanticka greska, designator mora biti tipa metode ", designatorStActualPars);
		}
	}
	
	public void visit(ReadDesignator readDesignator) {
		int kind = readDesignator.getDesignator().obj.getKind();
		if(kind != Obj.Var && kind != Obj.Elem && kind != Obj.Fld) {
			report_error("Semanticka greska, designator nije tipa Var,Elem ili Fld", readDesignator);
		}
		Struct type = readDesignator.getDesignator().obj.getType();
		if(!type.equals(Tab.intType) && !type.equals(Tab.charType) && !type.equals(Compiler.boolType)) {
			report_error("Semanticka greska, designator nije tipa int,char ili bool", readDesignator);
		}
	}
	
	public void visit(PrintStatement printStatement) {
		Struct type = printStatement.getExpr().struct;
		if(!type.compatibleWith(Tab.charType) && !type.compatibleWith(Tab.intType) && !type.compatibleWith(Compiler.boolType)) {
			report_error("Expr nije tipa char,int ili bool", printStatement);
		}
	}
	
	public void visit(ExprEqualsTerm exprEqualsTerm) {
		exprEqualsTerm.struct = exprEqualsTerm.getTerm().struct;
	}
	
	public void visit(MulopFactors mulopFactors) {
		if(!mulopFactors.getFactor().struct.compatibleWith(mulopFactors.getTerm().struct) && !mulopFactors.getFactor().struct.compatibleWith(Tab.intType)) {
			report_error("Semanticka greska, Factor i Term nisu int ", mulopFactors);
		}
		mulopFactors.struct = mulopFactors.getFactor().struct;
	}
	
	public void visit(MulopFactor mulopFactor) {
		mulopFactor.struct = mulopFactor.getFactor().struct;
	}
	
	public void visit(ExprEqualsMinusTerm exprEqualsMinusTerm) {
		if(!exprEqualsMinusTerm.getTerm().struct.compatibleWith(Tab.intType)) {
			report_error("Semanticka greska, Term mora biti tipa int ", exprEqualsMinusTerm);
		}
		exprEqualsMinusTerm.struct = exprEqualsMinusTerm.getTerm().struct;
	}
	
	public void visit(ExprAddopTerm exprAddopTerm) {
		if(!exprAddopTerm.getExpr().struct.compatibleWith(exprAddopTerm.getTerm().struct) && !exprAddopTerm.getExpr().struct.compatibleWith(Tab.intType)) {
			report_error("Semanticka greska, Expr i Term ili nisu istog tipa ili jedan od njih nije tipa int ", exprAddopTerm);
		}
		exprAddopTerm.struct = exprAddopTerm.getExpr().struct;
	}
	
	public void visit(NewFactor newFactor) {
		if(newFactor.getOptionSBracketExpr() instanceof OptionSBracketExprYes) {
			OptionSBracketExprYes osbey = (OptionSBracketExprYes) newFactor.getOptionSBracketExpr();
			if(!osbey.getExpr().struct.compatibleWith(Tab.intType)) {
				report_error("Expr mora biti tipa int", newFactor);
			}
			newFactor.struct = new Struct(Struct.Array, poslednjiTip);
		}
		else {
			newFactor.struct = poslednjiTip;
		}
	}
	
	public void visit(DotIdent dotIdent) {
		Obj obj = dotIdent.getDesignator().obj.getType().getMembersTable().searchKey(dotIdent.getI2());
		if(obj == null) {
			report_error("Semanticka greska, trazena vrednost " + dotIdent.getI2() + " ne postoji u enumu " + dotIdent.getDesignator().obj.getName() + " ", dotIdent);
		}
		dotIdent.obj = obj;
	}
	
	public boolean passed() {
		return !errorDetected;
	}
	
}

