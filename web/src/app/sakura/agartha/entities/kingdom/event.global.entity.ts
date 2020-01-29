import { KingdomContentService } from './../../kingdom/services/kingdom.content.service';
import { EntityBasic } from 'src/app/models/entity.basic';
import { SakuraDataSourceManager } from 'src/app/sakura.datasource.manager';
import { AgarthaAppContainer } from '../../services/container';
import { Hashmap } from 'src/app/models/data-structure/hashmap.model';
import { KingdomEventService } from '../../kingdom/mainframe/services/kingdom.event.service';
import { SchemaBasic } from 'src/app/models/schema.basic';

export class KingdomEventBasic implements EntityBasic {

    private _eventId: string;
    private _moduleId: string;
    private _title: string;
    private _type: string;
    private _createDate: Date;
    private _definition: any;
    private _quests: any[];
    private _publishCondition: number;
    private _profile: Hashmap<any>;
    private _appStorage: SakuraDataSourceManager;
    private _service: KingdomContentService;
    private _schema: Hashmap<SchemaBasic>;
    private _confirmed: boolean;
    private _offerId: string;
    private _dataReady: boolean;
    private _hooks: any;

    public constructor(eventId: string, service: KingdomContentService) {
        this._eventId = eventId;
        this._service = service;
        this._appStorage = AgarthaAppContainer.getAppStorage();
        this._hooks = {};
    }

    action(actionId: string) {
        this._service.action(actionId, this);
    }

    getService(key: string) {
        return this._service.service(key);
    }

    getProfile(key: string): any {
        return this._profile.get(key);
    }

    setProfile(key: string, value: any) {
        this._profile.put(key, value);
    }

    getAppData(key: string): any {
        return this._appStorage.get(key);
    }

    hook(name: string, action: CallableFunction) {
        this._hooks[name] = action;
    }

    get moduleId(): string {
        return this._moduleId;
    }

    set moduleId(moduleId: string) {
        this._moduleId = moduleId;
    }

    get eventId(): string {
        return this._eventId;
    }

    get title(): string {
        return this._title;
    }

    set title(title: string) {
        this._title = title;
    }

    get type(): string {
        return this._type;
    }

    set type(type: string) {
        this._type = type;
        this._moduleId = 'kingdom-' + type;
    }

    get createDate(): Date {
        return this._createDate;
    }

    set createDate(createDate: Date) {
        this._createDate = createDate;
    }

    get definition(): any {
        return this._definition;
    }

    set definition(definition: any) {
        this._definition = definition;
    }

    get publishCondition(): number {
        return this._publishCondition;
    }

    set publishCondition(publishCondition: number) {
        this._publishCondition = publishCondition;
    }

    get schema(): Hashmap<SchemaBasic> {
        return this._schema;
    }

    set schema(schema: Hashmap<SchemaBasic>) {
        this._schema = schema;
    }

    get quests(): any[] {
        return this._quests;
    }

    set quests(quests: any[]) {
        this._quests = quests;
    }

    get confirmed(): boolean {
        return this._confirmed;
    }

    set confirmed(confirmed: boolean) {
        if(!this._confirmed) {
            this._confirmed = confirmed;
            this._service.build(this._service).confirm(this);
        }
    }

    get offerId(): string {
        return this._offerId;
    }

    set offerId(offerId: string) {
        this._offerId = offerId;
    }

    get dataReady(): boolean {
        return this._dataReady;
    }

    set dataReady(dataReady: boolean) {
        this._dataReady = dataReady;
        if(this._hooks['dataReady']) {
            this._hooks['dataReady'](this._dataReady);
        }
    }
}