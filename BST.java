import java.lang.Integer;
import java.lang.String;

class BST {
	Node x;

	public BST() {
		x = null;
	}

	// Lab1
	// cau 1
	public void createTree(String strKey) {
		String s[] = strKey.split(" ");
		for (String w : s) {
			int a = Integer.valueOf(w);
			put(a);
		}
	}

	// cau 2
	public void lnr(Node x) {
		if (x != null) {
			lnr(x.left);
			System.out.print(x.key + " ");
			lnr(x.right);
		}
	}

	public void indeclnr() {
		lnr(x);
	}

	// cau 3
	public void rnl(Node x) {
		if (x != null) {
			rnl(x.right);
			System.out.print(x.key + " ");
			rnl(x.left);
		}
	}

	public void indecrnl() {
		rnl(x);
	}

	// cau 4
	public boolean contains(Integer key) {
		if (key == null)
			throw new IllegalArgumentException("Argument to contains() is null");
		return get(key) != null;
	}

	// cau 5
	public Integer get(Integer key) {
		return get(x, key);
	}

	public Integer get(Node x, Integer key) {
		if (key == null)
			throw new IllegalArgumentException("calls get() with a null key");
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			return get(x.left, key);
		else if (cmp > 0)
			return get(x.right, key);
		else
			return x.key;
	}

	public void put(Integer key) {
		if (key == null)
			throw new IllegalArgumentException("It's a null key");
		x = put(x, key);
	}

	public Node put(Node x, Integer key) {
		if (x == null)
			return new Node(key, 1, 0);
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			x.left = put(x.left, key);
		else if (cmp > 0)
			x.right = put(x.right, key);
		else
			x.key = key;
		x.size = 1 + size(x.left) + size(x.right);
		return x;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public int size() {
		return size(x);
	}

	public int size(Node x) {
		if (x == null)
			return 0;
		else
			return x.size;
	}

	public int height() {
		return height(x);
	}

	public int height(Node x) {
		if (x == null)
			return 0;
		else {
			int lHeight = height(x.left);
			int rHeight = height(x.right);
			if (lHeight > rHeight)
				return (lHeight + 1);
			else
				return (rHeight + 1);
		}
	}

	// cau 6
	public Integer sum(Node x) {
		if (x == null)
			return 0;
		else
			return sum(x.left) + sum(x.right) + x.key;
	}

	// cau 7
	public Integer sum() {
		return sum(x);
	}

	public Node min(Node x) {
		if (x == null)
			return null;
		if (x.left == null)
			return x;
		else
			return min(x.left);
	}

	public Node max(Node x) {
		if (x == null)
			return null;
		if (x.right == null)
			return x;
		else
			return max(x.right);
	}

	// Lab02
	public void deleteMin() {
		x = deleteMin(x);
	}

	public Node deleteMin(Node x) {
		if (x.left == null)
			return x.right;
		x.left = deleteMin(x.left);
		size();
		height();
		return x;
	}

	public void delete(Integer key) {
		if (key == null)
			throw new IllegalArgumentException("It's a null key");
		x = delete(x, key);
	}

	public Node delete(Node x, Integer key) {
		if (x == null)
			return null;
		else {
			if (contains(key) == false)
				return null;
			else {
				int cmp = key.compareTo(x.key);
				if (cmp < 0)
					x.left = delete(x.left, key);
				else if (cmp > 0)
					x.right = delete(x.right, key);
				else {
					if (x.right == null)
						return x.left;
					if (x.left == null)
						return x.right;
					Node t = x;
					x = min(t.right);
					x.right = deleteMin(t.right);
					x.left = t.left;
					size();
					height();
				}
			}
			return x;
		}
	}

	public void deleteMax() {
		x = deleteMax(x);
	}

	public Node deleteMax(Node x) {
		if (x.right == null)
			return x.left;
		x.right = deleteMax(x.right);
		size();
		height();
		return x;
	}

	public void delete1(Integer key) {
		if (key == null)
			throw new IllegalArgumentException("Key is null");
		else
			x = delete1(x, key);
	}

	public Node delete1(Node x, Integer key) {
		if (x == null)
			return null;
		else {
			int cmp = key.compareTo(x.key);
			if (cmp < 0)
				x.left = delete1(x.left, key);
			else if (cmp > 0)
				x.right = delete1(x.right, key);
			else {
				if (x.left == null)
					return x.right;
				if (x.right == null)
					return x.left;
				Node t = x;
				x = max(x.left);
				x.left = deleteMax(t.left);
				x.right = t.right;
				size();
				height();
			}
			return x;
		}
	}

	public static void main(String arr[]) {
		BST tree = new BST();
		tree.createTree("60 32 51 85 23 15 2 46 75 36");
		System.out.print("Cay theo thu tu tang dan: ");
		tree.indeclnr();
		System.out.print("\nCay theo thu tu giam dan: ");
		tree.indecrnl();
		System.out.println("\nKiem tra phan tu:" + tree.contains(33));
		System.out.println("Chieu cao cua cay: " + tree.height());
		System.out.println("Tong gia tri cac khoa trong cay: " + tree.sum());
		System.out.println("DeleteMin:");
		tree.deleteMin();
		tree.indeclnr();
		System.out.println("\nDelete:");
		tree.delete(23);
		tree.indeclnr();
		System.out.println("\nDeleteMax:");
		tree.deleteMax();
		tree.indeclnr();
		System.out.println("\nDelete1:");
		tree.delete1(32);
		tree.indeclnr();
	}
}
