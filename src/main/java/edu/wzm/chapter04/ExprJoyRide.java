package edu.wzm.chapter04;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * @author: wangzhiming
 * @Date: 2020/9/12
 * @version:
 * @Description:
 * @exmaple:
 *      193
 *      a = 5
 *      b = 6
 *      a+b*2
 *      (1+2)*3
 */
public class ExprJoyRide {

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            execLibExpr();
        } else {
            execExpr();
        }
    }

    private static void execExpr() throws Exception  {
        ANTLRInputStream input = new ANTLRInputStream(System.in);
        ExprLexer lexer = new ExprLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ExprParser parser = new ExprParser(tokens);

        // 从prog规则开始进行语法分析
        ParseTree tree = parser.prog();
        System.out.println(tree.toStringTree(parser));
    }

    private static void execLibExpr() throws Exception  {
        ANTLRInputStream input = new ANTLRInputStream(System.in);
        LibExprLexer lexer = new LibExprLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LibExprParser parser = new LibExprParser(tokens);

        // 从prog规则开始进行语法分析
        ParseTree tree = parser.prog();
        System.out.println(tree.toStringTree(parser));
    }
}
