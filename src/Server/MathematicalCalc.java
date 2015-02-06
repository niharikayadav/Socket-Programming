package Server;

public class MathematicalCalc {

	private String expression;

	public MathematicalCalc(String expression) {
		this.expression = expression;
	}

	public String calculate() {

		String result = null;
		double r = 0;
		String[] args = expression.split(" ");
		int len = args.length;

		if (len == 2) {
			if (args[0].equals("SQRT")) {
				r = Math.sqrt(Double.parseDouble(args[1]));
				result = r + "";
			}
		} else {
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
				if(b != 0){
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
