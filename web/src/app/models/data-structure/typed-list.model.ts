import { List } from './list.model';

export class TypedList<T> implements List {
    list: T[];
    private index: number;

    constructor(list: T[] = null) {
        this.list = list || [];
        this.index = 0;
    }

    size(): number {
        return this.list.length;
    }

    add(item: T) {
        this.list.push(item);
    }

    addAll(items: T[]) {
        this.list = this.list.concat(items);
    }

    addList(list: TypedList<T>) {
        var items: T[] = list.toArray();
        for(var i=0; i<items.length; i++) {
            this.add(items[i]);
        }
    }

    removeEmpty() {
        var temp = [];
        for(var i=0; i<this.list.length; i++) {
            if(!this.isEmpty(this.list[i])) {
                temp.push(this.list[i]);
            }
        }
        this.list = temp;
        return this;
    }

    isEmpty(element: T): boolean {
        return element === null;
    }

    toArray(): T[] {
        return this.list;
    }

    hasItem(): boolean {
        return this.list.length > 0;
    }

    hasNext(): boolean {
        return this.index == this.list.length - 1;
    } 

    each(callback: (item: T) => void): void {
        for(var i=0; i<this.list.length; i++) {
            callback(this.list[i]);
        }
    }

    filter(callback: (item: T) => boolean): T[] {
        var items: T[] = [];
        for(var i=0; i<this.list.length; i++) {
            if(callback(this.list[i])) {
                items.push(this.list[i]);
            };
        }
        return items;
    };
}
