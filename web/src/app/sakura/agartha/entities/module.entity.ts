import { EntityBasic } from 'src/app/models/entity.basic';

export class ModuleEntity implements EntityBasic {

    private _moduleId: string;
    private _moduleName: string;
    private _description: string;
    private _group: string;
    private _interface: string;
    private _structure: object;
    private _installed: boolean;

    public constructor(moduleId: string) {
        this._moduleId = moduleId;
    }

    public id(): string {
        return this._moduleId;
    }

    get moduleId(): string {
        return this._moduleId;
    }

    get moduleName(): string {
        return this._moduleName;
    }

    set moduleName(moduleName: string) {
        this._moduleName = moduleName;
    }

    get description(): string {
        return this._description;
    }

    set description(descriptin: string) {
        this._description = descriptin;
    }

    get group(): string {
        return this._group;
    }

    set group(group: string) {
        this._group = group;
    }

    get interface(): string {
        return this._interface;
    }

    set interface(type: string) {
        this._interface = type;
    }

    get structure(): object {
        return this._structure;
    }

    set structure(structure: object) {
        this._structure = structure;
    }

    get installed() {
        return this._installed;
    }

    set installed(installed: boolean) {
        this._installed = installed;
    }
}