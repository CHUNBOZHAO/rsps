package com.izhuixin.rsps.common.object;

public class Pair<A, B>{

	private static final long serialVersionUID = 6977780982504003462L;

	public static <A, B> Pair<A, B> of(A first, B second) {
        return new Pair<A, B>(first, second);
	}

	public A first;
	public B second;

	private Pair(A first, B second) {
		this.first = first;
		this.second = second;
	}

	public A getFirst() {
		return first;
	}

	public void setFirst(A first) {
		this.first = first;
	}

	public B getSecond() {
		return second;
	}

	public void setSecond(B second) {
		this.second = second;
	}
}