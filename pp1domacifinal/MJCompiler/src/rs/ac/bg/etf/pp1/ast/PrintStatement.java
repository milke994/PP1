// generated with ast extension for cup
// version 0.8
// 13/0/2019 19:40:31


package rs.ac.bg.etf.pp1.ast;

public class PrintStatement extends Statement {

    private Expr Expr;
    private OptionNumConst OptionNumConst;

    public PrintStatement (Expr Expr, OptionNumConst OptionNumConst) {
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.OptionNumConst=OptionNumConst;
        if(OptionNumConst!=null) OptionNumConst.setParent(this);
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public OptionNumConst getOptionNumConst() {
        return OptionNumConst;
    }

    public void setOptionNumConst(OptionNumConst OptionNumConst) {
        this.OptionNumConst=OptionNumConst;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expr!=null) Expr.accept(visitor);
        if(OptionNumConst!=null) OptionNumConst.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(OptionNumConst!=null) OptionNumConst.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(OptionNumConst!=null) OptionNumConst.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("PrintStatement(\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OptionNumConst!=null)
            buffer.append(OptionNumConst.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [PrintStatement]");
        return buffer.toString();
    }
}
