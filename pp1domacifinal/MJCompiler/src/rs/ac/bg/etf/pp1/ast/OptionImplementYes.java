// generated with ast extension for cup
// version 0.8
// 13/0/2019 19:40:31


package rs.ac.bg.etf.pp1.ast;

public class OptionImplementYes extends OptionImplement {

    private ImplTypeList ImplTypeList;

    public OptionImplementYes (ImplTypeList ImplTypeList) {
        this.ImplTypeList=ImplTypeList;
        if(ImplTypeList!=null) ImplTypeList.setParent(this);
    }

    public ImplTypeList getImplTypeList() {
        return ImplTypeList;
    }

    public void setImplTypeList(ImplTypeList ImplTypeList) {
        this.ImplTypeList=ImplTypeList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ImplTypeList!=null) ImplTypeList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ImplTypeList!=null) ImplTypeList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ImplTypeList!=null) ImplTypeList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OptionImplementYes(\n");

        if(ImplTypeList!=null)
            buffer.append(ImplTypeList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OptionImplementYes]");
        return buffer.toString();
    }
}
