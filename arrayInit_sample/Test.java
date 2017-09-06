import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Test {
	public static void main(String[] args) throws Exception {
		//新建一个CharStream,从标准输入读取数据
		ANTLRInputStream input = new ANTLRInputStream(System.in);
		//新建一个词法分析器,处理输入的CharStream
		ArrayInitLexer lexer = new ArrayInitLexer(input);
		//新建一个词法符号的缓冲区,用于存储词法分析器生成的词法符号
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		//新建一个语法分析器,处理词法符号缓冲区里的内容
		ArrayInitParser parser = new ArrayInitParser(tokens);
		
		ParseTree tree = parser.init();//根据规则生成语法树
		//新建一个语法树遍历器
		ParseTreeWalker walker = new ParseTreeWalker();
		//遍历前面的语法树并通过自定义的监听器翻译
        walker.walk(new ShortToUnicodeString(), tree);
        System.out.println();
	}
}