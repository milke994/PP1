// generated with ast extension for cup
// version 0.8
// 13/0/2019 19:40:31


package rs.ac.bg.etf.pp1.ast;

public class OptionParenActParsYes extends OptionParenActPars {

    private ActualPars ActualPars;

    public OptionParenActParsYes (ActualPars ActualPars) {
        this.ActualPars=ActualPars;
        if(ActualPars!=null) ActualPars.setParent(this);
    }

    public ActualPars getActualPars() {
        return ActualPars;
    }

    public void setActualPars(ActualPars ActualPars) {
        this.ActualPars=ActualPars;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ActualPars!=null) ActualPars.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ActualPars!=null) ActualPars.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ActualPars!=null) ActualPars.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OptionParenActParsYes(\n");

        if(ActualPars!=null)
            buffer.append(ActualPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OptionParenActParsYes]");
        return buffer.toString();
    }
}
