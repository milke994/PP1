// generated with ast extension for cup
// version 0.8
// 13/0/2019 19:40:31


package rs.ac.bg.etf.pp1.ast;

public class InterfaceD extends ConsVarClass {

    private InterfaceDecl InterfaceDecl;

    public InterfaceD (InterfaceDecl InterfaceDecl) {
        this.InterfaceDecl=InterfaceDecl;
        if(InterfaceDecl!=null) InterfaceDecl.setParent(this);
    }

    public InterfaceDecl getInterfaceDecl() {
        return InterfaceDecl;
    }

    public void setInterfaceDecl(InterfaceDecl InterfaceDecl) {
        this.InterfaceDecl=InterfaceDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(InterfaceDecl!=null) InterfaceDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(InterfaceDecl!=null) InterfaceDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(InterfaceDecl!=null) InterfaceDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("InterfaceD(\n");

        if(InterfaceDecl!=null)
            buffer.append(InterfaceDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [InterfaceD]");
        return buffer.toString();
    }
}
