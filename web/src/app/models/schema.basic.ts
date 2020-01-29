import { EntityBasic } from './entity.basic';
import { SakuraStringUtil } from '../sakura.string.util';

export class SchemaBasic implements EntityBasic {

    private _schema: object;
    private _text: string;
    private _format: string;

    constructor(schema: any) {
        this._text = schema.text;
        this._format = schema.format;
    }

    getContent(): string {
        return this.format();
    }

    translate(context: any): string {
        return this.getContent().replace(/{([^}]+?)}/g, s => {
            var ref = s.substr(1, s.length - 2);
            return ref ? (context[ref] || ref) : '';
        });
    }

    format(): string {
        if(this._format) {
            return SakuraStringUtil.format(this._format, this._text);
        }else {
            return this._text;
        }
    }
}