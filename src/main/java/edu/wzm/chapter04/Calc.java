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
 *      done
 */
public class Calc {

    public static void main(String[] args) throws Exception {
        ANTLRInputStream input = new ANTLRInputStream(System.in);
        LabeledExprLexer lexer = new LabeledExprLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LabeledExprParser parser = new LabeledExprParser(tokens);
        ParseTree tree = parser.prog();

        EvalVisitor eval = new EvalVisitor();
        eval.visit(tree);
        System.out.println(eval.memory);
    }
}
