package edu.wzm.chapter04;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wangzhiming
 * @Date: 2020/9/12
 * @version:
 * @Description:
 */
public class EvalVisitor extends LabeledExprBaseVisitor<Integer> {

    Map<String, Integer> memory = new HashMap<>();

    @Override
    public Integer visitPrintExpr(LabeledExprParser.PrintExprContext ctx) {
        int value = visit(ctx.expr());
        System.out.println("EvalVisitor#visitPrintExpr: " + value);
        return 0;
    }

    @Override
    public Integer visitAssign(LabeledExprParser.AssignContext ctx) {
        String id = ctx.ID().getText();
        int value = visit(ctx.expr());
        memory.put(id, value);
        return value;
    }

    @Override
    public Integer visitClear(LabeledExprParser.ClearContext ctx) {
        memory.clear();
        return 0;
    }

    @Override
    public Integer visitParens(LabeledExprParser.ParensContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Integer visitMulDiv(LabeledExprParser.MulDivContext ctx) {
        // 计算左侧子表达式的值
        int left = visit(ctx.expr(0));
        // 计算右侧子表达式的值
        int right = visit(ctx.expr(1));
        if (ctx.op.getType() == LabeledExprParser.MUL) {
            return left * right;
        } else {
            return left / right;
        }
    }

    @Override
    public Integer visitAddSub(LabeledExprParser.AddSubContext ctx) {
        // 计算左侧子表达式的值
        int left = visit(ctx.expr(0));
        // 计算右侧子表达式的值
        int right = visit(ctx.expr(1));
        if (ctx.op.getType() == LabeledExprParser.ADD) {
            return left + right;
        } else {
            return left - right;
        }
    }

    @Override
    public Integer visitId(LabeledExprParser.IdContext ctx) {
        String id = ctx.ID().getText();
        if (memory.containsKey(id)) {
            return memory.get(id);
        }
        return 0;
    }

    @Override
    public Integer visitInt(LabeledExprParser.IntContext ctx) {
        return Integer.valueOf(ctx.INT().getText());
    }
}
