import { EntityBasic } from './../../../../models/entity.basic';
import { AgarthaServiceBasic } from '../../services/service.basic';
import { SakuraEventBasic } from 'src/app/models/utility/event.basic';
import { Hashmap } from 'src/app/models/data-structure/hashmap.model';

export class AgarthaEventBasic extends SakuraEventBasic {

    protected _service: AgarthaServiceBasic;
    private _sourceId: string;

    constructor(eventId: string, service: AgarthaServiceBasic, sourceId: string, entity?: EntityBasic) {
        super(eventId, entity);
        this._service = service;
        this._sourceId = sourceId;
        this._eventData = null;
    }

    completed(target: any, timer: number = -1): boolean {
        return this.validate(target) || this._clock > timer;
    }

    validate(target: any): boolean {
        return this._service.buildValidator(target).test();
    }

    finish(target: any): void {
        if(this._hooks['finish']) {
            target = this._hooks['finish'].call(this, this._eventId, target);
        }
        this._service.store(this._eventId, target);
    }

    init(): Hashmap<any> {
        return this._service.getInitData();
    }

    get service(): AgarthaServiceBasic {
        return this._service;
    }

    set service(service: AgarthaServiceBasic) {
        this._service = service;
    }

    get sourceId(): string {
        return this._sourceId;
    }

    set sourceId(sourceId: string) {
        this._sourceId = sourceId;
    }
}