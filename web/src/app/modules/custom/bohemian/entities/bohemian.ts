import { EntityBasic } from 'src/app/models/entity.basic';
import { SchemaBasic } from 'src/app/models/schema.basic';
import { Hashmap } from 'src/app/models/data-structure/hashmap.model';

export class Bohemian implements EntityBasic {

    private _menuId: string;
    private _menuIcon: string;
    private _imgType: string;
    private _menuSchema: Hashmap<SchemaBasic>;

    constructor(id: string, imgType: string, menuIcon: string = null) {
        this._menuId = id;
        this._imgType = imgType;
        this._menuIcon = menuIcon ? (id + '-' + menuIcon) : null;
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

    get menuIcon() {
        return this._menuIcon;
    }

    set menuIcon(menuIcon: string) {
        this._menuIcon = menuIcon;
    }

    setSchema(schema: Hashmap<SchemaBasic>) {
        this._menuSchema = schema;
    }

    getSchema(key: string): any {
        return this._menuSchema.get(key).getContent();
    }

}
