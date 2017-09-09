import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.FileInputStream;
import java.io.InputStream;

public class Calc {
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
		LabeledExprLexer lexer = new LabeledExprLexer(input);
		//新建一个词法符号的缓冲区,用于存储词法分析器生成的词法符号
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		//新建一个语法分析器,处理词法符号缓冲区里的内容
		LabeledExprParser parser = new LabeledExprParser(tokens);
		
		ParseTree tree = parser.prog();//根据规则生成语法树
		
		//自定义一个访问器来遍历语法树
		EvalVisitor eval = new EvalVisitor();
		eval.visit(tree);
		//TODO 没有在文件中配置CLASSPATH
	}
}