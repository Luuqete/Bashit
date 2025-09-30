package bashLogic;

import textProcessor.TextProcessor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.InputMismatchException;

import UI.UI;
import bashit.IOBashit;
public class BashLogic implements IOBashit {

    private TextProcessor textProcessor;
    private UI ui;
    private Object inputLock;

    private int radix;
    public BashLogic() {
        inputLock = new Object();
        textProcessor = new TextProcessor();
        ui = new UI(textProcessor,inputLock);

        ui.setText("=)\n");
        textProcessor.setCurrentText("=)\n");
        radix = 10;
    }

    public synchronized void println(Object text) {
        print(text);
        print("\n");
    }

    public synchronized void print(Object text) {
        String updatedText = textProcessor.appendText(String.valueOf(text));
        ui.setText(updatedText);
    }

    public synchronized String nextLine(){
        ui.enableInput();
        synchronized (inputLock) {
            try {
                inputLock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ui.disableInput();
        String currentText = ui.getInput();
        String input = textProcessor.getInput(currentText);
        textProcessor.setCurrentText(currentText);  
        return input.trim();
    }

    public int radix() {
        return radix;
    }

    private void checkRadix(int radix) throws IllegalArgumentException {
        if(radix < 2 || radix > 36){
            throw new IllegalArgumentException("Radix must be between 2 and 36");
        }
    }

    public void useRadix(int radix) {
        checkRadix(radix);
        this.radix = radix;
    }

    public int nextInt(int radix) {
        checkRadix(radix);
        int toRet = 0;
        String input = nextLine();
        try{
            toRet = Integer.parseInt(input, radix);
        } catch (NumberFormatException e) {
            throw new InputMismatchException("Input is not a valid integer");
        }
        return toRet;
    }

    public int nextInt() {
        return nextInt(radix);
    }

    public double nextDouble() {
        double toRet = 0;
        String input = nextLine();
        try {
            toRet = Double.parseDouble(input);
        } catch (NumberFormatException e) {
            throw new InputMismatchException("Input is not a valid double");
        }
        return toRet;
    }

    public float nextFloat() {
        String input = nextLine();
        float toRet = 0;
        try {
            toRet = Float.parseFloat(input);
        } catch (NumberFormatException e) {
            throw new InputMismatchException("Input is not a valid float");
        }
        return toRet;
    }

    public long nextLong(int radix) {
        checkRadix(radix);
        String input = nextLine();
        long toRet = 0;
        try {
            toRet = Long.parseLong(input, radix);
        } catch (NumberFormatException e) {
            throw new InputMismatchException("Input is not a valid long");
        }
        return toRet;
    }

    public long nextLong() {
        return nextLong(radix);
    }

    public short nextShort(int radix) {
        checkRadix(radix);
        String input = nextLine();
        short toRet = 0;
        try {
            toRet = Short.parseShort(input, radix);
        } catch (NumberFormatException e) {
            throw new InputMismatchException("Input is not a valid short");
        }
        return toRet;
    }

    public short nextShort() {
        return nextShort(radix);
    }

    public byte nextByte(int radix) {
        checkRadix(radix);
        String input = nextLine();
        byte toRet = 0;
        try {
            toRet = Byte.parseByte(input, radix);
        } catch (NumberFormatException e) {
            throw new InputMismatchException("Input is not a valid byte");
        }
        return toRet;
    }

    public byte nextByte() {
        return nextByte(radix);
    }

    public BigDecimal nextBigDecimal() {
        String input = nextLine();
        BigDecimal toRet = BigDecimal.ZERO;
        try {
            toRet = new BigDecimal(input);
        } catch (NumberFormatException e) {
            throw new InputMismatchException("Input is not a valid BigDecimal");
        }
        return toRet;
    }

    public BigInteger nextBigInteger(int radix) {
        checkRadix(radix);
        BigInteger toRet = BigInteger.ZERO;
        String input = nextLine();
        try {
            toRet = new BigInteger(input, radix);
        } catch (NumberFormatException e) {
            throw new InputMismatchException("Input is not a valid BigInteger");
        }
        return toRet;
    }

    public BigInteger nextBigInteger() {
        return nextBigInteger(radix);
    }

}
