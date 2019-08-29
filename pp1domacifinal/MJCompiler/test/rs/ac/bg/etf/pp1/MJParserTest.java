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

public class MJParserTest {
	
	static {
		DOMConfigurator.configure(Log4JUtils.instance().findLoggerConfigFile());
		Log4JUtils.instance().prepareLogFile(Logger.getRootLogger());
	}
	
	public static final Struct boolType = new Struct(Struct.Bool);
	public static final Struct enumType = new Struct(Struct.Enum);
	
	public static void main(String[] args) throws Exception {
		Logger logger = Logger.getLogger(MJParserTest.class);
		Reader reader = null;
		
		try {
			
			File sourceCode = new File("test/programtest.mj");
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
				 
				Tab.dump();
				 
				File file = new File("test/program.obj");
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
