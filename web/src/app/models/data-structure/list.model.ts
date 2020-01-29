import { EntityBasic } from '../entity.basic';

export interface List extends EntityBasic {
    list: any[];

    add(item: any): void;

    addAll(items: any[]): void;

    toArray(): any[];

    hasItem(): boolean;

    hasNext(): boolean;

    each(callback: (item: any) => void): void;

    filter(callback: (item: any) => boolean): any[];
}
