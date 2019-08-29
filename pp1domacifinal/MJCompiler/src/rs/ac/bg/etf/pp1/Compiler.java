package rs.ac.bg.etf.pp1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.Reader;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java_cup.runtime.Symbol;
import rs.ac.bg.etf.pp1.ast.Program;
import rs.ac.bg.etf.pp1.util.Log4JUtils;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class Compiler {
	static {
		DOMConfigurator.configure(Log4JUtils.instance().findLoggerConfigFile());
		Log4JUtils.instance().prepareLogFile(Logger.getRootLogger());
	}
	
	static void tsdump() {
		Tab.dump();
	}
	
	public static final Struct boolType = new Struct(Struct.Bool);
	public static final Struct enumType = new Struct(Struct.Enum);
	
	public static void main(String[] args) throws Exception {
		Logger logger = Logger.getLogger(Compiler.class);
		Reader reader = null;
		if(args.length < 2) {
			logger.error("Nedovoljno argumenata, prvi argument je putanja do .mj fajla koji se prevodi, drugi argument je putanja do .obj fajla");
			return;
		}
		
		File sourceCode = new File(args[0]);
		if(!sourceCode.exists()) {
			logger.error("Fajl " + sourceCode.getAbsolutePath() + " prosledjen prvim argumentom ne postoji!");
			return;
		}
		
		try {
			logger.info("Compiling source file: " + sourceCode.getAbsolutePath());
			System.out.println("=====================LEKSICKA ANALIZA=========================");
			
			reader = new BufferedReader(new FileReader(sourceCode));
			Yylex lexer = new Yylex(reader);
			
			MJParser p = new MJParser(lexer);
			Symbol symbol = p.parse();
						
			Program program = (Program) (symbol.value);
			Tab.init();
			Tab.currentScope.addToLocals(new Obj(Obj.Type, "bool", boolType));
			Tab.currentScope.addToLocals(new Obj(Obj.Type, "enum", enumType));
			
			
			System.out.println("=====================SINTASKNO STABLO=========================");
			logger.info(program.toString(""));
			System.out.println("=====================SEMANTICKA ANALIZA=========================");

			SemanticAnalyzer v = new SemanticAnalyzer();
			program.traverseBottomUp(v);
		

			 if(!p.errorDetected && v.passed()) {
				 
				tsdump();
				 
				File file = new File(args[1]);
				if(file.exists()) file.delete();
				 
				CodeGenerator codeGenerator = new CodeGenerator();
				program.traverseBottomUp(codeGenerator);
				Code.dataSize = v.nVars;
				Code.mainPc = codeGenerator.getMainPc();
				Code.write(new FileOutputStream(file));
		        logger.info("Parsiranje uspesno zavrseno!");
		        }
		        else {
		        	logger.error("Parsiranje NIJE uspesno zavrseno!");
		        }
		
		} finally {
			if(reader != null) try {reader.close();} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	}
}
