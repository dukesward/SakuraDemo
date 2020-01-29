import { EntityBasic } from '../entity.basic';
import { SakuraProxyService } from 'src/app/sakura.proxy.service';

export class SakuraEventBasic implements EntityBasic {

    protected _service: SakuraProxyService;
    protected _eventId: string;
    protected _clock: number;
    protected _eventData: any;
    protected _entity: EntityBasic;
    protected _hooks: any;

    constructor(eventId: string, entity?: EntityBasic) {
        this._eventId = eventId;
        this._clock = 0;
        this._entity = entity;
        this._hooks = {};
    }

    get eventId(): string {
        return this._eventId;
    };

    set eventId(eventId: string) {
        this._eventId = eventId;
    }

    get clock(): number {
        return this._clock;
    }

    get service(): SakuraProxyService {
        return this._service;
    }

    set service(service: SakuraProxyService) {
        this._service = service;
    }

    get eventData(): any {
        return this._eventData;
    }

    set eventData(eventData: any) {
        this._eventData = eventData;
    }

    set entity(entity: EntityBasic) {
        if(!this._entity) this._entity = entity;
    }

    hook(name: string, action: CallableFunction) {
        this._hooks[name] = action;
    }

    getEntityProperty(property: string) {
        return this._entity ? this._entity[property] : null;
    }

    completed(target: any, timer: number = -1): boolean {
        return this._clock > timer;
    }

    finish(target: any): void {}

    init(): any {
        return {};
    }

    tick() {
        this._clock += 1;
    }
}