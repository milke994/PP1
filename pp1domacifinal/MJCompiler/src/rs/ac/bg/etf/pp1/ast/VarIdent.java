// generated with ast extension for cup
// version 0.8
// 13/0/2019 19:40:31


package rs.ac.bg.etf.pp1.ast;

public class VarIdent implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private String varName;
    private OptionSBracket OptionSBracket;

    public VarIdent (String varName, OptionSBracket OptionSBracket) {
        this.varName=varName;
        this.OptionSBracket=OptionSBracket;
        if(OptionSBracket!=null) OptionSBracket.setParent(this);
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName=varName;
    }

    public OptionSBracket getOptionSBracket() {
        return OptionSBracket;
    }

    public void setOptionSBracket(OptionSBracket OptionSBracket) {
        this.OptionSBracket=OptionSBracket;
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
        if(OptionSBracket!=null) OptionSBracket.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OptionSBracket!=null) OptionSBracket.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OptionSBracket!=null) OptionSBracket.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarIdent(\n");

        buffer.append(" "+tab+varName);
        buffer.append("\n");

        if(OptionSBracket!=null)
            buffer.append(OptionSBracket.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarIdent]");
        return buffer.toString();
    }
}
