package support;

//链表的节点
public class ListNode {

    public ListNode(int value) {
        this.value = value;
    }

    public int value;
    public base.ListNode nextNode = null;

    @Override
    public String toString() {
        return "" + value;
    }
}
