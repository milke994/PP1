// generated with ast extension for cup
// version 0.8
// 13/0/2019 19:40:31


package rs.ac.bg.etf.pp1.ast;

public class NewFactor extends Factor {

    private Type Type;
    private OptionSBracketExpr OptionSBracketExpr;

    public NewFactor (Type Type, OptionSBracketExpr OptionSBracketExpr) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.OptionSBracketExpr=OptionSBracketExpr;
        if(OptionSBracketExpr!=null) OptionSBracketExpr.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public OptionSBracketExpr getOptionSBracketExpr() {
        return OptionSBracketExpr;
    }

    public void setOptionSBracketExpr(OptionSBracketExpr OptionSBracketExpr) {
        this.OptionSBracketExpr=OptionSBracketExpr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(OptionSBracketExpr!=null) OptionSBracketExpr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(OptionSBracketExpr!=null) OptionSBracketExpr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(OptionSBracketExpr!=null) OptionSBracketExpr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("NewFactor(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OptionSBracketExpr!=null)
            buffer.append(OptionSBracketExpr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [NewFactor]");
        return buffer.toString();
    }
}
