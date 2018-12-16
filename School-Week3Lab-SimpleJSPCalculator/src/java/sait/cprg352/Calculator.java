package sait.cprg352;

/**
 *
 * @author 704199
 */
public class Calculator {
    public static final char OPERATION_ADD = '+';
    public static final char OPERATION_SUBTRACT = '-';
    public static final char OPERATION_MULTIPLY = '*';
    public static final char OPERATION_DIVIDE = '/';
    public static final char OPERATION_MODULO  = '%';
    
    private int first;
    private int second;
    
    /**
     * Constructor for Calculator
     * @param first First number
     * @param second Second number
     */
    public Calculator(int first, int second) {
        this.first = first;
        this.second = second;
    }
    
    /**
     * Performs an operation (add, subtract, etc.)
     * @param operation The operating to perform (one of OPERATION_* constants)
     * @return The number after first and second is added, subtracted, etc.
     * @throws Exception Thrown if the operation is invalid.
     */
    public int performOperation(char operation) throws Exception {
        switch (operation) {
            case OPERATION_ADD:
                return performAdd();
            case OPERATION_SUBTRACT:
                return performSubtract();
            case OPERATION_MULTIPLY:
                return performMultiply();
            case OPERATION_DIVIDE:
                return performDivide();
            case OPERATION_MODULO:
                return performModulo();
            
            default:
                throw new Exception();
        }
    }
    
    /**
     * Performs an add operation
     * @return First and second numbers added together
     */
    private int performAdd() {
        return this.first + this.second;
    }
    
    /**
     * Performs a subtract operation
     * @return First and second numbers subtracted from each other.
     */
    private int performSubtract() {
        return this.first - this.second;
    }
    
    /**
     * Performs a multiply operation
     * @return First and second numbers multiplied by each other.
     */
    private int performMultiply() {
        return this.first * this.second;
    }
    
    /**
     * Performs a divide operation
     * @return First and second numbers divided by each other.
     */
    private int performDivide() {
        return this.first / this.second;
    }
    
    /**
     * Performs a modulo operation
     * @return The remaining after first is divided by second.
     */
    private int performModulo() {
        return this.first % this.second;
    }
}
