import { Hashmap } from 'src/app/models/data-structure/hashmap.model';

export class AgarthaDataSourceManager {

    private storage: Hashmap<Hashmap<any>>;

    constructor() {
        this.storage = new Hashmap<Hashmap<any>>();
    }

    public get(hashcode: string): Hashmap<any> {
        return this.storage.get(hashcode);
    }

    public getOne(hashcode: string, key: string): any {
        if(this.storage.has(hashcode)) {
            return this.storage.get(hashcode).get(key);
        }
    }

    public put(hashcode: string, key: string, val: any) {
        if(!this.storage.has(hashcode)) {
            this.storage.put(hashcode, new Hashmap<any>());
        }
        this.storage.get(hashcode).put(key, val);
    }
}