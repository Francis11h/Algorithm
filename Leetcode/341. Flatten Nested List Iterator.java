341. Flatten Nested List Iterator


Given a nested list of integers, implement an iterator to flatten it.
Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Input: [[1,1],2,[1,1]]
Output: [1,1,2,1,1]
Explanation: By calling next repeatedly until hasNext returns false, 
             "the order of elements" returned by next should be: [1,1,2,1,1].

Input: [1,[4,[6]]]
Output: [1,4,6]
Explanation: By calling next repeatedly until hasNext returns false, 
             the order of elements returned by next should be: [1,4,6]


// This is the interface that allows for creating nested lists.
// You should not implement it, or speculate 推测 about its implementation

public interface NestedInteger {

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
}


//法 1 用 stack 反向入栈

public class NestedIterator implements Iterator<Integer> {

    Stack<NestedInteger> stack = new Stack<>();
    public NestedIterator(List<NestedInteger> nestedList) {
        //反向入栈
        for (int i = nestedList.size() - 1; i >= 0; i--) {
            stack.push(nestedList.get(i));
        }
    }

    @Override
    public Integer next() {
        // 必须要 先检查 是不是 hasNext() 这是 现实中 Iterator
        return hasNext() ? stack.pop().getInteger() : null;
    }

    @Override
    public boolean hasNext() {
        while (!stack.isEmpty()) {
            NestedInteger cur = stack.peek();
            if (cur.isInteger()) {
                return true;    //这里直接返回 也避免了 hasNext() 不能被多次call的bug
            }
            stack.pop();
            for (int i = cur.getList().size() - 1; i >= 0; i--) {
                stack.push(cur.getList().get(i));
            }
        }
        return false;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */




现实中 Iterator 必须满足  next() 可单独call 多次 hasNext() call
    Typical iterator should succeed as well in situation

    next() call without hasNext() call
    multiple hasNext() calls




这才是 本题 最优雅的解法
摆明了 递归 + 用 现实的iterator对象 


public class NestedIterator implements Iterator<Integer> {
    
    List<Integer> flattenList = new ArrayList<>();
    Iterator<Integer> iterator;

    public NestedIterator(List<NestedInteger> nestedList) {
        helper(nestedList);
        iterator = flattenList.iterator();          // a.iterator() 得到一个集合的 iterator对象
    }
    
    private void helper(List<NestedInteger> nestedList) {
        for (int i = 0; i < nestedList.size(); i++) {
            NestedInteger cur = nestedList.get(i);
            if (cur.isInteger()) {
                flattenList.add(cur.getInteger());
            } else {
                helper(cur.getList());
            }
        }
    }

    @Override
    public Integer next() {
        return iterator.next();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }
}


T:O(m+n) is better for the preprocess version.
m = number of nested lists (as we have to push/pop each of them)
n = number of elements across all lists (as we have to iterate through them)
It would be O(1) for next and hasNext since we flattened the list.




