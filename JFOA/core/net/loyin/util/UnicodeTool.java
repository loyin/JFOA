package net.loyin.util;

import java.lang.Character.UnicodeBlock;

public class UnicodeTool {
	/**
	  * 中文转换成 unicode
	  * @author fanhui
	  * 2007-3-15
	  * @param inStr
	  * @return
	  */
	 public  String encodeUnicode(String inStr) {
	        char[] myBuffer = inStr.toCharArray();
	       
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < inStr.length(); i++) {
	        	char ch=myBuffer[i];
	        	if((int)ch<10){
	        		sb.append("\\u000"+(int)ch);
	        		continue;
	        	}
	         UnicodeBlock ub = UnicodeBlock.of(ch);
	            if(ub == UnicodeBlock.BASIC_LATIN){
	             //英文及数字等
	             sb.append(myBuffer[i]);
	            }else if(ub == UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS){
	             //全角半角字符
	             int j = (int) myBuffer[i] - 65248;
	             sb.append((char)j);
	            }else{
	             //汉字
	             int s = (int) myBuffer[i];
	                String hexS = Integer.toHexString(Math.abs(s));
	                String unicode = "\\u"+hexS;
	             sb.append(unicode.toLowerCase());
	            }
	        }
	        return sb.toString();
	    }
	 
	 /**
	  * unicode 转换成 中文
	  * @author fanhui
	  * 2007-3-15
	  * @param theString
	  * @return
	  */
	 public  String decodeUnicode(String theString) {
	  char aChar;
	  int len = theString.length();
	  StringBuffer outBuffer = new StringBuffer(len);
	  for (int x = 0; x < len;) {
	   aChar = theString.charAt(x++);
	   if (aChar == '\\') {
	    aChar = theString.charAt(x++);
	    if (aChar == 'u') {
	     // Read the xxxx
	     int value = 0;
	     for (int i = 0; i < 4; i++) {
	      aChar = theString.charAt(x++);
	      switch (aChar) {
	      case '0':
	      case '1':
	      case '2':
	      case '3':
	      case '4':
	      case '5':
	      case '6':
	      case '7':
	      case '8':
	      case '9':
	       value = (value << 4) + aChar - '0';
	       break;
	      case 'a':
	      case 'b':
	      case 'c':
	      case 'd':
	      case 'e':
	      case 'f':
	       value = (value << 4) + 10 + aChar - 'a';
	       break;
	      case 'A':
	      case 'B':
	      case 'C':
	      case 'D':
	      case 'E':
	      case 'F':
	       value = (value << 4) + 10 + aChar - 'A';
	       break;
	      default:
	       throw new IllegalArgumentException(
	         "Malformed   \\uxxxx   encoding.");
	      }
	     }
	     outBuffer.append((char) value);
	    } else {
	     if (aChar == 't')
	      aChar = '\t';
	     else if (aChar == 'r')
	      aChar = '\r';
	     else if (aChar == 'n')
	      aChar = '\n';
	     else if (aChar == 'f')
	      aChar = '\f';
	     outBuffer.append(aChar);
	    }
	   } else
	    outBuffer.append(aChar);
	  }
	  return outBuffer.toString();
	 }
public  static void main(String[] args){
	System.out.print(new UnicodeTool().decodeUnicode("\u7fc1\u6d77\u6e05"));
}
}
