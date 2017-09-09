import java.util.HashMap;
import java.util.Map;

public class EvalVisitor extends LabeledExprBaseVisitor<Integer> {
	/**计算器的‘内存’,存放变量名和变量值的对应关系 */
	Map<String, Integer> memory = new HashMap<String, Integer>();
	
	/**等号赋值 */
	@Override 
	public Integer visitAssign(LabeledExprParser.AssignContext ctx) { 
		String id = ctx.ID().getText();  //取id的值
		int value = visit(ctx.expr());   //计算右边表达式的值
		memory.put(id, value);           //用map映射id和数值的关系
		return value; 
	}
	/**换行 */
	@Override 
	public Integer visitPrintExpr(LabeledExprParser.PrintExprContext ctx) {
		Integer value = visit(ctx.expr());
		System.out.println(value);
		return 0; 
	}
	
	@Override 
	public Integer visitInt(LabeledExprParser.IntContext ctx) {
		return Integer.valueOf(ctx.INT().getText());
	}
	
	@Override 
	public Integer visitId(LabeledExprParser.IdContext ctx) {
		String id = ctx.ID().getText();
		if (memory.containsKey(id)) {
			return memory.get(id);
		}
		return 0;
	}
	/** op=('*'|'/') */
	@Override 
	public Integer visitMulDiv(LabeledExprParser.MulDivContext ctx) {
		int left = visit(ctx.expr(0));  //计算左侧表达式的值
		int right = visit(ctx.expr(1));  //计算右侧表达式的值
		if (ctx.op.getType() == LabeledExprParser.MUL) {
			return left * right;
		} else if (right == 0) {
			System.out.println("divide can't be zero!");
			return 0;
		}
		return left / right; 
	}
	/** op=('+'|'-') */
	@Override 
	public Integer visitAddSub(LabeledExprParser.AddSubContext ctx) {
		int left = visit(ctx.expr(0));  //计算左侧表达式的值
		int right = visit(ctx.expr(1));  //计算右侧表达式的值
		if (ctx.op.getType() == LabeledExprParser.ADD) {
			return left + right;
		}
		return left - right; 
	}
	/** '(' expr ')' */
	@Override 
	public Integer visitParens(LabeledExprParser.ParensContext ctx) {
		return visit(ctx.expr()); 
	}
}