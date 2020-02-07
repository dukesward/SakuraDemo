import { Map } from './map.model';
import { TypedList } from './typed-list.model';

export class Hashmap<T> implements Map<String, T> {

	private type: string;
	private map: { [key:string]: T };

	constructor() {
		this.map = {};
	}

	put(key: string, val: T) {
		this.map[key] = val;
	}

	get(key: string) {
		return this.has(key) ? this.map[key] : null;
	}

	has(key: string) {
		return this.map[key] != null;
	}

	iterate(callback: (string, T) => void): void {
		var map = this.map;
		Object.keys(map).forEach((k) => callback(k, map[k]));
	};

	toList(): TypedList<T> {
		return new TypedList<T>(Object.values(this.map));
	}
}