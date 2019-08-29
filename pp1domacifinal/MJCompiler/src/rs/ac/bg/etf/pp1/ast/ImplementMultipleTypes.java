// generated with ast extension for cup
// version 0.8
// 13/0/2019 19:40:31


package rs.ac.bg.etf.pp1.ast;

public class ImplementMultipleTypes extends ImplTypeList {

    private ImplTypeList ImplTypeList;
    private Type Type;

    public ImplementMultipleTypes (ImplTypeList ImplTypeList, Type Type) {
        this.ImplTypeList=ImplTypeList;
        if(ImplTypeList!=null) ImplTypeList.setParent(this);
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
    }

    public ImplTypeList getImplTypeList() {
        return ImplTypeList;
    }

    public void setImplTypeList(ImplTypeList ImplTypeList) {
        this.ImplTypeList=ImplTypeList;
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ImplTypeList!=null) ImplTypeList.accept(visitor);
        if(Type!=null) Type.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ImplTypeList!=null) ImplTypeList.traverseTopDown(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ImplTypeList!=null) ImplTypeList.traverseBottomUp(visitor);
        if(Type!=null) Type.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ImplementMultipleTypes(\n");

        if(ImplTypeList!=null)
            buffer.append(ImplTypeList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ImplementMultipleTypes]");
        return buffer.toString();
    }
}
