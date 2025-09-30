package bashit;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface IOBashit {

    public void println(Object text);

    public void print(Object text);

    public String nextLine();

    public int radix();

    public void useRadix(int radix);

    public int nextInt(int radix);

    public int nextInt();

    public double nextDouble();

    public float nextFloat();

    public long nextLong(int radix);

    public long nextLong();

    public short nextShort(int radix);

    public short nextShort();

    public byte nextByte(int radix);

    public byte nextByte();

    public BigDecimal nextBigDecimal();

    public BigInteger nextBigInteger(int radix);

    public BigInteger nextBigInteger();
}

