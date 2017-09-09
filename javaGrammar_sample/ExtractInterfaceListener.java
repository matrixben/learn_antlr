import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.misc.Interval;

public class ExtractInterfaceListener extends JavaGrammarBaseListener {
	JavaGrammarParser parser;
	
	public ExtractInterfaceListener(JavaGrammarParser parser){
		this.parser = parser;
	}
	
	/**监听对类定义的匹配 */
	@Override
	public void enterClassDeclaration(JavaGrammarParser.ClassDeclarationContext ctx){
		System.out.println("interface I" + ctx.Identifier() + " {");
	}
	@Override
	public void exitClassDeclaration(JavaGrammarParser.ClassDeclarationContext ctx){
		System.out.println("}");
	}
	
	/**监听对方法定义的匹配 */
	@Override
	public void enterMethodDeclaration(JavaGrammarParser.MethodDeclarationContext ctx){
		//需要从语法分析器中获取词法符号
		TokenStream tokens = parser.getTokenStream();
		String type = "void";
		if(ctx.typeType() != null){
			type = tokens.getText(ctx.typeType());
		}
		String args = tokens.getText(ctx.formalParameters());
		System.out.println("\t" + type + " " + ctx.Identifier() + args + ";");
	}
}