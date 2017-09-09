import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.FileInputStream;
import java.io.InputStream;

public class ExtractInterfaceTool {
	public static void main(String[] args) throws Exception {
	    //将参数传入的文件转为ANTLR输入
	    String inputFile = null;
	    if (args.length > 0) {
	    	inputFile = args[0];
	    }
	    InputStream is = System.in;
	    if (inputFile != null) {
	    	is = new FileInputStream(inputFile);
	    }
		//新建一个CharStream,从参数输入读取数据
		ANTLRInputStream input = new ANTLRInputStream(is);
		//新建一个词法分析器,处理输入的CharStream
		JavaGrammarLexer lexer = new JavaGrammarLexer(input);
		//新建一个词法符号的缓冲区,用于存储词法分析器生成的词法符号
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		//新建一个语法分析器,处理词法符号缓冲区里的内容
		JavaGrammarParser parser = new JavaGrammarParser(tokens);
		
		ParseTree tree = parser.compilationUnit();//根据规则生成语法树
		ParseTreeWalker walker = new ParseTreeWalker();//新建一个标准遍历器
		ExtractInterfaceListener extractor = new ExtractInterfaceListener(parser);
		walker.walk(extractor, tree);
	}
}