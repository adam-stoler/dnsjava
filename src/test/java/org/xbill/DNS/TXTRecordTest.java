package org.xbill.DNS;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import java.io.IOException;

public class TXTRecordTest {
  @Test
  void basicRdata() throws IOException {
    String str = "test";
    System.out.println(str);
    printBytes(stringToWire(str));
  }

  @Test
  void weirdButValidRdata() throws IOException {
    String str = "\\\"";
    System.out.println(str);
    printBytes(stringToWire(str));
  }

  @Test
  void middleQuoteRdata() throws IOException {
    String str = "a\"b";
    System.out.println(str);
    printBytes(stringToWire(str));
  }

  @Test
  void weirderButValidRdata() throws IOException {
    String str = "\\\\\"48";
    System.out.println(str);
    printBytes(stringToWire(str));
  }

  public void printBytes(byte[] bytes) {
    System.out.print("[ ");
    for (byte b : bytes) {
      System.out.print((int) b);
      System.out.print(" ");
    }
    System.out.println("]");
  }

  public static byte[] stringToWire(String str) throws IOException {
    Tokenizer t = new Tokenizer(str);
    TXTRecord record = new TXTRecord();
    record.rdataFromString(t, null);
    DNSOutput out = new DNSOutput();
    record.rrToWire(out, null, true);
    return out.toByteArray();
  }

  public static String wireToString(byte[] bytes) throws IOException {
    DNSInput in = new DNSInput(bytes);
    TXTRecord record = new TXTRecord();
    record.rrFromWire(in);
    return record.rdataToString();
  }

  public static String stringToWireToString(String str) throws IOException {
    return wireToString(stringToWire(str));
  }
}
