import { EntityBasic } from 'src/app/models/entity.basic';
import { SchemaBasic } from 'src/app/models/schema.basic';
import { Hashmap } from 'src/app/models/data-structure/hashmap.model';

export class Bohemian implements EntityBasic {

    private _menuId: string;
    private _imgType: string;
    private _menuSchema: Hashmap<SchemaBasic>;

    constructor(id: string, imgType: string) {
        this._menuId = id;
        this._imgType = imgType;
    }

    get menuId() {
        return this._menuId;
    }

    get imgType() {
        return this._imgType;
    }

    set imgType(imgType: string) {
        this._imgType = imgType;
    }

    setSchema(schema: Hashmap<SchemaBasic>) {
        this._menuSchema = schema;
    }

    getSchema(key: string): any {
        return this._menuSchema.get(key).getContent();
    }

}
