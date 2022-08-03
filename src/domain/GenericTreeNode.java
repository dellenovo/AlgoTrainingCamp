package domain;

public class GenericTreeNode<T> {
    public T val;
    public GenericTreeNode<T> left;
    public GenericTreeNode<T> right;

    public GenericTreeNode(T val) {
        this.val = val;
    }
}
