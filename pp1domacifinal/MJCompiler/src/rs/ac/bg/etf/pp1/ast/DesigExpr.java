// generated with ast extension for cup
// version 0.8
// 13/0/2019 19:40:31


package rs.ac.bg.etf.pp1.ast;

public class DesigExpr extends Designator {

    private DesigExprBegin DesigExprBegin;
    private Expr Expr;

    public DesigExpr (DesigExprBegin DesigExprBegin, Expr Expr) {
        this.DesigExprBegin=DesigExprBegin;
        if(DesigExprBegin!=null) DesigExprBegin.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
    }

    public DesigExprBegin getDesigExprBegin() {
        return DesigExprBegin;
    }

    public void setDesigExprBegin(DesigExprBegin DesigExprBegin) {
        this.DesigExprBegin=DesigExprBegin;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesigExprBegin!=null) DesigExprBegin.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesigExprBegin!=null) DesigExprBegin.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesigExprBegin!=null) DesigExprBegin.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesigExpr(\n");

        if(DesigExprBegin!=null)
            buffer.append(DesigExprBegin.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesigExpr]");
        return buffer.toString();
    }
}
