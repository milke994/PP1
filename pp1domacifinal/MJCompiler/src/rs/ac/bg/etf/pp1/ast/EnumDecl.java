// generated with ast extension for cup
// version 0.8
// 13/0/2019 19:40:31


package rs.ac.bg.etf.pp1.ast;

public class EnumDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private String identName;
    private EnumIdentList EnumIdentList;

    public EnumDecl (String identName, EnumIdentList EnumIdentList) {
        this.identName=identName;
        this.EnumIdentList=EnumIdentList;
        if(EnumIdentList!=null) EnumIdentList.setParent(this);
    }

    public String getIdentName() {
        return identName;
    }

    public void setIdentName(String identName) {
        this.identName=identName;
    }

    public EnumIdentList getEnumIdentList() {
        return EnumIdentList;
    }

    public void setEnumIdentList(EnumIdentList EnumIdentList) {
        this.EnumIdentList=EnumIdentList;
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
        if(EnumIdentList!=null) EnumIdentList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(EnumIdentList!=null) EnumIdentList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(EnumIdentList!=null) EnumIdentList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("EnumDecl(\n");

        buffer.append(" "+tab+identName);
        buffer.append("\n");

        if(EnumIdentList!=null)
            buffer.append(EnumIdentList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [EnumDecl]");
        return buffer.toString();
    }
}
