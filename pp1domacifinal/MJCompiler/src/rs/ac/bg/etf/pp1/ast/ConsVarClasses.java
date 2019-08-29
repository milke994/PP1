// generated with ast extension for cup
// version 0.8
// 13/0/2019 19:40:31


package rs.ac.bg.etf.pp1.ast;

public class ConsVarClasses extends ConsVarClassList {

    private ConsVarClassList ConsVarClassList;
    private ConsVarClass ConsVarClass;

    public ConsVarClasses (ConsVarClassList ConsVarClassList, ConsVarClass ConsVarClass) {
        this.ConsVarClassList=ConsVarClassList;
        if(ConsVarClassList!=null) ConsVarClassList.setParent(this);
        this.ConsVarClass=ConsVarClass;
        if(ConsVarClass!=null) ConsVarClass.setParent(this);
    }

    public ConsVarClassList getConsVarClassList() {
        return ConsVarClassList;
    }

    public void setConsVarClassList(ConsVarClassList ConsVarClassList) {
        this.ConsVarClassList=ConsVarClassList;
    }

    public ConsVarClass getConsVarClass() {
        return ConsVarClass;
    }

    public void setConsVarClass(ConsVarClass ConsVarClass) {
        this.ConsVarClass=ConsVarClass;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConsVarClassList!=null) ConsVarClassList.accept(visitor);
        if(ConsVarClass!=null) ConsVarClass.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConsVarClassList!=null) ConsVarClassList.traverseTopDown(visitor);
        if(ConsVarClass!=null) ConsVarClass.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConsVarClassList!=null) ConsVarClassList.traverseBottomUp(visitor);
        if(ConsVarClass!=null) ConsVarClass.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConsVarClasses(\n");

        if(ConsVarClassList!=null)
            buffer.append(ConsVarClassList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConsVarClass!=null)
            buffer.append(ConsVarClass.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConsVarClasses]");
        return buffer.toString();
    }
}
