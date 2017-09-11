import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.TokenStreamRewriter;

public class InsertSerialIDListener extends JavaGrammarBaseListener {
	TokenStreamRewriter rewriter;
	
	public InsertSerialIDListener(TokenStream tokens){
		this.rewriter = new TokenStreamRewriter(tokens);
	}
	
	/**监听对类定义中所有内容的匹配 */
	@Override 
	public void enterClassBody(JavaGrammarParser.ClassBodyContext ctx){
		String field = "\n\tpublic static final long serialVersionUID = 1L;";
		rewriter.insertAfter(ctx.start, field);
	}
}