package Server;

//This class is initiated for each client by the server
public class MathematicalCalc {

	private String expression;
	
	public MathematicalCalc(String expression) {
		this.expression = expression;
	}
	
	//calculate method checks the expression for operators and operands and decides the action.
	public String calculate() {

		String result = null;
		double r = 0;
		String[] args = expression.split(" ");
		int len = args.length;
		// if there is only one operator and one operand
		if (len == 2) {
			if (args[0].equals("SQRT")) {
				r = Math.sqrt(Double.parseDouble(args[1]));
				result = r + "";
			}
		} else { // if there is only one operator and more than one operand
			if (args[0].equals("*")) {
				r = 1;
				for (int i = 1; i < len; i++) {
					r *= Integer.parseInt(args[i]);
					result = r + "";
				}
			} else if (args[0].equals("+")) {
				for (int i = 1; i < len; i++) {
					r += Integer.parseInt(args[i]);
					result = r + "";
				}
			} else if (args[0].equals("-")) {
				int a = Integer.parseInt(args[1]);
				int b = Integer.parseInt(args[2]);
				r = a - b;
				result = r + "";
			} else if (args[0].equals("/")) {
				int a = Integer.parseInt(args[1]);
				int b = Integer.parseInt(args[2]);
				if(b != 0){ // Checking condition to handle exception
				r = a / b;
				result = r + "";
				}
				else result = "Format is wrong..";
			} else if (args[0].equals("POW")) {
				r = Math.pow(Double.parseDouble(args[1]),
						Double.parseDouble(args[2]));
				result = r + "";
			}
		}
		return result;
	}
}
