package test;

public class HuffmanTree {
	
/*
 * 创建结点
 * 结点包含：权重，父级，左子级和右子级结点
 */
static class Node<E>{
	Node<E> parent,left,right;
	int weight;
	public Node(int weight,Node<E> parent,Node<E> left,Node<E> right){
		this.weight = weight;
		this.parent = parent;
		this.left = left;
		this.right = right;
	}
	public Node(int weight){
		this(weight,null,null,null);
	}
}

/**
 * 创建哈夫曼树
 * @param weights 叶子节点的权重集合的数组
 * @return 哈夫曼树根结点
 */
public static Node<?> BuildHTree(int[] weights){
	
	//判断是否为空
	if(weights == null) throw new RuntimeException("叶子节点为空");
	
	//创建结点数组
	Node<Integer>[] HTree = new Node[weights.length];
	
	//将各个权重当成一棵树，加入到森林数组中
	for (int i = 0 ; i<weights.length ; i++){
		Node<Integer> theNode = new Node<Integer>(weights[i]);
		HTree[i] = theNode;
	}
	
	//如果只有一个节点，直接返回
	if(weights.length == 1) return HTree[0];
	
	//创建指针，当前最小的两个权重值及其位置
	Node<Integer> p = null , q = null;
	int x1 = 0,x2 = 0;
	int index1 = 0 , index2 = 0;
	
	//循环（叶子节点个数-1）次
	for (int i = 0 ; i<weights.length-1;i++){
		
		//x1，x2取最大值
		x1 = Integer.MAX_VALUE;
		x2 = Integer.MAX_VALUE;
		
		//循环找出最小的两个权重及其位置，并将其移出森林
		for (int j = 0 ; j<HTree.length ; j++){
			if (HTree[j] == null) continue;
			if (HTree[j].weight <= x1){
				p = HTree[j];
				x1 = HTree[j].weight;
				index1 = j;
			}
		}
		HTree[index1] = null;
		
		for (int j = 0 ; j<HTree.length ; j++){
			if (HTree[j] == null) continue;
			if (HTree[j].weight <= x2){
				q = HTree[j];
				x2 = HTree[j].weight;
				index2 = j;
			}
		}

		HTree[index2] = null;
		
		
		//创建新的结点，并构建新的树
		Node<Integer> newNode = new Node<>(p.weight+q.weight);
		newNode.left = p;
		newNode.right = q;
		p.parent = newNode;
		q.parent = newNode;
		
		//将树加入到森林中
		HTree[index1] = newNode;
		
	}
	
	//找到最后一颗树的位置
	for (int i = 0 ; i<weights.length ; i++){
		if (HTree[i] != null) {x1 = i ; break;}
	}
	
	//输出根结点
	return HTree[x1];
}


/**
 * 二叉树的遍历（使用的之前的递归遍历）
 */
public static class TreeTraver{
	 //先序
	public static void preOrder(Node<?> root){
		if(root == null) return;
		System.out.print(root.weight + " ");
		preOrder(root.left);
		preOrder(root.right);
	}
	//中序
	private static void inOrder(Node<?> root){
		if (root == null) return;
		inOrder(root.left);
		System.out.print(root.weight + " ");
	    inOrder(root.right);
	}
	//后序
	private static void  postOrder(Node<?> root){
		if (root == null) return;
		postOrder(root.left);
		postOrder(root.right);
	    System.out.print(root.weight + " ");
	}
}

//主函数测试
public static void main(String[] args){
	int[] weights = {1,3,5,7,6};
	Node<?> BTree = BuildHTree(weights);
		
	System.out.print("先序遍历序列：");
	TreeTraver.preOrder(BTree);
	System.out.println();
	
	System.out.print("中序遍历序列：");
	TreeTraver.inOrder(BTree);
	System.out.println();
	
	System.out.print("后序遍历序列：");
	TreeTraver.postOrder(BTree);
	System.out.println();
		
	}
}
