/* BasicParser.java */
/* Generated By:JavaCC: Do not edit this line. BasicParser.java */
package de.haupz.basicode.parser;

import java.util.ArrayList;
import java.util.List;

import de.haupz.basicode.ast.*;

public class BasicParser implements BasicParserConstants {

// Parser.

//
// basic structures
//
  final public 
ProgramNode program() throws ParseException {List<LineNode> lines = new ArrayList<>();
    LineNode l;
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case NUMBER:{
        ;
        break;
        }
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      l = line();
lines.add(l);
    }
{if ("" != null) return new ProgramNode(lines);}
    throw new Error("Missing return statement in function");
}

  final public LineNode line() throws ParseException {List<StatementNode> statements = new ArrayList<>();
    int num;
    StatementNode st;
    num = lineNumber();
    st = statement();
statements.add(st);
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case COLON:{
        ;
        break;
        }
      default:
        jj_la1[1] = jj_gen;
        break label_2;
      }
      jj_consume_token(COLON);
      st = statement();
statements.add(st);
    }
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case EOL:{
      jj_consume_token(EOL);
      break;
      }
    case 0:{
      jj_consume_token(0);
      break;
      }
    default:
      jj_la1[2] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
{if ("" != null) return new LineNode(num, statements);}
    throw new Error("Missing return statement in function");
}

  final public int lineNumber() throws ParseException {Token n;
    n = jj_consume_token(NUMBER);
{if ("" != null) return Integer.parseInt(n.image);}
    throw new Error("Missing return statement in function");
}

//
// statements
//
  final public 
StatementNode statement() throws ParseException {Token t;
    ExpressionNode e;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case REM:{
      t = jj_consume_token(REM);
{if ("" != null) return new RemNode(t.image);}
      break;
      }
    case PRINT:{
      jj_consume_token(PRINT);
      e = expression();
{if ("" != null) return new PrintNode(e);}
      break;
      }
    default:
      jj_la1[3] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
}

//
// expressions
//
  final public 
ExpressionNode expression() throws ParseException {ExpressionNode e;
    Token t;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case ABS:{
      jj_consume_token(ABS);
      jj_consume_token(62);
      e = expression();
      jj_consume_token(63);
{if ("" != null) return new AbsNode(e);}
      break;
      }
    case ASC:{
      jj_consume_token(ASC);
      jj_consume_token(62);
      e = expression();
      jj_consume_token(63);
{if ("" != null) return new AscNode(e);}
      break;
      }
    case ATN:{
      jj_consume_token(ATN);
      jj_consume_token(62);
      e = expression();
      jj_consume_token(63);
{if ("" != null) return new AtnNode(e);}
      break;
      }
    case CHRS:{
      jj_consume_token(CHRS);
      jj_consume_token(62);
      e = expression();
      jj_consume_token(63);
{if ("" != null) return new ChrsNode(e);}
      break;
      }
    case COS:{
      jj_consume_token(COS);
      jj_consume_token(62);
      e = expression();
      jj_consume_token(63);
{if ("" != null) return new CosNode(e);}
      break;
      }
    case NUMBER:
    case INTEGER:{
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case NUMBER:{
        t = jj_consume_token(NUMBER);
        break;
        }
      case INTEGER:{
        t = jj_consume_token(INTEGER);
        break;
        }
      default:
        jj_la1[4] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
{if ("" != null) return new IntegerNode(Integer.parseInt(t.image));}
      break;
      }
    case FLOAT:{
      t = jj_consume_token(FLOAT);
{if ("" != null) return new DoubleNode(Double.parseDouble(t.image));}
      break;
      }
    case STRING:{
      t = jj_consume_token(STRING);
{if ("" != null) return new StringNode(t.image.substring(1, t.image.length() - 1));}
      break;
      }
    default:
      jj_la1[5] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
}

  /** Generated Token Manager. */
  public BasicParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[6];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
	   jj_la1_init_0();
	   jj_la1_init_1();
	}
	private static void jj_la1_init_0() {
	   jj_la1_0 = new int[] {0x10,0x8,0x5,0x0,0x30,0xf570,};
	}
	private static void jj_la1_init_1() {
	   jj_la1_1 = new int[] {0x0,0x0,0x0,0x14,0x0,0x0,};
	}

  /** Constructor with InputStream. */
  public BasicParser(java.io.InputStream stream) {
	  this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public BasicParser(java.io.InputStream stream, String encoding) {
	 try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
	 token_source = new BasicParserTokenManager(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 6; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
	  ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
	 try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
	 token_source.ReInit(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 6; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public BasicParser(java.io.Reader stream) {
	 jj_input_stream = new SimpleCharStream(stream, 1, 1);
	 token_source = new BasicParserTokenManager(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 6; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
	if (jj_input_stream == null) {
	   jj_input_stream = new SimpleCharStream(stream, 1, 1);
	} else {
	   jj_input_stream.ReInit(stream, 1, 1);
	}
	if (token_source == null) {
 token_source = new BasicParserTokenManager(jj_input_stream);
	}

	 token_source.ReInit(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 6; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public BasicParser(BasicParserTokenManager tm) {
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 6; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(BasicParserTokenManager tm) {
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 6; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
	 Token oldToken;
	 if ((oldToken = token).next != null) token = token.next;
	 else token = token.next = token_source.getNextToken();
	 jj_ntk = -1;
	 if (token.kind == kind) {
	   jj_gen++;
	   return token;
	 }
	 token = oldToken;
	 jj_kind = kind;
	 throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
	 if (token.next != null) token = token.next;
	 else token = token.next = token_source.getNextToken();
	 jj_ntk = -1;
	 jj_gen++;
	 return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
	 Token t = token;
	 for (int i = 0; i < index; i++) {
	   if (t.next != null) t = t.next;
	   else t = t.next = token_source.getNextToken();
	 }
	 return t;
  }

  private int jj_ntk_f() {
	 if ((jj_nt=token.next) == null)
	   return (jj_ntk = (token.next=token_source.getNextToken()).kind);
	 else
	   return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
	 jj_expentries.clear();
	 boolean[] la1tokens = new boolean[64];
	 if (jj_kind >= 0) {
	   la1tokens[jj_kind] = true;
	   jj_kind = -1;
	 }
	 for (int i = 0; i < 6; i++) {
	   if (jj_la1[i] == jj_gen) {
		 for (int j = 0; j < 32; j++) {
		   if ((jj_la1_0[i] & (1<<j)) != 0) {
			 la1tokens[j] = true;
		   }
		   if ((jj_la1_1[i] & (1<<j)) != 0) {
			 la1tokens[32+j] = true;
		   }
		 }
	   }
	 }
	 for (int i = 0; i < 64; i++) {
	   if (la1tokens[i]) {
		 jj_expentry = new int[1];
		 jj_expentry[0] = i;
		 jj_expentries.add(jj_expentry);
	   }
	 }
	 int[][] exptokseq = new int[jj_expentries.size()][];
	 for (int i = 0; i < jj_expentries.size(); i++) {
	   exptokseq[i] = jj_expentries.get(i);
	 }
	 return new ParseException(token, exptokseq, tokenImage);
  }

  private boolean trace_enabled;

/** Trace enabled. */
  final public boolean trace_enabled() {
	 return trace_enabled;
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

                          }
