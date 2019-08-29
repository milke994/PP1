// generated with ast extension for cup
// version 0.8
// 13/0/2019 19:40:31


package rs.ac.bg.etf.pp1.ast;

public class DesigFactor extends Factor {

    private Designator Designator;
    private OptionParenActPars OptionParenActPars;

    public DesigFactor (Designator Designator, OptionParenActPars OptionParenActPars) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.OptionParenActPars=OptionParenActPars;
        if(OptionParenActPars!=null) OptionParenActPars.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public OptionParenActPars getOptionParenActPars() {
        return OptionParenActPars;
    }

    public void setOptionParenActPars(OptionParenActPars OptionParenActPars) {
        this.OptionParenActPars=OptionParenActPars;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(OptionParenActPars!=null) OptionParenActPars.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(OptionParenActPars!=null) OptionParenActPars.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(OptionParenActPars!=null) OptionParenActPars.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesigFactor(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OptionParenActPars!=null)
            buffer.append(OptionParenActPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesigFactor]");
        return buffer.toString();
    }
}
