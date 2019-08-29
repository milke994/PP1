// generated with ast extension for cup
// version 0.8
// 13/0/2019 19:40:31


package rs.ac.bg.etf.pp1.ast;

public class Inheritance extends ClassDecl {

    private String I1;
    private OptionExtend OptionExtend;
    private OptionImplement OptionImplement;
    private VarsMethods VarsMethods;
    private OptionMethodDecl OptionMethodDecl;

    public Inheritance (String I1, OptionExtend OptionExtend, OptionImplement OptionImplement, VarsMethods VarsMethods, OptionMethodDecl OptionMethodDecl) {
        this.I1=I1;
        this.OptionExtend=OptionExtend;
        if(OptionExtend!=null) OptionExtend.setParent(this);
        this.OptionImplement=OptionImplement;
        if(OptionImplement!=null) OptionImplement.setParent(this);
        this.VarsMethods=VarsMethods;
        if(VarsMethods!=null) VarsMethods.setParent(this);
        this.OptionMethodDecl=OptionMethodDecl;
        if(OptionMethodDecl!=null) OptionMethodDecl.setParent(this);
    }

    public String getI1() {
        return I1;
    }

    public void setI1(String I1) {
        this.I1=I1;
    }

    public OptionExtend getOptionExtend() {
        return OptionExtend;
    }

    public void setOptionExtend(OptionExtend OptionExtend) {
        this.OptionExtend=OptionExtend;
    }

    public OptionImplement getOptionImplement() {
        return OptionImplement;
    }

    public void setOptionImplement(OptionImplement OptionImplement) {
        this.OptionImplement=OptionImplement;
    }

    public VarsMethods getVarsMethods() {
        return VarsMethods;
    }

    public void setVarsMethods(VarsMethods VarsMethods) {
        this.VarsMethods=VarsMethods;
    }

    public OptionMethodDecl getOptionMethodDecl() {
        return OptionMethodDecl;
    }

    public void setOptionMethodDecl(OptionMethodDecl OptionMethodDecl) {
        this.OptionMethodDecl=OptionMethodDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(OptionExtend!=null) OptionExtend.accept(visitor);
        if(OptionImplement!=null) OptionImplement.accept(visitor);
        if(VarsMethods!=null) VarsMethods.accept(visitor);
        if(OptionMethodDecl!=null) OptionMethodDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OptionExtend!=null) OptionExtend.traverseTopDown(visitor);
        if(OptionImplement!=null) OptionImplement.traverseTopDown(visitor);
        if(VarsMethods!=null) VarsMethods.traverseTopDown(visitor);
        if(OptionMethodDecl!=null) OptionMethodDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OptionExtend!=null) OptionExtend.traverseBottomUp(visitor);
        if(OptionImplement!=null) OptionImplement.traverseBottomUp(visitor);
        if(VarsMethods!=null) VarsMethods.traverseBottomUp(visitor);
        if(OptionMethodDecl!=null) OptionMethodDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Inheritance(\n");

        buffer.append(" "+tab+I1);
        buffer.append("\n");

        if(OptionExtend!=null)
            buffer.append(OptionExtend.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OptionImplement!=null)
            buffer.append(OptionImplement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarsMethods!=null)
            buffer.append(VarsMethods.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OptionMethodDecl!=null)
            buffer.append(OptionMethodDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Inheritance]");
        return buffer.toString();
    }
}
