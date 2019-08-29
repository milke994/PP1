// generated with ast extension for cup
// version 0.8
// 13/0/2019 19:40:31


package rs.ac.bg.etf.pp1.ast;

public class ReturnOnly extends Statement {

    private OptionExprRet OptionExprRet;

    public ReturnOnly (OptionExprRet OptionExprRet) {
        this.OptionExprRet=OptionExprRet;
        if(OptionExprRet!=null) OptionExprRet.setParent(this);
    }

    public OptionExprRet getOptionExprRet() {
        return OptionExprRet;
    }

    public void setOptionExprRet(OptionExprRet OptionExprRet) {
        this.OptionExprRet=OptionExprRet;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(OptionExprRet!=null) OptionExprRet.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OptionExprRet!=null) OptionExprRet.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OptionExprRet!=null) OptionExprRet.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ReturnOnly(\n");

        if(OptionExprRet!=null)
            buffer.append(OptionExprRet.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ReturnOnly]");
        return buffer.toString();
    }
}
