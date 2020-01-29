import { TypedList } from './typed-list.model';

export class Stack<T> extends TypedList<T> {

    private itemPointer: number;

    constructor(list: T[] = null) {
        super(list);
    }

    add(item: T) {
        this.list.unshift(item);
    }

    attach(item: T) {
        this.list.push(item);
    }

    next(): T {
        return this.list.shift();
    }
}