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
    ExpressionNode f;
    List<ExpressionNode> l = new ArrayList<>();
    e = and_expression();
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case OR:{
        ;
        break;
        }
      default:
        jj_la1[4] = jj_gen;
        break label_3;
      }
      jj_consume_token(OR);
      f = and_expression();
l.add(f);
    }
{if ("" != null) return l.isEmpty() ? e : new OrNode(e, l);}
    throw new Error("Missing return statement in function");
}

  final public ExpressionNode and_expression() throws ParseException {ExpressionNode e;
    ExpressionNode f;
    List<ExpressionNode> l = new ArrayList<>();
    e = equality_expression();
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case AND:{
        ;
        break;
        }
      default:
        jj_la1[5] = jj_gen;
        break label_4;
      }
      jj_consume_token(AND);
      f = equality_expression();
l.add(f);
    }
{if ("" != null) return l.isEmpty() ? e : new AndNode(e, l);}
    throw new Error("Missing return statement in function");
}

  final public ExpressionNode equality_expression() throws ParseException {ExpressionNode e;
    ExpressionNode f;
    e = relational_expression();
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case 51:
    case 52:{
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 51:{
        jj_consume_token(51);
        f = relational_expression();
{if ("" != null) return new EqNode(e, f);}
        break;
        }
      case 52:{
        jj_consume_token(52);
        f = relational_expression();
{if ("" != null) return new NeqNode(e, f);}
        break;
        }
      default:
        jj_la1[6] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
      }
    default:
      jj_la1[7] = jj_gen;
      ;
    }
{if ("" != null) return e;}
    throw new Error("Missing return statement in function");
}

  final public ExpressionNode relational_expression() throws ParseException {ExpressionNode e;
    ExpressionNode f = null;
    Token t = null;
    e = additive_expression();
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case 53:
    case 54:
    case 55:
    case 56:{
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 53:{
        jj_consume_token(53);
        f = additive_expression();
{if ("" != null) return new LtNode(e, f);}
        break;
        }
      case 54:{
        jj_consume_token(54);
        f = additive_expression();
{if ("" != null) return new LeqNode(e, f);}
        break;
        }
      case 55:{
        jj_consume_token(55);
        f = additive_expression();
{if ("" != null) return new GtNode(e, f);}
        break;
        }
      case 56:{
        jj_consume_token(56);
        f = additive_expression();
{if ("" != null) return new GeqNode(e, f);}
        break;
        }
      default:
        jj_la1[8] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
      }
    default:
      jj_la1[9] = jj_gen;
      ;
    }
{if ("" != null) return e;}
    throw new Error("Missing return statement in function");
}

  final public ExpressionNode additive_expression() throws ParseException {ExpressionNode e;
    ExpressionNode f;
    e = multiplicative_expression();
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 57:
      case 58:{
        ;
        break;
        }
      default:
        jj_la1[10] = jj_gen;
        break label_5;
      }
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 57:{
        jj_consume_token(57);
        f = multiplicative_expression();
e = new AddNode(e, f);
        break;
        }
      case 58:{
        jj_consume_token(58);
        f = multiplicative_expression();
e = new SubtractNode(e, f);
        break;
        }
      default:
        jj_la1[11] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
{if ("" != null) return e;}
    throw new Error("Missing return statement in function");
}

  final public ExpressionNode multiplicative_expression() throws ParseException {ExpressionNode e;
    ExpressionNode f;
    e = power_expression();
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 59:
      case 60:{
        ;
        break;
        }
      default:
        jj_la1[12] = jj_gen;
        break label_6;
      }
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 59:{
        jj_consume_token(59);
        f = power_expression();
e = new MultiplyNode(e, f);
        break;
        }
      case 60:{
        jj_consume_token(60);
        f = power_expression();
e = new DivideNode(e, f);
        break;
        }
      default:
        jj_la1[13] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
{if ("" != null) return e;}
    throw new Error("Missing return statement in function");
}

  final public ExpressionNode power_expression() throws ParseException {ExpressionNode e;
    ExpressionNode f;
    e = unary_expression();
    label_7:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 61:{
        ;
        break;
        }
      default:
        jj_la1[14] = jj_gen;
        break label_7;
      }
      jj_consume_token(61);
      f = unary_expression();
e = new PowerNode(e, f);
    }
{if ("" != null) return e;}
    throw new Error("Missing return statement in function");
}

  final public ExpressionNode unary_expression() throws ParseException {ExpressionNode e;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case 58:{
      jj_consume_token(58);
      e = unary_expression();
{if ("" != null) return new NegateNode(e);}
      break;
      }
    case NUMBER:
    case INTEGER:
    case FLOAT:
    case STRING:
    case ABS:
    case ASC:
    case ATN:
    case CHRS:
    case COS:
    case INT:
    case LEFTS:
    case MIDS:
    case NOT:
    case RIGHTS:
    case SGN:
    case SIN:
    case SQR:
    case TAN:
    case VAL:
    case IDENTIFIER:
    case 62:{
      e = unary_expression_not_plus_minus();
{if ("" != null) return e;}
      break;
      }
    default:
      jj_la1[15] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
}

  final public ExpressionNode unary_expression_not_plus_minus() throws ParseException {ExpressionNode e;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case NOT:{
      jj_consume_token(NOT);
      e = unary_expression();
{if ("" != null) return new NotNode(e);}
      break;
      }
    case NUMBER:
    case INTEGER:
    case FLOAT:
    case STRING:
    case ABS:
    case ASC:
    case ATN:
    case CHRS:
    case COS:
    case INT:
    case LEFTS:
    case MIDS:
    case RIGHTS:
    case SGN:
    case SIN:
    case SQR:
    case TAN:
    case VAL:
    case IDENTIFIER:
    case 62:{
      e = primary_expression();
{if ("" != null) return e;}
      break;
      }
    default:
      jj_la1[16] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
}

  final public ExpressionNode primary_expression() throws ParseException {ExpressionNode e;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case NUMBER:
    case INTEGER:
    case FLOAT:
    case STRING:
    case IDENTIFIER:{
      e = literal();
{if ("" != null) return e;}
      break;
      }
    case 62:{
      jj_consume_token(62);
      e = expression();
      jj_consume_token(63);
{if ("" != null) return e;}
      break;
      }
    case ABS:
    case ASC:
    case ATN:
    case CHRS:
    case COS:
    case INT:
    case LEFTS:
    case MIDS:
    case RIGHTS:
    case SGN:
    case SIN:
    case SQR:
    case TAN:
    case VAL:{
      e = builtin_call();
{if ("" != null) return e;}
      break;
      }
    default:
      jj_la1[17] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
}

  final public ExpressionNode literal() throws ParseException {Token t;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
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
        jj_la1[18] = jj_gen;
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
    case IDENTIFIER:{
      t = jj_consume_token(IDENTIFIER);
{if ("" != null) return new StringNode(t.image);}
      break;
      }
    default:
      jj_la1[19] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
}

  final public ExpressionNode builtin_call() throws ParseException {ExpressionNode e;
    ExpressionNode f;
    ExpressionNode g;
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
    case INT:{
      jj_consume_token(INT);
      jj_consume_token(62);
      e = expression();
      jj_consume_token(63);
{if ("" != null) return new IntNode(e);}
      break;
      }
    case LEFTS:{
      jj_consume_token(LEFTS);
      jj_consume_token(62);
      e = expression();
      jj_consume_token(64);
      f = expression();
      jj_consume_token(63);
{if ("" != null) return new LeftsNode(e, f);}
      break;
      }
    case MIDS:{
      jj_consume_token(MIDS);
      jj_consume_token(62);
      e = expression();
      jj_consume_token(64);
      f = expression();
      jj_consume_token(64);
      g = expression();
      jj_consume_token(63);
{if ("" != null) return new MidsNode(e, f, g);}
      break;
      }
    case RIGHTS:{
      jj_consume_token(RIGHTS);
      jj_consume_token(62);
      e = expression();
      jj_consume_token(64);
      f = expression();
      jj_consume_token(63);
{if ("" != null) return new RightsNode(e, f);}
      break;
      }
    case SGN:{
      jj_consume_token(SGN);
      jj_consume_token(62);
      e = expression();
      jj_consume_token(63);
{if ("" != null) return new SgnNode(e);}
      break;
      }
    case SIN:{
      jj_consume_token(SIN);
      jj_consume_token(62);
      e = expression();
      jj_consume_token(63);
{if ("" != null) return new SinNode(e);}
      break;
      }
    case SQR:{
      jj_consume_token(SQR);
      jj_consume_token(62);
      e = expression();
      jj_consume_token(63);
{if ("" != null) return new SqrNode(e);}
      break;
      }
    case TAN:{
      jj_consume_token(TAN);
      jj_consume_token(62);
      e = expression();
      jj_consume_token(63);
{if ("" != null) return new TanNode(e);}
      break;
      }
    case VAL:{
      jj_consume_token(VAL);
      jj_consume_token(62);
      e = expression();
      jj_consume_token(63);
{if ("" != null) return new ValNode(e);}
      break;
      }
    default:
      jj_la1[20] = jj_gen;
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
  final private int[] jj_la1 = new int[21];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static private int[] jj_la1_2;
  static {
	   jj_la1_init_0();
	   jj_la1_init_1();
	   jj_la1_init_2();
	}
	private static void jj_la1_init_0() {
	   jj_la1_0 = new int[] {0x10,0x8,0x5,0x0,0x0,0x400,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x51207b70,0x51207b70,0x11207b70,0x30,0x170,0x11207a00,};
	}
	private static void jj_la1_init_1() {
	   jj_la1_1 = new int[] {0x0,0x0,0x0,0xa,0x1,0x0,0x180000,0x180000,0x1e00000,0x1e00000,0x6000000,0x6000000,0x18000000,0x18000000,0x20000000,0x44064740,0x40064740,0x40064740,0x0,0x40000,0x24740,};
	}
	private static void jj_la1_init_2() {
	   jj_la1_2 = new int[] {0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,};
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
	 for (int i = 0; i < 21; i++) jj_la1[i] = -1;
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
	 for (int i = 0; i < 21; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public BasicParser(java.io.Reader stream) {
	 jj_input_stream = new SimpleCharStream(stream, 1, 1);
	 token_source = new BasicParserTokenManager(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 21; i++) jj_la1[i] = -1;
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
	 for (int i = 0; i < 21; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public BasicParser(BasicParserTokenManager tm) {
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 21; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(BasicParserTokenManager tm) {
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 21; i++) jj_la1[i] = -1;
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
	 boolean[] la1tokens = new boolean[65];
	 if (jj_kind >= 0) {
	   la1tokens[jj_kind] = true;
	   jj_kind = -1;
	 }
	 for (int i = 0; i < 21; i++) {
	   if (jj_la1[i] == jj_gen) {
		 for (int j = 0; j < 32; j++) {
		   if ((jj_la1_0[i] & (1<<j)) != 0) {
			 la1tokens[j] = true;
		   }
		   if ((jj_la1_1[i] & (1<<j)) != 0) {
			 la1tokens[32+j] = true;
		   }
		   if ((jj_la1_2[i] & (1<<j)) != 0) {
			 la1tokens[64+j] = true;
		   }
		 }
	   }
	 }
	 for (int i = 0; i < 65; i++) {
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
