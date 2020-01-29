export interface Map<T, K> {

    put(key: T, val: K): void;

    get(key: T): K;

    has(key: T): boolean;

    iterate(callback: (T, K) => void): void;
}