// generated with ast extension for cup
// version 0.8
// 13/0/2019 19:40:31


package rs.ac.bg.etf.pp1.ast;

public class ForStatement extends Statement {

    private DesignatorStatementOption DesignatorStatementOption;
    private ConditionOption ConditionOption;
    private DesignatorStatementOption DesignatorStatementOption1;
    private Statement Statement;

    public ForStatement (DesignatorStatementOption DesignatorStatementOption, ConditionOption ConditionOption, DesignatorStatementOption DesignatorStatementOption1, Statement Statement) {
        this.DesignatorStatementOption=DesignatorStatementOption;
        if(DesignatorStatementOption!=null) DesignatorStatementOption.setParent(this);
        this.ConditionOption=ConditionOption;
        if(ConditionOption!=null) ConditionOption.setParent(this);
        this.DesignatorStatementOption1=DesignatorStatementOption1;
        if(DesignatorStatementOption1!=null) DesignatorStatementOption1.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public DesignatorStatementOption getDesignatorStatementOption() {
        return DesignatorStatementOption;
    }

    public void setDesignatorStatementOption(DesignatorStatementOption DesignatorStatementOption) {
        this.DesignatorStatementOption=DesignatorStatementOption;
    }

    public ConditionOption getConditionOption() {
        return ConditionOption;
    }

    public void setConditionOption(ConditionOption ConditionOption) {
        this.ConditionOption=ConditionOption;
    }

    public DesignatorStatementOption getDesignatorStatementOption1() {
        return DesignatorStatementOption1;
    }

    public void setDesignatorStatementOption1(DesignatorStatementOption DesignatorStatementOption1) {
        this.DesignatorStatementOption1=DesignatorStatementOption1;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorStatementOption!=null) DesignatorStatementOption.accept(visitor);
        if(ConditionOption!=null) ConditionOption.accept(visitor);
        if(DesignatorStatementOption1!=null) DesignatorStatementOption1.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorStatementOption!=null) DesignatorStatementOption.traverseTopDown(visitor);
        if(ConditionOption!=null) ConditionOption.traverseTopDown(visitor);
        if(DesignatorStatementOption1!=null) DesignatorStatementOption1.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorStatementOption!=null) DesignatorStatementOption.traverseBottomUp(visitor);
        if(ConditionOption!=null) ConditionOption.traverseBottomUp(visitor);
        if(DesignatorStatementOption1!=null) DesignatorStatementOption1.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ForStatement(\n");

        if(DesignatorStatementOption!=null)
            buffer.append(DesignatorStatementOption.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConditionOption!=null)
            buffer.append(ConditionOption.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorStatementOption1!=null)
            buffer.append(DesignatorStatementOption1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ForStatement]");
        return buffer.toString();
    }
}
