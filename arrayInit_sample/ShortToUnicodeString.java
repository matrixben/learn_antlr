/**转换数组格式
 * 如:{1,2,3} -> "\u0001\u0002\u0003"
 */
public class ShortToUnicodeString extends ArrayInitBaseListener {
	/**将{转换成" */
	@Override
	public void enterInit(ArrayInitParser.InitContext ctx){
		System.out.print('"');
	}
	/**将}转换成" */
	@Override
	public void exitInit(ArrayInitParser.InitContext ctx){
		System.out.print('"');
	}
	/**将每个整数转换成四位的十六进制形式,然后加前缀\\u */
	@Override
	public void enterValue(ArrayInitParser.ValueContext ctx){
		int value = Integer.valueOf(ctx.INT().getText());
		System.out.printf("\\u%04x", value);
	}
}