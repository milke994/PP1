// generated with ast extension for cup
// version 0.8
// 13/0/2019 19:40:31


package rs.ac.bg.etf.pp1.ast;

public class EnumIdent implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private String identName;
    private EnumIdentValue EnumIdentValue;

    public EnumIdent (String identName, EnumIdentValue EnumIdentValue) {
        this.identName=identName;
        this.EnumIdentValue=EnumIdentValue;
        if(EnumIdentValue!=null) EnumIdentValue.setParent(this);
    }

    public String getIdentName() {
        return identName;
    }

    public void setIdentName(String identName) {
        this.identName=identName;
    }

    public EnumIdentValue getEnumIdentValue() {
        return EnumIdentValue;
    }

    public void setEnumIdentValue(EnumIdentValue EnumIdentValue) {
        this.EnumIdentValue=EnumIdentValue;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(EnumIdentValue!=null) EnumIdentValue.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(EnumIdentValue!=null) EnumIdentValue.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(EnumIdentValue!=null) EnumIdentValue.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("EnumIdent(\n");

        buffer.append(" "+tab+identName);
        buffer.append("\n");

        if(EnumIdentValue!=null)
            buffer.append(EnumIdentValue.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [EnumIdent]");
        return buffer.toString();
    }
}
