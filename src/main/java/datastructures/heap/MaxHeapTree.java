package datastructures.heap;

import java.util.Vector;

public class MaxHeapTree {

    Vector<Integer> arr ;

    public MaxHeapTree() {
        arr = new Vector<Integer>();
        //for( int i=0;i<a.length;i++)arr.add(a[i]);
    }

    private int parent( int i ) { return (i-1>>1); }
    private int left( int i ) { return (i<<1)|1; }
    private int right( int i ) { return (i<<1)+2; }

    public void heapify( int i) {
        int l = left(i);
        int r = right(i);
        int larger = i;
        if( l<arr.size() && arr.get(l)>arr.get(i) ) {
            larger = l;
        }
        if( r<arr.size() && arr.get(r)>arr.get(larger) ) {
            larger = r;
        }
        if( larger != i ) {
            swap(larger,i);
            heapify(larger);
        }
    }

    private void swap(int larger, int i) {
        int temp = arr.get(larger);
        arr.set(larger, arr.get(i));
        arr.set(i, temp);
    }

    public void buildHeap(int a[] ) {
        for( int i=0;i<a.length;i++)arr.add(a[i]);
        for ( int i=((arr.size()-1)/2)-1; i>=0; i--) {
            heapify(i);
        }
    }

    public int peek(){
        return arr.get(0);
    }

    public int poll() {
        if( arr.isEmpty() ) {
            System.err.println("heap underflow");
            return -1;
        }
        int max = arr.get(0);
        arr.set(0, arr.get(arr.size()-1));
        arr.remove((int)arr.size()-1);
        heapify(0);
        return max;
    }

    public void offer(int x) {
        arr.add(x);
        int i = arr.size()-1;
        while (i>0 && arr.get(parent(i))<arr.get(i)) {
            swap(i,parent(i));
            i = parent(i);
        }
    }

    public static void main(String[] args) {
        int a[]={1, 2, 3, 4, 5, 6, 7};
        MaxHeapTree maxheap = new MaxHeapTree();
        int k=1;
        for(int i=0;i<a.length;i++)maxheap.offer(a[i]);
        for(int i=0;i<k-1;i++)maxheap.poll();
        int ans=(int)maxheap.poll();
        System.out.println(ans);
    }
}